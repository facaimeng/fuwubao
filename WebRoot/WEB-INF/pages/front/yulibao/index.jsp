<%@ page language="java" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty fssInfo}">
    最新收益率:${fssInfo.prdRate}<br/>
    最近 7 日年化收益率 :${fssInfo.annuRate}
</c:if>
<br/>
==============================<br/>
<c:if test="${!empty fssAcctsInfo}">
    生利宝余额:${fssAcctsInfo.totalAsset}<br/>
    生利宝历史累计收益 :${fssAcctsInfo.totalProfit}
</c:if>
<c:if test="${empty fssAcctsInfo}">
    生利宝余额:0.00<br/>
    生利宝历史累计收益 :0.00
</c:if>
<br/>

<a href="/member/fss/fsstrans">立即购买</a>