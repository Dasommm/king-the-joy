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
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/resist.css"/>">
    <script src="<c:url value="/resources/js/resist.js"/>"></script>
<title>회원가입창</title>
</head>
<body>
	    <div class="home-img"><img src="<c:url value="/resources/img/home.png"/>" alt="homeLogo"></div>
	    <form action = "memberJoin.do" method="post" role="form" id="usercheck" name="member">
	    <input type="hidden" value="${mem_role }">
        <div class="home-div col-8 row"> 
            <div class="image-center col-12"><img src="<c:url value="/resources/img/Logo.png"/>" class="img-responsive center-block"></div>
            <div class="col-6"> 
                <label for="id">아이디</label>
                <input type="text" class="form-control" id="mem_id" name="mem_id" placeholder="ID">
                <div class="eheck_font" id="id_check"></div>
                <label for="pw">비밀번호</label>
                <input type="password" class="form-control" id="mem_pw" name="mem_pw" placeholder="PASSWORD">
                <div class="eheck_font" id="pw_check"></div>
                <label for="pw2">비밀번호 확인</label>
                <input type="password" class="form-control" id="mem_pw2" name="mem_pw2" placeholder="Confirm Password">
                <div class="eheck_font" id="pw2_check"></div>
                <label for="mem_email">이메일 주소</label>
                <input type="email" class="form-control" id="mem_email" name="mem_email" placeholder="E-mail">
                <div class="eheck_font" id="email_check"></div>
            </div>
            <div class="col-6">
                <label for="mem_name">이름</label>
                <input type="text" class="form-control" id="mem_name" name="mem_name" placeholder="Name">
                <div class="eheck_font" id="name_check"></div>
                <div>주소</div>
                <input class="form-control" style="width: 40%; display: inline;" placeholder="우편번호" name="mem_oaddress" id="mem_oaddress" type="text" readonly="readonly" >
                <button type="button" class="btn btn-default" onclick="execPostCode();"><i class="fa fa-search"></i> 우편번호 찾기</button>
                <input class="form-control" style="top: 5px;" placeholder="도로명 주소" name="mem_address" id="mem_address" type="text" readonly="readonly" />
                <input class="form-control" placeholder="상세주소" name="mem_detailaddress" id="mem_detailaddress" type="text" />
                <br><label for="mem_phone">휴대폰 번호('-'없이 번호만 입력해주세요)</label>
                <input type="tel" class="form-control" id="mem_phone" name="mem_phone" placeholder="Phone Number">
                <div class="eheck_font" id="phone_check"></div>
            </div> 
            <div><button type="submit" class="btn btn-success">회원가입하기</button></div> 
    </div>
    </form>
</body>
</html>