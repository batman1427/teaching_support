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
	            String gid=String.valueOf((Integer)session.getAttribute("groupid"));
	%>
	<div class="message">教学支持系统</div>
	<div class="user">
		欢迎你:<%=user%>; <a style="color: #ff0000; font-size: 16px" href="sign_in">退出!</a>
	</div>
	<div id="nav">
		<ul>
					<li><a href="admin_group?">分组信息</a></li>
			<li><a href="admin_file?path=&branch=master&gid=<%=gid %>">项目结构</a></li>
			<li><a href="admin_codeRecord?hash=0&branch=master&gid=<%=gid %>">代码记录</a></li>
				<li><a href="admin_graphs?gid=<%=gid %>">相关图表</a></li>
				<li><a href="admin_teamAnalysis?gid=<%=gid %>">团队分析</a></li>
				<li><a href="admin_discussion?show=1&content=">交流讨论</a></li>
		</ul>
		<div style="clear: both"></div>
	</div>
	<div class="test">
	<%   String  te=(String)session.getAttribute("branches");
	     String[] branches=te.split(";");
	     for(int i=0;i<branches.length;i++){%>
	    	 <a href="admin_file?path=&branch=<%=branches[i]%>&gid=<%=gid %>" style="color: #27A9E3; font-size: 16px;"><%=branches[i]%>分支</a>
	    	 &nbsp;&nbsp;&nbsp;&nbsp;
	   <%  }
	
	%>
	     <br>
	<%   String branch=(String)session.getAttribute("branch");%>
	<%   String dir=(String)session.getAttribute("dir");
		 String result=(String)session.getAttribute("path");%>
	<% 	 String[] temp=dir.split("/");
	     if(!dir.equals("")){%>
	    	 <a href="admin_file?path=&branch=<%=branch%>&gid=<%=gid %>" style="color: #000000; font-size: 16px;text-decoration: none;font-weight:bold">分支<%=branch+":"+"_" %>根目录cseiii/</a>
	   <%   for(int k=0;k<temp.length;k++){%>
	         <%String path0="";
	         for(int j=0;j<=k;j++){
	        	 path0=path0+temp[j]+"/";
	         }
	         %>
	    	 <a href="admin_file?path=<%=path0%>&branch=<%=branch%>&gid=<%=gid %>" style="color: #000000; font-size: 16px;text-decoration: none;font-weight:bold"><%=temp[k]%>/</a>
	 <% }
	    }else{%>
	    	<a href="admin_file?path=&branch=<%=branch%>&gid=<%=gid %>" style="color: #000000; font-size: 16px;text-decoration: none;font-weight:bold">分支<%=branch+":"+"_" %>根目录cseiii/</a>
	 <% }%>
	 	<br>
	     <% if(!dir.contains(".")){
	        String[] path=result.split(";");
	        for(int i=0;i<path.length;i++){
	          if(!path[i].contains(".")){
	        %><img src="images/files.jpg">
	    	 <a href="admin_file?path=<%=dir+path[i]+"/"%>&branch=<%=branch%>&gid=<%=gid %>" style="color: #000000; font-size: 16px;text-decoration: none"><%=path[i]%></a>
	    	 <br>
	    	 <% 
	          }else{
	        	  String al=path[i].replace("+","*");
	          %>
	          <img src="images/file.jpg">
	    	 <a href="admin_file?path=<%=dir+al+"/"%>&branch=<%=branch%>&gid=<%=gid %>" style="color: #000000; font-size: 16px;text-decoration: none"><%=path[i]%></a>
	    	 <br> 
	          <%}
	    	 %>
	     <%}
	     }else{%>
	         <%
	         String a=(String)session.getAttribute("content");
	         String[] content=a.split("\n");
	         for(int i=0;i<content.length;i++){ %>
	    	 	<p style="color: #000000; font-size: 16px;"><xmp><%=content[i] %></xmp></p>
	    		
	        <%  }
	    }
	%>
	</div>
</body>
</html>