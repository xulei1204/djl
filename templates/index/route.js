/**
 * Created by Administrator on 2017/5/23.
 */

angular.module("app.run").config(["urlRouteProvider", "$stateProvider",
    function(urlRoute,state){
        var json = {
            view: 'index',
            child: [{
                key: 'index',
                url: 'index',
                attrs:{
                    homePage:true,
                    headType:'shouye',
                    title:'首页',
                    userType:'user'

                },
                view: {
                    templateUrl: 'index/general_index.html',
                    controller:'genIndexController'
                },
            },{
                key: 'login',
                url: 'login',
                attrs:{
                    headType:'',
                    nocheck:true,
                    title:'登录',
                    userType:'user'
                },
                view: {
                    templateUrl: 'index/login.html',
                    controller:'indexController'
                }
            },{
                key: 'register',
                url: 'register',
                attrs:{
                    headType:'',
                    nocheck:true,
                    title:'注册',
                    userType:'user'
                },
                view: {
                    templateUrl: 'index/register.html',
                    controller:'indexController'
                }
            },{
                key: 'indexzccg',
                url: 'indexzccg',
                attrs:{
                    headType:'shouye',
                    title:'注册成功',
                    nocheck:true,
                    userType:'user'
                },
                view: {
                    templateUrl: 'index/register_result.html'
                }
            },{
                key: 'indexczl',
                url: 'indexczl',
                attrs:{
                    headType:'simple',
                    nocheck:true,
                    title:'上传资料',
                    userType:'user'
                },
                view: {
                    templateUrl: 'index/upload_information.html',
                    controller:'indexController'
                }
            },{
                key: 'search',
                url: 'search/:keyword',
                attrs:{
                    headType:'simple',
                    title:'搜索结果',
                    userType:'user'
                },

                view: {
                    templateUrl: 'index/search_result.html',
                    controller:'genIndexController'
                }
            },{
                key: 'about',
                url: 'about/:aboutId',
                attrs:{
                    headType:'simple',
                    title:'详情',
                    userType:'user'
                },

                view: {
                    templateUrl: 'public/about.html',
                    controller:'indexController'
                }
            }]
        };
        urlRoute.setRoute(json, state);
    }]);
