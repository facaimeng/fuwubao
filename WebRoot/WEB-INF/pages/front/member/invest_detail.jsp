<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>在线产品-${loan.name}</title>
	<link rel="stylesheet" type="text/css" href="/resources/style/mem/index.css">
	<link rel="stylesheet" type="text/css" href="/resources/style/mem/reset.css">
	<style type="text/css">
	.tjxqtcx{width: 500px;height: 500px;background: #fff;overflow: hidden;}
	</style>
</head>

<body class="IndexPage mgt30">
<jsp:include page="/WEB-INF/pages/front/member/include/header.jsp"/>
<div class="wrap productNav">
	<div class="mg-auto area clearfix">
		<h1 class="fl logo"><img src="/resources/images/mem/logo2.png" title="融银普惠" alt="融银普惠"></h1>
		<div class="listNav mgb30 fr" id="cssmenu">
			<ul class="clearfix nav">
				<li id="mainAssetNav"><a href="/recommend/">首页</a></li>
                <li id="mainProductNav" class="current"><a href="/member/invest/">在线产品</a></li>
                <li id="mainSafeNav"><a href="/member/security/">安全保障</a></li>
                <li id="mainAssetNav"><a href="/member/center/">我的账户</a></li>
               	<li class="bg" style="width:86px; left: 103px;"></li>
			</ul>
		</div>
	</div>
</div>

<div class="tjxq_cot">
	<div class="tjxq_cot_div">
        	<!-- photos start -->
        	<div class="tjxq_cot_divimg">
            	<div class="f-s1">
                    <div class="slide">
                        <div class="slideWrap">
                            <div id="slideWrap">
                                <ul><c:if test="${!empty loan.projectImages}"><c:forEach items="${loan.projectImages}" var="pic">
                                    <li class="slide-img">
                                        <a href="#" target="_blank"><img src="${pic.furl}" width="492px" alt="${pic.name}"  onerror="this.src='/resources/images/tm.gif'"/></a>
                                    </li></c:forEach></c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <i class="icon-arr s-arr-pre tjxq_l" id="btnPrev"><img src="/resources/images/mem/arr_next.png"></i>
                    <i class="icon-arr s-arr-next tjxq_r" id="btnNext"><img src="/resources/images/mem/arr_pre.png"></i>
                </div>
            </div>
            <!-- photos end -->
            <div class="tjxq_cot_divrr">
            	<h3>${loan.name}<i>限购一次</i><!-- <i>限购一次</i> --></h3>
                <h4><img src="/resources/images/mem/diz.png"/>${loan.address}</h4>
                <ul>
                	<li>
                    	<span>
                        	<img src="/resources/images/mem/tj_1.png" />
                            <h5>房产总额</h5>
                        </span>
                        <small style="width:109px;">
                        	<i><fmt:formatNumber value="${loan.allprice/1000000}" pattern="#0.00" /></i>
                            <b>万元</b>
                        </small>
                    </li>
                    <li>
                    	<span>
                        	<img src="/resources/images/mem/tj_2.png" style="margin-left:10px;" />
                            <h5>市场平均售价</h5>
                        </span>
                        <small style="width:86px;">
                        	<i><fmt:formatNumber value="${loan.avgprice/1000000}" pattern="#0.00" /></i>
                            <b>万元</b>
                        </small>
                    </li>
                    <li>
                    	<span>
                        	<img src="/resources/images/mem/tj_3.png" style="margin-left:10px;" />
                            <h5>预期交易价格</h5>
                        </span>
                        <small style="width:86px;">
                        	<i><fmt:formatNumber value="${loan.exprice/1000000}" pattern="#0.00" /></i>
                            <b>万元</b>
                        </small>
                    </li>
                    <li>
                    	<span>
                        	<img src="/resources/images/mem/tj_4.png" style="margin-left:10px;" />
                            <h5>剩余可投金额</h5>
                        </span>
                        <small style="width:86px;">
                        	<i><fmt:formatNumber value="${loan.avalmoney/1000000}" pattern="#0.00" /></i>
                            <b>万元</b>
                        </small>
                    </li>
                    <li>
                    	<span>
                        	<img src="/resources/images/mem/tj_5.png" />
                            <h5 style="line-height:12px;">1000元份<br />预期利润</h5>
                        </span>
                        <small style="width:108px;">
                        	<i><fmt:formatNumber value="${loan.earnings/100}" pattern="#0.00" /></i>
                            <b>元</b>
                        </small>
                    </li>
                    <li>
                    	<span>
                        	<img src="/resources/images/mem/tj_6.png" />
                            <h5>体验周期</h5>
                        </span>
                        <small style="width:108px;">
                        	<i>${loan.loandead}</i>
                            <b>天</b>
                        </small>
                    </li>
                </ul>
                
                <c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
                <h1>
	                <span>起投金额：<fmt:formatNumber value="${loan.minimoney/100}" pattern="#0.00" />元</span>
	                <span>剩余时间： <c:if test="${nowDate-loan.startdate.time < 0}">即将开始</c:if>
	                    <c:if test="${nowDate-loan.startdate.time >= 0}">
	                    <font id="timer">&nbsp;</font>
	                    </c:if>
	                </span>
                </h1>
                
                <h2>已售： <fmt:formatNumber value="${((loan.loanmoney-loan.avalmoney)/loan.loanmoney )*100}" pattern="#0.00" />%</h2>
                <em>
                    <c:if test="${nowDate-loan.startdate.time >= 0}">
	                    <c:if test="${loan.avalmoney > 0 }"><a class="havelink" onclick="confirm_bay();" href="javascript:;">立即购买</a></c:if>
	                    <c:if test="${loan.avalmoney == 0 }"><a class="nolink" href="javascript:;">已售罄</a></c:if>
                    </c:if>
                    
                    <c:if test="${nowDate-loan.startdate.time < 0}"><a class="nolink" href="javascript:;">即将开始</a></c:if>
                </em>
                
            </div>
        </div>
        
        <!-- 
        <div class="tjqx_two">
        	<span>
            	<img src="/resources/images/mem/tjxq_1.png" />
                <h2>购买资产</h2>
                <h3>资产物权100%归属所有投资人</h3>
            </span>
            <span>
            	<img src="/resources/images/mem/tjxq_2.png" />
                <h2>资产满标</h2>
                <h3>资产物权100%归属所有投资人</h3>
            </span>
            <span>
            	<img src="/resources/images/mem/tjxq_3.png" />
                <h2>处置资产</h2>
                <h3>资产物权100%归属所有投资人</h3>
            </span>
        </div>-->
        
        <div class="tjqx_Three">
            <a href="javascript:;" onclick="dtabs(this,'01');" class="on">投资详情</a>
            <a href="javascript:;" onclick="dtabs(this,'02');">资产介绍</a>
            <a href="javascript:;" onclick="dtabs(this,'03');">购买记录</a>
            <!-- 
            <a href="javascript:;" onclick="dtabs(this,'04');" style="margin-right:0px;">常见问题</a>
            -->
        </div>
        
        <div class="tjqx_Three_xt"></div>
        <div class="tjqx_Three_c">
            <!-- 投资详情   -->
            <div id="tab01" class="tab">
        	<table>
            	<tr>
                	<td>资产总额</td><td style="color:#333"><fmt:formatNumber value="${loan.allprice/1000000}" pattern="#0.00" />万</td>
                </tr>
                
                <tr class="tjqx_Three_c_2">
                	<td>预计交易价格</td><td style="color:#333"><fmt:formatNumber value="${loan.exprice/1000000}" pattern="#0.00" />万</td>
                </tr>
                
                <tr>
                	<td>10万预计利润</td><td style="color:#333"><fmt:formatNumber value="${loan.earnings/100*100}" pattern="#0.00" />元</td>
                </tr>
                
                <tr class="tjqx_Three_c_2">
                	<td>回款时间</td><td style="color:#333">满标后${loan.loandead}天回款</td>
                </tr>
                
                <tr>
                	<td>收益方式</td><td style="color:#333">到期一次获取本金和利润</td>
                </tr>
                
                <tr class="tjqx_Three_c_2">
                	<td>权益转让</td><td style="color:#333">否</td>
                </tr>
                <c:if test="${!empty loan.projectAttchs}">
                <c:forEach var="attch" items="${loan.projectAttchs}">
                <tr>
                	<td><img src="/resources/images/mem/renz.png" style="float:left; margin-top:2px; margin-right:15px;">${attch.name}</td>
                	<td style="color:#333"><a href="javascript:;" onclick="showPDF('${attch.furl}');">【点击查看】</a></td>
                </tr>
                </c:forEach></c:if>
            </table>
            		
            </div>
            
            <!-- 资产介绍   -->
            <div id="tab02" class="tab" style="display:none;">
            <ul>
           		<li>
                	<h2>项目名称</h2>
                    <p><span>资产类别</span><b><c:if test="${loan.prtype eq 1}">车辆</c:if><c:if test="${loan.prtype eq 2}">房屋</c:if></b></p>
                    <p><span>资产所属</span><b>${loan.address}</b></p>
                    <p><span>资产类型</span><b>${loan.htype}</b></p>
                    <p><span>资产情况</span><b>${loan.area}</b></p>
                    <p><span>资产详情</span><b>${loan.decorate}</b></p>
                    <p><span>资产性质</span><b>${loan.protype}</b></p>
                    <p><span>资产总额</span><b><fmt:formatNumber value="${loan.allprice/1000000}" pattern="#0.00" />万</b></p>
                    <p><span>市场价值</span><b><fmt:formatNumber value="${loan.exprice/1000000}" pattern="#0.00" />万</b></p>
                    <p><span>市场平均售价</span><b><fmt:formatNumber value="${loan.avgprice/1000000}" pattern="#0.00" />万</b></p>
                </li> 
                <li >
                	<h2>市场售价参考<b style="font-weight:normal; font-size:12px; color:#999; padding-left:20px;">( 同一小区相似户型房源信息，价格仅供参考！)</b></h2>
               </li>
               
               <c:if test="${loan.prtype eq 2}">
               <li>
               		<h2>位置周边</h2>
               		<div id="dituContent" style="margin-left:70px; width:960px;height:330px;">
                		<img src="/images/map.jpg" width="980" height="330" />
                	</div>
               </li>
               </c:if>
               
            </ul>
            </div>
            
            <!-- 购买记录   -->
            <div id="tab03" class="tab" style="display:none;">
            <table>
            	<tr>
                	<td>投资人</td><td>投资金额</td><td>投资时间</td>
                </tr>
                <c:if test="${!empty bidLogs}"><c:forEach items="${bidLogs}" var="item">
                <tr class="tjqx_Three_c_2">
                	<td>${item.phone}</td>
                	<td><fmt:formatNumber value="${item.bidmoney/100}" pattern="#0.00" /></td>
                	<td><fmt:formatDate value="${item.bidtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr></c:forEach></c:if>
            </table>
            </div>
            <!-- 常见问题   -->
            <div id="tab04" class="tab" style="display:none;">
            <ul class="tjqxyl_2">
           		<li>
                	<h2>1. 购买成功的房子产权归谁?</h2>
                    <p>房的产权归属于平台的投资人，对于已完成购买的房子，投资人与房主直接签订《资产交易协议》，与平台签订《项目委托投资协议》等合同来确保房子产权归属于聚融360投资人。</p>
                </li>
                <li style="background:none;">
                	<h2>1. 购买成功的房子产权归谁?</h2>
                    <p>房的产权归属于平台的投资人，对于已完成购买的房子，投资人与房主直接签订《资产交易协议》，与平台签订《项目委托投资协议》等合同来确保房子产权归属于聚融360投资人。</p>
                </li>
                <li>
                	<h2>1. 购买成功的房子产权归谁?</h2>
                    <p>房的产权归属于平台的投资人，对于已完成购买的房子，投资人与房主直接签订《资产交易协议》，与平台签订《项目委托投资协议》等合同来确保房子产权归属于聚融360投资人。</p>
                </li>
             </ul>
            </div>
        </div>

</div>
<jsp:include page="/WEB-INF/pages/front/member/include/footer.jsp"/>



<!-- 购买确认框   -->
<script id="formbox" type="text/html">
   <div class="tjxqtcx" style="height:515px;">
       <!-- token 标记 -->
       <input type="hidden" name="token" value="<%=session.getAttribute("token") %>">

       <!-- 标的ID -->
       <input type="hidden" name="lnid" id="lnid" value="${loan.lnid}" />
       
       <!-- 用户可用金额  -->
       <input type="hidden" name="avlmoney" id="avlmoney" value="${avlmoney}" />
      
       <!-- 下单人汇付ID -->
       <input type="hidden" name="merCustId" id="merCustId" value="6000060008448781" />
             
       <!-- 标的年化收益率 -->
       <input type="hidden" name="yearate" id="yearate" value="${loan.yearate}" />
       
       <!-- 标的时长 -->
       <input type="hidden" name="loandead" id="loandead" value="${loan.loandead}" />
       
       <!-- 起投金额 -->
       <input type="hidden" name="minimoney" id="minimoney" value="${loan.minimoney}" />

        
       <h2><span>确认购买</span><span style="float:right;width:30px;padding-right:15px;color:#333;cursor: pointer;" onclick="closeLayer0();">x</span></h2>
       <table>
           <tr>
               <td>处置周期：</td><td style=" color:#999;">${loan.loandead}天</td>
           </tr>
           <tr>
               <td>1000.0元/份预期利润：</td><td style=" color:#999;"><fmt:formatNumber value="${loan.earnings/100}" pattern="#0.00" />元</td>
           </tr>
           <tr>
               <td>&nbsp;</td><td style=" color:#999;">&nbsp;</td>
           </tr>
           <tr>
               <td>回款日期</td><td style="color:#999;">购买后${loan.loandead}天</td>
           </tr>
           <tr>
               <td>购买金额：</td>
               <td style=" color:#999; width:300px;">
                    <!-- 投资金额   -->
                   <input id="paymoney_input" type="text" placeholder="请输入" onfocus="closeMoneyTips();" name="transAmt" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'');calInterest(this.value);}else{this.value=this.value.replace(/\D/g,'');calInterest(this.value);}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}"> &nbsp;&nbsp;&nbsp;元
               </td>
           </tr>
           <tr>
               <td>预期收益：</td><td style="color:#999;"><span id="yuqishouyi">0.00</span>元</td>
           </tr>
           <tr>
               <td>账户余额：</td><td style="color:#999;"><span id="zhanghuyue">&nbsp;</span>元<a href="/member/invest" class="tjxqcz" target="_blank">充值</a></td>
           </tr>
           <tr>
               <td style="line-height:32px; width:450px;float:left;" colspan="2">
                   <span style="float:left;width:80px;"><input type="checkbox" id="agreeBox" checked="checked" style="width:15px; margin-right:8px; float:left; margin-top:4px;"/>同意</span>
                   <span style="float:left;width:100px;"><a href="javascript:;" onclick="showPDF('jiaoyi.pdf')" style="border:none; color:blue;">资产交易协议</a></span>
                   <c:if test="${loan.prtype eq 2}">
                     <span style="float:left;width:130px;"><a href="javascript:;" onclick="showPDF('weituo.pdf')" style="border:none; color:blue;">项目委托投资协议</a></span>
                   </c:if>
               </td>
           </tr>
           <tr style="height:20px;line-height:12px;margin:0px;padding:0px;">
               <td>&nbsp;</td>
               <td style="color:#999; width:300px;">
                   <span id="buzhu" style="display:none;">余额不足，需<font id="xuyao">0.00</font>元</span>
               </td>
           </tr>

       </table>
       <a class="goumaimai" href="javascript:;">购买</a>
