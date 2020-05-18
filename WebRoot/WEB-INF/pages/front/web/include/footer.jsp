<%@ page language="java" pageEncoding="UTF-8"%>
<div class="footer">
	<div class="footer_l"><img src="/resources/images/web/folino.png" /></div>
    <div class="footer_r">
    	<div class="footer_r_a">
        	<a href="javascript:void(0);">联系我们</a>丨<a href="/aboutus/">关于我们</a>丨<a href="javascript:void(0);">版权声明</a>丨<a href="/faq/">帮助中心</a>
        </div>
        <h2><img src="/resources/images/web/jc.png" /><b>桂ICP备 18005243号</b><img src="/resources/images/web/nl.png" /></h2>
        <div style="clear:both;"></div>
        <p><label id="copyright">Copyright © 2018</label> rongyinpuhui.com 版权所有   融银普惠信息技术有限公司</p>
        <p>400-666-9606  重庆南岸区江南大道7号西南经协大厦14层</p>
    </div>
</div>  
<script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script> 
<script type="text/javascript">
	$(function(){
		var curDate = new Date();
		$('#copyright').text('Copyright © '+ curDate.getFullYear()); 
		$("#"+'${from}_1').addClass("top_2_hovon");  
	});
</script> 