<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册成功-开通托管账户</title>
<link type="text/css" href="/resources/style/web/main.css" rel="stylesheet" />  
</head> 
<body> 
<div class="top_two">
	<div class="top_two_1">
    	<div class="top_two_1_c">
        	<img src="/resources/images/web/top_lgo.png" width="422px" />
            <a href="/account/member/login/"></a>
        </div>
    </div>
    <div class="top_two_2">
    	<div class="top_two_2_c" style="background:none; border:none;">
        	
        </div>
        <jsp:include page="/WEB-INF/pages/front/member/include/login_footer.jsp"/>
    </div>
</div>

<div class="tanchuang">
	<div class="tanchuang_cot">
    	<h2><img src="/resources/images/web/gooood.png" width="80px" /></h2>
        <h3>开通托管账户，确保资金安全！</h3>
        <h4>注册成功，为保证资金安全，投资前开通汇付天下的资金托管账户，保障您账户的资金独立存放。</h4>
        <h5 id="openbtn"><a  href="javascript:void(0);">立即开通</a></h5>
    </div>
</div> 
<script src="/resources/js/layer/layer.js"></script>
<script>
var op = '${param.op}'; 
<c:if test="${!empty memberVO}">
 $(function () { 
		$.ajax({
		          url:'/account/member/check_open/',  
		          method:"GET", 
		          success:function(result){   
			          if(result.code=='1'){
			          	  layer.msg('您已成功开户，正在为您跳转...',{shade:0.5}, function(){
		  					   window.location.href='/member/invest/';
						  })   
			          }    
		          } 
		   }); 
});
$("#openbtn").on({
     click:function(){ 
		var iii = layer.msg('正在执行中，开户完成前请不要关闭此窗口...',{shade:0.5,time:0});
		window.setInterval(checkOpen, 10000);
		window.open("/account/member/doopen/");
     } 
}); 
function checkOpen() {
	$.ajax({
          url:'/account/member/check_open/',  
          method:"GET", 
          success:function(result){   
	          if(result.code=='1'){  
	          	  layer.msg('您已成功开户，正在为您跳转...',{shade:0.5}, function(){
  					   window.location.href='/member/invest/';
				  }); 
	          }    
          } 
   });  
} 
</c:if> 
</script>

</body> 
</html>
