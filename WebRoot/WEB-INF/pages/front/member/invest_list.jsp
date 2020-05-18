<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>在线产品</title> 
<link rel="stylesheet" type="text/css" href="/resources/style/mem/index.css">
<link rel="stylesheet" type="text/css" href="/resources/style/mem/reset.css">  
</head>
<body class="IndexPage mgt30">  
<jsp:include page="/WEB-INF/pages/front/member/include/header.jsp"/> 
<div class="wrap productNav">
	<div class="mg-auto area clearfix">
		<h1 class="fl logo"><img src="/resources/images/mem/logo2.png" title="融银普惠" alt="融银普惠"></h1>
		<div class="listNav mgb30 fr" id="cssmenu">
			<ul class="clearfix nav">
				<li id="mainAssetNav"><a href="/recommend/">首页</a></li>
                <li id="mainProductNav" class="current"><a href="/member/invest/">在线产品</a></li> 
                <li id="mainSafeNav"><a href="/member/security/">安全保障</a></li>
                <li id="mainAssetNav"><a href="/member/center/">我的账户</a></li>
               	<li class="bg" style="width: 44px; left: 0px;"></li>
			</ul>
		</div>
	</div>
</div> 
<div class="tjzcbanner"></div>

<div class="tjzc_cot" style="padding-bottom:60px;">
	<div class="tjzc_cot_1">
    	<a <c:if test="${st eq 1}">class="sel"</c:if> href="/member/invest/class_1">可购买</a>
        <a <c:if test="${st eq 2}">class="sel"</c:if>href="/member/invest/class_2">已满标</a>
        <a <c:if test="${st eq 3}">class="sel"</c:if>href="/member/invest/class_3">已回款</a>
    </div>
    <div class="tjzc_cot_2">
    	<c:if test="${!empty xinloanList}"><c:forEach items="${xinloanList}" var="item">
    	<h2><i></i>新人专享</h2>
        <div class="tjzc_cot_div">
        	<div class="tjzc_cot_divimg"><img src="${item.coverurl}" width="300px" onerror="this.src='/resources/images/tm.gif'"/></div>
            <div class="tjzc_cot_divrr">
                <h3>${item.name}<i>限购一次</i></h3>
                <h4><img src="/resources/images/mem/diz.png" />${item.address}</h4>
                <ul>
                    <li>
                        <small style="width:158px;">
                            <i><fmt:formatNumber value="${item.allprice/1000000}" pattern="#0.00" /><b>万元</b></i>
                            <h5>房产总额</h5>
                        </small>
                    </li>
                    <li>
                        <small style="width:156px;">
                           <i><fmt:formatNumber value="${item.avgprice/1000000}" pattern="#0.00" /><b>万元</b></i>
                            <h5>市场平均售价</h5>
                        </small>
                    </li>
                    <li>
                        <small style="width:156px;">
                            <i><fmt:formatNumber value="${item.exprice/1000000}" pattern="#0.00" /><b>万元</b></i>
                            <h5>预期交易金额</h5>
                        </small>
                    </li>
                    <li>
                        <small style="width:156px;">
                            <i><fmt:formatNumber value="${item.avalmoney/1000000}" pattern="#0.00" /> <b>万元</b></i>
                            <h5>剩余可投金额</h5>
                        </small>
                    </li>
                    <li>
                        <small style="width:156px;">
                            <i><fmt:formatNumber value="${item.earnings/100}" pattern="#0.00" /> <b>元</b></i>
                            <h5>1000元/份预期收益</h5>
                        </small>
                    </li>
                    <li>
                        <small style="width:156px;">
                            <i>${item.loandead}<b>天</b></i>
                            <h5>投资周期</h5>
                        </small>
                    </li>
                  
                </ul>
            </div>
            <div class="tjzc_cot_divraaa"><a href="/member/invest/detail/id_${item.lnid}">立即购买</a></div>  
        </div>
        </c:forEach></c:if>
        
        <c:if test="${!empty loanList}">
        <h2><i></i>资产推荐</h2>
		<c:forEach items="${loanList}" var="item">
		<div class="tjzc_cot_div">
		    <div class="tjzc_cot_divimg"><img src="${item.coverurl}" width="300px" onerror="this.src='/resources/images/tm.gif'"/></div>
		    <div class="tjzc_cot_divrr">
		        <h3>${item.name}<i>限购一次</i><!-- <i>限购一次</i> --></h3>
		        <h4><img src="/resources/images/mem/diz.png" />${item.address}</h4>
		        <ul>
		            <li>
		                <small style="width:158px;">
		                   <i><fmt:formatNumber value="${item.allprice/1000000}" pattern="#0.00" /><b>万元</b></i>
		                   <h5>房产总额</h5>
		               </small>
		           </li>
		           <li>
		               <small style="width:156px;">
		                   <i><fmt:formatNumber value="${item.avgprice/1000000}" pattern="#0.00" /><b>万元</b></i>
		                   <h5>市场平均售价</h5>
		               </small>
		           </li>
		           <li>
		               <small style="width:156px;">
		                   <i><fmt:formatNumber value="${item.exprice/1000000}" pattern="#0.00" /><b>万元</b></i>
		                   <h5>预期交易金额</h5>
		               </small>
		           </li>
		           <li>
		               <small style="width:156px;">
		                   <i><fmt:formatNumber value="${item.avalmoney/1000000}" pattern="#0.00" /> <b>万元</b></i>
		                   <h5>剩余可投金额</h5>
		               </small>
		           </li>
		           <li>
		               <small style="width:156px;">
		                   <i><fmt:formatNumber value="${item.earnings/100}" pattern="#0.00" /> <b>元</b></i>
		                   <h5>1000元/份预期收益</h5>
		               </small>
		           </li>
		           <li>
		               <small style="width:156px;">
		                    <i>${item.loandead}<b>天</b></i>
		                    <h5>投资周期</h5>
		                </small>
		            </li> 
		        </ul>
		    </div>
		    <div class="tjzc_cot_divraaa"><a href="/member/invest/detail/id_${item.lnid}">立即购买</a></div>
		</div>
        </c:forEach>
        </c:if>     
    </div>
</div>
<jsp:include page="/WEB-INF/pages/front/member/include/footer.jsp"/> 
</body>
</html>