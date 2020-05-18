// JavaScript Document
$(function(){
	$(".personShow").hide();
	$(".moreArrow a").click(function(){
		$(".personShow").slideDown(1000);
		$(this).hide();
	});
});


//主导航
window.onload=function()
{
	
	var aNav=$(".nav").get();
	for(var i=0;i<aNav.length;i++){
		LineMove(aNav[i])
	}
	function LineMove(obj){
		var aLi=obj.getElementsByTagName("li");
		var oBg=aLi[aLi.length-1];
		var i=0;
		for(i=0;i<aLi.length-1;i++)
		{
			aLi[i].onmouseover=function(){
				startMove(this,oBg,this.offsetLeft);	
			}
			aLi[i].onmouseout=function(){
				var arr3=[];
				for(var i=0;i<this.parentNode.children.length;i++){
					if(this.parentNode.children[i].className=="current"){
						arr3.push(this.parentNode.children[i])
					}	
				}
				if(!arr3[0]) return false;
				startMove(arr3[0],oBg,arr3[0].offsetLeft);		
			}
		}
	}
	var aCurrent=$(".listNav .current");
	var aBg=$(".bg");
	for(var i=0;i<aCurrent.length;i++){
			startMove(aCurrent.get(i),aBg.get(i),aCurrent.get(i).offsetLeft);	
	}
}

function startMove(oLi,obj,iTarget)
{
	var iSpeed=0;
	var left=parseInt(getStyle(obj,"left"));
	obj.style.width=oLi.clientWidth+"px";
	clearInterval(obj.timer);
	obj.timer=setInterval(function(){
		iSpeed+=(iTarget-obj.offsetLeft)/5;
		iSpeed*=0.7;
		//obj.style.left=obj.offsetLeft+iSpeed+'px'; 
		left+=iSpeed;
		if(Math.abs(iSpeed)<1 && Math.abs(left-iTarget)<1)
		
		{
			clearInterval(obj.timer);
	
			obj.style.left=iTarget+'px';
		}
		else
		{
			obj.style.left=left+'px';
		}
		
	},30)
}

function getStyle(obj,attr){
	if(!obj) return false;
	return obj.currentStyle?obj.currentStyle[attr]:getComputedStyle(obj)[attr];
}

//返回顶部
var jingTop=500;
$(window).scroll(function(){
	var scroolh = $(window).scrollTop();
	if(scroolh > 500){
		$(".returnTop").addClass("returnTop_To");
	}
	else{
		$(".returnTop").removeClass("returnTop_To");
	}
	//箭头位置
	var bannerHeight=($(".IndexPage .banner").height()-$(".arrowBtn").height())-50;
	
		if(bannerHeight<scroolh-30+jingTop){
			$(".arrowBtn").stop().animate({top:bannerHeight-30});	
		}else{
			$(".arrowBtn").stop().animate({top:scroolh-30+jingTop});
		}
	
});

$(function(){
	/*$(".cancelBtn").click(function(){
		$(".pop").fadeIn(300);	
		$(".cancelCon").fadeIn(300);
		$("html").addClass("bodyTo");
	});*/
	$(".closeReasons").click(function(){
		$(".pop").fadeOut(300);
		$(".cancelCon").fadeOut(300);
		$("html").removeClass("bodyTo");
		return false;
	});
	$(".pdfFile a b").click(function(){
		$(".pop").fadeIn(300);	
		$(".cancelCon").fadeIn(300);
		$("html").addClass("bodyTo");
	});
	$(".closeFileCon").click(function(){
		$(".pop").fadeOut(300);
		$(".cancelCon").fadeOut(300);
		$("html").removeClass("bodyTo");
		return false;
	});
});

