<%@ page language="java" pageEncoding="UTF-8"%>
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
       <input type="hidden"  name="aptid" value="${articleptype.aptid}"/> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>一级分类名称：</label>
            <div class="controls">
              <input name="name" type="text" value="${articleptype.name}"  class="input-normal control-text"/>
            </div>
          </div> 
        </div>  
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">备注：</label>
            <div class="controls control-row4">
              <textarea name="memo" class="input-large" type="text">${articleptype.memo}</textarea>
            </div>
          </div>
        </div>
        <div class="row form-actions actions-bar" >
            <div class="span13 offset3 ">
              <button id="save" type="button" class="button button-primary">保存</button> 
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
					          url:'/system/content/article/aptype/edit/',
					          type:"POST",
					          data:$("#jbForm").serialize(),
					          success:function(result){  
					             if(result.code=='1'){
					                 BUI.Message.Alert(result.message,function() {
						                window.location.href='/system/content/article/aptype/enter_search/?aptype=${articleptype.aptype}';
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
		});
	</script> 

<body>
</html> 