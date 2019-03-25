<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
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
<div style="text-align: center;background-color: #F9F7F8;height: 120px;">
    <img style="width: 17%;margin-top: 9px;" src="/img/logo.png">
    <p style="margin-top: 0px;font-size: 24px;color: #A6A4A7">资源变现工具</p>
</div>
<div style="text-align: center;border-bottom: 1px solid #F9F7F8">
    <p style="font-size: 28px;margin-top: 65px;">${sessionScope.product.introduct}</p>
</div>
<div style="margin-left: 15px;">
    <p style="font-size: 20px;color: #A6A4A7"><fmt:formatDate value="${sessionScope.product.createTime}"
                                                              pattern="yyyy-MM-dd HH:ss:mm"/></p>
    <p style="font-size: 28px;color: #8B8B8B">${sessionScope.product.content}</p>

</div>
<c:forEach var="img" items="${sessionScope.imgArr}">
    <div style="text-align: center;">
        <img style="width: 92%;" src="http://47.98.48.197:8090/userImg/${img}">
    </div>
</c:forEach>
</body>
</html>
