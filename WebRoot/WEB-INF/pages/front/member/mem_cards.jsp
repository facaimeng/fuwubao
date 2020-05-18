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
				<div class="investLeft fl">
					<ul class="leftNavList">
						<li id="assetAllNav" class="mgl0 "><a href="/member/found/logs/"><i class="leftNavList_4"></i>资金记录</a></li>
						<li id="assetAllNav" class="mgl0 mgl0yes"><a href="/member/found/cards/" class="cur"><i class="leftNavList_9"></i>我要绑卡</a></li>
						<li id="assetAllNav" class="mgl0 "><a href="/member/found/recharge/" ><i class="leftNavList_7"></i>我要充值</a></li>
						<li id="assetAllNav" class="mgl0 "><a href="/member/found/cashout/"><i class="leftNavList_8"></i>我要提现</a></li>
						<li id="assetAllNav" class="mgl0 "><a href="/member/fss/queryfss/"><i class="leftNavList_10"></i>我的生利宝</a></li>
					</ul>
				</div> 
				<div class="investRight fl">
					<h2>我要绑卡</h2>
					<div class="user-detailct-ct" style="border-top:solid 1px #dddcdd;">
                        	<div class="invest-ct">
                            	<div class="manage-bank"> 
                                    <div class="manage-bankct">
                                    	<ul><c:forEach items="${clist}" var="item" varStatus="current">
                                        	<li>
                                            	<div class="manage-bctimg"><img src="${item.bklogourl}" height="33" onerror="this.src='/resources/images/tm.gif'"></div>
                                                <div class="manage-bct">
                                                	<h3 class="cc">${item.cardnum}</h3>
                                                    <p class="left">开户行：${item.bkname}</p><p class="right">开户名： ${mname}</p>
                                                </div>
                                                <div class="manage-bbottom">
                                                    <b><p class="left">已绑定</p></b>
                                                </div>
                                            </li></c:forEach> 
                                            <div class="manage-bmore">
                                        		<a href="/member/found/cards/bind/" target="_blank"><img src="/resources/images/mem/manage-more.jpg" width="70" height="69"></a>
                                            	<p style="font-size: 14px;margin-top: 5px;">绑定银行卡</p> 
                                        	</div>
                                        </ul> 
                                    </div>
                                    <div class="manage-bank-at">
									    <h4>温馨提示</h4>
										<p>* 为确保账户资金安全，请绑定开户者本人的储蓄卡。</p>
										<p>* 若姓名存在生僻字，请联系我们的在线客服或服务热线400-000-0000为您跟进。</p>
										<p>* 绑卡成功后，汇付天下会转入0.01元至您绑定的银行卡中以作验证，请勿担心账户安全。</p> 
                                    </div>
                                </div>
                            </div>
                            <div class="clear"></div>
                    </div>
				</div>
			</div>
		</div>
	</div> 
	<jsp:include page="/WEB-INF/pages/front/member/include/footer.jsp" />  
</body>
</html>