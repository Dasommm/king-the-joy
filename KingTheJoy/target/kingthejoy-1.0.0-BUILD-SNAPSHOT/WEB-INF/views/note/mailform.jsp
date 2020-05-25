<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="resources/css/note/mailForm.css">
</head>
<body>

	<section class="container">
		<div class="inner">
			<h1>단체메일 보내기</h1>
			<div class="div_box">
				<form action="sendMail.do?" method="post">
					<!-- 반 선택  -->
					<select name="mail_class" class="default-input option-menu">
						<c:forEach var="claz" items="${clazz }">

							<option value="${claz}">${claz }</option>
						</c:forEach>
					</select>
					<table>
						<tr>
							<td><input type="text" name="mail_title"
								placeholder="제목을 입력해주세요" class="default-input"></td>
						</tr>
						<tr>
							<td><textarea rows="10" cols="30" name="mail_body"
									placeholder="내용을 입력해주세요" class="default-input"></textarea></td>
						</tr>
					</table>
					<input type="submit" value="전송하기" class="btn blue float-right">
					<input type="button" value="취소" onclick="location='mailform.do?'"
						class="btn dark float-right">
				</form>
			</div>
		</div>
	</section>

</body>
</html>