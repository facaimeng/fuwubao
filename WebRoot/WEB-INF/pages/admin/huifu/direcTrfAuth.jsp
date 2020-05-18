<%@ page language="java" pageEncoding="UTF-8"%> 
<!DOCTYPE>
<html>
  <head>  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">     
  </head>

  <body onload="submitForm();">
    跳转中请稍后...
   <form action="http://mertest.chinapnr.com/muser/publicRequests" method="post" id="direcTrfAuthForm">
	   <input name="Version" type="hidden" value="${account.Version}"><br/>
	   <input name="CmdId" type="hidden" value="${account.CmdId}"><br/>
	   <input name="MerCustId" type="hidden" value="${account.MerCustId}"><br/>
	   <input name="UsrCustId" type="hidden" value="${account.UsrCustId}"><br/>
	   
	   <input name="InUsrCustId" type="hidden" value="${account.InUsrCustId}"><br/> 
	   <input name="AuthAmt" type="hidden" value="${account.AuthAmt}"><br/>   
	   <input name="ChkValue" type="hidden" value="${account.ChkValue}"><br/>
   </form>
   
   <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
   <script type="text/javascript">
	function submitForm(){
	  $("#direcTrfAuthForm").submit();
	}
	//onload="submitForm();
   </script>
  </body>
</html>
