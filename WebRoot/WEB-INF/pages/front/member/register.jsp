<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>个人中心-登录</title>
<link type="text/css" href="/resources/style/web/main.css" rel="stylesheet" />  
</head> 
<body> 
<div class="top_two">
	<div class="top_two_1">
    	<div class="top_two_1_c">
        	<img src="/resources/images/web/top_lgo.png" width="422px" />
            <a href="/account/member/login/">登陆</a>
        </div>
    </div>
    <div class="top_two_2">
    	<div class="top_two_2_c">
        	<div>
            	<h2>
                	<div id="n0" data-i="0" style="margin-left:60px; margin-right:100px;" class="nav ">
                		<a href="javascript:void(0);">
	                    	<i class="top_tow_i_1"></i>
	                        <b>手机注册</b>
	                        <span class="active"></span>
	                        <div style="clear:both;"></div>
                        </a>
                    </div>
                    <div id="n1" data-i="1" style="padding-left:40px;" class="nav">
                    	<a href="javascript:void(0);">
	                   		<i class="top_tow_i_2"></i>
	                        <b>老用户绑定</b>
	                        <span></span>
	                        <div style="clear:both;"></div>
                        </a>
                    </div>
                    <div style="clear:both;"></div>
                </h2> 
                <form id="sform" >
                <ul id="d0" class="top_two_2_cul">
                	<li>
                    	<div class="top_two_lil">
                            <b>手机号：</b>
                            <input id="phone" name="phone" placeholder="请输入手机号码"  type="text" style=" width:240px;" />
                            <div style="clear:both;"></div>
                        </div>
                        <div id="msg_div_phone" class="top_two_lir" style="width:340px;;display: none;" >
                        	<span style="width:340px; height:60px;"><img src="/resources/images/web/gantan.png" class="top_two_lirimg1" /><img src="/resources/images/web/topjt.png" class="top_two_lirimg2" /><em id="msg_phone"></em></span>
                        </div>
                    </li>
                    <li>
                    	<div class="top_two_lil">
                            <b>验证码：</b>
                            <input id="vcode" name="vcode" placeholder="4位验证码"  type="text" style=" width:80px;"  maxlength="4"/>
                            <div style="clear:both;"></div>
                        </div>
                        <a href="javascript:void(0);" class="top_two_lia"><img src="/patchca.png?p=regPat&w=284&h=70" width="230px" alt="点击图片刷新验证码" onclick="this.src=this.src+'&'+Math.random();"/></a>
                        <div id="msg_div_vcode" class="top_two_lir" style="width:250px;display: none;">
                        	<span style="width:250px; margin-left:20px;"><img src="/resources/images/web/gantan.png" class="top_two_lirimg1" /><img src="/resources/images/web/topjt.png" class="top_two_lirimg2" /><em id="msg_vcode" ></em></span>
                        </div>
                    </li>
                    <li>
                    	<div class="top_two_lil">
                            <b>验证码：</b>
                            <input id="pcode" name="pcode" placeholder="4位验证码"  type="text" style="width:80px;"  maxlength="4"/>
                            <div style="clear:both;"></div>
                        </div>
                        <a id="code_link" href="javascript:void(0);" class="top_two_lia2" onclick="sendSms();">获取短信验证码</a>
                        <div id="msg_div_pcode" class="top_two_lir" style="width:340px;display: none;">
                        	<span style="width:340px; margin-left:20px;"><img src="/resources/images/web/gantan.png" class="top_two_lirimg1" /><img src="/resources/images/web/topjt.png" class="top_two_lirimg2" /><em id="msg_pcode" ></em></span>
                        </div>
                    </li>
                     <li id="li_idnum" style="display: none;">
                    	<div class="top_two_lil">
                            <b>身份证号：</b>
                            <input id="idnum" name="idnum" placeholder="8-20个大小写英文字母，符号或数字"  type="text" style=" width:240px;" onblur="checkIdnum(this.value);"/>
                            <div style="clear:both;"></div>
                        </div>
                        <div id="msg_div_idnum" class="top_two_lir" style="width:340px;display: none;">
                        	<span style="width:340px; height:60px;"><img src="/resources/images/web/gantan.png" class="top_two_lirimg1" /><img src="/resources/images/web/topjt.png" class="top_two_lirimg2" /><em id="msg_idnum"></em></span>
                        </div>
                    </li>
                    <li>
                    	<div class="top_two_lil">
                            <b>设置密码：</b>
                            <input id="pwd" name="pwd" placeholder="8-20个大小写英文字母，符号或数字"  type="password" style=" width:225px;" />
                            <div style="clear:both;"></div>
                        </div>
                        <div id="msg_div_pwd" class="top_two_lir" style="width:340px;display: none;">
                        	<span style="width:340px; height:60px;"><img src="/resources/images/web/gantan.png" class="top_two_lirimg1" /><img src="/resources/images/web/topjt.png" class="top_two_lirimg2" /><em id="msg_pwd"></em></span>
                        </div>
                    </li>
                    <li class="top_two_liemi">
                    	<i>危险</i>
                        <em id="i0">弱</em>
                        <em id="i1">中</em>
                        <em id="i2">高</em>
                        <i>安全</i>
                    </li>
                     <li>
                    	<div class="top_two_lil">
                            <b>确认密码：</b>
                            <input id="inpwd" name="inpwd" placeholder="8-20个大小写英文字母，符号或数字"  type="password" style=" width:225px;" />
                            <div style="clear:both;"></div>
                        </div>
                        <div id="msg_div_inpwd" class="top_two_lir" style="width:340px;display: none;">
                        	<span style="width:340px; height:60px;"><img src="/resources/images/web/gantan.png" class="top_two_lirimg1" /><img src="/resources/images/web/topjt.png" class="top_two_lirimg2" /><em id="msg_inpwd"></em></span>
                        </div>
                    </li>
                    <li id="li_incode">
                    	<div class="top_two_lil">
                            <b>推荐手机：</b>
                            <input id="inphone" name="inphone" placeholder="推荐人手机号码"  type="text" style=" width:225px;" />
                            <div style="clear:both;"></div>
                        </div>
                        <div id="msg_div_inphone" class="top_two_lir" style="width:340px;display: none;">
                        	<span style="width:340px; height:60px;"><img src="/resources/images/web/gantan.png" class="top_two_lirimg1" /><img src="/resources/images/web/topjt.png" class="top_two_lirimg2" /><em id="msg_inphone"></em></span>
                        </div>
                    </li> 
                    <li class="top_two_liradio">
                    	<input type="checkbox" id="isagree" name="isagree" checked="checked"/>
                        <h5>我已阅读并同意<a href="/content/sigle/member/id_19" target="_blank">《融银网络服务使用协议》</a></h5>
                        <div id="msg_div_isagree" class="top_two_lir" style="width:340px;margin-left:58px;display: none;">
                        	<span style="width:340px; height:60px;"><img src="/resources/images/web/gantan.png" class="top_two_lirimg1" /><img src="/resources/images/web/topjt.png" class="top_two_lirimg2" /><em id="msg_isagree"></em></span>
                        </div>
                    </li>
                    <li class="top_two_lizcc">
                    	<a href="javascript:void(0);" id="subbtn">注册</a>
                    </li>
                </ul>
                <input type="hidden" id="op"/>
                </form>
            </div>
        </div>
        <jsp:include page="/WEB-INF/pages/front/member/include/login_footer.jsp"/> 
    </div>
