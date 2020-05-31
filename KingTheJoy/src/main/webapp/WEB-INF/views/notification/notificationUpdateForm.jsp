<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="${pageContext.request.contextPath}/resources/css/notification.css"
	rel="stylesheet">
	<%@include file="../common/PrincipalHeader.jsp"%>
</head>
<body>
	<form action="notificationUpdateRes.do" method="post" id="update">
		<input type="hidden" name="notification_seq" value="${notificationDto.notification_seq}">
		<div class="tbl-title">
	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td><input type="text" name="notification_title" value="${notificationDto.notification_title}" style="width: 100%"></td> 
		</tr>
		<tr>
			<td><textarea rows="30" cols="200" name="notification_content" form="update" >${notificationDto.notification_content}</textarea>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="수정하기">
				<input type="button" value="돌아가기" onclick="location.href='/notification/notificationDetail.do?notification_seq=${notificationDto.notification_seq}'" >
			</td>
		</tr>
	</table>
	</div>
	</form>
</body>
<%@include file="../common/footer.jsp"%>
</html>