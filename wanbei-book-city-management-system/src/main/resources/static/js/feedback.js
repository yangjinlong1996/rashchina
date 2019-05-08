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
$(document).ready(function(){
    $("#d1 li").mouseenter(function(){
        $(this).find("ul").slideDown(); //展开
    }).mouseleave(function(){
        $(this).find("ul").slideUp();//收起
    })
    $("#button").click(function () {
        var feedback = $("#textarea").val();
        var contactWay = $("#contactWay").val();
        $.ajax({
            //必须要写的四个参数,顺序不限
            url:"/feedbackInset",
            //处理页面的路径
            data:{"feedback":feedback,"contactWay":contactWay,"contactWay":contactWay},
            //传递的数据.提交数一般以json格式来写,key是自定义的,:后面的值 就是上面的值
            type:"POST",
            //数据的提交传递方式,GET,POST 最好用POST
            success:function(data){
                alert(data);
            },
        })
    })
});