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
              <input name="name" type="text" value="${perrole.name}"  class="input-normal control-text"/>
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
              <textarea name="memo" class="input-large" type="text">${perrole.memo}</textarea> 
            </div>
          </div>
        </div><!-- 
         <textarea class="input-large" type="text">${userVO.hasMenuJson}</textarea>
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">addIDS:</label>
            <div class="controls control-row4">
                <textarea id="mmo" class="input-large" type="text"></textarea><br/>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">delIDS:</label>
            <div class="controls control-row4">
                <textarea id="mmo1" class="input-large" type="text"></textarea><br/>
            </div>
          </div>
        </div> -->
        <div class="row form-actions actions-bar" >
            <div class="span13 offset3 ">
              <button id="save" type="button" class="button button-primary">保存</button>
              <button id="reset" type="button" class="button">重置</button>
            </div>
        </div>   
         <input type="hidden" id="selMenuIDS" name="selMenuIDS"/>
         <input type="hidden" id="addIDS" name="addMenuIDS"/>
         <input type="hidden" id="delIDS" name="delMenuIDS"/>
         <input type="hidden" value="${perrole.roleid}" name="roleid"/> 
      </form>
    </div> 
  </div>
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>  
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script> 
  <script type="text/javascript" src="/resources/js/ztree/jquery.ztree.core.min.js"></script>
  <script type="text/javascript" src="/resources/js/ztree/jquery.ztree.excheck.min.js"></script> 
  <script>  
		var rootIDS = '${hasMenusStr}'.split(',');
		var opIDS = rootIDS;
		var addIDS = [];
		var delIDS = [];  
		$(window).load(function(){
		    $("#save").on({
		      click:function(){    
		          //var mtree = $.fn.zTree.getZTreeObj("mtree");  
			      //var nodes = mtree.getCheckedNodes(true);   
			      //var tmps = '';
			      //if(nodes!=null && nodes.length>0){
			      	//for(var i = 0 ; i< nodes.length;i++){ 
			      		//tmps=tmps+nodes[i].id+"#" 
			      	//}   
			      //}
			      //tmps = tmps.substring(0,tmps.length-1);
			      //$("#selMenuIDS").val(tmps);  
			      if(addIDS.length>0){
			      	 $("#addIDS").val(addIDS);
			      }else{
			      	 $("#addIDS").val("-1");
			      }
			      if(delIDS.length>0){
			      	 $("#delIDS").val(delIDS);
			      }else{
			      	 $("#delIDS").val("-1");
			      } 
		          BUI.Message.Confirm('确认要提交么？',function(){
			          	$.ajax({
					          url:'/system/common/permission/role/edit/',
					          type:"POST",
					          data:$("#jbForm").serialize(),
					          success:function(result){  
					             if(result.code=='1'){
					                 BUI.Message.Alert(result.message,function() {
						                window.location.href='/system/common/permission/role/enter_search/';
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
	   var roleid ='${perrole.roleid}';
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
				onAsyncSuccess: zTreeOnAsyncSuccess,
				onCheck: zTreeOnCheck
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
	  		if(treeNode!=null){
	  			var childen = treeNode.children; 
				if(childen!=null && childen.length>0){
					var mtree = $.fn.zTree.getZTreeObj(treeId); 
					for(var i = 0 ; i< childen.length;i++){ 
		      			if(childen[i].isParent&&(!childen[i].open)){
		      				mtree.expandNode(childen[i]); 
		      			}
		      		}  
				} 
	  		}else{ 
				//var rids = msg.match(/id:(\d*)/g).join("");
				//rootIDS = rids.substring(3,rids.length).split("id:"); 
	  		} 
	  };
	  function zTreeOnCheck(event,treeId, node){  
	  	  var nid = node.id.toString();
	  	  if(node.checked==true){ 
	  	  	  var index = $.inArray(nid,delIDS);
	  	  	 // alert(node.name);
	  	  	  if(delIDS.length>0&&index!=-1){ 
	  	  	      delIDS.splice(index,1);
	  	  	  }else if($.inArray(nid,opIDS)==-1){
	  	  	  	  addIDS.push(nid); 
	  	  	  }   
	  	  }else if(node.checked==false){   
	  	  	  if(opIDS.length>0&&$.inArray(nid,opIDS)!=-1){ 
	  	  	      delIDS.push(nid);
	  	  	  }else if(addIDS.length>0){
	  	  	  	  addIDS.splice($.inArray(nid,addIDS),1);
	  	  	  } 
	  	  }
	  	 // $("#mmo").val("").val(addIDS);
	  	  //$("#mmo1").val("").val(delIDS);
	  }
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
	  function isRoot(id){
	  	 for (var i = 0; i < rootIDS.length; i++) {
		　　　　if (rootIDS[i] == id) {
		　　　　　　return true;
		 	}
		 }
		 return false; 		 			  			 	
	  } 
	  function rootOp(id){
	  		
	  			 	
	  } 
	  function addOp(id){
	  	 
	  } 
	  function delOp(id){
	  	 
	  } 
	</SCRIPT>

<body>
</html> 