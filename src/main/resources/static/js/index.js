var path = "/lmjl_core";
var basePath = "https://lmjl.ttkgou.com/lmjl_core/";
var img_url = "https://lm-img.oss-cn-shenzhen.aliyuncs.com/res/";

$(function () {

    $(".mui-table-view li").on("tap", function () {
        $('input').blur()
        $('textarea').blur()
    })

    var dtPicker = new mui.DtPicker({
        "beginYear": "2018" //开始时间
    });

    //选择时间
    $('body').on("tap", ".time", function () {
        var hgp = $(this)
        dtPicker.show(function (rs) {
            $(hgp).val(rs.text)
        })

    })

    //清空
    $(".ds_dd_deert").on("tap", function () {
        $(".time").val('')
    })

    //勾选
    var is_chssd = false;//是否勾选退款
    $(".df_rt_errtyxc").on("tap", function () {
        $(this).find(".sd_jh_dert").toggleClass("act")
        is_chssd = $(this).find(".sd_jh_dert").hasClass("act")
        console.log(is_chssd);
    })


    //改变模版页的高度
    $(".cz_mb").height(document.documentElement.clientHeight);
    //选择金额
    $(".sjh_de_det").on("click", function () {
        $(".sjh_de_det").removeClass("act")
        var sd_err = $(this).text().trim()
        if (sd_err == "免费") {
            sd_err = 0
        } else {
            sd_err = sd_err.split("元")[0]
        }
        $("#price").val(sd_err)
        $(this).addClass("act")
    })

    //选择时间段
    $(".dsf_jhg_der").on("click", function () {
        $(".dsf_jhg_der").removeClass("act")
        $(this).addClass("act")
        $(this).find("input").focus()
    })

    //详细说明
    $(".sd_jh_dertx_mb").on("click", function () {
        $(".dsf_dertyx").toggleClass("show")
    })

    //点击文字
    $(".fd_jh_dertt.aa").on("click", function () {
        $(this).hide()
        $(".dsfs_jh_derrt.aa").addClass("show")
    })

    //点击文字框叉
    $(".dsfs_jh_derrt.aa .closd_dert ").on("click", function () {
        $(this).parent().removeClass("show")
        $(".fd_jh_dertt.aa").show()
    })

    //点击图片
    $(".fd_jh_dertt.ab").on("click", function () {
        $("#ssd_ooie").click()
        $(this).removeClass("show")

    })


    $("body").on("touchend", ".sder_jh_dert", function () {
        $(this).parents(".df_jhh_deert").remove()
        console.log($(".sder_jh_dert").length);
        setTimeout(function () {
            if ($(".sder_jh_dert").length <= 0) {
                $(".dsfs_jh_derrt.ab").hide()
            }
        }, 200)
        /* if ($(".sder_jh_dert").length <= 0) {
              $(".dsfs_jh_derrt.ab").hide()
        } */
    })

    //生产料码
    var s_drer = false
    $(".sclma_ser").on("click", function () {
        if (s_drer) {
            return
        }
        ;
        var title = $('#title').val();//标题
        var moban = $(".sd_jh_dertx_mb").attr("data-type");//模版
        var color_s = "#" + moban.split("_")[0];
        var color_x = "#" + moban.split("_")[1];
        //var content = $('#content').val();//内容
        var code = $('#code').val();//openId
        var strContent = document.getElementById("content").value;
        //alert("处理前的strContent为\r\n"+strContent); 
        var content = getFormatCode(strContent);//内容
        var str_introduce = document.getElementById("introduce").value;
        //alert("处理前的strContent为\r\n"+strContent); 
        var introduce = getFormatCode(str_introduce);//内容
        //alert("转换之后的html代码为\r\n"+content); 
        var price = $('#price').val();//价格
        // var img = []//图片
        var img = new Array();
        $(".sd_jh_dertx").map(function (a) {
            var src_dr = $(this).attr("data-src")
            src_dr = src_dr.split(img_url)[1]
            img.push(src_dr)
        })
        img = img.join(",");
        if (title == "") {
            mui.toast('请输入标题');
            return;
        }
        /*         if(title.length>24){
                    mui.alert('标题不能超过24个汉字');
                    return ;} */
        if (content == "" && img == "") {
            mui.toast('请输入内容或图片');
            return;
        }
        if (price == "") {
            price = '0';
        }
        var reg = /^(([1-9]\d{0,9}|0))(\.\d{1,2})?$/;
        var r = price.match(reg);
        if (r == null || r == "") {
            mui.toast("请输入正确的价格");
            return;
        }
        if (price > 3000) {
            mui.alert('价格最多3000，谢谢！');
            return
        }
        var time_cycle = 1;
        var expire_time_str = $(".time").val();//过期时间
        var is_refund = 0;
        if (is_chssd) {
            is_refund = 1;
        }//是否退款
        if (is_refund == 1 && price == '0') {
            mui.alert('选择有退款，价格不能为0');
            return;
        }
        /*         var s_tye = $(".dsf_jhg_der.act").index() //获取包时段  0包周  1包月 2包天
                if (s_tye ==0) {
                    time_cycle=1; }
                if (s_tye ==1) {
                    time_cycle=7;}
                if (s_tye ==2) {
                    time_cycle=30;}
                if (s_tye == 3) {
                    var sd_drrt = $(".sd_erjh_dert").val()
                    if (!sd_drrt) {
                         mui.alert('请填写包的时段天数');
                        return  }
                    if (sd_drrt >90) {
                         mui.alert('最多包90天');
                        return }
                    if (sd_drrt <1) {
                           mui.alert('最少包1天');
                       return }
                    time_cycle=sd_drrt; } */
        $(".dsf_Jh_dfgf").addClass("show");
        s_drer = true;
        var data = {
            'title': title,
            'content': content,
            'price': price,
            'introduct': introduce,
            'expritationDate': expire_time_str,
            'isRefund': is_refund,
            'code': code,
            // 'imgArr': JSON.stringify(img)
            'imgArr': img
        };
        $.ajax({
            type: 'POST',
            url: '/product/save',
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                var str = eval('(' + data + ')');
                $(".dsf_Jh_dfgf").removeClass("show");
                s_drer = false;
                if (str.result == 1) {
                    window.location.href = basePath + "weixin/get_resource_info?type=1&id=" + result.data;
                } else {
                    mui.alert(result.msg)
                }
            },
            error: function () {
                s_drer = false;
                $(".dsf_Jh_dfgf").removeClass("show");
                mui.alert("网络错误!")
            }
        });
    })
})

