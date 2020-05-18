//在body前插入div
function suc_dialog(title,tourl,ifref){
	add_suc_msg_div();
    $("#msg_content").html("").html(title); 
	$("#suc_msg").removeClass().addClass('Basic_dvi_green');
	$("#suc_msg").fadeIn(500);
	$("#suc_msg").animate({top:'17%'});
    setTimeout(function(){
	    $("#suc_msg").fadeOut(600,function(){
	        if(ifref){
	            window.location=tourl;
			}
		});
    },800);
}

function err_dialog(title){
	add_suc_msg_div();
    $("#msg_content").html("").html(title);  
    $("#suc_msg").removeClass().addClass('Basic_dvi_red');
    $("#suc_msg").fadeIn(500);
    setTimeout(function(){
 		$("#suc_msg").fadeOut(500);   
 	}, 1500);
}
 

function war_dialog(){
	
}

function ajax_error_dialog(){
	add_suc_msg_div();
    $("#msg_content").html("").html("网络不畅，请重试! ");  
	$("#suc_msg").fadeIn(500);
	$("#suc_msg").fadeOut(3500);
}
 

//定义id选择器
function Id(id){
	return document.getElementById(id);
}
function changeTitle(){
	var file = Id("file"); 
	if(file.value!=''){
		$("#fname").val(file.value); 
	}
}

//判断页面上是否存在显示弹出信息框的div，如果不存该div则在body里面动态生成一个这样的div
function add_suc_msg_div(){
	if($("#suc_msg").val()=='undefined' || $("#suc_msg").length==0){ 
		$(document.body).append("<div id=\"suc_msg\" class=\"Basic_dvi_red\" style=\"display:none;\"><strong style=\"float:left;\"></strong><p style=\"float:left;\" id=\"msg_content\"></p><div style=\"clear:both;\"></div></div>");
	}
}
function dateFtt(fmt,date)   
{ //author: meizz   
  var o = {   
    "M+" : date.getMonth()+1,                 //月份   
    "d+" : date.getDate(),                    //日   
    "h+" : date.getHours(),                   //小时   
    "m+" : date.getMinutes(),                 //分   
    "s+" : date.getSeconds(),                 //秒   
    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
    "S"  : date.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} 