//首页效果
$(function(){
	$("#laydate_box").addClass("t450");
	
	//产品列表页展开收起
	$(".more-select").click(function()
	{
		$(".productSelectCon>ul").toggleClass("h198");
		$(this).toggleClass("updot");
		
	});
	//产品列表页选择
	$(".productSelectList a").click(function(){
		$(this).addClass("selectThis");
		$(this).siblings().removeClass("selectThis");
	});
	$(".unSelect").click(function(){
		$(this).parent().removeClass("selectThis");
		return false;
	});
	
	//产品列表页已约,已售显示/隐藏
	function IsPC()  //判断是否移动端
	{  
           var userAgentInfo = navigator.userAgent;  
           var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");  
           var flag = true;  
           for (var v = 0; v < Agents.length; v++) {  
               if (userAgentInfo.indexOf(Agents[v]) > 0) { flag = false; break; }  
           }  
           return flag;  
	} 
	if(IsPC()){
	$(".productListCon li").hover(function(){
		$(this).find(".yiyue").slideDown();
		$(this).find(".delectThis").slideDown();
	
	},function(){
			$(this).find(".yiyue").slideUp();
			$(this).find(".delectThis").slideUp();
		});
	}
	else{
		$(".productListCon li").click(function(){
		$(this).find(".yiyue").slideDown();
		$(this).siblings().find(".yiyue").slideUp();
	});
	}
	$(".delectThis").click(function(){
		$(this).parent().parent().remove();
	})
	$(".productListCon li .popCon").hover(function(){
		$(".yiyue").hide();
	});
	
	//消息中心删除功能暂去
	/*$(".messageReminder .Del").click(function(){
		$(this).parent().parent().remove();
	}) */
	
	//点击加号，查看弹出层
	$(".addIcon").on("click",function(){
		$(".pop").fadeIn();	
		$(".productList-right p").hide();	
		$(this).parent().parent().find(".popCon").fadeIn();	
		$(".popConArea .addIcon").addClass("closethis");
		$(".popConArea .addIcon").removeClass("addIcon");
		//关闭弹出层
		$(".closethis").on("click",function(){
			$(".pop").fadeOut(300);
			$(".popCon").fadeOut(300);
			$(".productList-right p").show();
			return false;
		}); 
		return false;					  
	});
	//收藏效果
	$(".DetailsRight .zhuangtai p a.SC").click(function(){
		$(this).toggleClass("SC_bg");	
	});	
	
	//banner 公共部分编辑头像
	$(function(){
		$(".iCenter .imgbox").hover(function(){
		  $(".cover_bg").show();
		},function(){
			$(".cover_bg").hide();
		})
	})
	
	//风险列表页
	$(".riskEvaluation dl dd").click(function(){
		$(this).addClass("Thisone");	
		$(".Thisone").find("input[type='radio']").attr("checked",true);
		$(this).siblings().removeClass("Thisone");
		$(".Thisone").siblings().find("input[type='radio']").attr("checked",false);
	});
	//表格区块浮动弹出层
	$(".showList").hide();
	$(".floatDot em").hover(function(){
		$(this).parent().find(".showList").show();
	},function(){
		$(this).parent().find(".showList").hide();
	})
	
	//菜单
	$(".listNav ul li").click(function(){
		$(this).addClass("current");
		$(this).siblings().removeClass("current");	
	});
	$(".phone-icon").click(function(){
		$(this).addClass("phone-iconHover");
		$(".email-icon").removeClass("email-iconHover");		
	});
	$(".email-icon").click(function(){
		$(this).addClass("email-iconHover");	
		$(".phone-icon").removeClass("phone-iconHover");
	});
	
	//二级市场和订单列表点击添加背景
	$(".secondaryMarket .search a").click(function(){
		$(this).addClass("u");
		$(this).siblings().removeClass("u");	
	});
	$(".dealList .search a").click(function(){
		$(this).addClass("u");
		$(this).siblings().removeClass("u");	
	});
	
	
	$(".productSelect .pop").click(function(){
		$(this).fadeOut(300);
		$(".popCon").fadeOut(300);
		$(".cancelCon").fadeOut(300);	
		$("html").removeClass("bodyTo");
		$(".productList-right p").show();
		return false;
	}); 
	
	//右侧滑动效果
	$(".floatLayer").hide();
//	$(".amount p").click(function(){
//		$(".amount .floatLayer").slideToggle(300);
//		$(".SignOn .floatLayer").slideUp(300);	
//		return false;
//	});
//	$("body").click(function(){
//		$(".amount .floatLayer").slideUp(300);
//	});
	$(".SignOn p").click(function(){
		$(".SignOn .floatLayer").slideToggle(300);
		//$(".amount .floatLayer").slideUp(300);
		return false;	
	});
	$("body").click(function(){
		$(".SignOn .floatLayer").slideUp(300);
	});
	$(".dateThis").hide();
	$(".roundHover").mouseover(function(){
		var spanDate = $(this).parent().find(".dateThis");
		if (spanDate.html() != "")
		{
			$(this).parent().find(".dateThis").fadeIn(300);
		}
		$(this).parent().parent().parent().find(".dateNow").fadeOut(300);
		$(this).parent().find(".text").fadeIn(300);
	});
	$(".roundHover").mouseout(function(){
		$(this).parent().find(".dateThis").fadeOut(300);
		$(this).parent().find(".text").fadeOut(300);
		$(this).parent().parent().parent().find(".dateNow").fadeIn(300);
	});
	//删除节点
	/*$(".orderList .delect").click(function(){
		$(this).parent().parent().hide();	
	})*/	
})

