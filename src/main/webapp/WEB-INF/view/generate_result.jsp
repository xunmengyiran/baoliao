<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 <!DOCTYPE html>
<html>
<head>
       
    <meta charset="utf-8">
       
    <%--  <meta content="width=device-width, height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
                name="viewport">--%>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link href="/css/mui.min.css" rel="stylesheet"/>
    <link href="/css/mui.picker.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/mui.min.js"></script>
    <script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
        <title>料信息</title>
    <style type="text/css">
        body {
            margin: 8px;
            padding: 8px;
            background: white;
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
<script>
    function refundMoey() {
        mui.confirm('你将退还给购买此料的所有人', '一键退款', ['取消', '确认'], function (e) {
            if (e.index == 1) {
                $.ajax({
                    type: "POST",
                    url: "/trade/refundMoneyByBalance",
                    data: {
                        productId: ${sessionScope.product.id}
                    },
                    dataType: 'json',
                    success: function (data) {
                        // 只有返回删除成功才可以remove
                        if (data.success) {
                            mui.alert("退款成功！");
                        } else {
                            refundMoeyByWeChatPay();
                        }
                    },
                    error: function () {
                        mui.alert("程序发生错误!");
                    }
                });

            }
        });
    }

    function refundMoeyByWeChatPay() {
        $.ajax({
            url: '/trade/refundMoneyByWeChatPay',
            data: {
                productId: ${sessionScope.product.id}
            },
            beforeSend: function () {
                /*  $(".loading_box ,.disalog_bg3").show(); 	 */
            },
            success: function (result) {
                result = $.parseJSON(result);
                s_drer = false;
                $(".dsf_Jh_dfgf").removeClass("show");
                if (result.success) {
                    appId = result.data.appId;
                    timeStamp = result.data.timeStamp;
                    nonceStr = result.data.nonceStr;
                    packageStr = result.data.package;
                    signType = result.data.signType;
                    paySign = result.data.paySign;
                    onBridgeReady(appId, timeStamp, nonceStr, packageStr, signType, paySign);
                    // mui.toast("支付成功");
                    // window.location.href = '/product/detailInfo2?id=' + en_id;
                } else {
                    mui.alert("退款失败！");
                }
            }
        });
    }

    function onBridgeReady(appId, timeStamp, nonceStr, packageStr, signType, paySign) {
        var id = $('#id').val();
        var en_id = $('#en_id').val();
        WeixinJSBridge.invoke(
            'getBrandWCPayRequest', {
                "appId": appId,     //公众号名称，由商户传入
                "timeStamp": timeStamp, //时间戳，自1970年以来的秒数
                "nonceStr": nonceStr, //随机串
                "package": packageStr,
                "signType": signType,  //微信签名方式：
                "paySign": paySign    //微信签名
            },
            function (res) {
                mui.alert(res.err_msg);
                /* toast(JSON.stringify(res));   */
                if (res.err_msg == "get_brand_wcpay_request:ok") {
                    // window.location.href = path + '/weixin/resource_pay_suc?id=' + en_id;
                    mui.alert("退款成功！");
                }
                if (res.err_msg == "get_brand_wcpay_request:cancel") {
                }
                if (res.err_msg == "get_brand_wcpay_request:fail") {
                    mui.alert("退款失败！");
                }
            }
        );
    }
</script>
<div style="text-align: center;">
    <img src="http://47.98.48.197:8090/QRCodeImg/${sessionScope.fileName}">
    <%--<img src="http://47.98.48.197:8090/QRCodeImg/20190416211900026593.jpg">--%>
</div>
<div style="margin-top: 20px;text-align: center;font-size: 20px;color: grey;">
    <span>点击图片长按保存到相册</span></br>
    <span>方便发到群里或朋友圈</span>
</div>
<button class="button button1"
        onclick="window.location.href='/product/detailInfo2?id=${sessionScope.product.id}'">
    查看详情
</button>
<c:if test="${fn:length(sessionScope.buyerUserInfoList) != 0 && sessionScope.product.isRefund == 1 && sessionScope.product.expritationDate ne '2000-01-01 00:00'}">
    <button style="margin-top: -3px;" class="button button1" onclick="refundMoey()">一键退款</button>
</c:if>
<button style="margin-top: 3px;" class="button button2" onclick="javascript:history.back();">关闭</button>
<div style="padding-top: 18px;padding-bottom: 14px">
    <span style="padding-left: 4px;font-size: 16px">收益总额 <span
            style="font-size: 16px;color: red;margin-left: 4px;">${fn:length(sessionScope.buyerUserInfoList) * sessionScope.product.price}</span>元</span>
    <span style="padding-right: 4px;font-size: 16px;float: right">已售 <span
            style="font-size: 16px;color: red;margin-left: 4px;">${fn:length(sessionScope.buyerUserInfoList)}</span>份</span>
</div>
<ul style="padding-left: 5px;position:initial">
    <%--  <li style="padding: 0px" class="mui-table-view-cell">
          <div style="margin-top: 5px;margin-bottom: 5px;padding-left: 10px;padding-right: 10px" class="mui-slider-handle">
              <img style="vertical-align: middle; width: 26px;height: 26px" src="http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqfAA1AJAgRCFthEdAvqzMSut19A09ibzBVv5lkjdia643BGmXrLKeZZJ5sXptUyjrHyILcJHcax58A/132">
              <span style="color: RGB(34, 34, 34);padding-right: 4px;font-size: 16px">寻梦依然</span>
              <span style="font-size: 14px;color: #BCBCBC;float: right">2019-04-16 22:22:22</span>
          </div>
      </li>
      <li style="padding: 0px" class="mui-table-view-cell">
          <div style="margin-top: 5px;margin-bottom: 5px;padding-left: 10px;padding-right: 10px" class="mui-slider-handle">
              <img style="vertical-align: middle; width: 26px;height: 26px" src="http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqfAA1AJAgRCFthEdAvqzMSut19A09ibzBVv5lkjdia643BGmXrLKeZZJ5sXptUyjrHyILcJHcax58A/132">
              <span style="color: RGB(34, 34, 34);padding-right: 4px;font-size: 16px">寻梦依然</span>
              <span style="font-size: 14px;color: #BCBCBC;float: right">2019-04-16 22:22:22</span>
          </div>
      </li>--%>
    <c:forEach var="buyerUserInfo" items="${sessionScope.buyerUserInfoList}">
        <li style="padding: 0px" class="mui-table-view-cell">
            <div style="margin-top: 5px;margin-bottom: 5px;padding-left: 10px;padding-right: 10px"
                 class="mui-slider-handle">
                <img style="vertical-align: middle; width: 26px;height: 26px" src="${buyerUserInfo.headImage}">
                <span style="color: RGB(34, 34, 34);padding-right: 4px;font-size: 12px">${buyerUserInfo.nickName}</span>
                <span style="font-size: 14px;color: #BCBCBC;float: right"><fmt:formatDate value="${buyerUserInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
        </div>
    </li>
</c:forEach>
</ul>
</body>
</html>