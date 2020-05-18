<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="wrap header">
	<div class="mg-auto area clearfix">
    	<div class="headNav clearfix" style="font-size: 14px;">
        	<ul class="headNavLeft fl clearfix">
                <li class="first"><a href="/" target="_blank">融银普惠</a></li>
            </ul><c:if test="${empty memberVO or empty memberVO.usrcustid}">
            <div class="headNavRight fr clearfix">
            	<span>融银普惠欢迎您！</span>
                <span class="Login-register"><a href="/account/member/login/">请登录</a>|<a href="/account/member/register/">免费注册</a></span>
                <span class="help"><a href="/faq/" target="_blank">帮助中心</a></span>
            </div></c:if><c:if test="${!empty memberVO and !empty memberVO.usrcustid}">
            <div class="headNavRight fr userInformation clearfix">
            	<p class="fl">${memberVO.surname}${memberVO.sex.name}
            	 ， 欢迎您！<a href="javascript:;" id="logout">[退出]</a></p>
                <ul class="clearfix fl"> 
                    <li class="help"><a href="/faq/" target="_blank">帮助中心</a></li>
                </ul>
            </div>  
            </c:if>
        </div>
  	</div>
</div>   