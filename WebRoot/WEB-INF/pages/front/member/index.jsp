<%@ page language="java" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
<title>会员中心</title> 
<link rel="stylesheet" type="text/css" href="/resources/style/mem/index.css">
<link rel="stylesheet" type="text/css" href="/resources/style/mem/reset.css">
<body class="IndexPage mgt30">  
<jsp:include page="/WEB-INF/pages/front/member/include/header.jsp"/>   
<div class="wrap banner" style="background:url(/resources/images/mem/banner.jpg) no-repeat fixed center top;">
    <div class="mg-auto overflow area clearfix bannerCon">
    	<div class="clearfix pdt10">
            <div class="Logo fl" >
                <img src="/resources/images/mem/logo.png" alt="融银普惠" title="融银普惠"> 
            </div>
            <div class="listNav mgb30 fr" id="cssmenu">
            	<ul class="clearfix nav">
                	<li id="mainIndexNav" class="current"><a href="/recommend/">首页</a></li>
                    <li id="mainProductNav"><a href="/member/invest/">在线产品</a></li>
                    <li id="mainSafeNav"><a href="/member/security/">安全保障</a></li>  
                    <li id="mainAssetNav"><a href="/member/center/">我的账户</a></li>
                	<li class="bg" style="width: 44px; left: 0px;"></li>
            	</ul>
        	</div>
        </div>
        <h2>融银与您<br>意义不仅止于财富</h2> 
        	<div class="userLogin tianstion"><c:if test="${empty memberVO}">
        		<a href="/account/member/login/">用户登录</a></c:if><c:if test="${!empty memberVO}">
        		<a href="/member/invest/">会员专属</a></c:if>
        	</div> 
        <a class="arrowBtn" href="javascript:void(0)" target="_self" style="top: 470px;"></a>
     </div>
