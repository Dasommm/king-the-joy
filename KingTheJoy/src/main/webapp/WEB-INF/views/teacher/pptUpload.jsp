<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="../common/TeacherHeader.jsp" %>
   <link rel="stylesheet" href="<c:url value="/resources/css/pptUpload.css"/>">
   <script src="<c:url value="/resources/js/pptUpload.js"/>"></script>
</head>
<body>

	<form action="/study/pptUpload.do" method="post"
		enctype="multipart/form-data">
            <fieldset class="scheduler-border">
                <legend class="scheduler-border">수업자료 등록</legend>
            <div class="form-group"> 
            <label>수업 제목</label>
            <input type="text" class="form-control" name="book_name">
            </div>
            <div class="form-group"> 
            <label>수업 내용</label>
            <textarea class="form-control"rows="5" name="book_content"></textarea>
            </div>
            <div class="form-group">
                <p id="file-list-x"></p>
                <p><a id="file-input-button-x" href="#" class="btn btn-success file-input-button" data-id="x">
                    <i class="fa fa-plus"></i>
                PPT 업로드
                </a>
                    <input id="file-input-x" class="file-input visually-hidden" type="file" name="file" data-id="x">
                    <input type="submit" value="등록"/>
                </p>
            </div>
    </fieldset>
	</form>
</body>
</html>