$(document).ready(function(e) {
    if($("#slideWrap li").length){
		$("#slideWrap li").each(function(index, element) {
			  $(this).attr("data-index",index);
		});
		function a(i){
			$("#slideWrap").jCarouselLite({
				auto: 8000,
				btnNext: $("#btnPrev"),
				btnPrev: $("#btnNext"),
				visible:1,
				speed: 1200,
				start:i
			});
		}
		a(0);
		var w=$(window).width();
		$(window).resize(function(e) {
			if(w==$(window).width())return false;
			w=$(window).width();
			a(index);
		});
	}

	if($("#casewrap li").length>3)$("#casewrap").jCarouselLite({
    		btnNext: $("#btn-next"),
    		btnPrev: $("#btn-prior"),
    		visible:3,
    		speed: 400,
    });
});

$(document).ready(function()
	{
	    $(".active-line").parent().next().show();
		//slides the element with class "menu_body" when paragraph with class "menu_head" is clicked
		$("#firstpane h3.menu-head").click(function()
		{
			$(this).next("div.menu-body").slideToggle(300).siblings("div.menu-body").slideUp("slow");

		});
		//slides the element with class "menu_body" when mouse is over the paragraph
		$("#secondpane h3.menu-head").mouseover(function()
		{
			 $(this).next("div.menu-body").slideDown(500).siblings("div.menu-body").slideUp("slow");

		});

		$(".service-post-tab li").click(function(e) {
                		$(".service-post-tab li.active").removeClass("active");
                		$(this).addClass("active");

                        $(".service-tab-content").hide();
                		$($(this).attr("data-id")).show();
                    });

                	$(".service-post-tab li").click(function(e) {
                		$(".service-post-tab li.active").removeClass("active");
                		$(this).addClass("active");

                        $(".service-tab-content").hide();
                		$($(this).attr("data-id")).show();
                    });
	});


$(function(){
	$('.main_ul li').hover(function(){
		$('span',this).stop().css('height','3px');
		$('span',this).animate({
			left:'0',
			width:'100%',
			right:'0'
		},200);
	},function(){
		$('span',this).stop().animate({
			left:'50%',
			width:'0'
		},200);
	});
});


$(function () {

    $(window).scroll(function () {
        if ($(this).scrollTop() != 0) {
            $('.top_2').addClass('on');
        } else {
            $('.top_2').removeClass('on');
        }
    })

}); 
 
var loadding = "<div align='center' style='margin-top:30px;margin-bottom:30px;'><img src='/images/loadding.gif'/>努力加载中...</div>"; 