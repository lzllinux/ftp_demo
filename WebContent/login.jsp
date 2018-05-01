<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
</head>
<body>
	<%
		//获取协议名称
		String scheme = request.getScheme();
		//获取服务地址
		String serverName = request.getServerName();
		//获取端口号
		int serverPort = request.getServerPort();
		//获取项目名称
		String contextPath = request.getContextPath();
		String basePath = scheme + "://" + serverName + ":" + Integer.toString(serverPort) + "/" + contextPath
				+ "/";
	%>
	<center>
		
		<form action="<%=basePath%>/Login" method="get">
			<p>
				用户名:<input type="text" name="user" pattern="[a-zA-Z]{3,12}" required="required" placeholder="输入三到12个英文"><span style="color:red"><%=request.getAttribute("errorMsg") %></span></input>
			</p>
			<p>
				密&nbsp码<input type="text" name="pass"></input>
			</p>
			<p>
				<input type="submit" value="登录"></input>
			</p>
		</form>
	</center>
</body>
</html>