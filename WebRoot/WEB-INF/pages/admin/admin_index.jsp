<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <head>
  <title>管理平台</title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link href="/resources/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
   <link href="/resources/bui/css/bui-min.css" rel="stylesheet" type="text/css" />
   <link href="/resources/bui/css/main-min.css" rel="stylesheet" type="text/css" />
 </head>
 
 <body>
   <div class="header">
    <div class="dl-title"><span class=""> 管理平台</span></div>
    <div class="dl-log">欢迎您，<span class="dl-log-user">${userVO.realname }</span>
        <a id="logout" href="javascript:void(0);" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
   </div> 
   <div class="content">
    <div class="dl-main-nav">
      <ul id="J_Nav" class="nav-list ks-clear">   
      </ul>
    </div>  
    <ul id="J_NavContent" class="dl-tab-conten">
 
    </ul> 
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/config-min.js"></script>
  <script> 
     BUI.use('common/main',function(){
      var mlist = BUI.JSON.parse('${userVO.hasMenuJson}');  
      var navStr = '';
      var config=[];
      for(var i=0;i<mlist.length;i++){
      	 navStr = navStr + "<li class='nav-item'><div class='nav-item-inner "+mlist[i].icon+"'>"+mlist[i].name+"</div></li>" 
      	 var twoList = mlist[i].sonList; 
      	 if(twoList!=null && twoList!='' && twoList.length>0){  
      	 	 var two = {};
      	 	 two.id = 'fm_'+i;  
      	 	 two.menu = [];  
	      	 for(var j=0;j<twoList.length;j++){
	      	 	 var twoMenuOne = {};
	      	 	 twoMenuOne.text = twoList[j].name;   
	      	 	 twoMenuOne.items = [];
	      	 	 var threeList = twoList[j].sonList; 
	      	 	 if(threeList!=null && threeList!='' && threeList.length>0){ 
		      	 	 for(var k=0;k<threeList.length;k++){
		      	 	 	/**
		      	 	 	  var fourList = threeList[j].sonList; 
		      	 	 	  var btns = '';
		      	 	 	  if(fourList!=null && fourList!='' && fourList.length>0){ 
		      	 	 	  	 for(var h=0;h<fourList.length;h++){
		      	 	 	  	 		btns = btns + fourList[h].actionurl + '#'; 
		      	 	 	  	 } 
		      	 	 	  	 btns = btns.substring(0,btns.length-1);
		      	 	 	  }   **/  
		      	 	 	  twoMenuOne.items.push({ 
				              id:'sm_'+threeList[k].menuid,
				              text:threeList[k].name, 
				              path:threeList[k].path.substring(6,threeList[k].path.length).split('/').join(' &gt; '),
				              href:"/system/tourl?id="+threeList[k].menuid
		      	 	 	  }); 
		      	 	 } 
		      	 	 two.menu.push(twoMenuOne);
	      	 	 } 
	      	 } 	  
      	 	config.push(two);
      	 }
      }   
      $("#J_Nav").html(navStr); 
       
      new PageUtil.MainPage({
        modulesConfig : config
      }); 
    });
     $("#logout").on({
      		click:function(){
      		     BUI.Message.Confirm('确认要退出系统么？',function(){
		          	$.ajax({
			          url:'/system/logout/',  
			          success:function(result){ 
			             if(result.code=='1'){
			             	 BUI.Message.Alert('退出成功！',function() {
					              window.location.href='/account/fkadin';
					        },"success");  
				         }else{
				         	 BUI.Message.Alert('操作有误，请重试!',"error");  
				         }  
				      },
			          error: function (XMLHttpRequest, textStatus, errorThrown) {
			          	 BUI.Message.Alert("网络不畅，请重试!","warning");   
			          }
	        		}); 
		        },'question');  
      		}
      });
 </script>
 </body>
</html>