</div>
</script>

<script id="pdf_view" type="text/html">
<div style="width:800px;height:600px;overflow: hidden;">
<iframe src="" id="pdfContainer" name="pdfContainer" style="height:100%;width:100%;margin:1px;" scrolling="no"></iframe>
</div>
</script>

<script type="text/javascript" src="/resources/js/jquery.jcarousellite.min.js"></script>
<script type="text/javascript" src="/resources/js/mem/main.js"></script>
<script type="text/javascript" src="/resources/js/cbai.commonutil.js"></script>
<script type="text/javascript" src="/resources/layer/layer.js"></script>


<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.4&services=true"></script>
<script src="/resources/js/bmap.js" type="text/javascript"></script>

<script type="text/javascript">
    function countdown (){
        
        var enddateStr = '${loan.enddate}';
        var end = new Date(enddateStr);
        var now = new Date ();
         
        var m = Math.round ((end - now) / 1000);
        var day = parseInt (m / 24 / 3600);
        var hours = parseInt ((m % (3600 * 24)) / 3600);
        var minutes = parseInt ((m % 3600) / 60);
        var seconds = m % 60;
         
        if (m < 0){
            document.getElementById ("timer").innerHTML = 0 + "天" + 0 + "小时" + 0 + "分钟" + 0 + "秒";
            return;
        }
        document.getElementById("timer").innerHTML = day + "天" + hours + "小时" + minutes + "分钟" + seconds + "秒";
        setTimeout ('countdown()', 1000); 
        
    }
    
    window.onload = function (){
        countdown ();
    }
    
    var _layer0;
    var _layer1;
    var _layer2;
    
    function confirm_bay(){
        window.setTimeout(function(){
	        _layer0 = layer.open({
	          title:false,
			  type: 1,
			  closeBtn:0,
			  area: ['502px', '525px'],
			  btn:false,
			  content:$('#formbox').html(),
			  success:function(){
			      $("#paymoney_input").val("");
			      $("#agreeBox").prop("checked",true);
			      $("#zhanghuyue").html(Fen2Yuan($("#avlmoney").val()));
			      $("#yuqishouyi").html("0.00");
			  }
			});
		},50);
    }
    
    function Fen2Yuan(num){
	  return (num/100).toFixed(2);
	}
    
    function calInterest(paymoney){
        var avlmoney = $("#avlmoney").val();
        var paymoney_fen = paymoney * 100;
        
        var token = $("input[name='token']").val();
        if((paymoney_fen - avlmoney) > 0){
            $(".goumaimai").html("充值").attr("href","/member/invest");
            $("#buzhu").css("display","").find("font#xuyao").html(Fen2Yuan(paymoney*100));
            
            $("#yuqishouyi").html("").html("0.00");
        }else{
            
            var yuqi = calIncome(paymoney);
            $("#yuqishouyi").html("").html(yuqi);
            
            //.attr("href","/member/initiativeTender")
            $(".goumaimai").html("购买").on({
               click:function(){
                   
                   var agree = $("#agreeBox").is(':checked');
                   if(!agree){
                       layer.msg('请仔细阅读并同意购买协议');
                       return false;
                   }
                   
                   var minimoney = $("#minimoney").val();
                   if(paymoney_fen<minimoney){
                       layer.msg('标的起投金额' + minimoney/100 +'元');
                       return false;
                   }
                   
                   
                   layer.close(_layer0);
                   
                   //询问框
				   _layer1 = layer.confirm('是否已付款成功？', {
					  btn: ['已付款','付款遇到问题'] //按钮
				   }, function(){
					  window.location.href='/member/asset/orders/';
				   }, function(){
					  window.location.href='/member/invest/detail/id_'+${loan.lnid};
				   });
				   
                   //打开窗口提交表单
                   var dataJson = {};
                   dataJson.token = token;
                   dataJson.transAmt = paymoney;
                   dataJson.lnid = $("#lnid").val();
                   openPostWindow("/member/initiativeTender", "buyForm", dataJson);
               }
            });
        }
    }
    
    function submitForm() {
       window.open('_blank','newWin');
       //提交当前窗口的表单，并将响应内容通过target指向新的窗口newWin
       $("#payForm").submit();
    }
    
    function showPDF(pdfname){
    
        _layer2 = layer.open({
	          title:false,
			  type: 1,
			  closeBtn:0,
			  shadeClose: true, //开启遮罩关闭
			  area: ['802px', '602px'],
			  btn:false,
			  content:$('#pdf_view').html(),
			  success:function(){
			      $("#pdfContainer").attr('src','');
				  var urlPre = "/resources/upload/xieyi/"+pdfname;
				  $("#pdfContainer").attr('src',urlPre);
			  },
			  end:function(){
			      $("#pdfContainer").attr('src','');
			  }
		});
		
		//$("#pdf_Modal").modal("show");
	}
	
	function dtabs(tabObj, tabId){
	    $(".tjqx_Three").find("a").removeClass("on");
	    $(tabObj).addClass("on");
	    
	    $(".tjqx_Three_c").find("div.tab").css("display","none");
	    $("#tab"+tabId).css("display","block");
	    
		if(tabId == '02'){
		 setTimeout(function(){initMap('${loan.coordinate}');});
		}
	}
	
	function closeMoneyTips(){
	    $("#buzhu").css("display","none");
	}
	
	function calIncome(paymoney){
	    var yearate = $("input[name='yearate']").val();
	    var loandead = $("input[name='loandead']").val();
		
		var step1 = (loandead/100) / 365;
		var step2 = yearate * paymoney;
		//alert("step2:yearate * paymoney =" + yearate +"*" + paymoney + "=" + step2);
		
		var step3 = parseFloat(step1) * parseFloat(step2);
	    return step3.toFixed(2);
	}
	
	function closeLayer0(){
	    //layer.close(_layer0);
	    layer.closeAll();
	    //window.location.reload();
	}
</script>
</body>
</html>