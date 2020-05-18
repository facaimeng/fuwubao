<%@ page language="java" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>个人中心-我的资产-账户总览</title> 
<link rel="stylesheet" type="text/css" href="/resources/style/mem/center.css">
<link rel="stylesheet" type="text/css" href="/resources/style/mem/center_reset.css">  
</head>
<body class="iCenter assetAll assetAllNo mgt30"> 
<jsp:include page="/WEB-INF/pages/front/member/include/center_header.jsp"/>   
<div class="wrap">
	<div class="mg-auto overflow area clearfix">
    	<!-- middle_menu -->
    	

<div class="listNav mgb20">
     <ul class="clearfix nav bd">
         <li id="persistNav" class="current"><a href="/member/account/overview/">我的账户</a></li> 
         <li id="dealNav"><a href="/member/asset/orders/">我的资产</a></li>
         <li id="accountNav"><a href="/member/found/logs/">我的资金</a></li>
         <li class="bg" style="width: 88px; left: 0px;"></li>
     </ul>
 </div>
 
        <div class="clearfix personCon mgb100">
        	<!--页面左侧-->
        	
		<div class="investLeft fl">
		 	<ul class="leftNavList">
		     	<li id="assetAllNav" class="mgl0 "><a href="/member/account/overview/" ><i class="leftNavList_1"></i>账户总览</a></li>
		        <li id="assetAllNav" class="mgl0 "><a href="/member/account/security/" ><i class="leftNavList_2"></i>安全认证</a></li>
		        <li id="assetAllNav" class="mgl0 mgl0yes"><a href="/member/account/invite/" class="cur"><i class="leftNavList_3"></i>推荐好友</a></li>
		     </ul>
		 </div>
            <!--页面右侧-->
         
             <div class="investRight fl"><!--
            	<h2>奖励规则</h2>
                <div class="investCon grzx_tjhy"> 
                	<div class="grzx_tjhy_1">
                    	<p>(1)活动活动活动活动活动活动活动活动活动</p>
                        <p>(1)活动活动活动活动活动活动活动活动活动</p>
                        <p>(1)活动活动活动活动活动活动活动活动活动</p>
                    </div>
                    <div class="grzx_tjhy_2">
                    	<p>试列说明</p>
                        <table  cellpadding="0" cellspacing="0">
                        	<tr>
                            	<th>投资额度</th><th>投资额度</th><th>投资额度</th><th>投资额度</th>
                            </tr>
                            <tr>
                            	<td>投资额度</td><td>投资额度</td><td>投资额度</td><td>投资额度</td>
                            </tr>
                        </table>
                    </div> 
                </div>
                 <h2 style="margin-top:30px;">推荐详情</h2>  -->
                <h2 >推荐详情</h2>
                <div class="investCon grzx_gmjl">
                	<!--环形图区块-->
                   <table  cellpadding="0" cellspacing="0" style="border:none;">
                   		<tr>
                        	<th>推荐用户</th>
                        	<th>状态</th>
                        	<th>注册时间</th>
                        </tr><c:forEach items="${page.list}" var="item">
                        <tr>
                        	<td>${item.phoneLable}</td>
                        	<td><c:if test="${!empty item.usrcustid}">已开户</c:if><c:if test="${empty item.usrcustid}">未开户</c:if> </td>
                        	<td><fmt:formatDate value="${item.regtime}" pattern="yyyy/MM/dd"/></td>
                        </tr></c:forEach>
                   </table>
                	<!--条状图区块-->
                </div>
            </div> 
        </div>
      </div>
</div>

<!--页脚-->
<div class="shadow2 hidden"></div>
<jsp:include page="/WEB-INF/pages/front/member/include/footer.jsp"/>  
</body>
</html>