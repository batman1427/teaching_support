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
			<li><a href="admin_group?">分组信息</a></li>
			<li><a href="admin_file?path=&branch=master&gid=1">项目结构</a></li>
			<li><a href="admin_codeRecord?hash=0&branch=master&gid=1">代码记录</a></li>
				<li><a href="admin_graphs?gid=1">相关图表</a></li>
				<li><a href="admin_teamAnalysis?gid=1">团队分析</a></li>
				<li><a href="admin_discussion?show=1&content=">交流讨论</a></li>
		</ul>
		<div style="clear: both"></div>
	</div>
	<div class="main_person">
		<table border="2"  >
		<tr>
  			<td width=100 style="font-family:'宋体';font-size:16px;font-weight: bold;">分组</td>
  			<td width=300 style="font-family:'宋体';font-size:16px;font-weight: bold;">项目</td>
  			<td width=600 style="font-family:'宋体';font-size:16px;font-weight: bold;">小组成员</td>
		</tr>   
		<tr>
  			<td width=100 style="font-family:'宋体';font-size:16px;font-weight: bold;">1</td>
  			<td width=300 style="font-family:'宋体';font-size:16px;font-weight: bold;"><a href="admin_file?path=&branch=master&gid=1">cseiii_group_1</a></td>
  			<td width=600 style="font-family:'宋体';font-size:16px;font-weight: bold;">131250122_冯鑫;131250000_admin;131250214_夏存舜;131250217_夏文治;</td>
		</tr>
	</div>
</table>
</body>
</html>