</div> 
<!-- 
<div class="box_indexCon">

 
	<div class="wrap bg4">
	    <div class="mg-auto overflow area clearfix centerText">
	    	<h2>融银团队</h2>
	        <h3><span>丰富的投资经历</span></h3>
	    	<ul class="listCon clearfix mgb30">
	        	<li>
	            	<a href="javascript:void(0);">
	                    <img src="/resources/images/tm.gif" title="融银普惠" alt="融银普惠">
	                    <dl>
                            <dt>XXX</dt>
                            <dd>XXXXXXXXXXXXXXXXXX</dd>
                            <dd>XXXXXXXXX</dd>
                        </dl>
	                </a>
	            </li>
	            <li>
	            	<a href="javascript:void(0);">
	                <img src="/resources/images/tm.gif" title="融银普惠" alt="融银普惠">
	                <dl>
                        <dt>XXXXXXXXX</dt>
                        <dd>XXXXXXXXX</dd>
                        <dd>XXXXXXXXX</dd>
                    </dl>
	                </a>
	            </li>
	            <li>
	            	<a href="javascript:void(0);">
	                <img src="/resources/images/tm.gif" title="融银普惠" alt="融银普惠">
	                <dl>
                        <dt>XXXXXXXXX</dt>
                        <dd>XXXXXXXXX</dd>
                        <dd>XXXXXXXXX</dd>
                        <dd>XXXXXXXXX</dd>
                    </dl>
	                </a>
	            </li>
	        </ul>
	  	</div> 
	</div>
 
	<div class="wrap bg5">
	    <div class="mg-auto overflow area clearfix centerText">
	    	<h2>我们的优势</h2>
	        <h3><span>专属于您的理财专家</span></h3>
	    	<ul class="mgb80 clearfix">
	        	<li class="project-card-item">
	              <div class="cool clearfix">
	                  <div class="front">
	                      <img src="/resources/images/mem/study.png" title="融银普惠" alt="融银普惠">
	                      <b>1400</b>
	                  </div>
	              </div>
	  			  <dl>
	                <dt>专业</dt>
	                <dd>累计服务超过5万名高净值人士, 资产管理规模超过<span>1400</span>亿元人民币</dd>
	              </dl>
	            </li>
	            <li class="project-card-item">
	              <div class="cool clearfix">
	                  <div class="front">
	                      <img src="/resources/images/mem/lock.png" title="融银普惠" alt="融银普惠">
	                      <b>100%</b>
	                  </div>
	              </div>
	  			  <dl>
	                <dt>安全</dt>
	                <dd>从2003年至今，所有到期的类固定收益产品均<span>100%</span>安全兑付！</dd>
	              </dl>
	            </li>
	            <li class="project-card-item">
	              <div class="cool clearfix">
	                  <div class="front">
	                      <img src="/resources/images/mem/diamond.png" title="融银普惠" alt="融银普惠">
	                      <b>4P</b>
	                  </div>
	              </div>
	  			  <dl>
	                <dt>精选</dt>
	                <dd>严格按照<span>4P</span>筛选标准对产品进行定量和定性分析，确保完美品质</dd>
	              </dl>
	            </li>
	        </ul>
	    </div> 
	</div>
 
	<div class="wrap bg6">
	    <div class="mg-auto overflow area clearfix centerText">
	    	<h2>合作伙伴</h2>
	        <h3><span>行业的领跑者</span></h3>
	    	 <ul class="listCon clearfix mgb80">
                <li><img src="/resources/images/mem/brand1.jpg" title="歌斐资产" alt="歌斐资产"></li>
                <li><img src="/resources/images/mem/brand2.jpg" title="红杉资本" alt="红杉资本"></li>
                <li><img src="/resources/images/mem/brand3.jpg" title="IDG资本" alt="IDG资本"></li>
                <li><img src="/resources/images/mem/brand4.jpg" title="鼎晖投资" alt="鼎晖投资"></li>
                <li><img src="/resources/images/mem/brand5.jpg" title="达晨创投" alt="达晨创投"></li>
                <li><img src="/resources/images/mem/brand6.jpg" title="万科" alt="万科"></li>
                <li><img src="/resources/images/mem/brand7.jpg" title="铁狮门" alt="铁狮门"></li>
                <li><img src="/resources/images/mem/brand8.jpg" title="恒大集团" alt="恒大集团"></li>
                <li><img src="/resources/images/mem/brand9.jpg" title="华远地产" alt="华远地产"></li>
                <li><img src="/resources/images/mem/brand10.jpg" title="万通控股" alt="万通控股"></li>
                <li><img src="/resources/images/mem/brand11.jpg" title="上投摩根" alt="上投摩根"></li>
                <li><img src="/resources/images/mem/brand12.jpg" title="南方基金" alt="南方基金"></li>
                <li><img src="/resources/images/mem/brand13.jpg" title="万家基金" alt="万家基金"></li>
                <li><img src="/resources/images/mem/brand14.jpg" title="博时基金" alt="博时基金"></li>
                <li><img src="/resources/images/mem/brand15.jpg" title="汇添富基金" alt="汇添富基金"></li>
            </ul>
	  	</div> 
	</div>
</div>
-->
<jsp:include page="/WEB-INF/pages/front/member/include/footer.jsp"/>   
<script> 
//一屏效果
var timerscroll=null;
var num=$(".box_indexCon").offset().top;
var liuOn=true;
$(".arrowBtn").click(function(){
	movescoll(num);
	
});
$(".top").click(function(){
	movescoll(0);
	return false;
});

$(document).on("touchstart touchmove",function(){
	liuOn=false;
});
$(window).scroll(function(){
	
	if(!liuOn){
		clearInterval(timerscroll);
	}
	liuOn=false;
});

function movescoll(iTarget){
	clearInterval(timerscroll);
	timerscroll=setInterval(function(){
		liuOn=true;
		var scrollTop=$(window).scrollTop();
		var iSpeed=(iTarget-scrollTop)/5;
		iSpeed=iSpeed>0?Math.ceil(iSpeed):Math.floor(iSpeed);
		if(Math.abs(iTarget-scrollTop)<1){
			clearInterval(timerscroll);	
			$(window).scrollTop(iTarget)
		}else{
		
			$(window).scrollTop(scrollTop+iSpeed);
		}
		
	},50)
} 
</script> 
</body>
</html>