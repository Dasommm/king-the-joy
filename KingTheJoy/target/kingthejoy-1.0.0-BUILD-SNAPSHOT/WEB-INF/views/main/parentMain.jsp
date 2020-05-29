<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />

<link href='resources/fullcalendar/core/main.css' rel='stylesheet' />
<link href='resources/fullcalendar/daygrid/main.css' rel='stylesheet' />
<link rel="stylesheet"
	href="<c:url value="/resources/css/rollingNotice.css"/>">


<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src='resources/fullcalendar/core/main.js'></script>
<script src='resources/fullcalendar/daygrid/main.js'></script>
<script src='resources/fullcalendar/interaction/main.js'></script>
<style type="text/css">
/* 캘린더 */
/* h1 */
h1 {
	text-align: center;
	font-weight: bold;
}

#noticeImg img{
	width: 30px;
	height: 30px;
	top: 212px;
	left:353px;
	position: absolute;
	}

#notice{
	height:50px;
	display:block;
	top: 0px;
	left: -180px;
	position: relative;
}

</style>

<!-- 캘린더 설정 -->
  <script src="resources/fullcalendar/parentMenu.js"></script>
</head>
<body>

<section>

<!-- 배경 사진 -->
<div id="background">
	<img src="./resources/img/parentMain.jpg">
</div>

<h1><a href="rolling.do">rolling</a> </h1>
<h1><a href="chatgo.do">chat</a></h1>


<%@include file="../common/parentHeader.jsp" %>
<%@include file="../parent/parentChatbot.jsp" %>


   <!-- 공지사항 -->
   <div id="noticeImg">
   		<img src="./resources/img/speaker.png">
   </div>
   <div id="notice">
			<ul class="rolling" id="notification">
				<li>TEXT</li>
			</ul>
			
	
 


<h1>오늘의 식단</h1>

	<!-- 공지사항 -->
	<div>공지사항 부분</div>
	<input type="button" value="선생님용식단페이지" onclick="location='menuteacher.do?'">
	<input type="button" value="학부모 알림장" onclick="openNote();">
	<input type="button" value="단체메일" onclick="location='mailform.do?'">
 
   <!-- 식단표 -->
      <div id='calendar' style="width: 800px; margin-left:350px;"></div>


</section>
<%@include file="../common/footer.jsp" %>

</body>

</html>