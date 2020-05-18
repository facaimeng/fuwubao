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
            <label class="control-label"><s>*</s>产品类别：</label>
            <div class="controls">
              <select name="prtid"><c:forEach items="${protList}" var="item">
                <option value="${item.prtid}">${item.name}</option></c:forEach>
              </select>
            </div>
          </div> 
        </div>  
         <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>短标题：</label>
            <div class="controls">
              <input name="title" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div>  
         <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>产品名称：</label>
            <div class="controls">
              <input name="pname" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div>  
        
         <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>起投金额：</label>
            <div class="controls">
              <input name="startbidmoneyStr" type="text"  class="input-normal control-text"/> <font color="#FF0000">0.00元</font> 
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>最高投金额：</label>
            <div class="controls">
              <input name="maxbidmoneyStr" type="text"  class="input-normal control-text"/> <font color="#FF0000">0.00元,0表示可一次购完</font> 
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>投资期限：</label>
            <div class="controls">
              <input name="bidperiod" type="text"  class="input-normal control-text"/> <font color="#FF0000">天</font> 
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>可持有期数：</label>
            <div class="controls">
              <input id="holdper" name="holdperiod" type="text"  class="input-normal control-text"/> <font color="#FF0000">0表示不限</font>
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>最小年化回报率：</label>
            <div class="controls">
              <input name="minreturnrate" type="text"  class="input-normal control-text"/>
            </div>
          </div>  
        </div>  
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>最大年化回报率：</label>
            <div class="controls">
              <input name="maxreturnrate" type="text"  class="input-normal control-text"/>
            </div>
          </div>  
        </div>  
        
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>回报率增长图：</label>
            <div class="span21 offset3 control-row-auto" style="margin-top: -23px;margin-left: 120px;">
              <div id="grid"></div>
            </div>
          </div>   
        </div> 
        
		<div class="row">
          <div class="control-group span8">
            <label class="control-label"> </label>
            <div class="controls span21" style="margin-left: 120px;">
           		 <font color="#FF0000">回报率增长期数不能超过可持有期数，并且回报率增长期数不能超过12期</font> 
            </div>
          </div>  
        </div>  
         
         <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>募集开始时间：</label>
            <div class="controls">
              <input name="buystart" type="text"  class="Wdate input-normal control-text" onFocus="WdatePicker({isShowClear:false,readOnly:false,dateFmt:'yyyy-MM-dd HH:mm'});"/>
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>募集结束时间：</label>
            <div class="controls">
              <input name="buyend" type="text"  class="Wdate input-normal control-text" onFocus="WdatePicker({isShowClear:false,readOnly:false,dateFmt:'yyyy-MM-dd HH:mm'});"/>
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>退出提前天数：</label>
            <div class="controls">
              <input name="exitday" type="text"  class="input-normal control-text"/> <font color="#FF0000">到期前几天申请退出</font> 
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>长周期提前退出天数：</label>
            <div class="controls">
              <input name="outexitday" type="text"  class="input-normal control-text"/> <font color="#FF0000">投资期限大于6个月，超过6个月后，每个月提前几天可申请退出</font> 
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>超过投资期限退出收益率：</label>
            <div class="controls">
              <input name="outexitrate" type="text"  class="input-normal control-text"/> <font color="#FF0000">超过投资期限退出，未满下一周期提前退出每月利率</font> 
            </div>
          </div>  
        </div>  
        
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">短介绍：</label>
            <div class="controls control-row4">
              <textarea name="memo" class="input-large" type="text"></textarea>
            </div>
          </div>
        </div>
        <div class="row form-actions actions-bar" >
            <div class="span13 offset3 ">
               <input id="rerate" name="returnrate" type="hidden" />
              <button id="save" type="button" class="button button-primary">保存</button>
              <button id="reset" type="button" class="button">重置</button>
            </div>
        </div>
      </form>
    </div> 
  </div>
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>   
<body>
</html> 