<%@ page language="java" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>会员中心-登录</title>
<link type="text/css" href="/resources/style/web/main.css" rel="stylesheet" />
</head> 
<body> 
<div class="top_two">
	<div class="top_two_1">
    	<div class="top_two_1_c">
        	<img src="/resources/images/web/top_lgo.png" width="422px" />
            <a href="/account/member/register/">注册</a>
        </div>
    </div>
    <div class="top_two_2" style="min-height:900px;">
    	<div class="top_two_2_cc">
        	<div class="top_two_2_cc_l"><img src="/resources/images/web/rycf.png" width="465px" /></div>	
            <div class="top_two_2_cc_r">
            	<h2>
                    <b>会员中心登录</b>
                    <img src="/resources/images/web/dada255.png" class="top_two_2_c_img23" />
                </h2>
                <ul class="top_two_2_cc_r_zhmm">
                	<li>
                    	<input id="uname" name="uname" placeholder="融银注册手机号"  type="text" style=" width:206px;" />
                        <img src="/resources/images/web/zhangh.png" width="35px" />
                        <div style="clear:both;"></div>
                    </li>
                    <li>
                    	<input id="pwd" name="pwd"  placeholder="登录密码"  type="password" style=" width:206px;" />
                        <img src="/resources/images/web/mima.png" width="35px" />
                        <div style="clear:both;"></div>
                    </li>
                    <li style="border:none;" class="top_two_2_cc_r_zhmma"><a href="javascript:void(0);">忘记密码</a></li>
                    <li style="border:none;" class="top_two_2_cc_r_zhmmaqd"><a class="subbtn" href="javascript:void(0);">登录</a></li>
                    <li style="border:none;" ><h3>如需帮助，请拨打客服热线    <b>400-666-9606</b></h3></li>
                </ul> 
            </div>
            
        </div>
        <jsp:include page="/WEB-INF/pages/front/member/include/login_footer.jsp"/>
    </div>
</div> 

<script type="text/javascript" src="/resources/js/validate.utils.js"></script>
<script src="/resources/js/layer/layer.js"></script>
<script>
$("#uname").on({
     blur:function(){ 
	     
	 }  
});
$(".subbtn").on({
	click:function(){ 
		var uname = $("#uname").val();
	    if(validateRules.isNull(uname)||!validateRules.isMobile(uname)){
		     layer.msg('请输入正确的手机号码！');   
		     return;
	    }
	    var pwd = $("#pwd").val();
	    if(validateRules.isNull(pwd)){
		     layer.msg('请输入登录密码！');   
		     return;
	    } 
	    var iii = layer.msg('正在执行中，请稍等片刻...',{shade:0.5,time:0}); 
	    $.ajax({
            url:'/account/member/dologin/',
            data:{'uname':uname,'pwd':pwd}, 
            method:"POST", 
            success:function(result){  
		         if(result.code=='0'){  
		         	  layer.msg('登录信息有误，请重试！');   
		         }else if(result.code=='-1'){ 
		         	  layer.msg('您的账户已被锁定！');  
		         }else if(result.code=='-2'){
		         	  window.location.href='/account/member/open/';
		         }else if(result.code=='1'){
		         	  window.location.href='/member/invest/';
		         }    
              }, 
              error: function (XMLHttpRequest, textStatus, errorThrown) { 
				layer.msg('登录信息有误，请重试！');  
			 }
        }); 
	}
});

</script> 
</body> 
</html>
