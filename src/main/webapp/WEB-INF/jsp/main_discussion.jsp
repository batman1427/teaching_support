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
	<% String show=(String)session.getAttribute("show"); 
	   String discussion=(String)session.getAttribute("discussion");
	   String commit=(String)session.getAttribute("commit");
	   if(show.equals("1")){
	%>
	<div class="test1">
	<% String[] result=discussion.split(";");
	   for(int i=0;i<result.length;i=i+3){%>
		   <a href="main_discussion?show=2&content=<%=result[i+2]%>" style="color: #27A9E3; font-size: 20px;font-weight:bold"><%=result[i]%></a>
	       <br>
	       <p style="color: #000000; font-size: 16px;"><%=result[i+1] %></p>
	  <% }
	%>
	</div>
	<div class="test2">
	<form action="create" method="post">
		<input name="theme" placeholder="主题" required="true" type="text">
		<hr class="hr15">		
		<input value="发布" align="right" style="width:30%;" type="submit">
	</form>
	</div>
	  <%}else{ %>
		  <div class="test1">
			<% String[] result=commit.split(";");
			   for(int i=0;i<result.length;i=i+2){%>
			       <%if(i==0){ %>
			      <p style="color: #27A9E3; font-size: 20px;font-weight:bold">主题:<%=result[i] %></p>
			       <p style="color: #000000; font-size: 12px;"><%=result[i+1] %></p>
			       <p style="color: #27A9E3; font-size: 20px;font-weight:bold"">最新评论:</p>
			      <% }else{%>
				   <p style="color: #27A9E3; font-size: 16px;font-weight:bold"><%=result[i] %></p>
			       <p style="color: #000000; font-size: 12px;"><%=result[i+1] %></p>
			  <%   }
			  }
			%>
			</div>
			<div class="test2">
			<form action="commit" method="post">
				<input name="commit" placeholder="评论" required="true" type="text">
				<hr class="hr15">		
				<input value="发布" align="right" style="width:30%;" type="submit">
			</form>
			</div>
	 <% } %>
</body>
</html>