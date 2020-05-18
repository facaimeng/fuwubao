<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="shadow2 hidden"></div>  
<div class="wrap footer" id="footer">
    <div class="mg-auto area clearfix">
        <div class="clearfix">
            <div class="footerLeft fl">
            	<ul class="clearfix"><!--  
                	<li class="appCon">
                    	<a href="javascript:void(0);">
                        	<dl>
                                <dt>&nbsp;</dt>
                                <dd>融银普惠APP</dd>
                            </dl>
                            <div class="showCon none"></div>
                        </a>
                    </li>-->
                    <li class="weixinCon">
                        <a href="javascript:void(0);">
                            <dl>
                                <dt class="">&nbsp;</dt>
                                <dd>融银普惠公众号</dd>
                            </dl>
                            <div class="showCon none"></div>
                        </a>
                    </li>
                </ul>
        	</div>
            <div class="footerRight fr">
                <p><b>400-666-9606</b>周一至周五 8:30-18:00</p> 
        	</div>
        </div>
    </div> 
</div>
<div class="wrap brandsLinks" id="footer2">
     <div class="mg-auto overflow area clearfix">
         <ul class="firendLinks clearfix pdb30">
             <li><a href="/" target="_blank">融银普惠</a></li>
          <li><a href="/member/invest/" target="_blank">推荐产品</a></li>
          <li><a href="/member/security/" target="_blank">安全保障</a></li>
          <li><a href="/faq/" target="_blank">帮助中心</a></li>
         </ul> 
         <p><label id="copyright">Copyright © 2018</label> RONGYIN. All rights reserved<br><a class="miitbeian" href="#" target="_blank"> 桂ICP备 18005243号</a></p>
     </div> 
</div> 
<div class="returnTop">
	<a href="#" target="_self" title="返回顶部" class="top"></a><!-- 
    <a href="#" title="意见反馈" class="edit"></a> -->
</div> 
<script type="text/javascript" src="/resources/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/resources/js/mem/silder.js"></script>
<script type="text/javascript">
	$(function(){
		var curDate = new Date();
		$('#copyright').text('Copyright © '+ curDate.getFullYear());
	});
	<c:if test="${!empty memberVO}">
	 $("#logout").on({
      		click:function(){
      			$.ajax({
			          url:'/member/logout/',  
			          success:function(result){ 
	                	if(result.code=='1'){  
	                		window.location.href='/recommend/';
			         	} else{
			         	     layer.msg('操作有误，请重试!');
			         	} 
	                  },
	                  error: function (XMLHttpRequest, textStatus, errorThrown) {
			              layer.msg('操作有误，请重试!');
			          }
	            });
      		}
      });
     </c:if>
</script>