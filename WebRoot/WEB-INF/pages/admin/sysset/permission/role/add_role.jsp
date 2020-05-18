<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
 <head>
  <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/resources/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="/resources/bui/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="/resources/js/ztree/zTreeStyle/zTreeStyle.css" type="text/css"/>
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
            <label class="control-label"><s>*</s>角色名称：</label>
            <div class="controls">
              <input name="name" type="text"  class="input-normal control-text"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>绑定菜单：</label>
            <div  style="overflow-y: scroll;height: 400px;">
               <ul id="mtree" class="ztree"></ul>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">备注：</label>
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
        <input type="hidden" value="${param.roleid}" name="fid"/>
        <input type="hidden" id="path" name="path"/>
        <input type="hidden" id="selMenuIDS" name="selMenuIDS"/>
        <span id="pspan" style="display: none;"> <c:forEach items="${roleList}" var="item" varStatus="current">
          /${item.name}<c:if test="${current.last}">/</c:if></c:forEach></span>
      </form>
    </div> 
  </div>
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>  
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script> 
  <script type="text/javascript" src="/resources/js/ztree/jquery.ztree.core.min.js"></script>
  <script type="text/javascript" src="/resources/js/ztree/jquery.ztree.excheck.min.js"></script>
  
  <script> 
		$(window).load(function(){
		    $("#save").on({
		      click:function(){  
		          $("#path").val($("#pspan").html().replace(/[\b\f\n\r\t]/g, '').replace(/\ +/g,""));
		          
		          var mtree = $.fn.zTree.getZTreeObj("mtree");  
			      var nodes = mtree.getCheckedNodes(true);   
			      var tmps = '';
			      if(nodes!=null && nodes.length>0){
			      	for(var i = 0 ; i< nodes.length;i++){ 
			      		tmps=tmps+nodes[i].id+"#" 
			      	}   
			      }
			      tmps = tmps.substring(0,tmps.length-1);
			      $("#selMenuIDS").val(tmps); 
		          //return ;
		            
		          BUI.Message.Confirm('确认要提交么？',function(){
			          	$.ajax({
					          url:'/system/common/permission/role/add/',
					          type:"POST",
					          data:$("#jbForm").serialize(),
					          success:function(result){  
					             if(result.code=='1'){
					                 BUI.Message.Alert(result.message,function() {
						                window.location.href='/system/common/permission/role/enter_search/?roleid=${param.roleid}';
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
		    openMenu();
		}); 
	   var roleid ='${param.roleid}';
 	   function openMenu(){ 
			var setting = { 
				check: {
					enable: true, 
					autoCheckTrigger:true
				},  
				async: {
				enable: true,
				url:"/system/common/permission/role/get_has_menu/",
				autoParam:["id=menuFid"],
				otherParam: {"roleid":roleid} 
			  },
			  callback: {
				beforeCheck: zTreeBeforeCheck,
				onAsyncSuccess: zTreeOnAsyncSuccess
			  } 
			}; 
 
 	   		$.fn.zTree.init($("#mtree"), setting); 
 	   }
 	  function zTreeBeforeCheck(treeId, treeNode) {//如果某个node在点的时候是父节点且没被展开，则先让其展开
  			if(treeNode.isParent&&(!treeNode.open)){
				var mtree = $.fn.zTree.getZTreeObj("mtree"); 
 	  		
				mtree.expandNode(treeNode);  //自动ajax方法，然后回调zTreeOnAsyncSuccess
				
				return false;
			}   
		    return true;
	  };  
	  function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) { //在此方法中判断展开的子node中有parent否，有则继续展开
    		var childen = treeNode.children; 
			if(childen!=null && childen.length>0){
				var mtree = $.fn.zTree.getZTreeObj(treeId); 
				for(var i = 0 ; i< childen.length;i++){ 
	      			if(childen[i].isParent&&(!childen[i].open)){
	      				mtree.expandNode(childen[i]); 
	      			}
	      		}  
			} 
	  };
	  function subMenuRole(){
	  	  var mtree = $.fn.zTree.getZTreeObj("mtree"); 
	  	  //var ch = mtree.getChangeCheckedNodes();
	  	  //for(var i = 0 ; i< ch.length;i++){ 
	      //	  alert(ch[i].id);
	     // }  
	      var nodes = mtree.getCheckedNodes(true);   
	      var params = '';
	      if(nodes!=null && nodes.length>0){
	      	for(var i = 0 ; i< nodes.length;i++){ 
	      		params=params+"checkedMenuIDS="+nodes[i].id+"&" 
	      	}   
	      }
	      params = params + 'roleid='+roleid;
 	      
 	      $.post("/admin/permission/setMenuRolePerRole.action",params,
			   function(result){  
				 if(result=='SUC'){
			  			alert('操作成功!');  
				  }
			   }
		  );
		
	  }  
	</SCRIPT>

<body>
</html> 