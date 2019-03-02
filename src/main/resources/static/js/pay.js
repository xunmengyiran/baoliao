var path = "/lmjl_core";
var basePath = "https://lmjl.ttkgou.com/lmjl_core/";
var img_url = "http://lm-img.oss-cn-shenzhen.aliyuncs.com/res/";

$(function() {
    $(".dsf_drer_ert li").on("click", function() {
        $(".sd_jh_dert").removeClass("act")
        $(".dsf_drer_ert li").removeClass("act")
        $(this).addClass("act")
        $(this).find(".sd_jh_dert").addClass("act")
    })
    
    $(".sd_guanzhu").on("click", function() {
    	var id = $('#id').val();
		$.ajax({
			type : 'POST',
			url : path+'/weixin/attention_add',
			data :{
				'id' : id
			},
			dataType : 'json',
			success : function(result) {
	            if (result.success) {
	            	$(".sd_guanzhu").text("已关注");
	            	$(".sd_guanzhu").css("pointer-events","none");
	            	$(".sd_guanzhu").css("color","#ff6b11");
	            	$(".sd_guanzhu").css("border","1px solid #ff6b11");
				}else{
					 mui.toast(result.msg)}
			},
			error : function() {
				mui.toast("网络错误!")}
		});
    })
})
  var s_drer=false
 $('#pay_resource,#pay_resource_zfb').click(function(){
	    if(s_drer){
            return }
	   var data_type=$(this).attr("data-type");
       var amount = $('#price').val();//单价
       var id = $('#id').val();
       var en_id = $('#en_id').val();
       var balance = $('#balance').val();
       //是否选中-未选中
       var wx_amount =amount;
       var ba_amount ='0';
       var sd_ert= $(".dsf_drer_ert li.act").attr("pay-type");
       if(sd_ert=="1"){//余额支付。或者余额+微信支付
           if(balance>=parseFloat(amount)){
              wx_amount ='0';
              ba_amount =amount;
          }else{
              wx_amount =amount-balance;
              ba_amount =balance;}
       }
       var type ="1";
       if(ba_amount !="0"){
    	   type="2";}
/*        toast(amount);
       toast(wx_amount);
       toast(ba_amount);
       return; */
       $(".dsf_Jh_dfgf").addClass("show")
       s_drer=true;
       if(wx_amount=='0'){//余额支付
	  		$.ajax({url: path+'/weixin/pay_balance', 
				data:{
/* 					  "amount":amount,
					  "wx_amount":wx_amount,
					  "ba_amount":ba_amount, */
					  "id":en_id}, 
					beforeSend:function(){
						/*  $(".loading_box ,.disalog_bg3").show(); 	 */
					},
					success:function (result) {
						s_drer=false;
						$(".dsf_Jh_dfgf").removeClass("show");
						if(result.success){
							window.location.href=path+'/weixin/get_resource_detail?type=1&id='+en_id;
						}else {
     						if(result.data=='1'){
     							mui.toast("文章已过期，不能购买");
     						}else if(result.data=='2'){
     							mui.toast("已经支付过了，再次扫码看到内容");
     						}else if(result.data=='3'){
     							mui.toast("文章已删除，不能购买");
     						}else if(result.data=='4'){
     							mui.toast("扫码无效，请再次扫码");
     						}else if(result.data=='5'){
     							mui.toast("用户涉嫌违规，不能购买");
     						}else{
     							window.location.href=path+'/weixin/pay_fail';
     						}
						} 
					}
				});
       }else{
    	   if(data_type=="zfb"){
    		   window.location.href=path+'/weixin/pay_zfb?id='+en_id+"&type="+type;
    	   }else{
       	    //微信支付;微信+余额支付;
       		$.ajax({url: path+'/weixin/pay_weixin', 
        			data:{
   /*      				  "amount":amount,
        				  "wx_amount":wx_amount,
        				  "ba_amount":ba_amount, */
        				  "type":type,
        				  "id":en_id}, 
        				beforeSend:function(){
        					/*  $(".loading_box ,.disalog_bg3").show(); 	 */
        				},
        				success:function (result) {
        					s_drer=false;
        					$(".dsf_Jh_dfgf").removeClass("show");
        					if(result.success){
        						appId=result.data.appId;
        						timeStamp=result.data.timeStamp;
        						nonceStr=result.data.nonceStr;
        						packageStr=result.data.packageStr;
        						signType=result.data.signType;
        						paySign=result.data.paySign;
        						onBridgeReady(appId,timeStamp,nonceStr,packageStr,signType,paySign);
        					}else {
        						if(result.data=='1'){
        							mui.toast("文章已过期，不能购买");
        						}else if(result.data=='2'){
        							mui.toast("已经支付过了，再次扫码看到内容");
        						}else if(result.data=='3'){
        							mui.toast("文章已删除，不能购买");
        						}else if(result.data=='4'){
        							mui.toast("扫码无效，请再次扫码");
        						}else if(result.data=='5'){
        							mui.toast("用户涉嫌违规，不能购买");
        						}else{
        							window.location.href=path+'/weixin/pay_fail';
        						}
        					} 
        				}
        			});
    	   }
       }
	});
	
 function onBridgeReady(appId,timeStamp,nonceStr,packageStr,signType,paySign){
	    var id = $('#id').val();
	    var en_id = $('#en_id').val();
	    WeixinJSBridge.invoke(
	        'getBrandWCPayRequest', {
	           "appId"     : appId,     //公众号名称，由商户传入
	           "timeStamp" : timeStamp, //时间戳，自1970年以来的秒数
	           "nonceStr"  : nonceStr , //随机串
	           "package"   : packageStr,
	           "signType"  : signType,  //微信签名方式：
	           "paySign"   : paySign    //微信签名
	        },
	        function(res){
	        	  /* toast(JSON.stringify(res));   */
	            if(res.err_msg == "get_brand_wcpay_request:ok" ) {
	            	window.location.href=path+'/weixin/resource_pay_suc?id='+en_id;
	            }
	            if (res.err_msg == "get_brand_wcpay_request:cancel") { 
	            }  
	            if (res.err_msg == "get_brand_wcpay_request:fail") {
	            	window.location.href=path+'/weixin/pay_fail';
	            } 
	        }
	    );
	}