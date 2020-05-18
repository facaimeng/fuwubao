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
    <div class="row">
      <form id="searchForm" class="form-horizontal span24">
        <div class="row">
          <div class="control-group span8">
            <label class="control-label">汇付编号：</label>
            <div class="controls">
              <input type="text" class="control-text" name="usrcustid">
            </div>
          </div>
          <div class="control-group span8">
            <label class="control-label">用户名称：</label>
            <div class="controls">
              <input type="text" class="control-text" name="name">
            </div>
          </div>
          <div class="control-group span8">
            <label class="control-label">用户类别：</label>
            <div class="controls" >
              <select name="lmtype">
                <option value="">全部</option>
                <option value="01">个人</option>
                <option value="02">企业</option>
              </select>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="control-group span8">
            <label class="control-label">电话号码：</label>
            <div class="controls">
              <input type="text" class="control-text" name="phone">
            </div>
          </div>
           <div class="control-group span8">
            <label class="control-label">证件号：</label>
            <div class="controls" >
               <input type="text" class="control-text" name="idnum">
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
  	var enumObj = {"01":"个人","02":"企业"};
    var  columns = [
    	  {title:'序号',width:40,renderer : function(value,obj,index){
    	  		return (store.get("pageIndex"))*store.get("pageSize")+(index+1);
    	  }},
          {title:'汇付ID',dataIndex:'usrcustid',width:150},
          {title:'汇付名',dataIndex:'usrcustname',width:150},
          {title:'用户类别',dataIndex:'lmtype',width:100,renderer:BUI.Grid.Format.enumRenderer(enumObj)},
          {title:'用户名称',dataIndex:'name',width:150},
          {title:'证件号',dataIndex:'idnum',width:150},
          {title:'电话',dataIndex:'phone',width:150},
          {title:'地址',dataIndex:'address',width:250}, 
          //{title:'紧急联系人',dataIndex:'usrcustname',width:250}, 
          {title:'状态',dataIndex:'stname',width:50}, 
          {title:'添加日期',dataIndex:'addtime',width:150,renderer:BUI.Grid.Format.datetimeRenderer},
          {title:'操作',width:150,renderer : function(value,obj){
            var editStr =  Search.createLink({  
                text : '编辑', 
                href : '/system/users/loanman/enter_edit/?id='+obj.lmid
            });    
            var delStr = '<span class="grid-command btn-lock" data-op="lock">锁定</span>';//页面操作不需要使用Search.createLink
            if(obj.state=='LOCKED'){
            	delStr = '<span class="grid-command btn-lock" data-op="unlock">解锁</span>';
            }
            return editStr + delStr;
          }}, 
         {dataIndex:'lmid', visible: false} ,
         {dataIndex:'state', visible: false}
        ],
        
      store = Search.createStore('/system/users/loanman/search/',{
	      //autoLoad : true
	  }),
	  
      gridCfg = Search.createGridCfg(columns,{  
      	//forceFit: true, 
        tbar : {
          items : [
            {
            	text : '<i class="icon-plus"></i>新建',
            	btnCls : 'button button-small',
            	handler:function(){
            		window.location.href='/system/users/loanman/enter_add/?t=${param.t}';
            	}
            } 
          ]
        },
        plugins : [BUI.Grid.Plugins.ColumnResize] 
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
			          url:'/system/users/loanman/lock/', 
			          data:{"op":op,"id":record[0].cmid},
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
      }else if(sender.hasClass('btn-open')){
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
