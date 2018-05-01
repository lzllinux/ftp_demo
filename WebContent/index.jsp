<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>湖南工业计算机协会</title>
</head>
<body>
	<center>
		<h1>湖南工业计协</h1>
	</center>
	<center>
		<a href="<%=basePath%>/login.jsp" >上传</a>
	</center>
	<center>
		<a href="<%=basePath%>/listFile">下载</a>
	</center>

</body>
</html>