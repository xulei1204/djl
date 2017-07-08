/**
 * Created by Administrator on 2017/5/23.
 */

angular.module("app.run").config(["urlRouteProvider", "$stateProvider",
    function(urlRoute,state){
        var json = {
            view: 'domain',
            child: [{
                key: 'domain',
                url: 'domain',
                attrs:{
                    homePage:true,
                    headType:'simple',
                    title:'我的地盘',
                    userType:'proprietor'
                },
                view: {
                    templateUrl: 'domain/mine_zone.html'
                }
            }]
        };
        urlRoute.setRoute(json, state);
    }]);