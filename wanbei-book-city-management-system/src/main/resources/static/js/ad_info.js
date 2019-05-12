window.onload = function(){
    $.ajax({
        url:"/administrator/showAdinfo",
        type:"post",
        dataType: "json",
        success:function(data){
            showData(data);
        },
        error:function(msg){
            alert("网络异常,暂时无法获取数据"+msg);
        }
    });
}
function showData(data){
    var str  = "";
    if(data.total == 0){
        alert("数据库中暂无合作信息");
    }
    if(data.total != 0){
        $("#pageNum").val(data.pageNum);
        $("#lastPage").val(data.lastPage);
        $(".tr").empty();
        for (var i = 0; i < data.total; i++) {
            var type = "其他类型";
            switch (data.list[i].type) {
                case 1 : type = "书籍合作";break;
                case 2 : type = "广告合作";break;
                case 3 : type = "应聘合作";break;
                case 4 : type = "其他合作";break;
                case 5 : type = "建议反馈";break;
                default : type = "其他合作" ;break;
            }
            str = "<tr class='tr'><td class='td'>" + data.list[i].id + "</td>" +
                "<td class='td'>" + data.list[i].username + "</td>" +
                "<td class='td' style='font-size: 10px;'>" + data.list[i].information + "</td>" +
                "<td class='td'>" + data.list[i].email + "</td>" +
                "<td class='td'>" + data.list[i].contactWay + "</td>" +
                "<td class='td'>"+ type +"</td>" +
                "<td class='tp'><input type='button' value='联系合作' class='t_button2' onclick='info()'></td></tr>";
            $("#b1").append(str);
        }
    }
}
function info() {
    alert("请您点击左边的联系方式就可以合作了哦！")
}
$(document).ready(function(){
    $("#top").click(function () {
        var pageNo = $("#pageNum").val();
        if(pageNo == "1"){
            alert("当前已经是第一页了哦！");
        }else{
            var pageNum = Number(pageNo) - 1 ;
            $.ajax({
                url:"/administrator/showAdinfo",
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
                url:"/administrator/showAdinfo",
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
    $("#user").click(function () {
        window.location.href = "/administrator/ad_user";
    })
    $("#book").click(function () {
        window.location.href = "/administrator/ad";
    })
    $("#info").click(function () {
        window.location.href = "/administrator/ad_info";
    })
    $("#pay").click(function () {
        window.location.href = "/administrator/ad_pay";
    })
    $("#search").click(function () {
        var search = $("#seachBook").val();
        $.ajax({
            url:"/administrator/showAdinfo",
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