<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />

<link href='resources/fullcalendar/core/main.css' rel='stylesheet' />
<link href='resources/fullcalendar/daygrid/main.css' rel='stylesheet' />
<link href='resources/fullcalendar/timegrid/main.css' rel='stylesheet' />

<script src='resources/fullcalendar/core/main.js'></script>
<script src='resources/fullcalendar/daygrid/main.js'></script>
<script src='resources/fullcalendar/interaction/main.js'></script>
<script src='resources/fullcalendar/timegrid/main.js'></script>


<script>
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');

		var calendar = new FullCalendar.Calendar(calendarEl, {
			height : 500,
			locale : 'ko',
			plugins : [ 'dayGrid' ],
			eventClick : function(info) {
				var popupX = (document.body.offsetWidth / 2) - (200/2);
				var popupY = (window.screen.height / 2) - (300 / 2);
				var detail = window.open("", "", "status = no, width=200, height=100 left="+popupX+"top="+popupY);
				detail.document.write("<p>ddddeeeetttaaaaiiilll<p>");
				//alert('Event: ' + info.event.content);
			},
			defaultView : 'dayGridMonth',
			events : [ {
				title : 'Morning',
				start : '2020-05-06T09:00:00',
				end : '2020-05-06T10:00:00'
			}, {
				title : 'Snack',
				start : '2020-05-06T11:00:00',
				end : '2020-05-06T12:00:00'
			}, {
				title : 'Lunch',
				start : '2020-05-06T13:00:00',
				end : '2020-05-06T14:00:00'
			}]
		});

		calendar.render();
	});
</script>
</head>
<body>
<section>

	<!-- 공지사항 -->
	<div>공지사항 부분</div>

	<!-- 식단표 -->
	<div id='calendar' style="width: 700; left:600;"></div>
	
     <!--날씨-->
    <div>날씨 부분</div>

    <!--챗봇-->
    <div>챗봇 부분</div>

</section>

<%@include file="../common/footer.jsp" %>
</body>

</html>