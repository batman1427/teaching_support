<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.sql.*"%>
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
	<div class="selfstat_table" style="float:left">
		<table border="1" bgcolor="ffffff">     
	 	<tr>    
	 	<th>学号</th>    
	 	<th>类型</th>    
	 	<th>周期</th>    
	 	<th>提交时间</th>     
	 	<th>文件名</th>       
	 	</tr>    
		<%     
		//驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		// URL指向要访问的数据库名scutcs
		String url = "jdbc:mysql://127.0.0.1:3306/教学支持系统";
   		String us = "xwz"; 
		// MySQL配置时的密码
		String password = "123456";
 		// 加载驱动程序
 		Class.forName(driver);
		// 连续数据库
 		Connection conn = DriverManager.getConnection(url, us, password);  
		//学号
		String sid=(String)session.getAttribute("studentid");
		int intPageSize;    
		int intRowCount;    
		int intPageCount;    
		int intPage;    
		String strPage;    
		int i;     
		intPageSize = 13;     
		strPage = request.getParameter("page");    
		if(strPage==null){       
			intPage =1;     
		}else{      
			intPage = java.lang.Integer.parseInt(strPage);    
			if(intPage<1){
				intPage = 1;      
			}
		}       
		Statement  stmt = conn.createStatement();
		String sql = "select * from record where sid="+" "+sid+" "+" order by time desc";    
		ResultSet rs=stmt.executeQuery(sql);    
		rs.last(); 
		intRowCount = rs.getRow();     
		intPageCount = (intRowCount+intPageSize-1)/intPageSize;     
		if(intPage>intPageCount){      
			intPage =intPageCount; 
		}
		if(intPageCount>0){       
			rs.absolute((intPage-1)*intPageSize+1);        
			i = 0;
			while(i<intPageSize && !rs.isAfterLast()){%>   
		<tr>    
		<td width=100><%=rs.getString("sid")%></td>    
		<td width=200><%=rs.getString("type")%></td>    
		<td width=80><%=rs.getString("iteration")%></td>    
		<td width=250><%=rs.getString("time")%></td>    
		<td width=200><%=rs.getString("filename")%></td>    
		</tr>   
		<%    
			rs.next();    
			i++;    
			}    
		}%>   
		</table>   
		<div align="center">   
		第<%=intPage%>页 共<%=intPageCount%>页 
		<%
		if(intPage>1){    %>    
		<a href="selfstat?page=<%=intPage-1%>">上一页</a>  
		<%     }    
		if( intPage<intPageCount){    %>    
    	<a href="selfstat?page=<%=intPage+1%>">下一页</a> 
    	 
		<%     } 
		rs.close();    
		stmt.close();    
		conn.close();     %>  
		</div> 
	</div>
	<div class="selfstat_chart" style="float:left">
	<img src="images/<%=(String)session.getAttribute("studentid") %>selfbar.jpg">
	</div>
</div>
</body>
</html>