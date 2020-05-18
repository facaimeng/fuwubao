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
            <label class="control-label"><s>*</s>姓名：</label>
            <div class="controls">
              <input id="realname" name="realname" type="text"  class="input-normal control-text" />
            </div>
          </div> 
        </div>  
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>手机号码：</label>
            <div class="controls">
              <input id="phone" type="text" name="phone" class="input-normal control-text" />
            </div>
          </div> 
        </div> 
         <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>登录密码：</label>
            <div class="controls">
              <input id="logpwd" type="password" name="logpwd" class="input-normal control-text" value="RY666666"/>
              <font color="#FF0000">不填默认为RY666666</font> 
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label id="" class="control-label"><s>*</s>身份证：</label>
            <div class="controls">
              <input id="idnum" type="text" name="idnum"  class="input-normal control-text" />
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>汇付帐户ID：</label>
            <div class="controls">
              <input type="text" name="usrcustid" class="input-normal control-text" />
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>汇付帐户名：</label>
            <div class="controls">
              <input type="text" name="usrcustname" class="input-normal control-text" />
            </div>
          </div> 
        </div> 
        
       <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>账户余额：</label>
            <div class="controls">
              <input name="avlMoneyStr" type="text" onkeyup="two_bit_decimal(this)" class="input-normal control-text" />
              <font color="#FF0000">0.00元</font> 
            </div>
          </div> 
        </div>  
       <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>是否经纪人：</label>
            <div class="controls">
               <select name="ifbus" >  
	              <option value="N" >否</option> 
	              <option value="Y" >是</option> 
	            </select>
            </div>
          </div> 
        </div> 
        
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>是否绑卡：</label>
            <div class="controls">
               <select name="ifbind" > 
	              <option value="Y" >是</option> 
	              <option value="N" >否</option> 
	            </select>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>是否冻结：</label>
            <div class="controls">
               <select name="iffreeze" >  
	              <option value="N" >否</option> 
	              <option value="Y" >是</option> 
	            </select>
            </div>
          </div> 
        </div> 
        
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>推荐人类别：</label>
            <div class="controls">
               <select id="dadtype" name="dadtype" >  
	              <option value="1" >渠道</option> 
	              <option value="2" >经纪人</option> 
	            </select>
            </div>
          </div> 
        </div>  <!-- 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>推荐人姓名：</label>
            <div class="controls">
              <input id="dadname" name="dadname" type="text"  class="input-normal control-text" />
            </div>
          </div> 
        </div>   -->
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>推荐人手机：</label>
            <div class="controls">
              <input id="dadmemid" name="dadmemid" type="text"  class="input-normal control-text" />
            </div>
          </div> 
        </div>   
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>注册时间：</label>
            <div class="controls">
              <input name="regtime" type="text"  class="Wdate input-normal control-text" onFocus="WdatePicker({isShowClear:false,readOnly:false,dateFmt:'yyyy-MM-dd HH:mm'});"/>
              <font color="#FF0000">不填为当前时间</font> 
            </div>
          </div> 
        </div>   
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>开户时间：</label>
            <div class="controls">
              <input name="authtime" type="text"  class="Wdate input-normal control-text" onFocus="WdatePicker({isShowClear:false,readOnly:false,dateFmt:'yyyy-MM-dd HH:mm'});"/>
              <font color="#FF0000">不填为当前时间</font> 
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
  <script type="text/javascript" src="/resources/js/validate.utils.js"></script>
  <script type="text/javascript" src="/resources/js/validate.idcard.js"></script>
  <script type="text/javascript" src="/resources/My97DatePicker/WdatePicker.js"></script>  
  <script> 
		$(window).load(function(){
		
		    $("#dadtype11").on({
			     change:function(){ 
				     var dadtype = $(this).val();
				     if(dadtype==null||dadtype==''){
					     $("#dadmemid").val("");
					     $("#dadname").val("");
				     } 
				 }  
			});
		    $("#save").on({
		      click:function(){ 
		         var logpwd = $("#logpwd").val(); 
		         
		         if(validateRules.isNull(logpwd)){
		      	  	 BUI.Message.Alert("请输入登陆密码","error");  
				     return ;
			      }
		           
		      	  var realname = $("#realname").val(); 
		      	  if(validateRules.isNull(realname)){
		      	  	 BUI.Message.Alert("请输入姓名","error");  
				     return ;
			      }
		      	  
		      	  var phone = $("#phone").val(); 
		      	  if(validateRules.isNull(phone)||!validateRules.isMobile(phone)){
		      	  	 BUI.Message.Alert("请输入正确的手机号码","error");  
				     return ;
			      } 
			      var flag = false; 
			      $.ajax({
			            url:'/account/member/reg/checkPhone/',
			            data:{'phone':phone}, 
			            method:"POST",
			            async:false, 
			            success:function(result){   
					         if(result.code!='1'){ 
					         	  BUI.Message.Alert("用户手机号已在平台注册","error");  
					         	  flag = true; 
					         }  
			              }, 
			              error: function (XMLHttpRequest, textStatus, errorThrown) { 
							BUI.Message.Alert("网络不畅，请重试!","warning");  
							flag = true; 
						 }
		          }); 
			      if(flag){
                 	return ;
            	  }
			      var idnum = $("#idnum").val();
			      flag = false; 
		      	  if(!validateRules.isNull(idnum)){
		      	     if(!isIdCardNo(idnum)){ 
		      	     	BUI.Message.Alert("请输入正确的身份证号","error");  
				     	return ;
		      	     }else{
		      	     	$.ajax({
				            url:'/account/member/reg/check_idnum/',
				            data:{'idnum':idnum}, 
				            method:"POST",
				            async:false, 
				            success:function(result){   
						         if(result.code!='1'){ 
						         	  BUI.Message.Alert("用户身份证号在平台已绑定","error");  
						         	  flag = true; 
						         }  
				              }, 
				              error: function (XMLHttpRequest, textStatus, errorThrown) { 
								BUI.Message.Alert("网络不畅，请重试!","warning");  
								flag = true; 
							 }
		            	}); 
		      	     } 
			      } 
			      if(flag){
                 	return ;
            	  } 
			      var dadtype = $("#dadtype").val(); 
		      	   
	      	  	  var dadmemid = $("#dadmemid").val(); 
		      	  if(validateRules.isNull(dadmemid)){
		      	  	 BUI.Message.Alert("请输入推荐人手机号码","error");  
				     return ;
			      }
			      
			      var ckurl = "/system/users/member/check_uphone";
			      if(dadtype=='2'){
			      	  ckurl = "/account/member/reg/checkPhone/";
			      }
			      $.ajax({
			            url:ckurl,
			            data:{'phone':dadmemid}, 
			            method:"POST",
			            async:false, 
			            success:function(result){   
					         if(result.code!='0'){ 
					         	  if(dadtype=='1'){
					         	  	  BUI.Message.Alert("推荐人手机号不是平台员工","error");  
					         	  }else{
					         	  	  BUI.Message.Alert("推荐人手机号在平台未注册","error");  
					         	  } 
					         	  flag = true; 
					         }  
			              }, 
			              error: function (XMLHttpRequest, textStatus, errorThrown) { 
							BUI.Message.Alert("网络不畅，请重试!","warning");  
							flag = true; 
						 }
	          	  }); 
	          	  if(flag){
                 	return ;
            	  }    
			      var avlMoneyStr = $("#avlMoneyStr").val(); 
			      if(avlMoneyStr==null || avlMoneyStr==''){
			      	  $("#avlMoneyStr").val("0.00");
			      }
		          BUI.Message.Confirm('确认要提交么？',function(){
		          	$.ajax({
			          url:'/system/users/member/add/',
			          type:"POST",
			          data:$("#jbForm").serialize(),
			          success:function(result){  
			             if(result.code=='1'){
			                 BUI.Message.Alert(result.message,function() {
				                window.location.href='/system/users/member/enter_search/';
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