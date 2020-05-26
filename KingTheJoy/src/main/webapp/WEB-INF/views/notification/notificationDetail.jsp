<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="${pageContext.request.contextPath}/resources/css/notification.css"
	rel="stylesheet">
<script type="text/javascript">
</script>

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
<div class="tbl-title">
	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td style="font-size: 20px; text-align: center;">${notificationDto.notification_title}</td>
			<td width="150" style="font-size: 20px;">${notificationDto.notification_date}</td>
			<td width="150" style="font-size: 20px;">${notificationDto.notification_writer}</td>
		</tr>
		<tr>
			<td height="500" style="font-size: 15px; padding-left: 50px;">${notificationDto.notification_content}</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:right ;">
				<c:choose>
    				<c:when test="${notificationDto.notification_writer} eq ${memberDto.getMember_name()}">
        				<input type="button" value="수정" onclick="location.href='notificationUpdate.do?notification_seq=${notificationDto.notification_seq}'" class="btn striped-shadow dark">
        				<input type="button" value="삭제" onclick="location.href='notificationDelete.do?notification_seq=${notificationDto.notification_seq}'" class="btn striped-shadow dark"> 
						<input type="button" value="목록으로" onclick="location.href='notification.do'" class="btn striped-shadow dark">
    				</c:when>
    				<c:otherwise>
    					<input type="button" value="목록으로" onclick="location.href='notification.do'" class="btn striped-shadow dark">
    				</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>