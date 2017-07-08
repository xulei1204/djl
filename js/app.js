angular.module("app.run", ['ionic', 'app.controller', 'app.service','app.filters','starter.directive'])

.config(['$stateProvider', '$urlRouterProvider', '$ionicConfigProvider', '$httpProvider', 'urlRouteProvider', function($stateProvider, $urlRouterProvider, $ionicConfigProvider, $httpProvider, urlRouteProvider) {
    $ionicConfigProvider.platform.ios.tabs.style('standard');
    $ionicConfigProvider.platform.ios.tabs.position('bottom');
    $ionicConfigProvider.platform.android.tabs.style('standard');
    $ionicConfigProvider.platform.android.tabs.position('bottom');
    $ionicConfigProvider.platform.ios.navBar.alignTitle('center');
    $ionicConfigProvider.platform.android.navBar.alignTitle('center');
    $ionicConfigProvider.platform.ios.backButton.previousTitleText(' ').icon('ion-ios-arrow-thin-left');
    $ionicConfigProvider.platform.android.backButton.previousTitleText(' ').icon('ion-android-arrow-back');
    $ionicConfigProvider.platform.ios.views.transition('ios');
    $ionicConfigProvider.platform.android.views.transition('android');
    $ionicConfigProvider.backButton.previousTitleText(false);
    ionic.Platform.ready(function() {
        // will execute when device is ready, or immediately if the device is already ready.
        if (ionic.Platform.isAndroid()) {
            //安卓小于5版本下，去除动画效果
            var userAgent = navigator.userAgent;
            var index = userAgent.indexOf("Android");
            if (index >= 0) {
                var androidVersion = parseFloat(userAgent.slice(index + 8));
                if (androidVersion < 5) {
                    // 版本小于3的事情
                    //alert('版本小于5.0');
                    $ionicConfigProvider.views.transition('no'); //没有动画效果
                }
            }
        }
    });


    $httpProvider.defaults.headers.post = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };
    // Use x-www-form-urlencoded Content-Type
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
    /**
     * The workhorse; converts an object to x-www-form-urlencoded serialization.
     * @param {Object} obj
     * @return {String}
     */
    var param = function(obj) {
        var query = '',
            name, value, fullSubName, subName, subValue, innerObj, i;
        for (name in obj) {
            value = obj[name];
            if (value instanceof Array) {
                for (i = 0; i < value.length; ++i) {
                    subValue = value[i];
                    fullSubName = name + '[' + i + ']';
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                }
            } else if (value instanceof Object) {
                for (subName in value) {
                    subValue = value[subName];
                    fullSubName = name + '[' + subName + ']';
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                }
            } else if (value !== undefined && value !== null) {
                query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
            }

        }
        return query.length ? query.substr(0, query.length - 1) : query;
    };
    // Override $http service's default transformRequest
    $httpProvider.defaults.transformRequest = [function(data) {
        return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
    }];
    urlRouteProvider.setAbsRoute($stateProvider, $urlRouterProvider);
}])

.run(['$rootScope', '$location', '$ionicPlatform', '$ionicHistory', function($rootScope, $location, $ionicPlatform, $ionicHistory) {
    $ionicPlatform.ready(function() {
        // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
        // for form inputs)
        if (window.cordova && window.cordova.plugins.Keyboard) {
            cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
        }
        if (window.StatusBar) {
            StatusBar.styleDefault();
        }

    });

}])

.provider('urlRoute', [function() {
    var abs = ["index", "djl", "prize", "ownerMine", "domain", "generalMine", "Published","news"],
        $$abs;
    this.setAbsRoute = function($stateProvider, $urlRouterProvider) {
        var userInfo = window.localStorage['userInfo'];
        if(!userInfo){
            $urlRouterProvider.otherwise("/app/index/login");
        }else{
            var json=JSON.parse(userInfo);
            json.type==1&& $urlRouterProvider.otherwise("/app/index/index");
            json.type==2&& $urlRouterProvider.otherwise("/app/domain/domain");

        }


        $stateProvider
            .state("app", {
                url: "/app",
                abstract: true,
                templateUrl: "templates/tabs.html"
            });
    };

    this.setRoute = function(routes, $stateProvider) {

        angular.forEach(routes.child, function(_k, _v) {
            var route = {
                url: "/" + _k.url,
                views: {}
            };
            if (_k.view.templateUrl) {
                _k.view.templateUrl = 'templates/' + _k.view.templateUrl;

            }
            if (_k.view.controller) {
                _k.view.controller =  _k.view.controller+' as ctrl';
                //_k.view.controllerAs
            }
            route = angular.extend(route, _k.attrs);
            $$abs = angular.copy(abs);
            angular.forEach($$abs, function(value, key) {
                var _route = angular.copy(route);
                _route.url = '/' + value + _route.url;
                _route.viewsName = value;
                _route.views[value] = _k.view;

                $stateProvider.state('app' + "." + _k.key + '__' + value, _route);
            });

        });
    };

    this.$get = function() {
        return {
            setRoute: setRoute,
            setAbsRoute: setAbsRoute
        };
    };
}])

.factory('locals', ['$window', function($window) {
    return {
        //存储单个属性
        set: function(key, value) {
            $window.localStorage[key] = value;
        },
        //读取单个属性
        get: function(key, defaultValue) {
            return $window.localStorage[key] || defaultValue;
        },
        //删除单个属性
        del: function(key) {
            delete $window.localStorage[key];
        },
        //存储对象，以JSON格式存储
        setObject: function(key, value) {
            $window.localStorage[key] = JSON.stringify(value);
        },
        //读取对象
        getObject: function(key) {
            return JSON.parse($window.localStorage[key] || '{}');
        },
        delObject: function(key) {
            delete $window.localStorage[key];
        }
    };
}]);
