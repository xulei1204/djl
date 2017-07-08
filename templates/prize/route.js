/**
 * Created by Administrator on 2017/5/23.
 */

angular.module("app.run").config(["urlRouteProvider", "$stateProvider",
    function(urlRoute,state){
        var json = {
            view: 'prize',
            child: [{
                key: 'prize',
                url: 'prize',
                attrs:{
                    homePage:true,
                    headType:'simple',
                    title:'每月抽奖',
                    userType:'user'
                },
                view: {
                    templateUrl: 'prize/prize_index.html',
                    controller:'prizeController'
                }
            },{
                key: 'pzjhg',
                url: 'pzjhg',
                attrs:{
                    headType:'simple',
                    title:'中奖回顾',
                    userType:'user'
                },
                view: {
                    templateUrl: 'prize/prize_review.html'
                }
            },{
                key: 'phbgz',
                url: 'phbgz',
                attrs:{
                    headType:'simple',
                    title:'红包规则',
                    userType:'user'
                },
                view: {
                    templateUrl: 'prize/redpacket_rule.html'
                }
            }]
        };
        urlRoute.setRoute(json, state);
    }]);
