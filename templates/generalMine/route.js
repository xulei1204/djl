/**
 * Created by Administrator on 2017/5/23.
 */

angular.module("app.run").config(["urlRouteProvider", "$stateProvider",
    function(urlRoute, state) {
        var json = {
            view: 'generalMine',
            child: [{
                key: 'generalMine',
                url: 'generalMine',
                attrs: {
                    homePage: true,
                    headType: 'simple',
                    title: '我的',
                    userType: 'user'
                },
                view: {
                    templateUrl: 'generalMine/mine.html',
                    controller:'generalMineController'
                }
            },{
                key: 'mhbjl',
                url: 'mhbjl',
                attrs:{
                    headType:'simple',
                    title:'红包记录',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/mine_repacket_record.html',
                    controller:'packetReController'
                }
            },{
                key: 'mwdsy',
                url: 'mwdsy',
                attrs:{
                    headType:'simple',
                    title:'我的收益',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/mine_earnings.html',
                    controller:'earningsController'
                }
            },{
                key: 'mcksygz',
                url: 'mcksygz',
                attrs:{
                    headType:'simple',
                    title:'收益规则',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/mine_earnings_rule.html',
                    controller:'indexController'
                }
            },{
                key: 'mwdyhk',
                url: 'mwdyhk',
                attrs:{
                    headType:'add',
                    title:'我的银行卡',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/mine_bank.html',
                    controller:'bankcardController'
                }
            },{
                key: 'mgnjs',
                url: 'mgnjs',
                attrs:{
                    headType:'simple',
                    title:'功能介绍',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/mine_introduce.html',
                    controller:'indexController'
                }
            },{
                key: 'mappewm',
                url: 'mappewm',
                attrs:{
                    headType:'simple',
                    title:'APP二维码',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/mine_QRcode.html',
                    controller:'indexController'
                }
            },{
                key: 'mts',
                url: 'mts',
                attrs:{
                    headType:'simple',
                    title:'投诉与意见',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/feedback_new.html',
                    controller:'feedbackController'
                }
            },{
                key: 'mtstjcg',
                url: 'mtstjcg',
                attrs:{
                    headType:'finish',
                    title:'投诉与意见',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/yh5.6.1tstjcg.html'
                }
            },{
                key: 'mbzyfk',
                url: 'mbzyfk',
                attrs:{
                    headType:'simple',
                    title:'帮助与反馈',
                    userType:'user'
                },
                view: {
                    templateUrl: 'public/mine_question.html',
                    controller:'questionController'
                }
            },{
                key: 'questionDetail',
                url: 'questionDetail/:questionId',
                attrs:{
                    headType:'simple',
                    title:'问题详情',
                    userType:'user'
                },
                view: {
                    templateUrl: 'public/question_detail.html',
                    controller:'questionController'
                }
            },{
                key: 'feedbackDetail',
                url: 'feedbackDetail/:id',
                attrs:{
                    headType:'simple',
                    title:'反馈详情',
                    userType:'user'
                },
                view: {
                    templateUrl: 'public/feedback_detail.html',
                    controller:'feedbackController'
                }
            },{
                key: 'mhbtx',
                url: 'mhbtx',
                attrs:{
                    headType:'simple',
                    title:'红包提现',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/yh6.1hbtx.html'
                }
            },{
                key: 'mxzyhk',
                url: 'mxzyhk',
                attrs:{
                    headType:'add',
                    title:'选择银行卡',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/yh6.2xzyhk.html'
                }
            },{
                key: 'mtxcg',
                url: 'mtxcg',
                attrs:{
                    headType:'finish',
                    title:'余额提现',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/yh6.3txcg.html'
                }
            },{
                key: 'mtjyhk',
                url: 'mtjyhk',
                attrs:{
                    headType:'simple',
                    title:'添加银行卡',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/bankcard_new.html',
                    controller:'bankcardController'
                }
            },{
                key: 'mtjyhkcg',
                url: 'mtjyhkcg',
                attrs:{
                    headType:'finish',
                    title:'添加银行卡',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/bankcard_new_result.html'
                }
            },{
                key: 'chooseBank',
                url: 'chooseBank',
                attrs:{
                    headType:'simple',
                    title:'选择银行卡',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/chooseBank.html',
                    controller:'bankcardController'
                }
            },{
                key: 'mzhzl',
                url: 'mzhzl',
                attrs:{
                    headType:'simple',
                    title:'账号资料',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/mine_information.html',
                    controller:'generalMineController'
                }
            },{
                key: 'mxgmm',
                url: 'mxgmm',
                attrs:{
                    headType:'simple',
                    title:'修改密码',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/update_password.html',
                    controller:'generalMineController'
                }
            },{
                key: 'updateNickname',
                url: 'updateNickname',
                attrs:{
                    headType:'simple',
                    title:'修改昵称',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/update_nickname.html',
                    controller:'generalMineController'
                }
            },{
                key: 'browseHistory',
                url: 'browseHistory',
                attrs:{
                    headType:'simple',
                    title:'浏览记录',
                    userType:'user'
                },
                view: {
                    templateUrl: 'generalMine/mine_history.html',
                    controller:'generalMineController'
                }
            }]
        };
        urlRoute.setRoute(json, state);
    }
]);
