<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的料</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <script type="text/javascript">
        var path = "/lmjl_core";
        var basePath = "https://lmjl.ttkgou.com/lmjl_core/";
        var img_url = "http://lm-img.oss-cn-shenzhen.aliyuncs.com/res/";
    </script>
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
    <script type="text/javascript" src="/js/lrz.bundle.js"></script>
</head>
<body>
<section class="pr bgff">
    <section class="cen df_jh_drt">
        <img src="/img/logo.png" class="logo_dert">
        <p>资源变现工具</p>
    </section>
    <section class="mui-row mt10 pm10 cen dsf_jherr_der">
        <section class="mui-col-xs-6 fz16 act">
            我卖的料(0)
        </section>
        <section class="mui-col-xs-6 fz16">
            我买的料(0)
        </section>
    </section>
</section>

<section class="sdf_khj_dert  show aa">
    <ul>
    </ul>
    <p class="pt10 pm10 fz13 cen btm z3 chakn_jghd_dr aa">点击查看更多</p>
</section>

<section class="sdf_khj_dert   ab">
    <ul>

    </ul>
    <p class="pt10 pm10 fz13 cen btm z3 chakn_jghd_dr ab">点击查看更多</p>
</section>

<section class="dsf_jh_derert mui-row ">
    <a class="mui-col-xs-4 z3 act" href="http://localhost/user/queryUserList">
        <i class="dx icon-wenjian  fz22"></i>
        <p class="fz12">首页</p>
    </a>
    <a class="mui-col-xs-4 z3" href="http://localhost/user/queryMyCode">
        <i class="dx icon-ziliaoku fz20"></i>
        <p class="fz12">我的料</p>
    </a>
    <a class="mui-col-xs-4 z3 " href="http://localhost/user/queryMyInfo">
        <i class="dx icon-qiandai  fz24"></i>
        <p class="fz12">我的</p>
    </a>
</section>
<script src="/js/resource_record.js?v2018100888" type="text/javascript"></script>
</body>
</html>