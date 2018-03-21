<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache"> 
<meta http-equiv="Expires" content="0"> 
<title>教学支持系统登录</title>
<link href="css/login.css" type="text/css" rel="stylesheet"> 
</head> 
<body> 
<div class="login">
    <div class="message">教学支持系统登录</div>
    <div id="darkbannerwrap"></div>
    <form:form commandName="user" action="login" method="post">
		<form:input path="username" placeholder="用户名" required="true" id="name" />
		<hr class="hr15">
		<form:password path="password" placeholder="密码" required="true" id="password"/>
		<hr class="hr15">
		<input value="登录" style="width:100%;" type="submit">
		<hr class="hr20">
		<a href="register-turn">注册新用户</a>
	</form:form>

	
</div>

</body>
</html>