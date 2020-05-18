//在body前插入div
function suc_dialog(title,tourl,ifref){
	add_suc_msg_div();
    $("#msg_content").html("").html(title); 
	$("#suc_msg").removeClass().addClass('Basic_dvi_green');
	popup($("#suc_msg")); 
	$("#suc_msg").fadeIn(600); 
    setTimeout(function(){
	    $("#suc_msg").fadeOut(700,function(){
	        if(ifref){
	        	if(tourl != ""){
	        		window.location=tourl;
	        	}else{ 
	        		window.location.reload();
	        	}
	            
			}
		});
    },800);
}

function popup(popupName){
    var _scrollHeight = $(document).scrollTop(),//获取当前窗口距离页面顶部高度
    _windowHeight = $(window).height(),//获取当前窗口高度
    _windowWidth = $(window).width(),//获取当前窗口宽度
    _popupHeight = popupName.height(),//获取弹出层高度
    _popupWeight = popupName.width();//获取弹出层宽度
    _posiTop = (_windowHeight - _popupHeight)/2 + _scrollHeight-100;
    _posiLeft = (_windowWidth - _popupWeight)/2;
    popupName.css({"left": _posiLeft + "px","top":_posiTop + "px"});//设置position
}
function err_dialog(title){
	add_suc_msg_div();
    $("#msg_content").html("").html(title);  
    $("#suc_msg").removeClass().addClass('Basic_dvi_red');
    popup($("#suc_msg"));
    $("#suc_msg").fadeIn(600); 
    setTimeout(function(){
 		$("#suc_msg").fadeOut(700);   
 	}, 1800);
}
 

function war_dialog(){
	
}

function ajax_error_dialog(){
	add_suc_msg_div();
    $("#msg_content").html("").html("网络不畅，请重试! ");  
    popup($("#suc_msg")); 
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

function subSform(p) {  
	$("#p").val(p); 
	$("#sform").submit();
} 
