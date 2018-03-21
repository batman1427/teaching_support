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
			<li><a href="main_project?">项目信息</a></li>
			<li><a href="main_file?path=&branch=master">项目结构</a></li>
			<li><a href="main_codeRecord?hash=0&branch=master">代码记录</a></li>
			<li><a href="main_graphs?">相关图表</a></li>
			<li><a href="main_document?">文档提交</a></li>
			<li><a href="main_docuRecord?">文档记录</a></li>
			<li><a href="main_teamAnalysis?">团队分析</a></li>
			<li><a href="main_score?">成绩查询</a></li>
			<li><a href="main_discussion?show=1&content=">交流讨论</a></li>
		</ul>
		<div style="clear: both"></div>
	</div>
	<div class="main_person">
		<table border="2"  >
		<tr>
  			<td width=200 style="font-family:'宋体';font-size:16px;font-weight: bold;">项目名称</td>
  			<td width=600 style="font-family:'宋体';font-size:16px;font-weight: bold;">cseiii_group_<%=(Integer)session.getAttribute("groupid")%></td>
		</tr>   
		<tr>
  			<td width=200 style="font-family:'宋体';font-size:16px;font-weight: bold;">项目地址</td>
  			<td width=600 style="font-family:'宋体';font-size:16px;font-weight: bold;"><%=(String)session.getAttribute("gitlab")%></td>
		</tr>
		<tr>
  			<td width=200 style="font-family:'宋体';font-size:16px;font-weight: bold;">团队成员</td>
  			<td width=600 style="font-family:'宋体';font-size:16px;font-weight: bold;"><%=(String)session.getAttribute("members")%></td>
		</tr>   
		<tr>
  			<td width=200 style="font-family:'宋体';font-size:16px;font-weight: bold;">总提交次数</td>
  			<td width=600 style="font-family:'宋体';font-size:16px;font-weight: bold;"><%=(Integer)session.getAttribute("commit_total")%></td>
		</tr> 
		<tr>
  			<td width=200 style="font-family:'宋体';font-size:16px;font-weight: bold;">最近一次提交</td>
  			<td width=600 style="font-family:'宋体';font-size:16px;font-weight: bold;"><a href="main_codeRecord?hash=0&branch=master"><%=(String)session.getAttribute("recent_commit")%></a></td>
		</tr>
		<tr>
  			<td width=200 style="font-family:'宋体';font-size:16px;font-weight: bold;">项目分支数</td>
  			<td width=600 style="font-family:'宋体';font-size:16px;font-weight: bold;"><%=(Integer)session.getAttribute("br")%></td>
		</tr>
	</div>
</table>
</body>
</html>