<%@page pageEncoding="UTF-8" %>
<%@ page import="com.baoliao.weixin.bean.User" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>支付料</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <link href="/css/mui.min.css" rel="stylesheet"/>
    <link href="/css/mui.picker.min.css" rel="stylesheet"/>
    <link href="/css/base.css?v20180601" rel="stylesheet"/>
    <link href="/css/style.css?v20180602" rel="stylesheet"/>
    <link href="/css/iconfont.css" rel="stylesheet"/>
    <link href="/css/swiper3.07.min.css" rel="stylesheet"/>
    <link href="/css/pay.css" rel="stylesheet"/>

    <script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="/js/mui.min.js"></script>
    <script type="text/javascript" src="/js/mui.picker.min.js"></script>
    <script type="text/javascript" src="/js/base.js?v20180813"></script>
</head>
<body>
<section class="cen df_jh_drt">
    <img src="/img/logo.png" class="logo_dert"/>
    <p>资源变现工具</p>
</section>

<section class="mt20 cen">
    <img src="${sessionScope.seller_user.headImgUrl}" class="yj user_icon_b"/>
    <p class="fz13 mt5">${sessionScope.seller_user.nickName}</p>
    <c:if test="${sessionScope.focus == 1}">
        <div><span class="sd_guanzhu_">已关注</span></div>
    </c:if>
    <c:if test="${sessionScope.focus == 0}">
        <div><span class="sd_guanzhu">关注作者</span></div>
    </c:if>
    <span class="b fz16 sd_jgh_ddr">${sessionScope.product.title}</span>
    <section class="mt15">
        <span class="fz14 dserrf_jh_d br yj4"><span
                class="num_er red fz30">${sessionScope.product.price}</span> 元</span>
    </section>
    <input type="hidden" name="price" id="price" value="${sessionScope.product.price}"/>
    <input type="hidden" name="id" id="id" value="${sessionScope.product.id}"/>
    <input type="hidden" name="en_id" id="en_id" value="${sessionScope.product.id}"/>
    <input type="hidden" name="buyer_openId" id="buyer_openId" value="${sessionScope.buyer_user.openId}"/>
    <input type="hidden" name="seller_openId" id="seller_openId" value="${sessionScope.seller_user.openId}"/>
    <input type="hidden" name="balance" id="balance" value="${sessionScope.balance}"/>

    <section class="eer_deert"></section>

    <p class="fz12 mt5 z3 cen  pd">
        <a class="mui-btn bgls  pt10 pm10 fz16 " style="width:320px" id="pay_resource" data-type="weixin" onclick="payByWeChat()">微信支付获取付费资源</a>
        <%--<a class="mui-btn bgls_zfb  pt10 pm10 fz16 mt10" style="width:320px" id="pay_resource_zfb" data-type="zfb">支付宝支付获取付费资源</a>--%>
    </p>

    <p class="red fz12 dfdf_deererty mt20">温馨提示：关注彩料助手公众号 <br/>新建料、查看购买记录、接收消息提醒</p>

</section>

<section class="dsf_Jh_dfgf yj4">
    <p class="cen mt30"><i class="dx icon-load cf fz40 dsdf_df_ert"></i></p>
    <p class="cen cf mt10">处理中</p>
</section>

<section class="sd_jh_derett cen">
    <img src="http://47.98.48.197:8090/qrcode_for_gh_4a3e9c82befd_258.jpg" class="sd_hjg_deeert"/>
    <p class="red fz12 dfdf_deererty ">长按识别 关注公众号</p>
</section>
</body>

<script type="text/javascript" src="/js/pay.js"></script>

</html>