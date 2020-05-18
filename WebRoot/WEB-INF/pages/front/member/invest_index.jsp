<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>在线产品</title> 
<link rel="stylesheet" type="text/css" href="/resources/style/mem/index.css">
<link rel="stylesheet" type="text/css" href="/resources/style/mem/reset.css">     
<link rel="stylesheet" type="text/css" href="/resources/style/mem/animate.css"> 
<link rel="stylesheet" type="text/css" href="/resources/style/mem/indexJuly.css"> 
<link rel="stylesheet" type="text/css" href="/resources/js/swiper/s1.css"> 
<style >
	* {-webkit-box-sizing: border-box;-moz-box-sizing: border-box; box-sizing: border-box;}
	.boxx {-webkit-box-sizing: content-box;-moz-box-sizing: content-box;box-sizing: content-box;}
</style>

</head>
<body class="IndexPage mgt30">  
	<jsp:include page="/WEB-INF/pages/front/member/include/header.jsp"/> 
	<div class="wrap productNav boxx" style="border-bottom:solid 1px #e4e2e2;">
		<div class="mg-auto area clearfix boxx">
			<h1 class="fl logo boxx"><img src="/resources/images/mem/logo2.png" title="融银普惠" alt="融银普惠"></h1>
			<div class="listNav mgb30 fr boxx" id="cssmenu">
				<ul class="clearfix nav boxx">
					<li id="mainAssetNav"><a href="/recommend/">首页</a></li>
	                <li id="mainProductNav" class="current"><a href="/member/invest/">在线产品</a></li> 
	                <li id="mainSafeNav"><a href="/member/security/">安全保障</a></li>
	                <li id="mainAssetNav"><a href="/member/center/">我的账户</a></li>
	               	<li class="bg" style="width: 44px; left: 0px;"></li>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="banner_index">
		<div class="swiper-container">
			<ol class="swiper-pagination"> </ol>
			
			<div class="swiper-wrapper"><c:forEach items="${adList}" var="item" varStatus="current">
				<div class="swiper-slide" style="cursor: pointer;">
					<a href="${item.adurl}" target="_blank"><img src="${item.imgurl}"/></a>
				</div></c:forEach>  
			</div>
			
			<div style="position: absolute; bottom: 50%; width: 100%;">
				<div class="container">
					<a href="javascript:void(0);" class=" swiper-button-prev">
						<span>
							<img src="/resources/images/mem/arrows.png">
						</span>
					</a>
					<a href="javascript:void(0);" class="swiper-button-next" >
						<span>
							<img src="/resources/images/mem/arrows.png">
						</span>
					</a>
				</div>
			</div>
		</div>
	</div>
	
	<div class="index_pro boxx" >
			<div id="jingpin_loan" class="container">
				<c:if test="${!empty xinloanList}"><c:forEach items="${xinloanList}" var="xinshou">
				<div>
					<div class="in_title">
						<h1>活动专享</h1>
						<h2></h2>
					</div>
					
					<div class="fine_quality">
						<div class="fine_tab">
							<img src="/resources/images/mem/index_tab.png">
						</div>
						<div class="fine_quality_l">
							<div class="fine_quality_l_pic"> 
								<img src="${xinshou.coverurl}" width="620" height="315" onerror="this.src='/resources/images/tm.gif'">
							</div>
							<div class="fine_quality_l_detail">
								<div class="fine_bg"></div>
								<div class="fine_quality_l_text">
									<p style="width: 150px;"><fmt:formatNumber value="${xinshou.avalmoney/1000000}" pattern="#0.00" /><b>万元</b>
										<br>可投金额</p>
									<p style="width: 210px;"><fmt:formatNumber value="${xinshou.allprice/1000000}" pattern="#0.00" /><b>万元</b>
										<br>资产总额</p>
									<p style="width: 90px;">${xinshou.loandead}<b>天</b>
										<br>投资周期</p>
									<a href="/member/invest/detail/id_${xinshou.lnid}">查看此项目</a>
								</div>
							</div>
						</div>
						
						<div class="fine_quality_r">
							<h1><a href="/member/invest/detail/id_${xinshou.lnid}" style="color:#000;">${xinshou.name}</a></h1>
							<div class="location clearfix">
								<img src="/resources/images/mem/dingwei.png">
								<p>${xinshou.address}</p>
							</div>
							
							<div class="fine_quality_r00">
								<div class="fine_quality_r01 clearfix">
									<img src="/resources/images/mem/fine01.png">
									<p>${xinshou.htype}</p>
								</div>
								<div class="fine_quality_r02 clearfix">
									<img src="/resources/images/mem/fine02.png">
									<p>${xinshou.address}</p>
								</div>
							</div>
							
							<div class="fine_quality_r00">
								<div class="fine_quality_r01 clearfix">
									<img src="/resources/images/mem/fine03.png">
									<p><fmt:formatNumber value="${xinshou.exprice/100}" pattern="#0.00" /></p>
								</div>
								<div class="fine_quality_r02 clearfix">
									<img src="/resources/images/mem/fine04.png">
									<p>${xinshou.area}</p>
								</div>
							</div>
							
							<p>${xinshou.memo}</p>
						</div>
					</div>
				</div>
				</c:forEach></c:if>
				<div>
					<div class="in_title">
						<h1>为您优选</h1>
						<h2></h2>
					</div>
					<div class="rec_project clearfix">
						<c:if test="${!empty loanList}"><c:forEach items="${loanList}" var="item">
						<div class="rect"><a href="/member/invest/detail/id_${item.lnid}">
							<div class="rec_project_img">
								<img src="${xinshou.coverurl}" width="383" height="255" onerror="this.src='/resources/images/tm.gif'">
								<div class="rec_project_img_tab"></div>
								<div class="rec_project_black">
									<p><fmt:formatNumber value="${item.allprice/1000000}" pattern="#0.00" /><b>万元</b><br>资产总额</p>
									<p><fmt:formatNumber value="${item.exprice/1000000}" pattern="#0.00" /><b>万元</b><br>预期交易额</p>
									<p>${item.loandead}<b>天</b><br>投资周期</p>
								</div>
							</div>
							<div class="rec_project_text">
								<div class="pro_round">
									<div>
										<div class="circleChart" style="position: relative;" data-tou="${item.loanmoney - item.avalmoney}" data-all="${item.loanmoney}" >
											<canvas class="circleChart_canvas" width="90" height="90" style="margin-left: auto; margin-right: auto; display: block;"></canvas>
											<p class="circleChart_text" style="position: absolute; line-height: 90px; top: 0px; width: 100%; margin: 0px; padding: 0px; text-align: center; font-size: 18px; font-weight: normal; font-family: sans-serif;">0%</p>
										</div>
									</div>
									<p>认购进度</p>
								</div>
								<div class="pro_round_text">
									<h1>${item.name}</h1>
									<p><span><img src="/resources/images/mem/dingwei.png"></span>${item.address}</p>
								</div>
							</div></a>
						</div>
						
						</c:forEach>
						
						<button id="loan_list_href" onclick="javascript:window.location.href='/member/invest/class_1';" style="cursor: pointer;clear: both;">查看所有项目</button>
						</c:if>
					</div>
				</div>
			</div>
    </div>
    
	<jsp:include page="/WEB-INF/pages/front/member/include/footer.jsp"/>
	<script src="/resources/js/mem/circleChart.js"></script>  
	<script type="text/javascript" src="/resources/js/swiper/swiper-3.4.2.jquery.min.js"></script>
	<script>
	$(function(){   
	    var mySwiper = new Swiper('.swiper-container', {
	        pagination: '.swiper-pagination',
	        nextButton: '.swiper-button-next',
	        prevButton: '.swiper-button-prev',
	        paginationClickable: true, 
	        autoplay: 5000,
	        effect : 'slide',
	        autoplayDisableOnInteraction: false,
	        loop:true
	    }); 
	    $('.fine_quality_l').mouseenter(function(){
			$('.fine_quality_l_detail').css('top','-315px');
		});
		$('.fine_quality_l').mouseleave(function(){
			$('.fine_quality_l_detail').css('top','0');
		});
		
	    $('.rect').mouseenter(function() {
	    	$(this).find(".rec_project_black").css('top', '0'); 
		}); 
		
		$('.rect').mouseleave(function() {
	    	$(this).find(".rec_project_black").css('top', '255px'); 
		}); 
		
		$(".pro_round").each(function(){
		
		    var tou = $(this).find("div.circleChart").attr("data-tou");
		    var all = $(this).find("div.circleChart").attr("data-all");
		    
		    var per = ((tou / all) * 100).toFixed(2);
		    
		    $(this).find("div.circleChart").circleChart({
				value:per,
				text: 0,
				onDraw: function(el, circle) {
				    //alert(circle.value);
					//circle.text(Math.round(circle.value) + "%");
					circle.text((circle.value).toFixed(2) + "%");
				}
		    });
		});
	
	}); 
	</script> 
</body>
</html>