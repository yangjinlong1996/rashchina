window.onload = function(){
    $.ajax({
        url:"/showAuUser",
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
function showData(data) {
    var str = "";
    if (data.total == 0) {
        alert("数据库中暂无用户信息");
    }
    if (data.total != 0) {
        $("#pageNum").val(data.pageNum);
        $("#lastPage").val(data.lastPage);
        $(".tr").empty();
        for (var i = 0; i < data.total; i++) {
            for (var i = 0; i < data.total; i++) {
                var people = "未知";
                switch (data.list[i].people) {
                    case 1 :
                        people = "用户";
                        break;
                    case 2 :
                        people = "系统管理员";
                        break;
                    case 3 :
                        people = "开发人员";
                        break;
                    default:
                        people = "未知";
                        break;
                }
                var de = "未知";
                if(data.list[i].isDelete == 0){
                    de = "已启用";
                }else{
                    de = "已禁用";
                }
                str = "<tr class='tr'><td class='td'>" + data.list[i].id + "</td>" +
                    "<td class='td'>" + data.list[i].userId + "</td>" +
                    "<td class='td'>" + data.list[i].username + "</td>" +
                    "<td class='td'>" + de + "</td>" +
                    "<td class='td'>" + data.list[i].data + "</td>" +
                    "<td class='td' style='font-size: 10px;'>" + data.list[i].pwdKey + "</td>" +
                    "<td class='td'>" + people + "</td>" +
                    "<td class='tp'><input type='button' value='禁用账户' class='t_button2' onclick='deleteUser(" + data.list[i].id + ")'></td>" +
                    "<td class='tp'><input type='button' value='启用账户' class='t_button2' onclick='openUser(" + data.list[i].id + ")' style='background-color: deepskyblue'></td></tr>";
                $("#b1").append(str);
            }
        }
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
                url:"/showAuUser",
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
                url:"/showAuUser",
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
    $("#search").click(function () {
        var search = $("#seachBook").val();
        $.ajax({
            url:"/showAuUser",
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
function deleteUser(id) {
    if(confirm("确定禁用该账户吗?")){
        var enable = 1;
        $.ajax({
            url:"/deleteUserAu",
            type:"post",
            data:{"id":id,"enable":enable},
            dataType: "TEXT",
            success:function(data){
                alert(data);
            },
            error:function(msg){
                alert("网络异常,暂时无法获取数据"+msg);
            }
        })
    }
}
function openUser(id) {
    if(confirm("确定启用该账户吗?")){
        var enable = 2;
        $.ajax({
            url:"/deleteUserAu",
            type:"post",
            data:{"id":id,"enable":enable},
            dataType: "TEXT",
            success:function(data){
                alert(data);
            },
            error:function(msg){
                alert("网络异常,暂时无法获取数据"+msg);
            }
        })
    }
}