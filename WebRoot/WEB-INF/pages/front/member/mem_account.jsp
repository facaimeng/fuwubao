<%@ page language="java" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>个人中心-我的资产-账户总览</title> 
<link rel="stylesheet" type="text/css" href="/resources/style/mem/center.css">
<link rel="stylesheet" type="text/css" href="/resources/style/mem/center_reset.css">  
<style>
.center_account_detail {
    width: 100%;
    height: 386px;   
}

.center_account_detail_item {
    width: 230px;
    height: 80px;
    border-right: 1px solid rgb(229,229,229);
    margin-top: 75px;
    color: rgb(102,102,102);
    text-align: center;
    float: left;
}

.center_account_detail_item:nth-child(3n+3) {
    border-right: 0;
}

.center_account_detail_item .item_title {
    line-height: 35px;
    font-size: 16px;
}

.center_account_detail_item .item_ammount {
    line-height: 35px;
    font-size: 24px;
    color:#ec5801;
}

.icon-ask {
    display: inline-block;
    width: 20px;
    height: 18px;
    background-image: url(/resources/images/mem/helpIcon.png);
    float: right;
    margin-top: -26px;
    margin-right: 20px;
}

.icon-ask-div {
	font-size:12px;
    display: none;
    border: 1px solid #333;
    text-align: left;
    line-height: 175%;
    text-indent: 2em;
    position: absolute;
    color: #db7c22;
    line-height: 1.5;
    zoom: 1;
    background-color: #fffcef;
    border: 1px solid #ffbb76;
    border-radius: 2px;
    padding: 5px 22px 5px 10px;
    margin-top: -100px;
}

.icon-ask-1 {
    margin-top: -100px;
    margin-left: 40px;
}

.icon-ask-2 {
    margin-top: -100px;
    margin-left: 20px;
}

.icon-ask-3 {
    margin-top: -100px;
    margin-left: 20px;
}

.icon-ask-4 {
    margin-top: -100px;
    margin-left: 20px;
}

.icon-ask-5 {
    margin-top: -100px;
    margin-left: 20px;
}

.icon-ask-6 {
	width:320px;
    margin-top: -100px;
    margin-left: 0px;
}

