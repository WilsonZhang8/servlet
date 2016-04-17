<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="myRequestServlet3" method="post" enctype="multipart/form-data">
		<input name="userName" type="text" value="张三"/>
		<input name="age" type="text" value="age"/><br/>
		<input name="myfile1" type="file" value="上传"/><br/>
		<input name="myfile2" type="file" value="上传"><br/>
		<input name="sub" type="submit" value="提交">
	</form>
</body>
</html>