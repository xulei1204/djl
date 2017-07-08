/**
 * Created by Administrator on 2017/3/8.
 */

angular.module('app.utils.service', [])
    .service('common', ['$state', "$ionicLoading", "locals", '$ionicConfig',function (state, $ionicLoading, locals, $ionicConfig) {
        this.domain = "http://192.168.2.165/djl/";//
        this.basePath = this.domain + "api/";

        this.abstract = state.current.name.split(".")[0];

        this.curUrl = state.current.name.replace(this.abstract + ".", "");

        this.urlState = state.current;

        this.activeTabName = '';

        this.pageJump = function (stateName,param) {
            // $ionicConfig.views.transition('ios');
            if (typeof stateName == 'undefined') {
                console.warn("跳转路由不存在 或者为undefined");
                return;
            }
            var $$url = state.current.url;
            state.go('app.'+stateName+"__"+this.activeTabName,param);
        };




        this.getTOKEN = function () {
            var TOKEN = locals.getObject("userInfo") || {};
            return TOKEN['U_id'];
        };

        this.getUserInfo = function () {
            return locals.getObject("userInfo");
        };

        //判断obj是否为json对象
        this.isJson = function (obj) {
            var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;
            return isjson;
        };

        /**
         * 保存用户信息
         * @param value
         * @returns {*}
         */
        this.setUserInfo = function (value) {
            return locals.setObject("userInfo", value);
        };

        this.showLoading = function () {
            $ionicLoading.show({
                content: 'Loading',
                animation: 'fade-in',
                showBackdrop: false,
                showDelay: 0
            });
        };

        this.hideLoading = function () {
            $ionicLoading.hide();
        };

        this.getAipPath=function(){
            return this.basePath;
        }

        this.randomStr = function (len) {
            var str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            var n = len || 10,
                s = "",
                rand;
            for (var i = 0; i < n; i++) {
                rand = Math.floor(Math.random() * str.length);
                s += str.charAt(rand);
            }
            return s;
        };

        this.isEmptyObject = function (e) {
            var t;
            for (t in e)
                return !1;
            return !0
        };

        /**
         * 根据所占位置进行截取  汉字占据长度为2
         * @param str
         * @param length
         * @returns {*}
         */
        this.subBytes = function (str, length) {
            if (angular.isUndefined(str)) {
                str = '';
            }
            var $str = str, $length = $str.replace(/[\u0391-\uFFE5]/g, "aa").length;
            if ($length <= length) {
                return str;
            }
            if (angular.isUndefined(length)) {
                length = 20;
            }
            return str.substring(0, Number(length / 2));
        };

        /**
         *
         * @param str
         * @param length
         * @returns {*}
         */
        this.substring = function (str, length) {
            if (angular.isUndefined(str)) {
                str = '';
            }
            if (str.length <= length) {
                return str;
            }
            if (angular.isUndefined(length)) {
                length = 20;
            }
            return str.substring(0, length);
        };

        this.domainPic = function (pic, defaultPic) {
            if (pic != null && pic != "") {
                if (pic.indexOf("http://") < 0 && pic.indexOf("https://") < 0) {
                    pic = this.domain + pic;
                    return pic;
                }
                if (pic.indexOf("base64") >= 0) {
                    return pic;
                }
            } else {
                pic = 'images/' + (defaultPic || "pro_pic_default.png");
            }
            return pic;
        };
        //空字符串和undefined 都是为空
        this.isBlank = function (obj) {
            if (angular.isUndefined(obj) || obj == null) {
                return true;
            }
            if (angular.isArray(obj) || angular.isObject(obj)) {
                return this.isEmptyObject(obj);
            } else {
                if (obj == "") {
                    return true;
                } else {
                    if (isNaN(obj)) {
                        return obj.trim() == "";
                    } else {
                        return false;
                    }
                }
            }
        };


        /**
         * 合并两个json对象
         *
         */
        this.mergeJsonObject = function (jsonbject1, jsonbject2) {
            var resultJsonObject={};
            for(var attr in jsonbject1){
                resultJsonObject[attr]=jsonbject1[attr];
            }
            for(var attr in jsonbject2){
                resultJsonObject[attr]=jsonbject2[attr];
            }
            return resultJsonObject;
        };


        //对图片旋转处理 added by lzk
        this.rotateImg=function(img,direction,canvas) {
            //alert(img);
            //最小与最大旋转方向，图片旋转4次后回到原方向
            var min_step = 0;
            var max_step = 3;
            //var img = document.getElementById(pid);
            if (img == null)return;
            //img的高度和宽度不能在img元素隐藏后获取，否则会出错
            var height = img.height;
            var width = img.width;
            //var step = img.getAttribute('step');
            var step = 2;
            if (step == null) {
                step = min_step;
            }
            if (direction == 'right') {
                step++;
                //旋转到原位置，即超过最大值
                step > max_step && (step = min_step);
            } else {
                step--;
                step < min_step && (step = max_step);
            }

            //旋转角度以弧度值为参数
            var degree = step * 90 * Math.PI / 180;
            var ctx = canvas.getContext('2d');
            switch (step) {
                case 0:
                    canvas.width = width;
                    canvas.height = height;
                    ctx.drawImage(img, 0, 0);
                    break;
                case 1:
                    canvas.width = height;
                    canvas.height = width;
                    ctx.rotate(degree);
                    ctx.drawImage(img, 0, -height);
                    break;
                case 2:
                    canvas.width = width;
                    canvas.height = height;
                    ctx.rotate(degree);
                    ctx.drawImage(img, -width, -height);
                    break;
                case 3:
                    canvas.width = height;
                    canvas.height = width;
                    ctx.rotate(degree);
                    ctx.drawImage(img, -width, 0);
                    break;
            }
        }

    }])


    .service('httpUtils', ["$q", "$http", "common",function($q, $http, Common) {

        this.get = function (url, isHide) {
            var d = $q.defer();
            var promise = d.promise;
            if (typeof isHide == 'undefined') {
                isHide = true;
            }
            if (isHide) {
                Common.showLoading();
            }
            $http.get(url, {
                timeout: 7000
            })
                .success(function (data) {
                    if (isHide) {
                        Common.hideLoading();
                    }
                    d.resolve(data);
                })
                .error(function (error) {
                    if (isHide) {
                        Common.hideLoading();
                    }
                    d.reject(error);
                });
            promise.success = function (fn) {
                promise.then(fn);
                return promise;
            };
            promise.error = function (fn) {
                promise.then(null, fn);
                return promise;
            };
            return d.promise;
        };

        this.post = function (url, data, isHide) {
            //console.log("请求接口:" + url)
            //console.log("请求参数:" + JSON.stringify(data))
            var d = $q.defer();
            var promise = d.promise;
            if (angular.isUndefined(isHide)) {
                isHide = true;
            }
            if (isHide) {
                Common.showLoading();
            }
            $http.post(url, data, {
                timeout: 7000
            }).success(function (data) {
                d.resolve(data);
                if (isHide) {
                    Common.hideLoading();
                }
            })
                .error(function (error) {
                    d.reject(error);
                    if (isHide) {
                        Common.hideLoading();
                    }
                });
            promise.success = function (fn) {
                promise.then(fn);
                return promise;
            };
            promise.error = function (fn) {
                promise.then(null, fn);
                return promise;
            };
            return d.promise;
        };
    }])

    .service('validate', function () {
        //手机号码
        this.validatePhone = function (phone) {
            var reg =/^1[3|4|5|8][0-9]\d{4,8}$/;
            return reg.test(phone);
        };
        // 验证身份证
        this.validateIDCard = function (IDCard) {
            var reg = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/; // 身份证
            return reg.test(IDCard);
        };

        // 验证登录密码6-18位
        this.validatePassWord = function (passWord) {
            var reg = /\b(^[a-zA-Z0-9]{6,18}$)\b/;
            return reg.test(passWord);
        };

        // 验证邮箱地址
        this.validateEmail = function (Email) {
            var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/; // 邮箱地址
            return reg.test(Email);
        };

        this.validateBankCard = function (bankCard) {
            var reg = /^\d{16}|\d{19}$/;
            return reg.test(bankCard);
        };

        this.validatePassword = function (password) {
            var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
            return reg.test(password);
        };
    })
    .service("webAlert",["$timeout",function (timeout) {

        var $body = $("body"),
            $toast = $("#toastPanel"),
            $Tip, timer;

        this.tip = function (tip, outTime) {
            if (!tip) {
                console.warn("输入内容为空");
                return;
            }
            var time = 500;
            if (angular.isDefined(outTime)) {
                time = outTime;
            }
            if ($toast.length <= 0) {
                $body.append("<div class='tsk' id='toastPanel'><a href='javascript:void(0)' style='color:#FFFFFF'>" + tip + "</a></div>");
                $toast = $("#toastPanel");
                $Tip = $toast.children("a");
            } else {
                $Tip.html(tip);
                $toast.show();
            }
            if (timer) {
                timeout.cancel(timer);
            }
            timer = timeout(function () {
                $toast.fadeOut("slow");
            }, time);
        };
        var json = {};



    }])
    .service("fn",function () {
            var $fn={};
            this.setFn=function (fn) {
              $fn=fn;
            }
            this.getFn=function () {
                return $fn;
            }

    })
    .service('$xhHandle', function () {
        var $handle = {};
        this.$setHandle = function (handle, json) {
            if ($handle[handle]) {
                console.warn(handle + "already exists, you must be $destroy it");
            } else {
                $handle[handle] = json;
            }
        };

        this.$getByHandle = function (handle) {
            if (!$handle[handle]) {
                console.log("can not find " + handle);
                return undefined;
            }
            return $handle[handle];
        };

        this.$destroy = function (handle) {
            delete $handle[handle];
        };
    })
;

