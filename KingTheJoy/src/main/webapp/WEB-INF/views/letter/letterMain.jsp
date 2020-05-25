<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>쪽지 메인 테스트</h1>
	<button onclick="window.open('letterPage.do?currentpage=1&member_seq=1','쪽지함','width=430,height=520,location=no,status=no,scrollbars=yes');">쪽지함(관리자:admin)</button>
	<button onclick="window.open('letterPage.do?currentpage=1&member_seq=2','쪽지함','width=430,height=520,location=no,status=no,scrollbars=yes');">쪽지함(학부모:user)</button>(임시)
	<button onclick="window.open('letterSendPage.do?currentpage=1&member_seq=1','쪽지함','width=430,height=520,location=no,status=no,scrollbars=yes');">보낸쪽지함(관리자)</button>(임시)
	<br>member_seq = ${member_seq }
</body>
</html>