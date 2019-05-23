<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>


    <script type="text/javascript">
        var path = "/lmjl_core";
        var basePath = "https://lmjl.ttkgou.com/lmjl_core/";
        var img_url = "http://lm-img.oss-cn-shenzhen.aliyuncs.com/res/";
    </script>

    <link href="/css/mui.min.css" rel="stylesheet"/>

    <link href="/css/mui.picker.min.css" rel="stylesheet"/>
    <link href="/css/base.css?v20180601" rel="stylesheet"/>
    <link href="/css/style.css?v20180602" rel="stylesheet"/>
    <link href="/css/iconfont.css" rel="stylesheet"/>
    <link href="/css/swiper3.07.min.css" rel="stylesheet"/>

    <script src="/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="/js/mui.min.js"></script>
    <script src="/js/mui.picker.min.js"></script>
    <script src="/js/base.js?v20180813" type="text/javascript"></script>
    <style>
        .srtop {
            display: flex;
            text-align: center;
            line-height: 50px;
            font-size: 16px;
        }

        .srtop div {
            flex: 1;
            position: relative;
        }

        .srtop div span {
            color: #ff8d03
        }

        .srtop div:after {
            content: " ";
            position: absolute;
            left: 0;
            bottom: 0;
            right: 0;
            top: 15%;
            height: 70%;
            width: 1px;
            border-left: 1px solid #ccc;
            color: #ccc;
            -webkit-transform-origin: 0 100%;
            transform-origin: 0 100%;
            -webkit-transform: scaleX(.5);
            transform: scaleX(.5);
        }

        .srtop a {
            color: #70c434;
        }

        .df_jh_deet {
            font-size: 36px;
        }

        .df_jh_deet.ab {
            position: relative;
            bottom: 5px;
        }

        .user_icon_e.ab {
            width: 26px;
            height: 26px;
        }

        .dsf_jh_derert .mui-col-xs-4.act * {
            color: #70C434
        }

        .zfb_commit {
            float: right;
            width: 48% !important;
            background: #09f !important;
            border: 1px solid #09f !important;
            color: #fff;
        }

        .weixin_commit {
            float: left;
            width: 48% !important;
            background: #70c434 !important;
            border: 1px solid #70c434 !important;
            color: #fff;
        }

        .mt40_ {
            margin-top: 80px !important;
        }
    </style>
</head>
<body>
<section class="sd_jh_deertd ">
    <!--  <p class="cf pl10">今日收益</p> -->
    <section class='cf  pl10 fz12'>今日收益
        <section class='df_jh_deet cf cen  fr pr20 ab'>
            <image src='${sessionScope.user.headImgUrl}'
                   class='user_icon_e yj cz ab'></image>
            <span class=' fz12 cz '>${sessionScope.user.nickName}</span>
        </section>
    </section>
    <view class="qc">
    </view>

    <p class="cf fz36 cen pt20">${sessionScope.todayIncome}</p>
    <input type="hidden" name="balance" id="balance" value="${sessionScope.balance}"/>
    <input type="hidden" name="zfb_account" id="zfb_account" value=""/>
    <section class="mui-row mt20 cen">
        <section class="mui-col-xs-6 cf fz18">
            <p style="color: white;font-size: 18px" id="p1">${sessionScope.balance}</p>
            <p class="cf fz12">余额</p>
        </section>
        <section class="mui-col-xs-6 cf fz18">
            ${sessionScope.incomeCount}
            <p class="cf fz12">收益总额</p>
        </section>
    </section>
</section>
<div class="srtop">
    <div id="attention"><a href="/focus/getFocusList">关注
        <span>${sessionScope.focusCount}</span></a></div>
    <div id="follower"><a href="/focus/getFansList">粉丝
        <span>${sessionScope.fansCount}</span></a></div>
</div>
<ul class="mui-table-view">
    <li class="mui-table-view-cell fz16">
        <a class="mui-navigate-right" href="/trade/tradeList">
            账户明细
        </a>
    </li>
    <li class="mui-table-view-cell fz16">
        <a class="mui-navigate-right" href="/trade/depositList">
            提现明细
        </a>
    </li>


</ul>

