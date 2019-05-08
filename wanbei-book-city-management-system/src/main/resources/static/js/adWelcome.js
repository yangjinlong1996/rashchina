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
                "<td class='tp'><input type='button' value='编辑书籍' class='t_button1'onclick='addBook("+ data.list[i].id +")'></td>" +
                "<td class='tp'><input type='button' value='删除本书' class='t_button2'onclick='deleteBook("+ data.list[i].id +")'></td></tr>";
            $("#b1").append(str);
        }
    }
}