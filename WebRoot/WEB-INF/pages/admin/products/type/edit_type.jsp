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
       <input type="hidden"  name="prtid" value="${prot.prtid}"/> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>类型名称：</label>
            <div class="controls">
              <input name="name" type="text" value="${prot.name}"  class="input-normal control-text"/>
            </div>
          </div> 
        </div>  
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">备注：</label>
            <div class="controls control-row4">
              <textarea name="memo" class="input-large" type="text">${prot.memo}</textarea>
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
  <script src="/resources/js/cbai.dialog.js"></script>
  <script> 
		$(window).load(function(){
		    $("#save").on({
		      click:function(){     
		          $.ajax({
			          url:'/system/products/type/edit/',
			          type:"POST",
			          data:$("#jbForm").serialize(),
			          success:function(result){  
			             if(result.code=='1'){
				           	 BUI.Message.Alert(result.message,function() {
				                window.location.href='/system/products/type/enter_search/';
				        	 },"success");  
				         }else{
				         	  BUI.Message.Alert(result.message,"error");  
				         } 
			          },
			          error: function (XMLHttpRequest, textStatus, errorThrown) {
		                  BUI.Message.Alert("网络不畅，请重试!","warning");   
			          }
		          });
		      }
		    }); 
		});
	</script> 

<body>
</html> 