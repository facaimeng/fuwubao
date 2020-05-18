<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<div class="rtop_l">当前位置：${menu.path}</div>
</div>  
  <div class="container">
    <div class="row">
      <form id="searchForm" class="form-horizontal span32">
        <div class="row">
          <div class="control-group span8">
            <label class="control-label">类别：</label>
            <div class="controls">
              <select name="prtype"> 
                    <option value="">全部</option>
				    <option value="1">车辆</option> 
				    <option value="2">房屋</option> 
			    </select>
            </div>
          </div>
          <div class="control-group span8">
            <label class="control-label">资产编号：</label>
            <div class="controls">
              <input type="text" class="control-text" name="pronum">
            </div>
          </div>
          <div class="control-group span8">
            <label class="control-label">资产所属：</label>
            <div class="controls">
              <input type="text" class="control-text" name="address">
            </div>
          </div>
         <div class="control-group span8">
            <label class="control-label">资产类型：</label>
            <div class="controls">
              <input type="text" class="control-text" name="htype">
            </div>
          </div>
        </div>
        <div class="row">
           <div class="control-group span8">
            <label class="control-label">资产性质：</label>
            <div class="controls" >
               <input type="text" class="control-text" name="protype">
            </div>
          </div>
          <div class="span3 offset2">
            <button  type="button" id="btnSearch" class="button button-primary">搜索</button>
          </div>
        </div> 
         <input type="hidden" id="st" name="st" value="${param.st}">
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
          <div style="margin-left:130px;margin-top:5px;">
              <font color="#FF0000">jpg格式，500K以内</font>
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
  	 var enumObj = {"1":"车辆","2":"房屋"};
    var  columns = [
    	  {title:'序号',width:40,renderer : function(value,obj,index){
    	  		return (store.get("pageIndex"))*store.get("pageSize")+(index+1);
    	  }}, 
    	  {title:'预览',width:40,renderer : function(value,obj){
            var  delStr = "<img src='"+obj.coverurl+"' width='30' height='20' onerror=\"this.src='/resources/images/tm.gif'\" class='tooltip' />"; 
            return delStr;
          }},
    	  {title:'资产编号',dataIndex:'pronum',width:120},
    	  {title:'类别',dataIndex:'prtype',width:80,renderer:BUI.Grid.Format.enumRenderer(enumObj)},
          {title:'资产所属',dataIndex:'address',width:250},
          {title:'资产类型',dataIndex:'htype',width:100},
          {title:'资产情况',dataIndex:'area',width:100},
          {title:'资产详情',dataIndex:'decorate',width:80}, 
          {title:'资产性质',dataIndex:'protype',width:120}, 
          {title:'资产总额（万）',dataIndex:'allprice',width:100,renderer : function(value,obj,index){  
          		var sb = obj.allprice.toString(); 
          		return sb.substring(0, sb.length-6)+'.'+sb.substring(sb.length-6, sb.length-4);  
    	  }}, 
    	  {title:'市场价值（万）',dataIndex:'exprice',width:100,renderer : function(value,obj,index){ 
          		var sb = obj.exprice.toString(); 
          		return sb.substring(0, sb.length-6)+'.'+sb.substring(sb.length-6, sb.length-4);  
    	  }}, 
    	  {title:'市场平均售价（万）',dataIndex:'avgprice',width:100,renderer : function(value,obj,index){ 
          		var sb = obj.avgprice.toString(); 
          		return sb.substring(0, sb.length-6)+'.'+sb.substring(sb.length-6, sb.length-4);  
    	  }},  
          {title:'资产备注',dataIndex:'memo',width:300},  
          {title:'状态',dataIndex:'stname',width:80},
          {title:'录入人',dataIndex:'opuname',width:80},
          {title:'录入时间',dataIndex:'addtime',width:120,renderer:BUI.Grid.Format.datetimeRenderer},
          {title:'操作',width:250,renderer : function(value,obj){ 
             var opStr = '';
             <c:if test="${!empty mlist}"><c:forEach items="${mlist}" var="item" varStatus="current">  
			  	<c:if test="${item.tag eq 'sh'}">
			  		opStr = opStr +   Search.createLink({ 
			  			fid : '${menu.menuid}', 
			  			title: '资产审核',  
		                text : '审核', 
		                path : '${item.path}', 
		                href : '/system/projects/enter_check/?id='+obj.proid
			        }); 
			  	</c:if>
			  	<c:if test="${item.tag eq 'xq'}">
			  		opStr = opStr +   Search.createLink({
			  			fid : '${menu.menuid}',
			  			title: '资产详情',  
		                text : '详情', 
		                path : '${item.path}', 
		                href : '/system/projects/detail/?id='+obj.proid
			        }); 
			  	</c:if> </c:forEach> 
			 </c:if> 
            return opStr;
          }} ,
          {dataIndex:'proid', visible: false},
          {dataIndex:'state', visible: false}
        ],
        
      store = Search.createStore('/system/projects/search/',{
	     // autoLoad : true
	  }),
	  
      gridCfg = Search.createGridCfg(columns,{  
      	//forceFit: true, 
        tbar : {
          items : [
          	 
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
	   if(sender.hasClass('btn-upcv')){
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
							    url: "/system/common/upload/project_cover/",   
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
     //var navStr = '${menu.path}';
     //navStr = navStr.substring(6,navStr.length).split('/').join(' &gt; ');
     //$(".rtop_l").html("当前位置："+navStr); 
  });
  function repeatTitle(){ 
	
		var fileName = $("#areaFile").val(); 
		
		$("#showAreaFileName").val(fileName); 
  }
</script>

<body>
</html>  
