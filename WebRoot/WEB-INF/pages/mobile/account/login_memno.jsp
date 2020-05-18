<%@ page language="java" pageEncoding="UTF-8"%>   
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>个人中心</title>
<meta name="viewport" content="width=device-width,initial-scale=1,maximuuser-scale=1,user-scalable=no">  
<link rel="stylesheet" href="/resources/style/swiper.min.css" />
<link rel="stylesheet" href="/resources/style/style.css"> 
</head>
<body >
<div class="wrap pb100">
  <div class="user-mact-infobox"> 
    <!-- 页头信息 -->
    <div class="user-header">
      <div class="user-main">
        <dl>
          <dt><span class="n-more"><img src="/resources/images/m/icon-more.png" alt=""></span><span class="n-wk" id="tosetting"><img src="/resources/images/m/user-tx.png" alt=""></span></dt>
          <dd>
            <p class="n-username" id="mobile">账户名称</p>
            <p class="n-vip-level"><span>会员等级</span></p>
          </dd>
        </dl>
      </div>
      <span class="n-msg" ><img src="/resources/images/m/icon-msg.png" alt=""><em>4</em></span> </div>
    <!-- /页头信息 --> 
    <!-- 总资产 -->
    <div class="user-mact-total">
      <div class="n-total" onclick="">
        <p class="n-t">可结算回报(元)</p>
        <p class="n-m"><big id="">--</big></p>
      </div>
      <div class="n-income">
        <dl id="" onclick="">
          <dt>总资产(元)</dt>
          <dd id="y">--</dd>
        </dl>
        <dl onclick="">
          <dt>累计期望回报(元)</dt>
          <dd id="">--</dd>
        </dl>
      </div>
    </div>
  </div> 
  <!-- 总资产 -->
  <div class="m-myinvest">
    <h2>我的账户</h2>
    <div class="swiper-container my-category swiper-container-horizontal swiper-container-free-mode">
      <ul class="swiper-wrapper banner-box" style="transform: translate3d(0px, 0px, 0px);">
        <li class="swiper-slide swiper-slide-next"> <a href=""> <span><img src="/resources/images/m/icon-month.png" alt=""></span>
          <p>月账户</p>
          </a> </li>
        <li class="swiper-slide"> <a href=""> <span><img src="/resources/images/m/icon-season.png" alt=""></span>
          <p>季账户</p>
          </a> </li>
        <li class="swiper-slide"> <a href=""> <span><img src="/resources/images/m/icon-year.png" alt=""></span>
          <p>年账户</p>
          </a> </li>
        <li class="swiper-slide"> <a href=""> <span><img src="/resources/images/m/icon-tg.png" alt=""></span>
          <p>特供</p>
          </a> </li>
      </ul>
    </div> 
  </div>
  <ul class="my-mact-main">
    <li> <a href="#"> <span class="n-left"><em class="n-icon"><img src="/resources/images/m/icon-qb.png" alt=""></em>我的钱包 </span> <span class="n-right"> <em class="n-jt-r"><img src="/resources/images/m/ico-right-sj.svg" alt=""></em></span> </a> </li>
    <li> <a href="#"> <span class="n-left"><em class="n-icon"><img src="/resources/images/m/icon-set.png" alt=""></em>账户设置 </span> <span class="n-right"> <em class="n-jt-r"><img src="/resources/images/m/ico-right-sj.svg" alt=""></em></span> </a> </li>
    <li> <a href="#"> <span class="n-left"><em class="n-icon"><img src="/resources/images/m/icon-hy.png" alt=""></em>邀请好友 </span> <span class="n-right"> <em class="n-jt-r"><img src="/resources/images/m/ico-right-sj.svg" alt=""></em></span> </a> </li>
    <li> <a href="#"> <span class="n-left"><em class="n-icon"><img src="/resources/images/m/icon-wt.png" alt=""></em>常见问题 </span> <span class="n-right"> <em class="n-jt-r"><img src="/resources/images/m/ico-right-sj.svg" alt=""></em></span> </a> </li>
    <li> <a href="#"> <span class="n-left"><em class="n-icon"><img src="/resources/images/m/icon-gd.png" alt=""></em>更多 </span> <span class="n-right"> <em class="n-jt-r"><img src="/resources/images/m/ico-right-sj.svg" alt=""></em></span> </a> </li>
    <li> <a href="#"> <span class="n-left"><em class="n-icon"><img src="/resources/images/m/icon-xx.png" alt=""></em>我的消息 </span> <span class="n-right"> <em class="n-jt-r"><img src="/resources/images/m/ico-right-sj.svg" alt=""></em></span> </a> </li>
  </ul>
  <div class="my-mact-btnbox"> <a href="javascript:redeem()" class="n-btn-zr">转让退出</a> <a href="#"  class="n-btn-tz">马上出借</a> </div>
  <footer class="foot-cyp">
    <p>期望回报并非平台承诺回报，市场有风险，投资需谨慎</p>
  </footer>
</div>
<!-- 底部-->
<div class="fixed"> 
 <a href="/weixin/home/" >
  <dl>
    <dt > <img src="/resources/images/m/home-1.png"> </dt>
    <dd>
      <p>首页 </p>
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
    <dt> <img src="/resources/images/m/my-2.png"> </dt>
    <dd>
      <p style="color:#ff8762;">我的</p>
    </dd>
  </dl>
  </a> 
</div> 
<!-- 弹出-->
<div id="popdiv" class="window-layer" style="display: none;">
  <div class="wealth"><img src="/resources/images/m/wealth.png"></div>
  <div class="btn-b centerdiv ">
    <input name="" type="button" class="btn" value="开始挣钱" onclick="javascript:window.location.href='/weixin/account/login/tophone/'">
  </div>
</div>

<script src="/resources/js/rem.js"></script>
<script  src="/resources/js/jquery-1.11.0.min.js"></script> 
<script type="text/javascript" src="/resources/js/layer/mobile/layer.js"></script> 
<script>   
    $(function(){   
    	layer.open({
		 	type:1,
		    content: $('#popdiv').html()
	    });
    });   
  </script>
</body>
</html>