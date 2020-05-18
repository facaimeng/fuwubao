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
        <input type="hidden" value="${loanman.lmid}" name="lmid"/> 
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>用户名称：</label>
            <div class="controls">
              <input name="name" type="text"  class="input-normal control-text" value="${loanman.name}"/>
              <font color="#FF0000">姓名或者公司名</font> 
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>类别：</label>
            <div class="controls">
                <select name="lmtype" > 
	              <option value="01" <c:if test="${loanman.lmtype eq '1'}">selected="selected"</c:if>>个人</option> 
	              <option value="02" <c:if test="${loanman.lmtype eq '2'}">selected="selected"</c:if>>企业</option> 
	            </select> 
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>证件号：</label>
            <div class="controls">
              <input name="idnum" type="text"  class="input-normal control-text" value="${loanman.idnum}"/>
              <font color="#FF0000">身份证或者执照号</font> 
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>电话号码：</label>
            <div class="controls">
              <input name="phone" type="text"  class="input-normal control-text" value="${loanman.phone}"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>地址：</label>
            <div class="controls">
              <input name="address" type="text"  class="input-normal control-text" value="${loanman.address}"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>汇付号：</label>
            <div class="controls">
              <input name="usrcustid" type="text"  class="input-normal control-text" value="${loanman.usrcustid}"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>汇付账户名：</label>
            <div class="controls">
              <input name="usrcustname" type="text"  class="input-normal control-text" value="${loanman.urgentman}"/>
            </div>
          </div> 
        </div>     
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">紧急联系人：</label>
            <div class="controls control-row4">
              <textarea name="urgentman" class="input-large" type="text">${loanman.urgentman}</textarea>
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
  <script type="text/javascript" src="/resources/My97DatePicker/WdatePicker.js"></script>  
  <script> 
		$(window).load(function(){
		    $("#save").on({
		      click:function(){ 
		      	  BUI.Message.Confirm('确认要提交么？',function(){
			          	$.ajax({
					          url:'/system/users/loanman/edit/',
					          type:"POST",
					          data:$("#jbForm").serialize(),
					          success:function(result){  
					             if(result.code=='1'){
					                 BUI.Message.Alert(result.message,function() {
						                window.location.href='/system/users/loanman/enter_search/';
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