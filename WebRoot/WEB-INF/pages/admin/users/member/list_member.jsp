<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <head>
  <title></title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
     <link href="/resources/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
   <link href="/resources/bui/css/bui-min.css" rel="stylesheet" type="text/css" />
   <link href="/resources/bui/css/page-min.css" rel="stylesheet" type="text/css" />  
 </head>
 <body>
 <div class="rtop">
	<div class="rtop_l">当前位置：项目管理 &gt; <a href="#">集团架构</a></div>
</div> 

  <div class="container">
    <div class="row" >
      <form id="searchForm" class="form-horizontal">
        <div class="row" >
          <div class="control-group span9">
            <label class="control-label">汇付ID：</label>
            <div class="controls">
              <input type="text" class="control-text" name="usrcustid">
            </div>
          </div>
          <div class="control-group span9">
            <label class="control-label">真实姓名：</label>
            <div class="controls">
              <input type="text" class="control-text" name="realname">
            </div>
          </div>
          <div class="control-group span9">
            <label class="control-label">身份证：</label>
            <div class="controls">
              <input type="text" class="control-text" name="idnum">
            </div>
          </div>
          <div class="control-group span9">
            <label class="control-label">手机号码：</label>
            <div class="controls">
              <input type="text" class="control-text" name="phone">
            </div>
          </div> 
          <div class="control-group span9">
            <label class="control-label">是否经纪人：</label>
            <div class="controls" >
              <select name="ifbus">
                <option value="">全部</option>
                <option value="Y">是</option>
                <option value="N">否</option>
              </select>
            </div>
          </div>
          <div class="control-group span9">
            <label class="control-label">是否绑卡：</label>
            <div class="controls" >
              <select name="ifbind">
                <option value="">全部</option>
                <option value="Y">是</option>
                <option value="N">否</option>
              </select>
            </div>
          </div>
          <div class="control-group span9">
            <label class="control-label">是否冻结：</label>
            <div class="controls" >
              <select name="iffreeze">
                <option value="">全部</option>
                <option value="Y">是</option>
                <option value="N">否</option>
              </select>
            </div>
          </div>
          <div class="control-group span9">
            <label class="control-label">状态：</label>
            <div class="controls" >
              <select name="state">
                <option value="">全部</option>
                <option value="NOAUTH">未认证</option>
                <option value="NORMAL">正常</option>
                <option value="LOCKED">锁定</option>
              </select>
            </div>
          </div>
           <div class="control-group span9">
            <label class="control-label">计算截止时间：</label> 
            <div class="controls">
            	<input type="text" class="calendar calendar-time" name="calcDead"><span> 
            </div>
          </div> 
         <div class="control-group span9">
            <label class="control-label">用户生日：</label> 
            <div class="controls">
            	<input type="text" class="calendar" name="birthStart"><span> - </span><input name="birthEnd" type="text" class="calendar">
            </div>
          </div> 
           <div class="control-group span9">
            <label class="control-label">注册时间：</label> 
            <div class="controls">
              <input type="text" class="calendar" name="regStart"><span> - </span><input name="regEnd" type="text" class="calendar">
            </div>
          </div>
           <div class="control-group span9">
            <label class="control-label">开户时间：</label> 
            <div class="controls">
              <input type="text" class="calendar" name="openStart"><span> - </span><input name="openEnd" type="text" class="calendar">
            </div>
          </div> 
        </div>  
        <div class="row">
        	<div class="control-group span8">
	            <label class="control-label">&nbsp;&nbsp;</label>
	            <div class="controls" >
	            	<span><button type="button" id="btnSearch" class="button button-primary">搜索</button></span>
	            	<span><button type="button" id="btnExport" onclick="exports(this);" class="button button-export">导出</button></span>
	            </div>
          	</div>
        </div>
      </form>
    </div>
    
    <hr/>
    <div class="search-grid-container">
      <div id="grid"></div>
    </div>
  </div>
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="/resources/js/cbai.commonutil.js"></script>
  
  <script type="text/javascript" src="/resources/js/jquery.cookie.js"></script>
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/config-min.js"></script>
  <script type="text/javascript">
    BUI.use('common/page');
  </script>   
