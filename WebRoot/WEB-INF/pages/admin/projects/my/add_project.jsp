<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<div class="rtop_l"></div>
  </div> 
  <div class="container" >
    <div class="row">
      <form id="jbForm" class="form-horizontal span24">     
      
        <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>资产类别：</label>
            <div class="controls">
                <select id="prtype" name="prtype"> 
				    <option value="1">车辆</option> 
				    <option value="2">房屋</option> 
			    </select>
            </div>
          </div> 
        </div> 
        
        <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>资产所属：</label>
            <div class="controls">
              <input name="address" type="text"  class="input-normal control-text"/> <font color="#FF0000"><font class="t1">品牌</font><font class="t2" style="display: none;">小区</font></font>
            </div>
          </div> 
        </div> 
         
        <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>资产类型：</label>
            <div class="controls">
              	 <input name="htype" type="text"  class="input-normal control-text"/> <font color="#FF0000"><font class="t1">型号</font><font class="t2" style="display: none;">户型</font></font>
            </div>
          </div> 
        </div>  
        
        <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>资产情况：</label>
            <div class="controls">
               <input name="area" type="text"  class="input-normal control-text"/> <font color="#FF0000"><font class="t1">年限</font><font class="t2" style="display: none;">面积</font></font>
            </div>
          </div> 
        </div> 
        
        <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>资产详情：</label>
            <div class="controls">
              <input name="decorate" type="text"  class="input-normal control-text"/> <font color="#FF0000"><font class="t1">公里数</font><font class="t2" style="display: none;">装修情况</font></font>
            </div>
          </div> 
        </div>  
        <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>资产性质：</label>
            <div class="controls">
              <input name="protype" type="text"  class="input-normal control-text"/> <font color="#FF0000"><font class="t1">运营/非运营</font><font class="t2" style="display: none;">住宅/商业</font></font>
            </div>
          </div> 
        </div> 
         <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>资产总额：</label>
            <div class="controls">
              <input name="allpriceStr" type="text" onkeyup="two_bit_decimal(this)" class="input-normal control-text"/> <font color="#FF0000">0.00万元</font>
            </div>
          </div> 
        </div>  
        <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>市场价值：</label>
            <div class="controls">
              <input name="expriceStr" type="text" onkeyup="two_bit_decimal(this)" class="input-normal control-text"/> <font color="#FF0000">0.00万元</font>
            </div>
          </div> 
        </div>   
        <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>市场平均售价：</label>
            <div class="controls">
              <input name="avgpriceStr" type="text" onkeyup="two_bit_decimal(this)" class="input-normal control-text"/> <font color="#FF0000">0.00万元</font>
            </div>
          </div> 
        </div>   
        
        <div class="row t2" style="display: none;">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>房屋坐标：</label>
            <div class="controls">
              <input name="coordinate" type="text"  class="input-normal control-text"/>
              <font color="#FF0000"><a href="http://api.map.baidu.com/lbsapi/getpoint/index.html" target="_blank">拾取坐标</a></font> 
            </div>
          </div> 
        </div> 
        
        <div class="row">
          <div class="control-group span24">
            <label class="control-label">资产备注：</label>
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
  <script type="text/javascript" src="/resources/js/cbai.dateutils.js?i=2"></script>
  <script type="text/javascript" src="/resources/js/validate.utils.js"></script>
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script>
  <script> 
		$(window).load(function(){
			var fmenuid = "sm_"+top.topManager.getFid();
			 $("#prtype").on({
			 	change:function(){
			 		var val = $(this).val();
			 		if(val=='1'){
			 			$(".t2").hide();
			 			$(".t1").show();
			 		}else{
			 			$(".t2").show();
			 			$(".t1").hide();
			 		}
			 	} 
			 });
		    $("#save").on({
		      click:function(){   
		         BUI.Message.Confirm('确认要提交么？',function(){  
			          $.ajax({
				          url:'/system/projects/my/add/',
				          type:"POST",
				          data:$("#jbForm").serialize(),
				          success:function(result){  
				             if(result.code=='1'){
				                 BUI.Message.Alert(result.message,function() {
				                 	top.topManager.openPage({
								      id : fmenuid,
								      isClose : true,
								      reload : true
								    });
					                //window.location.href='/system/projects/enter_search/';
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
		    $(".rtop_l").html("当前位置："+top.topManager.getNav());
		});
	</script>  
<body>
</html> 