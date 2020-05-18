<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
        <input type="hidden" value="${puser.userid}" name="userid"/>
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>姓名：</label>
            <div class="controls">
              <input name="realname" type="text"  class="input-normal control-text" value="${puser.realname}"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>性别：</label>
            <div class="controls">
                <select name="sex" > 
                   <option value="M" <c:if test="${puser.sex eq 'M'}">selected="selected"</c:if>>男</option> 
	               <option value="F" <c:if test="${puser.sex eq 'F'}">selected="selected"</c:if>>女</option> 
	            </select> 
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>所在部门：</label>
            <div class="controls">
              <select name="pdid"><c:forEach items="${depList}" var="item">
                <option value="${item.pdid}" <c:if test="${puser.pdid eq item.pdid}">selected="selected"</c:if>>${item.name}</option></c:forEach>
              </select>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>手机号：</label>
            <div class="controls">
              <input id="phone" name="phone" type="text"  class="input-normal control-text" value="${puser.phone}"/>
              <input id="oldphone" name="oldphone" type="hidden" value="${puser.phone}"/>
            </div>
          </div> 
        </div> 
         <!--  
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>汇付ID：</label>
            <div class="controls">
              <input name="usrcustid" type="text" value="${puser.usrcustid}" class="input-normal control-text"/>
            </div>
          </div> 
        </div> -->
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>身份证：</label>
            <div class="controls">
              <input name="idnum" type="text"  class="input-normal control-text" value="${puser.idnum}"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>生日：</label>
            <div class="controls">
              <input name="birthday" type="text"  value="<fmt:formatDate value="${puser.birthday}" pattern="yyyy-MM-dd"/>" class="Wdate input-normal control-text" onFocus="WdatePicker({isShowClear:false,readOnly:false,dateFmt:'yyyy-MM-dd'});"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>籍贯：</label>
            <div class="controls">
              <input name="comefrom" type="text"  class="input-normal control-text" value="${puser.comefrom}"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>入职时间：</label>
            <div class="controls">
              <input name="joindate" type="text" value="<fmt:formatDate value="${puser.joindate}" pattern="yyyy-MM-dd"/>"  class="Wdate input-normal control-text" onFocus="WdatePicker({isShowClear:false,readOnly:false,dateFmt:'yyyy-MM-dd'});"/>
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>离职时间：</label>
            <div class="controls">
              <input name="quitdate" type="text" value="<fmt:formatDate value="${puser.quitdate}" pattern="yyyy-MM-dd"/>"  class="Wdate input-normal control-text" onFocus="WdatePicker({isShowClear:false,readOnly:false,dateFmt:'yyyy-MM-dd'});"/>
            </div>
          </div> 
        </div> 
        
         <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>设置密码：</label>
            <div class="controls">
              <input name="password" type="password"  class="input-normal control-text" value="RY888888"/> 
            </div>
          </div> 
        </div> 
         <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>一级收益率：</label>
            <div class="controls">
              <input name="rate1" type="text" id="rate1" class="input-normal control-text" value="${puser.rate1*100}"/> <font color="#FF0000">%</font> 
            </div>
          </div> 
        </div>  
         <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>二级收益率：</label>
            <div class="controls">
              <input name="rate2" type="text"  id="rate2" class="input-normal control-text" value="${puser.rate2*100}"/> <font color="#FF0000">%</font> 
            </div>
          </div> 
        </div> 
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>设置角色：</label>
            <div  class="span21 offset3 control-row-auto" style="margin-top: -23px;margin-left: 120px;">
                <div id="grid2"></div>
            </div>
          </div> 
        </div>  
        
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">备注：</label>
            <div class="controls control-row4">
              <textarea name="memo" class="input-large" type="text">${puser.memo}</textarea>
            </div>
          </div>
        </div>
        <div class="row form-actions actions-bar" >
            <div class="span13 offset3 ">
              <button id="save" type="button" class="button button-primary">保存</button>
              <button id="reset" type="button" class="button">重置</button>
            </div>
        </div>
        <input id="roleJson" name="roleJson" type="hidden" />
        <input id="isRoleChange" name="isRoleChange" type="hidden" /> 
      </form>
    </div> 
  </div>
  <div id="per" style="overflow-y: scroll;height: 400px;display: none;">
        <ul id="mtree" class="ztree"></ul>
  </div>
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>  
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script> 
  <script type="text/javascript" src="/resources/My97DatePicker/WdatePicker.js"></script>  
  <script type="text/javascript" src="/resources/js/ztree/jquery.ztree.core.min.js"></script>
  <script type="text/javascript" src="/resources/js/ztree/jquery.ztree.excheck.min.js"></script>
  <script> 
         BUI.use(['bui/grid','bui/data','bui/overlay','bui/form','common/search'],function (Grid,Data,Overlay,Search) { 
  		 
  		        var columns2 = [
			    	  {title:'序号',width:30,renderer : function(value,obj,index){
			    	  		return (index+1);
			    	  }}, 
			          {title:'角色名',dataIndex:'name',width:80},  
			          {title:'备注',dataIndex:'memo',width:300},   
			          {title:'操作',width:150,renderer : function(value,obj){ 
			            var deStr = '<span class="grid-command btn-per" >查看权限</span>'; 
			            return deStr;
			          }} 
			    ];
			    var roleJson = '${roleJson}'; 
			    var hasRoleJson = '${hasRoleJson}';   
			    var hasRoleList;
			    if(hasRoleJson!=null && hasRoleJson!=''){
			    	hasRoleList = BUI.JSON.parse(hasRoleJson);
			    }
			    var store2 = new Data.Store({
			   		data:BUI.JSON.parse(roleJson)
			    }),    
		        grid2 = new Grid.Grid({
			        render : '#grid2',
			        columns : columns2, 
			        forceFit : true,
			        store : store2,
			        itemStatusFields : { //设置数据跟状态的对应关系
				        selected : 'isSel' 
				    },
			        plugins : [Grid.Plugins.CheckSelection], 
		      	});
		    	grid2.render();   
		    	
		    	grid2.on('cellclick',function(ev){
				      var sender = $(ev.domTarget);  
				      if(sender.hasClass('btn-per')){ 
					        var record = [ev.record];  
					        if(record.length==1){
					        	openMenu(record[0].roleid);  
					        	var dialog = new Overlay.Dialog({
						            title:'查看 '+record[0].name+' 权限',
						            width:400,
						            height:650,
						            closeAction : 'destroy', //每次关闭dialog释放
						            //配置DOM容器的编号
						            contentId:'per',
						            buttons:[ 
						              {
						                text:'关闭',
						                elCls : 'button',
						                handler : function(){
						                  this.close();
						                }
						              }
						            ]
					           });
					           dialog.show();
					        }
			      	 } 
			    });
		 
			    $("#save").on({
		      		click:function(){   
		      			  BUI.Message.Confirm('确认要提交么？',function(){ 
		      			    var oldphone = $("#oldphone").val(); 
		      			    var phone = $("#phone").val(); 
		      			    if(oldphone!=phone){
		      			    	if(phone==null||phone==''){ 
		      			    	  setTimeout(function(){BUI.Message.Alert("请输入手机号码!","error");  }, 10); 
		      			    	 return;
			      			    }
						      	var flag = false; 
						      	$.ajax({
						            url:'/system/common/permission/users/checkPhone/',
						            data:{'phone':phone}, 
						            method:"POST",
						            async:false, 
						            success:function(result){   
								         if(result.code!='1'){ 
								         	 setTimeout(function(){BUI.Message.Alert("员工手机号已存在!","error");  }, 10); 
								         	  flag = true; 
								         }  
						              }, 
						              error: function (XMLHttpRequest, textStatus, errorThrown) { 
										setTimeout(function(){BUI.Message.Alert("网络不畅，请重试!","warning");    }, 10); 
										flag = true; 
									 }
					          	}); 
							    if(flag){
				                 	return ;
				            	}
		      			    }  
		      			    var selRoles = grid2.getSelection(); 
		      			    var selRoleJson ;
		      			    if(selRoles.length>0){ 
		      			    	selRoleJson = BUI.JSON.stringify(selRoles);
		      			    	$("#roleJson").val(selRoleJson);  
		      			    }  
		      			    var isChange = 'N'; 
		      			    if(hasRoleList!=null){ 
		      			    	if(selRoles.length!=selRoles.length){
		      			    		isChange = 'Y';
		      			    	}else{ 
		      			    		 
		      			    	    var regex = /\"roleid\":(.*?),\"fid\"/g;  
		      			    	    var selRoleIDS='';
								    while ((rs = regex.exec(selRoleJson)) != null) {
								        selRoleIDS = selRoleIDS+rs[1];
								    }   
								    regex = /\"roleid\":(.*?),\"userid\"/g;    
		      			    	    var hasRoleIDS='';
								    while ((rs = regex.exec(hasRoleJson)) != null) {
								        hasRoleIDS = hasRoleIDS+rs[1];
								    }
								    if(selRoleIDS!=hasRoleIDS){
								    	isChange = 'Y';
								    }
		      			    	} 
		      			    } 
		      			   
		      			    $("#isRoleChange").val(isChange);   
				          	$.ajax({
					          url:'/system/common/permission/users/edit/',
					          type:"POST",
					          data:$("#jbForm").serialize(),
					          success:function(result){  
					             if(result.code=='1'){
					                 BUI.Message.Alert(result.message,function() {
						                window.location.href='/system/common/permission/users/enter_search/';
						        	 },"success");   
						         }else{
						         	 setTimeout(function(){BUI.Message.Alert(result.message,"error");      }, 10); 
						         } 
					          },
					          error: function (XMLHttpRequest, textStatus, errorThrown) {
				                   setTimeout(function(){BUI.Message.Alert("网络不畅，请重试!","warning");      }, 10);   
					          }
				          });
				        },'question'); 	  
		     		}
			    }); 
		    	
  		});   
 	   function openMenu(roleid){ 
			var setting = { 
				check: {
					enable: true, 
					autoCheckTrigger:true
				},  
				async: {
				enable: true,
				url:"/system/common/permission/role/get_has_menu/?op=1",
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
	  	    } 
	  }; 
	</script> 

<body>
</html> 