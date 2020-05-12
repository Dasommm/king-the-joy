<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/rollingNotice.css"/>">
<script src="<c:url value="/resources/js/rollingNotice.js"/>"></script>
</head>
<body>
	<div id="notice">
		<ul class="rolling" id="notification">
		</ul>
	</div>
</body>
</html>
