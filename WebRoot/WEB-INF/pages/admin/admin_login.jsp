<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理系统</title>
	<link href="/resources/style/sys/adminlogin.css" rel="stylesheet" />
	<style type="text/css">
	<!--
		html,body{background:#fbfbfb;}
	-->
	</style>
	<script type="text/javascript">
	<!--
	  if (window.self != window.top) {
	    window.top.location.href = "/account/fkadin/";
	  }
	//-->
	</script>
</head>   
<body id="login">
<div class="login-min">
			<div class="login-left">
				<div style=" border-right:1px solid #fff; padding:0px 0px 20px;">

                    <a href="#" style="font-size:0px;" class="img">
                        <img src="/resources/images/sys/login-logo.png" />
                    </a>
                    <p>乾以易知 坤以简能</p>
				</div>
			</div>
			<div class="login-right">	
				<h1 class="login-title">账号登录</h1>
				<div>
				<form id="jbForm">
					<p><label>用户名</label><input type="text" class="text" name="uname" /></p>
                    <i style="display:none">用户名错误</i>
					<p><label>密码</label><input type="password" class="text" name="pwd"  /></p>
                    <i style="display:none">用户名错误</i>  
					<p class="login-submit">
						<input type="button" name="Submit" value="登录" id="sub_logbutton"/>
						<input type="reset" name="reset" value="重置" />
					</p>
				</form>
				</div>

                <div id="suc_msg" class="Basic_dvi_red" style="display:none;">
                	<strong style="float:left;"></strong>
                    <p style="float:left;" id="msg_content"></p>
                    <div style="clear:both;"></div>
                </div>
                
			</div>
		<div class="clear"></div>
</div>

<div class="footer" style="margin-top:80px; display:block!important;">Powered by <b><a href="javascript:void;" target="_blank">CodeDonkey V2.0.2</a></b> ©2017</div>
<!--[if IE 6]>
<script src="../templates/met/images/js/IE6-png.js" type="text/javascript"></script>
<script type="text/javascript">DD_belatedPNG.fix('.bg,img');</script>
<![endif]-->
<script src="/resources/js/bigodd.dialog.js" type="text/javascript"></script>
<script src="/resources/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(window).load(function(){
    
    $("#sub_logbutton").on({ 
   
      click:function(){   
          $.ajax({
	          url:'/account/system/dologin/',
	          type:"POST",
	          dataType:"json",
	          data:$("#jbForm").serialize(),
	          success:function(result){   
	             if(result.code=='1'){ 
	             	suc_dialog("登录成功!","/system/home/",true);  
		         }else{
		             err_dialog(result.message); 
		         } 
	          }, 
	          error: function (XMLHttpRequest, textStatus, errorThrown) {
	             ajax_error_dialog();
	          }
          });
      }
    });
    
    $("#jbForm").keypress(function(e) {
		if (e.which == 13) {
			$("#sub_logbutton").click();
		}
	});
    
});
</script>
</body></html>
