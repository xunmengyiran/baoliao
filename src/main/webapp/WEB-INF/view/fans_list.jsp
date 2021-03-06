<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>粉丝列表</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link href="/css/mui.min.css" rel="stylesheet"/>
    <link href="/css/mui.picker.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/mui.min.js"></script>
    <script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
    <style type="text/css">
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
<ul id="focusList" style="position:initial" class="mui-table-view">
    <%--<li style="padding: 0px" class="mui-table-view-cell">
        &lt;%&ndash;<div class="mui-slider-left mui-disabled">
            <a class="mui-btn mui-btn-red">取消关注</a>
        </div>&ndash;%&gt;
        &lt;%&ndash;<div  class="mui-slider-handle">
            <div style="margin-bottom: 6px">
            </div>
            <div> <span>
            <img style="width: 26px;height: 26px" src="http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqfAA1AJAgRCFthEdAvqzMSut19A09ibzBVv5lkjdia643BGmXrLKeZZJ5sXptUyjrHyILcJHcax58A/132">
        </span>
                <span>
            <span style="color: #EBC49D;padding-right: 4px;">寻梦依然</span>
        </span>
            </div>
        </div>&ndash;%&gt;
        <div class="mui-slider-handle">
            <img style="width: 26px;height: 26px" src="http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqfAA1AJAgRCFthEdAvqzMSut19A09ibzBVv5lkjdia643BGmXrLKeZZJ5sXptUyjrHyILcJHcax58A/132">
            <span style="color: #EBC49D;padding-right: 4px;">寻梦依然</span>
            </span>
        </div>
    </li>
    <li style="padding: 0px" class="mui-table-view-cell">
        <div class="mui-slider-left mui-disabled">
            <a class="mui-btn mui-btn-red">取消关注</a>
        </div>
        &lt;%&ndash;<div  class="mui-slider-handle">
            <div style="margin-bottom: 6px">
            </div>
            <div> <span>
            <img style="width: 26px;height: 26px" src="http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqfAA1AJAgRCFthEdAvqzMSut19A09ibzBVv5lkjdia643BGmXrLKeZZJ5sXptUyjrHyILcJHcax58A/132">
        </span>
                <span>
            <span style="color: #EBC49D;padding-right: 4px;">寻梦依然</span>
        </span>
            </div>
        </div>&ndash;%&gt;
        <div class="mui-slider-handle">
            <img style="width: 26px;height: 26px" src="http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqfAA1AJAgRCFthEdAvqzMSut19A09ibzBVv5lkjdia643BGmXrLKeZZJ5sXptUyjrHyILcJHcax58A/132">
            <span style="color: #EBC49D;padding-right: 4px;">寻梦依然</span>
            </span>
        </div>
    </li>--%>
    <c:forEach var="user" items="${sessionScope.fansList}">
        <li style="padding: 0px" class="mui-table-view-cell">
            <div style="margin-top: 5px;margin-bottom: 5px;padding-left: 10px;padding-right: 10px"
                 class="mui-slider-handle">
                <img style="vertical-align: middle; width: 26px;height: 26px" src="${user.headImgUrl}">
                <span style="color: RGB(34, 34, 34);padding-right: 4px;font-size: 12px">${user.nickName}</span>
                <span style="font-size: 10px;color: #BCBCBC;float: right;padding-right: 6px"><fmt:formatDate
                        value="${user.subscribeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
            </div>
        </li>
    </c:forEach>
</ul>
<div>
    <p style="text-align: center;color: #797979;font-size: 15px;">亲，没有更多数据了--！</p>
</div>
</body>
</html>
