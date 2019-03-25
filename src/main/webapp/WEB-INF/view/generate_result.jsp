<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <!DOCTYPE html>
<html>
<head>
       
    <meta charset="utf-8">
       
    <meta content="width=device-width, height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
              name="viewport">
        <title>料信息</title>
    <style type="text/css">
        body {
            margin: 8px;
            padding: 8px;
        }

        img {
            /* width: 100%;
             height: 100%;*/
            max-width: 100%;
        }

        .button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 20px;
            margin: 17px 2px;

        }

        .button1 {
            background-color: white;
            color: black;
            border: 2px solid #e7e7e7;
            width: 100%;
        }

        .button2 {
            background-color: #FF6B11;
            color: black;
            width: 100%;
        }
    </style>
</head>
<body>
<div style="text-align: center;">
    <img src="http://47.98.48.197:8090/QRCodeImg/${sessionScope.fileName}">
</div>
<div style="margin-top: 20px;text-align: center;font-size: 20px;color: grey;">
    <span>点击图片长按保存到相册</span></br>
    <span>方便发到群里或朋友圈</span>
</div>
<button class="button button1">查看详情</button>
<button style="margin-top: -3px;" class="button button1">一键通知<span style="font-size: 16px;color: red;margin-left: 4px;">(关注粉丝会受到消息推送)</span>
</button>
<button style="margin-top: 3px;" class="button button2">关闭</button>
</body>
</html>