.icon-ask-7 {
    margin-left: 200px;
    margin-top: -70px
}
</style>
</head>
<body class="iCenter assetAll assetAllNo mgt30"> 
<jsp:include page="/WEB-INF/pages/front/member/include/center_header.jsp"/>   
<div class="wrap">
	<div class="mg-auto overflow area clearfix"> 
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
		     	<li id="assetAllNav" class="mgl0 mgl0yes"><a href="/member/account/overview/" class="cur"><i class="leftNavList_1"></i>账户总览</a></li>
		        <li id="assetAllNav" class="mgl0"><a href="/member/account/security/" ><i class="leftNavList_2"></i>安全认证</a></li>
		        <li id="assetAllNav" class="mgl0"><a href="/member/account/invite/" ><i class="leftNavList_3"></i>推荐好友</a></li>
		     </ul>
		 </div>
            <!--页面右侧-->
            <div class="investRight fl">
            	<h2>我的资产</h2>
            	<div class="center_account_detail investCon">
                            <div class="center_account_detail_item">
                                <div class="item_title">可用余额</div>
								<i class="icon-ask fn-right j-atip" data-content="当前可投资金额。" onmouseover="display(1)" onmouseout="disappear(1)"></i>
                                <div class="item_ammount">¥<fmt:formatNumber value="${avlMoney/100}" pattern="#0.00" /></div> 
								<div id="icon-ask-1" class="icon-ask-div icon-ask-1" onmouseover="display(1)" onmouseout="disappear(1)" style="display: none;">当前可投资金额。</div>
							</div> 
                            
                            <div class="center_account_detail_item">
                                <div class="item_title">冻结金额</div>
								<i class="icon-ask fn-right j-atip" data-content="已投标且待成立项目之投资金额总和。" onmouseover="display(2)" onmouseout="disappear(2)"></i>
                                <div class="item_ammount">¥<fmt:formatNumber value="${freezeCapital/100}" pattern="#0.00" /></div>
								
								<div id="icon-ask-2" class="icon-ask-div icon-ask-2" onmouseover="display(2)" onmouseout="disappear(2)" style="display: none;">已投标且待成立项目之投资金额总和。</div>
							</div>
							 <div class="center_account_detail_item">
                                <div class="item_title">待收本金</div>
								<i class="icon-ask fn-right j-atip" data-content="已成立且尚未到期项目之投资金额总和。" onmouseover="display(3)" onmouseout="disappear(3)"></i>
                                <div class="item_ammount">¥<fmt:formatNumber value="${waitCapital/100}" pattern="#0.00" /></div>
								
								<div id="icon-ask-3" class="icon-ask-div icon-ask-3" onmouseover="display(3)" onmouseout="disappear(3)" style="display: none;">已成立且尚未到期项目之投资金额总和。</div>
							</div>
                            <div class="center_account_detail_item">
                                <div class="item_title">待收收益</div>
								<i class="icon-ask fn-right j-atip" data-content="已成立且尚未到期项目之应收利息总和。" onmouseover="display(4)" onmouseout="disappear(4)"></i>
                                <div class="item_ammount">¥<fmt:formatNumber value="${waitProfit/100}" pattern="#0.00" /></div>
								
								<div id="icon-ask-4" class="icon-ask-div icon-ask-4" onmouseover="display(4)" onmouseout="disappear(4)" style="display: none;">已成立且尚未到期项目之应收利息总和。</div>
							</div>
						   <div class="center_account_detail_item">
                                <div class="item_title">累计收益</div>
								<i class="icon-ask fn-right j-atip" data-content="历史投资收益的总和。" onmouseover="display(5)" onmouseout="disappear(5)"></i>
                                <div class="item_ammount">¥<fmt:formatNumber value="${allProfit/100}" pattern="#0.00" /></div>
								
								<div id="icon-ask-5" class="icon-ask-div icon-ask-5" onmouseover="display(5)" onmouseout="disappear(5)" style="display: none;">历史投资收益的总和。</div>
							</div>
						    <div class="center_account_detail_item">
                                <div class="item_title">账户资产</div>
								<i class="icon-ask fn-right j-atip" data-content="账户资产=可用余额+冻结金额+待收本金+待收收益。" onmouseover="display(6)" onmouseout="disappear(6)"></i>
                                <div class="item_ammount">¥<fmt:formatNumber value="${(avlMoney+freezeCapital+waitCapital+waitProfit)/100}" pattern="#0.00" /></div>
								
								<div id="icon-ask-6" class="icon-ask-div icon-ask-6" onmouseover="display(6)" onmouseout="disappear(6)" style="display: none;">账户资产=可用余额+冻结金额+待收本金+待收收益。</div>
							</div>
						 </div> 
                <!-- 
                <h2 style="margin-top:30px;">最近购买</h2>
                <div class="investCon grzx_gmjl">
                	
                   <table  cellpadding="0" cellspacing="0" style="border:none;">
                   		<tr>  
                        	<th>资产名称</th><th>购买日期</th><th>购买金额(元)</th><th>预计收益(元)</th><th>回款日期</th><th>状态</th><th>凭证</th>
                        </tr><c:forEach items="${recentList}" var="item">
                        <tr>
                        	<td>${item.loanname}</td>
                        	<td><fmt:formatDate value="${item.bidtime}" pattern="yyyy/MM/dd"/></td>
                        	<td><fmt:formatNumber value="${item.bidmoney/100}" pattern="#0.00" /></td>
                        	<td><fmt:formatNumber value="${item.profit/100}" pattern="#0.00" /></td>
                        	<td><fmt:formatDate value="${item.repaydate}" pattern="yyyy/MM/dd"/></td>
                        	<td>${item.stname}</td>
                        	<td>下载</td> 
                        </tr></c:forEach>
                   </table> 
                </div> 
                -->
            </div>  
        </div>
      </div>
</div>

<!--页脚-->
<div class="shadow2 hidden"></div>
<jsp:include page="/WEB-INF/pages/front/member/include/footer.jsp"/>   
<script>
			 
function display(i){
	var name= "icon-ask-"+i;
    document.getElementById(name).style.display="block"; 
}
function disappear(i){
	var name= "icon-ask-"+i;
    document.getElementById(name).style.display="none"; 
}
</script>

</body>
</html>