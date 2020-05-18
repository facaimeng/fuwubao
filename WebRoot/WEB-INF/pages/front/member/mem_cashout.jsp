<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
			     	<li id="assetAllNav" class="mgl0 "><a href="/member/found/logs/" ><i class="leftNavList_4"></i>资金记录</a></li>
			     	<li id="assetAllNav" class="mgl0 "><a href="/member/found/cards/"><i class="leftNavList_9"></i>我要绑卡</a></li>
			        <li id="assetAllNav" class="mgl0 "><a href="/member/found/recharge/" ><i class="leftNavList_7"></i>我要充值</a></li>
			        <li id="assetAllNav" class="mgl0 mgl0yes"><a href="/member/found/cashout/" class="cur"><i class="leftNavList_8"></i>我要提现</a></li> 
			        <li id="assetAllNav" class="mgl0 "><a href="/member/fss/queryfss/"><i class="leftNavList_10"></i>我的生利宝</a></li>
			     </ul>
			</div>
			 
	        <div class="investRight fl">
	       	<h2>我要提现</h2>
	           <div class="investCon grzx_wycz" style="clear: both;"> 
	           	<ul>
	               	<li>
	                   	<em>提现金额:</em>
	                       <div>
	                       	   <span><input type="text" id="transAmt" placeholder="请输入取现金额" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}">&nbsp;&nbsp;&nbsp;元</span>
	                           <p>可用余额：<font style="color:#ec5801;">¥<fmt:formatNumber value="${avamoney/100}" pattern="#0.00" /></font></p>
	                           <input type="hidden" id="old_avamoney" name="avamoney" value="${avamoney}" />
	                       </div>
	                   </li>
	                   <li >
	                   	   <em style="margin-top:0px;">到账渠道:</em>
	                       <div class="cashOut_box" style="color:#666;font-size:14px;margin-top: 2px;"> 
					          <input type="checkbox" value="GENERAL" checked="checked"/> 一般取现   
					          <!-- <input type="checkbox" value="FAST"/> 快速取现    -->
					          <input type="checkbox" value="IMMEDIATE"  style="margin-left: 10px;"/> 即时取现
					          <input type="hidden" name="temp_cashChl" value="GENERAL"/>
	                       </div>
	                   </li>
	                   <li>
	                   	<em style="margin-top:0px;"> </em>
	                       <div>
	                       	<a href="javascript:;" onclick="cashOut();">我要提现</a>
	                       </div>
	                   </li>
	               </ul> 
	               <div class="manage-bank-at">
	               	<h4>温馨提示</h4>
	                   <p>1.根据监管部门指导意见，自2016年02月01日起，用户当日充值资金需要到次日才能提现。</p>
	                   <p>2.当日可提现金额=账户可用余额-当日充值金额。</p>
	                   <p>3.即时取现：有效时间为工作日（周一至周五）8:00-18:00，18点后发起快速和即时取现将默认为普通取现，下个工作日到账。</p>
	                   <p>4.如果您在提现过程中遇到任何问题，请咨询客服人员或拨打客服电话400 666 9606。</p>
	               </div> 
	           </div>
	       </div>
	    </div>
	    
    </div> 
</div>
<!--页脚-->
<div class="shadow2 hidden"></div>
<jsp:include page="/WEB-INF/pages/front/member/include/footer.jsp"/>  
<script type="text/javascript" src="/resources/js/validate.utils.js"></script>
<script src="/resources/js/layer/layer.js"></script>

<script type="text/javascript">
   $(function () {
       //到账渠道
       var fanxiBox = $(".cashOut_box input:checkbox");
       //fanxiBox.attr("disabled","disabled");
       
       fanxiBox.click(function () {
          if(this.checked || this.checked=='checked'){
              
              fanxiBox.removeAttr("checked");
              //这里需注意jquery1.6以后必须用prop()方法
              $(this).prop("checked", true);
              
              //执行对应的操作
              var cashChl = $(this).val(); 
              $("input[name='temp_cashChl']").val(cashChl);
          }
       });
       
  });
  
	    
    function cashOut(){
        
        var transAmt = $("#transAmt").val();
        var old_avamoney = $("#old_avamoney").val();
		
		if(!validateRules.isInteger1(transAmt)){
		    layer.msg('提现金额格式错误!');   
		    return false;
		}

		if(parseInt(transAmt)*100 > old_avamoney){
		    layer.msg('提现金额大于可用余额!');   
		    return false;
		}

	    layer.confirm('请在新开的页面完成提现', {
       	   title:"提现完成前请不要关闭此窗口",
		   btn: ['已完成提现','提现遇到问题']
		}, function(){ 
		   window.location.href='/member/center/';
		}, function(){
		   layer.msg('去到帮助页');
		});

        var cashChl = $("input[name='temp_cashChl']").val();
        //alert("方式:" + cashChl);
		var param = {};
		param.transAmt = transAmt;
		param.cashChl = cashChl;
		
		openPostWindow('/member/found/do_cashout', 'Rechange', param);
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