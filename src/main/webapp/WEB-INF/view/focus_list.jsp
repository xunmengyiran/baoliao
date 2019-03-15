<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
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