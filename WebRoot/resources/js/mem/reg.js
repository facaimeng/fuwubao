var errorHtml = '';
var sucHtml = '';

var errorClass = 'prompt';
var sucClass = 'true';

function showMsg(att,msg){
	$("#msg_"+att).html("").html(msg);
	$("#msg_div_"+att).show();
}
function hideMsg(att){
	$("#msg_div_"+att).hide();
}

function checkIdnum(idnum){ 
	if(!(validateRules.isNull(idnum)) ){
      	 if(!isIdCardNo(idnum)){
	  		showMsg('idnum','请输入正确的身份证号！');  
 		 }else{
      	 	  hideMsg('idnum');
      	 } 
    } 
}
$("#phone").on({
     blur:function(){ 
	     var phone = $(this).val();
	     if(!(validateRules.isNull(phone)) ){
		     if(validateRules.isMobile(phone)){ 
		     	 hideMsg('phone');
		      }else{  
		      	 showMsg('phone','请输入正确的手机号码！');  
		      }
	     } 
	 }  
});

$("#vcode").on({
     blur:function(){
         var vcode = $(this).val();
         if(!(validateRules.isNull(vcode)) ){
         	 if(vcode.length!=4){
         	 	  showMsg('vcode','请输入正确的验证码！');
         	 }else{
         	 	  hideMsg('vcode');
         	 } 
         }   
     } 
});

$("#pcode").on({
     blur:function(){
         var pcode = $(this).val();
         if(!(validateRules.isNull(pcode)) ){
         	 if(pcode.length!=4){
         	 	  showMsg('pcode','请输入正确的验证码！');
         	 }else{
         	 	  hideMsg('pcode');
         	 } 
         } 
     } 
});
 

$("#pwd").on({
       blur:function(){
        var pwd = $(this).val();
        var inpwd = $("#inpwd").val();
        if( !(validateRules.isNull(pwd)) ){
	         if(!validateRules.betweenLength(pwd,8,20)){
	             showMsg('pwd','密码长度为8~20个字符！');  
		     }else{
		     	if (!isPassword(pwd)) { 
					showMsg('pwd','密码安全性太低，必须包含数字，大小写字母，特殊字符中的其中两种！'); 
				}else{
					hideMsg('pwd');
				}
		     }  
		     $(".top_two_liemi").find("em").removeClass("lieiion");
		     var level = checkStrong(pwd); 
			 if(level==0){
			 	$("#i0").addClass("lieiion");
			 }else if(level>0){
			 	for(var i = 0;i<level;i++){
			 		$("#i"+i).addClass("lieiion");
			 	}
			 } 
             if(inpwd!=null&&inpwd!=''&&pwd!=inpwd){ 
             	
	     	 	showMsg('inpwd','两次密码输入不一致！'); 
	     	 }
             if(pwd==inpwd){ 
             	hideMsg('inpwd');
	     	 }
	     } 
    } 
});


$("#inpwd").on({
       blur:function(){
        var pwd = $("#pwd").val();
        var inpwd = $(this).val();
        if( !(validateRules.isNull(inpwd)) ){
	         if(pwd!=inpwd){
	             showMsg('inpwd','两次密码输入不一致！'); 
		     }else{
		     	 hideMsg('inpwd');
		     } 
	     } 
    } 
});

$("#inphone").on({
     blur:function(){ 
	     var inphone = $(this).val();
	     if(!(validateRules.isNull(inphone)) ){
		     if(validateRules.isMobile(inphone)){ 
		     	 hideMsg('inphone');
		      }else{  
		      	 showMsg('inphone','请输入正确的手机号码！');  
		      }
	     } 
	 }  
});
 
