<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />

<link href='resources/fullcalendar/core/main.css' rel='stylesheet' />
<link href='resources/fullcalendar/daygrid/main.css' rel='stylesheet' />

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src='resources/fullcalendar/core/main.js'></script>
<script src='resources/fullcalendar/daygrid/main.js'></script>
<script src='resources/fullcalendar/interaction/main.js'></script>
<script src="https://unpkg.com/popper.js/dist/umd/popper.min.js"></script>
<script src="https://unpkg.com/tooltip.js/dist/umd/tooltip.min.js"></script> 

<!-- 캘린더 설정 -->
 <script>
		document.addEventListener('DOMContentLoaded', function () {
			console.log("캘린더!!!!!!");
	      var calendarEl = document.getElementById('calendar');

	      var calendar = new FullCalendar.Calendar(calendarEl, {
	          plugins: ['dayGrid'],
	          defaultView: 'dayGridMonth',
	          height : 800,
	          locale : 'ko',
	          displayEventTime : false,
	          eventSources : [
	        	  {
	        		  url : '/kingthejoy/menuList.do',
	        		  type : 'POST',
	        		  error : function(){
	        			  alert('menu load failure');
	        		  },
	        		  color : 'yellow'
	        	  }

	          ],
	          eventRender : function(info){
	        	  console.log(info);
	        	  console.log(info.el);
	        	  console.log(info.event.extendedProps.description);
	        	 	
	        	        
	          }	          
	      });
	      calendar.render();
	  });
		
</script>
<style type="text/css">

/* 배경 이미지 사이즈 */
#background img{
	z-index : -1;
	bottom: 0px;
	left: 0px;
	position : absolute;
	top : 430px;
}
/* 캘린더 */
/* h1 */
h1{
	text-align: center;
}
</style>
</head>
<body>

<section>

<!-- 배경 사진 -->
<div id="background">
	<img src="./resources/img/parentMain.jpg">
</div>

<h1><a href="rolling.do">rolling</a> </h1>
<h1><a href="chatgo.do">chat</a></h1>

<%@include file="../parent/parentChatbot.jsp" %>

<h1>오늘의 식단</h1>

	<!-- 공지사항 -->
	<div>공지사항 부분</div>
	<input type="button" value="선생님용식단페이지" onclick="location='menuteacher.do?'">
	<input type="button" value="학부모 알림장" onclick="location='note.do?'">
	<input type="button" value="단체메일" onclick="location='mailform.do?'">

	<div>
	<!-- 식단표 -->
		<div id='calendar' style="width: 800px; margin-left:350px;"></div>
		<div id='background'>
			<img alt="" src="./resources/img/parentMain.jpg">
		</div>
	</div>


</section>
<%@include file="../common/footer.jsp" %>
</body>

</html>