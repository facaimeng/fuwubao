<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${froms.name}-融银普惠服务平台</title>
<link type="text/css" href="/resources/style/web/main.css" rel="stylesheet" /> 
<link type="text/css" href="/resources/style/web/dookayui-basic.css" rel="stylesheet" />
<link type="text/css" href="/resources/style/web/service.css" rel="stylesheet" />  
</head>

<body class="bodycls"> 
<div class="yingying"> 
<jsp:include page="/WEB-INF/pages/front/web/include/header.jsp"/>  
<div class="bg-gary" style="height: 46px;"> </div>
<div class="block-main fcls">
    <div class="container">
                <div class="pic">
            <img src="/resources/images/web/h5gra3ni.uvj19282015065436.jpg" draggable="false" alt="产品与服务">
        </div>
            <section>
                <h3 class="t-style-1 text-right mb-1-5">
                    产品与服务
                    <small>PRODUCTS AND SERVICES</small>
                    <span class="icon icon-horizontal-1"></span>
                </h3>
                <p style="text-align: justify;">融银普惠是重庆融银科技和融银普惠团队倾力打造的专业资产服务平台。平台将通过资产行业资源，发现价值低估，升值空间的资产，为广大用户提供安全的资产投资渠道，通过“价值挖掘，价值流通”的方式，为投资人实现资产增值。</p><p><br></p>
            </section>
            <span class="arrow arrow-1"></span>

    </div>
    <div class="bg-blank"></div>
</div>

<div class="about-content fcls" style="padding-bottom:10px;">
    <div class="container container-arrow box-main pb-10 mb-6-5">
        <div class="t-style-2  ml-3 clearfix pt-16 t-product-1" id="noah">
            <h3>产品与服务</h3>
            <h4>PRODUCTS AND SERVICES</h4>
            <span class="icon icon-horizontal-1"></span>
        </div>
        <div class="service-bar">
            <ul class="t-style-3 clearfix" role="tablist">
					
                    <li id="online_finance" role="presentation"><a href="/service/online_finance/" aria-controls="team-1" role="tab" data-toggle="tab">资本<i class="i-ar"></i></a></li>
                    <li id="wealth_management" role="presentation"><a href="/service/wealth_management/" aria-controls="team-1" role="tab" data-toggle="tab">资产<i class="i-ar"></i></a></li> 
                    <li id="assets_management" role="presentation"><a href="/service/assets_management/" aria-controls="team-1" role="tab" data-toggle="tab">资管<i class="i-ar"></i></a></li> 
            </ul>
        </div>
        ${commonOne.content}
        <span class="arrow arrow-7 arrow-left"></span>
    </div>
</div> 
<jsp:include page="/WEB-INF/pages/front/web/include/footer.jsp"/>  
<script type="text/javascript">
	$("#"+'${ntype}').addClass("active"); 
</script> 
</body>  
</html>
