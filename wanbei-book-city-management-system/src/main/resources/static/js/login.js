$(document).ready(function(e){
    $("body").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
            $('#button').click();
        }
    });
    $("#button").click(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        var pwdKey = $("#pwdKey").val();
        var people = $("input[name='people']:checked").val();
        var message = "";
        $.ajax({
            //必须要写的四个参数,顺序不限
            url:"/login",
            //处理页面的路径
            data:{"username":username,"password":password,"pwdKey":pwdKey,"people":people,"message":message},
            //传递的数据.提交数一般以json格式来写,key是自定义的,:后面的值 就是上面的值
            type:"POST",
            //数据的提交传递方式,GET,POST 最好用POST
            success:function(message){
                //如果ajax执行成功,返回来调用success函数即回调函数,返回值以参数的形式返回
                if(message == "登录成功!"){
                    alert(message);
                    window.location.href = "/welcome";
                }else if(message == "尊敬的游客,欢迎您！") {
                    alert(message);
                    window.location.href = "/tourist";
                }else{
                        alert(message);
                    }
            },
        });
    })
});