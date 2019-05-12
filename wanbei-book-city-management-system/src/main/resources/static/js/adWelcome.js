$(function(){
    $.ajax({
        url:"/administrator/showAdBooks",
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
function showData(data){
    var str = "";
    if(data.total == 0){
        alert("数据库中暂无书籍");
    }
    if(data.total != 0){
        $("#pageNum").val(data.pageNum);
        $("#lastPage").val(data.lastPage);
        $(".tr").empty();
        for (var i = 0; i < data.total; i++) {
            str = "<tr class='tr'><td class='td'>" + data.list[i].id + "</td>" +
                "<td class='td'><a href='https://baike.baidu.com/item/"+data.list[i].bookName+"' class='t_a'>《" + data.list[i].bookName + "》</a></td>" +
                "<td class='td'>" + data.list[i].author + "</td>" +
                "<td class='td' style='font-size: 10px;'>" + data.list[i].message + "</td><td class='td'>" + data.list[i].price + "</td>" +
                "<td class='td'>" + data.list[i].mouthSales + "</td><td class='td'>" + data.list[i].allSales + "</td>" +
                "<td class='tp'><input type='button' value='删除本书' class='t_button2'onclick='deleteBook("+ data.list[i].id +")'></td></tr>";
            $("#b1").append(str);
        }
    }
}

function deleteBook(bookId) {
    if(window.confirm('你确定要将该书从书库中删除吗？')){
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
$(document).ready(function(){
    //用户点击搜索
    $("#search").click(function () {
        var search = $("#seachBook").val();
        $.ajax({
            url:"/administrator/showAdBooks",
            type:"post",
            data:{"search":search},
            dataType: "json",
            success:function(data){
                showData(data);
            },
            error:function(msg){
                alert("网络异常,暂时无法获取数据"+msg);
            }
        })
    })
    $("#top").click(function () {
        var pageNo = $("#pageNum").val();
        if(pageNo == "1"){
            alert("当前已经是第一页了哦！");
        }else{
            var pageNum = Number(pageNo) - 1 ;
            $.ajax({
                url:"/administrator/showAdBooks",
                type:"post",
                data:{"pageNum":pageNum},
                dataType: "json",
                success:function(data){
                    showData(data);
                },
                error:function(msg){
                    alert("网络异常,暂时无法获取数据"+msg);
                }
            })
        }
    })
    $("#bottom").click(function () {
        var pageNo = $("#pageNum").val();
        var page = $("#lastPage").val();
        if(page == "true"){
            alert("当前已经是第最后一页了哦！");
        }else{
            var pageNum = Number(pageNo) + 1 ;
            $.ajax({
                url:"/administrator/showAdBooks",
                type:"post",
                data:{"pageNum":pageNum},
                dataType: "json",
                success:function(data){
                    showData(data);
                },
                error:function(msg){
                    alert("网络异常,暂时无法获取数据"+msg);
                }
            })
        }
    })
    //弹出div层
    $("#updateBook").click(function () {
        $("#updateDiv input").val("");
        $("#updateO").val("确认修改");
        $("#updateDiv").fadeIn(500);
    })
    //弹出div层
    $("#insertBook").click(function () {
        $("#insertDiv").fadeIn(500);
        $("#insertDiv input").val("");
        $("#updateT").val("确认添加");
    })
    $("#updateO").click(function () {
        $("#updateDiv").fadeOut(500,function () {
            var enable = 1;
            var bookId = $("#bookId").val();
            var bookName = $("#bookName").val();
            var bookAuthor = $("#bookAuthor").val();
            var bookMessage = $("#bookMessage").val();
            var bookPrice = $("#bookPrice").val();
            $.ajax({
                headers:{
                    "content-type":"application/json"
                },
                url:"/administrator/updateBook",
                type:"post",
                data:JSON.stringify({"bookId":bookId,"bookName":bookName,"bookAuthor":bookAuthor,
                    "bookMessage":bookMessage,"bookPrice":bookPrice,"enable":enable}),
                dataType:"TEXT",
                success:function(data){
                    alert(data);
                },
                error:function () {
                    alert("网络异常,请稍后再试");
                }
            })
        });
    })
    $("#updateT").click(function () {
        $("#insertDiv").fadeOut(500,function () {
            var enable = 2;
            var bookName = $("#bookName_1").val();
            var bookAuthor = $("#bookAuthor_1").val();
            var bookMessage = $("#bookMessage_1").val();
            var bookPrice = $("#bookPrice_1").val();
            var mouthSales = $("#mouth_sales").val();
            var allSales = $("#all_sales").val();
            var username = $("#people").val();
            $.ajax({
                headers:{
                    "content-type":"application/json"
                },
                url:"/administrator/updateBook",
                type:"post",
                data:JSON.stringify({"bookName":bookName,"bookAuthor":bookAuthor,
                    "bookMessage":bookMessage,"bookPrice":bookPrice,
                    "mouthSales":mouthSales,"allSales":allSales,"username":username,"enable":enable}),
                dataType:"TEXT",
                success:function(data){
                    alert(data);
                },
                error:function () {
                    alert("网络异常,请稍后再试");
                }
            })
        });
    })
    $("#user").click(function () {
        window.location.href = "/administrator/ad_user";
    })
    $("#info").click(function () {
        window.location.href = "/administrator/ad_info";
    })
    $("#pay").click(function () {
        window.location.href = "/administrator/ad_pay";
    })
})
