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
</head>
<body>
	<form action="notificationInsertRes.do" method="post">
		<div class="tbl-title">
		<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td>제목</td>
				<td><input type="text" name="notification_title" value="[${school_name}]"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" cols="30" name="notification_content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="글 작성" class="btn striped-shadow dark"> <input
					type="button" value="취소" onclick="location.href='notification.do'" class="btn striped-shadow dark">
				</td>
			</tr>
		</table>
		</div>

	</form>
</body>
</html>