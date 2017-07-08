/**
 * Created by Administrator on 2017/5/23.
 */

angular.module('app.prize', [])
    .controller('prizeController', ['apiService', '$scope', function(api, scope) {
        var that = this;
        that.prizeIndex=function () {
            var promise=api.prizePic({});
            promise.then(function (data) {
                that.indexPic=data.data.value;
            })
        }

    }])
