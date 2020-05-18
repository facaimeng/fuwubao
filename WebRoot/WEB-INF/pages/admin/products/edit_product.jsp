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
	<div class="rtop_l">当前位置：项目管理 &gt;  <a href="#">集团架构</a></div>
  </div> 
  
  <div class="container">
    <div class="row">
      <form id="jbForm" class="form-horizontal span24">
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>产品类别：</label>
            <div class="controls">
              <select disabled="disabled"> 
                <option >${pro.prtname}</option> 
              </select>
            </div>
          </div> 
        </div>  
         <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>短标题：</label>
            <div class="controls">
              <input name="title" type="text"  class="input-normal control-text" value="${pro.title}"/>
            </div>
          </div> 
        </div>  
         <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>产品名称：</label>
            <div class="controls">
              <input name="pname" type="text"  class="input-normal control-text" value="${pro.pname}"/>
            </div>
          </div> 
        </div>  
        
         <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>起投金额：</label>
            <div class="controls">
              <input name="startbidmoneyStr" type="text"  class="input-normal control-text" value="<fmt:formatNumber value="${pro.startbidmoney/100}" pattern="#0.00" /> "/> <font color="#FF0000">0.00元</font> 
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>最高投金额：</label>
            <div class="controls">
              <input name="maxbidmoneyStr" type="text"  class="input-normal control-text" value="<fmt:formatNumber value="${pro.maxbidmoney/100}" pattern="#0.00" />"/> <font color="#FF0000">0.00元,0表示可一次购完</font> 
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>投资期限：</label>
            <div class="controls">
              <input name="bidperiod" type="text"  class="input-normal control-text" value="${pro.bidperiod}"/> <font color="#FF0000">天</font> 
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>可持有期数：</label>
            <div class="controls">
              <input id="holdper" name="holdperiod" type="text"  class="input-normal control-text" value="${pro.holdperiod}"/> <font color="#FF0000">0表示不限</font>
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>最小年化回报率：</label>
            <div class="controls">
              <input name="minreturnrate" type="text"  class="input-normal control-text" value="${pro.minreturnrate}"/>
            </div>
          </div>  
        </div>  
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>最大年化回报率：</label>
            <div class="controls">
              <input name="maxreturnrate" type="text"  class="input-normal control-text" value="${pro.maxreturnrate}"/>
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
              <input name="buystart" type="text"  value="<fmt:formatDate value="${pro.buystart}" pattern="yyyy-MM-dd HH:mm"/>" class="Wdate input-normal control-text" onFocus="WdatePicker({isShowClear:false,readOnly:false,dateFmt:'yyyy-MM-dd HH:mm'});"/>
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>募集结束时间：</label>
            <div class="controls">
              <input name="buyend" type="text"  value="<fmt:formatDate value="${pro.buyend}" pattern="yyyy-MM-dd HH:mm"/>" class="Wdate input-normal control-text" onFocus="WdatePicker({isShowClear:false,readOnly:false,dateFmt:'yyyy-MM-dd HH:mm'});"/>
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>退出提前天数：</label>
            <div class="controls">
              <input name="exitday" type="text"  value="${pro.exitday}" class="input-normal control-text"/> <font color="#FF0000">到期前几天申请退出</font> 
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>长周期提前退出天数：</label>
            <div class="controls">
              <input name="outexitday" type="text"  value="${pro.outexitday}" class="input-normal control-text"/> <font color="#FF0000">投资期限大于6个月，超过6个月后，每个月提前几天可申请退出</font> 
            </div>
          </div>  
        </div>  
         <div class="row">
          <div class="control-group span24">
            <label class="control-label"><s>*</s>超过投资期限退出收益率：</label>
            <div class="controls">
              <input name="outexitrate" type="text"  value="${pro.outexitrate}" class="input-normal control-text"/> <font color="#FF0000">超过投资期限退出，未满下一周期提前退出每月利率</font> 
            </div>
          </div>  
        </div>  
        
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">短介绍：</label>
            <div class="controls control-row4">
              <textarea id="memo" name="memo" class="input-large" type="text">${pro.memo}</textarea>
            </div>
          </div>
        </div>
        <div class="row form-actions actions-bar" >
            <div class="span13 offset3 ">
               <input id="rerate" name="returnrate" type="hidden" value='${pro.returnrate}'/>
               <input name="prid" type="hidden" value='${pro.prid}'/>
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
  <script>  
	    BUI.use(['bui/grid','bui/data','bui/form'],function (Grid,Data,Form) {

			    var columns = [
			    	  {title : '期数',dataIndex :'per',editor : {xtype : 'text',rules:{required:true,digits:true}}},
			          {title : '回报率',dataIndex :'rate',editor : {xtype : 'text',rules:{required:true,number:true}}}
			    ],
		        editing = new Grid.Plugins.CellEditing();
		        var rerate = $("#rerate").val(); 
			    var store = new Data.Store({
			   		data:BUI.JSON.parse(rerate)
			   }), 
		        grid = new Grid.Grid({
			        render : '#grid',
			        columns : columns,
			        width : 700,
			        forceFit : true,
			        store : store,
			        plugins : [Grid.Plugins.CheckSelection,editing],
			        tbar:{
			            items : [{
			              btnCls : 'button button-small',
			              text : '<i class="icon-plus"></i>添加',
			              listeners : {
			                'click' : addFunction
			              }
			            },
			            {
			              btnCls : 'button button-small',
			              text : '<i class="icon-remove"></i>删除',
			              listeners : {
			                'click' : delFunction
			              }
			            }]
		        }

		      	});
		    	grid.render();
		    	
		    	grid.on('cellclick',function  (ev) {
				     var sender = $(ev.domTarget);
				     if(sender.hasClass("x-grid-checkbox")){
				     	return true;
				     }else{
				     	return false;
				     }   
		        });
		
			    function addFunction(){
			      var newData = {};
			      store.add(newData);
			      //editing.edit(newData,'per','rate'); //添加记录后，直接编辑
			    }
			
			    function delFunction(){
			      var selections = grid.getSelection();
			      store.remove(selections);
			    }  
			    $("#save").on({
		      		click:function(){       
					      var records = store.getResult();  
					      var rerate = BUI.JSON.stringify(records);
					      var flag = checkRerate(records,rerate); 
					      alert(flag);
					      if(flag==-1){
					      	   BUI.Message.Alert("回报率增长图数据有误!","warning");   
					      	   return ;
					      } 
					      if(flag==-2){
					      	   BUI.Message.Alert("回报率增长图期数不连续!","warning");   
					      	   return ;
					      }    
					      if(flag==-3){
					      	   BUI.Message.Alert("回报率增长期数不能超过可持有期数!","warning");   
					      	   return ;
					      } 
					      $("#rerate").val(rerate);
					      BUI.Message.Confirm('确认要提交么？',function(){
					             $.ajax({
							          url:'/system/products/edit/',
							          type:"POST",
							          data:$("#jbForm").serialize(),
							          success:function(result){  
							             if(result.code=='1'){
							                 BUI.Message.Alert(result.message,function() {
								                window.location.href='/system/products/enter_search/';
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
  		
  		function up(x,y){ 
        	return parseInt(x.per)-parseInt(y.per);
    	}
  		function checkRerate(records,rerate){
  			var len = records.length;
  			if(records==null || len==0){
  				return -1;
  			}  
  			var holdper = $("#holdper").val();
  			holdper == 0?holdper=12:holdper=holdper;
  			if(len>holdper){ 
	      	   return -3;
		    }
  			if(rerate.search("\{\}")==1){ 
	      	   return -1;
		    } 
  			for(i=0;i<=len-1;i++){ 
 			  if(records[i].per==null || records[i].rate==null){
 			  	 return -1;
 			  	 break;
 			  }  
			}   
		    records.sort(up);
		    
		    if(records[0].per!=1){
		    	return -2;
		    }
			for(i=0;i<=records.length-1;i++){ 
				if(i!=len-1){
					if((records[i+1].per-records[i].per)!=1){
						return -2;
						break;
					}
				} 
			}
			return 1;  
	    }
		
	</script> 

<body>
</html> 