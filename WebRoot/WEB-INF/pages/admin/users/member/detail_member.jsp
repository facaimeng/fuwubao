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
      <div class="detail-page"> 
      <form id="jbForm" >
      <div class="detail-section">  
        <h3>基本信息</h3>
        <div class="row detail-row">
          <div class="span8">
            <label>姓名：</label><span class="detail-text">${member.realname}</span>
          </div> 
          <div class="span8">
            <label>手机号码：</label><span class="detail-text">${member.phone}</span>
          </div>
          <div class="span8">
            <label>状态：</label><span class="detail-text">${member.state.name}</span>
          </div>
          <div class="span8">
            <label>是否冻结：</label><span class="detail-text">${member.iffreeze.name}</span>
          </div>
        </div>
        
        <div class="row detail-row">
          <div class="span8">
            <label>性别：</label><span class="detail-text">${member.sex}</span>
          </div>
          <div class="span8">
            <label>生日：</label><span class="detail-text"><fmt:formatDate value="${member.birth}" pattern="yyyy-MM-dd"/></span>
          </div>
         <div class="span8">
            <label>身份证：</label><span class="detail-text">${member.idnum}</span>
          </div> 
        </div>
        
        <div class="row detail-row"> 
          <div class="span8">
            <label>汇付帐户ID：</label><span class="detail-text">${member.usrcustid}</span>
          </div>
          <div class="span8">
            <label>汇付帐号：</label><span class="detail-text">${member.usrcustname}</span>
          </div>
          <div class="span8">
            <label>是否绑卡：</label><span class="detail-text">${member.ifbind.name}</span>
          </div>
        </div>
        
        <div class="row detail-row"> 
          <div class="span8">
            <label>推荐人类别：</label><span class="detail-text"><c:if test="${member.dadtype eq '1'}">渠道</c:if><c:if test="${member.dadtype eq '2'}">经纪人</c:if></span>
          </div>
           <div class="span8">
            <label>推荐人姓名：</label><span class="detail-text">${member.dadname}</span>
          </div> 
          <div class="span8">
            <label>推荐人手机：</label><span class="detail-text">${member.dadmemid}</span>
          </div>  
        </div>
        
        <div class="row detail-row"> 
           
          <div class="span8">
            <label>注册时间：</label><span class="detail-text"><fmt:formatDate value="${member.regtime}" pattern="yyyy-MM-dd HH:mm"/></span>
          </div>
          <div class="span8">
            <label>开户时间：</label><span class="detail-text"><fmt:formatDate value="${member.authtime}" pattern="yyyy-MM-dd HH:mm"/> </span>
          </div>
        </div>  
        
      </div>
      <hr/>
      <div class="detail-section"> 
        <h3>账户信息</h3>
        <div class="row detail-row">
          <div class="span8">
            <label>可用余额：</label><span class="detail-text"><fmt:formatNumber value="${memAccount.allassets/100}" pattern="#0.00" /> </span>
          </div>
          <div class="span8">
            <label>待收本金：</label><span class="detail-text"><fmt:formatNumber value="${memAccount.allrepay/100}" pattern="#0.00" /></span>
          </div>
          <div class="span8">
            <label>待收收益：</label><span class="detail-text"><fmt:formatNumber value="${memAccount.avlmoney/100}" pattern="#0.00" /></span>
          </div>
          <div class="span8">
            <label>累计收益：</label><span class="detail-text"><fmt:formatNumber value="${memAccount.avlmoney/100}" pattern="#0.00" /></span>
          </div>
        </div> 
      </div> 
      <hr/>
      <div class="detail-section"> 
        <h3>绑定银行卡</h3>
        <div class="row detail-row">
          <div class="span24">
            <div id="grid"></div>
          </div>
        </div>
      </div> 
	 
    </div>
    </form>
  </div>
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>  
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script> 
  <script type="text/javascript" src="/resources/My97DatePicker/WdatePicker.js"></script>   

<body>
</html> 