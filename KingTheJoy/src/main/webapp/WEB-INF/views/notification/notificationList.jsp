<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link
	href="${pageContext.request.contextPath}/resources/css/notification.css"
	rel="stylesheet">
<script src="<c:url value="/resources/js/notification.js"/>"> </script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">
	$(window).on("load resize ", function () {
	    var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
	    $('.tbl-header').css({ 'padding-right': scrollWidth });
	}).resize();
		
	
	</script>
</head>

<body>
	<section>

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
		<div id="noticeTable" class="tbl-header">
			<table cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<td >제목</td>
						<td width="150">작성자</td>
						<td width="150">작성일</td>
					</tr>
				</thead>
			</table>
		</div>
		<div id="noticeTable" class="tbl-content">
			<table cellpadding="0" cellspacing="0" border="0">
				<tbody>
					<c:choose>
						<c:when test="${empty notificationList }">
							<tr>
								<td colspan="3" align="center">----- 작성된 공지사항이 없습니다 -----</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${notificationList}" var="dto">
								<tr>
									<td><strong><a
											href="notificationDetail.do?/notification/notification_seq=${dto.notification_seq}">${dto.notification_title}</a></strong>
									</td>
									<td width="150">${dto.notification_writer}</td>
									<td width="150">${dto.notification_date}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<c:choose>

				<c:when test="${memberDto.getMember_role() <3 }">
					<input type="button" value="공지사항 작성" onclick="location.href='/notification/notificationInsert.do'">
				</c:when>
				<c:when test="${memberDto.getMember_role() eq 3 }">
					<input type="button" value="공지사항 작성" onclick="location.href='/notification/notificationInsert.do'" style="display: none;">
				</c:when>
				
			</c:choose>
		</div>
	</section>
	<%@include file="../common/footer.jsp"%>

</body>