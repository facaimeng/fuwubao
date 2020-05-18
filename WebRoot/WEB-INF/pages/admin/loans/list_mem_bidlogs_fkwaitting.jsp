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
					<div class="row">
						<div class="control-group span8">
							<label class="control-label">
								投标人姓名：
							</label>
							<div class="controls">
								<input type="text" class="control-text" name="realname">
							</div>
						</div>
						<div class="control-group span8">
							<label class="control-label">
								放款状态：
							</label>
							<div class="controls">
								<select name="state">
									<option value="">全部</option>
									<option value="DOING">处理中</option>
								    <option value="DONE">已完成</option>
								    <option value="FAIL">已失败</option>
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
						
						<div class="control-group span9">
							<label class="control-label">
								放款时间：
							</label>
							<div class="controls">
								<input type="text" class="calendar" name="addtimestart">
								<span> - </span>
								<input type="text" class="calendar" name="addtimeend">
							</div>
						</div>
						
						<div class="span3 offset2">
							<button type="button" id="btnSearch"
								class="button button-primary">
								搜索
							</button>
						</div>
					</div>
					<div class="row">
						
						

					</div>
				</form>
			</div>
			<div class="search-grid-container">
				<div id="grid"></div>
			</div>
		</div>
		
		<script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/resources/layer/layer.js"></script>
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
	                {title:'投标编号',dataIndex:'bidordernum',width:130},
	                {title:'标的编号',dataIndex:'lnnum',width:130},
	                {title:'冻结资金编号',dataIndex:'freezeordernum',width:130},
	                {title:'投标人姓名',dataIndex:'realname',width:100},
	                {title:'投资金额',dataIndex:'bidmoneyLabel',width:100}, 
	                {title:'投资日期',dataIndex:'bidtime',width:100,renderer:BUI.Grid.Format.dateRenderer},
	                {title:'投资处理日期',dataIndex:'optime',width:100,renderer:BUI.Grid.Format.dateRenderer}, 
	                
	                {title:'待放款金额',dataIndex:'bidmoneyLabel',width:100},
	                {title:'放款处理日期',dataIndex:'optime',width:100,renderer:BUI.Grid.Format.dateRenderer}, 
	                {title:'放款状态',width:120,renderer : function(value,obj,index){ 
    	  				return "<font id='b_"+obj.mblid+"'>"+obj.stname+"</font>"
    	  			}}
                ],
                
                store = Search.createStore('/system/bidlog/fangkuan_waitting/',{
					//autoLoad : true
				}),
                
                gridCfg = Search.createGridCfg(columns,{
                      tbar : {
				          items : [
				            {text : '<i class="icon-plus"></i>一键放款', btnCls : 'button button-small',
				                handler:function(){
				                	var _layer;
				                	
				                	BUI.Message.Confirm('确认要放款吗？',function(){
							            var records = store.getResult();
							            
							            var lnid = $("input[name='lnid']").val();
							            
							            //提示处理中
							            setTimeout(function(){
							                _layer = layer.load(0);
							            });
										
								        
					                	for(var i=0;i<records.length;i++){
					                		fangkuan(records[i].mblid, lnid);
					                	}
					                	
					                	//关闭处理中的提示 
					                	setTimeout(function(){
							                layer.close(_layer);
							                search.load();
							            });
							            
							        },'question');
							        
				                }
				            }
				          ]
                      },
                      plugins : [BUI.Grid.Plugins.ColumnResize]
                });

			    var search = new Search({
			        store : store,
			        gridCfg : gridCfg
			    }),
    
                grid = search.get('grid');
    });
    
    //发送放款请求
    function fangkuan(mblid, lnid){
        $.ajax({
            url: '/system/loan/loans/',
            data: {"mblid" : mblid, "lnid":lnid},
            async: false,
            success : function(data){
              if(data.code == "1"){ //删除成功
                  $("#b_"+mblid).html("放款完成");
              }else{
                  $("#b_"+mblid).html(data.message);
              }
            }
        });
    }
    
</script>
<body>
</html>