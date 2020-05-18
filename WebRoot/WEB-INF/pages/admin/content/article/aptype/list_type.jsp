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
  
   <div id="sortDIV" class="hide">
      <form id="sortForm" class="form-horizontal" > 
      	 <input type="hidden" name="aptid" id="id" />
      	 <input type="hidden" name="aptype" value="${param.aptype}"/>
      	 <input type="hidden" id="oldOrderNum" name="oldOrderNum" />
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>一级分类名称</label>
            <div class="controls">
               <input type="text" class="input-normal control-text" id="name" disabled="disabled"/> 
            </div>
          </div> 
          <div class="control-group span8">
            <label class="control-label"><s>*</s>当前索引</label>
            <div class="controls">
               <input type="text" class="input-normal control-text" id="newOrderNum" name="newOrderNum"/> 
            </div>
          </div> 
        </div> 
      </form>
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
          {title:'一级分类',dataIndex:'name',width:80},
          {title:'备注',dataIndex:'memo',width:300},  
          {title:'操作',width:80,renderer : function(value,obj,index){
          	  var scStr =  Search.createLink({  
                text : '二级分类', 
                href : '/system/content/article/atype/enter_search/?aptid='+obj.aptid
              }); 
              var editStr =  Search.createLink({ 
                text : '编辑', 
                href : '/system/content/article/aptype/enter_edit/?id='+obj.aptid
              }), 
              delStr = '<span class="grid-command btn-del" >删除</span>'; 
              
              var sindex = (store.get("pageIndex"))*store.get("pageSize")+(index+1)
              var sortStr = '<span class="grid-command btn-sort" data-sindex="'+sindex+'" >排序</span>';
            return scStr + editStr + delStr + sortStr;
          }} 
        ],
      store = Search.createStore('/system/content/article/aptype/search/?aptype=${aptype}',{
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
            		window.location.href='/system/content/article/aptype/enter_add/?aptype=${aptype}';
            	}
            } 
          ]
        } 
        //plugins : [BUI.Grid.Plugins.RowNumber] 
      });

    var  search = new Search({
        store : store,
        gridCfg : gridCfg
      }),
      grid = search.get('grid'); 
    //监听事件，删除一条记录
    grid.on('cellclick',function(ev){
      var sender = $(ev.domTarget); //点击的Dom
      if(sender.hasClass('btn-del')){
        var record = [ev.record];  
        if(record.length==1){
	        BUI.Message.Confirm('确认要删除选中的记录么？',function(){
	          	$.ajax({
		          url:'/system/content/article/aptype/del/', 
		          data:{"id":record[0].aptid,"aptype":'${aptype}'},
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
      }else if(sender.hasClass('btn-sort')){
      	   var sindex = sender.attr("data-sindex"); 	 
	       var record = [ev.record];  
	       if(record.length==1){
	       		$('#id').val(record[0].aptid);   
				$('#oldOrderNum').val(sindex);  
				$('#newOrderNum').val(sindex);
				$('#name').val(record[0].name);
		        var dialog = new Overlay.Dialog({
		            title:'排序',
		            width:360,
		            height:130,
		            closeAction : 'destroy', //每次关闭dialog释放
		            //配置DOM容器的编号
		            contentId:'sortDIV',
		            success:function () {
		              	var newOrderNum = parseInt($("#newOrderNum").val()); 
						var allCounts = store.getTotalCount(); 
						if(isNaN(newOrderNum)){
							alert('您的输入有误!');
							return false;
						}else if(newOrderNum<1){
							alert("您输入的索引必须大与1!");
							return false;
						}else if(newOrderNum>allCounts){
							alert("您输入的索引超出记录总数!");
							return false;
						} 
						$.ajax({
					          url:'/system/content/article/aptype/sort/',
					          type:"POST",
					          data:$("#sortForm").serialize(),
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
		              	this.close();
		            }
	           });
	           dialog.show();
	       }
	    }
    });
  });
</script>

<body>
</html>  
