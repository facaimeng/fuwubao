<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'huifuBindcard.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body onload="submitForm();">
          跳转中请稍后...
   <form action="http://mertest.chinapnr.com/muser/publicRequests" method="post" id="registForm">
	   <input name="Version" type="hidden" value="${account.Version}"><br/>
	   <input name="CmdId" type="hidden" value="${account.CmdId}"><br/>
	   <input name="MerCustId" type="hidden" value="${account.MerCustId}"><br/>
	   <input name="UsrCustId" type="hidden" value="${account.UsrCustId}"><br/>
	   <input name="BgRetUrl" type="hidden" value="${account.BgRetUrl}"><br/>
	   <input name="MerPriv" type="hidden" value="${account.MerPriv}"><br/>
	   <input name="PageType" type="hidden" value="${account.PageType}"><br/>
	   <input name="ChkValue" type="hidden" value="${account.ChkValue}"><br/>
   </form>
   
   <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
   <script type="text/javascript">
	function submitForm(){
	  $("#registForm").submit();
	}
   </script>
</html>
