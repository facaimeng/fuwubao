<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
 <head>
  <title> 资源文件结构</title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link href="/resources/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
   <link href="/resources/bui/css/bui-min.css" rel="stylesheet" type="text/css" />
   <link href="/resources/bui/css/page-min.css" rel="stylesheet" type="text/css" />
   
   <style type="text/css">
	.form-horizontal .control-label {width: 130px;}
   </style>
 </head>
 
 <body>
	<div class="rtop" style="position:fixed;width:100%;">
		<div style="float:left;margin-right:5px;"><img src="/resources/bui/img/lang.png" /></div> <div class="rtop_l" style="float:left;">当前位置：借款管理 &gt; <a>${loan.name}</a></div>
	</div>

    <div class="container" style="padding-top:50px;">
    <div class="row">
      <form id="loanForm" class="form-horizontal span24">  
        
        <input type="hidden" value="${loan.lnid}" name="lnid">
        <input type="hidden" name="bidType" value="${loan.bidtype}#${loan.bidtypename}" />
        <input type="hidden" name="bidProdType" value="${loan.protype}#${loan.protypename}" /> 
        
        <!-- 使用的项目(资产) -->
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>引用房源：</label>
            <div class="controls">
              <select name="proid" class="input-normal" style="width:150px;">
                <c:forEach items="${projects}" var="project">
                <option value="${project.proid}#${project.pronum}" <c:if test="${loan.proid == project.proid}">selected="selected"</c:if> <c:if test="${loan.proid != project.proid}"><c:if test="${project.state != 'NORMAL'}">disabled="disabled"</c:if></c:if>>${project.address}</option>
                </c:forEach>
              </select>
            </div>
          </div>
        </div>
        
        <!-- 标的名称 -->
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>标的名称：</label>
            <div class="controls">
              <input name="bidName" type="text" class="input-large control-text" value="${loan.name}">
            </div>
          </div>
        </div>
        
        <!-- 是否新手标 -->
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>是否新手标：</label>
            <div class="controls">
                <input type="radio" name="lntype" value="1" <c:if test="${loan.lntype eq 1}">checked="checked"</c:if>>普通标
                <input type="radio" name="lntype" value="2" <c:if test="${loan.lntype eq 2}">checked="checked"</c:if>>新手标
            </div>
          </div>
        </div>
        
        <!-- 是否定向放款 -->
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>是否定向放款：</label>
            <div class="bui-form-group controls ifDirect_box">
              <label class="checkbox"><input type="checkbox" value="N" <c:if test="${loan.ifdirect eq 'N'}">checked="checked"</c:if>/>不定向&nbsp;&nbsp;</label>
              <label class="checkbox"><input type="checkbox" value="Y" <c:if test="${loan.ifdirect eq 'Y'}">checked="checked"</c:if>/>定向&nbsp;&nbsp;</label>
              <input type="hidden" name="ifdirect" value="${loan.ifdirect}" />
            </div>
          </div>
        </div>
        
        <!-- 定向收款人汇付ID -->
        <div class="row directview_box" <c:if test="${loan.ifdirect eq 'Y'}">style="display:;"</c:if> <c:if test="${loan.ifdirect eq 'N'}">style="display:none;"</c:if>>
          <div class="control-group span15">
            <label class="control-label"><s>*</s>定向收款人汇付ID：</label>
            <div class="controls">
              <input name="dusrcustid" type="text" value="${loan.dusrcustid}" class="input-large control-text" onblur="loadLman();">
            </div>
          </div>
        </div>
        
        <!-- 定向收款人姓名 -->
        <div class="row directview_box" <c:if test="${loan.ifdirect eq 'Y'}">style="display:;"</c:if> <c:if test="${loan.ifdirect eq 'N'}">style="display:none;"</c:if>>
          <div class="control-group span15">
            <label class="control-label"><s>*</s>定向收款人名称：</label>
            <div class="controls">
              <input name="dman" type="text" value="${loan.dman}" class="input-large control-text" readonly="readonly">
            </div>
          </div>
        </div>
        
        <!-- 担保机构 -->
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>担保机构：</label>
            <div class="controls">
              <select name="waninfo" class="input-normal" style="width:150px;"> 
                <c:forEach items="${wanlist}" var="wan">
                <option value="${wan.cmid}#${wan.name}" <c:if test="${loan.cmid eq wan.cmid}">selected="selected"</c:if>>${wan.name}</option>
                </c:forEach>
              </select>
            </div>
          </div>
        </div>
        
        <!-- 标的金额 -->
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>资产总额：</label>
            <div class="controls"> 
              <input name="borrTotAmt" value="${loan.loanMoneyLabel}" type="text" class="input-normal control-text" style="float:left;margin-right:3px;"  onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}">
              <span class="valid-text" style="float:left; text-align: left; padding-left:2px;"><em>单位元,精确到分(如1000.01)</em></span>
            </div>
          </div>
        </div>
        
        <!-- 最低投资金额 -->
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>起投金额：</label>
            <div class="controls">
              <input name="minimoney" type="text" class="input-normal control-text" style="float:left;margin-right:3px;" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}" value="${loan.miniMoneyLabel}">
              <span class="valid-text" style="float:left; text-align: left; padding-left:2px;"><em>单位元</em></span>
            </div>
          </div>
        </div>
        
        <!-- 年化利率 -->
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>年化利率：</label>
            <div class="controls">
              <input name="yearRate" value="${loan.yearate}" type="text" onkeyup="two_bit_decimal(this)" value="0.01" class="input-normal control-text" style="float:left;margin-right:3px;">
              <!-- 应还款总利息 -->
              <input name="retInterest" type="hidden" value="0.00"/>
              <span class="valid-text" style="float:left; text-align: left; padding-left:2px;"><em>百分比,保留2位小数(如24.55)</em></span>
            </div>
          </div>
        </div>
        
        <!-- 计划投标开始日期 -->
        <div class="row">
          <div class="control-group span15 ">
            <label class="control-label"><s>*</s>计划投标开始日期：</label>
            <div id="range" class="controls bui-form-group"  data-rules="{dateRange:true}">
              <input name="bidStartDate"  value="<fmt:formatDate value="${loan.startdate}" pattern="yyyy-MM-dd HH:mm:ss"/>" type="text" style="float:left;margin-right:3px;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'})">
              <span class="valid-text" style="float:left; text-align: left; padding-left:2px;"><em>格式 yyyy-MM-dd HH:mm:ss</em></span>
            </div>
          </div>
        </div>
        
        <!-- 计划投标截止日期 -->
        <div class="row">
          <div class="control-group span15 ">
            <label class="control-label"><s>*</s>计划投标截止日期：</label>
            <div id="range" class="controls bui-form-group"  data-rules="{dateRange:true}">
              <input name="bidEndDate" value="<fmt:formatDate value="${loan.enddate}" pattern="yyyy-MM-dd HH:mm:ss"/>" type="text" style="float:left;margin-right:3px;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'})">
              <span class="valid-text" style="float:left; text-align: left; padding-left:2px;"><em>格式 yyyy-MM-dd HH:mm:ss</em></span>
            </div>
          </div>
        </div>
        
        <!-- 还款方式 -->
        <div class="row">
          <div class="control-group span20">
            <label class="control-label"><s>*</s>还款方式：</label>
            <div class="controls">
              <select name="retType" class="input-normal" style="width:150px;" onchange="repayLimit(this.value);"> 
				<option value="01#一次还本付息" <c:if test="${loan.repaytype eq '01'}">selected="selected"</c:if>>一次还本付息</option>
			    <!-- <option value="02#等额本金" <c:if test="${loan.repaytype eq '02'}">selected="selected"</c:if>>等额本金</option> -->
              </select>
              
              <select class="input-normal" name="repaytimes" style="width:50px;" id="repay_limit" onchange="repayDate(this.value);">  
			    <option value="1" <c:if test="${loan.repaytype eq '1'}">selected="selected"</c:if> <c:if test="${loan.repaytype eq '2'}">disabled="disabled"</c:if>>1期</option>
			    <option value="2" <c:if test="${loan.repaytype eq '2'}">selected="selected"</c:if> <c:if test="${loan.repaytype eq '1'}">disabled="disabled"</c:if>>2期</option>
			    <option value="3" <c:if test="${loan.repaytype eq '3'}">selected="selected"</c:if> <c:if test="${loan.repaytype eq '1'}">disabled="disabled"</c:if>>3期</option>
			    <option value="4" <c:if test="${loan.repaytype eq '4'}">selected="selected"</c:if> <c:if test="${loan.repaytype eq '1'}">disabled="disabled"</c:if>>4期</option>
			    <option value="5" <c:if test="${loan.repaytype eq '5'}">selected="selected"</c:if> <c:if test="${loan.repaytype eq '1'}">disabled="disabled"</c:if>>5期</option>
			    <option value="6" <c:if test="${loan.repaytype eq '6'}">selected="selected"</c:if> <c:if test="${loan.repaytype eq '1'}">disabled="disabled"</c:if>>6期</option>
			    <option value="7" <c:if test="${loan.repaytype eq '7'}">selected="selected"</c:if> <c:if test="${loan.repaytype eq '1'}">disabled="disabled"</c:if>>7期</option>
			    <option value="8" <c:if test="${loan.repaytype eq '8'}">selected="selected"</c:if> <c:if test="${loan.repaytype eq '1'}">disabled="disabled"</c:if>>8期</option>
			    <option value="9" <c:if test="${loan.repaytype eq '9'}">selected="selected"</c:if> <c:if test="${loan.repaytype eq '1'}">disabled="disabled"</c:if>>9期</option>
			    <option value="10" <c:if test="${loan.repaytype eq '10'}">selected="selected"</c:if> <c:if test="${loan.repaytype eq '1'}">disabled="disabled"</c:if>>10期</option>
			    <option value="11" <c:if test="${loan.repaytype eq '11'}">selected="selected"</c:if> <c:if test="${loan.repaytype eq '1'}">disabled="disabled"</c:if>>11期</option>
			    <option value="12" <c:if test="${loan.repaytype eq '12'}">selected="selected"</c:if> <c:if test="${loan.repaytype eq '1'}">disabled="disabled"</c:if>>12期</option>
			    <option value="18" <c:if test="${loan.repaytype eq '18'}">selected="selected"</c:if> <c:if test="${loan.repaytype eq '1'}">disabled="disabled"</c:if>>18期</option>
			    <option value="24" <c:if test="${loan.repaytype eq '24'}">selected="selected"</c:if> <c:if test="${loan.repaytype eq '1'}">disabled="disabled"</c:if>>24期</option>
              </select>
            </div>
          </div>
        </div>
        
        <!-- 借款期限 -->
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>处置周期：</label>
            <div class="controls">
              <input name="loanPeriod" type="text" class="input-normal control-text" value="${loan.loandead}" style="float:left;margin-right:3px;"/>天
            </div>
            
          </div>
        </div>
		
		<!-- 应还款日期 -->
        <input name="retDate" type="hidden" value="<fmt:formatDate value="${loan.repaydate}" pattern="yyyyMMdd"/>"/>
	    <!-- 最后还款日期 -->
	    <input name="lastRetDate" type="hidden" value="<fmt:formatDate value="${loan.lastrepaydate}" pattern="yyyyMMdd"/>"/>
        
        <!-- 借款人名称 -->
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>借款人名称：</label>
            <div class="controls">
              <input name="borrName" type="hidden" class="input-normal control-text" readonly="readonly" value="${loan.lman}">
              <select name="borrManId" class="input-normal" onchange="setProjectInfo(this);" style="width:150px;">
                <c:forEach items="${loanmans}" var="item">
			    <option value="${item.lmid}" <c:if test="${item.lmid == loan.lmanuid}">selected="selected"</c:if> >${item.usrcustname}</option></c:forEach>
              </select>
            </div>
          </div>
        </div>
        
        <!-- 借款人类型 -->
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>借款人类型：</label>
            <div class="bui-form-group controls borrType_box">
              <label class="checkbox"><input type="checkbox" value="02" <c:if test="${loan.lmantype eq '02'}">checked="checked"</c:if> readonly="readonly"/>企业</label>
              <label class="checkbox"><input type="checkbox" value="01" <c:if test="${loan.lmantype eq '01'}">checked="checked"</c:if> readonly="readonly"/>个人</label>
              <input type="hidden" name="borrType" id="borrType_input" value="${loan.lmantype}">
            </div>
          </div>
        </div>
        
	    <!-- 借款人ID -->
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>借款人ID：</label>
            <div class="controls">
              <input name="borrCustId" value="${loan.lusrcustid}" type="text" class="input-normal control-text" readonly="readonly" style="float:left;margin-right:3px;">
              <span class="valid-text" style="float:left; text-align: left; padding-left:2px;"><em>16位汇付开户ID号(个人和企业)</em></span>
            </div>
          </div>
        </div>
		
        <!-- 借款企业营业执照编号(借款人类型为企业时为必填)-->
        <div class="row company_code" <c:if test="${loan.lmantype eq '02'}">style="display:;"</c:if> <c:if test="${loan.lmantype eq '01'}">style="display:none;"</c:if>>
          <div class="control-group span8">
            <label class="control-label"><s>*</s>企业营业执照编号：</label>
            <div class="controls">
              <input name="borrBusiCode" value="${loan.lcertnum}" type="text"  readonly="readonly" class="input-normal control-text" style="float:left;margin-right:3px;">
              <span class="valid-text" style="float:left; text-align: left; padding-left:2px;"><em></em></span>
            </div>
          </div>
        </div>
		
        <!-- 借款人证件类型(借款人类型为“ 01：个人”时为必须参数) -->
        <div class="row persion_info" <c:if test="${loan.lmantype eq '02'}">style="display:none;"</c:if> <c:if test="${loan.lmantype eq '01'}">style="display:;"</c:if>>
          <div class="control-group span8">
            <label class="control-label"><s>*</s>借款人证件类型：</label>
            <div class="controls">
              <select name="borrCertType" class="input-normal" style="width:150px;"> 
			    <option value="00">身份证</option>
              </select>
            </div>
          </div>
        </div>
        
		<!-- 借款人证件号码(借款人类型为“ 01：个人”时为必须参数)-->
        <div class="row persion_info" <c:if test="${loan.lmantype eq '02'}">style="display:none;"</c:if> <c:if test="${loan.lmantype eq '01'}">style="display:;"</c:if>>
          <div class="control-group span15">
            <label class="control-label"><s>*</s>借款人证件号码：</label>
            <div class="controls">
              <input name="borrCertId" value="${loan.lcertnum}" type="text" class="input-normal control-text" readonly="readonly">
            </div>
          </div>
        </div>
	
        <!-- 借款人手机号码 -->
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>借款人手机号码：</label>
            <div class="controls">
              <input name="borrMobiPhone" value="${loan.lmanphone}" type="text" class="input-normal control-text">
            </div>
          </div>
        </div>
        
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">借款用途：</label>
            <div class="controls control-row4">
              <textarea name="borrPurpose" class="input-large">${loan.usefor}</textarea>
            </div>
          </div>
        </div>
        <hr/>
        <!-- 平台服务费  -->
        <div class="row">
          <div class="control-group span15">
            <label class="control-label"><s>*</s>平台服务费：</label>
            <div class="controls">
              <input name="charges" type="text" class="input-normal control-text" value="${loan.charges}" onkeyup="two_bit_decimal(this)" class="input-normal control-text" style="float:left;margin-right:3px;">
              <span class="valid-text" style="float:left; text-align: left; padding-left:2px;"><em>百分比,保留2位小数(如24.55)</em></span>
            </div>
          </div>
        </div>
        <div class="row form-actions actions-bar">
            <div class="span13 offset3 ">
              <button type="button" class="button button-primary" onclick="subForm();">修改</button>
              <button type="reset" class="button">重置</button>
            </div>
        </div>
      </form>
    </div>
  </div>
  
  <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="/resources/js/cbai.commonutil.js?i=8"></script>
  <script type="text/javascript" src="/resources/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript" src="/resources/bui/js/bui-min.js"></script>
  <script type="text/javascript" src="/resources/bui/js/config-min.js"></script>
  <script type="text/javascript" src="/resources/js/cbai.dateutils.js?i=2"></script>
    
  <script type="text/javascript">
   var project_json = ${projects_json};
   var loanmans_json = ${loanmans_json};
   var t_repaytype = '${loan.repaytype}';
   //alert(t_repaytype);
   $(function () {
       //借款人类型不能编辑
       var fanxiBox = $(".borrType_box input:checkbox");
       fanxiBox.attr("disabled","disabled");
       
       fanxiBox.click(function () {
          if(this.checked || this.checked=='checked'){
              
              fanxiBox.removeAttr("checked");
              
              //这里需注意jquery1.6以后必须用prop()方法
              $(this).prop("checked", true);
              
              //执行对应的操作
              var borrType = $(this).val();
              
              if("01" == borrType.split("#")[0]){
                  //显示个人信息,隐藏企业信息
                  $(".persion_info").show();
                  $(".company_code").hide();
              }else if("02" == borrType.split("#")[0]){
                  //显示企业信息,隐藏个人信息
                  $(".persion_info").hide();
                  $(".company_code").show();
              }
              
            }
       });
       
       var ifDirectBox = $(".ifDirect_box input:checkbox");
       ifDirectBox.click(function () {
          if(this.checked || this.checked=='checked'){
              
              ifDirectBox.removeAttr("checked");
              //这里需注意jquery1.6以后必须用prop()方法
              $(this).prop("checked", true);
              
              //执行对应的操作
              var ifDirect = $(this).val(); 
              $("input[name='ifdirect']").val(ifDirect);
              
              if("N" == ifDirect){
                  $(".directview_box").hide();
                  $("input[name='dusrcustid']").val('');
                  $("input[name='dman']").val('');
              }else if("Y" == ifDirect){
                  $(".directview_box").show();
                  $("input[name='directtype']").val('');
              }
              
            }
       });
       
       
       var directBox = $(".directType_box input:checkbox");
       directBox.click(function () {
          if(this.checked || this.checked=='checked'){
              directBox.removeAttr("checked");
              //这里需注意jquery1.6以后必须用prop()方法
              $(this).prop("checked", true);
              var directtype = $(this).val(); 
              $("input[name='directtype']").val(directtype);
            }
       });
       
       
       
       if(t_repaytype){
           repayLimit("0" + t_repaytype);
       }
       
  });
  </script>   
  <script type="text/javascript">
    function loadLman(){
         
         var directtype = $("input[name='directtype']").val();
         var dusrcustid = $("input[name='dusrcustid']").val();
         $.ajax({
             type: "get",
             url:'/system/loan/loadDirectMan/',
             data:{"directtype":directtype, "dusrcustid":dusrcustid},
             success:function(result){
                 if(result.code == "1"){
                     $("input[name='dman']").val("").val(result.message);
                 }else{
                     BUI.Message.Alert(result.message,'info');
                 }
             },
             error: function (jqXHR, textStatus, errorThrown) {
                /*错误信息处理*/
		     }
         });
    }
    
    /**
     * 根据还款方式不同显示不同的表单内容
     */
    function repayLimit(objValue){
        
        var selectValue = objValue.split("#")[0];
 
        var repayLimit = $("#repay_limit").val();
      
        if(selectValue == "01"){
           //所有大于1的不能选
           optinGt01(1);
           //还款期限可填
           $("input[name='loanPeriod']").removeAttr("readonly");
        }else if(selectValue == "02"){
           //所有大于1的能选
           optinGt02(2);
           //还款期限不可填
           $("input[name='loanPeriod']").attr("readonly","readonly");
        }
    }
    
    /**
     * 还款方式：一次还本付息的时候期数只能选择1其他的不能选
     */
    function optinGt01(optValue) {
        var me = document.getElementById('repay_limit');
        for (i = 0; i < me.options.length; i++) {
            me.options[i].disabled = false;
            if (me.options[i].value != null && me.options[i].value > optValue){
                me.options[i].disabled = true;
            }
        } 
        me.options[0].selected="selected";
    }
    
    /**
     * 还款方式：等额本金的时候期数只能选择2及以上
     */
    function optinGt02(optValue) {
        var me = document.getElementById('repay_limit');
        for (i = 0; i < me.options.length; i++) {
            me.options[i].disabled = false;
            if (me.options[i].value != null && me.options[i].value < optValue){
                me.options[i].disabled = true;
            }
        } 
        me.options[1].selected="selected";
    }
    
    /**
     * 用户选择等额本金的还款方式时
     * 根据用户选择的还款期数,以30天为单位 计算标的期限的值
     * @repayLimit 还款期数
     */
    function repayDate(repayLimit){
        //标的还款期数
        var repayLimit = $("#repay_limit").val();
        //计算标的期限(天数)
        $("input[name='loanPeriod']").val(repayLimit * 30);
    }
  
	function subForm(){
	
         var ifdirect = $("input[name='ifdirect']").val();
         
         if(ifdirect == "Y"){
             var directtype = $("input[name='directtype']").val();
             if(directtype==""){
                 BUI.Message.Alert('定向收款人类型不能为空!','info');
                 return false;
             }
             
             var dusrcustid = $("input[name='dusrcustid']").val();
             if(dusrcustid==""){
                 BUI.Message.Alert('定向收款人汇付ID不能为空!','info');
                 return false;
             }
             
             var dman = $("input[name='dman']").val();
             if(dman==""){
                 BUI.Message.Alert('定向收款人姓名不能为空!','info');
                 return false;
             }
         }else{
             $("input[name='directtype']").val('');
             $("input[name='dusrcustid']").val('');
             $("input[name='dman']").val('');
         }
         
         var proid = $("select[name='proid']").val();
         if(proid=="" || proid == null){
            BUI.Message.Alert('请指定标的使用项目!','info');
            return false;
         }
         
         var bidName = $("input[name='bidName']").val();
         if(bidName=="" || bidName == null){
            BUI.Message.Alert('请填写标的名称!','info');
            return false;
         }
         
         //标的金额验证
         var borrTotAmt = $("input[name='borrTotAmt']").val();
         if(borrTotAmt=="" || borrTotAmt == null){
            BUI.Message.Alert('请填写标的金额!','info');
            return false;
         }
         
         //if(validateRules.isMoney(borrTotAmt)){
         //   var amtPoint = borrTotAmt.split(".")[1];
         //  if(amtPoint!=null && amtPoint!=''){
         //       if(amtPoint.length != 2){
	     //           BUI.Message.Alert('标的金额,格式错误!','info');
		 //           return false;
         //       }
         //  }else{
		 //           BUI.Message.Alert('标的金额,格式错误!','info');
		 //          return false;
         //   }
         //}
         
         //最低投资金额
         var minimoney = $("input[name='minimoney']").val();
         if(minimoney=="" || minimoney == null){
            BUI.Message.Alert('请填写最低投资金额!', 'info');
            return false;
         }
         
         //年利率验证
         var yearRate = $("input[name='yearRate']").val();
         if(yearRate=="" || yearRate == null){
            BUI.Message.Alert('请填写标的年利率!','info');
            return false;
         }
         
         if(validateRules.isMoney(yearRate)){
            var ratePoint = yearRate.split(".")[1];
            if(ratePoint!=null && ratePoint!=''){
                if(ratePoint.length != 2){
	                BUI.Message.Alert('标的年利率,格式错误!','info');
		            return false;
                }
            }else{
		            BUI.Message.Alert('标的年利率,格式错误!','info');
		            return false;
            }
         }
         
         //标的开始筹集时间
         var bidStartDate = $("input[name='bidStartDate']").val();
         if(bidStartDate=="" || bidStartDate == null){
            BUI.Message.Alert('请填写标的开始筹集时间!','info');
            return false;
         }
         
         //标的筹集结束时间
         var bidEndDate = $("input[name='bidEndDate']").val();
         if(bidEndDate=="" || bidEndDate == null){
            BUI.Message.Alert('请填写标的筹集结束时间!','info');
            return false;
         }
         
         //还款方式
         var retType = $("select[name='retType']").val();
         if(retType=="" || retType == null){
            BUI.Message.Alert('请填选择标的还款方式!','info');
            return false;
         }
  
         var retTypeValue = retType.split("#")[0];
         //每月还款日期
         var repayMonthDate = $("select[name='monthlyrepaydate']").val();
         if(retTypeValue=='02'){
             if(repayMonthDate==''){
                 BUI.Message.Alert('请填选择每月还款日期!','info');
            	 return false;
             }
         }

         //标的期限
         var loanPeriod = $("input[name='loanPeriod']").val();
         if(loanPeriod=="" || loanPeriod == null){
            BUI.Message.Alert('标的期限不能为空!','info');
            return false;
         } 
         
         
         var charges = $("input[name='charges']").val();
         if(charges=="" || charges == null){
            BUI.Message.Alert('请填写平台服务费率!','info');
            return false;
         }
         
         if(validateRules.isMoney(charges)){
            var chargesPoint = charges.split(".")[1];
            if(chargesPoint!=null && chargesPoint!=''){
                if(chargesPoint.length != 2){
	                BUI.Message.Alert('平台服务费率,格式错误!','info');
		            return false;
                }
            }else{
		            BUI.Message.Alert('平台服务费率,格式错误!','info');
		            return false;
            }
         }
         
         //投标结束字符串转换成日期格式的字符串
         var bidEndDateStr = dateUtil.formatterDate(dateUtil.dateStryyyyMMddToyyyy_MM_dd(bidEndDate));
         //alert("投标截止日期:" + bidEndDateStr);
         
         var retDate = dateUtil.nextDay(bidEndDateStr, parseInt(loanPeriod) + 5, 'd');
         //alert("根据期限还原出的最后一次还款日期:" + retDate);

         //计算标的还款日期
         $("input[name='retDate']").val(retDate.split("-")[0] + retDate.split("-")[1] + retDate.split("-")[2]); 
         
         //计算标的最后还款日期 = 还款日期延后 7天
         var lastRetDate = dateUtil.nextDay(retDate, 10, 'd');
         $("input[name='lastRetDate']").val(lastRetDate.split("-")[0] + lastRetDate.split("-")[1] + lastRetDate.split("-")[2]);
         
         $.ajax({
             type: "POST",
             url:'/system/loan/edit/',
             data:$("#loanForm").serialize(),
             success:function(result){
                 if(result.code == "0"){
                     BUI.Message.Alert(result.message,'info');
                 }else{
                     BUI.Message.Alert(result.message,function(){
                         location.href='/system/loan/enter_search/?status=KECAOZUO';
                     },'info');
                 }
             },
             error: function (jqXHR, textStatus, errorThrown) {
                /*错误信息处理*/
		     }
         });
	}
     
	    //选定项目自动设定借款人信息
	function setProjectInfo(obj){
		var select_loanman_id = $(obj).val();
		
		//借款人类型
		var fanxiBox = $(".borrType_box input:checkbox");
		fanxiBox.attr("disabled", "disabled");
		
		for(var p in loanmans_json){//遍历json对象的每个key/value对,p为key

			var currentLoanman = loanmans_json[p];

			if(currentLoanman.lmid == select_loanman_id){
				
				var lmantype = currentLoanman.lmtype;
                
			    //默认勾选
			    fanxiBox.each(function(){
			        
			        if($(this).val() == (lmantype)){
			            $(this).prop("checked", true);
			            $("#borrType_input").val(lmantype);
			        }else{
			            $(this).prop("checked", false);
			        }
			        
				});
      
      
		        //处理相应的显示问题
		        $("input[name='borrName']").val(currentLoanman.usrcustname);
		        $("input[name='borrCustId']").val(currentLoanman.usrcustid);
		        $("input[name='borrMobiPhone']").val(currentLoanman.phone);
      
		      	if("01" == lmantype){
		            //显示个人信息,隐藏企业信息
	                $(".persion_info").show();
	                $(".company_code").hide();
	                $("input[name='borrCertId']").val(currentLoanman.idnum);
	            }else if("02" == lmantype){
	                //显示企业信息,隐藏个人信息
	                $(".persion_info").hide();
	                $(".company_code").show();
	                $("input[name='borrBusiCode']").val(currentLoanman.idnum);
	            }
  			} 
		} 
	}
    </script>    
<body>
</html> 