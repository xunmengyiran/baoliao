<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>关注列表</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link href="/css/mui.min.css" rel="stylesheet"/>
    <link href="/css/mui.picker.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/mui.min.js"></script>
    <script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
    <style type="text/css">
        body {
            margin: 8px;
            padding: 8px;
        }

        .user_icon_e.ab {
            width: 26px;
            height: 26px;
            float: right;
        }

        .yj {
            border-radius: 50%;
            -moz-border-radius: 50%;
            -webkit-border-radius: 50%
        }

        .cz {
            vertical-align: middle !important
        }
    </style>
</head>
<body>
<script type="text/javascript">
    mui.init()
    (function ($) {
        $('#focusList').on('tap', '.mui-btn', function (event) {
            var elem = this;
            var li = elem.parentNode.parentNode;
            mui.confirm('确认取消关注？', '取消关注', ['取消', '确认'], function (e) {
                if (e.index == 0) {
                    setTimeout(function () {
                        $.swipeoutClose(li);
                    }, 0);
                } else {
                    li.parentNode.removeChild(li);
                    //TODO 调用后台接口进行删除
                }
            });
        });
    })(mui);

</script>
<div class="mui-content">
    <ul id="focusList" class="mui-table-view">
        <c:forEach var="user" items="${sessionScope.focusList}">
        <li style="padding: 0px" class="mui-table-view-cell">
            <div class="mui-slider-left mui-disabled">
                <a class="mui-btn mui-btn-red">取消关注</a>
            </div>
            <div style="border-bottom: 1px solid #F4F4F4;margin-top: 10px;" class="mui-slider-handle">
                <div style="margin-bottom: 6px">
                    <%-- <span style="font-size: 10px;color: #BCBCBC;"><fmt:formatDate value="${trade.createTime}"
                                                                                   pattern="yyyy-MM-dd HH:ss:mm"/></span>--%>
                    <%--<span style="float:right;font-size: 10px;color: #BCBCBC;">寻梦依然</span>--%>
                    <%--<img class='user_icon_e yj cz ab' src="http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqfAA1AJAgRCFthEdAvqzMSut19A09ibzBVv5lkjdia643BGmXrLKeZZJ5sXptUyjrHyILcJHcax58A/132">--%>
                    <%-- <span style="float:right;font-size: 10px;color: #BCBCBC;">${sessionScope.user.nickName}</span>
                     <img class='user_icon_e yj cz ab' src="${sessionScope.user.headImgUrl}" alt="">--%>
                </div>
                <div> <span>
                <img style="width: 26px;height: 26px" src="${user.headImgUrl}">
            </span>
                    <span>
                <span style="color: #EBC49D;padding-right: 4px;">${user.nickName}</span>
            </span>
                </div>
            </div>
        </li>
        </c:forEach>
    </ul>
</div>
<div>
    <p style="text-align: center;color: #797979;font-size: 15px;">亲，没有更多数据了--！</p>
</div>
</body>
</html>
