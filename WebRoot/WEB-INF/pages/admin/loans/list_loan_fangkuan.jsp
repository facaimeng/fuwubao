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
		.bui-grid-table .grid-command {
			margin-right: 0px;
		}
		</style>
	</head>
	
	<body>
		<div class="rtop" style="position:fixed;width:100%;">
			<div style="float:left;margin-right:5px;"><img src="/resources/bui/img/lang.png" /></div> <div class="rtop_l" style="float:left;">当前位置：借款管理 &gt; <a href="#">借款信息</a></div>
		</div>
		
		<div class="container" style="padding-top:50px;">
			<div class="row">
				<form id="searchForm" class="form-horizontal span32">
				    <input name="status" type="hidden" value="${status}">
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
								借款人：
							</label>
							<div class="controls">
								<input type="text" class="control-text" name="lman">
							</div>
						</div>
						
						<div class="control-group span8">
							<div class="control-group span9">
								<label class="control-label">
									借款金额(元)：
								</label>
								<div class="controls">
									<input name="minmoney" type="text" class="control-text" style="width:80px;" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"  
              onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}"/>
									<span> - </span>
									<input name="maxmoney" type="text" class="control-text" style="width:80px;" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"  
              onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}"/>
								</div>
							</div>
						</div>
						
						<div class="control-group span8">
							<button type="reset" class="button button-primary">清空条件</button>
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
                	{title:'序号',width:50,renderer : function(value,obj,index){
			    	  		return (store.get("pageIndex"))*store.get("pageSize")+(index+1);
			    	}},
	                {title:'编号(汇付ID)',dataIndex:'lnnum',width:120},
	                {title:'名称',dataIndex:'name',width:200},
	                //{title:'类别',dataIndex:'bidtypename',width:50},
	                
	                {title:'借款金额',dataIndex:'loanMoneyLabel',width:100},
	                {title:'已筹金额',dataIndex:'collectmoneyLabel',width:100},
	                {title:'最低投资额',dataIndex:'miniMoneyLabel',width:100},
	                
	                //{title:'已还金额',dataIndex:'repayedMoneyLabel',width:80},
	                //{title:'年利率',dataIndex:'yearate',width:60},
	                //{title:'应还总利息',dataIndex:'allinterest',width:60},
	                {title:'借款期限',width:80,renderer : function(value,obj){
	                    return obj.loandead + " " + obj.loandeadunit;
	                }},
	                //{title:'还款方式',dataIndex:'repaytypename',width:80},
	                
	                //{title:'投标开始日期',dataIndex:'startdate',width:100,renderer:BUI.Grid.Format.dateRenderer},
	                //{title:'投标结束日期',dataIndex:'enddate',width:100,renderer:BUI.Grid.Format.dateRenderer},
	                //{title:'应还款日',dataIndex:'repaydate',width:100,renderer:BUI.Grid.Format.dateRenderer},
	                //{title:'最后还款日期',dataIndex:'lastrepaydate',width:100,renderer:BUI.Grid.Format.dateRenderer},
	                //{title:'本息保障',dataIndex:'warrtypename',width:100},
	                //{title:'标的产品',dataIndex:'protypename',width:100},
	                
	                //{title:'风险控制方式',dataIndex:'risktypename',width:100},
	                {title:'借款人类型',dataIndex:'lmantypename',width:80},
	                {title:'借款人名称',dataIndex:'lman',width:100},
	                {title:'借款人汇付ID',dataIndex:'lusrcustid',width:150},
	                {title:'借款人证件号',dataIndex:'lcertnum',width:150},
	                {title:'借款人手机号',dataIndex:'lmanphone',width:100},
	                //{title:'借款用途',dataIndex:'usefor',width:120},
	                //{title:'审核状态',dataIndex:'address',width:300},
	                //{title:'审核状态描述',dataIndex:'address',width:300},
	                {title:'操作',width:150, renderer : function(value,obj){
	                    var fangkuanStr;
	                    
	                    if(obj.directtype == "Y"){
	                         fangkuanStr = '<span class="grid-command btn-fk">定向放款</span>&nbsp;&nbsp;';
	                    }else{
	                         fangkuanStr = '<span class="grid-command btn-del"><a href="/system/bidlog/enter_fangkuan_search/?lnid='+obj.lnid +'">立即放款</a></span>&nbsp;&nbsp;';
	                    }
	                    
	                    var fangkuanDetailStr = '<span class="grid-command btn-del"><a href="/system/bidlog/enter_fangkuan_history/?lnid='+obj.lnid +'">放款详情</a></span>';  
	                    return fangkuanStr + fangkuanDetailStr;
	                }}
                ],
                
                store = Search.createStore('/system/loan/waitloans_search/',{
					//autoLoad : true
				}),
                
                gridCfg = Search.createGridCfg(columns,{
                      //forceFit: true,
                      plugins : [BUI.Grid.Plugins.ColumnResize] 
                });

			    var search = new Search({
			        store : store,
			        gridCfg : gridCfg
			    }),
    
                grid = search.get('grid');
			    grid.on('cellclick',function(ev){
				      var sender = $(ev.domTarget); //点击的Dom
				      if(sender.hasClass('btn-fk')){
			       	  var record = [ev.record];	 
			          if(record.length==1){ 
			         		top.topManager.openPage({ 
							    href : '/system/users/company/huifu/regester/?id='+record[0].cmid,
							    title : '企业汇付开户'
							}); 
				            BUI.Message.Confirm('是否企业开户成功？',function(){
					             search.load();
					        },'question'); 
			            }
			      	  }
			    });
    });
  
    
    
</script>
	<body>
</html>