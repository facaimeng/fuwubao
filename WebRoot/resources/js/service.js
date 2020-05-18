/**
 * Created by dookey-tl on 2015/6/29.
 */
var serviceApp = function(){

  //产品总也侧边导航浮动
  var affixFun = function(){

    var $aboutmenu = $('#j_about_menu');

    var $win = $(window);
     var drag=true;
    affixOffset($win);
    $win.resize(function(){
      affixOffset($win);
    });
    function affixOffset(){
      if(drag){
        var w = $win.width()
        if(w > 1366 && $aboutmenu.hasClass('affix')){
          $aboutmenu.css({right:(w-1366)/2});
        }else if(w < 1366 && $aboutmenu.hasClass('affix') || w > 1366 && !$aboutmenu.hasClass('affix')){
          $aboutmenu.css({right:0});
        }else if(w < 1366 && !$aboutmenu.hasClass('affix')){
          $aboutmenu.css({right:1366-w});
        }
        drag=false;
        setTimeout(function(){
          drag=true;
        },50);
      }
    }

    $aboutmenu.affix({
      offset: {top:565}
    });

    $aboutmenu.on('affixed.bs.affix',function(){
      var w = $win.width();
      w > 1366?$aboutmenu.css({right:(w-1366)/2}):$aboutmenu.css({right:0});
    });
    $aboutmenu.on('affixed-top.bs.affix',function(){
      var w = $win.width();
      $aboutmenu.css({right:0});
      w > 1366?$aboutmenu.css({right:0}):$aboutmenu.css({right:1366-w});
      //w > 1366?$aboutmenu.css({right:(w-1366)/2}):$aboutmenu.css({right:0});
    });

  }
  //首页跳转至指定产品与服务页指定锚点
  var jumpToAnchor = function(){
    var href = location.href;
    var arrUrl = href.split('#');
    var arrArgument = [];
    var txtHref = '#';
    var iH = 0;
    var sName = '';
    var sVal = '';
    if(arrUrl.length > 1) {
      arrArgument = arrUrl[arrUrl.length-1].split('&');
      for(var i = 0; i < arrArgument.length; i++){
        sName = arrArgument[i].split('=')[0];
        sVal = arrArgument[i].split('=')[1];
        if(sName == 'anchor'){
          txtHref += sVal;
        }
      }
      //txtHref += arrHref[arrHref.length - 1];
      //window.history.pushState('', '', arrHref[0]);
      /*iH = $(txtHref).offset().top - 73;
       $(document).scrollTop(iH);*/
      $('.about-menu').find('a[href=' + txtHref +']').trigger('click');
      location.hash = '';
    }
  }
  //导航栏点击不显示
  var hideMenu=function(){
    $(".service-nav dd").click(function(){
      $(".service-nav").removeClass("open");
    })
  }

  return{
    init:function(){
      hideMenu();
    },
    product:function(){
      affixFun();
    },
    jumpToAnchor:jumpToAnchor
  }
}();