</div>   
<script src="/resources/js/layer/layer.js"></script>
<script type="text/javascript" src="/resources/js/validate.utils.js"></script>
<script type="text/javascript" src="/resources/js/validate.idcard.js"></script>  
<script type="text/javascript" src="/resources/js/mem/reg.js"></script> 
<script>
$(function(){ 
    $('#n0').addClass("top_two_on");
	$('#n0').find("span").addClass("active");
	$('.nav1').hover(function(){  
	    $(".nav").removeClass("top_two_on");
		$(".nav").find("span").removeClass("active");
		$(".nav").find("span").css('height','0px'); 
		$(this).find("span").stop().css('height','3px');
		//alert($('span',this).html());
		$(this).find("span").animate({
			left:'0',
			width:'100%',
			right:'0'
		},300);
	},function(){
		var i = $(this).attr("data-i");
		var cur;
		if(i=='0'){
			cur = '1';
		}else{
			cur = '0';
		}
		if(!$(this).find("span").hasClass("active")){
			$('span',this).stop().animate({
				left:'50%',
				width:'0'
			},300); 
			var cobj = $('#n'+cur);
			cobj.find("span").stop().css('height','3px');
			cobj.find("span").animate({
				left:'0',
				width:'100%',
				right:'0'
			},200,function(){
				$('#n'+cur).addClass("top_two_on");
		 		$('#n'+cur).find("span").addClass("active");
			});   
		}  
	});
	$('.nav').click(function(){ 
		 $(".nav").removeClass("top_two_on");
		 $(".nav").find("span").removeClass("active");
		 $(this).addClass("top_two_on");
		 $(this).find("span").addClass("active");
		 $('span',this).stop().css('height','3px')
		 var cur = $(this).attr("data-i");
		 if(cur=='0'){
		 	$("#li_incode").show();
		 	$('#li_idnum').hide(); 	
		 }else{
		 	$("#li_incode").hide();
		 	$('#li_idnum').show(); 
		 }
		 $('#op').val(cur); 
		 
	});
	
});  
var InterValObj;  
var count = 90;  
var curCount; 
var code = "";  
var codeLength = 4; 
var sendFlag = true;  
function sendSms(){ 
	curCount = count;  
	var phone = $("#phone").val();  
	var vcode = $("#vcode").val(); 
	if(validateRules.isNull(phone)){
		showMsg('phone','请输入手机号码！'); 
		return;	
	}
	if(!validateRules.isMobile(phone)){
		showMsg('phone','请输入正确的手机号码！'); 
		return;	
	}
	if(validateRules.isNull(vcode)){
		showMsg('vcode','请输入验证码！'); 
		return;		
	}
	if(vcode.length!=4){
		showMsg('vcode','请输入正确的验证码！'); 
		return;		
	} 
	if(sendFlag){ 
		// 设置button效果，开始计时 
		$('#code_link').removeAttr("onclick");
		$.ajax({
			type: "POST", 
			url: "/sms/reg/", 
			method : "POST",
			data: {"phone":phone,"vcode":vcode}, 
			error: function (XMLHttpRequest, textStatus, errorThrown) {
					//$("#code_link").removeClass("gray").addClass("blue");
			        $("#code_link").attr("onclick","sendSms()"); 
			},
			success: function (result){     
				if(result.code != '1'){
					window.clearInterval(InterValObj);  
					//$("#code_link").removeClass("gray").addClass("blue");
			        $("#code_link").attr("onclick","sendSms()"); 
			        $("#code_link").html("").html("发送短信验证码"); 
			        if(result.code=='-1'){
			        	showMsg('vcode',result.message);
			        }else if(result.code=='-2'){
			        	showMsg('phone',result.message);
			        }else{
			        	showMsg('pcode',result.message);
			        } 
				}else{ 
					$("#code_link").html('').html(curCount + "秒后重新发送"); 
					InterValObj = window.setInterval(SetRemainTime, 1000);    
					//$("#code_link").removeClass("blue").addClass("gray");
				} 
			}
		}); 
	}
	
}  
function SetRemainTime() {
	if (curCount == 0) {                
		window.clearInterval(InterValObj); 
		$("#code_link").attr("onclick","sendSms()"); 
		$("#code_link").html("重新发送验证码");
		code = ""; 
	}else {
		curCount--;
        $("#code_link").html('').html(curCount + "秒后重新发送");
	}
}
 
</script>
</body> 
</html>
