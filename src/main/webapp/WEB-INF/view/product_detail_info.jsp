<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/index.css" type="text/css"/>
    <link href="/css/mui.min.css" rel="stylesheet"/>
    <link href="/css/mui.picker.min.css" rel="stylesheet"/>
    <link href="/css/base.css?v20180601" rel="stylesheet"/>
    <link href="/css/style.css?v20180602" rel="stylesheet"/>
    <link href="/css/iconfont.css" rel="stylesheet"/>
    <link href="/css/swiper3.07.min.css" rel="stylesheet"/>
    <title>${sessionScope.product.title}</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<section class="cen df_jh_drt">
    <img src="/img/logo.png" class="logo_dert"/>
    <p>资源变现工具</p>
</section>
<div style="text-align: center;border-bottom: 1px solid #F9F7F8">
    <p style="font-size: 22px;margin-top: 24px;">${sessionScope.product.introduct}</p>
</div>
<div style="margin-left: 15px;margin-bottom: 22px;margin-top: 21px">
    <p style="font-size: 20px;color: #A6A4A7"><fmt:formatDate value="${sessionScope.product.createTime}"
                                                              pattern="yyyy-MM-dd HH:ss:mm"/></p>
    <p style="font-size: 25px;color: #8B8B8B">${sessionScope.product.content}</p>

</div>
<c:forEach var="img" items="${sessionScope.imgArr}">
    <div style="text-align: center;">
        <img style="width: 92%;" src="http://47.98.48.197:8090/userImg/${img}">
    </div>
</c:forEach>
</body>
</html>
