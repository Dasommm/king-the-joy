<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style type="text/css">
	.form{
		margin: 20px;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<h2>쪽지보기</h2>
	<div class="form">
		<p type="보낸 사람:">
			<input name="member_id" value="${letterDto.letter_sender }" readonly="readonly" style="width: 155px;">
		</p>
		<p type="제목:">
			<input name="letter_title" value="${letterDto.letter_title }" readonly="readonly"></input>
		</p>
		<p type="내용:">
			<textarea name="letter_content" style="width: 380px; height: 200px; border-radius: 10px;" readonly="readonly">${letterDto.letter_content }</textarea>
		</p>
		<button type="button" onclick="location.href='letterPickedWrite.do?member_seq=${member_seq}&letter_sender=${letterDto.letter_sender }'">답장</button>
		<button type="button" onclick="location.href='letterDelOne.do?letter_seq=${letterDto.letter_seq }&member_seq=${member_seq }'">삭제</button>
		<button type="button" onclick="location.href='letterPage.do?currentpage=1&member_seq=${member_seq }'">뒤로가기</button>
	</div>
</body>
</html>