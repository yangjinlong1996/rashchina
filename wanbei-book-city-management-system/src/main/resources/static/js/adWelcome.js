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

})