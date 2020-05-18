<%@ page language="java" pageEncoding="UTF-8"%> 
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>投资</title>
	<meta name="viewport" content="width=device-width,initial-scale=1,maximuuser-scale=1,user-scalable=no">  
	<link rel="stylesheet" href="/resources/style/style.css">
</head>

<body>
<div class="wrap pb100">
  <div class="change_content">
    <ul class="tabs" id="type">
      <li id="M" class="">月账户</li>
      <li id="Q" class="on">季账户</li>
      <li id="Y">年账户</li>
    </ul>
    <div class="tabs_content"> 
      <!--月账户-->
      <div class="part2 part" id="Mdiv" style="display: none;">
        <div class="title">
          <h1>月账户</h1>
          <span>网贷</span> </div>
        <div class="pics">
          <p class="line1">期望年化回报率</p>
          <p class="line2">8.5<span>%</span></p>
          <p class="check" >查看详情<span></span></p>
          <input type="hidden" value="M180319154413958">
        </div>
        <div class="couple">
          <p class="blank"> </p>
        </div>
        <p class="word">期望年化回报率<b>4.5%</b>起，逐月涨<b>0.5%</b>，最高<b>8.5%</b></p>
        <p class="word">每月可转让<b>1</b>次</p>
      </div>
      <!--季账户-->
      <div class="part3 part" style="" id="Qdiv">
        <div class="title">
          <h1>季账户-6个月</h1>
          <span>网贷</span> </div>
        <div class="pics">
          <p class="line1">期望年化回报率</p>
          <p class="line2">7.1<span>%</span></p>
          <p class="check" onclick="investDetail(this)" data-id="1">查看详情<span></span></p>
          <input type="hidden" value="Q6-180309113520025">
        </div>
        <div class="couple">
          <p class="blank"></p>
        </div>
        <p class="word">封闭期<b>180</b>天，结束后转让或续投</p>
        <ul class="time_tabs" id="quarter">
          <li id="Q3"><span class="span1"></span>3个月</li>
          <li class="current" id="Q6"><span class="span2"></span>6个月</li>
          <li id="Q9"><span class="span3"></span>9个月</li>
        </ul>
      </div>
      <!--年账户-->
      <div class="part4 part" style="display: none;" id="Ydiv">
        <div class="title">
          <h1>年账户-1年</h1>
          <span>网贷</span> </div>
        <div class="pics">
          <p class="line1">期望年化回报率</p>
          <p class="line2">8.5<span>%</span></p>
          <p class="check" onclick="investDetail(this)">查看详情<span></span></p>
          <input type="hidden" value="Y12-180313114114193">
        </div>
        <div class="couple">
          <p class="blank"> </p>
        </div>
        <p class="word">封闭期<b>365</b>天，结束后转让或续投</p>
        <ul class="time_tabs" id="year">
          <li class="current" id="Y12"><span class="span1"><b></b></span>1年</li>
          <li id="Y24"><span class="span2"></span>2年</li>
          <li id="Y36"><span class="span3"></span>3年</li>
          <li id="Y48"><span class="span4"></span>4年</li>
        </ul>
      </div>
    </div>
  </div>
  <div id="investButton" class="buttons">
  <!--  <input type="hidden" value="Q6-180309113520025" id="productCode">
    <input type="hidden" value="Q6" id="productType">
    <input type="hidden" value="180309113520025-178" id="productPackageCode">-->
    <p class="p1">马上出借</p>
    <p class="p2">剩余332785.49元</p>
  </div>
  <footer class="foot-cyp">
    <p>汇付为您提供网贷信息中介服务</br>
      期望回报并非平台承诺回报，市场有风险，投资需谨慎</p>
    <p>融银由重庆***科技有限公司全权运营管理</br>
      渝ICP01325665号-6</p>
  </footer>
</div>
<!-- 底部-->
<div class="fixed"> 
 <a href="/weixin/home/" >
  <dl>
    <dt > <img src="/resources/images/m/home-1.png"> </dt>
    <dd>
      <p >首页 </p>
    </dd>
  </dl>
  </a> 
  <a href="/weixin/invest/" >
  <dl>
    <dt> <img src="/resources/images/m/tz-2.png"> </dt>
    <dd>
      <p style="color:#ff8762;">投资</p>
    </dd>
  </dl>
  </a>
   <a href="/weixin/member/">
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
<script type="text/javascript" src="/resources/js/layer/mobile/layer.js"></script> 
<script>
	$(function(){   
		//init();
    });
    function init(){   
    	layer.open({
		 	type:2 
		    //shadeClose:false
	    }); 
    	
    } 
    function investDetail(obj){
		var id = $(obj).attr("data-id"); 
		window.location.href="/weixin/invest/detail/?id="+id;
	} 
</script>
</body>
</html>