<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
       <div class="row">
      <form id="searchForm" class="form-horizontal ">
        <div class="row">
          <div class="control-group span8">
            <label class="control-label">产品类别：</label>
            <div class="controls" >
              <select id="prtid" name="prtid">
              	<option value="">全部</option><c:forEach items="${protList}" var="item">
                <option value="${item.prtid}">${item.name}</option></c:forEach>
              </select>
            </div>
          </div>
          <div class="control-group span8">
            <label class="control-label">产品名称：</label>
            <div class="controls">
              <input type="text" class="control-text" name="pname">
            </div>
          </div>
          <div class="control-group span8">
            <label class="control-label">投资期限：</label>
            <div class="controls">
              <input type="text" class="control-text" name="bidperiod">
            </div>
          </div>
          
          <div class="control-group span8">
            <label class="control-label">状态：</label>
            <div class="controls" >
              <select name="state">
                <option value="">全部</option> 
                <option value="NORMAL">正常</option>
                <option value="LOCKED">锁定</option>
              </select>
            </div>
          </div> 
          <div class="span3 offset2">
            <button  type="button" id="btnSearch" class="button button-primary">搜索</button>
          </div> 
        </div> 
      </form>
    </div>
    <div class="search-grid-container">
      <div id="grid"></div>
    </div>
  </div>
   <div id="sortDIV" class="hide">
      <form id="sortForm" class="form-horizontal" >
      	 <input type="hidden" name="prtid" id="sort_prtid" /> 
      	 <input type="hidden" name="prid" id="id" />
      	 <input type="hidden" id="oldOrderNum" name="oldOrderNum" />
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>类型名称</label>
            <div class="controls">
               <input type="text" class="input-normal control-text" id="pname" disabled="disabled"/> 
            </div>
          </div> 
          <div class="control-group span8">
            <label class="control-label"><s>*</s>当前索引</label>
            <div class="controls">
               <input type="text" class="input-normal control-text" id="newOrderNum" name="newOrderNum"/> 
            </div>
          </div> 
        </div> 
      </form>
    </div>
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/config-min.js"></script>
  <script type="text/javascript" src="/resources/js/cbai.dialog.js"></script>
  <script type="text/javascript">
    BUI.use('common/page');
  </script> 
