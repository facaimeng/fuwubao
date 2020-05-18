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
          <div style="margin-left:60px;margin-top:5px;">
              <font color="#FF0000">宽度不超过250，高度为33，png格式，500K以内</font>
          </div>
        </div> 
      </form>
  </div>
  
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/config-min.js"></script>
  <script type="text/javascript" src="/resources/js/cbai.dialog.js"></script>
   <script type="text/javascript" src="/resources/js/ajaxfileupload.js"></script>
  <script type="text/javascript">
    BUI.use('common/page');
  </script> 
<script type="text/javascript">
  BUI.use(['common/search','bui/overlay'],function (Search,Overlay) {
    
    var  columns = [
    	  {title:'序号',width:12,renderer : function(value,obj,index){ 
    	  		return (store.get("pageIndex"))*store.get("pageSize")+(index+1);
    	  }},
    	  {title:'图标',width:80,renderer : function(value,obj){
            var  delStr = "<img src='"+obj.bklogourl+"' width='28' height='20' onerror=\"this.src='/resources/images/tm.gif'\" class='tooltip' />"; 
            return delStr;
          }},
          {title:'银行名称',dataIndex:'name',width:80}, 
          {title:'银行代号',dataIndex:'bkcode',width:80}, 
          {title:'备注',dataIndex:'memo',width:200},  
          {title:'操作',width:80,renderer : function(value,obj,index){  
            var cvStr = '<span class="grid-command btn-upcv" data-id="'+obj.bkid+'" >图标</span>'; 
            var editStr =  Search.createLink({ //链接使用 此方式 
                text : '编辑', 
                href : '/system/sysset/bankinfo/enter_edit/?id='+obj.bkid
              }); 
            var delStr = '<span class="grid-command btn-del" >删除</span>'; 
            return cvStr + editStr + delStr;
          }} 
        ],
      store = Search.createStore('/system/sysset/bankinfo/search/',{
	     // autoLoad : true
	  }),
      gridCfg = Search.createGridCfg(columns,{  
      	forceFit: true,
        tbar : {
          items : [
            {
            	text : '<i class="icon-plus"></i>新建',
            	btnCls : 'button button-small',
            	handler:function(){
            		window.location.href='/system/sysset/bankinfo/enter_add/';
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
			          url:'/system/sysset/bankinfo/del/', 
			          data:{"id":record[0].bkid}, 
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
		            title:'上传图标',
		            width:400,
		            height:150,
		            closeAction : 'destroy', //每次关闭dialog释放
		            //配置DOM容器的编号
		            contentId:'fengmian',
		            success:function () { 
		            	$.ajaxFileUpload({     
							    url: "/system/common/upload/bank_logo/",   
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
