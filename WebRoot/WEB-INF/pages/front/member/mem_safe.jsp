<%@ page language="java" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>个人中心-我的资产-账户总览</title> 
<link rel="stylesheet" type="text/css" href="/resources/style/mem/center.css">
<link rel="stylesheet" type="text/css" href="/resources/style/mem/center_reset.css">  
</head>
<body class="iCenter assetAll assetAllNo mgt30"> 
<jsp:include page="/WEB-INF/pages/front/member/include/center_header.jsp"/>   
<div class="wrap">
	<div class="mg-auto overflow area clearfix">
    	<!-- middle_menu -->
    	

<div class="listNav mgb20">
     <ul class="clearfix nav bd">
         <li id="persistNav" class="current"><a href="/member/account/overview/">我的账户</a></li> 
         <li id="dealNav"><a href="/member/asset/orders/">我的资产</a></li>
         <li id="accountNav"><a href="/member/found/logs/">我的资金</a></li>
         <li class="bg" style="width: 88px; left: 0px;"></li>
     </ul>
 </div>
 
        <div class="clearfix personCon mgb100">
        	<!--页面左侧-->
        	
		<div class="investLeft fl">
		 	<ul class="leftNavList">
		     	<li id="assetAllNav" class="mgl0 "><a href="/member/account/overview/" ><i class="leftNavList_1"></i>账户总览</a></li>
		        <li id="assetAllNav" class="mgl0 mgl0yes"><a href="/member/account/security/" class="cur"><i class="leftNavList_2"></i>安全认证</a></li>
		        <li id="assetAllNav" class="mgl0"><a href="/member/account/invite/" ><i class="leftNavList_3"></i>推荐好友</a></li>
		     </ul>
		 </div>
            <!--页面右侧-->
         
            <div class="investRight fl">
            	<h2>安全认证</h2>
                <div class="investCon grzx_zhzn">
                	<!--环形图区块-->
                	<div class="">
                    	<img src="/resources/images/mem/lizsxa.png">	<span>手机认证</span> <small><i>${memberVO.phoneLable}</i><em style="display:none;">请输入您的手机号</em></small> <a>已认证</a>
                    </div> 
                     <div class="">
                    	<img src="/resources/images/mem/lizsxa_2.png">	<span>汇付认证</span> <small><i>&nbsp;</i><em style="display:none;">请输入您的手机号</em></small> <a>已认证</a>
                    </div> 
                    <div class="">
                    	<img src="/resources/images/mem/lizsxa_3.png">	<span>登录密码</span> <small><i style="display:none;">135449848***</i><em style="display:;"><h3>您的密码强度：中</h3><h4>互联网账号存在被盗风险，建议您定期更改密码以保护账户安全。</h4></em></small> <a class="grzx_zhzn_on" href="#">修改密码</a>
                    </div>
                   
                	<!--条状图区块-->
                </div>
            </div>
            
            
            
        </div>
      </div>
</div>

<!--页脚-->
<div class="shadow2 hidden"></div>
<jsp:include page="/WEB-INF/pages/front/member/include/footer.jsp"/>  
</body>
</html>