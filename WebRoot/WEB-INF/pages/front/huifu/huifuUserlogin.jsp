<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><!-- 汇付登录跳转页面 --></head>
  
<body onload="submitForm();">
    跳转中请稍后...
   <form action="http://mertest.chinapnr.com/muser/publicRequests" method="post" id="registForm">
	   <input name="Version" type="hidden" value="${account.Version}"><br/>
	   <input name="CmdId" type="hidden" value="${account.CmdId}"><br/>
	   <input name="MerCustId" type="hidden" value="${account.MerCustId}"><br/>
	   <input name="UsrCustId" type="hidden" value="${account.UsrCustId}"><br/>
   </form>
   
   <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
   <script type="text/javascript">
	function submitForm(){
	  $("#registForm").submit();
	}
   </script>
</html>