//select模拟选择
$(document).ready(function(){
	//状态栏显示current
	var userNav = $("#userNav").val();
	$("#"+userNav).addClass("current");
	
	var mainNav = $("#mainNav").val();
	$("#"+mainNav).addClass("current");
	
	var productMiddleMenuNav = $("#productMiddleMenuNav").val();
	$("#"+productMiddleMenuNav).addClass("current");
	
	var middleMenuNav = $("#middleMenuNav").val();
	$("#"+middleMenuNav).addClass("current");
	
	var userSilderNav = $("#userSilderNav").val();
	$("#"+userSilderNav).addClass("mgl0");
	$("#"+userSilderNav).find("a").addClass("cur");
	
	var dealSilderNav = $("#dealSilderNav").val();
	$("#"+dealSilderNav).addClass("mgl0");
	$("#"+dealSilderNav).find("a").addClass("cur");
	
	var accountSilderNav = $("#accountSilderNav").val();
	$("#"+accountSilderNav).addClass("mgl0");
	$("#"+accountSilderNav).find("a").addClass("cur");
	
 	$('.son_ul').hide(); 
 	$('.select_box span').hover(function(){
	$(this).parent().find('ul.son_ul').slideDown(); 
 	$(this).parent().find('li').hover(function(){$(this).addClass('hover')},function(){$(this).removeClass('hover')}); 
	$(this).parent().hover(function(){
			$(this).parent().find("ul.son_ul").slideUp(300); 
		});
		},function(){});
			$('ul.son_ul li').click(function(){
			$(this).parent().parent().find('span').html($(this).html());
			$(this).parent().slideUp(300);
		});
});
/*点击选中，多选*/
/*$(function(){
	$(".checkboxs input").click(function(){
			if($(this).hasClass('selected')){
				$(this).removeClass('selected');
			}else{
				$(this).addClass('selected');	
			}
	});
	
});*/
$(function(){
	$(".bankLogos li div").click(function(){
			if($(this).hasClass('selectedThis')){
				$(this).removeClass('selectedThis');
			}else{
				$(this).addClass('selectedThis');	
			}
	});
	
});
/* tab切换 */
function hTab(tab_controler,tab_con){
	var lne=$(tab_controler).find("li");
	var pen=$(tab_con).children("div")
	 tabOne()
	lne.click(function(){
		$(this).addClass("current").siblings().removeClass("current");	
		tabOne()
	})
	
	function tabOne(){
	   var tabArr=[];
	   for(var i=0;i<lne.length;i++){
		if(lne.eq(i).hasClass("current")){
			tabArr.push(lne.eq(i));
		}
	 }
	if(!tabArr[0]) return false;
	pen.css("display","none");
	pen.eq(tabArr[0].index()).css("display","block");	
	}
};

//首页消息展开
$(function(){
	var more=document.getElementById('more');
	var show=document.getElementById('show');
	var Timer=null;
	if(!more) return false;
	if(!show) return false;
	more.onmouseover=show.onmouseover=function()
	{
		clearTimeout(Timer);
		
		show.style.display='block';	
	}
	more.onmouseout=show.onmouseout=function()
	{
		Timer=setTimeout(hide,300);	
	}
	
	function hide()
	{
		show.style.display='none';	
	}
});

