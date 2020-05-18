<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
 <head>
  <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/resources/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="/resources/bui/css/bui-min.css" rel="stylesheet" type="text/css" /> 
    <link rel="stylesheet" href="/resources/editor/plugins/code/prettify.css" />
 </head>
 <body>
  <div class="rtop">
	<div class="rtop_l">当前位置：项目管理 &gt;  <a href="#">集团架构</a></div>
  </div> 
  <div class="container">
    <div class="row">
      <form id="jbForm" class="form-horizontal span24">
         <input type="hidden" name="adid" value="${advertise.adid}"/>
          <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>动画类别：</label>
            <div class="controls">
                <select > <c:if test="${advertise.adtype eq '1'}">
				    <option value="1">首页动画</option> </c:if><c:if test="${advertise.adtype eq '2'}">
				    <option value="2">产品动画</option> </c:if>
			   </select>
            </div>
          </div> 
        </div>
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>动画标题：</label>
            <div class="controls">
              <input name="adtitle" type="text"  class="input-normal control-text" value="${advertise.adtitle}"/> 
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>动画链接：</label>
            <div class="controls">
              <input name="adurl" type="text"  class="input-normal control-text"  value="${advertise.adurl}"/> 
            </div>
          </div> 
        </div>  
        <div class="row form-actions actions-bar" >
            <div class="span13 offset3 ">
              <button id="save" type="button" class="button button-primary">保存</button>
              <button id="reset" type="button" class="button">重置</button>
            </div>
        </div>
      </form>
    </div> 
  </div>
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>  
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script>  
    <script> 
    	 
		$(window).load(function(){
		    $("#save").on({
		      click:function(){ 
		      	  BUI.Message.Confirm('确认要提交么？',function(){ 
			          	$.ajax({
					          url:'/system/content/advertise/edit/',
					          type:"POST",
					          data:$("#jbForm").serialize(),
					          success:function(result){  
					             if(result.code=='1'){
					                 BUI.Message.Alert(result.message,function() {
						                window.location.href='/system/content/advertise/enter_search/?t=${advertise.adtype}';
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
		    });
		    $("#reset").on({
		      click:function(){ 
		      	  $("#jbForm").reset();
		      }
		    });
		});
	</script> 

<body>
</html> 