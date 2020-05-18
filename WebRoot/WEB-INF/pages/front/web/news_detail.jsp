<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@ taglib prefix="str" uri="http://www.cbai.com.cn/utils-strsplit"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${news.title}-${globals.gtitle}</title> 
<meta name="keywords" content="${news.pkeywords}" />
<meta name="description" content="${news.pdescription}" />
<link type="text/css" href="/resources/style/web/main.css" rel="stylesheet" />
</head> 
<body> 
<div class="yingying"> 
<jsp:include page="/WEB-INF/pages/front/web/include/header.jsp"/>  
<div class="newcot">
	<div class="newcot_1">
    	<div class="newcot_1_l"><a href="/">首页</a>  >  ${froms.name}  >  ${newstype.name}</div>
        <div class="newcot_1_2"><img src="/resources/images/web/newcot_2.png" /></div>
        <div class="newcot_2">
        	<h2>${news.title} <a href="/${from}/${ntype}/">返回上层</a></h2>
            <h3><fmt:formatDate value="${news.pubtime}" pattern="yyyy-MM-dd HH:mm"/>  &nbsp;&nbsp;&nbsp;  来源：${news.source}  &nbsp;&nbsp;&nbsp;    作者：${news.author}   &nbsp;&nbsp;&nbsp;     浏览量：${news.viewcount}</h3>
            <div>
            	${news.content}
            </div><c:if test="${empty next}">
			<a class="newcot_2_a" title="没有了!">下一篇：没有了</a></c:if><c:if test="${!empty next}">
			<a class="newcot_2_a" href="/${from}/${ntype}/id_${next.nid}" title="${next.title}">下一篇：${next.title}</a> </c:if>
        </div>
    </div>
    
    <div class="newcot_3">
    	<div class="newcot_3_1"></div>
        <div class="newcot_3_2">
            <h2>免责申明</h2>
            <p>1、本网站信息均来源于市场公开资料，融银仅基于上述公开资料阐述融银观点，并不保证其准确性、完整性、实时性或正确性。本网站的信息和内容仅供参考，请谨慎使用。</p>
            <p>2、本网站信息中署名"融银普惠"、"融银普惠研究部"的文章，以及图片和音视频资料，版权均属于融银普惠。如需转载请与融银普惠联系，并在授权的范围内注明来源和作者，保证作品的完整性。违反上述声明者，本网站将追究相关法律责任。</p>
            <p>3、本网站转载其他媒体或机构的作品，并不意味赞同其观点或证实其内容的真实性。如其他媒体、网站或个人从本网下载使用，必须保留本网注明的"来源"，并自负版权等法律责任。如本网转载涉及版权等问题，请作者在两周内来电或来函联系。</p>
            <p>4、问题咨询及相关合作，请发邮件至：service@rongyin.com。</p>
        </div>
    </div>
    
</div> 
<jsp:include page="/WEB-INF/pages/front/web/include/footer.jsp"/> 
</div> 
</body> 
</html>
