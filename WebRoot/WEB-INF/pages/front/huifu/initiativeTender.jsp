<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><!-- 汇付下单跳转页面  --></head>
  <body onload="submitForm();">
       跳转中请稍后...
   <form action="http://mertest.chinapnr.com/muser/publicRequests" method="post" id="registForm">
	   <input name="Version" type="hidden" value="${account.Version}"><br/>
	   <input name="CmdId" type="hidden" value="${account.CmdId}"><br/>
	   <input name="MerCustId" type="hidden" value="${account.MerCustId}"><br/>
	   
	   <input name="OrdId" type="hidden" value="${account.OrdId}"><br/>
	   <input name="OrdDate" type="hidden" value="${account.OrdDate}"><br/>
	   <input name="TransAmt" type="hidden" value="${account.TransAmt}"><br/>
	   <input name="UsrCustId" type="hidden" value="${account.UsrCustId}"><br/>
	   <input name="MaxTenderRate" type="hidden" value="${account.MaxTenderRate}"><br/>
	   
	   <input name="IsFreeze" type="hidden" value="${account.IsFreeze}"><br/> 
	   <input name="FreezeOrdId" type="hidden" value="${account.FreezeOrdId}"><br/> 
	   <input name="BgRetUrl" type="hidden" value="${account.BgRetUrl}"><br/> 
	   <input name="ChkValue" type="hidden" value="${account.ChkValue}"><br/>

	   <textarea rows="0" cols="0" name="BorrowerDetails" style="width:0px; height:0px;">${account.BorrowerDetails}</textarea>
   </form>
   
   <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
   <script type="text/javascript">
	function submitForm(){
	    $("#registForm").submit();
	}
   </script>
  </body>
</html>