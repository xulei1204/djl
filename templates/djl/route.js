/**
 * Created by Administrator on 2017/5/23.
 */

angular.module("app.run").config(["urlRouteProvider", "$stateProvider",
    function(urlRoute,state){
        var json = {
            view: 'djl',
            child: [{
                key: 'djl',
                url: 'djl',
                attrs:{
                    homePage:true,
                    headType:'index',
                    title:'',
                    userType:'user'
                },
                view: {
                    templateUrl: 'djl/djl.html',
                    controller:'djlController'
                }
            },{
                key: 'djlsy',
                url: 'djlsy/:topTitle/:id',
                attrs:{
                    headType:'simple',
                    title:'专区',
                    userType:'user'
                },
                view: {
                    templateUrl: 'djl/classify_list.html',
                    controller:'classifyController'
                }
            },{
                key: 'djltt',
                url: 'djltt',
                attrs:{
                    headType:'simple',
                    title:'头条',
                    userType:'user'
                },
                view: {
                    templateUrl: 'djl/headline.html',
                    controller:'headlineController'
                }
            },{
                key: 'djllbxxxq',
                url: 'djllbxxxq/:id',
                attrs:{
                    headType:'simple',
                    title:'详情',
                    userType:'user'
                },
                view: {
                    templateUrl: 'djl/headline_detail.html',
                    controller:'headlineController'
                }
            },{
                key: 'djlxq',
                url: 'djlxq/:id',
                attrs:{
                    headType:'share',
                    title:'',
                    userType:'user'
                },
                view: {
                    templateUrl: 'djl/ad_detail.html',
                    controller:'ADController'
                }
            },{
                key: 'djlpl',
                url: 'djlpl/:id',
                attrs:{
                    headType:'simple',
                    title:'评论',
                    userType:'user'
                },
                view: {
                    templateUrl: 'djl/comment.html',
                    controller:'ADController'
                }
            },{
                key: 'djllhb',
                url: 'djllhb',
                attrs:{
                    headType:'share',
                    title:'笑笑',
                    userType:'user'
                },
                view: {
                    templateUrl: 'djl/yh3.4lhb.html'
                }
            },{
                key: 'djlfxwd',
                url: 'djlfxwd',
                attrs:{
                    headType:'share',
                    title:'笑笑',
                    userType:'user'
                },
                view: {
                    templateUrl: 'djl/yh3.5fxwd.html'
                }
            },{
                key: 'djldw',
                url: 'djldw',
                attrs:{
                    headType:'simple',
                    title:'选择地址',
                    userType:'user'
                },
                view: {
                    templateUrl: 'djl/choose_city.html'
                }
            }]
        };
        urlRoute.setRoute(json, state);
    }]);
