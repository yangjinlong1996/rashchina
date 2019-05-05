$(document).ready(function(){
    $("#d1 li").mouseenter(function(){
        $(this).find("ul").slideDown(); //展开
    }).mouseleave(function(){
        $(this).find("ul").slideUp();//收起
    })
    $("input,.c2").click(function(){
        alert("您好,您的登录身份为游客,暂无此权限,请登录后再试");
    })
});
var i = 0;
var timer;
$(function(){
    $("img").eq(0).show().siblings().hide();
    start();
    $("b").hover(function(){
        clearInterval(timer);
        i = $(this).index();
        change();
    }, function(){
        start();
    });
    $(".left").click(function(){
        i--;
        if(i == -1){
            i = 5;
        }
        change();
    });
    $(".right").click(function(){
        i++;
        if(i == 6){
            i = 0;
        }
        change();
    });
});
function start(){
    timer = setInterval(function(){
        i++;
        if(i == 6){
            i = 0;
        }
        change();
    }, 3000);
}
function change(){
    $("img").eq(i).fadeIn(300).siblings().stop(true, true).fadeOut(300);
    $("b").eq(i).addClass("current").siblings().removeClass("current");
}
window.onload=function(){
    var v1=document.getElementById('d6');
    var v2=document.getElementById('div2');
    var v3=document.getElementById('div3');

    v3.innerHTML= v2.innerHTML;//将v2容器里面的图片插入到v3容器里面  使其空白区域被遮住。
    function fun(){
        if(v1.scrollLeft<=0){
            v1.scrollLeft=1700;
        }else{
            v1.scrollLeft--;
        }
    }
    var fun1=setInterval(fun,10);
    v1.onmouseover = function() {//鼠标经过时  清除定时器  停止图片的滚动
        clearInterval(fun1)
    };
    v1.onmouseout = function() {//鼠标离开后  继续滚动图片
        fun1 = setInterval(fun, 10)
    };
}


