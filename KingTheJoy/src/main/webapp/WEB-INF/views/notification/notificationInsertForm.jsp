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
	<form action="notificationInsertRes.do" method="post">
		<div class="tbl-title">
		<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td><input type="text" name="notification_title" value="[${school_name}]" style="width: 100%"></td>
			</tr>
			<tr>
				<td><textarea rows="30" cols="200" name="notification_content" placeholder="내용을 입력해주세요"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="글 작성" > <input
					type="button" value="취소" onclick="location.href='/notification/notification.do'">
				</td>
			</tr>
		</table>
		</div>

	</form>
</body>
<%@include file="../common/footer.jsp"%>
</html>