<script type="text/javascript"> 
  BUI.use(['common/search','bui/overlay'],function (Search,Overlay) {
    var enumObj = {"Y":"是","N":"否"};
    var  columns = [
    	  {title:'序号',width:40,renderer : function(value,obj,index){ 
    	  		return (store.get("pageIndex"))*store.get("pageSize")+(index+1);
    	  }}, 
          {title:'产品类别',dataIndex:'prtname',width:100},
    	  {title:'短标题',dataIndex:'title',width:100},
          {title:'产品名称',dataIndex:'pname',width:150},
          {title:'起投金额',dataIndex:'startbidmoney',width:100,renderer : function(value,obj,index){ 
          		var sb = obj.startbidmoney.toString(); 
          		return sb.substring(0, sb.length-2)+'.'+sb.substring(sb.length-2, sb.length);  
    	  }},
          {title:'最高投金额',dataIndex:'maxbidmoney',width:100,renderer : function(value,obj,index){ 
          		var sb = obj.maxbidmoney.toString(); 
          		return sb.substring(0, sb.length-2)+'.'+sb.substring(sb.length-2, sb.length);  
    	  }},
          {title:'投资期限(天)',dataIndex:'bidperiod',width:100},
          {title:'可持有期数',dataIndex:'holdperiod',width:100},
          {title:'最小年化回报率(%)',dataIndex:'minreturnrate',width:120},
          {title:'最大年化回报率(%)',dataIndex:'maxreturnrate',width:120},
          {title:'募集开始时间',dataIndex:'buystart',width:130,renderer:BUI.Grid.Format.datetimeRenderer},
          {title:'募集结束时间',dataIndex:'buyend',width:130,renderer:BUI.Grid.Format.datetimeRenderer},
          {title:'剩余可购金额',dataIndex:'stockmoney',width:100,renderer : function(value,obj,index){ 
          		var sb = obj.stockmoney.toString();  
          		if(sb.length<=2){
          			return "0."+sb;
          		}else{ 
          			return sb.substring(0, sb.length-2)+'.'+sb.substring(sb.length-2, sb.length);
          		} 
    	  }},
          {title:'购买人数',dataIndex:'sales',width:100},
          {title:'退出提前天数',dataIndex:'exitday',width:120}, 
          {title:'状态',dataIndex:'stname',width:60},
          {title:'首页显示',dataIndex:'iftop',width:80,renderer:BUI.Grid.Format.enumRenderer(enumObj)},  
          {title:'操作',width:250,renderer : function(value,obj,index){  
            var editStr =  Search.createLink({ //链接使用 此方式 
                text : '编辑', 
                href : '/system/products/enter_edit/?id='+obj.prid
              }); 
            var sortStr = '';
            var prtid = $("#prtid").val(); 
            if(prtid!=null&&prtid!=''){
            	var sindex = (store.get("pageIndex"))*store.get("pageSize")+(index+1);
            	sortStr = '<span class="grid-command btn-sort" data-sindex="'+sindex+'" >排序</span>';
            } 
            var delStr = '<span class="grid-command btn-lock" data-op="lock">锁定</span>';//页面操作不需要使用Search.createLink
            if(obj.state=='LOCKED'){
            	delStr = '<span class="grid-command btn-lock" data-op="unlock">解锁</span>';
            }
            
            var inStr = '<span class="grid-command btn-top" data-op="top">首页置顶</span>';//页面操作不需要使用Search.createLink
            if(obj.iftop=='Y'){
            	inStr = '<span class="grid-command btn-top" data-op="untop">取消置顶</span>';
            }
           
            return editStr + sortStr + inStr + delStr;
          }}, 
          {dataIndex:'prid', visible: false},
          {dataIndex:'prtid', visible: false},
          {dataIndex:'state', visible: false}
        ],
      store = Search.createStore('/system/products/search/',{
	      //autoLoad : true
	  }),
      gridCfg = Search.createGridCfg(columns,{  
      	//forceFit: true,
        tbar : {
          items : [
            {
            	text : '<i class="icon-plus"></i>新建',
            	btnCls : 'button button-small',
            	handler:function(){
            		window.location.href='/system/products/enter_add/';
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
    //监听事件，删除一条记录
     grid.on('cellclick',function(ev){
      var sender = $(ev.domTarget); //点击的Dom
      if(sender.hasClass('btn-lock')){
	        var op = sender.attr("data-op"); 
	        var record = [ev.record];  
	        if(record.length==1){
		        BUI.Message.Confirm('确认要操作选中的记录么？',function(){
		          	$.ajax({
			          url:'/system/products/lock/', 
			          data:{"op":op,"id":record[0].prid},
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
      }else if(sender.hasClass('btn-top')){
	        var op = sender.attr("data-op"); 
	        var record = [ev.record];  
	        if(record.length==1){
		        BUI.Message.Confirm('确认要操作选中的记录么？',function(){
		          	$.ajax({
			          url:'/system/products/set_top/', 
			          data:{"op":op,"id":record[0].prid},
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
      }else if(sender.hasClass('btn-sort')){
      	   var sindex = sender.attr("data-sindex"); 	 
	       var record = [ev.record];  
	       if(record.length==1){
	       		$('#id').val(record[0].prid); 
	       		$('#sort_prtid').val(record[0].prtid); 
				$('#oldOrderNum').val(sindex);  
				$('#newOrderNum').val(sindex);
				$('#pname').val(record[0].pname);
		        var dialog = new Overlay.Dialog({
		            title:'排序',
		            width:360,
		            height:130,
		            closeAction : 'destroy', //每次关闭dialog释放
		            //配置DOM容器的编号
		            contentId:'sortDIV',
		            success:function () {
		              	var newOrderNum = parseInt($("#newOrderNum").val()); 
						var allCounts = store.getTotalCount(); 
						if(isNaN(newOrderNum)){
							alert('您的输入有误!');
							return false;
						}else if(newOrderNum<1){
							alert("您输入的索引必须大与1!");
							return false;
						}else if(newOrderNum>allCounts){
							alert("您输入的索引超出记录总数!");
							return false;
						} 
						$.ajax({
					          url:'/system/products/sort/',
					          type:"POST",
					          data:$("#sortForm").serialize(),
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
		              	this.close();
		            }
	           });
	           dialog.show();
	       }
      }
    });
  });
</script>

<body>
</html>  
