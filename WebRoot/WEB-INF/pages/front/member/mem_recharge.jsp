<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
	<title>个人中心-我的资产-账户总览</title>
	<link rel="stylesheet" type="text/css" href="/resources/style/mem/center.css">
	<link rel="stylesheet" type="text/css" href="/resources/style/mem/center_reset.css">
</head>

<body class="iCenter assetAll assetAllNo mgt30">
	<jsp:include page="/WEB-INF/pages/front/member/include/center_header.jsp" />
	<div class="wrap">
		<div class="mg-auto overflow area clearfix">
			<!-- middle_menu -->
			<div class="listNav mgb20">
				<ul class="clearfix nav bd">
					<li id="persistNav"><a href="/member/account/overview/">我的账户</a></li>
					<li id="dealNav"><a href="/member/asset/orders/">我的资产</a></li>
					<li id="accountNav" class="current"><a href="/member/found/logs/">我的资金</a></li>
					<li class="bg" style="width: 88px; left: 0px;"></li>
				</ul>
			</div>

			<div class="clearfix personCon mgb100">
				<!--页面左侧-->
				<div class="investLeft fl">
					<ul class="leftNavList">
						<li id="assetAllNav" class="mgl0 "><a href="/member/found/logs/"><i class="leftNavList_4"></i>资金记录</a></li>
						<li id="assetAllNav" class="mgl0 "><a href="/member/found/cards/"><i class="leftNavList_9"></i>我要绑卡</a></li>
						<li id="assetAllNav" class="mgl0 mgl0yes"><a href="/member/found/recharge/" class="cur"><i class="leftNavList_7"></i>我要充值</a></li>
						<li id="assetAllNav" class="mgl0 "><a href="/member/found/cashout/"><i class="leftNavList_8"></i>我要提现</a></li>
						<li id="assetAllNav" class="mgl0 "><a href="/member/fss/queryfss/"><i class="leftNavList_10"></i>我的生利宝</a></li>
					</ul>
				</div>
				<!--页面右侧-->
				<div class="investRight fl">
					<h2>我要充值</h2>
					<div class="investCon grzx_wycz"> 
						<ul>
							<li>
							    <em>充值金额:</em>
								<div>
									<span>
									<input type="text" id="transAmt" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}">&nbsp;&nbsp;&nbsp;元</span>
									<p>可用余额：<font style="color:#ec5801;">¥<fmt:formatNumber value="${avamoney/100}" pattern="#0.00" /><font></p>
								</div>
						    </li>
							<li>
							    <em style="margin-top:0px;"> </em>
								<div>
									<a href="javascript:;" onclick="rechange();">我要充值</a>
								</div>
							</li>
						</ul>  
						<div class="manage-bank-at">
							<h4>温馨提示</h4>
							<p>1.充值操作支持国内大部分银行的网上银行，只要您开通了网上银行的账户即可充值。</p>
							<p>2.充值过程中，请勿关闭浏览器窗口，操作完成后，浏览器会自动跳转返回到网站界面。</p>
							<p>3.如果您在充值过程中遇到任何问题，请咨询客服人员或拨打客服电话400 666 9606。</p>
						</div> 
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--页脚-->
	<div class="shadow2 hidden"></div>
	<jsp:include page="/WEB-INF/pages/front/member/include/footer.jsp" />
	
	<script type="text/javascript" src="/resources/js/validate.utils.js"></script>
    <script src="/resources/js/layer/layer.js"></script>
    
    <script type="text/javascript">
	    
	    function rechange(){
	        var transAmt = $("#transAmt").val();
			
			if(!validateRules.isInteger1(transAmt)){
			    layer.msg('充值金额格式错误!');   
			    return false;
			}
			
		    layer.confirm('请在新开的页面完成充值', { 
	       	   title:"充值完成前请不要关闭此窗口",
			   btn: ['已完成充值','充值遇到问题']
			}, function(){ 
			   window.location.href='/member/center/';
			}, function(){
			   layer.msg('去到帮助页');
			});

			var param = {};
			param.transAmt = transAmt;
			param.usrCustId = 5000;
			
			openPostWindow('/member/found/do_recharge', 'Rechange', param);
		}
		
		
		/**
		 * window.open模拟表单提交
		 * @param url 为请求地址
		 * @param name为form表单的target的name(可以随意写)
		 * @param data1为需要请求的数据
		 */
		function openPostWindow(url, name, dataJson){

		    var tempForm = document.createElement("form");//创建form表单，以下数form表单的各种参数
		    tempForm.id = "tempForm1";
		    tempForm.method = "post";
		    tempForm.action = url;
		    tempForm.target=name;
  
		    for(var p in dataJson){//遍历json对象的每个key/value对,p为key
			    //alert(p + " " + );
		
			    var hideInput = document.createElement("input");//创建标签 <input></input> 标签 然后设定属性，最后追加为 form标签的子标签
			    hideInput.type = "hidden";
			    hideInput.name = p;
			    hideInput.value = dataJson[p];
			    
			    tempForm.appendChild(hideInput);
		    }
		   
		    if(document.all){
		        tempForm.attachEvent("onsubmit",function(){});//IE
		    }else{
		        var subObj = tempForm.addEventListener("submit",function(){},false);//firefox
		    }
		    
		    document.body.appendChild(tempForm);
		    if(document.all){
		        tempForm.fireEvent("onsubmit");
		    }else{
		        tempForm.dispatchEvent(new Event("submit"));
		    }
		   
		    tempForm.submit();//提交POST请求
		    document.body.removeChild(tempForm);//删除整个form标签
		}
	</script>
</body>
</html>