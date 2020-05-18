<%@ page language="java" pageEncoding="UTF-8"%>  
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>验证码</title>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<link rel="stylesheet" href="/resources/style/style.css">
</head>
<body>
<div class="wrap">
<form id="sbform" action="/weixin/account/register/infor/" method="POST">
  <div class="content">
    <div class="formlist">
      <ul>
        <li class="vcode-icon">
          <div class="fl">
          	<input id="vcode" name="vcode" type="text" placeholder="请输入图片验证码" class="inp-s"/>
          </div>
          <div class="code">
          	<img src="/patchca.png?p=regPat&w=120&h=60" alt="点击图片刷新验证码" onclick="this.src=this.src+'&'+Math.random();" width="120" height="60"/>
          </div>
        </li>
      </ul>
    </div>
    <div class="btn-b">
      <input id="subbtn" type="button" class="btn" value="下一步">
      <input id="openid" name="openid" type="hidden" value="${param.openid}"/>
      <input id="phone" name="phone" type="hidden" value="${param.phone}"/>
      <input type="hidden" id="result" value="${result}">
    </div> 
  </div>
  </form>
</div>
<!--底部-->
<div class="footer">希望回报并非平台承诺收益，市场有风险，投资需谨慎</div>
<script src="/resources/js/rem.js"></script>
<script  src="/resources/js/jquery-1.11.0.min.js"></script> 
<script type="text/javascript" src="/resources/js/layer/mobile/layer.js"></script> 
<script  src="/resources/js/validate.utils.js"></script> 
<script >
$(function() {  
	showRs();
});
$("#subbtn").on({
	click:function(){ 
		var vcode = $("#vcode").val(); 
		if(!validateRules.isNull(vcode)){ 
			$("#sbform").submit();
		}else{
			showMsg('请输入正确的验证码！'); 
		}
	}
});  
function showRs(){
   var rs = $("#result").val(); 
   if(rs=='0'){
      showMsg('请输入正确的验证码！');         
   }
}
</script> 
</body>
</html>