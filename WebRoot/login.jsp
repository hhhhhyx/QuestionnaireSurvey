<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录界面</title>
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
</head>

<body>
<h2>管理员登录</h2>
<p><span id="loginHit"><%=session.getAttribute("msg")==null?"":session.getAttribute("msg")%><%session.removeAttribute("msg");%></span></p>
<form action="./login" method="post" class="information">
    <table>
        <tr>
            <td >用户名：</td>
            <td><input type="text"  name="userName"></td>
        </tr>
        <tr>
            <td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="登录"></td>
        </tr>
        
    </table>
</form>
</body>
</html>