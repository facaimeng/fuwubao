<%@ page language="java" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>融银金服</title>
<meta name="viewport" content="width=device-width,initial-scale=1,maximuuser-scale=1,user-scalable=no"> 
<link rel="stylesheet" href="/resources/style/swiper.min.css" />
<link rel="stylesheet" href="/resources/style/style.css">
</head>
<body ><!-- 
<div id="hdiv" style="width:100%;height:100%;position: fixed; z-index:9900;background: #eef3fa;"></div> -->
<div class="wrap pb100">
  <div class="m-news" id="notice" style=""> <em><img src="/resources/images/m/adv.png" alt=""></em> 融银金服五一假期安排 </div>
  <span class="btn-close" style=""><img src="/resources/images/m/close-top.png" alt=""></span>
  <div class="swiper-container swiper-banner swiper-container-horizontal banner-container">
    <ul class="swiper-wrapper" style="transform: translate3d(-2250px, 0px, 0px); transition-duration: 300ms;"><!--
      <li class="swiper-slide swiper-slide-prev" data-swiper-slide-index="1" style="width: 750px;"><a href="#"><img src="/resources/images/m/banner-1.jpg" ></a></li>
      <li class="swiper-slide"><a href="#"><img src="/resources/images/m/banner-1.jpg" ></a></li>
      <li class="swiper-slide"><a href="#"><img src="/resources/images/m/banner-1.jpg" ></a></li>  -->
    </ul>
    <!-- Add Pagination -->
    <div class="swiper-pagination banner-container-pagination"></div>
  </div> 
  <!-- 总资产 -->
  <div>
    <ul class="m-nav">
      <li onclick="')"><em><img src="/resources/images/m/icon-month-1.png"  ></em><span>新手指引</span></li>
      <li onclick="jumpTo('/weixin/user/coupons.html')"><em><img src="/resources/images/m/icon-season-1.png"></em><span>安全保障</span></li>
      <li onclick=""><em><img src="/resources/images/m/icon-year-1.png"></em><span>其他栏目</span></li>
      <li onclick=""><em><img src="/resources/images/m/icon-tg-1.png"></em><span>其他栏目</span></li>
    </ul>
  </div>
  
  <!-- Swiper -->
  <div class="swiper-container m-pro swiper-contnet-container">
    <div class="swiper-wrapper"><!--
      <div class="swiper-slide n-pro-list " style="margin-right: 0.14rem;">
        <div class="n-pro-main">
          <div class="n-top"> <span>新手专享21天</span><em class="icon-wd">网贷</em> <a href="">详情<em><img src="/resources/images/m/ico-right-sj.svg" alt=""></em></a> </div>
          <div class="n-main">
            <div class="n-box">
              <p class="n-mag">期望年化回报率</p>
              <h2 class="n-int-rate">12.0<em>%</em></h2>
              <p class="n-magb"> </p>
              <div class="n-qual"> <span>封闭期21天</span> <span>最低100元起</span> <span>上限3万</span> </div>
            </div>
            <div class="n-btn"> <a href="javascript:void(0);" onclick="investDetail(this)" data-id="1"> <big>马上加入</big> </a> </div>
          </div>
        </div>
      </div>
      <div class="swiper-slide n-pro-list " style="margin-right: 0.14rem;">
        <div class="n-pro-main">
          <div class="n-top"> <span>限时特供366天</span><em class="icon-wd">网贷</em> <a href="">详情<em><img src="/resources/images/m/ico-right-sj.svg" alt=""></em></a> </div>
          <div class="n-main">
            <div class="n-box">
              <p class="n-mag">期望年化回报率</p>
              <h2 class="n-int-rate">8.5<em>%</em>+1.0<em>%</em></h2>
              <p class="n-magb"> </p>
              <div class="n-qual"> <span>封闭期366天</span> <span>最低20000元起</span> <span>上限500万</span> </div>
            </div>
            <div class="n-btn"> <a href="javascript:void(0);" onclick="investDetail(this)" data-id="1"> <big>马上加入</big> </a> </div>
          </div>
        </div>
      </div>
      <div class="swiper-slide n-pro-list " style="margin-right: 0.14rem;">
        <div class="n-pro-main">
          <div class="n-top"> <span>月账户</span><em class="icon-wd">网贷</em> <a href="">详情<em><img src="/resources/images/m/ico-right-sj.svg" alt=""></em></a> </div>
          <div class="n-main">
            <div class="n-box">
              <p class="n-mag">期望年化回报率</p>
              <h2 class="n-int-rate">8.5<em>%</em></h2>
              <div class="n-moth"><em>4.5%</em>起，逐月<em>+0.5%</em>，最高<em>8.5%</em><br>
                每月可申请转让1次</div>
            </div>
            <div class="n-btn"> <a href="javascript:void(0);" onclick="investDetail(this)" data-id="1"> <big>马上加入</big> </a> </div>
          </div>
        </div>
      </div>
      <div class="swiper-slide n-pro-list " style="margin-right: 0.14rem;">
        <div class="n-pro-main">
          <div class="n-top"> <span>新手专享90天</span><em class="icon-wd">网贷</em> <a href="">详情<em><img src="/resources/images/m/ico-right-sj.svg" alt=""></em></a> </div>
          <div class="n-main">
            <div class="n-box">
              <p class="n-mag">期望年化回报率</p>
              <h2 class="n-int-rate">6.0<em>%</em>+2.0<em>%</em></h2>
              <p class="n-magb"> </p>
              <div class="n-qual"> <span>封闭期90天</span> <span>最低100元起</span> <span>上限2万</span> </div>
            </div>
            <div class="n-btn"> <a href="javascript:void(0);" onclick="investDetail(this)" data-id="1"> <big>马上加入</big> </a> </div>
          </div>
        </div>
      </div>
      <div class="swiper-slide n-pro-list " style="margin-right: 0.14rem;">
        <div class="n-pro-main">
          <div class="n-top"> <span>限时特供180天</span><em class="icon-wd">网贷</em> <a href="">详情<em><img src="/resources/images/m/ico-right-sj.svg" alt=""></em></a> </div>
          <div class="n-main">
            <div class="n-box">
              <p class="n-mag">期望年化回报率</p>
              <h2 class="n-int-rate">7.1<em>%</em>+0.5<em>%</em></h2>
              <p class="n-magb"> </p>
              <div class="n-qual"> <span>封闭期180天</span> <span>最低1000元起</span> <span>上限200万</span> </div>
            </div>
            <div class="n-btn"> <a href="javascript:void(0);" onclick="investDetail(this)" data-id="1"> <big>马上加入</big> </a> </div>
          </div>
        </div>
      </div>
      <div class="swiper-slide n-pro-list " style="margin-right: 0.14rem;">
        <div class="n-pro-main">
          <div class="n-top"> <span>新手专享365天</span><em class="icon-wd">网贷</em> <a href="#">详情<em><img src="/resources/images/m/ico-right-sj.svg" alt=""></em></a> </div>
          <div class="n-main">
            <div class="n-box">
              <p class="n-mag">期望年化回报率</p>
              <h2 class="n-int-rate">8.5<em>%</em>+1.0<em>%</em></h2>
              <p class="n-magb"> </p>
              <div class="n-qual"> <span>封闭期365天</span> <span>最低100元起</span> <span>上限1万</span> </div>
            </div>
            <div class="n-btn"> <a href="javascript:void(0);" onclick="investDetail(this)" data-id="1"> <big>马上加入</big><small>12233</small> </a> </div>
          </div>
        </div>
      </div>
      <div class="swiper-slide n-pro-list " style="margin-right: 0.14rem;">
        <div class="n-pro-main">
          <div class="n-top"> <span>季账户-6个月</span><em class="icon-wd">网贷</em> <a href="#">详情<em><img src="/resources/images/m/ico-right-sj.svg" alt=""></em></a> </div>
          <div class="n-main">
            <div class="n-box">
              <p class="n-mag">期望年化回报率</p>
              <h2 class="n-int-rate">7.1<em>%</em></h2>
              <p class="n-magb"> </p>
              <div class="n-qual"> <span>封闭期180天</span> <span>最低100元起</span> <span>出借无上限</span> </div>
            </div>
            <div class="n-btn"> <a href="javascript:void(0);" onclick="investDetail(this)" data-id="1"> <big>马上加入</big> </a> </div>
          </div>
        </div>
      </div>
      <div class="swiper-slide n-pro-list " style="margin-right: 0.14rem;">
        <div class="n-pro-main">
          <div class="n-top"> <span>年账户-1年</span><em class="icon-wd">网贷</em> <a href="#">详情<em><img src="/resources/images/m/ico-right-sj.svg" alt=""></em></a> </div>
          <div class="n-main">
            <div class="n-box">
              <p class="n-mag">期望年化回报率</p>
              <h2 class="n-int-rate">8.5<em>%</em></h2>
              <p class="n-magb"> </p>
              <div class="n-qual"> <span>封闭期365天</span> <span>最低100元起</span> <span>出借无上限</span> </div>
            </div>
            <div class="n-btn"> <a href="javascript:void(0);" onclick="investDetail(this)" data-id="1"> <big>马上加入</big> </a> </div>
          </div>
        </div>
      </div>
      <div class="swiper-slide n-pro-list "style="margin-right: 0.14rem;" >
        <div class="n-pro-main">
          <div class="n-top"> <span>季账户-3个月</span><em class="icon-wd">网贷</em> <a href="#">详情<em><img src="/resources/images/m/ico-right-sj.svg" alt=""></em></a> </div>
          <div class="n-main">
            <div class="n-box">
              <p class="n-mag">期望年化回报率</p>
              <h2 class="n-int-rate">6.0<em>%</em></h2>
              <p class="n-magb"> </p>
              <div class="n-qual"> <span>封闭期90天</span> <span>最低100元起</span> <span>出借无上限</span> </div>
            </div>
            <div class="n-btn"> <a href="javascript:void(0);" onclick="investDetail(this)" data-id="1"> <big>马上加入</big> </a> </div>
          </div>
        </div>
      </div>-->
    </div>
    <!-- Add Pagination -->
    <div class="swiper-pagination my-page"></div>
  </div>
   
 
  <div class="m-ad-box"> </div>
  <footer class="foot-cyp">
    <p>汇付为您提供网贷信息中介服务
      期望回报并非平台承诺回报，市场有风险，投资需谨慎</p>
    <p style="font-size:0.18rem;"> 融银由重庆***科技有限公司全权运营管理<br>
      渝ICP01325665号-6</p>
  </footer>
