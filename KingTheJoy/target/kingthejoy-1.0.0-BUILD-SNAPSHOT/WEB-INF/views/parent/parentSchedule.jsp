<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href='resources/fullcalendar/core/main.css' rel='stylesheet' />
<link href='resources/fullcalendar/daygrid/main.css' rel='stylesheet' />


<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src='resources/fullcalendar/core/main.js'></script>
<script src='resources/fullcalendar/daygrid/main.js'></script>
<script src='resources/fullcalendar/interaction/main.js'></script>

<!-- 캘린더 설정 -->
<script src="resources/fullcalendar/parentSchedule.js"></script>


<title>Insert title here</title>
</head>
<body>

<section>
<!-- 배경 사진 -->
<div id="background">
	<img src="./resources/img/parentMain.jpg">
</div>

<%@include file="../common/parentHeader.jsp" %>
<%@include file="../parent/parentChatbot.jsp" %>
<h1>행사일정!</h1>

<input type="button" value="선생님용 스케쥴" onclick="location.href='teacherSchedule.do?'">

	<!-- 식단표 -->
		<div id='calendar' style="width: 800px; margin-left:350px;"></div>

</section>

<%@include file="../common/footer.jsp" %>

</body>
</html>