<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
 <head>
  <title> </title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
      <link href="/resources/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
   <link href="/resources/bui/css/bui-min.css" rel="stylesheet" type="text/css" /> 
 </head>
 <body> 
	<div class="rtop" style="position:fixed;width:100%;">
		<div style="float:left;margin-right:5px;"><img src="/resources/bui/img/lang.png" /></div> <div class="rtop_l" style="float:left;">当前位置：借款管理 &gt; <a href="#">借款信息</a></div>
	</div>
	
	<div class="container" style="padding-top:50px;">
    <div class="detail-page">
      <div class="detail-section">  
        <h3>基本信息</h3>
        <div class="row detail-row">
          <div class="span8">
            <label>标的编号：</label><span class="detail-text">${loan.lnnum}</span>
          </div>
          <div class="span8">
            <label>标的名称：</label><span class="detail-text">${loan.name}</span>
          </div>
          <div class="span8">
            <label>使用项目编号：</label><span class="detail-text">${loan.pronum}</span>
          </div>
        </div>
        
        <div class="row detail-row">
          <div class="span8">
            <label>借款类型：</label><span class="detail-text">${loan.bidtypename}</span>
          </div>
          <div class="span8">
            <label>标的产品：</label><span class="detail-text">${loan.protypename}</span>
          </div>
          <div class="span8">
            <label>借款年利率：</label><span class="detail-text">${loan.yearate}</span>
          </div>
        </div>
        
        <div class="row detail-row">
          <div class="span8">
            <label>资产金额：</label><span class="detail-text">${loan.loanMoneyLabel} 元</span>
          </div>
           <div class="span8">
            <label>已还金额：</label><span class="detail-text">${loan.repayedMoneyLabel} 元</span>
          </div>
          <div class="span8">
            <label>剩余可投金额：</label><span class="detail-text">${loan.avalMoneyLabel} 元</span>
          </div>
        </div>
        
        <div class="row detail-row">
          <div class="span8">
            <label>借款期限：</label><span class="detail-text">${loan.loandead}${loan.loandeadunit}</span>
          </div>
          <div class="span8">
            <label>开始投标日期：</label><span class="detail-text"><fmt:formatDate value="${loan.startdate}" pattern="yyyy-MM-dd HH:mm:ss"/> </span>
          </div>
           <div class="span8">
            <label>结束投标日期：</label><span class="detail-text"><fmt:formatDate value="${loan.enddate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
          </div>
        </div>
        
        <div class="row detail-row">
          <div class="span8">
            <label>还款方式：</label><span class="detail-text">${loan.repaytypename}</span>
          </div>
          <div class="span8">
            <label>应还款日期：</label><span class="detail-text"><fmt:formatDate value="${loan.repaydate}" pattern="yyyy-MM-dd"/></span>
          </div>
          <div class="span8">
            <label>最后还款日期：</label><span class="detail-text"><fmt:formatDate value="${loan.lastrepaydate}" pattern="yyyy-MM-dd"/></span>
          </div>
        </div>
        
        <div class="row detail-row">
          <div class="span8">
            <label>最低投资额：</label><span class="detail-text">${loan.miniMoneyLabel} 元</span>
          </div>
          <div class="span8">
            <label>借款用途：</label><span class="detail-text">${loan.usefor}</span>
          </div>
        </div>
        
      </div>
      <hr/>
      <div class="detail-section"> 
        <h3>借款人信息</h3>
        <div class="row detail-row">
          <div class="span8">
            <label>借款人类型：</label><span class="detail-text">${loan.lmantypename}</span>
          </div>
          <div class="span8">
            <label>借款人编号：</label><span class="detail-text">${loan.lusrcustid}</span>
          </div>
          <div class="span8">
            <label>借款人姓名：</label><span class="detail-text">${loan.lman}</span>
          </div>
        </div>
        <div class="row detail-row">
          <div class="span8">
            <label>借款人手机号码：</label><span class="detail-text">${loan.lmanphone}</span>
          </div>
          <div class="span8">
            <label>借款人证件号码：</label><span class="detail-text">${loan.lcertnum}</span>
          </div>
        </div>
      </div> 
      
      <c:if test="${(loan.status eq 'FINISH')}">
      <hr/>
      <div class="detail-section"> 
        <h3>还款计划${loan.status}</h3>
        <div class="row detail-row">
          <div class="span24">
            <div id="grid"></div>
          </div>
        </div>
      </div>
      </c:if>
      <hr/>
      <div class="detail-section"> 
        <h3>审核意见</h3>
        <div class="row detail-row">
          <div class="span24">
            <label>${loan.checkmemo}</label>
          </div>
        </div>
      </div>
    </div>
  </div> 
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/config-min.js"></script>
  <script type="text/javascript">
    BUI.use('common/page');
  </script> 
<script type="text/javascript"> 
	BUI.use('bui/grid',function (Grid) {
	  var data = [
	   {id:'1803282322484885',name:'2018-04-09',day:2500,address:'已还款'},
	   {id:'1803282322484885',name:'2018-04-09',day:2500,address:'未还款'},
	   {id:'1803282322484885',name:'2018-04-09',day:2500,address:'未还款'},
	   {id:'1803282322484885',name:'2018-04-09',day:2500,address:'未还款'}],
	   
	   grid = new Grid.SimpleGrid({
	        render : '#grid', //显示Grid到此处
	        width : 950,      //设置宽度
	        columns : [
	          {title:'标的编号',dataIndex:'id',width:80},
	          {title:'还款日期',dataIndex:'name',width:100},
	          {title:'还款金额',dataIndex:'day',width:100,renderer:Grid.Format.dateRenderer},
	          {title:'还款状态',dataIndex:'address',width:300}
	        ]
	    });
	    
	    grid.render();
	    grid.showData(data);
	});
  </script> 

<body>
</html>  
