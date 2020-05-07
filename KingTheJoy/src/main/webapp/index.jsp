<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	
	<link href="/resources/css/common.css" rel="stylesheet">
	<link href="/resources/css/home.css" rel="stylesheet">
	
<title>Insert title here</title>
</head>
<body> 
	<div class="row">
		<div class="home-img"><img src="${pageContext.request.contextPath}/resources/img/home.png" alt="homeLogo"></div>
		<div class="home-div col-4">
			<img src="${pageContext.request.contextPath}/resources/img/Logo.png" class="img-responsive center-block">
			<h1><a href="notification.do">Notification</a></h1>
			<div class="col-12 id-textbox">
				아이디 
				<input type="text" placeholder="아이디를 입력해주세요">
			</div>
			
			<div class="col-12 text pass-textbox">
				비밀번호
				<input type="password" placeholder="비밀번호를 입력해주세요">
			</div>
			
			<div class="login-button col-12">
				<button type="button" class="btn btn-info btn-md">로그인</button>
				<button type="button" class="btn btn-info btn-md reset-button" onclick="location='selectResistForm.do'">회원가입</button>
			</div>
			<div class="col-12">
				<img src="<c:url value="/resources/img/kakaoNaver.png"/>">
			</div>
			
			<div class="col-12">
				<button type="button" class="btn btn-info btn-md search-button">아이디/비밀번호 찾기</button>
			</div> 
		</div>
	</div>
</body>
</html> 