/* 焦点图(渐隐) */
function new_slider(slidercon,mainspeed,inspeed,outspeed){
	this.slidercon = slidercon;
	this.mainspeed = mainspeed;
	this.inspeed = inspeed;
	this.outspeed = outspeed;
	var i = 1;
	var listcon = '<li class="current">' + 1 + '</li>';
	$(slidercon).append(
		'<div id="slider_title"></div>'
	);
	$(slidercon).children("#slider_title").html($(slidercon).children("ul:first").children("li:eq(0)").children("a").children("img").attr("title"));
	$(slidercon).append(
		'<ul id="slider_counter"></ul>'
	);
	for(var j = 2; j <= $(slidercon).children("ul:first").children("li").length; j ++){
		listcon = listcon + "<li>" + j + "</li>";
	};
	$(slidercon).children("#slider_counter").html(listcon);
	$(slidercon).children("#slider_counter").children("li").mouseover(function(){
		var index = $.inArray(this,$(slidercon).children("#slider_counter").children("li"));
		$(slidercon).children("#slider_counter").children("li").removeClass("current")
		.eq(index).addClass("current");
		$(slidercon).children("ul:first").children("li").fadeOut(outspeed)
		.eq(index).fadeIn(inspeed);
		$(slidercon).children("#slider_title").html($(slidercon).children("ul:first").children("li:eq(" + parseInt($(this).index())  + ")").children("a").children("img").attr("title"));
		i = $(this).index() + 1;
		if(i == $(slidercon).children("ul:first").children("li").length){
			i = 0;
		};
	});
	function init(){
		$(slidercon).children("ul:first").children("li:not(" + i + ")").fadeOut(outspeed);
		$(slidercon).children("ul:first").children("li:eq(" + i + ")").fadeIn(inspeed);
		$(slidercon).children("#slider_counter").children("li:not(" + i + ")").removeClass("current");
		$(slidercon).children("#slider_counter").children("li:eq(" + i + ")").addClass("current");
		$(slidercon).children("#slider_title").html($(slidercon).children("ul:first").children("li:eq(" + i + ")").children("a").children("img").attr("title"));
		i++;
		if(i >= $(slidercon).children("ul:first").children("li").length){
			i = 0;
		};
	};
	var move = setInterval(init,mainspeed);
	$(slidercon).mouseover(function(){
		clearInterval(move);
	});
	$(slidercon).mouseout(function(){
		move = setInterval(init,mainspeed);
	});
};
 
(function ($) {

    $.fn.menumaker = function (options) {

        var cssmenu = $(this), settings = $.extend({
            title: "Menu",
            format: "dropdown",
            sticky: false
        }, options);

        return this.each(function () {

            cssmenu.find('li ul').parent().addClass('has-sub');

            multiTg = function () {
                cssmenu.find(".has-sub").prepend('<span class="submenu-button"></span>');
                cssmenu.find('.submenu-button').on('click', function () {
                    $(this).toggleClass('submenu-opened');
                    if ($(this).siblings('ul').hasClass('open')) {
                        $(this).siblings('ul').removeClass('open').hide();
                    }
                    else {
                        $(this).siblings('ul').addClass('open').show();
                    }
                });
            };

            if (settings.format === 'multitoggle') multiTg();
            else cssmenu.addClass('dropdown');

            if (settings.sticky === true) cssmenu.css('position', 'fixed');

            resizeFix = function () {
                if ($(window).width() > 768) {
                    cssmenu.find('ul').show();
                }

                if ($(window).width() <= 768) {
                    cssmenu.find('ul').hide().removeClass('open');
                }
            };
            resizeFix();
            return $(window).on('resize', resizeFix);

        });
    };
})(jQuery);

(function ($) {
    $(document).ready(function () {

        $(document).ready(function () {
            $("#cssmenu").menumaker({
                title: "Menu",
                format: "multitoggle"
            });

            var foundActive = false, activeElement, linePosition = 0, menuLine = $("#cssmenu #menu-line"), lineWidth, defaultPosition, defaultWidth;

            $("#cssmenu > ul > li").each(function () {
                if ($(this).hasClass('active')) {
                    activeElement = $(this);
                    foundActive = true;
                }
            });

            if (foundActive === false) {
                activeElement = $("#cssmenu > ul > li").first();
            }

            defaultWidth = lineWidth = activeElement.width();

//            defaultPosition = linePosition = activeElement.position().left;

            menuLine.css("width", lineWidth);
            menuLine.css("left", linePosition);

            $("#cssmenu > ul > li").hover(function () {
                activeElement = $(this);
                lineWidth = activeElement.width();
                linePosition = activeElement.position().left;
                menuLine.css("width", lineWidth);
                menuLine.css("left", linePosition);
            },
function () {
//    menuLine.css("left", defaultPosition);
    menuLine.css("width", defaultWidth);
});

        });
    });
})(jQuery);
