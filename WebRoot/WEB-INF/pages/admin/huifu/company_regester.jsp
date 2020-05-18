<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">     
  </head>

  <body onload="submitForm();">
    跳转中请稍后...
   <form action="http://mertest.chinapnr.com/muser/publicRequests" method="post" id="registForm">
	   <input name="Version" type="hidden" value="${account.Version}"><br/>
	   <input name="CmdId" type="hidden" value="${account.CmdId}"><br/>
	   <input name="MerCustId" type="hidden" value="${account.MerCustId}"><br/>
	   <input name="BusiCode" type="hidden" value="${account.BusiCode}"><br/>
	   <input name="BgRetUrl" type="hidden" value="${account.BgRetUrl}"><br/>
	   <input name="MerPriv" type="hidden" value="${account.MerPriv}"><br/> 
	   <input name="GuarType" type="hidden" value="${account.GuarType}"><br/>  
	   <input name="UsrId" type="hidden" value="${account.UsrId}"><br/>   
	   <input name="ChkValue" type="hidden" value="${account.ChkValue}"><br/>
   </form>
   <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
   <script type="text/javascript">
	function submitForm(){
	  $("#registForm").submit();
	}
	//onload="submitForm();
   </script>
  </body>
</html>
