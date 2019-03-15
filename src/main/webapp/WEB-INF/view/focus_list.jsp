<%@ page import="com.baoliao.weixin.bean.User" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>关注列表</title>
</head>
<body>
<c:forEach var="user" items="${sessionScope.focusList}">
    <c:out value="${user.openId}"></c:out>
</c:forEach>
</body>
</html>