</div>
<!-- 底部-->
<div class="fixed"> 
 <a href="/weixin/home/" >
  <dl>
    <dt > <img src="/resources/images/m/home-2.png"> </dt>
    <dd>
      <p style="color:#ff8762;">首页 </p>
    </dd>
  </dl>
  </a> 
  <a href="/weixin/invest/" >
  <dl>
    <dt> <img src="/resources/images/m/tz-1.png"> </dt>
    <dd>
      <p>投资</p>
    </dd>
  </dl>
  </a>
   <a href="/weixin/member/center/">
  <dl>
    <dt> <img src="/resources/images/m/my-1.png"> </dt>
    <dd>
      <p >我的</p>
    </dd>
  </dl>
  </a> 
</div> 
<script src="/resources/js/rem.js"></script>
<script  src="/resources/js/jquery-1.11.0.min.js"></script>
<script  src="/resources/js/swiper-3.3.1.jquery.min.js"></script> 
<script type="text/javascript" src="/resources/js/layer/mobile/layer.js"></script> 

 <!-- Initialize Swiper --> 
  <script>   
    $(function(){  
    	//$("#hdiv").css({ opacity: .7 });  
    	layer.open({
			 	type:2 
			    //shadeClose:false
		}); 
	    init(); 
    });  
    function init(){   
    	//do ajax
    	/*
    	var swiper = new Swiper('.swiper-contnet-container', {
		    pagination: '.my-page',
			slidesPerView:'auto',
			centeredSlides: true,
			paginationClickable: true,
			loop:true,
			spaceBetween: 14
	    });
	    var swiper = new Swiper('.banner-container', {
			pagination: '.banner-container-pagination',
			paginationClickable: true,
			spaceBetween: 0,
			autoplay:3000,
			loop:true
	    });
	  	*/  
    }
    function investDetail(obj){ 
		var id = $(obj).attr("data-id"); 
		window.location.href="/weixin/invest/detail/?id="+id;
	} 
  </script>
</body>
</html>