<section class="mt15 pd">
    <p class="z3 fz16">提现说明</p>
    <section class="fz14 red mt10 df_kj_dert">
        <!-- 余额每天0-1点之间系统会自动提现到您的微信钱包(满2元)，无提现手续费请注意查收！ --><!-- 提现收取2%-5%服务费. -->
        提现是通过企业转账的方式转到您的微信钱包，每天限额2~20000元(超过的请明天再提)，提现收取5%服务费，提现过程中如果有疑问请联系我们的客服，谢谢！
        <!--  配合微信日常核查，现暂停提现，开放提现时间另行通知，谢谢理解！ -->
    </section>
    <!--         <p class="z3 fz16 mt15">服务费说明</p>
        <section class="fz14 red mt10 df_kj_dert">
                      当日流水达到2000，当日收入只收取4%;
            当日流水达到5000，当日收入只收取3.5%;
            当日流水达到10000，当日收入只收取3%;
            当日流水达到15000，当日收入只收取2.5%;
            当日流水达到20000，当日收入只收取2%.
            </section> -->
    <br>
    <p>


        <button class="w100 fz16 bgls pt10 pm10 sclma_ser weixin_putong" data-type="weixin">提现(秒到账)</button>


    </p>
</section>

<section class="dsf_jh_derert mui-row ">
    <a class="mui-col-xs-4 z3 " href="/user/goIndex">
        <i class="dx icon-wenjian  fz22"></i>
        <p class="fz12">首页</p>
    </a>
    <a class="mui-col-xs-4 z3" href="/user/queryMyCode">
        <i class="dx icon-ziliaoku fz20"></i>
        <p class="fz12">我的料</p>
    </a>
    <a class="mui-col-xs-4 z3 act" href="/user/queryMyInfo">
        <i class="dx icon-qiandai  fz24"></i>
        <p class="fz12">我的</p>
    </a>
</section>

<!--         <p class="fz16 cen mt40">料码</p>
            <p class="cen fz12">资源变现工具，帮你提高变现率</p> -->

<%--<p class="cen mt40_">
    <a class="cen fz14  z9 sd_j_rrrt" href="https://lmjl.ttkgou.com/lmjl_core/weixin/protocol_info">
        <ins>用户使用协议</ins>
    </a>
</p>--%>

<section class="dsf_Jh_dfgf yj4" style="z-index:102">
    <p class="cen mt30"><i class="dx icon-load cf fz40 dsdf_df_ert"></i></p>
    <p class="cen cf mt10">处理中</p>
</section>
<div class="bgb"
     style="position:fixed; top:0; right:0; left:0; bottom:0; background:rgba(0,0,0,.6); width:100%;height:100%; display:none; z-index:101"></div>
</body>

<script type="text/javascript">
    $(function () {
        //提现
        var s_drer = false
        $(".weixin_commit,.zfb_commit,.weixin_putong").on("click", function () {
            /*     	mui.alert('数据维护提现暂停2小时，10点重新开放！');
             return;  */
            if (s_drer) {
                return;
            }
            var data_type = $(this).attr("data-type");
            var type = "1";
            if (data_type == "zfb") {
                var zfb_account = $('#zfb_account').val();//余额
                if (zfb_account == "") {
                    window.location.href = basePath + "weixin/add_zfb_account_";
                    return;
                } else {
                    type = 2;
                }
            }
            var balance = $('#balance').val();//余额
            if (balance < 2) {
                mui.alert('余额满2元才能提现！');
                return;
            }
            var inputMoney = 0;
            mui.prompt('请输入需要提现的金额', '请输入金额', '提现', ['取消', '确认'], function (e) {
                if (e.index == 1) {
                    //确认
                    inputMoney = e.value;
                    //小数比较可能会报错
                    if (inputMoney * 100 > balance * 100) {

                        mui.alert('提现金额不能大于余额');
                        return;
                    }
                    if (inputMoney < 2) {
                        mui.alert('提现金额至少2元');
                        return;
                    }
                    if (inputMoney > 20000) {
                        mui.alert('提现金额每天最多20000');
                        return;
                    }
                    $.ajax({
                        type: 'POST',
                        url: '/trade/oper_cash',
                        sync: true,
                        data: {
                            'type': type,
                            'inputMoney': inputMoney
                        },
                        dataType: 'json',
                        success: function (result) {
                            $(".dsf_Jh_dfgf").removeClass("show");
                            $(".bgb").hide();
                            s_drer = false;
                            if (result.success) {//成功
                                //alert(result.msg)
                                /* mui.alert(result.msg, '提示', function () {
                                     window.location.href = basePath + "weixin/account_info";
                                 });*/
                                mui.toast("提现成功");
                                //更新余额
                                $('#p1').html(result.balance);
                                $('#balance').val(result.balance);
                            } else {
                                //mui.alert(result.msg)
                                mui.alert(result.msg);
                            }
                        },
                        error: function () {
                            s_drer = false;
                            $(".dsf_Jh_dfgf").removeClass("show");
                            $(".bgb").hide();
                            mui.alert("网络错误！")
                        }
                    });
                } else {
                    return;
                }
            }, 'div');
            /* $(".dsf_Jh_dfgf").addClass("show");
             $(".bgb").show();
             s_drer = true;*/

        })
    })

</script>
</html>