<script type="text/javascript">
  BUI.use(['common/search','bui/overlay','bui/tab'],function (Search,Overlay,Tab) {
    var enumObj = {"Y":"是","N":"否"};
    var enumObj1 = {"M":"男","F":"女"};
    var enumObj2 = {"1":"员工","2":"会员"};
    var  columns = [
    	  {title:'序号',width:30,renderer : function(value,obj,index){
    	  		return (store.get("pageIndex"))*store.get("pageSize")+(index+1);
    	  }},
          {title:'汇付ID',dataIndex:'usrcustid',width:120,renderer : function(value,obj){
            
            var openStr = obj.usrcustid; 
            if(openStr==null || openStr==''){ 
              	openStr = '<span class="grid-command btn-open" >未开户</span>';
            }  
            return openStr;
          }},
          {title:'姓名',dataIndex:'realname',width:50}, 
          {title:'性别',dataIndex:'sex',width:50,renderer:BUI.Grid.Format.enumRenderer(enumObj1)}, 
          {title:'生日',dataIndex:'birth',width:80,renderer:BUI.Grid.Format.dateRenderer},
          {title:'手机号',dataIndex:'phone',width:100},
          {title:'身份证',dataIndex:'idnum',width:150}, 
          {title:'可用余额',dataIndex:'avlmoney',width:100,renderer : function(value,obj,index){  
          		var sb = obj.avlmoney;  
          		if(sb==null||sb==''){
          			return "0.00";
          		}
          		sb = sb.toString(); 
          		if(sb.length<=2){
          			return "0.0"+sb;
          		}else{ 
          			return sb.substring(0, sb.length-2)+'.'+sb.substring(sb.length-2, sb.length);
          		}  
    	  }}, 
    	  {title:'在投金额',dataIndex:'bidingmoney',width:100,renderer : function(value,obj,index){  
          		var sb = obj.bidingmoney;  
          		if(sb==null||sb==''){
          			return "0.00";
          		}
          		sb = sb.toString(); 
          		if(sb.length<=2){
          			return "0.0"+sb;
          		}else{ 
          			return sb.substring(0, sb.length-2)+'.'+sb.substring(sb.length-2, sb.length);
          		}  
    	  }},  
    	  {title:'总投金额',dataIndex:'allbidmoney',width:100,renderer : function(value,obj,index){  
          		var sb = obj.allbidmoney;  
          		if(sb==null||sb==''){
          			return "0.00";
          		}
          		sb = sb.toString(); 
          		if(sb.length<=2){
          			return "0.0"+sb;
          		}else{ 
          			return sb.substring(0, sb.length-2)+'.'+sb.substring(sb.length-2, sb.length);
          		}  
    	  }},   
          {title:'绑卡',dataIndex:'ifbind',width:50,renderer:BUI.Grid.Format.enumRenderer(enumObj)},
          {title:'冻结',dataIndex:'iffreeze',width:50,renderer:BUI.Grid.Format.enumRenderer(enumObj)},
          {title:'经纪人',dataIndex:'ifbus',width:50,renderer:BUI.Grid.Format.enumRenderer(enumObj)},
          {title:'状态',dataIndex:'stname',width:50},   
          {title:'经纪人',width:80,renderer : function(value,obj){
          		var grand = obj.dadinfo.dadinfo; 
          		if(grand!=null){ 
          			return "<img src='/resources/bui/img/dadtype"+grand.mytype+".png' width='16' height='16' /> "+grand.realname;
          		}else{
          			return '';
          		}
          }}, 
          {title:'经纪人手机',width:120,renderer : function(value,obj){ 
          		var grand = obj.dadinfo.dadinfo;  
          		if(grand!=null){
          			return grand.phone;
          		}else{
          			return '';
          		} 
          }},
          {title:'渠道',width:80,renderer : function(value,obj){
          		var dad = obj.dadinfo; 
          		if(dad!=null){
          			return "<img src='/resources/bui/img/dadtype"+dad.mytype+".png' width='16' height='16' /> "+dad.realname;
          		}else{
          			return '';
          		}
          }}, 
          {title:'渠道手机',width:120,renderer : function(value,obj){
          		var dad = obj.dadinfo; 
          		if(dad!=null){
          			return dad.phone;
          		}else{
          			return '';
          		} 
          }},
          {title:'注册时间',dataIndex:'regtime',width:120,renderer:BUI.Grid.Format.datetimeRenderer},
          {title:'开户时间',dataIndex:'authtime',width:120,renderer:BUI.Grid.Format.datetimeRenderer},
          {title:'操作',width:150,renderer : function(value,obj){
            var deStr =  Search.createLink({  
                text : '详情', 
                href : '/system/users/member/detail/?id='+obj.memid
            }); 
            var editStr =  Search.createLink({  
                text : '编辑', 
                href : '/system/users/member/enter_edit/?id='+obj.memid
            });     
            var frStr = '<span class="grid-command btn-freeze" data-op="freeze">冻结</span>';
            if(obj.iffreeze=='Y'){
            	frStr = '<span class="grid-command btn-freeze" data-op="unfreeze">解冻</span>';
            }
            var delStr = '<span class="grid-command btn-lock" data-op="lock">锁定</span>';
            if(obj.state=='LOCKED'){
            	delStr = '<span class="grid-command btn-lock" data-op="unlock">解锁</span>';
            }
            var openLoanStr = "";
            if(obj.ifloanman=='N'){
            	openLoanStr = Search.createLink({  
                	text : '开通借款', 
                	href : '/system/users/member/enter_openloan/?id='+obj.memid
            	});     
            }   
            return deStr + editStr + frStr + delStr + openLoanStr ;
          }}, 
         {dataIndex:'memid', visible: false} ,
         {dataIndex:'state', visible: false}
        ],
        
      store = Search.createStore('/system/users/member/search/',{
	      //autoLoad : true
	  }),
	  
      gridCfg = Search.createGridCfg(columns,{  
        tbar : {
          items : [
            {
            	text : '<i class="icon-plus"></i>新建',
            	btnCls : 'button button-small',
            	handler:function(){
            		window.location.href='/system/users/member/enter_add/';
            	}
            } 
          ]
        }, 
        plugins : [BUI.Grid.Plugins.ColumnResize] 
      });

    var  search = new Search({
        store : store,
        gridCfg : gridCfg  
    }),
      
    grid = search.get('grid'); 
 
    grid.on('cellclick',function(ev){
	      var sender = $(ev.domTarget); //点击的Dom
	      if(sender.hasClass('btn-lock')){
	        var op = sender.attr("data-op"); 
	        var record = [ev.record];  
	        if(record.length==1){
		        BUI.Message.Confirm('确认要操作选中的记录么？',function(){
		          	$.ajax({
			          url:'/system/users/member/lock/', 
			          data:{"op":op,"id":record[0].memid},
			          type:"POST",
			          success:function(result){ 
			             if(result.code=='1'){
			             	 BUI.Message.Alert(result.message,function() {
					              search.load();
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
      }else if(sender.hasClass('btn-freeze')){
       	  	var op = sender.attr("data-op"); 
	        var record = [ev.record];  
	        if(record.length==1){
		        BUI.Message.Confirm('确认要操作选中的记录么？',function(){
		          	$.ajax({
			          url:'/system/users/member/freeze/', 
			          data:{"op":op,"id":record[0].memid},
			          type:"POST",
			          success:function(result){ 
			             if(result.code=='1'){
			             	 BUI.Message.Alert(result.message,function() {
					              search.load();
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
      }
    });
  });
  
  var timer1;
  
  
  function exports(btnObj){
  
      var url = '/system/report/member/';
      
      var dataJson = {};
      
      dataJson.usrcustid = $("input[name='usrcustid']").val();
      dataJson.realname = $("input[name='realname']").val();
      dataJson.phone = $("input[name='phone']").val();
      dataJson.iffreeze = $("select[name='iffreeze']").val();
      dataJson.state = $("select[name='state']").val();
      
      openPostWindow(url, "donloadForm", dataJson);
  
      //$(btnObj).html('处理中..');
      //$("#searchForm").attr('target','_blank').attr('action','/system/report/member/').submit();
      //timer1 = setInterval(refeshPage,1000);
      
  }
  
  function refeshPage(){
      
      var status = $.cookie('updateStatus');
      if(status == "success"){
	     clearInterval(timer1);
	     $.removeCookie('updateStatus',{ path: '/'}); //path为指定路径，直接删除该路径下的cookie
	     location.reload(true);
	  }
	  
  }
</script>

<body>
</html>  
