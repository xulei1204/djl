/**
 * Created by Administrator on 2017/5/23.
 */

angular.module('app.index', []).
controller('indexController', ['apiService', '$scope','webAlert','validate','common','$stateParams','fn','locals',
    function(api, scope,webAlert,validate,common,param,fn,locals) {

        var that=this;
         //注册获取验证码
         scope.sendCodeForRe=function(data){
             if(!validate.validatePhone(data.tel)){
                webAlert.tip("请输入正确手机号！");
                return false;
            }
             if(common.isBlank(data.type)){
                 webAlert.tip("请选择要注册的会员类型！");
                 return false;
             }
            var json={tel:data.tel,type:data.type};
             var promise=api.sendCodeRe(json);
             promise.then(function (data){
                 webAlert.tip(data.message);
             })
        };


        //注册
        scope.register=function (data){

            if(!validate.validatePhone(data.tel)){
                webAlert.tip("请输入正确手机号！");
                return false;
            }
            if(common.isBlank(data.tel)) {
                webAlert.tip("请输入验证码！");
                return false;
            }
            if(common.isBlank(data.password)) {
                webAlert.tip("请输入密码！");
                return false;
            }
            if(common.isBlank(data.type)){
                webAlert.tip("请选择要注册的会员类型！");
                return false;
            }

            var json={tel:data.tel,password:data.password,code:data.code,type:data.type};
            //如果是业主注册且上传了图片的话
            if(json.type==2&&scope.img){
                json=common.mergeJsonObject(json,scope.img);
            }
            var promise=api.register(json);
            promise.then(function (data){
                webAlert.tip(data.message);
                if(data.success){
                    common.pageJump("login");
                }
            })

        };

        //登陆
        scope.login=function(data){

            if(!validate.validatePhone(data.tel)){
                webAlert.tip("请输入正确手机号！");
                return false;
            }
            if(common.isBlank(data.password)) {
                webAlert.tip("请输入密码！");
                return false;
            }
            if(common.isBlank(data.type)){
                webAlert.tip("请选择会员类型！");
                return false;
            }
            var json={tel:data.tel,password:data.password,type:data.type};
            var promise=api.login(json);
            promise.then(function (data){
                webAlert.tip(data.message);
                locals.setObject("userInfo",data.data);
                if(data.success){
                    if(json.type==1){
                        scope.main.cutTab("index","index");
                        scope.main.routeAttrs.headType="shouye";
                    }else if(json.type==2){
                        scope.main.cutTab("domain","domain");
                        scope.main.routeAttrs.headType="simple";
                    }

                }

            })
        };
        scope.reader = new FileReader();   //创建一个FileReader接口
        scope.img={};
        //上传图片
        scope.img_upload = function (files,id) {
            var formdata=new FormData();
            formdata.append("userPic", files[0]);
            scope.reader.readAsDataURL(files[0]);  //FileReader的方法,把图片转成base64
            scope.reader.onload = function (ev) {
                scope.$apply(function(){
                    $("#"+id).attr("src",ev.target.result);
                    $("#"+id).attr("class","idcard")
                });
            };
            var url = common.getAipPath()+"uploadImg";
           $.ajax({
                url: url,
                type: 'POST',
                data: formdata,
                async: false,//同步执行
                cache: false,
                contentType: false,
                processData: false,
                dataType: "json",
                success: function (data) {
                    if(data.success){
                        scope.img[id]=data.data[0];
                    }
                },
                error: function () {
                }
            });
        };

        //单篇文章
        that.showAbout=function (aboutId) {
            var json={};
            json.aboutId=aboutId
            var promise=api.showAbout(json);
            promise.then(function (data) {
                data.success&&(that.about=data.data);
            });
        };

        //单篇文章
        that.showAboutSe=function () {
            var json={};
            json.aboutId=param.aboutId;
            var promise=api.showAbout(json);
            promise.then(function (data) {
                data.success&&(that.about=data.data);
            });
        };
    }])
    .controller('genIndexController', ['apiService', '$scope','webAlert','validate','common','$stateParams','fn','locals',
    function(api, scope,webAlert,validate,common,param,fn,locals) {

            //显示banner
            scope.showBanner=function(){
                var promise=api.showBanner({});
                promise.then(function (data) {
                    if(data.success)scope.banner=data.data;
                })
            };

         scope.list = [];

            //搜索广告
            scope.searchAd=function (json) {
                json.keyword=param.keyword;
                return api.showADlist(json);
            }

    }])

