<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />

<link href='resources/fullcalendar/core/main.css' rel='stylesheet' />
<link href='resources/fullcalendar/daygrid/main.css' rel='stylesheet' />

<script src='resources/fullcalendar/core/main.js'></script>
<script src='resources/fullcalendar/daygrid/main.js'></script>
<script src='resources/fullcalendar/interaction/main.js'></script>


 <script>
     document.addEventListener('DOMContentLoaded', function () {
         var calendarEl = document.getElementById('calendar');
   
         var calendar = new FullCalendar.Calendar(calendarEl, {
             plugins: ['dayGrid'],
             defaultView: 'dayGridMonth',
             height : 800,
             locale : 'ko',
             displayEventTime : false,
            eventMouseEnter: function (info) {
                 console.log(info);
                 tooltip = 
                 '<div class="tooltiptopicevent" style="width:auto; height:auto; background:#feb811; position:absolute; z-index:10001; padding:10px 10px 10px 10px ;  line-height: 200%;">' + info.event.extendedProps.description + '</br>' + '</div>';
   
                 $("body").append(tooltip);
                 $(this).mouseover(function (e) {
                     $(this).css('position','absolute');
                     $(this).css('z-index', 10000);
                     $('.tooltiptopicevent').fadeIn();
                 }).mousemove(function (e) {
                     $('.tooltiptopicevent').css('top', e.pageY + 10);
                     $('.tooltiptopicevent').css('left', e.pageX + 20);
                 });
             },
             eventMouseLeave: function (info) {
                 $('.tooltiptopicevent').remove();
   
             },
             events: [
                 {
                 title: 'Morning',
                 description: '1',
                 start: '2020-05-06T09:00:00',
                 end: '2020-05-06T10:00:00'
                 }, 
                 {
                 title: 'Snack',
                 description: '2',
                 start: '2020-05-06T11:00:00',
                 end: '2020-05-06T12:00:00'
                 }, {
                 title: 'Lunch',
                 description: '3',
                 start: '2020-05-06T13:00:00',
                 end: '2020-05-06T14:00:00'
                 }, {
                 title: 'Morning',
                 description: '1',
                 start: '2020-05-13T09:00:00',
                 end: '2020-05-13T10:00:00'
                 }, {
                 title: 'Snack',
                 description: '2',
                 start: '2020-05-13T11:00:00',
                 end: '2020-05-13T12:00:00'
                 }, {
                 title: 'Lunch',
                 description: '3',
                 start: '2020-05-13T13:00:00',
                 end: '2020-05-13T14:00:00'
                 }
             ]
         });
         
         console.log(event);
         calendar.render();
     });
    </script>
<style type="text/css">

/* 배경 이미지 사이즈 */
#background img{
   z-index : -1;
   bottom: 0px;
   left: 0px;
   top : 330px;
   opacity : 0.7;
   height:559px;
   width: 777px;
}

/* 캘린더 */
/* h1 */
h2{
	text-align: center;
	font-weight: bold;
}

#mainNotice{
 text-align: center;

}
</style>
</head>
<body>
<section>

<%@include file="../common/parentHeader.jsp" %>
<%@include file="../parent/parentChatbot.jsp" %>

   <!-- 공지사항 -->
   <div id="mainNotice">
   		<a>공지사항</a>
   </div>
	<h2>오늘의 식단</h2>


   <div>
   <!-- 식단표 -->
      <div id='calendar' style="width: 800px; height:480px; margin-left:350px;"></div>
      <div id='background'>
         <img alt="" src="./resources/img/parentMain.jpg">
      </div>
   </div>


</section>
<%@include file="../common/footer.jsp" %>

</body>

</html>