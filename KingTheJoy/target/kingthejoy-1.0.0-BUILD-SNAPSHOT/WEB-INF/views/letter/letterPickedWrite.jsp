<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style type="text/css">
	form{
		margin: 20px;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<h2>쪽지쓰기</h2>
	<form class="form" action="letterWriteRes.do" method="post">
		<input type="hidden" name="member_seq" value="${member_seq }" />
		<p type="받는사람:">
			<input name="member_id" value="${letter_sender }" readonly="readonly" style="width: 155px;">
		</p>
		<p type="제목:">
			<input name="letter_title" placeholder="제목(title)"></input>
		</p>
		<p type="내용:">
			<textarea name="letter_content" placeholder="내용(content)" style="width: 380px; height: 200px; border-radius: 10px;"></textarea>
		</p>
		<button type="submit">보내기(send)</button>
	</form>
</body>
</html>