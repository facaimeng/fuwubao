<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><!-- 汇付充值跳转页面 --></head>
  <body onload="submitForm();">
      跳转中请稍后...
   <form action="http://mertest.chinapnr.com/muser/publicRequests" method="post" id="netSaveForm">
	   <input name="Version" type="hidden" value="${netSaveInfo.Version}"><br/>
	   <input name="CmdId" type="hidden" value="${netSaveInfo.CmdId}"><br/>
	   <input name="MerCustId" type="hidden" value="${netSaveInfo.MerCustId}"><br/>
	   
	   <input name="UsrCustId" type="hidden" value="${netSaveInfo.UsrCustId}"><br/>
	   <input name="OrdId" type="hidden" value="${netSaveInfo.OrdId}"><br/>
	   <input name="OrdDate" type="hidden" value="${netSaveInfo.OrdDate}"><br/>
	   <input name="TransAmt" type="hidden" value="${netSaveInfo.TransAmt}"><br/>
	   <input name="BgRetUrl" type="hidden" value="${netSaveInfo.BgRetUrl}"><br/>
	   <input name="RetUrl" type="hidden" value="${netSaveInfo.RetUrl}"><br/>
	   <input name="ChkValue" type="hidden" value="${netSaveInfo.ChkValue}"><br/>
	   <!-- 
	   <input type="button" value="提交" onclick="submitForm();">
	   -->
   </form>
   
   <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
   <script type="text/javascript">
	function submitForm(){
	    $("#netSaveForm").submit();
	}
   </script>
   <!-- 
    //
	//$(function() {
	//	if (window.history && window.history.pushState) {
	//		$(window).on('popstate', function () {
	//			window.history.pushState('forward', null, '#');
	//			window.history.forward(1);
　　	//		});
　　	//	}
　　	//	window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
　　	//	window.history.forward(1);
　　    //})
   -->
  </body>
</html>