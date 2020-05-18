<%@ page language="java" pageEncoding="UTF-8"%> 
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>登录</title>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"> 
<link rel="stylesheet" href="/resources/style/style.css">
</head>
<body>
<div class="wrap">
 <form id="sbform" action="/weixin/account/login/check_phone/" method="POST">
  <div class="content">
    <div class="formlist">
      <ul>
        <li class="tel-icon">
          <input id="phone" name="phone" type="text" placeholder="请输入手机号码" class="inp" />
        </li>
      </ul>
    </div>
    <div class="user-agreement"><img id="ckbox" data-sel="Y" src="/resources/images/m/selet-2.png" >本人已阅读并同意 <a href="#" class="blue">《用户协议》</a></div>
    <div class="btn-b">
      <input id="subbtn" type="button" class="btn" value="下一步">
    </div>
    <div class="red login-tip" align="center"> * 融银不会在任何地方泄露您的号码</div> 
  </div>
  </form>
</div>
<div class="footer">希望回报并非平台承诺收益，市场有风险，投资需谨慎</div>
<script src="/resources/js/rem.js"></script>
<script  src="/resources/js/jquery-1.11.0.min.js"></script> 
<script type="text/javascript" src="/resources/js/layer/mobile/layer.js"></script> 
<script  src="/resources/js/validate.utils.js"></script> 
<script>
$("#subbtn").on({
	click:function(){
		var phone = $("#phone").val();
		if(validateRules.isNull(phone)|| !validateRules.isMobile(phone)){
			showMsg('请输入正确的手机号码'); 
			return;
		} 
		$("#sbform").submit();
	}
}); 
$(".user-agreement").on({
	click:function(){
		var sel = $("#ckbox").attr("data-sel");
		if(sel == 'Y'){
			$("#ckbox").attr("data-sel","N");
			$("#ckbox").attr("src","/resources/images/m/selet-1.png");
		}else{
			$("#ckbox").attr("data-sel","Y");
			$("#ckbox").attr("src","/resources/images/m/selet-2.png");
		}
		
	}
});  
</script> 
</body>
</html>