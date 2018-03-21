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
	<div class="test">
	 <%   String branch=(String)session.getAttribute("codebranch");%>
     <%   String hash=(String)session.getAttribute("hash");
          if(hash.equals("0")){%>
        	 <%   String  te=(String)session.getAttribute("codebranches");
	     			String[] branches=te.split(";");
	    		 for(int i=0;i<branches.length;i++){%>
	    		 <a href="main_codeRecord?hash=0&branch=<%=branches[i]%>" style="color: #27A9E3; font-size: 16px;"><%=branches[i]%>分支</a>
	    	 &nbsp;&nbsp;&nbsp;&nbsp;
	   <%  	}
	
	%>
	    			<br> 
        <% 	String temp=(String)session.getAttribute("coderecord");
          	String[] record=temp.split(";");
          	for(int i=0;i<record.length;i++){ 
               	String[] a=record[i].split("&");                %>
        	   <a href="main_codeRecord?hash=<%=a[3]%>&branch=<%=branch%>" style="color: #000000; font-size: 16px;font-weight:bold">提交说明:<%=a[0] %></a>
                <br>
                <p style="color: #000000; font-size: 12px;">提交者:<%=a[1]%>&nbsp;&nbsp;提交时间:<%=a[2]%></p>
         <%  }
         }else{
        	 String detail=(String)session.getAttribute("detail");
        	 String[] details=detail.split(";");%>
        	 <p style="color: #000000; font-size: 20px;">修改了<%=details[0]%>个文件;新增了<%=details[1]%>行;删除了<%=details[2]%>行</p>
        <%  
             String filenames=(String)session.getAttribute("filenames");
        	 String modify=(String)session.getAttribute("modify");
        	 String[] file=filenames.split(";");
        	 String[] mod=modify.split(";;;");
        	 for(int i=0;i<file.length;i++){%>
        		 <p style="color: #000000; font-size: 18px;">修改文件:<%=file[i] %></p>
        		 <%
	         		String a=mod[i];
	        		 String[] content=a.split("\n");
	        		 System.out.println(content.length);
	        		 for(int j=0;j<content.length;j++){ 	 %>
	        		 <%  if(content[j].startsWith("+")){%>
	    	 			    <p style="color: #00FF7F; font-size: 16px;"><xmp style="color: #6F00D2; font-size: 16px;"><%=content[j] %></xmp></p>
	    		        <%}else if(content[j].startsWith("-")){%>
	    		        	<p style="color: #ffc0cb; font-size: 16px;"><xmp style="color: #EA0000; font-size: 16px;"><%=content[j] %></xmp></p>
	    		       <% }else{%>
	    		    	   <p style="color: #000000; font-size: 16px;"><xmp style="color:#000000; font-size: 16px;"><%=content[j] %></xmp></p>
	    		      <% }
	          	}
           }
         }
     %>
	</div>
	
</body>
</html>