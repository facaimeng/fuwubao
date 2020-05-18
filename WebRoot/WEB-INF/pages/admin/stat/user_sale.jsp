<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
 <head>
  <title></title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
     <link href="/resources/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
   <link href="/resources/bui/css/bui-min.css" rel="stylesheet" type="text/css" />
   <link href="/resources/bui/css/page-min.css" rel="stylesheet" type="text/css" />  
 </head>
 <body>
 <div class="rtop">
	<div class="rtop_l">当前位置：项目管理 &gt; <a href="#">集团架构</a></div>
</div> 

  <div class="container">
    <div class="row" >
      <form id="searchForm" class="form-horizontal">
        <div class="row" > 
          <div class="control-group span8">
            <label class="control-label">姓名：</label>
            <div class="controls">
              <input type="text" class="control-text" name="realname">
            </div>
          </div>
          <div class="control-group span8">
            <label class="control-label">手机号码：</label>
            <div class="controls">
              <input type="text" class="control-text" name="phone">
            </div>
          </div>   
          <div class="span3 offset2">
            <button  type="button" id="btnSearch" class="button button-primary">搜索</button>
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
  BUI.use(['common/search','bui/overlay','bui/tab'],function (Search,Overlay,Tab) {
    var enumObj = {"Y":"是","N":"否","M":"男","F":"女"};
    var  columns = [
    	  {title:'序号',width:40,renderer : function(value,obj,index){
    	  		return (store.get("pageIndex"))*store.get("pageSize")+(index+1);
    	  }}, 
    	  {title:'项目名称',dataIndex:'lname',width:330},
    	  {title:'融资天数',dataIndex:'ldead',width:80},
    	  {title:'成立总金额',dataIndex:'lmoney',width:100},
    	  {title:'投资时间',dataIndex:'bidtime',width:120,renderer:BUI.Grid.Format.datetimeRenderer},
    	  {title:'投资人',dataIndex:'bidman',width:80},
    	  {title:'投资人手机',dataIndex:'bidphone',width:120}, 
          {title:'投资金额',dataIndex:'bidmoney',width:120}, 
          {title:'预计收益',dataIndex:'bidprofit',width:120},
          {title:'渠道姓名',dataIndex:'uname',width:80}, 
          {title:'渠道手机',dataIndex:'uphone',width:120},
          {title:'一级收益率',dataIndex:'rate1',width:80}, 
          {title:'一级收益',dataIndex:'uprofit1',width:120},   
          {title:'二级收益率',dataIndex:'rate2',width:80}, 
          {title:'二级收益',dataIndex:'uprofit2',width:120}  
        ],
        
      store = Search.createStore('/system/common/permission/users/search/',{
	      //autoLoad : true
	  }),
	  
      gridCfg = Search.createGridCfg(columns,{   
        plugins : [BUI.Grid.Plugins.ColumnResize],
        tbar : {
          items : [
            {
            	text : '<i class="icon-plus"></i>新建',
            	btnCls : 'button button-small',
            	handler:function(){
            		window.location.href='/system/common/permission/users/enter_add/';
            	}
            } 
          ]
        }  
      });

    var  search = new Search({
        store : store,
        gridCfg : gridCfg  
    }),
      
    grid = search.get('grid'); 
 
    grid.on('cellclick',function(ev){
	      var sender = $(ev.domTarget); //点击的Dom
	      if(sender.hasClass('btn-lock')){
	        var op = sender.attr("data-op"); 
	        var record = [ev.record];  
	        if(record.length==1){
		        BUI.Message.Confirm('确认要操作选中的记录么？',function(){
		          	$.ajax({
			          url:'/system/common/permission/users/lock/', 
			          data:{"op":op,"id":record[0].userid},
			          type:"POST",
			          success:function(result){ 
			             if(result.code=='1'){
			             	 BUI.Message.Alert(result.message,function() {
					              search.load();
					        },"success");  
				         }else{
				         	 BUI.Message.Alert(result.message,"error");  
				         }  
				      },
			          error: function (XMLHttpRequest, textStatus, errorThrown) {
			          	 BUI.Message.Alert("网络不畅，请重试!","warning");   
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
