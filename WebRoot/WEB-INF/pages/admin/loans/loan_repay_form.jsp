<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>会员投标记录</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="/resources/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
	<link href="/resources/bui/css/bui-min.css" rel="stylesheet" type="text/css" />
	<link href="/resources/bui/css/page-min.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	.bui-grid-table .grid-command {margin-right: 0px;}
	</style>
</head>

<body>
	<div class="rtop" style="position:fixed;width:100%;">
		<div style="float:left;margin-right:5px;"><img src="/resources/bui/img/lang.png" /> </div>
		<div class="rtop_l" style="float:left;"> 当前位置：借款管理 &gt; <a href="#">投标管理</a> </div>
	</div>

	<div class="container" style="padding-top:50px;">
		<div class="row">
			<h3>还款总计:</h3>
			<form id="loanForm" class="form-horizontal span24">

				<input type="hidden" name="rpid" value="${repayPlan.rpid}" /> 
				
				<input type="hidden" name="lnid" value="${loan.lnid}" />

				<!-- 还款金额 -->
				<div class="row">
					<div class="control-group span15">
						<label class="control-label"><s>*</s>还款金额：</label>
						<div class="controls">
							<input type="text" id="repaymoney_input" class="input-large control-text" value="<fmt:formatNumber value="${repayPlan.rpmoney/100}" pattern="#0.00" />" readonly="readonly">
						</div>
					</div>
				</div>

				<!-- 还款人类型 -->
				<div class="row">
					<div class="control-group span8">
						<label class="control-label"><s>*</s>还款人/机构：</label>
						<div class="controls">
							<select class="input-normal" name="lusrcustid" style="width:150px;" onchange="viewBalance(this.value)">
								<option value="${loan.lusrcustid}" selected="selected">借款人</option>
								<option value="${company.usrcustid}">担保机构</option>
							</select>
						</div>
					</div>
				</div>

				<!-- 担保机构 -->
				<div class="row">
					<div class="control-group span15">
						<label class="control-label"><s>*</s>可用余额：</label>
						<div class="controls">
							<span class="yen">&yen;</span>
							<span id="balance_span">0.00</span>
							<span><a class="page-action grid-command" id="loanman_ranchege" data-href="#" title="汇付充值" style="cursor:pointer;">去充值</a> </span>
						</div>
					</div>
				</div>

				<!-- 计划还款日期 -->
				<div class="row">
					<div class="control-group span15">
						<label class="control-label"><s>*</s>计划还款日期：</label>
						<div class="controls">
							<input type="text" class="input-large control-text" value="<fmt:formatDate value="${repayPlan.pretime}"  pattern="yyyy-MM-dd"/>" readonly="readonly">
						</div>
					</div>
				</div>

				<!-- 实际还款日期 -->
				<div class="row">
					<div class="control-group span15">
						<label class="control-label"><s>*</s>实际还款日期：</label>
						<div class="controls">
							<input name="realtime" type="text" class="input-large control-text" value="<fmt:formatDate value="${currentDay}" pattern="yyyy-MM-dd"/>" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
						</div>
					</div>
				</div>

				<!-- 还款类型 -->
				<!--
		        <div class="row">
		          <div class="control-group span8">
		            <label class="control-label"><s>*</s>还款类型：</label>
		            <div class="controls">
		              <select name="rptype" class="input-normal" style="width:150px;"> 
					    <option value="RPM1">本金</option>
					    <option value="RPM2">利息</option>
		              </select>
		            </div>
		          </div>
		        </div>
		        -->

				<!-- 标的类型 -->
				<!--
		        <div class="row">
		          <div class="control-group span8">
		            <label class="control-label"><s>*</s>支付方式：</label>
		            <div class="controls">
		              <select name="paytype" class="input-normal" style="width:150px;"> 
					    <option value="网银支付">网银支付</option>
					    <option value="支付宝支付">支付宝支付</option>
					    <option value="微信支付">微信支付</option>
					    <option value="现金支付">现金支付</option>
			            <option value="其他">其他</option>
		              </select>
		            </div>
		          </div>
		        </div>
        
        
		        <div class="row">
		          <div class="control-group span15">
		            <label class="control-label">备 注：</label>
		            <div class="controls control-row4">
		              <textarea name="memo" class="input-large" type="text"></textarea>
		            </div>
		          </div>
		        </div>
		        -->

			</form>
		</div>
        <hr/>
        
		<h2>还款明细:</h2>
		<div class="search-grid-container">
			<div id="grid"></div>
		</div>
	</div>

	<script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/resources/bui/js/bui-min.js"></script>
	<script type="text/javascript" src="/resources/bui/js/config-min.js"></script>
	<script type="text/javascript" src="/resources/js/cbai.commonutil.js?i=8"></script>
	<script type="text/javascript" src="/resources/My97DatePicker/WdatePicker.js"></script>

	<script type="text/javascript">
		BUI.use('common/page');
	</script>
    
	<script type="text/javascript">
		BUI.use([ 'common/search', 'bui/overlay' ], function(Search, Overlay) {
			var enumObj = {"1" : "男","0" : "女"}, 
			    columns = [
					{
						title : '序号',
						width : 50,
						renderer : function(value, obj, index) {
							return index + 1;
						}
					},

					{
						title : '投标交易流水号',
						dataIndex : 'bidordernum',
						width : 130
					},
					{
						title : '计息日期',
						dataIndex : 'loantime',
						width : 100,
						renderer : BUI.Grid.Format.dateRenderer
					},
					{
						title : '交易名称(标的)',
						dataIndex : 'name',
						width : 250
					},
					{
						title : '投资人姓名',
						dataIndex : 'lman',
						width : 130
					},
					{
						title : '投资人汇付号',
						dataIndex : 'holdusrcustid',
						width : 130
					},
					{
						title : '预计到期应还(元)',
						dataIndex : 'repaymoney',
						width : 130
					},
					//{   title : '已还金额',dataIndex : 'realrpmoneyLabel',width : 100},
					//{ title : '还款订单号',dataIndex : 'optime',width : 180,renderer : BUI.Grid.Format.dateRenderer},
					//{ title : '还款成功日期',dataIndex : 'bidtime',width : 150,renderer : BUI.Grid.Format.dateRenderer},
					{
						title : '还款状态',
						width : 80,
						renderer : function(value,obj,index){ 
    	  				    return "<font id='b_"+obj.mblid+"'>"+obj.stname+"</font>"
    	  			    }
					}

			],

			store = Search.createStore('/system/loan/replay_detail_waitting/?rpid=' + '${rpid}', {
			//autoLoad : true
			}),

			gridCfg = Search.createGridCfg(columns, {
				tbar : {
					items : [ {
						text : '<i class="icon-plus"></i>立即还款',
						btnCls : 'button button-small',
						handler : function() {
							BUI.Message.Confirm('确认要还款吗？', function() {
								var records = store.getResult();
								
								//提示处理中
					            setTimeout(function(){
					                _layer = layer.load(0);
					            });
								
								for ( var i = 0; i < records.length; i++) {
                                           
                                    var lrpdid = records[i].lrpdid;
                                    var rpid = records[i].rpid;
                                    var outCustId = $("select[name='lusrcustid']").val();
                                    
									huankuan(lrpdid, rpid, outCustId);

								}
								
								//关闭处理中的提示 
			                	setTimeout(function(){
					                layer.close(_layer);
					                search.load(); 
					            });
					            
							}, 'question');

						}
					} ]
				},
				plugins : [ BUI.Grid.Plugins.ColumnResize ]
			});

			var search = new Search({
				store : store,
				gridCfg : gridCfg
			}),

			grid = search.get('grid');
		});

		
		//发送还款请求
		function huankuan(lrpdid, rpid, outCustId) {
		    
			$.ajax({
				url : '/system/loan/sub_repay/',
				data : {"lrpdid" : lrpdid, "rpid":rpid, "outCustId":outCustId},
				async:false,
				success : function(data) {
					if (data.code == "1") { //删除成功
						$("#b_" + mblid).html("还款完成!");
					} else {
						$("#b_" + mblid).html(data.message);
					}
				}
			});
			
		}

		$(function() {
			var default_UsrCustId = ${loan.lusrcustid};
			viewBalance(default_UsrCustId);
		});

		function viewBalance(usrCustId) {
			$("#balance_span").html("获取中...");
			$.ajax({
				type : "GET",
				url : '/system/loan/search_balance/',
				data : {
					"usrCustId" : usrCustId
				},
				success : function(result) {

					if (result.code == "0") {
						BUI.Message.Alert(result.message, 'info');
						$("#balance_span").html("0.00");
					} else {
						$("#balance_span").html(result.returnObj.avlBal);
					}

				},
				error : function(jqXHR, textStatus, errorThrown) {
					/*错误信息处理*/
				}
			});
			
			var target_href = "/system/users/loanman/torechange/?transAmt="+$('#repaymoney_input').val()+"&usrcustId="+usrCustId;
			//给充值链接赋值
			$("#loanman_ranchege").attr('data-href',target_href);
		}
		
		
		

		function subForm() {

			var rpid = $("input[name='rpid']").val();
			if (rpid == "" || rpid == null) {
				BUI.Message.Alert('计划ID不能为空!', 'info');
				return false;
			}

			var realtime = $("input[name='realtime']").val();
			if (realtime == "" || realtime == null) {
				BUI.Message.Alert('请指定真实还款日期!', 'info');
				return false;
			}

			$.ajax({
				type : "POST",
				url : '/system/loan/sub_repay/',
				data : $("#loanForm").serialize(),
				success : function(result) {
					if (result.code == "0") {
						BUI.Message.Alert(result.message, 'info');
					} else {
						BUI.Message.Alert(result.message, function() {
							location.href = '/system/loan/enter_search/';
						}, 'info');
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					/*错误信息处理*/
				}
			});
		}
	</script>
<body>
</html>