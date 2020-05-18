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
            <label class="control-label"><s>*</s>企业名称：</label>
            <div class="controls">
              <input name="name" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>类别：</label>
            <div class="controls">
                <select name="ctype" > 
	              <option value="${ctype}" selected="selected">${ctype.name}</option> 
	            </select> 
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>营业执照号：</label>
            <div class="controls">
              <input name="licnum" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>税务登记号：</label>
            <div class="controls">
              <input name="taxnum" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>组织机构代码：</label>
            <div class="controls">
              <input name="orgnum" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>注册地址：</label>
            <div class="controls">
              <input name="regaddress" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>常用地址：</label>
            <div class="controls">
              <input name="realaddress" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>公司联系电话：</label>
            <div class="controls">
              <input name="contactphone" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>法人：</label>
            <div class="controls">
              <input name="dutyman" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>法人身份证：</label>
            <div class="controls">
              <input name="dutyidnum" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>法人电话：</label>
            <div class="controls">
              <input name="dutyphone" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>常用联系人：</label>
            <div class="controls">
              <input name="contactman" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div>  
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>常用联系人身份：</label>
            <div class="controls">
              <input name="contactidnum" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div>  
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>常用联系人电话：</label>
            <div class="controls">
              <input name="ofenmanphone" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div>      <!--  
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>开户银行：</label>
            <div class="controls">
              <input name="bkname" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div>   
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>支行名称：</label>
            <div class="controls">
              <input name="bkaddress" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div>  
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>开户银行账户：</label>
            <div class="controls">
              <input name="bkcardnum" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div>  -->
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>注册资金：</label>
            <div class="controls">
              <input name="regcapital" type="text"  class="input-normal control-text"/> <font color="#FF0000">万</font> 
            </div>
          </div> 
        </div>  
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>成立日期：</label>
            <div class="controls">
              <input name="foundate" type="text" class="Wdate input-normal control-text" onFocus="WdatePicker({isShowClear:false,readOnly:false,dateFmt:'yyyy-MM-dd'});"/>
            </div>
          </div> 
        </div>  <!--
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>经营期限：</label>
            <div class="controls">
              <input name="busperiod" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div>   
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">主营业务：</label>
            <div class="controls control-row4">
              <textarea name="mainbus" class="input-large" type="text"></textarea>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">经营状况：</label>
            <div class="controls control-row4">
              <textarea name="busstate" class="input-large" type="text"></textarea>
            </div>
          </div>
        </div>  -->
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">企业简介：</label>
            <div class="controls control-row4">
              <textarea name="memo" class="input-large" type="text"></textarea>
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
					          url:'/system/users/company/add/',
					          type:"POST",
					          data:$("#jbForm").serialize(),
					          success:function(result){  
					             if(result.code=='1'){
					                 BUI.Message.Alert(result.message,function() {
						                window.location.href='/system/users/company/enter_search/?t=${param.t}';
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