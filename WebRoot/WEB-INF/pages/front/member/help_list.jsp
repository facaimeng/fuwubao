<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>帮助中心-${globals.gtitle}</title>
	<link rel="stylesheet" type="text/css" href="/resources/style/mem/index.css">
	<link rel="stylesheet" type="text/css" href="/resources/style/mem/reset.css"> 
</head>
<body style="background:#fafafa;" class="IndexPage mgt30">
<jsp:include page="/WEB-INF/pages/front/member/include/header.jsp"/> 
<div class="wrap productNav" style="border-bottom:solid 1px #e4e2e2;">
	<div class="mg-auto area clearfix">
		<h1 class="fl logo"><img src="/resources/images/mem/logo2.png" title="融银普惠" alt="融银普惠"></h1>
		<div class="listNav mgb30 fr" id="cssmenu">
			<ul class="clearfix nav">
				<li id="mainAssetNav"><a href="/recommend/">首页</a></li>
                <li id="mainProductNav"><a href="/member/invest/">在线产品</a></li> 
                <li id="mainSafeNav"><a href="/member/security/">安全保障</a></li>
                <li id="mainAssetNav"><a href="/member/center/">我的账户</a></li>
               	<li class="bg" style="width: 44px; left: 0px;"></li>
			</ul>
		</div>
	</div>
</div> 
<div style="clear:both; height:10px;"></div> 
<!---------------开始------------------------->
<div class="Help_center_list">
	<h1><img src="/resources/images/mem/help_home.png" width="18" height="16" /><a href="/recommend/">首页</a><img src="/resources/images/mem/hlep_d.png" width="12" height="12" />帮助中心</h1>
    <div class="center_list_cot">
        <div class="center_list_left"><c:forEach items="${aptList}" var="item" varStatus="current">
            <div class="menu" id="ap_${item.aptid}" >
                <a href="javascript:void(0);">
                    <img src="/resources/images/mem/cjwt${current.count}.png" width="16" height="16" />
                    <em>${item.name}</em>
                    <img src="/resources/images/mem/list_jiant.png" width="13" height="13" class="list_left_go" />
                 </a>
             </div><c:if test="${!empty item.atypeList}"><c:forEach items="${item.atypeList}" var="item1" varStatus="current1"><c:if test="${current1.first}">
             <ul class="sonul" id="ap_${item.aptid}_ul" style="display: none;"></c:if>
             	<li id="at_${item1.atid}" ><a href="/faq/cata_${item.aptid}/class_${item1.atid}">${item1.atname}</a></li> <c:if test="${current1.last}">
             </ul></c:if></c:forEach></c:if></c:forEach> 
        </div>
        <div class="center_list_right">
        	<h2><em>${articletype.atname}</em></h2>
            <ul><c:forEach items="${page.list}" var="item" varStatus="current">
            	<li>
                	<a>
                    	<h3><em id="ar_${item.aid}_lg" class="center_list_right_off"></em><i id="ar_${item.aid}" class="clkme">${item.title}</i></h3>
                        <div id="ar_${item.aid}_d" style="display: none;">
                        	 ${item.content}
                        </div> 
                    </a>
                </li></c:forEach>  
            </ul>
        </div>
        <div style="clear:both;"></div>
    </div>
</div>    
<!-----------------------cont内容-------------------------->
<jsp:include page="/WEB-INF/pages/front/member/include/footer.jsp"/> 
<script>
   $(window).load(function() {

		$(".menu").on({
		    click:function(){
		    	$(this).siblings().removeClass("center_list_left_go"); 
		    	
		         $(this).next().siblings(".sonul").slideUp();
		    	
		         var id = $(this).attr("id");
		         
		         if($(this).hasClass("center_list_left_go")){ 
		         	
		         	//$(this).removeClass("center_list_left_go"); 
		         }else{
		         	$(this).addClass("center_list_left_go"); 
		         	
		         	 $("#"+id+"_ul").slideToggle();
		         	 
		         }
		         
		    }
	    });  
	    $(".clkme").on({
		    click:function(){ 
		         var id = $(this).attr("id");
		         
		         $("#"+id+"_d").slideToggle();  
		         
		         var lg = $("#"+id+"_lg");
		         
		         if(lg.hasClass("center_list_right_off")){
		         	
		         	lg.removeClass("center_list_right_off").addClass("center_list_right_on"); 
		         }else{
		         	lg.removeClass("center_list_right_on").addClass("center_list_right_off"); 
		         }
		         
		    }
	    });  
	    
	     
		$("#ap_${articleptype.aptid}").addClass("center_list_left_go");
		
		$("#ap_${articleptype.aptid}_ul").slideToggle();
		 
		$("#at_${articletype.atid}").addClass("center_list_a");
	 
	 
	}); 
</script>
</body>
</html>
