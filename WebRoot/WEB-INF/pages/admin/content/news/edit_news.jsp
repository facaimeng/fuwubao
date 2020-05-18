<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
         <input type="hidden" name="nid" value="${news.nid}"/>
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>SEO标题：</label>
            <div class="controls">
              <input name="ptitle" type="text"  class="input-normal control-text" value="${news.ptitle}"/> 
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>SEO关键词：</label>
            <div class="controls">
              <input name="pkeywords" type="text"  class="input-normal control-text" value="${news.pkeywords}"/> 
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">文章摘要：</label>
            <div class="controls control-row4">
              <textarea name="pdescription" class="input-large" type="text">${news.pdescription}</textarea>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>标题：</label>
            <div class="controls">
              <input name="title" type="text"  class="input-normal control-text" value="${news.title}"/> 
            </div>
          </div> 
        </div>
         <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>来源：</label>
            <div class="controls">
              <input name="source" type="text"  class="input-normal control-text" value="${news.source}"/> 
            </div>
          </div> 
        </div>
         <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>作者：</label>
            <div class="controls">
              <input name="author" type="text"  class="input-normal control-text" value="${news.author}"/> 
            </div>
          </div> 
        </div>
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>类别：</label>
            <div class="controls">
                <select > 
				    <option >${news.ntype.name}</option> 
			   </select>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>发布时间：</label>
            <div class="controls">
              <input name="pubtime" type="text" value="<fmt:formatDate value="${news.pubtime}" pattern="yyyy-MM-dd HH:MM"/>"  class="Wdate input-normal control-text" onFocus="WdatePicker({isShowClear:false,readOnly:false,dateFmt:'yyyy-MM-dd HH:mm'});"/>
              <font color="#FF0000">不填为当前时间</font> 
            </div>
          </div> 
        </div>  
        <div class="row">
          <div class="control-group span24">
            <label class="control-label">内容：</label>
            <div class="controls" style="height:480px;">
              <textarea name="content" class="input-large" type="text"  style=" width:780px;height:460px;">${news.content}</textarea>
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
	<script charset="utf-8" src="/resources/editor/kindeditor.js"></script>
	<script charset="utf-8" src="/resources/editor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="/resources/editor/plugins/code/prettify.js"></script>
    <script> 
    	var editor0;
		KindEditor.ready(function(K) {
			 
		 editor0 = K.create('textarea[name="content"]', {
				cssPath : '/resources/editor/plugins/code/prettify.css',
				uploadJson : '/resources/editor/jsp/upload_json.jsp',
				fileManagerJson : '/resources/editor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				resizeType:1,
				minHeight : 60
			});
			//上机题的编辑器 结束
			prettyPrint();
		});
		$(window).load(function(){
		    $("#save").on({
		      click:function(){ 
		      	  BUI.Message.Confirm('确认要提交么？',function(){
		      	  		editor0.sync();
			          	$.ajax({
					          url:'/system/content/news/edit/',
					          type:"POST",
					          data:$("#jbForm").serialize(),
					          success:function(result){  
					             if(result.code=='1'){
					                 BUI.Message.Alert(result.message,function() {
						                window.location.href='/system/content/news/enter_search/?ntype=${param.ntype}';
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