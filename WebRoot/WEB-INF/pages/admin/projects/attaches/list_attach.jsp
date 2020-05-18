<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <head>
  <title></title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
     <link href="/resources/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
   <link href="/resources/bui/css/bui-min.css" rel="stylesheet" type="text/css" />
   <link href="/resources/bui/css/page-min.css" rel="stylesheet" type="text/css" />   
	<link href="/resources/swfupload/upload_pic.css" rel="stylesheet" type="text/css" />
 </head>
 <body>
 <div class="rtop">
	<div class="rtop_l"></div>
</div> 
  <div class="container">
    <div class="search-grid-container">
      <div id="grid"></div>
    </div>
  </div>
  <!-- 上传隐藏层 end -->
<div class="div1" id="updiv" style="display: none;">
<form id="form1"   method="post" enctype="multipart/form-data">
  <div class="liulan" align="left" ><span id="upbtn"></span><p style="margin-left: 70px;margin-top: -20px;color: red;">请选择要上传的文件，PDF格式，10M以内</p></div>
  
  <div class="kuang">
    <div class="kuang3" style="height:270px;" id="processdiv"></div>
    <div class="kuang4 size12 heise" style="display: none;" id="allInfo">
      <div class="kuang4_1">
        <div  style="width:50px; float:left; text-align:right;">总进度：</div>
        <div style="width:60px;height:9px; border:1px #CCCCCC solid; margin-top:10px; font-size:0px;float:left; padding:1px;">
          <div class="allbar" id="allBar"></div>
        </div>
        <div style="width:40px; float:left; text-align:right;" id="allper">0%</div>
      </div>
     总大小：<span class="hongse" id="allsize">0KB</span>,成功上传<span class="hongse" id="upcount">0</span>个文件，共<span class="hongse" id="upsize">0KB</span></div>
  </div>   
  
  <div class="liulan" align="right"><img src="/resources/swfupload/up2.jpg" width="58" height="22"  onclick="swfu.startUpload();" style="cursor: pointer;"/></div>
 
  </form>
</div>
<!-- 上传隐藏层 end --> 

  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/config-min.js"></script>
  <script type="text/javascript" src="/resources/swfupload/swfupload.js"></script>
	<script type="text/javascript" src="/resources/swfupload/swfupload.speed.js"></script>
	<script type="text/javascript" src="/resources/swfupload/swfupload.queue.js"></script>
	<script type="text/javascript" src="/resources/swfupload/fileprogress.js"></script>
	<script type="text/javascript" src="/resources/swfupload/handlers.js"></script> 
    <script type="text/javascript" src="/resources/swfupload/swfutil.js"></script>
	<script src="/resources/artDialog/artDialog.source.js?skin=default"></script>
  
  <script type="text/javascript">
    BUI.use('common/page');
  </script> 
<script type="text/javascript">
	var swfu = null;
	
	 function openUpDiv(id,title,tourl) {         
		  art.dialog({
		  title:title,
		  lock:true,
		  padding: 0,
		  margin: 0,
		  content: document.getElementById(id),
		  close:function (){
		    swfu.shut(tourl);
		  },
	      id: 'EF693L'
	     });
	 }  
	function closeMyDialog(){
		art.dialog({id:'EF893L'}).close();	       
	}   
	 function initswf(param,tourl){  
	   	    settings.upload_url = "/system/common/upload/project_attach/";
			settings.file_size_limit = "50 MB";
			settings.file_types = "*.pdf";
			settings.file_types_description = "请选择要上传的文件!";
			settings.file_upload_limit = 20;
			settings.file_queue_limit = 0;	
			settings.post_params = param;
			
			if(swfu==null){ 
				swfu = new SWFUpload(settings); 
			}						
			openUpDiv('updiv','上传图片',tourl); 
		} 
  BUI.use(['common/search','bui/overlay'],function (Search,Overlay) { 
    
    var  columns = [
    	  {title:'序号',width:20,renderer : function(value,obj,index){
    	  		return (store.get("pageIndex"))*store.get("pageSize")+(index+1);
    	  }},
          {title:'附件名称',dataIndex:'name',width:80},
          {title:'附件大小',dataIndex:'fsize',width:80},
          {title:'附件后缀',dataIndex:'ext',width:80},
          {title:'备注',dataIndex:'memo',width:300},  
          {title:'上传时间',dataIndex:'uptime',width:80,renderer:BUI.Grid.Format.datetimeRenderer},  
          {title:'操作',width:80,renderer : function(value,obj){
            var editStr =  Search.createLink({  
                text : '编辑', 
                href : '/system/projects/attaches/enter_edit/?id='+obj.prattid
              }), 
              downStr = Search.createLink({  
                text : '下载', 
                href : '/system/common/download/project_attach/?id='+obj.prattid
              }),
              delStr = '<span class="grid-command btn-del" >删除</span>'; 
            return editStr + downStr + delStr;
          }}, 
          {dataIndex:'prattid', visible: false}
        ],
      store = Search.createStore('/system/projects/attaches/search/?id=${param.id}',{
	      //autoLoad : true
	  }),
      gridCfg = Search.createGridCfg(columns,{  
      	forceFit: true,
        tbar : {
          items : [
            {
            	text : '<i class="icon-plus"></i>上传',
            	btnCls : 'button button-small',
            	handler:function(){
            		initswf({'id':'${param.id}'},'/system/projects/attaches/enter_search/?id=${param.id}');
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
      if(sender.hasClass('btn-del')){
        var record = [ev.record];  
        if(record.length==1){
	        BUI.Message.Confirm('确认要删除选中的记录么？',function(){
	          	$.ajax({
		          url:'/system/projects/attaches/del/', 
		          data:{"id":record[0].prattid},
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
    $(".rtop_l").html("当前位置："+top.topManager.getNav());
  });
</script>

<body>
</html>  
