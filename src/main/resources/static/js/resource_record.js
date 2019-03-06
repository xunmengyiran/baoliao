"use strict";

function pulldownRefresh() {
    mui("#pullrefresh").pullRefresh().endPulldown()
}

function pullupRefresh() {
    var a = this;
    if (0 == sd_der) {
        var b = {};
        b.pageNo = pageNo,
            ajax(path + "/weixin/resource_list_my", b,
                function (b) {
                    app_in(1, b)
                    is_sdf_a = false
                    if (b.length < 10) {
                        $(".chakn_jghd_dr.aa").remove()
                    }
                })
    } else if (pageNo_er > 1) {
        var c = {};
        c.pageNo = pageNo_er,
            ajax(path + "/weixin/order_list_my", c,
                function (b) {
                    app_in(2, b)
                    is_sdf_b = false
                    /* console.log(b.length) */
                    if (b.length < 10) {
                        $(".chakn_jghd_dr.ab").remove()
                    }
                })
    }
}

function s_jgher() {
    var a = {};
    a.pageNo = pageNo_er,
        ajax(path + "/weixin/order_list_my", a,
            function (a) {
                app_in(2, a)
                is_sdf_b = false
                if (a.length < 10) {
                    $(".chakn_jghd_dr.ab").remove()
                }
            })
}

function app_in(a, b) {
    1 == a ? (b.map(function (a) {
        var b = '<li class="btm pd pt10 pm10">\n\t        <a href="' + basePath + "weixin/get_resource_info?type=2&id=" + a.id + '">\n\t        <p><span class="fz12">' + get_time(a.create_time) + '</span> <span class="fr">宸插敭 <span class="red">' + a.sale + '</span>浠�</span>\n\t        </p>\n\t        <p class="z3 mt5"><span class="red">' + bao_y(a.is_refund, a.is_expire) + "</span>" + a.title + "</p>\n\t    </a>";
        if (0 == a.sale || '2' == a.is_refund || ('2' == a.is_expire && '0' == a.is_refund) || a.valid_time > 6) {
            b += '<span class="fr dsf_jgh_der" data-title="' + a.title + '" data-id="' + a.id + '"><i class="dx icon-lajixiang"></i></span>'
        }
        b += "</li>",
            $(".sdf_khj_dert.aa ul").append(b)
    }), pageNo++) : (b.map(function (a) {
        var b = '<li class="btm pd pt10 pm10 pr"><a href="' + basePath + "weixin/get_resource_detail?type=3&id=" + a.en_id + '"><p><span class="fz12">' + get_time(a.create_time) + '</span><span class="fr red fz16">' + a.price + '鍏�</span> </p><p class="z3 mt5"><span class="red"></span>' + a.title + '</p></a><i class="dx icon-lajixiang fr de_df_deert f_drt" data-id="' + a.id + '" data-title="' + a.title + '"></i></li>'

        $(".sdf_khj_dert.ab ul").append(b)
    }), pageNo_er++)
}

var sd_der = 0;
$(function () {
    $("body").on("tap", ".dsf_jgh_der",
        function (a) {
            a.preventDefault();
            var b = $(this).attr("data-title"),
                c = $(this).attr("data-id"),
                d = $(this);
            mui.confirm("纭畾鍒犻櫎璇�" + b + "锛�",
                function (a) {
                    1 == a.index && $.ajax({
                        type: "POST",
                        url: path + "/weixin/resource_remove",
                        data: {
                            id: c,
                            type: 1
                        },
                        dataType: "json",
                        success: function (a) {
                            a.success ? (mui.toast("鍒犻櫎鎴愬姛"), $(d).parents("li").remove()) : mui.alert(a.msg)
                        },
                        error: function () {
                            alert("缃戠粶閿欒!")
                        }
                    })
                })
        }),
        $(".dsf_jherr_der .mui-col-xs-6").on("click",
            function () {
                $(".dsf_jherr_der .mui-col-xs-6").removeClass("act"),
                    $(this).addClass("act");
                var a = $(this).index();
                sd_der = a,
                    $(".sdf_khj_dert").removeClass("show"),
                    $(".sdf_khj_dert").eq(a).addClass("show")
                if (a == 0 && is_sdf_a) {
                    s_jgher()
                }
                if (a == 1 && is_sdf_b) {
                    s_jgher()
                }
            }),
        $("body").on("tap", ".sdf_khj_dert a",
            function () {
                window.location.href = $(this).attr("href")
            })
    $(".chakn_jghd_dr.aa").on("click", function () {// 鏌ョ湅鏇村鎸夐挳瑙﹀彂
        pullupRefresh()
    })
    $(".chakn_jghd_dr.ab").on("click", function () {// 鏌ョ湅鏇村鎸夐挳瑙﹀彂
        pullupRefresh()
    })

    $("body").on("tap", ".de_df_deert", function (e) {
        e.preventDefault();
        var th = $(this),
            title = $(this).attr("data-title"),
            id = $(this).attr("data-id")
        mui.confirm("纭畾鍒犻櫎" + title, function (a) {
            if (1 == a.index) {//閫夋嫨妗嗙‘璁ゆ寜閽Е鍙戜簨浠�
                $.ajax({
                    type: "POST",
                    url: path + "/weixin/resource_remove",
                    data: {
                        id: id,
                        type: 2
                    },
                    dataType: "json",
                    success: function (a) {
                        a.success ? (mui.toast("鍒犻櫎鎴愬姛"), $(th).parents("li").remove()) : mui.alert(a.msg)
                    },
                    error: function () {
                        alert("缃戠粶閿欒!")
                    }
                })
            }
        })
    })

    pullupRefresh()
})
var pageNo = 1,
    pageNo_er = 1,
    is_sdf_a = true,
    is_sdf_b = true