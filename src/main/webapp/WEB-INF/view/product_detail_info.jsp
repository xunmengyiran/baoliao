<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/index.css" type="text/css"/>
    <link href="/css/mui.min.css" rel="stylesheet"/>
    <link href="/css/pay.css" rel="stylesheet"/>
    <link href="/css/mui.picker.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="/js/mui.min.js"></script>
    <script type="text/javascript" src="/js/mui.picker.min.js"></script>
    <script type="text/javascript" src="/js/base.js?v20180813"></script>
    <script type="text/javascript" src="/js/lrz.bundle.js"></script>
    <script src="/js/resource_record.js?v2018100888" type="text/javascript"></script>
    <script type="text/javascript" src="/js/pay.js"></script>
    <title>${sessionScope.product.title}</title>
    <title>料信息</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body style="background-color: white">
<script>
    <c:if test="${sessionScope.isPurchased == 1}">
    mui.toast("您已购买过此料，可以直接查看。")
    </c:if>
</script>
<%--<div style="text-align: center;background-color: #F9F7F8;height: 87px;">
    <img style="width: 17%;margin-top: 9px;" src="http://47.98.48.197:8090/QRCodeImg/logo.png">
    <p style="margin-top: -13px;font-size: 18px;color: #A6A4A7">资源变现工具</p>
</div>

<div style="padding-top: 11px;border-bottom: 1px solid #F9F7F8">
    <img style="width: 14%;margin-left: 11px;" src="http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELMYUVuKkz7JmG1QKDOKMZ1I7MBElhqCnxrQDnicvhYEJSQaZTtpHZ34tI7nibrlJgMFwZWBiacBz7iag/132">
    <p style="margin-top: -41px;margin-left: 73px;">和赐福</p>
    &lt;%&ndash;<p style="font-size: 14px;border:2px solid #EA0F52;width: 18%;float: right;margin-top: -39px;padding-left: 10px;padding-top: 1px;padding-bottom: 1px;margin-right: 18px;color: #EA0F52;border-radius: 5px;font-weight: 600;">关注作者</p>&ndash;%&gt;
    <p class="sd_guanzhu" style="float: right;margin-top: -39px;padding-left: 10px;padding-top: 1px;padding-bottom: 1px;margin-right: 18px;color: #EA0F52;border-radius: 5px;font-weight: 600;">关注作者</p>
    <p style="font-size: 16px;font-weight: bolder;text-align: center;margin-top: 25px;color:RGB(22, 22, 22);">推荐一直必涨牛股 多机构集体拉伸 好机会来啦</p>
</div>

<div style="margin-left: 15px;border-bottom: 1px solid #F9F7F8">
    <p style="padding-top: 8px;font-size: 16px;color: #A6A4A7">2019-01-01 22:22:00</p>
    <p style="padding-top: 21px;font-size: 16px;font-weight: bolder;text-align: center;color: RGB(22, 22, 22)">午后必涨牛股</p>

</div>
<div style="margin-left: 15px;">
    <p style="padding-top: 8px;font-size: 16px;color: #A6A4A7">2019-01-01 22:22:00</p>
    <span style="padding-top: 8px;font-size: 16px;color: #7E7E7E">000000   下午1点40后准时买入</span>
</div>--%>
<div style="text-align: center;background-color: #F9F7F8;height: 87px;">
    <img style="width: 17%;margin-top: 9px;" src="http://47.98.48.197:8090/QRCodeImg/logo.png">
    <p style="margin-top: -13px;font-size: 18px;color: #A6A4A7">资源变现工具</p>
</div>
<input type="hidden" name="buyer_openId" id="buyer_openId" value="${sessionScope.user.openId}"/>
<input type="hidden" name="seller_openId" id="seller_openId" value="${sessionScope.productUser.openId}"/>
<div style="padding-top: 11px;border-bottom: 1px solid #F9F7F8">
    <img style="width: 14%;margin-left: 11px;" src="${sessionScope.productUser.headImgUrl}">
    <p style="margin-top: -41px;margin-left: 73px;">${sessionScope.productUser.nickName}</p>
    <c:if test="${sessionScope.user.openId ne sessionScope.productUser.openId }">
        <c:if test="${sessionScope.focus == 1}">
            <p class="sd_guanzhu"
               style="float: right;margin-top: -39px;padding-left: 10px;padding-top: 1px;padding-bottom: 1px;margin-right: 18px;color: #EA0F52;border-radius: 5px;font-weight: 600;">
                已关注</p>
        </c:if>
        <c:if test="${sessionScope.focus == 0}">
            <p class="sd_guanzhu"
               style="float: right;margin-top: -39px;padding-left: 10px;padding-top: 1px;padding-bottom: 1px;margin-right: 18px;color: #EA0F52;border-radius: 5px;font-weight: 600;">
                关注作者</p>
        </c:if>
    </c:if>
    <p style="font-size: 16px;font-weight: bolder;text-align: center;margin-top: 25px;color:RGB(22, 22, 22);">${sessionScope.product.introduct}</p>
</div>

<%--<div style="margin-left: 15px;">
    <p style="padding-top: 8px;font-size: 16px;color: #A6A4A7">2019-04-09 22:22:22</p>
    <span style="padding-top: 8px;font-size: 16px;color: #7E7E7E">dev</span>
</div>--%>

<div style="margin-left: 15px;">
    <p style="padding-top: 8px;font-size: 16px;color: #A6A4A7"><fmt:formatDate
            value="${sessionScope.product.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
    <span style="padding-top: 8px;font-size: 16px;color: #7E7E7E">${sessionScope.product.content}</span>
</div>
<c:forEach var="img" items="${sessionScope.imgArr}">
    <div style="text-align: center;">
        <img style="width: 92%;" src="http://47.98.48.197:8090/userImg/${img}">
    </div>
</c:forEach>
</body>
<%--<section class="cen df_jh_drt">
    <img src="/img/logo.png" class="logo_dert"/>
    <p>资源变现工具</p>
</section>
<div style="text-align: center;border-bottom: 1px solid #F9F7F8">
    <p style="font-size: 22px;margin-top: 24px;">${sessionScope.product.introduct}</p>
</div>
<div style="margin-left: 15px;margin-bottom: 22px;margin-top: 21px">
    <p style="font-size: 20px;color: #A6A4A7"><fmt:formatDate value="${sessionScope.product.createTime}"
                                                              pattern="yyyy-MM-dd HH:mm:ss"/></p>
    <p style="font-size: 25px;color: #8B8B8B">${sessionScope.product.content}</p>

</div>
<c:forEach var="img" items="${sessionScope.imgArr}">
    <div style="text-align: center;">
        <img style="width: 92%;" src="http://47.98.48.197:8090/userImg/${img}">
    </div>
</c:forEach>
</body>--%>
</html>
