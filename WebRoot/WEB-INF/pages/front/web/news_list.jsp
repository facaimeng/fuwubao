<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@ taglib prefix="str" uri="http://www.cbai.com.cn/utils-strsplit"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${newstype.name}-${globals.gtitle}</title>
<meta name="keywords" content="${globals.gkeywords}" />
<meta name="description" content="${globals.gdescription}" />
<link type="text/css" href="/resources/style/web/main.css" rel="stylesheet" /> 
</head> 
<body> 
<div class="yingying">
<jsp:include page="/WEB-INF/pages/front/web/include/header.jsp"/>  
<div class="newlb">
	<div class="newlb_1"><a href="/">首页</a>  >  ${froms.name}  >  ${newstype.name}</div>
    <div class="newlb_2">
    	<div class="newlb_2_l">
        	<h2>${newstype.name}</h2>
            <h3>${newstype}</h3>
            <div></div>
            <img src="/resources/images/web/newlbjt.png" />
        </div>
        <div class="newlb_2_r">
        	<ul class="newlb_2_r_li"><c:if test="${!empty years}"><c:forEach items="${years}" var="item" varStatus="current">
            	<li id="y_${item}">
            		<a href="/${from}/${ntype}/?y=${item}"> <h2>${item}</h2> <span></span> </a>
                </li></c:forEach></c:if><c:if test="${!empty yearsNo}"><c:forEach items="${yearsNo}" var="item" varStatus="current">
            	<li id="y_${item}">
            		<a href="javascript:void(0);"><h2>${item}</h2><span></span></a>
                </li></c:forEach></c:if><c:if test="${empty yearsNo}">
                <li id="y_-1">
                	<a href="/${from}/${ntype}/?y=-1"><h2 style="font-size:18px; line-height:56px;"> ●  &nbsp;&nbsp; ● &nbsp;&nbsp; ● </h2><span></span></a>
                </li> </c:if>  <c:if test="${!empty yearsNo}">
                <li>
                	<a href="javascript:void(0);"><h2 style="font-size:18px; line-height:56px;"> ●  &nbsp;&nbsp; ● &nbsp;&nbsp; ● </h2><span></span></a>
                </li> </c:if>  
            </ul>
            <div style="clear:both;"></div>
        </div>
    </div>
</div>

<div class="newlb_3">
    	<ul class="newlb_3_li"><c:forEach items="${page.list}" var="item" varStatus="current">
        	<li>
            	<div class="newlb_3_li_nrwss_l">
                	<h2><fmt:formatDate value="${item.pubtime}" pattern="dd"/></h2>
                    <h3><fmt:formatDate value="${item.pubtime}" pattern="yyyy-MM"/></h3>
                </div>
                <div class="newlb_3_li_nrwss_r">
                	<div>
                    	<img src="${item.coverurl}" width="122" height="122"/>
                    </div>
                	<div class="newlb_3_li_nrwss_r_2">
                        <h2><a href="/${from}/${ntype}/id_${item.nid}" target="_blank">${item.title}</a></h2>
                        <p>${str:onSubString(item.pdescription,58,true)}</p>
                        <h3>
                        	<img src="/resources/images/web/yj.png" />${item.viewcount} <a href="/${from}/${ntype}/id_${item.nid}" target="_blank">阅读全文 >></a> <a class="nrwss_r_fx" href="#"><img src="/resources/images/web/fxx.png" /></a>
                        </h3>
                    </div>
                </div>
            </li><c:if test="${!current.last}">
            <div style="clear:both;"></div></c:if></c:forEach> 
        </ul>
        <div class="newlb_sx"><c:if test="${page.pageNo > 1}">
        	<a href="/${from}/${ntype}/?y=${y}&p=${page.pageNo-1}" class="newlb_sx_1"></a></c:if><c:if test="${page.pageNo == 1}">
        	<a href="javascript:void(0);" class="newlb_sx_1"></a></c:if><c:if test="${page.pageNo < page.totalPage}">
            <a href="/${from}/${ntype}/?y=${y}&p=${page.pageNo+1}" class="newlb_sx_2"></a></c:if><c:if test="${page.pageNo == page.totalPage}">
            <a href="javascript:void(0);" class="newlb_sx_2"></a></c:if>
        </div>	
</div> 
<jsp:include page="/WEB-INF/pages/front/web/include/footer.jsp"/>
<script type="text/javascript">
	$("#"+'y_${y}').addClass("newlb_2_r_lion"); 
</script>  
</div> 
</body> 
</html>
