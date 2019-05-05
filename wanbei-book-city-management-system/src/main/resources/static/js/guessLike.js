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
    $("#buy").click(function () {
        var price = 32.80;
        /* 返回书名给后台,后台由书名查询书的id */
        var bookName = "追风筝的人";
        var message = "";
        $.ajax({
            url:"/mallSellingBuy",
            type:"post",
            data:{"price":price,"bookName":bookName,"message":message},
            dataType:"TEXT",
            success:function (message) {
                alert(message);
            },
            error:function () {
                alert("网络异常"+message);
            }
        })
    })
});

