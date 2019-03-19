<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <!-- 本地资源 -->
    <link rel="stylesheet" href="/css/index.css" type="text/css"/>
    <link href="/css/mui.min.css" rel="stylesheet"/>
    <link href="/css/mui.picker.min.css" rel="stylesheet"/>
    <link href="/css/base.css?v20180601" rel="stylesheet"/>
    <link href="/css/style.css?v20180602" rel="stylesheet"/>
    <link href="/css/iconfont.css" rel="stylesheet"/>
    <link href="/css/swiper3.07.min.css" rel="stylesheet"/>


    <script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="/js/mui.min.js"></script>
    <script type="text/javascript" src="/js/mui.picker.min.js"></script>
    <script type="text/javascript" src="/js/base.js?v20180813"></script>
    <%--<script type="text/javascript" src="/js/lrz.bundle.js"></script>--%>
    <script type="text/javascript" src="/js/lrz.mobile.min.js"></script>

</head>
<body>
<!--  <div class="animate">温馨提示:由于微信朋友圈风控，单张料码图发朋友圈会被微信屏蔽，您可以同时发2张图避免此问题（我司会在2号之前修复此问题，谢谢理解!）  </div> -->
<section class="cen df_jh_drt">
    <img src="/img/logo.png" class="logo_dert"/>
    <p>资源变现工具</p>
    <%--<a onclick="testAjax()">11111111111111</a>--%>
</section>
<section class="mt20">
    <ul class="pl10 sdfsdf fz16">
        <input type="text" hidden="hidden" name="code" id="code" value="${param.code}"/>
        <li class="pr10">
            <span class="bgzhu dsf_jh_s yj"></span> 标题
            <p class="mt15 dfer_jh_drt">
                <input type="text"  id="title" maxlength="18"/>
            </p>
        </li>
        <li class="pr10">
            <span class="bgzhu_ dsf_jh_s yj"></span> 简介<span class="red fz12 ml5">(可不填)</span>
            <p class="mt15 dfer_jh_drt">
                <textarea id="introduce" maxlength="45" rows="2"></textarea>
            </p>
        </li>
        <li>
            <span class="bgzhu dsf_jh_s yj"></span> 料
            <section class="pr10 mt10 pr dsfs_jh_derrt aa">
                <textarea id="content" rows="5"></textarea>
                <i class="dx closd_dert icon-guanbi z9"></i>
            </section>
            <section class="pr10 mt10 pr dsfs_jh_derrt ab  mui-row">
                <section class="mui-col-xs-4 pr10 dsf_jh_dr_reet">
                    <section class="sd_jhh_s yj4 br cen">
                        <i class="dx icon-jia ls b fz26"></i>
                        <input name="imgLogo" type="file" onchange="fileChange(this)" id="ssd_ooie" multiple/><br/>
                        <!--  <input type="hidden" name="img" id="img" /> -->
                    </section>
                </section>
            </section>

            <section class="mt15">
                <section class="fd_jh_dertt aa">
                    <section class="yj4 br sd_j_deetrtxd">
                        <i class="dx icon-pan_icon cz ye fz26"></i>
                    </section>
                    <p class="mt5">文字</p>
                </section>
                <section class="fd_jh_dertt ml20 ab">
                    <section class="yj4 br sd_j_deetrtxd">
                        <i class="dx icon-tupian cz ye fz26"></i>
                    </section>
                    <p class="mt5">图片</p>
                </section>
                <p class="qc"></p>
            </section>
        </li>
        <li>
            <span class="bgzhu dsf_jh_s yj"></span> 定价
            <section class="mui-row">
                <section class="mui-col-xs-3 pr10">
                    <p class="yj4 br sjh_de_det act">
                        免费
                    </p>
                </section>
                <section class="mui-col-xs-3 pr10">
                    <p class="yj4 br sjh_de_det">
                        1元
                    </p>
                </section>
                <section class="mui-col-xs-3 pr10">
                    <p class="yj4 br sjh_de_det">
                        5元
                    </p>
                </section>
                <section class="mui-col-xs-3 pr10">
                    <p class="yj4 br sjh_de_det">
                        18元
                    </p>
                </section>
                <section class="mui-col-xs-3 pr10">
                    <p class="yj4 br sjh_de_det">
                        38元
                    </p>
                </section>
                <section class="mui-col-xs-3 pr10">
                    <p class="yj4 br sjh_de_det">
                        68元
                    </p>
                </section>
                <section class="mui-col-xs-3 pr10">
                    <p class="yj4 br sjh_de_det">
                        88元
                    </p>
                </section>
                <section class="mui-col-xs-3 pr10">
                    <p class="yj4 br sjh_de_det">
                        188元
                    </p>
                </section>
            </section>

            <p class="mt15 dfer_jh_drt pr10">
                <input type="text" placeholder="自定义金额" id="price"/>
            </p>
        </li>

    </ul>
