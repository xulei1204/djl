/**
 * Created by Administrator on 2017/3/13.
 */
angular.module('app.filters', [])
    .filter("httpImg", httpImg)
    .filter("httpImgAlbum", httpImgAlbum)
    .filter('httpNoImg', httpNoImg)
    .filter("domain", domain)
    .filter('subStr', subStr)
    .filter('filterHtml', filterHtml);

filterHtml.$inject = ['$sce'];
function filterHtml($sce) {
    return function (input) {
        if (input) {
            return $sce.trustAsHtml(input);
        } else {
            return '';
        }
    }
}

httpImg.$inject = ["common"];
function httpImg(common) {
    return function (input) {
        if (input) {
            if (input.indexOf("base64") >= 0 || input.indexOf("http") >= 0) {
                return input;
            } else {
                return common.domainPic(input);
            }
        } else {
            return 'images/pro_pic_default.png';
        }
    }
}

httpImgAlbum.$inject = ["common"];
function httpImgAlbum(common) {
    return function (input) {
        if (input) {
            if (input.indexOf("base64") >= 0 || input.indexOf("http") >= 0) {
                return input;
            } else {
                return common.domainPic(input, 'wzp.png');
            }
        } else {
            return 'images/wzp.png';
        }
    }
}

httpNoImg.$inject = ["common"];
function httpNoImg(common) {
    return function (input) {
        if (input != undefined && input.indexOf("base64") >= 0) {
            return input;
        }
        return common.domainPic(input, 'wzp.png');
    }
}


domain.$inject = ["common"];
function domain(common) {
    return function (input) {
        if (input != undefined && input.indexOf("base64") >= 0) {
            return input;
        }
        return common.domainPic(input, 'no');
    }
}
subStr.$inject = ["common"];
function subStr(common) {
    return function (input, length) {
        return common.subBytes(input, length);
    }
}
