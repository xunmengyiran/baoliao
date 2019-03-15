<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        body {
            background: url("/img/muban1.jpg");
            background-repeat: no-repeat;
            background-size: 100% 100%;

        }
    </style>
</head>
<body>
<div style="text-align: center;margin-top: 80%;">
    <img style="width: 50%;" src="http://47.98.48.197:8090/QRCodeImg/${sessionScope.fileName}">
</div>
<div style="margin-top: 14%;margin-left: 12%;">
    <img style="width: 17%;position: relative;" src="${sessionScope.user.headImgUrl}">
</div>
<span style="margin-left: 28%;margin-top: -37px;position: absolute;color: #fff;">${sessionScope.user.nickName}</span>
<c:if test="${sessionScope.product.price == 0}">
    <span style="color: #fff;margin-left: 71%;margin-top: -46px;position: absolute;"><span
            style="margin-right: 3px;font-size: 25px;color: #fff">免费</span></span>
</c:if>
<c:if test="${sessionScope.product.price!=0}">
    <span style="color: #fff;margin-left: 71%;margin-top: -46px;position: absolute;"><span
            style="margin-right: 3px;font-size: 25px;color: #fff">${sessionScope.product.price}</span>元</span>
</c:if>
</body>
</html>
