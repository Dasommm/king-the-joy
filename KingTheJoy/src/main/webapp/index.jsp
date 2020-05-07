<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/resources/css/loading.css"/>" rel="stylesheet">

<script>
function window_onload(){
	setTimeout('go_url()',5000)
}

function go_url(){
	location.href="home.do"
}

</script>
<title>Insert title here</title>
</head>
<<<<<<< HEAD
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
=======
<body onload="javascript:window_onload()"> 
<div class="stand">
  <div class="ball"></div>
  <div class="ball"></div>
  <div class="ball"></div>
</div>
>>>>>>> 5fa469811700ae993eecbdc15b9c9505347db6b9
</body>
</html> 