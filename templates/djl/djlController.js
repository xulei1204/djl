/**
 * Created by Administrator on 2017/5/23.
 */

angular.module('app.djl', [])
    .controller('djlController', ['apiService', '$scope','$timeout', function(api, scope,$timeout) {

        scope.list=[];

        //显示banner
        scope.showBanner=function(){
            var promise=api.showBanner({});
            promise.then(function (data) {
                if(data.success)scope.banner=data.data;
            })
        };
        //分类列表
        scope.showClassify=function(){
            var promise=api.classifyList({});
            promise.then(function (data) {
                if(data.success)scope.classify=data.data;
                var watch = $timeout(function () {
                    loadClassify();
                    $timeout.cancel(watch);
                },1);
            })
        };
        //加载分类
        var loadClassify=function () {
            var swiper = new Swiper('.swiper-container', {
                pagination: '.swiper-pagination',
                slidesPerView: 4,
                slidesPerColumn: 1,
                paginationClickable: true,
                spaceBetween: 30
            });
        }

        //最新头条
        scope.showNewest=function () {
            var promise=api.showNewest({});
            promise.then(function (data) {
                if(data.success)scope.newest=data.data;
            });
     }
        //广告列表
        scope.ADList=function(json){
            return api.showADlist(json);
        }


    }])
    .controller('classifyController', ['apiService', '$scope','$state','$stateParams','$xhHandle', function(api, scope,state,param,$xhHandle) {

        var that = this;
        that.keyword = "";
        that.list=[];
        scope.main.routeAttrs.title=param.topTitle;

        //显示专区广告列表
        that.ADList=function(json){
            json.classifyId=param.id;
            json.keyword=that.keyword;
            return api.showADlist(json);
        };

        //关键字搜索
        that.searchAd=function(){
            var pagePlugin = $xhHandle.$getByHandle('classify_handle');
            if (pagePlugin) {
                pagePlugin.reload();
            }
        }

    }])
    .controller('headlineController', ['apiService', '$scope','$state','$stateParams', function(api, scope,state,param) {

        scope.list=[];

        //头条列表
        scope.headlineList=function(json){
            return {
                promise: api.headlineList(json)
            };
        }

        //头条详情
        scope.headlineDetail=function () {
            var json={'id':param.id};
            var promise=api.headlineDetail(json);
            promise.then(function (data) {
                if(data.success)scope.detail=data.data;
            });
        }


    }])
    .controller('ADController', ['apiService', '$scope','$state','$stateParams','$xhHandle','$interval','webAlert','common',function(api,scope,state,param,$xhHandle,$interval,webAlert,common) {

        //领取红包falg
        scope.cando=false;

        //浏览时长
        scope.time=5;
        //广告详情
        scope.AdDetail=function () {
            var json={'id':param.id};
            var promise=api.AdDetail(json);
            promise.then(function (data) {
                if(data.success)scope.detail=data.data;
                scope.main.routeAttrs.title=scope.detail.nickname;
                scope.main.routeAttrs.pic=scope.detail.pic;
                if(!common.isBlank(scope.detail.video)){
                    scope.time=1;
                }
                var i=1;
                var timer=$interval(function(){
                   if(i++==scope.time){
                       scope.cando=true;
                       $interval.cancel(timer);
                   }
                },1000);
            });
        }

        //记录浏览记录
        scope.recordHistory=function () {

            var json={'ADID':param.id};
            var promise=api.recordHistory(json);
            promise.then(function (data) {
                console.log(data.message);
            })
        }
        
        //拆红包
        scope.receiveRedpacket=function () {
            if(!scope.cando){
                webAlert.tip("还没达到领取条件，再等一会吧！");
                return;
            }
            var json={};
            json.ADID=param.id;

            var promise=api.receiveRedpacket(json);
            promise.then(function (data) {
                if(data.code=="204"){
                    webAlert.tip(data.message);
                    return;
                }else if(data.code=="201"){
                    scope.showTip=2;
                }else if(data.code=="200"){
                    scope.showTip=1;
                    scope.redpacket=data.data;
                }

            });
        };

        scope.list=[];
        scope.content="";
        scope.open=false;
        //评论列表
        scope.commentList=function (json) {
             json.ADID=param.id;
            return {
                promise:api.commentList(json)
            }
        };

        //新增评论
        scope.submitComment=function () {
            var json={};
            json.content=scope.content;
            json.ADID=param.id;
            var promise=api.submitComment(json);
            promise.then(function (data) {
                if(data.success)scope.detail=data.data;
                var pagePlugin = $xhHandle.$getByHandle('comment_handle');
                if (pagePlugin) {
                    pagePlugin.reload();
                }
                scope.content="";
            });
        };

        //关闭&打开红包弹框
        scope.clickRedpacket=function(type){
                type==1&&(scope.open=!scope.open);
                (type==2)&&(scope.showTip="");


        };


    }])