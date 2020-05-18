<%@ page language="java" pageEncoding="UTF-8"%>
<div class="wrap header">
	<div class="mg-auto area clearfix">
    	<div class="headNav clearfix" style="font-size: 14px;">
        	<ul class="headNavLeft fl clearfix">
                <li class="first"><a href="/" target="_blank">融银普惠</a></li>
            </ul>   
            <div class="headNavRight fr userInformation clearfix">
            	<p class="fl">${memberVO.surname}${memberVO.sex.name}
            	 ， 欢迎您！<a href="javascript:;" id="logout">[退出]</a></p>
                <ul class="clearfix fl"> 
                    <li class="help"><a href="/faq/" target="_blank">帮助中心</a></li>
                </ul>
            </div>  
        </div>
  	</div>
</div> 
<!--banner-->
<div class="wrap banner">
    <div class="mg-auto area clearfix bannerCon">
    	<div class="clearfix pdt10">
            <div class="Logo fl">
                <img src="/resources/images/mem/logo.png" alt="融银普惠" title="融银普惠">
                <h1>融银普惠</h1>
            </div>
            <div class="listNav mgb30 fr" id="cssmenu">
            	<ul class="clearfix nav">
            		<li id="mainIndexNav"><a href="/recommend/">首页</a></li>
                    <li id="mainProductNav"><a href="/member/invest/">在线产品</a></li> 
                    <li id="mainSafeNav"><a href="/member/security/">安全保障</a></li> 
                    <li id="mainAssetNav"  class="current"><a href="/member/center/">我的账户</a></li> 
                    <li class="bg" style="width: 88px; left: 399.933px;"></li>
                </ul>
       		</div>
        </div>
        <div class="icenter_box clearfix">
        	<div class="imgbox">
        		<div class="imgbox_con"> 
        			<img src="/resources/images/mem/head_bg1.png"> <!-- 
                	<div class="cover_bg"><span><a href="javascript:void(0);">编辑头像</a></span></div> -->
                </div>
           </div> 
           <div class="itext">
              <div class="itext_box">
               <div class="itext_box_integral fl ">
               <div class="clearfix">
                  <h2 class="assset_h2">${memberVO.surname}${memberVO.sex.name}</h2> 
  				  <span class="level level1 orangered"></span>
                  <span class="text01">等级:象牙</span> 
			   </div>
					<div class="clearfix">
					  <span class="text02">平台账号: ${memberVO.phoneLable} </span>
	                  <span class="text02">登录汇付: <a href="/member/account/huifu/login/" class="orangered" target="_blank">[立即前往]</a></span>
				    </div><!--  
				  <div class="clearfix">
                    <span class="text03">风险测评:
	                  <input type="hidden" id="faccountpkid_cf" value="1">
                      <input type="hidden" id="faccountpkid_zx" value="">
	                  <input type="hidden" id="customerType" value="1">
	                  <a href="javascript:riskCk();" class="orangered">[立即测评] </a>
	           		  </span>
				  </div>-->
				  <div class="clearfix" style="margin-top:10px;">
                    <span class="text02">查看历史数据:<a href="http://www.vxiaodai.com/login/login" class="orangered" target="_blank">[立即前往]</a></span> 
				  </div>

                </div>
                <div class="integral_div fr">
                	<p class="integral_p1" style="color:#ec5801;cursor:pointer;" ><u>积分商城</u></p> 
				</div>
              </div>
            </div>
        </div>
   </div>
</div>