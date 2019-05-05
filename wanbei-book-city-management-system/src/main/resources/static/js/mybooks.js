$(document).ready(function(){
    $("#d1 li").mouseenter(function(){
        $(this).find("ul").slideDown(); //展开
    }).mouseleave(function(){
        $(this).find("ul").slideUp();//收起
    })
    $("#likeBookPage").click(function(){
        var pageNum = $("#wantPageNum").val();
        var total = 0;
        var pages = 0;
        var lastPage = true;
        var bookName = "";
        var username = "";
        var str = "";
        $.ajax({
            url:"/showLikeBooks",
            type:"post",
            dataType: "json",
            data:{"pageNum":pageNum,"total":total,"pages":pages,"lastPage":lastPage,"list":[{"bookName":bookName,"username":username}]},
            success:function(data){
                if(data.total == 0){
                    alert("您书架里暂无藏书，请前去添加几本吧！");
                }else{
                    $("#pageNum").text(data.pageNum);
                    $("#total").text(data.total);
                    $(".tr1").empty();
                    //按list的长度批量输出list
                    for (var i = 0; i < data.list.length; i++) {
                        str = "<tr class='tr1'><td class='td'><a href='https://baike.baidu.com/item/"+data.list[i].bookName+"' class='t_a'>《" + data.list[i].bookName + "》</a></td>" +
                            "<td style='display: none'>" + data.list[i].bookId + "</td>" +
                            "<td class='tp'><input type='button' value='购买书籍' class='t_button2'onclick='buyBook("+data.list[i].bookId+")'></td></tr>";
                        $("#b2").append(str);
                    }
                }
            },
            error:function(msg){
                alert("网络异常,暂时无法获取数据"+msg);
            }
        });
    })
    $("#submitButton").click(function(){
        var pageNo = $("#wantPageNo").val();
        var dataMessage = "";
        $.ajax({
            url:"/showbooks",
            type:"post",
            data:{"pageNo":pageNo,"dataMessage":dataMessage},
            dataType: "json",
            success:function(dataMessage){
                showData(dataMessage);
            },
            error:function(){
                alert("该页暂无数据");
            }
        });
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
$(function(){
    $.ajax({
        url:"/showbooks",
        type:"post",
        dataType: "json",
        success:function(dataMessage){
            showData(dataMessage);
        },
        error:function(msg){
            alert("网络异常,暂时无法获取数据"+msg);
        }
    });
});
function showData(data){
    var str = "";
    if(data.length == 0){
        if(pageNo == "1"){
            alert("您的书库中空空如也,快去收藏几本吧！");
        }else{
            alert("该页暂无书籍！");
        }
    }
    if(data[0].data != null){
        if(data[0].data < 1) {
            alert("暂时无法查询到数据");
        }else{
            $("#pageNo").text(data[0].data);
            $(".tr").empty();
            for (var i = 0; i < data.length; i++) {
                str = "<tr class='tr'><td class='td'><a href='https://baike.baidu.com/item/"+data[i].bookName+"' class='t_a'>《" + data[i].bookName + "》</a></td><td class='td'>" + data[i].author + "</td>" +
                    "<td class='td' style='font-size: 10px;'>" + data[i].message + "</td><td class='td'>" + data[i].price + "</td>" +
                    "<td class='td'>" + data[i].mouthSales + "</td><td class='td'>" + data[i].allSales + "</td><td style='display: none'>" + data[i].id + "</td>" +
                    "<td class='tp'><input type='button' value='加入书架' class='t_button1'onclick='addBook("+data[i].id+")'></td>" +
                    "<td class='tp'><input type='button' value='删除本书' class='t_button2'onclick='deleteBook("+data[i].id+")'></td></tr>";
                $("#b1").append(str);
            }
        }
    }
}
function addBook(bookId) {
    var message = "";
    $.ajax({
         url:"/addBooks",
         type:"post",
         data:{"bookId":bookId,"message":message},
         dataType: "TEXT",
        success:function(message){
            alert(message);
        },
        error:function(msg){
            alert("网络异常,暂时无法获取数据"+msg);
        }
    });
};
function deleteBook(bookId) {
    if(window.confirm('你确定要将该书从您的丛书中删除吗？')){
        var message = "";
        $.ajax({
            url:"/deleteBook",
            type:"post",
            data:{"bookId":bookId,"message":message},
            dataType: "TEXT",
            success:function(message){
                alert(message);
            },
            error:function(msg){
                alert("网络异常,暂时无法获取数据"+msg);
            }
        });
    }else{
        alert("您已取消操作");
    }
};
$(function(){
    var total = 0;
    var pages = 0;
    var pageNum = 1;
    var bookId = 0;
    var lastPage = true;
    var bookName = "";
    var username = "";
    var str = "";
    $.ajax({
        url:"/showLikeBooks",
        type:"post",
        dataType: "json",
        data:{"pageNum":pageNum,"total":total,"pages":pages,"lastPage":lastPage,"list":[{"bookId":bookId,"bookName":bookName,"username":username}]},
        success:function(data){
            if(data.total == 0){
                alert("您书架里暂无藏书，请前去添加几本吧！");
            }else{
                $("#pageNum").text(data.pageNum);
                $("#total").text(data.total);
                $(".tr1").empty();
                //按list的长度批量输出list
                for (var i = 0; i < data.list.length; i++) {
                    str = "<tr class='tr1'><td class='td'><a href='https://baike.baidu.com/item/"+data.list[i].bookName+"' class='t_a'>《" + data.list[i].bookName + "》</a></td>" +
                        "<td style='display: none'>" + data.list[i].bookId + "</td>" +
                        "<td class='tp'><input type='button' value='购买书籍' class='t_button2'onclick='buyBook("+data.list[i].bookId+")'></td></tr>";
                    $("#b2").append(str);
                }
            }
        },
        error:function(msg){
            alert("网络异常,暂时无法获取数据"+msg);
        }
    });
});
function buyBook(bookId) {
    var message = "";
    $.ajax({
        url:"/buyBook",
        type:"post",
        data:{"bookId":bookId,"message":message},
        dataType: "TEXT",
        success:function(message){
            alert(message);
        },
        error:function(msg){
            alert("网络异常,暂时无法购买书籍"+msg);
        }
    });
};

$(function(){
    var books = 0;
    var loveBooks = 0;
    var buyBooks = 0;
    $.ajax({
        url:"/statistics",
        type:"post",
        dataType: "json",
        data:{"books":books,"loveBooks":loveBooks,"buyBooks":buyBooks},
        success:function(data){
            $("#books").text(data.books);
            $("#loveBooks").text(data.loveBooks);
            $("#buyBooks").text(data.buyBooks);
        },
    });
});