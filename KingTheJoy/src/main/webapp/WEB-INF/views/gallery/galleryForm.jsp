<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>jpg 파일만 올려주세요. (5MB이하)</p>
	<form action="${pageContext.request.contextPath}/gallery/uploadFormAction.do" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFile" multiple="multiple">
		<button>Submit</button>
	</form>
</body>
</html>