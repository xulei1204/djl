/**
 * Created by Administrator on 2017/5/23.
 */

angular.module('app.generalMine', [])
    .controller('generalMineController', ['apiService', '$scope','common','locals','webAlert',function(api, scope,common,locals,webAlert) {
        var that = this;
        //获取用户信息
        that.getUserInfo=function(){
            that.userInfo=common.getUserInfo();
        }
        //修改头像
        scope.updateHeadpic=function(file) {
            //图片方向角 added by lzk
            var Orientation = null;
            var base64 = null;
            if (file) {
                var rFilter = /^(image\/jpeg|image\/png)$/i; // 检查图片格式
                if (!rFilter.test(file.type)) {
                    //showMyTips("请选择jpeg、png格式的图片", false);
                    return;
                }
                // var URL = URL || webkitURL;
                //获取照片方向角属性，用户旋转控制
                EXIF.getData(file, function() {
                    // alert(EXIF.pretty(this));
                    EXIF.getAllTags(this);
                    //alert(EXIF.getTag(this, 'Orientation'));
                    Orientation = EXIF.getTag(this, 'Orientation');
                    //return;
                });

                var oReader = new FileReader();
                oReader.onload = function(e) {
                    //var blob = URL.createObjectURL(file);
                    //_compress(blob, file, basePath);
                    var image = new Image();
                    image.src = e.target.result;
                    image.onload = function() {

                        var expectWidth = this.naturalWidth;
                        var expectHeight = this.naturalHeight;
                        if (this.naturalWidth > this.naturalHeight && this.naturalWidth > 800) {
                            expectWidth = 800;
                            expectHeight = expectWidth * this.naturalHeight / this.naturalWidth;
                        } else if (this.naturalHeight > this.naturalWidth && this.naturalHeight > 1200) {
                            expectHeight = 1200;
                            expectWidth = expectHeight * this.naturalWidth / this.naturalHeight;
                        }
                        var canvas = document.createElement("canvas");
                        var ctx = canvas.getContext("2d");
                        canvas.width = expectWidth;
                        canvas.height = expectHeight;
                        ctx.drawImage(this, 0, 0, expectWidth, expectHeight);
                        //修复ios
                        if (navigator.userAgent.match(/iphone/i)) {
                            //alert(expectWidth + ',' + expectHeight);
                            //如果方向角不为1，都需要进行旋转 added by lzk
                            if(Orientation != "" && Orientation != 1){
                                //alert('旋转处理');
                                switch(Orientation){
                                    case 6://需要顺时针（向左）90度旋转
                                        //alert('需要顺时针（向左）90度旋转');
                                        common.rotateImg(this,'left',canvas);
                                        break;
                                    case 8://需要逆时针（向右）90度旋转
                                        //alert('需要顺时针（向右）90度旋转');
                                        common.rotateImg(this,'right',canvas);
                                        break;
                                    case 3://需要180度旋转
                                        //alert('需要180度旋转');
                                        common.rotateImg(this,'left',canvas);//转两次
                                        common.rotateImg(this,'left',canvas);
                                        break;
                                }
                            }
                            base64=canvas.toDataURL("image/jpeg", 0.3);

                        }else{
                            //alert(Orientation);
                            if(Orientation != "" && Orientation != 1){
                                //alert('旋转处理');
                                switch(Orientation){
                                    case 6://需要顺时针（向左）90度旋转
                                        //alert('需要顺时针（向左）90度旋转');
                                        common.rotateImg(this,'left',canvas);
                                        break;
                                    case 8://需要逆时针（向右）90度旋转
                                        //alert('需要顺时针（向右）90度旋转');
                                        common.rotateImg(this,'right',canvas);
                                        break;
                                    case 3://需要180度旋转
                                        // alert('需要180度旋转');
                                        common.rotateImg(this,'right',canvas);//转两次
                                        common.rotateImg(this,'right',canvas);
                                        break;
                                }
                            }

                            base64=canvas.toDataURL("image/jpeg", 0.3);
                        }
                        var json={};
                        json.uid=common.getTOKEN();
                        json.pic=base64
                     var url = common.basePath+"member/updateHeadpic";
                        $.ajax({
                            url: url,
                            type: 'POST',
                            data: {"JSON":JSON.stringify(json)},
                            async: false,//同步执行
                            cache: false,
                            dataType: "json",
                            success: function (data) { // 请求成功后处理函数。
                                if (data.code == 200) {
                                    that.userInfo.pic=data.data;
                                    locals.setObject("userInfo",that.userInfo);
                                }
                            },
                            error: function (){
                                webAlert.tip("修改失败，请稍后再试！");
                            }
                        });
                    };
                };
                oReader.readAsDataURL(file);
            }
        };


        //修改昵称
        that.updateNickname=function () {
            var json={};
            json.nickname=that.nickname;
            json.uid=common.getTOKEN();
            var promise=api.updateNickname(json);
            promise.then(function (data) {
                webAlert.tip(data.message);
                if(data.success){
                    that.userInfo.nickname=data.data;
                    locals.setObject("userInfo",that.userInfo);
                }
            })
        };


        //修改密码
        that.updatePsword=function () {

            if(common.isBlank($.trim(that.prePsword))){
                webAlert.tip("请输入原密码");
                return;
            }
            if(common.isBlank($.trim(that.newPsword))){
                webAlert.tip("请输入新密码");
                return;
            }
            if($.trim(that.newPsword).length<6){
                webAlert.tip("密码长度最少6位");
                return;
            }
            if(common.isBlank($.trim(that.againPsword))){
                webAlert.tip("请再次输入新密码");
                return;
            }
            if($.trim(that.newPsword)!=$.trim(that.againPsword)){
                webAlert.tip("两次密码输入不一致");
                return;
            }

            var json={};
            json.prePsword=that.prePsword;
            json.newPsword=that.newPsword;
            json.againPsword=that.againPsword;
            json.uid=common.getTOKEN();
            var promise=api.updatePsword(json);
            promise.then(function (data) {
                webAlert.tip(data.message);
                that.prePsword="";
                that.newPsword="";
                that.againPsword="";
                data.success&&history.back();
            })

        }


        //我的浏览记录
        scope.myBrowseHistory=function () {
            var promise=api.myBrowseHistory({});
            promise.then(function(data){
                data.success&&(scope.list=data.data);
            })
        }


    }])
    .controller('packetReController', ['apiService', '$scope','common','$xhHandle',function(api, scope,common,$xhHandle) {
        var that = this;
        that.type=1; //type 1.全部 3.红包 4.提现
        that.list=[];
        //红包记录&提现记录
        that.showRecord=function (json) {
            json.type= that.type;
            return {
                promise:api.redpacketRecord(json)
            }
        }
        //切换列表
        that.changeTab=function(type){
            that.type=type;
            var pagePlugin = $xhHandle.$getByHandle('redpacketRe_handle');
            if (pagePlugin) {
                pagePlugin.reload();
            }
        }

    }])

    .controller('earningsController', ['apiService', '$scope','common','$xhHandle','webAlert',function(api, scope,common,$xhHandle,webAlert) {
        var that=this;

        //我的收益
        that.showEarnings=function () {
            var promise=api.showEarnings({});
            promise.then(function (data) {
                data.success&&(that.data=data.data);
            });
        };

        that.type=1; //type 1.全部 3.红包 4.提现
        that.list=[];
        //红包记录&提现记录
        that.showRecord=function (json) {
            json.type= that.type;
            return {
                promise:api.redpacketRecord(json)
            }
        }
        //切换列表
        that.changeTab=function(type){
            that.type=type;

            var pagePlugin = $xhHandle.$getByHandle('redpacketRe_handle');
            if (pagePlugin) {
                pagePlugin.reload();
            }
        }

    }])
    .controller('questionController', ['apiService', '$scope','common','$xhHandle','$stateParams','webAlert',function(api, scope,common,$xhHandle,param,webAlert) {

        var that=this;
        that.type=2;//2.常见问题 3.全部问题 1.我的反馈
        that.list=[];
        //切换列表
        that.changeTab=function(type){
            that.type=type;
            var pagePlugin;
                pagePlugin = $xhHandle.$getByHandle('question_handle')
            if (pagePlugin) {
                pagePlugin.reload();
            }
        }

        //问题&反馈列表
        that.showQuestionList=function (json) {
            json.type=that.type;
            return {
                promise:api.showQuestionList(json)
            }
        }
        //问题详情
        that.questionDeatil=function () {
            var json={};
            json.aboutId=param.questionId;
            var promise=api.showAbout(json);
            promise.then(function (data) {
                data.success&&(that.about=data.data);
            });
        };


    }])
    .controller('feedbackController', ['apiService', '$scope','common','$xhHandle','$stateParams','webAlert',function(api, scope,common,$xhHandle,param,webAlert) {

        var that=this;

        //反馈详情
        that.feedbackDeatil=function () {
            var json={};
            json.id=param.id;
            var promise=api.showFeedbackDetail(json);
            promise.then(function (data) {
                data.success&&(that.about=data.data);
            });
        };

        that.imgArr=[];//展示图片
        that.reader = new FileReader();   //创建一个FileReader接口
        that.baseArr=[];//保存图片数据
        that.content="";
        //添加图片
        scope.img_upload = function (files) {
            if(that.baseArr.length>=9){
                webAlert.tip("最多上传九张!");
                return;
            }
            that.reader.readAsDataURL(files[0]);  //FileReader的方法,把图片转成base64
            that.reader.onload = function (ev) {
                scope.$apply(function(){
                    that.imgArr.push(ev.target.result);
                    that.selectFileImage(files[0]);
                });
            };

        }

        /*
         * 处理ios上传图片旋转
         */
         that.selectFileImage=function(file) {
            //图片方向角 added by lzk
            var Orientation = null;
            var base64 = null;
            if (file) {
                var rFilter = /^(image\/jpeg|image\/png)$/i; // 检查图片格式
                if (!rFilter.test(file.type)) {
                    //showMyTips("请选择jpeg、png格式的图片", false);
                    return;
                }
                // var URL = URL || webkitURL;
                //获取照片方向角属性，用户旋转控制
                EXIF.getData(file, function() {
                    // alert(EXIF.pretty(this));
                    EXIF.getAllTags(this);
                    //alert(EXIF.getTag(this, 'Orientation'));
                    Orientation = EXIF.getTag(this, 'Orientation');
                    //return;
                });

                var oReader = new FileReader();
                oReader.onload = function(e) {
                    //var blob = URL.createObjectURL(file);
                    //_compress(blob, file, basePath);
                    var image = new Image();
                    image.src = e.target.result;
                    image.onload = function() {

                        var expectWidth = this.naturalWidth;
                        var expectHeight = this.naturalHeight;
                        if (this.naturalWidth > this.naturalHeight && this.naturalWidth > 800) {
                            expectWidth = 800;
                            expectHeight = expectWidth * this.naturalHeight / this.naturalWidth;
                        } else if (this.naturalHeight > this.naturalWidth && this.naturalHeight > 1200) {
                            expectHeight = 1200;
                            expectWidth = expectHeight * this.naturalWidth / this.naturalHeight;
                        }
                        var canvas = document.createElement("canvas");
                        var ctx = canvas.getContext("2d");
                        canvas.width = expectWidth;
                        canvas.height = expectHeight;
                        ctx.drawImage(this, 0, 0, expectWidth, expectHeight);
                        //修复ios
                        if (navigator.userAgent.match(/iphone/i)) {
                            //alert(expectWidth + ',' + expectHeight);
                            //如果方向角不为1，都需要进行旋转 added by lzk
                            if(Orientation != "" && Orientation != 1){
                                //alert('旋转处理');
                                switch(Orientation){
                                    case 6://需要顺时针（向左）90度旋转
                                        //alert('需要顺时针（向左）90度旋转');
                                        common.rotateImg(this,'left',canvas);
                                        break;
                                    case 8://需要逆时针（向右）90度旋转
                                        //alert('需要顺时针（向右）90度旋转');
                                        common.rotateImg(this,'right',canvas);
                                        break;
                                    case 3://需要180度旋转
                                        //alert('需要180度旋转');
                                        common.rotateImg(this,'left',canvas);//转两次
                                        common.rotateImg(this,'left',canvas);
                                        break;
                                }
                            }
                            that.baseArr.push(canvas.toDataURL("image/jpeg", 0.3));

                        }else{
                            //alert(Orientation);
                            if(Orientation != "" && Orientation != 1){
                                //alert('旋转处理');
                                switch(Orientation){
                                    case 6://需要顺时针（向左）90度旋转
                                        //alert('需要顺时针（向左）90度旋转');
                                        common.rotateImg(this,'left',canvas);
                                        break;
                                    case 8://需要逆时针（向右）90度旋转
                                        //alert('需要顺时针（向右）90度旋转');
                                        common.rotateImg(this,'right',canvas);
                                        break;
                                    case 3://需要180度旋转
                                        // alert('需要180度旋转');
                                        common.rotateImg(this,'right',canvas);//转两次
                                        common.rotateImg(this,'right',canvas);
                                        break;
                                }
                            }

                            that.baseArr.push(canvas.toDataURL("image/jpeg", 0.3));
                        }
                    };
                };
                oReader.readAsDataURL(file);
            }
        }



        //移除图片
        that.removeImg=function(index){
            that.imgArr.splice(index,1);
            that.baseArr.splice(index,1);
            $("#image"+index).remove();
        }

        //提交反馈
        that.submitFeedback=function () {

            if(common.isBlank(that.content)){
                webAlert.tip("请填写您的宝贵意见！");
                return;
            }
            var json={};
            json.content=that.content;
            json.pic=that.baseArr;
            json.uid=common.getTOKEN();
            console.log(that.baseArr)
            var url = common.basePath+"feedback/submit";
            $.ajax({
                url: url,
                type: 'POST',
                data: {"JSON":JSON.stringify(json)},
                async: false,//同步执行
                cache: false,
                dataType: "json",
                success: function (data) { // 请求成功后处理函数。
                    if (data.code == 200) {
                        webAlert.tip("反馈成交，感谢您的意见！");
                        that.baseArr="";
                        that.imgArr="";
                        that.content="";
                        history.back();
                    }
                },
                error: function (){
                    webAlert.tip("服务器好像不在线哦！");
                }
            });
        }

    }])

    .controller('bankcardController', ['apiService', '$scope','common','$stateParams','webAlert','fn','validate',function(api, scope,common,param,webAlert,fn,validate) {

        var that=this;

        //我的银行卡
        that.myBankcard=function(){
            var promise=api.myBankcard({});
            promise.then(function (data) {
                if(data.success){
                    that.list=data.data;
                }
            })
        };

        //银行选择列表
        that.bankList=function() {
            var promise=api.bankList({});
            promise.then(function (data) {
                data.success&&(that.bank=data.data);
            })
        };

        //去银行卡列表
        that.goChooseBank=function () {
            fn.setFn(that.chooseBank);
            common.pageJump("chooseBank");
        };

        //选择银行卡
        that.chooseBank=function (obj) {
            that.choosedBank=obj;
        };

        //点击银行卡
        that.clickBank=function (obj) {
            fn.getFn()(obj);
            history.back();
        };

        //新增银行卡
        that.submit=function () {
            var json={};
            if(!validate.validateBankCard(that.code)){
                webAlert.tip("请输入正确银行卡号");
                return;
            }
            if(common.isBlank(that.name)){
                webAlert.tip("请输入持卡人姓名");
                return;
            }

            json.name=that.name;
            json.code=that.code;
            console.log(that.choosedBank)
            json.bankId=that.choosedBank.id;
            var promise=api.submitBankcard(json);
            promise.then(function (data) {
                webAlert.tip(data.message);
                data.success&&history.back();
            })
        };

    }])
