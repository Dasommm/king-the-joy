<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='resources/fullcalendar/core/main.css' rel='stylesheet' />
<link href='resources/fullcalendar/daygrid/main.css' rel='stylesheet' />
<link href='resources/fullcalendar/timegrid/main.css' rel='stylesheet' />

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src='resources/fullcalendar/core/main.js'></script>
<script src='resources/fullcalendar/daygrid/main.js'></script>
<script src='resources/fullcalendar/interaction/main.js'></script>
<script src='resources/fullcalendar/timegrid/main.js'></script>
<script src="resources/fullcalendar/teacherSchedule.js"></script>

<style type="text/css">
#subtitle {
  text-align: center;
  margin: 10px;
</style>
<title>Insert title here</title>
</head>
<body>


<section>

<%@include file="../common/TeacherHeader.jsp" %>

	<div>
	<!-- 사용방법 -->
	<p id="subtitle">
	<strong>※행사등록 페이지입니다. 날짜 클릭시 새로운 행사 등록, 이벤트 클릭시 상세내용 확인 및 수정, 더블클릭시 삭제가 가능합니다※</strong>
	</p>
	<!-- 식단표 -->
		<div id='calendar' style="width: 800px; margin-left:350px; margin-top: 20px; margin-bottom: 20px;"></div>
	</div>


</section>
<%@include file="../common/footer.jsp" %>


</body>
</html>