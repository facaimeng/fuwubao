<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@ taglib prefix="str" uri="http://www.cbai.com.cn/utils-strsplit"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>融银普惠服务平台</title>
<link type="text/css" href="/resources/style/web/main.css" rel="stylesheet" /> 
<link rel="stylesheet" type="text/css" href="/resources/js/swiper/s2.css" /> 
</head> 

<body> 
<div class="yingying"> 
<jsp:include page="/WEB-INF/pages/front/web/include/header.jsp"/> 
<div style="width: 100%;overflow: hidden;">
	<div class="swiper-container">
        <div class="swiper-wrapper"><c:forEach items="${adList}" var="item" varStatus="current">
            <div class="swiper-slide"><a href="${item.adurl}" target="_blank"><img src="${item.imgurl}"/></a></div></c:forEach> 
        </div> 
        <div class="swiper-pagination"></div> 
    </div> 
</div>

<div class="about">
	<div class="about_img"><img src="/resources/images/web/about_og.png" /></div>
    <div class="about_cot">
    	<h2>关于融银</h2>
        <h3>ABOUT US</h3>
        <div></div>
        <h4 style="clear:both;"></h4>
        <p>融银普惠是融银科技公司与融银普惠信息技术公司联合打造的专为中产阶层会员提供资产管理服务的平台。融银人以资产为立足点，结合资本、资管全方位运营，旨在资产配置、保值增值等普惠财富管理领域有所建树，回馈社会。</p>
    </div>
</div>

<div class="fuwu">
	<div class="fuwu_cot">
    	<h2>融银服务</h2>
        <h3>SERVICE</h3>
        <div></div>
        <h4 style="clear:both;"></h4>
    </div>
    <ul class="fuwu_ul">
    	<li>
        	<img src="/resources/images/web/fuwu_2.png" width="308" height="130" />
            <h2>资本</h2>
            <h3></h3>
            <p>融银普惠是专为广大用户服务的互联网综合服务平台。采用先进的网络技术与安全手段，为广大用户提供在线开户、签约等互联网便捷服务。</p>
            <a href="/service/online_finance/">了解更多></a>
        </li>
        <li>
        	<img src="/resources/images/web/fuwu_1.png" width="308" height="130" />
            <h2>资产</h2>
            <h3></h3>
            <p>融银普惠引进资产服务平台的概念，以资产为导向，建立聚焦在底层资产为需求的资产平台，也同时建立一个完整的以资产为基础的服务平台。</p>
            <a href="/service/wealth_management/">了解更多></a>
        </li>
        <li style=" margin-right:0px;">
        	<img src="/resources/images/web/fuwu_3.png" width="308" height="130" />
            <h2>资管</h2>
            <h3></h3>
            <p>建立多元类型的资产中心，以提供关于资产全方位服务，挑选具有可供“价值挖掘、价值发现”的优质资产，凭融银普惠在资产处置业务中拥有独特的资源，处置变现资产。</p>
            <a href="/service/assets_management/">了解更多></a>
        </li>
    </ul>
</div>

<div class="ryfw">
	<div class="ryfw_cot_1">
    	<h2>融银之声</h2>
       	<h3>POINT</h3>
        <div></div>
        <h4 style="clear:both;"></h4>
        <p>强大的研究能力为产品研发、筛选和风控保驾护航。融银普惠的研究范围涵盖宏观经济、类固定收益、二级市场、房地产、私募股权等领域，解读热点，分析策略，帮助客户把握趋势，前瞻布局，实现资产稳健、安全的增长。</p>
    </div>
    <div style="clear:both;"></div>
	<div class="ryfw_cot">
    	<ul><c:forEach items="${rpList}" var="item" varStatus="current"><c:if test="${current.count==1 or current.count==2}">
    		<li>
        		<a href="/news/rongyin_point/id_${item.nid}" target="_blank">
	            	<img src="${item.coverurl}" width="283" height="268" />
	                <div>
	                	<h2>${item.title}</h2>
	                    <p>${str:onSubString(item.pdescription,158,true)}</p>
	                </div>
	                <span></span>
                </a>
            </li></c:if><c:if test="${current.count==3 or current.count==4}">
             <li class="ryfw_cot_li">
             	<a href="/news/rongyin_point/id_${item.nid}" target="_blank">
                <div>
                	<h2>${item.title}</h2>
                    <p>${str:onSubString(item.pdescription,158,true)}</p>
                </div>
                <img src="${item.coverurl}" width="270" height="268" />
                <span class="jit2"></span>
                </a>
            </li></c:if> 
        	</c:forEach>  
        </ul>
        <div class="ryfw_cot_sx">
        	<a href="/news/rongyin_point/" class="ryfw_cot_sx_1"></a>
            <a href="/news/rongyin_point/" class="ryfw_cot_sx_2"></a>
        </div>	
        <div style="clear:both;"></div>
    </div>
</div>


