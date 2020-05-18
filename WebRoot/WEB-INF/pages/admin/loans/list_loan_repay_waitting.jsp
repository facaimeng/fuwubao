<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>搜索表单</title>
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
			<div style="float:left;margin-right:5px;"><img src="/resources/bui/img/lang.png" /></div> <div class="rtop_l" style="float:left;">当前位置：借款管理 &gt; <a href="#">${loan.name}--还款管理</a></div>
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
		
		  <script type="text/javascript">
		    BUI.use('common/page');
		  </script> 
  
		<script type="text/javascript">
        BUI.use(['common/search','bui/overlay'],function (Search,Overlay) {
            
            var enumObj = {"1":"男","0":"女"},
                columns = [
                	{title:'序号',width:50,renderer : function(value,obj,index){return index+1;}},
	                
	                {title:'借款人',dataIndex:'lman',width:80},
	                {title:'用户名',dataIndex:'phone',width:100},
	                {title:'交易名称',dataIndex:'name',width:300},
	                {title:'当前期数',dataIndex:'curperiod',width:80},
	                {title:'总期数',dataIndex:'repaytimes',width:80},
	                {title:'类型',dataIndex:'repaytypename',width:120},
	                {title:'待还款金额',dataIndex:'rpmoneyLabel',width:120},
	                {title:'待还款日期',dataIndex:'pretime',width:160,renderer:BUI.Grid.Format.dateRenderer},
	                {title:'操作',width:130,renderer:function(value,obj){
	                    //var repayStr = '<span><a href="/system/loan/enter_repay_form/?rpid='+obj.rpid+'">立即还款</a></span>&nbsp;&nbsp;';
	                    var repayStr = '<span><a href="/system/loan/enter_replay_form/?rpid='+obj.rpid+'">立即还款</a></span>&nbsp;&nbsp;';
	                    return  repayStr;
	                }}
                ],
                
                store = Search.createStore('/system/loan/replay_search/'),
                gridCfg = Search.createGridCfg(columns,{
                      plugins : [BUI.Grid.Plugins.ColumnResize]
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