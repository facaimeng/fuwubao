<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
 <head>
  <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/resources/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="/resources/bui/css/bui-min.css" rel="stylesheet" type="text/css" />
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
	<div class="rtop_l">当前位置：项目管理 &gt;  <a href="#">集团架构</a></div>
  </div> 
  <div class="container">
      <div class="detail-page"> 
      <form id="jbForm" >
      <div class="detail-section">  
        <h3>基本信息</h3>
        <div class="row detail-row">
          <div class="span8">
            <label>资产所属：</label><span class="detail-text">${project.address}</span>
          </div>
          <div class="span8">
            <label>资产类型：</label><span class="detail-text">${project.htype}</span>
          </div>
          <div class="span8">
            <label>资产情况：</label><span class="detail-text">${project.area}</span>
          </div>
        </div>
        
        <div class="row detail-row">
          <div class="span8">
            <label>资产详情：</label><span class="detail-text">${project.decorate}</span>
          </div>
          <div class="span8">
            <label>资产性质：</label><span class="detail-text">${project.protype}</span>
          </div><c:if test="${project.prtype eq '2'}"> 
          <div class="span8">
            <label>房屋坐标：</label><span class="detail-text">${project.coordinate}</span>
          </div></c:if>
        </div>
        
        <div class="row detail-row">
          <div class="span8">
            <label>资产总额：</label><span class="detail-text"><fmt:formatNumber value="${project.allprice/1000000}" pattern="#0.00" /> 万元</span>
          </div>
          <div class="span8">
            <label>市场价值：</label><span class="detail-text"><fmt:formatNumber value="${project.exprice/1000000}" pattern="#0.00" />万元</span>
          </div>
           <div class="span8">
            <label>市场平均售价：</label><span class="detail-text"><fmt:formatNumber value="${project.avgprice/1000000}" pattern="#0.00" />万元</span>
          </div> 
        </div> 
        <div class="row detail-row"> 
           <div class="span8">
            <label>资产备注：</label><span class="detail-text">${project.memo}</span>
          </div> 
        </div> 
      </div>
      <hr/> 
      <div class="detail-section"> 
        <h3>图片列表</h3>  
        <div class="row detail-row"> 
          <div class="span24">
            <div id="grid2"></div>
          </div>
        </div>
      </div>
       <hr/>
      <div class="detail-section"> 
        <h3>附件列表</h3>  
        <div class="row detail-row"> 
          <div class="span24">
            <div id="grid3"></div>
          </div>
        </div>
      </div>
      <hr/> 
   
       <div class="detail-section"> 
        <h3>项目审核</h3>  
        <div class="row detail-row">
        	<div class="span8">
           	 	<label>审核结果：</label> <span class="detail-text" style="color: red;font-weight: bold;">  ${project.state.name} </span>
          	</div>
          	<div class="span8">
           	 	<label>审核时间：</label> <span class="detail-text" style="color: red;font-weight: bold;">  <fmt:formatDate value="${project.checktime}" pattern="yyyy-MM-dd HH:mm"/></span>
          	</div>
          	<div class="span8">
           	 	<label>审核意见：</label><span class="detail-text">${project.ckmemo} </span>
          	</div>
        </div> 
      </div> 
      <hr/>
      <div class="detail-section"> 
        <h3>操作信息</h3>
        <div class="row detail-row">
          <div class="span8">
            <label>录入人员：</label><span class="detail-text">${project.opuname}</span>
          </div>
          <div class="span8">
            <label>录入时间：</label><span class="detail-text"><fmt:formatDate value="${project.addtime}" pattern="yyyy-MM-dd HH:mm"/></span>
          </div>  
        </div>  
      </div>  
       
       <div class="row form-actions actions-bar" >
            <div class="span13 offset3 ">
              
            </div>
        </div>
        <input name="proid" type="hidden" value="${project.proid}"  /> 
    </div>
    </form>
  </div>
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>  
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script> 
  <script type="text/javascript" src="/resources/My97DatePicker/WdatePicker.js"></script>  
  <script> 
  		 BUI.use(['bui/grid','bui/data','bui/form','common/search'],function (Grid,Data,Form,Search) {  
			    var columns2 = [
			    	  {title:'序号',width:30,renderer : function(value,obj,index){
			    	  		return (index+1);
			    	  }},
			    	  {title:'预览',width:80,renderer : function(value,obj){
			            var  delStr = "<img src='"+obj.furl+"' width='30' height='20' onerror=\"this.src='/resources/images/tm.gif'\" class='tooltip' />"; 
			            return delStr;
			          }}, 
			          {title:'图片名称',dataIndex:'name',width:80},
			          {title:'图片大小',dataIndex:'fsize',width:80}, 
			          {title:'备注',dataIndex:'memo',width:300},  
			          {title:'上传时间',dataIndex:'uptime',width:80,renderer:BUI.Grid.Format.datetimeRenderer},
			          {title:'操作',width:80} 
			    ];
			    var pimgsJson = '${pimgsJson}';   
			    var store2 = new Data.Store({
			   		data:BUI.JSON.parse(pimgsJson)
			    }),    
		        grid2 = new Grid.Grid({
			        render : '#grid2',
			        columns : columns2, 
			        forceFit : true,
			        store : store2,
			        listeners : {
				       aftershow : function(ev){ 
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
					   } 
				    },
		      	});
		    	grid2.render();  
			    
		        var columns3 = [
			    	  {title:'序号',width:30,renderer : function(value,obj,index){
			    	  		return (index+1);
			    	  }},
			          {title:'附件名称',dataIndex:'name',width:80},
			          {title:'附件大小',dataIndex:'fsize',width:80},
			          {title:'附件后缀',dataIndex:'ext',width:80},
			          {title:'备注',dataIndex:'memo',width:300},  
			          {title:'上传时间',dataIndex:'uptime',width:80,renderer:BUI.Grid.Format.datetimeRenderer},  
			          {title:'操作',width:80,renderer : function(value,obj){  
			              var downStr = Search.createLink({  
			                text : '下载', 
			                href : '/system/common/download/project_attach/?id='+obj.prattid
			              }) ;
			            return downStr ;
			          }} 
			    ];
			    var pattJson = '${pattJson}';   
			    var store3 = new Data.Store({
			   		data:BUI.JSON.parse(pattJson)
			    }),    
		        grid3 = new Grid.Grid({
			        render : '#grid3',
			        columns : columns3, 
			        forceFit : true,
			        store : store3
		      	});
		    	grid3.render(); 
		 
			    
		    	$(".rtop_l").html("当前位置："+top.topManager.getNav());
  		});  
  		 
  </script> 

<body>
</html> 