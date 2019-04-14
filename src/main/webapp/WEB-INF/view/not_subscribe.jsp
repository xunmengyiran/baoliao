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
    mui.confirm('为了你有更好的体验，请先关注公众号。', '关注公众号', ['取消', '确认'], function (e) {
        if (e.index == 0) {
            WeixinJSBridge.call('closeWindow');
        } else {
            // $("#div1").show();

        }
    });
</script>
</body>
<div id="div1" style="display: none">
    <section class="sd_jh_derett cen">
        <img id="img1" src="http://47.98.48.197:8090/qrcode_for_gh_4a3e9c82befd_258.jpg" class="sd_hjg_deeert"/>
        <p class="red fz12 dfdf_deererty ">长按识别 关注公众号</p>
    </section>
</div>
</html>
