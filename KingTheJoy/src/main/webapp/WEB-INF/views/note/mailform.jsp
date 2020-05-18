<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>단체메일 보내기</h1>


<!-- 제목, 내용 -->

<form action="sendMail.do?" method="post">
<!-- 반 선택  -->
<select name="mail_class">
<c:forEach var="claz" items="${clazz }">
	<option value="${claz}">${claz }</option>
</c:forEach>
</select>
<table>
	<tr>
		<th>제목</th>
		<td><input type="text" name="mail_title"></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="30" name="mail_body"></textarea></td>
	</tr>
</table>
<input type="submit" value="전송하기">
<input type="button" value="취소" onclick="location='mailform.do?'">
</form>

</body>
</html>