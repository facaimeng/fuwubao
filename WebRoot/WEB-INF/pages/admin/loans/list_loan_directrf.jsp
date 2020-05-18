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
				<form id="searchForm" class="form-horizontal span24">
					<div class="row">
						<div class="control-group span8">
							<label class="control-label">
								标的编号：
							</label>
							<div class="controls">
								<input type="text" class="control-text" name="lnnum">
							</div>
						</div>
						<div class="control-group span8">
							<label class="control-label">
								标的名称：
							</label>
							<div class="controls">
								<input type="text" class="control-text" name="lname">
							</div>
						</div>
						<div class="control-group span8">
							<label class="control-label">
								标的类别：
							</label>
							<div class="controls">
								<select name="">
									<option value="01">信用</option>
								    <option value="02">抵押</option>
								    <option value="03">债权转让</option>
						            <option value="99">其他</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="control-group span9">
							<label class="control-label">
								标的开始时间：
							</label>
							<div class="controls">
								<input type="text" class=" calendar" name="startDate">
								<span> - </span>
								<input name="endDate" type="text" class=" calendar">
							</div>
						</div>
						<div class="span3 offset2">
							<button type="button" id="btnSearch"
								class="button button-primary">
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
	                {title:'编号(汇付ID)',dataIndex:'lnnum',width:120},
	                {title:'名称',dataIndex:'name',width:200},

	                {title:'借款金额',dataIndex:'loanMoneyLabel',width:80},
	                {title:'已筹金额',dataIndex:'collectmoneyLabel',width:80},
	                {title:'放款金额',dataIndex:'payformoneyLabel',width:80},
	                
	                //{title:'年利率',dataIndex:'yearate',width:60},
	                //{title:'应还总利息',dataIndex:'allinterest',width:60},
	                {title:'借款期限',width:60,renderer : function(value,obj){
	                    return obj.loandead + " " + obj.loandeadunit;
	                }},
	                //{title:'还款方式',dataIndex:'repaytypename',width:80},
	                
	                //{title:'投标开始日期',dataIndex:'startdate',width:100,renderer:BUI.Grid.Format.dateRenderer},
	                //{title:'投标结束日期',dataIndex:'enddate',width:100,renderer:BUI.Grid.Format.dateRenderer},
	                {title:'应还款日',dataIndex:'repaydate',width:100,renderer:BUI.Grid.Format.dateRenderer},
	                {title:'最后还款日期',dataIndex:'lastrepaydate',width:100,renderer:BUI.Grid.Format.dateRenderer},
	                //{title:'本息保障',dataIndex:'warrtypename',width:100},
	                //{title:'标的产品',dataIndex:'protypename',width:100},
	                
	                //{title:'风险控制方式',dataIndex:'risktypename',width:100},
	                //{title:'借款人类型',dataIndex:'lmantypename',width:60},
	                
	                {title:'借款人名称',dataIndex:'lman',width:80},
	                {title:'借款人汇付ID',dataIndex:'lusrcustid',width:130},
	                
	                {title:'定向收款人名称',dataIndex:'dman',width:150},
	                {title:'定向收款人汇付ID',dataIndex:'dusrcustid',width:150},
	                //{title:'借款用途',dataIndex:'usefor',width:120},
	                //{title:'审核状态',dataIndex:'address',width:300},
	                //{title:'审核状态描述',dataIndex:'address',width:300},
	                {title:'操作',width:150, renderer : function(value,obj){
	                    var fangkuanStr = '<span class="grid-command btn-auth">授权定向</span>&nbsp;&nbsp;'; 
                        var checkStr = '<span class="grid-command btn-direct">执行定向</a></span>&nbsp;&nbsp;';
	                    
	                    return fangkuanStr + checkStr;
	                }}
                ],
                
                store = Search.createStore('/system/loan/directrf_search/',{
					//autoLoad : true
				}),
                
                gridCfg = Search.createGridCfg(columns,{
                      //forceFit: true,
                      //tbar : {
				      //    items : [
				      //      {text : '<i class="icon-plus"></i>发布借款信息',btnCls : 'button button-small',handler:function(){location.href='/system/loan/enter_add/'}}
				            //{text : '<i class="icon-edit"></i>编辑',btnCls : 'button button-small',handler:function(){alert('编辑');}},
				            //{text : '<i class="icon-remove"></i>删除',btnCls : 'button button-small',handler : delFunction}
				      //   ]
                      //},
                      plugins : [BUI.Grid.Plugins.ColumnResize] 
                });

			    var search = new Search({
			        store : store,
			        gridCfg : gridCfg
			    }),
    
                grid = search.get('grid');
			    grid.on('cellclick',function(ev){
				      var sender = $(ev.domTarget); //点击的Dom
				      if(sender.hasClass('btn-auth')){
			       	      var record = [ev.record];	 
				          if(record.length==1){
				         		top.topManager.openPage({ 
								    href : '/system/loan/direcTrfAuth/?lnid='+record[0].lnid,
								    title : '资金定向授权'
								});
								
					            BUI.Message.Confirm('是否已执行资金定向？',function(){
						            search.load();
						        },'question'); 
				          }
			      	  }else if(sender.hasClass('btn-direct')){
			      	      var record = [ev.record];
				          if(record.length==1){
				              BUI.Message.Confirm('确定要将借款人资金执行定向吗?',function(){
					              $.ajax({
						             type: "POST",
						             url:'/system/loan/direcTrf/',
						             data:{"lnid":record[0].lnid},
						             success:function(result){
						                 if(result.code == "1"){
						                     BUI.Message.Alert(result.message,'info');
						                     search.load();
						                 }else{
						                     BUI.Message.Alert(result.message,'info');
						                 }
						             },
						             error: function (jqXHR, textStatus, errorThrown) {
						                /*错误信息处理*/
								     }
						          }); 
					          },'question');
				          }
			      	  }
			    });
    });
</script>
	<body>
</html>