<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/resources/css/note/mailForm.css">
</head>
<body>

	<section class="container">
		<div class="inner">
			<h1>단체메일 발송</h1>
			<p>※반을 선택 후 제목과 메일 내용을 작성해주세요※</p>
			<div class="div_box">
				<form action="/note/sendMail.do?" method="post">
					<!-- 반 선택  -->
					<select name="mail_class" class="default-input option-menu" required="required">
						<c:forEach var="claz" items="${clazz }">

							<option value="${claz}">${claz }</option>
						</c:forEach>
					</select>
					<table>
						<tr>
							<td><input type="text" name="mail_title"
								placeholder="제목을 입력해주세요" class="default-input" required="required"></td>
						</tr>
						<tr>
							<td><textarea rows="10" cols="30" name="mail_body"
									placeholder="내용을 입력해주세요" class="default-input" required="required"></textarea></td>
						</tr>
					</table>
					<input type="submit" value="전송하기" class="btn blue float-right">
					<input type="button" value="취소" onclick="self.close();"
						class="btn dark float-right">
				</form>
			</div>
		</div>
	</section>

</body>
</html>