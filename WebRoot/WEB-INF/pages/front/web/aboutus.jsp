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
                    ${froms.name}
                    <small>ABOUT US</small>
                    <span class="icon icon-horizontal-1"></span>
                </h3>
                <p style="text-align: justify;">
融银普惠是重庆融银科技和融银普惠团队倾力打造的专业资产服务平台。平台将通过资产行业资源，发现价值低估，升值空间的资产，为广大用户提供安全的资产投资渠道，通过“价值挖掘，价值流通”的方式，为投资人实现资产增值。</p><p><br></p>
            </section>
            <span class="arrow arrow-1"></span>

    </div>
    <div class="bg-blank"></div>
</div>
<div class="about-content fcls" style="padding-bottom:10px;">
<div class="container container-arrow box-main bg-quotes pb-8 mb-3-5">
    <div class="t-style-2 ml-3 clearfix">
        <h3>行业先锋</h3>
        <h4>INDUSTRY PIONEER</h4>
        <span class="icon icon-horizontal-1"></span>
    </div>
    <div class="company-intro clearfix">
        <p><img src="/resources/images/web/abt1.jpg" title="company-5.jpg"></p><div class="company-content"><p>丰富的从业经验和渠道资源有效解决资产各类问题，通过互联网渠道整合，从而破解资产门槛，再采用有效的解决方案解决变现资产流通信息等问题，实现了关于资产多方的共赢。</p></div>
    </div>
    <span class="arrow arrow-7 arrow-left"></span>
</div>
<div class="container container-arrow bg-gary bg-quotes mb-5-5 pb-8 pt-5">
    <div class="t-style-2 text-right mr-3 clearfix">
        <h3>服务平台</h3>
        <h4>Service platform</h4>
        <span class="icon icon-horizontal-1"></span>
    </div>
    <div class="company-now clearfix">
        <p class="pull-right"><img src="/resources/images/web/abt2.jpg"></p><p class="w-51-5 float-none">融银普惠提供“资产中介”平台服务，用户与资产所有者直接进行在线撮合，签署具有法律效力的保障合同，交易资金全部由汇付天下托管，平台不碰触任何交易资金。</p>
    </div>
    <span class="arrow arrow-6 arrow-right"></span>
</div>
<div class="container container-arrow box-main bg-quotes pb-8 mb-3-5 new-box">
    <div class="t-style-2 ml-3 clearfix">
        <h3>综合金融服务</h3>
        <h4>Integrated financial services</h4>
        <span class="icon icon-horizontal-1"></span>
    </div>
    <div class="company-intro clearfix">
        <p><img src="/resources/images/web/abt3.jpg" title="company-4.jpg"></p><div class="company-content"><p>融银普惠平台所有的撮合资产在这里统称“机会资产“，初期资产项目大部分都是金融机构的资产，也基本属于金融机构等原因需要变现处置的资产，交易流程和手续相对简便。资产项目的来源渠道主要是银行、资产管理公司、民间借贷从业机构，还有一部分是偶发性的企业改制或破产清算的处置资产，大部分是以协议或谈判的方式进行购入交易。</p></div>
    </div>
    <span class="arrow arrow-6 arrow-left"></span>
</div>
</div>
<jsp:include page="/WEB-INF/pages/front/web/include/footer.jsp"/>  
<script type="text/javascript">
	$("#"+'${ntype}').addClass("active"); 
</script> 
</body>  
</html>
