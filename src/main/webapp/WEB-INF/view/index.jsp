<%@ page import="com.baoliao.weixin.bean.User" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<% List<User> userList = (List<User>)request.getAttribute("userList");
for(int i=0;i<userList.size();i++){
    System.out.println(userList.get(i).getOpenId());
    System.out.println("=========>>>"+request.getParameter("code"));
}
%>
<c:forEach var="user" items="${requestScope.userList}">
    <c:out value="${user.openId}"></c:out>
    <c:out value="${param.code}"></c:out>
</c:forEach>
</body>
</html>