//上传图片base64
var s_img = false

function fileChange(that) {
    if (s_img) {
        return
    }
    var files = that.files;
    if (files.length < 1) {
        return
    }
    for (var i = 0; i < files.length; i++) {
        var filepath = files[i].name;
        if (filepath == "") {
            return;
        }
        var extStart = filepath.lastIndexOf(".");
        var txt = filepath.substring(extStart, filepath.length);
        var ext = filepath.substring(extStart, filepath.length).toUpperCase();
        if (".jpg|.png|.bmp|.jpeg".toUpperCase().indexOf(ext.toUpperCase()) == -1) {
            mui.alert("只允许上传jpg、png、bmp、jpeg格式的图片");
            return false;
        }
    }
    $(".dsf_Jh_dfgf").addClass("show")
    s_img = true;
    var length = files.length;
    var res = 0;
    for (var i = 0; i < files.length; i++) {
        //以图片宽度为600进行压缩
        lrz(files[i], {
            width: 600
        })
            .then(function (rst) {
                //压缩后上传
                $.ajax({
                    url: '/product/img_upload_base64',
                    type: "POST",
                    data: {
                        txt: txt,
                        imgdata: rst.base64//压缩后的base值
                        // imgdata:'111222333'//压缩后的base值
                    },

                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        res++;
                        if (data.success) {
                            /*   alert(data.data);  */
                            $(".dsfs_jh_derrt.ab").show()
                            console.log("===>>>" + rst.base64)
                            $(".dsf_jh_dr_reet").before('<section class="mui-col-xs-4 pr10 df_jhh_deert"> <section class="sd_jhh_s yj4 br cen pr"> <img src="' + rst.base64 + '" data-src="' + img_url + data.data + '" class="sd_jh_dertx"> <i class="dx sd_hjerer icon-guanbi  sder_jh_dert fz24 " style="color:#e0e0e0"></i> </section> </section>')
                            if (res == length) {
                                $(".dsf_Jh_dfgf").removeClass("show");
                                s_img = false;
                            }
                        }
                    },
                    error: function () {
                        res++;
                        /*  mui.alert("服务中断或连接超时导致通信失败！"); */
                        if (res == length) {
                            $(".dsf_Jh_dfgf").removeClass("show");
                            s_img = false;
                        }
                    }
                });
            });
    }
}

//上传图片
/*  var s_img=false
function previewFile(obj) {
	if(s_img){
            return }
	var array = [ 'jpg', 'png', 'gif', 'jpeg' ];
	if (!checkFileType(obj, array)) {
		mui.alert("只支持上传jpg,png,gif,jpeg格式！");
		return;
	} else if (!checkFileSize(obj, 2)) {
		mui.alert("图片大小不能超过2MB！");
		return;
	}
	$(".dsf_Jh_dfgf").addClass("show")
	 s_img=true;
  	 $.ajaxFileUpload({
   		 url:path+'/weixin/img_upload',
         secureuri:true,
         fileElementId:'ssd_ooie',
         dataType:'json',
         success: function (data,status){
        	 $(".dsf_Jh_dfgf").removeClass("show");
        	 s_img=false;
             if(data.success){ */
/*   alert(data.data);  */
/*        	        $(".dsfs_jh_derrt.ab").show()
       	        $(".dsf_jh_dr_reet").before('<section class="mui-col-xs-4 pr10 df_jhh_deert"> <section class="sd_jhh_s yj4 br cen pr"> <img src="' +img_url+data.data + '" class="sd_jh_dertx"> <i class="dx sd_hjerer icon-guanbi  sder_jh_dert fz24 " style="color:#e0e0e0"></i> </section> </section>')
             }else{
            	 mui.alert("文件上传过程中出错!请重试!");
             }
         },
         error: function (data,status,e){
        	 $(".dsf_Jh_dfgf").removeClass("show");
        	 s_img=false;
        	 mui.alert("服务中断或连接超时导致通信失败！");
         }
   	});
} */
$(".dsf_jhh_xdf .mui-col-xs-6").on("click", function () {
    $(".dsf_jhh_xdf .mui-col-xs-6").removeClass("act")
    $(this).addClass("act")
    //console.log($(this).attr("data-type"));
    var data_type = $(this).attr("data-type");
    $(".sd_jh_dertx_mb").attr("src", basePath + "img/" + data_type + ".png");
    $(".sd_jh_dertx_mb").attr("data-type", data_type);
    $(".dsf_dertyx").toggleClass("show")
})