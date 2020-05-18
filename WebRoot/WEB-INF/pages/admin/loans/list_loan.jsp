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
					<!-- 
					<div class="row">
						<div class="control-group span12">
							<div class="control-group span12">
								<label class="control-label">
									标的开始时间：
								</label>
								<div class="controls">
									<input name="startDate" type="text" class="calendar">
									<span> - </span>
									<input name="endDate" type="text" class="calendar">
								</div>
							</div>
						</div>
						
						<div class="control-group span12">
							<div class="control-group span12">
								<label class="control-label">
									标的结束时间：
								</label>
								<div class="controls">
									<input name="startDate" type="text" class="calendar">
									<span> - </span>
									<input name="endDate" type="text" class="calendar">
								</div>
							</div>
						</div>
						
						<div class="span3 offset2">
							<button type="button" id="btnSearch"
								class="button button-primary">
								搜索
							</button>
						</div>
					</div>
					-->
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
	                {title:'编号(汇付ID)',dataIndex:'lnnum',width:130},
	                {title:'名称',dataIndex:'name',width:200},
	                //{title:'类别',dataIndex:'bidtypename',width:50},
	                {title:'借款金额',dataIndex:'loanMoneyLabel',width:100},
	                {title:'可投金额',dataIndex:'avalMoneyLabel',width:100},
	                {title:'已筹金额',dataIndex:'collectmoneyLabel',width:100},
	                {title:'最低投资额',dataIndex:'miniMoneyLabel',width:100},
	                //{title:'已还金额',dataIndex:'repayedMoneyLabel',width:80},
	                //{title:'年利率',dataIndex:'yearate',width:60},
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
	                {title:'借款人类型',dataIndex:'lmantypename',width:120},
	                
	                {title:'借款人名称',dataIndex:'lman',width:100},
	                {title:'借款人汇付ID',dataIndex:'lusrcustid',width:130},
	                //{title:'借款人证件号',dataIndex:'lcertnum',width:150},
	                {title:'借款人手机号',dataIndex:'lmanphone',width:100},
	                //{title:'借款用途',dataIndex:'usefor',width:120},
	                //{title:'审核状态',dataIndex:'address',width:300},
	                //{title:'审核状态描述',dataIndex:'address',width:300},
	                {title:'操作',width:250, renderer : function(value,obj){
	                    
	                    var checkStr='';
	                    
	                    if(obj.status == "NOCHECK"){
	                        checkStr = '<span><a href="/system/loan/enter_check/?lnid='+obj.lnid +'">标的审核</a></span>&nbsp;&nbsp;';
	                    }else if(obj.status == "NORMAL"){
	                         checkStr = ''
	                    }else if(obj.status == "UNPASS"){
	                         checkStr = '<span><a href="/system/loan/enter_check/?lnid='+obj.lnid +'">再次审核</a></span>&nbsp;&nbsp;'
	                    }else if(obj.status == "LOSSED"){
	                         checkStr = ''
	                    }
	                       
	                    var detailStr = '<span><a href="/system/loan/enter_detail/?lnid='+obj.lnid +'">标的详情</a></span>&nbsp;&nbsp;'; //标的详情
	                    
	                    var editStr='';
	                    
	                    if(obj.status == "NOCHECK"){
	                         editStr = '<span class="grid-command btn-del"><a href="/system/loan/enter_edit/?lnid='+obj.lnid +'">编辑标的</a></span>&nbsp;&nbsp;';
	                    }else if(obj.status == "NORMAL"){
	                         editStr = ''
	                    }else if(obj.status == "UNPASS"){
	                         editStr = '<span class="grid-command btn-del"><a href="/system/loan/enter_edit/?lnid='+obj.lnid +'">编辑标的</a></span>&nbsp;&nbsp;'
	                    }else if(obj.status == "LOSSED"){
	                         checkStr = ''
	                    }
	                    
	                    var delStr = '';
	                    if(obj.status == "NOCHECK"){
	                        delStr = '<span class="grid-command btn-del" title="删除未审核标的">删除标的</span>&nbsp;&nbsp;';
	                    }else if(obj.status == "NORMAL"){
	                         delStr = ''
	                    }else if(obj.status == "UNPASS"){
	                         delStr = '<span class="grid-command btn-del" title="删除未通过标的">删除标的</span>&nbsp;&nbsp;'
	                    }
	                    
	                    var repayStr = '';
	                    if(obj.status == "FINISH"){
	                        repayStr = '<span><a href="/system/loan/enter_repay/?lnid='+obj.lnid +'">还款管理</a></span>&nbsp;&nbsp;'//还款管理
	                    }else if(obj.status == "LOSSED"){
	                        repayStr = ''
	                    }
	                    
	                    var bidRec = '';
	                    if(obj.status == "NORMAL"){
	                        bidRec = '<span><a href="/system/bidlog/enter_search/?lnid='+obj.lnid +'">投标记录</a></span>&nbsp;&nbsp;'//立即投标
	                    }else if(obj.status == "LOSSED"){
	                         bidRec = ''
	                    }
	                    
	                    var manBiaoStr = '';
	                    if(obj.status == "NORMAL"){
	                        if(obj.avalmoney < obj.loanmoney){
	                            manBiaoStr = '<span class="grid-command btn-full" title="满标停投">满标停投</span>'//立即投标
	                        }else{
	                            manBiaoStr = '<span><a href="/system/loan/lossStop/?lnid='+obj.lnid +'">流标停投</a></span>'//立即投标
	                        }
	                    }else if(obj.status == "LOSSED"){
	                         manBiaoStr = ''
	                    }
	                    
	                    return checkStr + detailStr + editStr + delStr + repayStr + bidRec + manBiaoStr;
	                }}
                ],
                
                store = Search.createStore('/system/loan/search/',{
					//autoLoad : true
				}),
                
                gridCfg = Search.createGridCfg(columns,{
                      //forceFit: true,
                      tbar : {
				          items : [
				            {text : '<i class="icon-plus"></i>发布借款信息',btnCls : 'button button-small',handler:function(){location.href='/system/loan/enter_add/'}}
				            //{text : '<i class="icon-edit"></i>编辑',btnCls : 'button button-small',handler:function(){alert('编辑');}},
				            //{text : '<i class="icon-remove"></i>删除',btnCls : 'button button-small',handler : delFunction}
				          ]
                      },
                      plugins : [BUI.Grid.Plugins.ColumnResize] 
                });

			    var search = new Search({
			        store : store,
			        gridCfg : gridCfg
			    }),
    
                grid = search.get('grid');
    
			    //监听事件，删除一条记录
			    grid.on('cellclick',function(ev){
			      var sender = $(ev.domTarget); //点击的Dom
			      if(sender.hasClass('btn-del')){
			        var record = ev.record;
			        delItems(search,[record]);
			      }else if(sender.hasClass('btn-full')){
			        var record = ev.record;
			        fullFunction(search,[record]);
			      }
			    });
    });
    
    //删除操作
    function delFunction(){
        var selections = grid.getSelection();
        delItems(selections);
    }
    
    function fullFunction(search, items){
        var arrays = [];
        BUI.each(items,function(item){
            arrays.push(item.lnid);
        });
        
        if(arrays.length){
	        BUI.Message.Confirm('满标后用户将无法投资,确认停投吗?',function(){
	          $.ajax({
	            url : '/system/loan/fullStop/',
	            data : {"lnid" : arrays[0]},
	            //traditional:true,
	            success : function(data){
	              if(data.code == "1"){ //满标操作成功
	                BUI.Message.Alert(data.message,function(){
	                    search.load();  
	                },"success");
	              }else{ //满标操作失败
	                    BUI.Message.Alert(data.message);
	              }
	            }
	        });
	        },'question');
        }
    }
    
    function delItems(search,items){
      var arrays = [];
      BUI.each(items,function(item){
        arrays.push(item.lnid);
      });
      
      if(arrays.length){
        BUI.Message.Confirm('确认要删除选中的记录么？',function(){
          $.ajax({
            url : '/system/loan/del/',
            data : {"lnid" : arrays[0]},
            //traditional:true,
            success : function(data){
              if(data.code == "1"){ //删除成功
                BUI.Message.Alert(data.message,function(){
                    search.load();  
                },"success");
              }else{ //删除失败
                    BUI.Message.Alert('删除失败！');
              }
            }
        });
        },'question');
      }
    }
</script>
	<body>
</html>