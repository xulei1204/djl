/**
 * Created by Administrator on 2017/5/23.
 */

angular.module("app.run").config(["urlRouteProvider", "$stateProvider",
    function(urlRoute,state){
        var json = {
            view: 'Published',
            child: [{
                key: 'Published',
                url: 'Published',
                attrs:{
                    homePage:true,
                    headType:'fabu',
                    title:'我的发布',
                    userType:'proprietor'
                },
                view: {
                    templateUrl: 'Published/myPublish.html'
                }
            },{
                key: 'Puckfbxq',
                url: 'Puckfbxq',
                attrs:{
                    headType:'share2',
                    title:'',
                    userType:'proprietor',
                    username:'笑笑'
                },
                view: {
                    templateUrl: 'Published/ad_detail.html'
                }
            },{
                key: 'Puckpl',
                url: 'Puckpl',
                attrs:{
                    headType:'simple',
                    title:'评论',
                    userType:'proprietor'
                },
                view: {
                    templateUrl: 'Published/comment.html'
                }
            },{
                key: 'Pubjxq',
                url: 'Pubjxq',
                attrs:{
                    headType:'share2',
                    title:'',
                    userType:'proprietor',
                    username:'笑笑'
                },
                view: {
                    templateUrl: 'Published/yz2.4bjxq.html'
                }
            },{
                key: 'Pufbxx',
                url: 'Pufbxx',
                attrs:{
                    headType:'simple',
                    title:'发布信息',
                    userType:'proprietor'
                },
                view: {
                    templateUrl: 'Published/publish_new.html'
                }
            },{
                key: 'Puxzcs',
                url: 'Puxzcs',
                attrs:{
                    headType:'simple',
                    title:'选择城市',
                    userType:'proprietor'
                },
                view: {
                    templateUrl: 'Published/city_choose.html'
                }
            },{
                key: 'Puszhb',
                url: 'Puszhb',
                attrs:{
                    headType:'simple',
                    title:'设置红包',
                    userType:'proprietor'
                },
                view: {
                    templateUrl: 'Published/redpacket_set.html'
                }
            },{
                key: 'Pufbcg',
                url: 'Pufbcg',
                attrs:{
                    headType:'shouye',
                    title:'设置红包',
                    userType:'proprietor'
                },
                view: {
                    templateUrl: 'Published/result_publish.html'
                }
            },{
                key: 'Puxx',
                url: 'Puxx',
                attrs:{
                    headType:'simple',
                    title:'消息',
                    userType:'proprietor'
                },
                view: {
                    templateUrl: 'Published/message.html'
                }
            },{
                key: 'Puxtxxxq',
                url: 'Puxtxxxq',
                attrs:{
                    headType:'simple',
                    title:'系统消息',
                    userType:'proprietor'
                },
                view: {
                    templateUrl: 'Published/sysmessage_detail.html'
                }
            },{
                key: 'Puxxhf',
                url: 'Puxxhf',
                attrs:{
                    headType:'simple',
                    title:'个人消息',
                    userType:'proprietor'
                },
                view: {
                    templateUrl: 'Published/admessage_detail.html'
                }
            }]
        };
        urlRoute.setRoute(json, state);
    }]);
