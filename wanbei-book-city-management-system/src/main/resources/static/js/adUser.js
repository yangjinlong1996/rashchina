window.onload = function(){
    $.ajax({
        url:"/administrator/showAdUser",
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
    var str = "";
    if(data.total == 0){
        alert("数据库中暂无用户信息");
    }
    if(data.total != 0){
        $("#pageNum").val(data.pageNum);
        $("#lastPage").val(data.lastPage);
        $(".tr").empty();
        for (var i = 0; i < data.total; i++) {
            str = "<tr class='tr'><td class='td'>" + data.list[i].id + "</td>" +
                "<td class='td'>" + data.list[i].username + "</td>" +
                "<td class='td' style='font-size: 10px;'>" + data.list[i].pwdKey + "</td>" +
                "<td class='tp'><input type='button' value='禁用账户' class='t_button2' onclick='deleteUser("+ data.list[i].id +")'></td></tr>";
            $("#b1").append(str);
        }
    }
}
function deleteUser(id){
    if(confirm("确定禁用该账户?")){
        var enable = 2;
        $.ajax({
            headers:{
                "content-type":"application/json"
            },
            url:"/administrator/updateUser",
            type:"post",
            data:JSON.stringify({"id":id,"enable":enable}),
            dataType:"TEXT",
            success:function(data){
                alert(data);
            },
            error:function () {
                alert("网络异常,请稍后再试");
            }
        })
    }else{
        alert("您已取消操作");
    }
}
$(document).ready(function(){
    $("#top").click(function () {
        var pageNo = $("#pageNum").val();
        if(pageNo == "1"){
            alert("当前已经是第一页了哦！");
        }else{
            var pageNum = Number(pageNo) - 1 ;
            $.ajax({
                url:"/administrator/showAdUser",
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
                url:"/administrator/showAdUser",
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
    $("#updateO").click(function () {
        $("#updateDiv").fadeOut(500,function () {
            var enable = 1;
            var id = $("#id").val();
            var username = $("#username").val();
            var pwdKey = $("#pwdKey").val();
            $.ajax({
                headers:{
                    "content-type":"application/json"
                },
                url:"/administrator/updateUser",
                type:"post",
                data:JSON.stringify({"id":id,"username":username,
                    "pwdKey":pwdKey,"enable":enable}),
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
            url:"/administrator/showAdUser",
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
