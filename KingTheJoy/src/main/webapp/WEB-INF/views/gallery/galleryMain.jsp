<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
	height: 100%;
	overflow: hidden;
}
</style>
</head>
<body>
<jsp:include page="../common/TeacherHeader.jsp" />
	<h1>임시페이지 galleryMain</h1>
	<h2><a href="galleryUpload.do?school_seq=1">1반 앨범 등록</a></h2>
	<h2><a href="galleryForm.do?school_seq=1">form-file(normal)</a></h2>
	<h2><a href="galleryPage.do?school_seq=1">1반 앨범 보기</a></h2>
</body>
</html>