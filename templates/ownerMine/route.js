/**
 * Created by Administrator on 2017/5/23.
 */

angular.module("app.run").config(["urlRouteProvider", "$stateProvider",
    function(urlRoute, state) {
        var json = {
            view: 'ownerMine',
            child: [{
                key: 'ownerMine',
                url: 'ownerMine',
                attrs: {
                    homePage: true,
                    headType: 'shezhi',
                    title: '管理中心',
                    userType: 'proprietor'
                },
                view: {
                    templateUrl: 'ownerMine/mine.html'
                }
            },{
                key: 'maxfmx',
                url: 'maxfmx',
                attrs: {
                    headType: 'simple',
                    title: '消费明细',
                    userType: 'proprietor'
                },
                view: {
                    templateUrl: 'ownerMine/mine_consumption.html'
                }
            },{
                key: 'maxfmx2',
                url: 'maxfmx2',
                attrs: {
                    headType: 'simple',
                    title: '消费明细',
                    userType: 'proprietor'
                },
                view: {
                    templateUrl: 'ownerMine/yz4.2xfmx2.html'
                }
            },{
                key: 'maxfmx3',
                url: 'maxfmx3',
                attrs: {
                    headType: 'simple',
                    title: '消费明细',
                    userType: 'proprietor'
                },
                view: {
                    templateUrl: 'ownerMine/yz4.2xfmx3.html'
                }
            },{
                key: 'mamrxfmx',
                url: 'mamrxfmx',
                attrs: {
                    headType: 'simple',
                    title: '每日消费明细',
                    userType: 'proprietor'
                },
                view: {
                    templateUrl: 'ownerMine/mine_redpacket_record.html'
                }
            }, {
                key: 'mazhcz',
                url: 'mazhcz',
                attrs: {
                    headType: 'simple',
                    title: '账户充值',
                    userType: 'proprietor'
                },
                view: {
                    templateUrl: 'ownerMine/mine_pay.html'
                }
            }, {
                key: 'matxfpxx',
                url: 'matxfpxx',
                attrs: {
                    headType: 'simple',
                    title: '发票信息',
                    userType: 'proprietor'
                },
                view: {
                    templateUrl: 'ownerMine/mine_invoice.html'
                }
            }, {
                key: 'madzgl',
                url: 'madzgl',
                attrs: {
                    headType: 'simple',
                    title: '地址管理',
                    userType: 'proprietor'
                },
                view: {
                    templateUrl: 'ownerMine/mine_address.html'
                }
            }, {
                key: 'maxjdz',
                url: 'maxjdz',
                attrs: {
                    headType: 'simple',
                    title: '新建地址',
                    userType: 'proprietor'
                },
                view: {
                    templateUrl: 'ownerMine/mine_address_new.html'
                }
            }, {
                key: 'maewm',
                url: 'maewm',
                attrs: {
                    headType: 'simple',
                    title: 'APP二维码',
                    userType: 'proprietor'
                },
                view: {
                    templateUrl: 'ownerMine/mine_QRcode.html'
                }
            }, {
                key: 'magnjs',
                url: 'magnjs',
                attrs: {
                    headType: 'simple',
                    title: '功能介绍',
                    userType: 'proprietor'
                },
                view: {
                    templateUrl: 'ownerMine/mine_about.html'
                }
            }, {
                key: 'mats',
                url: 'mats',
                attrs: {
                    headType: 'simple',
                    title: '投诉',
                    userType: 'proprietor'
                },
                view: {
                    templateUrl: 'ownerMine/mine_feedback.html'
                }
            }, {
                key: 'mabzyfk',
                url: 'mabzyfk',
                attrs: {
                    headType: 'simple',
                    title: '帮助与反馈',
                    userType: 'proprietor'
                },
                view: {
                    templateUrl: 'ownerMine/mine_about_list.html'
                }
            }, {
                key: 'mafx',
                url: 'mafx',
                attrs: {
                    headType: 'simple',
                    title: '分享',
                    userType: 'proprietor'
                },
                view: {
                    templateUrl: 'ownerMine/mine_share.html'
                }
            }, {
                key: 'mazhzl',
                url: 'mazhzl',
                attrs: {
                    headType: 'simple',
                    title: '账号资料',
                    userType: 'proprietor'
                },
                view: {
                    templateUrl: 'ownerMine/mine_info.html'
                }
            }]
        };
        urlRoute.setRoute(json, state);
    }
]);
