<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>教学支持系统</title>
<link href="css/main.css" type="text/css" rel="stylesheet">
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

#nav {
	width: 1300px;
	margin: 0px auto 0px auto;
	background: #27A9E3;
	color: #ffffff;
}

#nav li {
	float: left;
	display: inline;
}

#nav li a {
	display: block;
	color: #ffffff;
	padding: 10px 15px;
	font-size: 16px;
	font-weight: bold;
	text-decoration: none;
}
</style>
</head>
<body>
	<%
				String user = (String)session.getAttribute("username");
	%>
	<div class="message">教学支持系统</div>
	<div class="user">
		欢迎你:<%=user%>; <a style="color: #ff0000; font-size: 16px" href="sign_in">退出!</a>
	</div>
	<div id="nav">
		<ul>
			<li><a href="main?">提交作业</a></li>
			<li><a href="selfstat?">个人提交</a></li>
			<li><a href="teamstat?">团队提交</a></li>
			<li><a href="score?">成绩查询</a></li>
			<li><a href="person?">个人信息</a></li>
		</ul>
		<div style="clear: both"></div>
	</div>
    <div style="display:inline"> 
	<div class="teamstat_table" style="float:left">
	<img src="images/<%=(Integer)session.getAttribute("groupid") %>teambar.jpg">
	</div>
	<div class="teamstat_chart" style="float:left">
	<img src="images/<%=(String)session.getAttribute("studentid") %>teampie.jpg">
	</div>
</div>
</body>
</html>