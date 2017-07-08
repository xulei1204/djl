/**
 * Created by Administrator on 2017/3/8.
 */

angular.module('app.api.service', [])
    .service('apiService', ["httpUtils", "common", "locals",function(h, c, l) {
        var isUe = false;

        var httpPost = function (url, json, isHide, TOKEN) {
            if (url == '') {
                return undefined;
            }
            var j = json || {}, address_url = url.split("/");
            if (TOKEN) {
                j.u = c.getTOKEN();
            }
            j.platform = ionic.Platform.  platform();//当前平台

            if (isUe) {
                return h.get("json/" + url + ".json", j, false);
            } else {

               // j.operation = address_url[1];
                return h.post(c.basePath + url, j, isHide);
            }

        };//HTTPS
        /**
         * 公共的
         *
         * @param json
         */
        this.login = function (json) {
            return httpPost("member/login", json,true,true);
        };//登陆

        this.register = function (json) {
            return httpPost("member/register", json,true,true);
        };//注册

        this.sendCodeRe = function (json) {
            return httpPost("member/sendCodeRe", json,true,true);
        };//注册获取验证码

        this.showAbout = function (json) {
            return httpPost("about/showAbout", json,true,true);
        };//单篇文章

        this.showQuestionList = function (json) {
            return httpPost("about/showQuestionList", json,true,true);
        };//常见问题&全部问题&我的反馈

        this.showFeedbackDetail = function (json) {
            return httpPost("feedback/showDetail", json,true,true);
        };//反馈详情
        /**
         * 用户端
         * @param json
         */
        this.showBanner = function (json) {
            return httpPost("banner/showBanner", json,true,true);
        };//显示banner

        this.showADlist= function (json) {
            return httpPost("ad/showList", json,true,true);
        };//显示广告列表

        this.classifyList= function (json) {
            return httpPost("classify/classifyList", json,true,true);
        };//显示分类列表

        this.showNewest= function (json) {
            return httpPost("headline/showNewest", json,true,true);
        };//最新头条

        this.headlineList= function (json) {
            return httpPost("headline/showList", json,true,true);
        };//头条列表

        this.headlineDetail= function (json) {
            return httpPost("headline/showDetail", json,true,true);
        };//头条详情

        this.AdDetail= function (json) {
            return httpPost("ad/showDetail", json,true,true);
        };//广告详情

        this.recordHistory= function (json) {
            return httpPost("history/recordHistory", json,true,true);
        };//记录浏览历史

        this.myBrowseHistory= function (json) {
            return httpPost("history/showList", json,true,true);
        };//记录浏览历史

        this.commentList= function (json) {
            return httpPost("comment/showComment", json,true,true);
        };//评论列表

        this.submitComment= function (json) {
            return httpPost("comment/addComment", json,true,true);
        };//提交

        this.receiveRedpacket= function (json) {
            return httpPost("redpacket/openRedpacket", json,true,true);
        };//拆红包

        this.redpacketRecord= function (json) {
            return httpPost("consume/showRecord", json,true,true);
        };//红包记录

        this.showEarnings= function (json) {
            return httpPost("member/showEarnings", json,true,true);
        };//

        this.updateNickname= function (json) {
            return httpPost("member/updateNickname", json,true,true);
        };//修改昵称

        this.updatePsword= function (json) {
            return httpPost("member/updatePsword", json,true,true);
        };//修改密码

        this.myBankcard= function (json) {
            return httpPost("bankcard/myBankCard", json,true,true);
        };//我的银行卡

        this.bankList= function (json) {
            return httpPost("bank/bankList", json,true,true);
        };//银行选择列表

        this.submitBankcard= function (json) {
            return httpPost("bankcard/addBankCard", json,true,true);
        };//提交银行卡

        this.prizePic= function (json) {
            return httpPost("finalset/prizeIndex", json,true,true);
        };//每月抽奖首页

        /**
         * 业主端
         */


    }]);




