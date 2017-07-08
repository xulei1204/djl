/**
 * Created by Administrator on 2017/5/23.
 */

angular.module('app.news', []).
controller('newsController', ['apiService', '$scope','webAlert','validate','common','$stateParams','locals',
    function(api, scope,webAlert,validate,common,param,locals) {

        scope.list=[];

        //头条列表
        scope.headlineList=function(json){
            return {
                promise: api.headlineList(json)
            };
        }

        //头条详情
        scope.headlineDetail=function () {
            var json={'id':param.id};
            var promise=api.headlineDetail(json);
            promise.then(function (data) {
                if(data.success)scope.detail=data.data;
            });
        }



    }])

