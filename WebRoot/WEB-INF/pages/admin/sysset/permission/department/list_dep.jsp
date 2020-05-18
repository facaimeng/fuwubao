<%@ page language="java" pageEncoding="UTF-8"%>
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
    <div class="search-grid-container">
      <div id="grid"></div>
    </div>
  </div>
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/config-min.js"></script>
  <script type="text/javascript" src="/resources/js/cbai.dialog.js"></script>
  <script type="text/javascript">
    BUI.use('common/page');
  </script> 
<script type="text/javascript">
  BUI.use(['common/search','bui/overlay'],function (Search,Overlay) {
    
    var  columns = [
    	  {title:'序号',width:12,renderer : function(value,obj,index){ 
    	  		return (store.get("pageIndex"))*store.get("pageSize")+(index+1);
    	  }},
          {title:'部门名称',dataIndex:'name',width:80},
          {title:'状态',dataIndex:'stname',width:80},
          {title:'备注',dataIndex:'memo',width:200},  
          {title:'操作',width:80,renderer : function(value,obj){
            var editStr =  Search.createLink({ //链接使用 此方式 
                text : '编辑', 
                href : '/system/common/permission/department/enter_edit/?id='+obj.pdid
              }); 
            var delStr = '<span class="grid-command btn-lock" data-op="lock">锁定</span>';//页面操作不需要使用Search.createLink
            if(obj.state=='LOCKED'){
            	delStr = '<span class="grid-command btn-lock" data-op="unlock">解锁</span>';
            }
            return editStr + delStr;
          }}, 
          {dataIndex:'pdid', visible: false},
          {dataIndex:'state', visible: false}
        ],
      store = Search.createStore('/system/common/permission/department/search/',{
	      //autoLoad : true
	  }),
      gridCfg = Search.createGridCfg(columns,{  
      	forceFit: true,
        tbar : {
          items : [
            {
            	text : '<i class="icon-plus"></i>新建',
            	btnCls : 'button button-small',
            	handler:function(){
            		window.location.href='/system/common/permission/department/enter_add/';
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
    //监听事件，删除一条记录
     grid.on('cellclick',function(ev){
      var sender = $(ev.domTarget); //点击的Dom
      if(sender.hasClass('btn-lock')){
        var op = sender.attr("data-op"); 
        var record = [ev.record];  
        if(record.length==1){
	        BUI.Message.Confirm('确认要操作选中的记录么？',function(){
	          	$.ajax({
		          url:'/system/common/permission/department/lock/', 
		          data:{"op":op,"id":record[0].pdid},
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
