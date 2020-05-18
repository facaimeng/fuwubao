<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>${news.title}</title> 
<link rel="stylesheet" type="text/css" href="/resources/style/mem/index.css">
<link rel="stylesheet" type="text/css" href="/resources/style/mem/reset.css">  
</head>
<body class="IndexPage mgt30">  
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
<div style="height:100px;"></div>

<div class="tjzc_cot" style="padding-bottom:60px;">
	${news.content}
</div>
<jsp:include page="/WEB-INF/pages/front/member/include/footer.jsp"/> 
</body>
</html>