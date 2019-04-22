<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CCQ
  Date: 2019-3-13
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link href="/css/mui.min.css" rel="stylesheet"/>
    <link href="/css/mui.picker.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/mui.min.js"></script>
    <script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
</head>
<body>
<script>
    <c:if test="${sessionScope.isScan == 1}">
    mui.alert("此料已过期，不能购买！");
    WeixinJSBridge.call('closeWindow');
    </c:if>
    <c:if test="${sessionScope.isScan == 0}">
    mui.alert("此料已过期，不能查看！");
    history.back();
    </c:if>
</script>
</body>
</html>