<div class="new">
	<div class="new_cot">
    	<div class="new_cot_nav">
        	<div class="new_cot_nav_1">
            	<h2>融银动态</h2>
       			<h3>NEWS</h3>
        		<div></div>
                <img src="/resources/images/web/jjjj.png" />	
            </div>
            <ul class="new_cot_nav_li">
            	<li data-i="1" class="tabcls new_cot_nav_lion">
            		<a  href="javascript:void(0);">
	                	<h2>最新公告</h2>
	                    <h3>NOTICE</h3>
	                    <span></span>
                    </a>
                </li>
                <li class="tabcls" data-i="2">
                	<a href="javascript:void(0);">
	                	<h2>公司动态</h2>
	                    <h3>OURNEWS</h3>
	                    <span></span>
                    </a>
                </li>
                <li class="tabcls" data-i="3">
                	<a href="javascript:void(0);">
	                	<h2>财富学堂</h2>
	                    <h3>WEALTH CLASS</h3>
	                    <span></span>
                    </a>
                </li>
            </ul>
             <div style="clear:both;"></div>
        </div>
        <ul class="nrwss" id="tab1" ><c:forEach items="${noticeList}" var="item" varStatus="current">
        	<li>
            	<div class="nrwss_l">
                	<h2><fmt:formatDate value="${item.pubtime}" pattern="dd"/></h2>
                    <h3><fmt:formatDate value="${item.pubtime}" pattern="yyyy-MM"/></h3>
                </div>
                <div class="nrwss_r">
                	<div>
                        <h2><a href="/news/notice/id_${item.nid}" target="_blank">${item.title}</a></h2>
                        <p>${str:onSubString(item.pdescription,58,true)}</p>
                        <h3><img src="/resources/images/web/yj.png" />${item.viewcount} <a href="/news/notice/id_${item.nid}" target="_blank">阅读全文 >></a> <a class="nrwss_r_fx"><img src="/resources/images/web/fxx.png" /></a></h3>
                    </div>
                </div>
            </li> </c:forEach>
            <div class="ckgd">
            	<div class="ckgd_l">
                </div>
                <div class="ckgd_r"><a href="/news/notice/">查看更多</a></div>
            </div>	
        </ul>
         <ul class="nrwss" id="tab2" style="display: none;"><c:forEach items="${ournewsList}" var="item" varStatus="current">
        	<li>
            	<div class="nrwss_l">
                	<h2><fmt:formatDate value="${item.pubtime}" pattern="dd"/></h2>
                    <h3><fmt:formatDate value="${item.pubtime}" pattern="yyyy-MM"/></h3>
                </div>
                <div class="nrwss_r">
                	<div>
                        <h2><a href="/news/ournews/id_${item.nid}" target="_blank">${item.title}</a></h2>
                        <p>${str:onSubString(item.pdescription,58,true)}</p>
                        <h3><img src="/resources/images/web/yj.png" />${item.viewcount} <a href="/news/ournews/id_${item.nid}" target="_blank">阅读全文 >></a> <a class="nrwss_r_fx"><img src="/resources/images/web/fxx.png" /></a></h3>
                    </div>
                </div>
            </li> </c:forEach>
            <div class="ckgd">
            	<div class="ckgd_l">
                </div>
                <div class="ckgd_r"><a href="/news/ournews/">查看更多</a></div>
            </div>	
        </ul>
         <ul class="nrwss" id="tab3" style="display: none;"><c:forEach items="${wcList}" var="item" varStatus="current">
        	<li>
            	<div class="nrwss_l">
                	<h2><fmt:formatDate value="${item.pubtime}" pattern="dd"/></h2>
                    <h3><fmt:formatDate value="${item.pubtime}" pattern="yyyy-MM"/></h3>
                </div>
                <div class="nrwss_r">
                	<div>
                        <h2><a href="/news/wealth_class/id_${item.nid}" target="_blank">${item.title}</a></h2>
                        <p>${str:onSubString(item.pdescription,58,true)}</p>
                        <h3><img src="/resources/images/web/yj.png" />${item.viewcount} <a href="/news/wealth_class/id_${item.nid}" target="_blank">阅读全文 >></a> <a class="nrwss_r_fx"><img src="/resources/images/web/fxx.png" /></a></h3>
                    </div>
                </div>
            </li> </c:forEach>
            <div class="ckgd">
            	<div class="ckgd_l">
                </div>
                <div class="ckgd_r"><a href="/news/wealth_class/">查看更多</a></div>
            </div>	
        </ul> 
    </div>	
</div>


<div class="rmcp">
	<h2>热门产品推荐</h2>
    <h3>融银财富——专属于您的理财专家</h3>
    <a href="/recommend/"></a>
</div>

<div class="zlhb">
	<div class="zlhb_ct">
        <h2>合作单位</h2>
       	<h3>STRATEGIC PARTNERS</h3>
    	<div></div>
    </div>
    <ul>
    	<li><a href="javascript:void(0);"><img src="/resources/images/web/gsag1.jpg" /></a></li>
        <li><a href="javascript:void(0);"><img src="/resources/images/web/gsag2.jpg" /></a></li>
        <li><a href="javascript:void(0);"><img src="/resources/images/web/gsag4.jpg" /></a></li>
        <li><a href="javascript:void(0);"><img src="/resources/images/web/gsag3.jpg" /></a></li>
        <li><a href="javascript:void(0);"><img src="/resources/images/web/gsag5.jpg" /></a></li>
    </ul>
</div> 
<jsp:include page="/WEB-INF/pages/front/web/include/footer.jsp"/> 
<script type="text/javascript" src="/resources/js/swiper/swiper-3.4.2.jquery.min.js"></script>
<script type="text/javascript">
	var mySwiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination', 
        paginationClickable: true,
        spaceBetween: 30, 
        autoplay: 5000,
        effect : 'fade',
        autoplayDisableOnInteraction: false,
        loop:true
    }); 
	$(".tabcls").click(function(){
		var i = $(this).attr("data-i");
	    $(".tabcls").removeClass("new_cot_nav_lion"); 
	    $(this).addClass("new_cot_nav_lion");
    	$(".nrwss").hide();
    	$("#tab"+i).show();
    }); 
</script>  
</div> 
</body> 
</html>