</section>

<section class="sd_hjh_dertd">
    <section class="sdff_jhh_ert">
        <p class="fz16 z3 pd pt10 pm10">
            其他功能 <span class="red fz12">(非必填)</span>
        </p>
        <section class="pd">

            <ul class="mui-table-view">
                <li class="mui-table-view-cell mui-collapse">
                    <a class="mui-navigate-right fz14">
                        过期时间
                    </a>
                    <div class="mui-collapse-content">
                        <p class="red1 fz12 dgf_deert">所选时间为料付费截止时间，此时段以后将不用付费可免费查看</p>
                        <div class="mui-row">
                            <p class="mui-col-xs-6"><input type="text" class=" guoqishij time" placeholder="请选择过期时间"/>
                            </p>
                            <!--                                <p class="mui-col-xs-6"> <input type="text" class=" guoqishij time" readonly placeholder="请选择过期时间" ></p> -->
                            <p class="mui-col-xs-6 tl pl10">
                                <a class="mui-btn ls ds_dd_deert fz12">清空</a>
                            </p>
                        </div>
                    </div>
                </li>

                <li class="mui-table-view-cell mui-collapse">
                    <a class="mui-navigate-right fz14">
                        是否退款
                    </a>
                    <div class="mui-collapse-content">
                        <p class="red fz12 dgf_deert">选中有退款将会有一键退款的功能</p>
                        <div class="mui-row df_rt_errtyxc">

                            <span class="yj dx br sd_jh_dert cz ao"><i class="dx icon-success"></i></span>
                            <span class="fz14 z3">有退款</span>
                        </div>
                    </div>
                </li>

                <li class="mui-table-view-cell mui-collapse">
                    <a class="mui-navigate-right fz14">选择模板</a>
                    <div class="mui-collapse-content">
                        <p class="red fz12 dgf_deert">点击图片选择需要生成的料码模版</p>
                        <div class="mui-row">
                            <section class=" dfer_jh_drt">
                                <section class="mui-col-xs-4 pr10 df_jhh_deert">
                                    <section class=" yj4  cen pr">
                                        <img src="/img/muban1.jpg" data-type="DE3319_FD4626"
                                             class="sd_jh_dertx_mb ab"/>
                                    </section>
                                </section>
                            </section>
                        </div>
                    </div>
                </li>
            </ul>
        </section>
    </section>
</section>

<section class="btm  pd">
    <p>
        <button class="w100 fz16 bgls pt10 pm10 sclma_ser mt50">生成料码</button>
    </p>
</section>

<section class="dsf_dertyx ">
    <section class="cz_w dsf_h_deret pr">
        <section class="cz_mb ">
            <div class="mui-row pl10 dsf_jhh_xdf bgff">
                <div class="mui-col-xs-6 pr10 " data-type="FE761E_EC3004">
                    <div class="pr">
                        <img src="/img/muban1.jpg" class="w100"/>
                    </div>
                </div>
            </div>
        </section>
    </section>
</section>
<section class="dsf_jh_derert mui-row ">
    <a class="mui-col-xs-4 z3 act" href="/user/goIndex">
        <i class="dx icon-wenjian  fz22"></i>
        <p class="fz12">首页</p>
    </a>
    <a class="mui-col-xs-4 z3" href="/user/queryMyCode">
        <i class="dx icon-ziliaoku fz20"></i>
        <p class="fz12">我的料</p>
    </a>
    <a class="mui-col-xs-4 z3 " href="/user/queryMyInfo">
        <i class="dx icon-qiandai  fz24"></i>
        <p class="fz12">我的</p>
    </a>
</section>

<section class="dsf_Jh_dfgf yj4">
    <p class="cen mt30"><i class="dx icon-load cf fz40 dsdf_df_ert"></i></p>
    <p class="cen cf mt10">处理中</p>
</section>
</body>
<script type="text/javascript" src="/js/index.js"></script>
<script>
    function testAjax() {
        var data = JSON.stringify({'phoneNumber': '130499555'})
        $.ajax({
            type: "POST",
            url: "/user/testAjax",
            contentType: "application/json",
            data: data,
            success: function (ret) {
                alert("chengg ");
            },
            error: function () {
                alert("程序发生错误!");
            }
        });
    }
</script>
</html>