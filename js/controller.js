/**
 * Created by Administrator on 2017/5/23.
 */
angular.module('app.controller',['app.index','app.djl','app.prize','app.ownerMine','app.domain','app.generalMine','app.Published',"app.news"])
    .controller('mainController',['common','$scope','$state','$stateParams','webAlert',function(common,scope,state,param,webAlert){
        var that = this;
        that.tabsName = 'index';
        that.showTabs = true;
        that.routeAttrs = {
            headType:'simple',
            title:''
        };

        that.pageJump = function(stateName,param,e){
            if(e){
                e.stopPropagation();
            }
            common.pageJump(stateName,param);

        };

        that.cutTab = function(name,stateName){
            common.activeTabName = name;
            that.pageJump(stateName);
            // scope.$broadcast("test",3213);

        };

        that.interface = function(){
            urlFilter(state.current);
                //登陆验证
            if(common.isBlank(common.getTOKEN())&&!state.current.nocheck){
                webAlert.tip("请先登录");
                that.pageJump("login");
            }
            scope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
                //登陆验证
                if(common.isBlank(common.getTOKEN())&&!toState.nocheck){
                    event.preventDefault();
                    that.pageJump("login");
                }
                if (urlFilter(toState)) {

                }
            });
        };



        that.backRoute = function(){
            history.go(-1);
        };

        var urlFilter = function (_state) {
            var state = angular.copy(_state);
            that.tabsName = state.viewsName;
            common.activeTabName = that.tabsName;
            that.showTabs = state.homePage;// 是否显示tabs
            that.tabsType = state.userType;// tabs类型
            that.routeAttrs = state;

        }

    }]);