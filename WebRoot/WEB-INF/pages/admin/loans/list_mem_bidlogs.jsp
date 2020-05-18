<%@ page language="java" pageEncoding="UTF-8"%>
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
			<div style="float:left;margin-right:5px;"><img src="/resources/bui/img/lang.png" /></div> <div class="rtop_l" style="float:left;">当前位置：借款管理 &gt; <a href="#">投标管理</a></div>
		</div>
		
		<div class="container" style="padding-top:50px;">
			<div class="row">
				<form id="searchForm" class="form-horizontal span36">
				    <input name="lnid" type="hidden" value="${lnid}">
				    <input name="poid" type="hidden" value="${poid}">
					<div class="row">
						<div class="control-group span8">
							<label class="control-label">
								投标订单号：
							</label>
							<div class="controls">
								<input type="text" class="control-text" name="bidordernum">
							</div>
						</div>
						
						<div class="control-group span8">
							<label class="control-label">
								标的名称：
							</label>
							<div class="controls">
								<input type="text" class="control-text" name="loanname">
							</div>
						</div>
						
						<div class="control-group span8">
							<label class="control-label">
								投标人姓名：
							</label>
							<div class="controls">
								<input type="text" class="control-text" name="realname">
							</div>
						</div>
					</div>

                    <div class="row">
                    						
						<div class="control-group span8">
							<label class="control-label">
								投标状态：
							</label>
							<div class="controls">
								<select name="state">
									<option value="">全部</option>
									<option value="BIDDING">投标处理中</option>
								    <option value="BIDDONE">投标成功</option>
								    <option value="FKDING">放款处理中</option>
								    <option value="FKDONE">放款完成</option>
								    <option value="BIDCANCLE">投标已撤销</option>
								</select>
							</div>
						</div>
						<div class="control-group span9">
							<label class="control-label">
								投标时间：
							</label>
							<div class="controls">
								<input type="text" class="calendar" name="addtimestart">
								<span> - </span>
								<input type="text" class="calendar" name="addtimeend">
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
			    	  		return index+1;
			    	}},
	                {title:'投标订单号',dataIndex:'bidordernum',width:130},
	                {title:'标的编号',dataIndex:'lnnum',width:130},
	                {title:'标的名称',dataIndex:'lnname',width:200},
	                
	                {title:'投标人',dataIndex:'realname',width:150},
	                {title:'投资金额',dataIndex:'bidmoneyLabel',width:150},
	                {title:'已还金额',dataIndex:'repaymoneyLabel',width:150},
	                {title:'下单日期',dataIndex:'bidtime',width:100,renderer:BUI.Grid.Format.dateRenderer},
	                {title:'处理日期',dataIndex:'optime',width:100,renderer:BUI.Grid.Format.dateRenderer},
	                {title:'处理状态',dataIndex:'stname',width:100},
	                {title:'操作', width:120,renderer : function(value,obj){
	                       //<a href="/system/bidlog/autoTenderCancel/?mblid='+obj.mblid +'">撤销投标</a>
	                       var delStr = '<span class="grid-command btn-cancel" title="撤销投标">撤销投标</span>';
	                       if(obj.state == 'BIDDONE'){
	                           return delStr;
	                       }
	                }}
                ],
                
                store = Search.createStore('/system/bidlog/search/',{
					//autoLoad : true
				}),
                
                gridCfg = Search.createGridCfg(columns,{
                      //tbar : {
				      //    items : [
				      //      {text : '<i class="icon-plus"></i>立即投标',btnCls : 'button button-small',handler:function(){location.href='/system/loan/autoTender/?lnid='+10}}
				      //    ]
                      //},
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
			      
			      if(sender.hasClass('btn-cancel')){
			        var record = ev.record;
			        cancelItems(search,[record]);
			      }
			    });
                
    });
    
    function cancelItems(search, items){
        var arrays = [];
        BUI.each(items,function(item){
            arrays.push(item.lnid);
        });
        
        if(arrays.length){
	        BUI.Message.Confirm('确认撤销投资吗?',function(){
	          $.ajax({
	            url : '/system/bidlog/autoTenderCancel/',
	            data : {"mblid" : arrays[0]},
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
</script>
	<body>
</html>