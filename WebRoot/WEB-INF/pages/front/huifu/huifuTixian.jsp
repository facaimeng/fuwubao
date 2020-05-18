<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><!-- 汇付充值跳转页面   --></head>
  
  <body onload="submitForm();">
           跳转中请稍后...
   <form action="http://mertest.chinapnr.com/muser/publicRequests" method="post" id="cashOutForm">
	   <input name="Version" type="hidden" value="${cashInfo.Version}"><br/>
	   <input name="CmdId" type="hidden" value="${cashInfo.CmdId}"><br/>
	   <input name="MerCustId" type="hidden" value="${cashInfo.MerCustId}"><br/>
	   

	   <input name="OrdId" type="hidden" value="${cashInfo.OrdId}"><br/>
	   <input name="UsrCustId" type="hidden" value="${cashInfo.UsrCustId}"><br/>
	   <input name="TransAmt" type="hidden" value="${cashInfo.TransAmt}"><br/>
	   
	   <input name="ReqExt" type="hidden" value="${cashInfo.ReqExt}"><br/>

	   <input name="BgRetUrl" type="hidden" value="${cashInfo.BgRetUrl}"><br/>
	   <input name="ChkValue" type="hidden" value="${cashInfo.ChkValue}"><br/>
	   <!-- 
	   <input type="button" value="提交" onclick="submitForm();" >
	   -->
   </form>
   
   <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
   <script type="text/javascript">
	function submitForm(){
	    $("#cashOutForm").submit();
	}
   </script>
  </body>
</html>