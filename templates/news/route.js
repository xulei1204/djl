/**
 * Created by Administrator on 2017/5/23.
 */

angular.module("app.run").config(["urlRouteProvider", "$stateProvider",
    function(urlRoute,state){
        var json = {
            view: 'news',
            child: [{
                key: 'news',
                url: 'news',
                attrs:{
                    homePage:true,
                    headType:'shouye',
                    title:'资讯',
                    userType:'user'

                },
                view: {
                    templateUrl: 'news/news.html',
                    controller:'newsController'
                },
            }]
        };
        urlRoute.setRoute(json, state);
    }]);
