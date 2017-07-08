/**
 * Created by Administrator on 2017/5/23.
 */

angular.module('app.Published', [])
    .controller('publishedController', ['apiService', '$scope', function(api, scope) {
        var that = this;

        that.init = function() {

            new Swipe(document.getElementById('banner_box'), {
                speed: 500,
                auto: 3000,
                callback: function() {
                    var lis = $(this.element).next("ol").children();
                    lis.removeClass("on").eq(this.index).addClass("on");
                }
            });
            var promise = api.login({});
            promise.success(function(data) {
                that.code = data;
            });
        };

    }])
    .controller('spxqController', ['apiService', '$scope', function(api, scope) {
        var that = this;
        that.init = function() {

            new Swipe(document.getElementById('banner_box2'), {
                speed: 500,
                auto: 3000,
                callback: function() {
                    var lis = $(this.element).next("ol").children();
                    lis.removeClass("on").eq(this.index).addClass("on");
                }
            });
            var promise = api.login({});
            promise.success(function(data) {
                that.code = data;
            });
            var i = 1;
            var box = document.querySelectorAll(".spxq_num li");
            box[0].addEventListener('click', function() {
                if (i == 0) {
                    return
                }
                box[1].innerHTML = i - 1;
                i--;
            })
            box[2].addEventListener('click', function() {
                box[1].innerHTML = i + 1;
                i++;
            })
        };

    }]).controller('splbController', [function() {
        var that = this;
        that.init = function() {
            var box = document.querySelector(".box_sort"),
                dri = box.querySelector("i"),
                sort = box.querySelector("span");
            box.addEventListener("click", function() {
                if (sort.className == 'down') {
                    dri.className = 'icon ion-ios-arrow-up pl5';
                    sort.className = 'up';
                } else {
                    dri.className = 'icon ion-ios-arrow-down pl5';
                    sort.className = 'down';
                }
            })
        };

    }]);
