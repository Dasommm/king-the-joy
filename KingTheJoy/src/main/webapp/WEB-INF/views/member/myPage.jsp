<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/myPage.js"/>"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/myPage.css"/>">
<c:choose>
	<c:when test="${memberDto.getMember_role() == 3}">
		<%@include file="../common/parentHeader.jsp"%>
		<%@include file="../parent/parentChatbot.jsp"%>
	</c:when>
	<c:when test="${memberDto.getMember_role() == 2}">
		<%@include file="../common/TeacherHeader.jsp"%>
	</c:when>
	<c:when test="${memberDto.getMember_role() == 1}">
		<%@include file="../common/PrincipalHeader.jsp"%>
	</c:when>
</c:choose>

</head>
<body>
	<div class="container">
		<div class=".col-xs-4 .col-md-offset-2">
			<div class="panel panel-default panel-info Profile">
				<div class="panel-body">
					<div class="form-horizontal">
						<form action="/member/memberUpdate.do" method="POST">
							<div class="form-group">
								<label class="col-sm-2 control-label">아이디</label>
								<div class="col-sm-5">
									<input class="form-control" type="text" name="member_id"
										value="${sessionScope.memberDto.getMember_id()}" readonly>
								</div>
								<c:choose>
									<c:when
										test="${memberDto.getMember_role() == 1 || memberDto.getMember_role() == 2}">
										<div class="col-sm-6 right">
											<h2>유치원 정보</h2>
											<table class="myPageInfo"> 
													<tr>
														<td><a href="#"><img
																src="<c:url value="/resources/img/school.png"/>" /></a> <span><c:out
																	value="${school_name}"/></span></td>
													</tr> 
											</table>
											<h2>작성된 공지사항</h2>
											<c:forEach items="${notificationList }"
													var="notificationList">
													<table>
														<tr>
															<td><c:out
																	value="${notificationList.notification_title} " /></td>
															<td><input type="button" value="열람 요청 메일보내기"
																onclick="sendMail(${notificationList.notification_seq})"></td>
														</tr>
													</table>
												</c:forEach>
										</div>
									</c:when>
									<c:when test="${memberDto.getMember_role() == 3}">
										<div class="col-sm-6 right">
											<h2>자녀정보</h2>
											<table class="myPageInfo">
												<c:forEach items="${childrenList }" var="childrenList">
													<tr>
														<td><a href="#"><img
																src="<c:url value="/resources/img/lion.jpg"/>" /></a> <span><c:out
																	value="${childrenList.children_name } 어린이" /></span></td>
													</tr>
												</c:forEach>
											</table>
										</div>
										
									</c:when> 
								</c:choose>

							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">이름</label>
								<div class="col-sm-5">
									<input class="form-control" type="text" name="member_name"
										value="${sessionScope.memberDto.getMember_name()}" readonly>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">이메일</label>
								<div class="col-sm-5">
									<input class="form-control" type="text" name="member_email"
										value="${sessionScope.memberDto.getMember_email()}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">전화번호</label>
								<div class="col-sm-5">
									<input class="form-control" type="text" name="member_phone"
										value="${sessionScope.memberDto.getMember_phone()}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-1 control-label">주소</label>
								<button type="button" class="btn btn-primary"
									onclick="execPostCode();">
									<i class="fa fa-search"></i> 우편번호 찾기
								</button>
								<div class="col-sm-5">
									<input class="form-control" type="text" name="member_oaddr"
										value="${sessionScope.memberDto.getMember_oaddr()}"> <input
										class="form-control" type="text" name="member_addr"
										value="${sessionScope.memberDto.getMember_addr()}"> <input
										class="form-control" type="text" name="member_detailaddr"
										value="${sessionScope.memberDto.getMember_detailaddr()}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">회원 분류</label>
								<div class="col-sm-5">
									<c:choose>
										<c:when test="${sessionScope.memberDto.getMember_role() eq 0}">
											<input class="form-control" type="text" name="member_rodle"
												value="관리자" readonly>
										</c:when>
										<c:when test="${sessionScope.memberDto.getMember_role() eq 1}">
											<input class="form-control" type="text" name="member_rodle"
												value="원장님" readonly>
										</c:when>
										<c:when test="${sessionScope.memberDto.getMember_role() eq 2}">
											<input class="form-control" type="text" name="member_rodle"
												value="선생님" readonly>
										</c:when>
										<c:when test="${sessionScope.memberDto.getMember_role() eq 3}">
											<input class="form-control" type="text" name="member_rodle"
												value="학부모" readonly>
										</c:when>
									</c:choose>
								</div>

							</div>
							<c:choose>
								<c:when test="${memberDto.getMember_role() == 3}">
								<div id="paymentDiv">
													<%@ include file="../member/payment.jsp" %>
										</div>
								</c:when>
							</c:choose>
						
	


							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-primary">회원 수정</button>
									
								</div> 
							</div>
						</form>  
						
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>