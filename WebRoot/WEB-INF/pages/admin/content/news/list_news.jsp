<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <head>
  <title></title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
     <link href="/resources/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
   <link href="/resources/bui/css/bui-min.css" rel="stylesheet" type="text/css" />
   <link href="/resources/bui/css/page-min.css" rel="stylesheet" type="text/css" />  
   <style type="text/css"> 
	  #tooltip{
		position:absolute;
		border:1px solid #ccc;
		background:#fff;
		padding:2px;
		display:none;
		color:#000;
	  }
	</style>
 </head>
 <body>
 <div class="rtop">
	<div class="rtop_l">当前位置：项目管理 &gt; <a href="#">集团架构</a></div>
</div> 

  <div class="container">
    <div class="row">
      <form id="searchForm" class="form-horizontal span32">
        <input type="hidden" name="ntype" value="${ntype}"/>
        <div class="row">
          <div class="control-group span8">
            <label class="control-label">文章标题：</label>
            <div class="controls">
              <input type="text" class="control-text" name="title">
            </div>
          </div>
          <div class="control-group span8">
            <label class="control-label">来源：</label>
            <div class="controls">
              <input type="text" class="control-text" name="source">
            </div>
          </div>
          <div class="control-group span8">
            <label class="control-label">作者：</label>
            <div class="controls">
              <input type="text" class="control-text" name="author">
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
   <div id="fengmian" class="hide">
      <form id="sortForm" class="form-horizontal" >
       <input type="hidden" name="id" value="" id="upfm"/> 
        <div class="row" style="margin-left:-30px;margin-top:25px;">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>请选择图片</label>
            <div class="controls">
               <input type="text"  class="control-text" id="showAreaFileName" /> 
			   <input type="file" id="areaFile" name="areaFile" onchange="repeatTitle()" style="display:none" /> 
			   <input class="submitx" type="button" value="选择图片" class="input-normal control-text"  onclick="$('#areaFile').click();"/> 
            </div> 
          </div>  
        </div> 
        <div class="row"> 
          <div style="margin-left:100px;margin-top:5px;">
              <font color="#FF0000">尺寸300X300，jpg格式，500K以内</font>
          </div>
        </div> 
      </form>
  </div>
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/config-min.js"></script>
  <script type="text/javascript" src="/resources/js/ajaxfileupload.js"></script>
  <script type="text/javascript">
    BUI.use('common/page');
  </script>   
<script type="text/javascript">
  BUI.use(['common/search','bui/overlay','bui/tab'],function (Search,Overlay,Tab) { 
    var  columns = [
    	  {title:'序号',width:40,renderer : function(value,obj,index){
    	  		return (store.get("pageIndex"))*store.get("pageSize")+(index+1);
    	  }},
          {title:'封面',width:80,renderer : function(value,obj){
            var  delStr = "<img src='"+obj.coverurl+"' width='25' height='25' onerror=\"this.src='/resources/images/tm.gif'\" class='tooltip' />"; 
            return delStr;
          }},
          {title:'标题',dataIndex:'title',width:650},
          {title:'来源',dataIndex:'source',width:250},
          {title:'作者',dataIndex:'author',width:250},
          {title:'浏览次数',dataIndex:'viewcount',width:100 }, 
          {title:'发布时间',dataIndex:'pubtime',width:150,renderer:BUI.Grid.Format.datetimeRenderer},
          {title:'操作',width:150,renderer : function(value,obj){
            var editStr =  Search.createLink({  
                text : '编辑', 
                href : '/system/content/news/enter_edit/?id='+obj.nid+'&ntype=${param.ntype}'
            });    
            var cvStr = '<span class="grid-command btn-upcv" data-id="'+obj.nid+'" >封面</span>'; 
            var delStr = '<span class="grid-command btn-del" >删除</span>'; 
            return cvStr + editStr + delStr;
          }}, 
         {dataIndex:'nid', visible: false} ,
         {dataIndex:'state', visible: false}
        ],
        
      store = Search.createStore('/system/content/news/search/',{
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
            		window.location.href='/system/content/news/enter_add/?ntype=${param.ntype}';
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
	      if(sender.hasClass('btn-del')){ 
	        var record = [ev.record];  
	        if(record.length==1){
		        BUI.Message.Confirm('确认要删除选中的记录么？',function(){
		          	$.ajax({
			          url:'/system/content/news/del/', 
			          data:{"id":record[0].nid}, 
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
      }else if(sender.hasClass('btn-upcv')){
          var id = sender.attr("data-id"); 
       	  var record = [ev.record];	 
          if(record.length==1){ 
         	  $('#upfm').val(id); 
         	  var dialog = new Overlay.Dialog({
		            title:'上传封面',
		            width:400,
		            height:150,
		            closeAction : 'destroy', //每次关闭dialog释放
		            //配置DOM容器的编号
		            contentId:'fengmian',
		            success:function () { 
		            	$.ajaxFileUpload({     
							    url: "/system/common/upload/news_cover/",   
							    data:{"id":$("#upfm").val()},
				                secureuri:false, 
				                fileElementId:'areaFile',  
				                dataType: 'json', 
				                success: function (result, status)   {  
				                    if(result.code=='1'){ 
				                    	BUI.Message.Alert(result.message,function() {
								              search.load();
								        },"success");  
									}else {
										BUI.Message.Alert(result.message,"error"); 
									} 
				                },
				                error: function (data, status, e)  {
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
     grid.on('aftershow',function(ev){
     	var x = 10;
		var y = 20;
		$("img.tooltip").mouseover(function(e){  
			var tooltip = "<div id='tooltip'><img src='"+ this.src +"'   onerror='this.src=\"/images\/tm.gif\"'  width='"+parseInt(this.width)*6+"' height='"+parseInt(this.height)*6+"'/><\/div>";  
			$("body").append(tooltip);	 					 
			$("#tooltip")
				.css({
					"top": (e.pageY+y) + "px",
					"left":  (e.pageX+x)  + "px"
				}).show("fast");	   
	    }).mouseout(function(){ 
			$("#tooltip").remove();	  
	    }).mousemove(function(e){
			$("#tooltip")
				.css({
					"top": (e.pageY+y) + "px",
					"left":  (e.pageX+x)  + "px"
				});
		}); 
     });
     
  });
  function repeatTitle(){ 
	
		var fileName = $("#areaFile").val(); 
		
		$("#showAreaFileName").val(fileName); 
	}
</script>

<body>
</html>  
