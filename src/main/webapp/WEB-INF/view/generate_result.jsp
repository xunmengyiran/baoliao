<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <!DOCTYPE html>
<html>
<head>
       
    <meta charset="utf-8">
       
    <meta content="width=device-width, height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
              name="viewport">
        <title>料信息</title>
</head>
<style>
    html, body {
        margin: 0;
        padding: 0;
        overflow: hidden;
    }

    .content {
        width: 100%;
        height: 100%;
        top: 0;
        z-index: -1;
        position: absolute;
    }

    .content img {
        display: block;
        outline: none;
        border: 0;
        height: 100%;
        width: 100%;
    }

    /* img {
         height: auto;
         width: auto \9;
         width: 100%;
     }*/
</style>
<body>
<div class="content">
    <img style="width:100%;heignt:100%;" src="http://47.98.48.197:8090/QRCodeImg/${sessionScope.fileName}" alt="">
</div>
</body>
</html>