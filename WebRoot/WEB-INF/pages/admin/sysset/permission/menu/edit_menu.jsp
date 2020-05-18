<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
 <head>
  <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/resources/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="/resources/bui/css/bui-min.css" rel="stylesheet" type="text/css" />
 </head>
 <body>
  <div class="rtop">
	<div class="rtop_l">当前位置：项目管理 &gt;  <a href="#">集团架构</a></div>
  </div> 
  
  <div class="container">
    <div class="row">
      <form id="jbForm" class="form-horizontal span24">
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>菜单名称：</label>
            <div class="controls">
              <input name="name" type="text"  class="input-normal control-text" value="${permenu.name}"/>
              <input name="oldname" type="hidden" value="${permenu.name}"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>标签：</label>
            <div class="controls">
              <input name="tag" type="text" value="${permenu.tag}" class="input-normal control-text"/>
            </div>
          </div> 
        </div>
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>链接：</label>
            <div class="controls">
              <input name="actionurl" type="text"  class="input-normal control-text"  value="${permenu.actionurl}"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">备注：</label>
            <div class="controls control-row4">
              <textarea name="memo" class="input-large" type="text">${permenu.memo}</textarea>
            </div>
          </div>
        </div>
        <div class="row form-actions actions-bar" >
            <div class="span13 offset3 ">
              <button id="save" type="button" class="button button-primary">保存</button>
              <button id="reset" type="button" class="button">重置</button>
            </div>
        </div> 
        <input type="hidden" value="${permenu.menuid}" name="menuid"/> 
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
					          url:'/system/common/permission/menu/edit/',
					          type:"POST",
					          data:$("#jbForm").serialize(),
					          success:function(result){  
					             if(result.code=='1'){
					                 BUI.Message.Alert(result.message,function() {
						                window.location.href='/system/common/permission/menu/enter_search/?menuid=${permenu.fid}';
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