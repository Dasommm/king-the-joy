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
    <script src="<c:url value="/resources/js/snsMemberUpdate.js"/>"></script>
<title>회원가입창</title>
</head>
<body>
	    <div class="home-img"><img src="<c:url value="/resources/img/home.png"/>" alt="homeLogo"></div>
	    <form action = "snsMemberUpdate.do" method="post" role="form" id="usercheck" name="member">
	    <input type="hidden" name="member_role" value="${member_role }">
        <div class="home-div col-8 row">
            <div class="image-center col-12"><img src="<c:url value="/resources/img/Logo.png"/>" class="img-responsive center-block"></div>
            <div class="col-6"> 
            	<br><br><br>
            	<h2>${sessionScope.member_name} 님 추가 정보를 입력해주세요 </h2>
            </div>
            <div class="col-6">
                        <div>주소</div>
                <input class="form-control" style="width: 40%; display: inline;" placeholder="우편번호" name="member_oaddr" id="member_oaddr" type="text" readonly="readonly" >
                <button type="button" class="btn btn-default" onclick="execPostCode();"><i class="fa fa-search"></i> 우편번호 찾기</button>
                <input class="form-control" style="top: 5px;" placeholder="도로명 주소" name="member_addr" id="member_addr" type="text" readonly="readonly" />
                <input class="form-control" placeholder="상세주소" name="member_detailaddr" id="member_detailaddr" type="text" />
                
                <input type="hidden" class="form-control" id="member_id" name="member_id" placeholder="ID" value="${sessionScope.member_id }">
                <input type="hidden" class="form-control" id="member_pw" name="member_pw" value="****">
                <input type="hidden" class="form-control" id="member_email" name="member_email" value="${sessionScope.member_id }">
                
            <br><label for="mem_phone">휴대폰 번호('-'없이 번호만 입력해주세요)</label>
                <input type="tel" class="form-control" id="member_phone" name="member_phone" placeholder="Phone Number">
                <div class="eheck_font" id="phone_check"></div>
                <input type="hidden" class="form-control" id="member_name" name="member_name" value="${sessionScope.member_name }">
    </div>
     <div><button type="submit" class="btn btn-success">회원가입하기</button></div> 
            </div>
            
    </form>
</body>
</html>