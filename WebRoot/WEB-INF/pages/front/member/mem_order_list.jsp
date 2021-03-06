<%@ page language="java" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		         <li id="dealNav" class="current"><a href="/member/asset/orders/">我的资产</a></li>
		         <li id="accountNav"><a href="/member/found/logs/">我的资金</a></li>
		         <li class="bg" style="width: 88px; left: 0px;"></li>
		     </ul>
		</div>
 
        <div class="clearfix personCon mgb100"> 
			<div class="investLeft fl">
			 	<ul class="leftNavList">
			     	<li id="assetAllNav" class="mgl0 mgl0yes"><a href="/member/asset/orders/" class="cur"><i class="leftNavList_4"></i>购买记录</a></li><!--  
			        <li id="assetAllNav" class="mgl0"><a href="/member/asset/profits/" ><i class="leftNavList_6"></i>收益记录</a></li> -->
			     </ul>
			</div>
			<form action="/member/asset/orders" name="sform" id="sform" >  	
             <div class="investRight fl">
            	<h2>购买记录</h2>
                <div class="investCon grzx_gmjl"> 
                	<div class="grzx_gmjl_1">
                    	<span>交易时间：<input type="text"  id="start" name="start" value="${param.start}" class="Wdate" onFocus="WdatePicker({isShowClear:false,readOnly:false,dateFmt:'yyyy-MM-dd'});" style="width:146px;height:27px;padding-left: 8px;"  placeholder="请选择日期" /> - <input type="text"  id="end" name="end" value="${param.end}" placeholder="请选择日期" class="Wdate" style="width:146px;height:27px;padding-left: 8px;" onFocus="WdatePicker({isShowClear:false,readOnly:false,dateFmt:'yyyy-MM-dd'});"/><a id="dt0" data-dt="0" href="javascript:void(0);" class="dtclass ">最近6个月</a><!--<a id="dt1" data-dt="1" href="javascript:void(0);" class="dtclass">一年</a>  --><a href="javascript:void(0);" data-dt="-1" class="dtclass grzx_gmjl_1_on">搜索</a><a id="st0" href="javascript:void(0);" data-st="0" class="stclass">全部</a><a id="st1" data-st="1" href="javascript:void(0);" class="stclass">投资中</a><a id="st2" data-st="2" href="javascript:void(0);" class="stclass">已回款</a></span>
                    </div>
                   <table  cellpadding="0" cellspacing="0">
                   		<tr>  
                        	<th width="10%">资产名称</th>
                        	<th width="15%">购买日期</th>
                        	<th width="15%">购买金额(元)</th>
                        	<th width="15%">预计收益(元)</th>
                        	<th width="15%">回款日期</th>
                        	<th width="18%">状态</th>
                        	<!-- <th>凭证</th> -->
                        </tr><c:forEach items="${page.list}" var="item">
                        <tr>
                        	<td>${item.loanname}</td>
                        	<td><fmt:formatDate value="${item.bidtime}" pattern="yyyy/MM/dd"/></td>
                        	<td><fmt:formatNumber value="${item.bidmoney/100}" pattern="#0.00" /></td>
                        	<td><fmt:formatNumber value="${item.profit/100}" pattern="#0.00" /></td>
                        	<td><c:if test="${empty  item.repaydate}">筹集中</c:if><c:if test="${!empty  item.repaydate}"><fmt:formatDate value="${item.repaydate}" pattern="yyyy/MM/dd"/></c:if></td>
                        	<td>${item.stname}</td>
                        	<!-- <td>下载</td> --> 
                        </tr></c:forEach>
                   </table> 
                </div>
            </div>  
			<input type="hidden" name="dt" id="dt" value="${param.dt}" />
			<input type="hidden" name="st" id="st" value="${st}"/>
			</form> 
        </div>
      </div>
</div>

<!--页脚-->
<div class="shadow2 hidden"></div>
<jsp:include page="/WEB-INF/pages/front/member/include/footer.jsp"/>  
<script type="text/javascript" src="/resources/My97DatePicker/WdatePicker.js"></script>  
<script>
var dt = '${param.dt}';
var st = '${st}';
if(dt!=null&&dt!=""&&dt!='-1'){
	$("#dt"+dt).addClass("grzx_gmjl_1_onnn");
}
if(st!=null&&st!=""){ 
	$("#st"+st).addClass("grzx_gmjl_1_onnn");
}

$(".dtclass").click(function(){
	var val = $(this).attr("data-dt");
	if(val !='-1'){
		$("#start").val("");
		$("#end").val("");
	}
	$("#dt").val(val);
	$("#sform").submit(); 
});
$(".stclass").click(function(){
	var val = $(this).attr("data-st"); 
	$("#st").val(val);
	$("#sform").submit(); 
}); 
</script> 
</body>
</html>