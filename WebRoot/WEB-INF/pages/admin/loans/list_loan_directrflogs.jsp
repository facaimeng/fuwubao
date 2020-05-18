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
			<div style="float:left;margin-right:5px;"><img src="/resources/bui/img/lang.png" /></div> <div class="rtop_l" style="float:left;">当前位置：借款管理 &gt; <a href="#">借款信息</a></div>
		</div>
		
		<div class="container" style="padding-top:50px;">
			<div class="row">
				<form id="searchForm" class="form-horizontal">
				    <input name="directtype" type="hidden" value="Y">
					<div class="row">
						<div class="control-group span8">
							<label class="control-label">
								转出账号：
							</label>
							<div class="controls">
								<input type="text" class="control-text" name="lnnum">
							</div>
						</div>
						<div class="control-group span8">
							<label class="control-label">
								转入账号：
							</label>
							<div class="controls">
								<input type="text" class="control-text" name="lname">
							</div>
						</div>
						<div class="control-group span9">
							<label class="control-label">
								转出时间：
							</label>
							<div class="controls">
								<input type="text" class=" calendar" name="startDate">
								<span> - </span>
								<input name="endDate" type="text" class=" calendar">
							</div>
						</div>
						<div class="control-group span3">
							<button type="button" id="btnSearch" class="button button-primary">
								搜索
							</button>
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
                	{title:'序号',width:50,renderer : function(value,obj,index){
			    	  		return index+1;
			    	}},
	                {title:'项目名称',dataIndex:'name',width:200},
	                {title:'转出账号',dataIndex:'lusrcustid',width:160},
	                {title:'转出人',dataIndex:'lman',width:100},
	                {title:'转入账号',dataIndex:'dusrcustid',width:160},
	                {title:'转入人',dataIndex:'',width:100},
	                {title:'募集资金',dataIndex:'loanMoneyLabel',width:100},
	                {title:'划拨资金',dataIndex:'loanMoneyLabel',width:100},
	                {title:'到账资金',dataIndex:'loanMoneyLabel',width:100},
	                {title:'划拨状态',dataIndex:'dirstate',width:80},
	                {title:'划拨时间',dataIndex:'diroptime',width:100,renderer:BUI.Grid.Format.dateRenderer}
                ],
                
                store = Search.createStore('/system/loan/directrf_search/',{
					//autoLoad : true
				}),
                
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