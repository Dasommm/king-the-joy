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
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src='resources/fullcalendar/core/main.js'></script>
<script src='resources/fullcalendar/daygrid/main.js'></script>
<script src='resources/fullcalendar/interaction/main.js'></script>
<script src="https://unpkg.com/popper.js/dist/umd/popper.min.js"></script>
<script src="https://unpkg.com/tooltip.js/dist/umd/tooltip.min.js"></script>
<style>

  /*
  i wish this required CSS was better documented :(
  https://github.com/FezVrasta/popper.js/issues/674
  derived from this CSS on this page: https://popper.js.org/tooltip-examples.html
  */

  .popper,
  .tooltip {
    position: absolute;
    z-index: 9999;
    background: #FFC107;
    color: black;
    width: 150px;
    border-radius: 3px;
    box-shadow: 0 0 2px rgba(0,0,0,0.5);
    padding: 10px;
    text-align: center;
  }
  .style5 .tooltip {
    background: #1E252B;
    color: #FFFFFF;
    max-width: 200px;
    width: auto;
    font-size: .8rem;
    padding: .5em 1em;
  }
  .popper .popper__arrow,
  .tooltip .tooltip-arrow {
    width: 0;
    height: 0;
    border-style: solid;
    position: absolute;
    margin: 5px;
  }

  .tooltip .tooltip-arrow,
  .popper .popper__arrow {
    border-color: #FFC107;
  }
  .style5 .tooltip .tooltip-arrow {
    border-color: #1E252B;
  }
  .popper[x-placement^="top"],
  .tooltip[x-placement^="top"] {
    margin-bottom: 5px;
  }
  .popper[x-placement^="top"] .popper__arrow,
  .tooltip[x-placement^="top"] .tooltip-arrow {
    border-width: 5px 5px 0 5px;
    border-left-color: transparent;
    border-right-color: transparent;
    border-bottom-color: transparent;
    bottom: -5px;
    left: calc(50% - 5px);
    margin-top: 0;
    margin-bottom: 0;
  }
  .popper[x-placement^="bottom"],
  .tooltip[x-placement^="bottom"] {
    margin-top: 5px;
  }
  .tooltip[x-placement^="bottom"] .tooltip-arrow,
  .popper[x-placement^="bottom"] .popper__arrow {
    border-width: 0 5px 5px 5px;
    border-left-color: transparent;
    border-right-color: transparent;
    border-top-color: transparent;
    top: -5px;
    left: calc(50% - 5px);
    margin-top: 0;
    margin-bottom: 0;
  }
  .tooltip[x-placement^="right"],
  .popper[x-placement^="right"] {
    margin-left: 5px;
  }
  .popper[x-placement^="right"] .popper__arrow,
  .tooltip[x-placement^="right"] .tooltip-arrow {
    border-width: 5px 5px 5px 0;
    border-left-color: transparent;
    border-top-color: transparent;
    border-bottom-color: transparent;
    left: -5px;
    top: calc(50% - 5px);
    margin-left: 0;
    margin-right: 0;
  }
  .popper[x-placement^="left"],
  .tooltip[x-placement^="left"] {
    margin-right: 5px;
  }
  .popper[x-placement^="left"] .popper__arrow,
  .tooltip[x-placement^="left"] .tooltip-arrow {
    border-width: 5px 0 5px 5px;
    border-top-color: transparent;
    border-right-color: transparent;
    border-bottom-color: transparent;
    right: -5px;
    top: calc(50% - 5px);
    margin-left: 0;
    margin-right: 0;
  }

</style>


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
	          eventRender : function(info){
	        	  console.log(info.el);
	        	  console.log(info.event.extendedProps.description);
	        	  var tooltip = new Tooltip(info.el, {
	        		  title : info.event.extendedProps.description,
	        		  placement : 'top',
	        		  trigger : 'hover',
	        		  container : 'body'	        	  
	        	  });
	        	  
	          },
	          eventSources : [
	        	  {
	        		  url : '/kingthejoy/menuList.do',
	        		  type : 'POST',
	        		  error : function(){
	        			  alert('menu load failure');
	        		  },
	        		  color : 'yellow'
	        	  }

	          ]
	          	          
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
   top : 330px;
   opacity : 0.7;
   height:559px;
   width: 777px;
}

/* 캘린더 */
/* h1 */
h2{
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

<section>

<%@include file="../parent/parentChatbot.jsp" %>

<h1>오늘의 식단</h1>

	<!-- 공지사항 -->
	<div>공지사항 부분</div>
	<input type="button" value="선생님용식단페이지" onclick="location='menuteacher.do?'">

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