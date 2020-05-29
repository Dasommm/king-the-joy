<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href='/resources/fullcalendar/core/main.css' rel='stylesheet' />
<link href='/resources/fullcalendar/daygrid/main.css' rel='stylesheet' />


<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src='/resources/fullcalendar/core/main.js'></script>
<script src='/resources/fullcalendar/daygrid/main.js'></script>
<script src='/resources/fullcalendar/interaction/main.js'></script>

<!-- 캘린더 설정 -->
<script src="/resources/fullcalendar/parentSchedule.js"></script>


<style type="text/css">
#subtitle {
  text-align: center;
  margin: 10px;
</style>
<title>Insert title here</title>
</head>
<body>

<section>
<!-- 배경 사진 -->
<div id="background">
	<img src="/resources/img/parentMain.jpg">
</div>

<%@include file="../common/parentHeader.jsp" %>
<%@include file="../parent/parentChatbot.jsp" %>

	<!-- 사용방법 -->
	<p id="subtitle">
	<strong>※행사 일정 페이지입니다. 상세내용 확인은 이벤트를 클릭해주세요※</strong>
	</p>
	<!-- 식단표 -->
		<div id='calendar' style="width: 800px; margin-left:350px;"></div>
		

</section>

<%@include file="../common/footer.jsp" %>

</body>
</html>