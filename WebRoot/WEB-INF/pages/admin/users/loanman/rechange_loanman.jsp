<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
 <head>
  <title> 资源文件结构</title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link href="/resources/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
   <link href="/resources/bui/css/bui-min.css" rel="stylesheet" type="text/css" />
   <link href="/resources/bui/css/page-min.css" rel="stylesheet" type="text/css" />
   
   <style type="text/css">
	.form-horizontal .control-label {width: 130px;}
   </style>
 </head>
 
 <body>
	<div class="rtop" style="position:fixed;width:100%;">
		<div style="float:left;margin-right:5px;"><img src="/resources/bui/img/lang.png" /></div> <div class="rtop_l" style="float:left;">当前位置：后台充值管理 &gt; <a href="#">还款充值</a></div>
	</div>

    <div class="container" style="padding-top:50px;">
    <div class="row">
      <form id="loanMan_RechangeForm" class="form-horizontal span24" action="/system/users/loanman/rechange/" method="post">     
      
        <input type="hidden" name="usrCustid" value="${usrcustId}" />
        
        <!-- 标的名称 -->
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>充值金额：</label>
            <div class="controls">
              <input name="transAmt" type="text" class="input-large control-text" value="${transAmt}">
            </div>
          </div>
        </div>
        
        <div class="row form-actions actions-bar">
            <div class="span13 offset3 ">
              <button type="button" class="button button-primary" onclick="subForm();">确认充值</button>
            </div>
        </div>
      </form>
    </div>
  </div> 
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript">
      function subForm(){
          var transAmt = $("input[name='transAmt']").val();
          if(transAmt == null || transAmt==""){ 
              alert('请填写充值金额');
              return false;
          }
          $("#loanMan_RechangeForm").submit();
      }
  </script>
<body>
</html> 