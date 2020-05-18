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
			<form id="searchForm" class="form-horizontal span24">
			    <input name="status" value="${status}" type="hidden" />
				<div class="row">
					<div class="control-group span12">
						<label class="control-label">
							计划还款日期：
						</label>
						<div class="controls">
							<input type="text" class="calendar" name="startDate">
							<span> - </span>
							<input name="endDate" type="text" class=" calendar">
						</div>
					</div>
					<div class="span3 offset2">
						<button type="button" id="btnSearch" class="button button-primary">搜索</button>
					</div>
				</div>
			</form>
		</div>

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
					{ title : '已还金额',dataIndex : 'realrpmoneyLabel',width : 100},
					{ title : '还款订单号',dataIndex : 'rpordernum',width : 180,renderer : BUI.Grid.Format.dateRenderer},
					{ title : '还款成功日期',dataIndex : 'realrptime',width : 150,renderer : BUI.Grid.Format.dateRenderer},
					{
						title : '还款状态',
						width : 80,
						renderer : function(value,obj,index){ 
						    if(obj.rpmoney >= obj.realrpmoney){
						        return "已还清";
						    }else{
						        return "未还清";
						    }
    	  			    }
					}

			],

			store = Search.createStore('/system/loan/replay_detail_history/?rpid=' + '${rpid}', {
			//autoLoad : true
			}),

			gridCfg = Search.createGridCfg(columns, {
				//tbar : {},
				plugins : [ BUI.Grid.Plugins.ColumnResize ]
			});

			var search = new Search({
				store : store,
				gridCfg : gridCfg
			}),

			grid = search.get('grid');
		});
	</script>
<body>
</html>