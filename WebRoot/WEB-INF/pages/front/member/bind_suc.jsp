<%@ page language="java" pageEncoding="UTF-8"%>  
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
        <h3>恭喜你，绑定成功！</h3>
        <h4>将在<font id="404_time">5</font>后为您跳转到个人中心 </h4>
        <h5><a  id="openbtn" href="/member/center/" >立即跳转</a></h5>
    </div>
</div> 
<script src="/resources/js/layer/layer.js"></script>
<script type="text/javascript">
	function gid(id) { return document.getElementById ? document.getElementById(id) : null; }
	
	function timeDesc() {
		if (all <= 0) {
			self.location = "/";
		}
		var obj = gid("404_time");
		if (obj) obj.innerHTML = all + " ";
		all--;
	}
	var all = 4;
	if (all > 0) window.setInterval("timeDesc();", 1000);
</script>

</body> 
</html>
