angular.module('starter.directive', [])
    .directive("publicHead", ['$rootScope', function (root) {
        return {
            restrict: 'A',
            link: function (scope, ele, attr) {
                scope.$on('$ionicView.beforeEnter', function () {
                    root.appMenu = 'tabs-item-hide';
                    root.hideTop = false;
                    root.topType = "";      //type_1登陆，type_2注册，type_3简单的标题加返回

                    if (angular.isDefined(attr.topTitle)){
                        root.topTitle = attr.topTitle;
                    }
                    if (angular.isDefined(attr.topType)) {
                        root.topType = attr.topType;
                    }
                    if (attr.appMenu == "true") {
                        root.appMenu = '';
                    }
                    if (attr.hideTop == "true") {
                        root.hideTop = true;
                    }
                });
            }
        }
    }])

    .directive("pagePlugin", ['$timeout',function (timeout) {
        return {
            restrict: 'E',
            templateUrl: 'templates/module/pagination.html',
            transclude: true,
            scope: {
                load: '&pageLoad',
                data: '=pageData',
                dataType:'=dataType',
                init: '&pageInit'
            },
            controller: ["$scope", "$attrs", "$element","$xhHandle",function($scope, attr, ele,$xhHandle) {
                var json = {page: 1},
                    $json, pageSize = "10",
                    watch, $root = '',
                    result, loading = false,
                    wait = 'false', isPaging = ( typeof attr.infiniteScroll == 'string'),//true 开启分页  false 为 未开启
                    initData, autoLoad = true;// 初始化数据
                if (attr.waitLoad) {
                    wait = $scope.wait;//是否按需加载数据
                }
                if (attr.autoLoad && attr.autoLoad == "false") {
                }
                $scope.plugin = {
                    refresher: false,//下拉刷新
                    infinite: false,//上拉加载
                    stopPage: true//停止分页
                };//初始插件

                $scope.$on("$destroy", function () {
                    console.log("remove page-plugin");
                    if (attr.handle) {
                        $xhHandle.$destroy(attr.handle);
                    }
                    $scope.$destroy();
                });
                if (attr.root) {
                    $root = "." + attr.root;
                }
                if (attr.pageData) {
                    initData = angular.copy($scope.data);//初始化数据
                }

                var promiseCallback = undefined, successCallback = undefined, errorCallback = undefined;//promise  回调
                $scope.loadMore = function (obj) {//分页加载
                    if (!obj) {
                        json = {page:1};
                    }
                    $json = angular.copy(json);
                    $json.currentPage = $json.page;
                    $json.pageSize = pageSize;
                    var promise = $scope.load({json: $json});//发送请求  返回json  将使用新的请求方式

                    /**
                     * load 返回json 示例代码
                     * {
             *      promise : API,
             *      success : FN code 200 回调
             *      error : FN code! 200 回调
             * }
                     *
                     */

                    //新的promise 回调方式
                    if (angular.isDefined(promise) && angular.isObject(promise) && promise.promise) {
                        promiseCallback = promise.callback;//不再使用
                        successCallback = promise.success;
                        errorCallback = promise.error;
                        promise = promise.promise;
                    }

                    if (angular.isDefined(promise) && attr.pageData) {
                        promise.then(function (_data) {
                            if (promiseCallback && angular.isFunction(promiseCallback)) {
                                promiseCallback(_data);
                            }//成功回调
                            if (_data.code == "200") {
                                var result = _data.data.list, $$result = _data.data.list;
                                result = eval('result' + $root);
                                if (isPaging) {//分页查询
                                    var $initDate = undefined;
                                    if ($json.page == 1) {
                                        $initDate = initData;
                                    } else {
                                        $initDate = $scope.data;
                                    }
                                    if (angular.isArray(result)) {
                                        paging(result, $initDate);
                                    }
                                    json.page = json.page + 1;
                                } else {
                                    $scope.data = result;
                                }

                                //成功回调 -----
                                if (attr.promise) {
                                    $scope.promise({data: $$result});//旧版本回调
                                }
                                if (successCallback && angular.isFunction(successCallback)) {
                                    successCallback($$result);
                                }//成功回调
                            } else {
                                if (errorCallback && angular.isFunction(errorCallback)) {
                                    errorCallback(_data.data);
                                }//失败回调
                                $scope.data="";
                                $scope.plugin.stopPage = false;
                                //alert.alert(_data.message);
                            }
                            broadcast(obj);
                        });
                    } else {
                        broadcast(obj, 2000);
                    }
                };
                if (attr.handle) {
                    $xhHandle.$setHandle(attr.handle, {
                        reload: function () {
                            $scope.loadMore(false);
                        }
                    });
            }

                var paging = function (result, initData) {
                    if (result.length < Number(pageSize)) {
                        $scope.plugin.stopPage = false;
                    } else {
                        $scope.plugin.stopPage = true;
                    }
                    $scope.data = (initData = initData.concat(result));
                };//分页逻辑

                var broadcast = function (obj, time) {
                    time = typeof time == 'undefined' ? 500 : time;
                    console.log("loading more");
                    if (angular.isDefined(obj)){
                        if (obj) {//true
                            $scope.$broadcast('scroll.infiniteScrollComplete');
                        } else { //false
                            var timer = timeout(function () {
                                $scope.$broadcast('scroll.refreshComplete');
                                timeout.cancel(timer);
                            }, time);
                        }
                    } else {
                        $scope.$broadcast('scroll.infiniteScrollComplete');
                    }
                };//广播结束  下拉和上拉

                if (angular.isUndefined(attr.infiniteScroll)) {//没有分页属性自动加载数据
                    if (wait == "true") {//
                        watch = $scope.$watch("wait", function (value) {
                            if (value == "false") {
                                $scope.loadMore(true);
                                watch && watch();
                            }
                        });
                    } else {
                        if (autoLoad) {
                            $scope.loadMore();
                        }
                    }
                }

                this.addRefresher = function () {
                    $scope.plugin.refresher = true;
                };

                this.addInfinite = function () {
                    if (wait == 'true') {
                        watch = $scope.$watch("wait", function (value) {
                            if (value == "false") {
                                $scope.loadMore();
                                $scope.plugin.infinite = true;
                                watch && watch();
                            }
                        });
                    } else {
                        if (autoLoad) {
                            $scope.loadMore();
                        }
                        $scope.plugin.infinite = true;
                    }
                };
            }]
        }
    }])
    .directive('refresher', [function () {
        return {
            restrict: 'A',
            require: '^pagePlugin',
            link: function (scope, element, attr, pageCtrl) {
                pageCtrl.addRefresher();
            }
        }
    }])

    .directive('infiniteScroll', [function () {
        return {
            restrict: 'A',
            require: '^pagePlugin',
            link: function (scope, element, attr, pageCtrl) {
                pageCtrl.addInfinite();
            }
        }
    }])
    .directive( 'videoPlay', videoPlay); //视频
    videoPlay.$inject = ["common"];
    function videoPlay(common) {
        videoPlayCtrl.$inject = ["$element", "$scope"];
    function videoPlayCtrl(ele, scope) {
        var $ele = $(ele), _this = scope, height, width, poster,
            watch = scope.$watch('src', function (value) {
                if (value) {
                    $ele.attr("src",  value);
                }
            }),
            watchPoster = scope.$watch('poster', function (value) {
                if (value) {
                    $ele.attr("poster",  value);
                } else {
                    $ele.attr("poster", 'images/noVideo.jpg');
                }
                $ele.attr("poster", value);
            });
        height = _this.height || screen.height * 0.3;
        width = _this.width || screen.width;
        $ele.attr("height", height);
        $ele.attr("width", width);
        scope.$on("$destroy", function () {
            console.log("remove videoPlay");
            watch && watch();
            watchPoster && watchPoster();
            scope.$destroy();
        });
    }

    return {
        restrict: 'E',
        template: '<video style="width:100%"  x5-video-player-type="h5"  controls="controls" ></video>',
        replace: true,
        scope: {
            src: '=videoSrc',
            height: '@',
            width: '@',
            poster: '=videoPoster'
        },
        controller: videoPlayCtrl
    };
}





