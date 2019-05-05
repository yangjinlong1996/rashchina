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
    $("#submitButton").click(function(){
        var pageNum = $("#wantPageNo").val();
        $.ajax({
            url:"/showBuyBooks",
            type:"post",
            data:{"pageNum":pageNum},
            dataType: "json",
            success:function(data){
                showData(data);
            },
            error:function(){
                alert("该页暂无数据");
            }
        });
    })
});
function showData(data) {
    if(data.total == 0){
        alert("您的购物车空空如也,快去购买几本吧！");
    }else{
        $("#pageNo").text(data.pageNum);
        $(".tr").empty();
        for (var i = 0; i < data.list.length; i++) {
            var status = "";
            switch (data.list[i].status) {
                case 1 : status = "待支付";     break;
                case 2 : status = "待商家确认"; break;
                case 3 : status = "待商家发货"; break;
                case 4 : status = "待收货";     break;
                case 5 : status = "交易成功";   break;
                case 6 : status = "待评价";     break;
                case 7 : status = "评价完成";   break;
                default: status = "未知交易状态";break;
            }
            if(data.list[i].courierName == null || data.list[i].courierName == ""){
                data.list[i].courierName = "暂无快递公司信息";
            }
            if(data.list[i].courierNumber == null || data.list[i].courierNumber == ""){
                data.list[i].courierNumber = "暂无快递单号信息";
            }
            str = "<tr class='tr'><td class='td'><a href='https://baike.baidu.com/item/"+data.list[i].bookName+"' class='t_a'>《" + data.list[i].bookName + "》</a></td><td class='td'>" + data.list[i].price + "</td>" +
                "<td class='td' style='font-size: 10px;'>" + status + "</td><td class='td'>" + data.list[i].courierName + "</td>" +
                "<td class='td'>" + data.list[i].courierNumber + "</td><td style='display: none'>" + data.list[i].bookId + "</td>" +
                "<td class='tp'><input type='button' value='支付购买' class='t_button1'onclick='pay()'></td>" +
                "<td class='tp'><input type='button' value='我已支付' class='t_button2'onclick='payBuyBook("+data.list[i].bookId+")'></td></tr>";
            $("#b1").append(str);
        }
    }
}
function pay(){
    alert("扫描下图商家二维码即可购买,支付时请备注书籍名及用户名,方便商家及时发货哦!");
}
function payBuyBook(bookId){
    if(window.confirm('您确定已经支付成功了吗？')){
        $.ajax({
            url:"/payBuyBook",
            type:"post",
            data:{"bookId":bookId},
            dataType: "TEXT",
            success:function(data){
                alert(data);
            },
            error:function(msg){
                alert("网络异常,暂时无法获取数据"+msg);
            }
        });
    }else{
        alert("您已取消操作");
    }
}
$(function(){
    $.ajax({
        url:"/showBuyBooks",
        type:"post",
        dataType: "json",
        success:function(data){
            showData(data);
        },
        error:function(msg){
            alert("网络异常,暂时无法获取数据"+msg);
        }
    });
});


