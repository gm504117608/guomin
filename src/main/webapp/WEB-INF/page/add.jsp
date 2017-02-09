<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>SpringMVC+MySql+MyBatis---Test</title>
</head>

<script type="text/javascript" src="<%= basePath %>/js/jquery.min.js" ></script>
<script type="text/javascript" src="js/jquery.serializejson.min.js" ></script>

<body>
<form id="form" name="form" method="post">
    <p>
        <span>账号</span><input name="name" id="name" type="text"/>
    </p>
    <p>
        <span>密码</span><input name="password" id="password" type="text"/>
    </p>
    <p>
        <span>地址</span><input name="address" id="address" type="text"/>
    </p>
</form>
<a href="#" id="button" onClick="save();">添 加</a>
</body>

<script type="text/javascript">
    var path = "<%= path %>";
    var basePath = "<%= basePath %>";

    function save() {
        var json = $('#form').serializeJSON();
        console.log(json);
        $.ajax({
            url: path + "/add",
            type:"POST",
            async: true,
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            data: json,
            success : function(data){
                console.log(data);
                alert("操作成功");
            },
            error:function(data){
                console.log(data);
                alert("操作失败");
            }
        });
    }
</script>
</html>