$("#subbtn").on({
	click:function(){  
			var op = $("#op").val(); 
	      	var phone = $("#phone").val();  
			if(validateRules.isNull(phone)){ 	
				showMsg('phone','请输入手机号码！'); 
				return;	
			}
			if(!validateRules.isMobile(phone)){
				showMsg('phone','请输入正确的手机号码！'); 
				return;	
			} 
			var vcode = $("#vcode").val(); 
			if(validateRules.isNull(vcode)){
				showMsg('vcode','请输入验证码！'); 
				return;		
			}
			if(vcode.length!=4){
				showMsg('vcode','请输入正确的验证码！'); 
				return;		
			} 
			var pcode = $("#pcode").val(); 
			if(validateRules.isNull(pcode)){
				showMsg('pcode','请输入验证码！'); 
				return;		
			}
			if(pcode.length!=4){
				showMsg('pcode','请输入正确的验证码！'); 
				return;		
			}
			var flag = false;
			$.ajax({
	            url:'/account/member/reg/check_pcode/',
	            data:{'pcode':pcode,'phone':phone}, 
	            method:"POST",
	            async:false, 
	            success:function(result){   
			         if(result.code!='1'){  
			         	  showMsg('pcode','短信验证码错误或已超时，请重新发送！');  
			         	  flag = true; 
			         }   
	              }, 
	              error: function (XMLHttpRequest, textStatus, errorThrown) { 
					showMsg('pcode','短信验证码错误或已超时，请重新发送！');
					flag = true; 
				 }
            });    
            if(flag){
                 return ;
            } 
            flag = false;
			if(op=='1'){
				var idnum = $("#idnum").val();  
				if(validateRules.isNull(idnum)){
					showMsg('idnum','请输入身份证号！'); 
					return;	
				}
				if(!isIdCardNo(idnum)){
					showMsg('idnum','请输入正确的身份证号！'); 
					return;	
				}  
				$.ajax({
		            url:'/account/member/reg/check_idnum/',
		            data:{'idnum':idnum}, 
		            method:"POST",
		            async:false, 
		            success:function(result){   
				         if(result.code!='1'){  
				         	  showMsg('idnum','您的身份证号在平台已绑定！');  
				         	  flag = true; 
				         }else{
				         	 hideMsg('idnum');
				         }    
		              }, 
		              error: function (XMLHttpRequest, textStatus, errorThrown) { 
						showMsg('idnum','您的操作有误,请重试！');
						flag = true; 
					 }
            	}); 
			} 
			if(flag){
            	return ;
            } 
			
			var pwd = $("#pwd").val();
			if(validateRules.isNull(pwd)){
				showMsg('pwd','请输入密码！'); 
				return;		
			}
			if(!validateRules.betweenLength(pwd,8,20)){
	             showMsg('pwd','密码长度为8~20个字符！');
	             return;	
		    }
		    if (!isPassword(pwd)) { 
				showMsg('pwd','您输入的密码安全性太低，密码必须包含数字，大小写字母，特殊字符中的其中两种！');
				return;
			} 
		    var inpwd = $("#inpwd").val();
			if(validateRules.isNull(inpwd)){
				showMsg('inpwd','请输入确认密码！'); 
				return;		
			}
			if(pwd != inpwd){ 
	            showMsg('inpwd',"两次密码输入不一致！"); 
	            return;
	        } 
	        if(op=='0'){
		        var inphone = $("#inphone").val();   
				if((inphone!=null&&inphone!='') && !validateRules.isMobile(inphone)){
					showMsg('inphone','请输入正确的手机号码！'); 
					return;	
				}
			}
			var kk = $("#isagree").is(':checked'); 
			if(!kk){
				showMsg('isagree',"请先阅读并同意《融银网络服务使用协议》");
				return;	
			} 
			var url = '/account/member/doreg/';
			if(op=='1'){
				url = '/account/member/bind';
			}
			var iii = layer.msg('正在执行中，请稍等片刻...',{shade:0.5,time:0}); 
			$.ajax({
                url:url,
                data:$("#sform").serialize(),
                method:"POST",
                success:function(result){
                    if(result.code=='1'){ 
                    	 if(op=='1'){
							window.location.href='/account/member/bind/suc/';
						 }else{
						 	window.location.href='/member/account/open/';
						 } 
	             	}else{ 
	             		layer.msg(result.message,{shade:0.5});  
	             	} 
	         	}, 
	              error: function (XMLHttpRequest, textStatus, errorThrown) { 
					layer.msg('您的操作有误，请重试...',{shade:0.5});
				 }
           });  
	}
}); 
 





