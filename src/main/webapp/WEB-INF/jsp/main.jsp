<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>教学支持系统</title>
<script type="text/javascript">
	function beforeSubmit(form){
		if(form.type.value=="0"){
			alert('请选择文件类型!');
			form.type.focus();
			return false;
		}
		if(form.type.value=="1" && form.userdefined.value.length==0){
			alert('请输入自定义类型!');
			form.time.focus();
			return false;
		} 
		if(form.time.value=="0"){
			alert('请选择迭代周期!');
			form.time.focus();
			return false;
		} 
		if(form.file.value.length==0){
			alert('请选择文件!');
			form.file.focus();
			return false;
		} 
	}
</script>
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
	    //用户名
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
	<div class="main_main">
		<form action="upload" method="post" enctype="multipart/form-data" onsubmit="return beforeSubmit(this);">
			<select name="type" type="text">
				<option value="0">选择文件类型</option>
				<option value="用例文档">用例文档</option>
				<option value="需求规格说明文档">需求规格说明文档</option>
				<option value="体系结构描述文档">体系结构描述文档</option>
				<option value="详细设计描述文档">详细设计描述文档</option>
				<option value="测试用例">测试用例</option>
				<option value="1">自定义类型(在下方输入类型名称)</option>
			</select>
			<hr class="hr15">
			<input type="text" placeholder="自定义类型" name="userdefined" />
			<hr class="hr15">
			<select name="time" type="text">
				<option value="0">选择迭代周期</option>
				<option value="迭代一">迭代一</option>
				<option value="迭代二">迭代二</option>
				<option value="迭代三">迭代三</option>
			</select>
			<hr class="hr15">
			<input type="file" name="file" size="50" />
			<hr class="hr15">
			<input type="submit" value="提交" name="submit" /> 

		</form>
	</div>
</body>
</html>