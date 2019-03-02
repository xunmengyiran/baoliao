var yanza = {
	mail : function(a) {
		var b = !1;
		return /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(a) && (b = !0), b
	},
	phone : function(a) {
		var b = !1;
		return a.match(/^13[0-9]{9}|15[0-9]{9}|17[0-9]{9}|18[0-9]{9}$/)
				&& 11 == a.length && (b = !0), b
	},
	car_t : function(a) {
		var b = !1;
		return

		

				/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/
						.test(a)
						&& (b = !0), b
	}
};
function ajax(url, data, call_back) {
	$.ajax({
		type : "post",
		url : url,
		data : data,
		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		dataType : 'json',
		success : function(data) {
			try {
				call_back(data)
			} catch (e) {

			}
		},
		error : function(res) {
			toast("璇锋眰澶辫触!")
		}
	});
}
function get_time(time_s) {
	var date = new Date(time_s);
	Y = date.getFullYear() + '-';
	M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1)
			: date.getMonth() + 1)
			+ '-';
	D = (date.getDate() < 10 ? '0' + (date.getDate())
			:date.getDate()) + ' ';
	h = (date.getHours() < 10 ? '0' + (date.getHours())
			:date.getHours()) + ':';
	m = (date.getMinutes() < 10 ? '0' + (date.getMinutes())
			:date.getMinutes()) + ':';
	s = (date.getSeconds() < 10 ? '0' + (date.getSeconds())
			:date.getSeconds());
	return Y + M + D + h + m + s; // 鍛€楹荤
}

function get_amount(amount,type) {
	var sd_rer=amount
	if(type=='1'){
		sd_rer="+"+amount
	}
	if(type=='2'){
		sd_rer="(娑堣垂)-"+amount
	}
	if(type=='3'){
		sd_rer="(閫€娆�)+"+amount
	}
	if(type=='4'){
		sd_rer="(閫€娆�)-"+amount
	}
	return sd_rer
}


function bao_y(tye,expire){
	var sd_rer=""
	if(expire==2){
		sd_rer="銆愬凡杩囨湡銆�"}
	if(tye==1){
		sd_rer="銆愭湁閫€娆俱€�"}
	if(tye==2){
		sd_rer="銆愬凡閫€娆俱€�"}
/*	if(tye==7){
		sd_rer="銆愬寘鍛ㄣ€�"
	}
	if(tye==30){
		sd_rer="銆愬寘鏈堛€�"
	}
	if(tye==90){
		sd_rer="銆愬寘瀛ｃ€�"
	}
	if(tye==1){
		sd_rer=""
	}*/
	return sd_rer
}

function yft_tel(tel){
	 var flag=true;
	 if(!tel.match(/^13[0-9]{9}|15[0-9]{9}|17[0-9]{9}|18[0-9]{9}$/)||tel.length!=11){  
		 flag=false;
	 }
	 return flag;
}

/**
 * 鏂囦欢涓婁紶鏍煎紡楠岃瘉
 * @param obj	鏂囦欢DOW瀵硅薄
 * @param array	楠岃瘉鏍煎紡鏁扮粍(鏍峰紡:['gif','png','jpg'])
 * @returns {Boolean}
 */
function checkFileType(obj,array){
	var filepath = $(obj).val();
	var extStart = filepath.lastIndexOf(".")+1;
	var ext=filepath.substring(extStart,filepath.length).toLowerCase();
	var bl = false;
	for(var i = 0 ; i < array.length ; i++){
		if(array[i] == ext){
			bl = true;
			break;
		}
	}
	return bl;
}

/**
 * 鏂囦欢涓婁紶澶у皬楠岃瘉
 * @param obj	鏂囦欢DOW瀵硅薄
 * @param size	鏂囦欢澶у皬锛堝崟浣峂B锛�
 */
function checkFileSize(obj,size){
	var bl = true;
	if(!$.support.leadingWhitespace){
		var img=new Image();
		img.src=filepath;
		if(img.fileSize > 0){
			if(img.fileSize > (size*1024)){
				bl = false;
			}
		}
	}else{
		var file_size = obj.files[0].size;
		var fileSize = file_size / 1024 / 1024;
		if(fileSize > size){
			bl = false;
		}
	}
	return bl;
}

/* 
 * 鏍规嵁Value鏍煎紡鍖栦负甯︽湁鎹㈣銆佺┖鏍兼牸寮忕殑HTML浠ｇ爜 
 * @param strValue {String} 闇€瑕佽浆鎹㈢殑鍊� 
 * @return  {String}杞崲鍚庣殑HTML浠ｇ爜 
 * @example   
 * getFormatCode("娴媆r\n\s璇�")  =>  鈥滄祴<br/> 璇曗€� 
 */  
var getFormatCode=function(strValue){  
    return strValue.replace(/\r\n/g, '<br/>').replace(/\n/g, '<br/>').replace(/\s/g, '&nbsp;');  
}  