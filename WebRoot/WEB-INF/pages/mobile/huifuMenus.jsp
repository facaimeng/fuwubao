<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'huifuMenus.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  
    <a href="/front/newAccount/">立即开户</a>
    <br/>
    
    <a href="/front/bindCard/?usrCustId=6000060008971786">立即绑卡</a>
    <br/>
    <a href="/front/queryCardInfo/?usrCustId=6000060008971786&cardId=6214668881234512396">queryCardInfo</a>
    <br/>
    
    <a href="/front/userLogin.do?usrCustId=6000060008971786">立即登录</a>
    <br/>

	<form action="/front/netSave/" method="POST">
		<input type="text" name="usrCustId" value="6000060008971786" /> <input
			type="text" name="transAmt" value="50000.03" /> <input type="submit"
			value="充值">
	</form>

	<form action="/front/startAuto/" method="get">
        <input type="text" name="usrCustId" value="6000060008971786" />
        <select name="tenderPlanType">
	        <option value="W">全额</option>
	        <option value="P">部分</option>
        </select><br/>
          可操作金额<input type="text" name="transAmt" value="1000.00" /> <input type="submit" value="开启自动投资">
    </form>
    
    <form action="/front/closeAuto/" method="get">
        <input type="text" name="usrCustId" value="6000060008448781" />
        <input type="submit" value="取消自动投资">
    </form>
    
    <a href="javascript:;" onclick="viewAutoStatus('6000060008971786');">查看当前自动投资状态</a><br />
    
    
    <a href="/front/autoTender/">自动投资</a><br />
    <a href="/front/initiativeTender/">主动投资</a>
    <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
	function viewAutoStatus(usrCustId){
	     $.ajax({
             type: "GET",
             url:'/front/autoStatus/',
             data:{"usrCustId":usrCustId,"i":Math.random()},
             success:function(result){
                alert(result.message,'info');
             },
             error: function (jqXHR, textStatus, errorThrown) {
                /*错误信息处理*/
		     }
         });
	}
    </script>
  </body>
  
</html>
