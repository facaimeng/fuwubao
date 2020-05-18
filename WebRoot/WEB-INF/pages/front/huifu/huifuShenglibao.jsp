<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><!-- 汇付充值跳转页面   --></head>
  
  <body onload="submitForm();">
           跳转中请稍后...
   <form action="http://mertest.chinapnr.com/muser/publicRequests" method="post" id="cashOutForm">
	   <input name="Version" type="hidden" value="${fssTrans.Version}"><br/>
	   <input name="CmdId" type="hidden" value="${fssTrans.CmdId}"><br/>
	   <input name="MerCustId" type="hidden" value="${fssTrans.MerCustId}"><br/>
	   
	   <input name="UsrCustId" type="hidden" value="${fssTrans.UsrCustId}"><br/>
	   <input name="OrdId" type="hidden" value="${fssTrans.OrdId}"><br/>
	   <input name="OrdDate" type="hidden" value="${fssTrans.OrdDate}"><br/>


	   <input name="BgRetUrl" type="hidden" value="${fssTrans.BgRetUrl}"><br/>
	   <input name="ChkValue" type="hidden" value="${fssTrans.ChkValue}"><br/>
	  
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