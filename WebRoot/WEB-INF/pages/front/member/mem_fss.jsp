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
 
.buyBtn{ font-size:14px;width:120px; height:35px; line-height:35px; color:#fff; background:#ec5801; display:block; text-align:center; border-radius:3px; margin-left:70px;}
.investRight font a:hover {
    background: #dc5201;
    transition: .3s;
}
</style>
</head>
<body class="iCenter assetAll assetAllNo mgt30"> 
<jsp:include page="/WEB-INF/pages/front/member/include/center_header.jsp"/>   
<div class="wrap">
	<div class="mg-auto overflow area clearfix"> 
<div class="listNav mgb20">
     <ul class="clearfix nav bd">
         <li id="persistNav" ><a href="/member/account/overview/">我的账户</a></li> 
         <li id="dealNav"><a href="/member/asset/orders/">我的资产</a></li>
         <li id="accountNav" class="current"><a href="/member/found/logs/">我的资金</a></li>
         <li class="bg" style="width: 88px; left: 0px;"></li>
     </ul>
 </div>
 
        <div class="clearfix personCon mgb100"> 
        	
		<div class="investLeft fl">
		 	<ul class="leftNavList">
		     	<li id="assetAllNav" class="mgl0 "><a href="/member/found/logs/"><i class="leftNavList_4"></i>资金记录</a></li>
				<li id="assetAllNav" class="mgl0 "><a href="/member/found/cards/"><i class="leftNavList_9"></i>我要绑卡</a></li>
				<li id="assetAllNav" class="mgl0 "><a href="/member/found/recharge/" ><i class="leftNavList_7"></i>我要充值</a></li>
				<li id="assetAllNav" class="mgl0 "><a href="/member/found/cashout/"><i class="leftNavList_8"></i>我要提现</a></li>
				<li id="assetAllNav" class="mgl0 mgl0yes"><a href="/member/fss/queryfss/" class="cur"><i class="leftNavList_10"></i>我的生利宝</a></li>
		     </ul>
		 </div> 
            <div class="investRight fl">
            	<h2>我的生利宝</h2>
            	<div class="center_account_detail investCon">
                            <div class="center_account_detail_item">
                                <div class="item_title">最新收益率</div>
								<i class="icon-ask fn-right j-atip" data-content="生利宝当前最新收益率（%）。" onmouseover="display(1)" onmouseout="disappear(1)"></i>
                                <div class="item_ammount">${fssInfo.prdRate}</div> 
								<div id="icon-ask-1" class="icon-ask-div icon-ask-1" onmouseover="display(1)" onmouseout="disappear(1)" style="display: none;">生利宝当前最新收益率（%）。</div>
							</div> 
                            
                            <div class="center_account_detail_item">
                                <div class="item_title">最近7日年化收益率</div>
								<i class="icon-ask fn-right j-atip" data-content="生利宝最近 7 日年化收益率（%）。" onmouseover="display(2)" onmouseout="disappear(2)"></i>
                                <div class="item_ammount">${fssInfo.annuRate}</div>
								
								<div id="icon-ask-2" class="icon-ask-div icon-ask-2" onmouseover="display(2)" onmouseout="disappear(2)" style="display: none;">最近 7 日年化收益率（%）。</div>
							</div>
							 <div class="center_account_detail_item">
                                <div class="item_title">生利宝余额</div>
								<i class="icon-ask fn-right j-atip" data-content="生利宝在投金额。" onmouseover="display(3)" onmouseout="disappear(3)"></i>
                                <div class="item_ammount">¥<c:if test="${empty fssAcctsInfo}">0.00</c:if><c:if test="${!empty fssAcctsInfo}">${fssAcctsInfo.totalAsset}</c:if></div>
								
								<div id="icon-ask-3" class="icon-ask-div icon-ask-3" onmouseover="display(3)" onmouseout="disappear(3)" style="display: none;">生利宝在投金额。</div>
							</div>
                            <div class="center_account_detail_item">
                                <div class="item_title">生利宝累计收益</div>
								<i class="icon-ask fn-right j-atip" data-content="生利宝历史累计收益。" onmouseover="display(4)" onmouseout="disappear(4)"></i>
                                <div class="item_ammount">¥<c:if test="${empty fssAcctsInfo}">0.00</c:if><c:if test="${!empty fssAcctsInfo}">${fssAcctsInfo.totalProfit}</c:if></div> 
								<div id="icon-ask-4" class="icon-ask-div icon-ask-4" onmouseover="display(4)" onmouseout="disappear(4)" style="display: none;">生利宝历史累计收益。</div>
							</div> 
				</div> 
                <font >
               		<a class="buyBtn" href="/member/fss/fsstrans/" target="_blank">我要购买</a>
                </font>
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