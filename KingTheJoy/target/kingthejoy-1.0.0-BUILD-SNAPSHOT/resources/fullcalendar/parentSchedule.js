/**
 * 부모용 행사일정 일정 뿌리기, 조회
 */

document.addEventListener('DOMContentLoaded', function () {
			console.log("캘린더!!!!!!");
	      var calendarEl = document.getElementById('calendar');

	      var calendar = new FullCalendar.Calendar(calendarEl, {
	          plugins: ['dayGrid'],
	          defaultView: 'dayGridMonth',
	          height : 800,
	          locale : 'ko',
	          displayEventTime : false,
	          eventClick : function(info){
	        	  console.log(info);
	        	  schedule_seq = info.event.extendedProps.schedule_seq;
	        	  var openWin;
	        	  var popUrl = "scheduleOne.do?schedule_seq="+schedule_seq;
	        	  var popOption = "width=770, height=860, left=100, top=50";
	        	  openWin = window.open(popUrl, "scheduleOne", popOption);
	          },
	          eventSources : [
	        	  {
	        		  url : '/kingthejoy/selectSchedule.do',
	        		  type : 'POST',
	        		  error : function(){
	        			  alert('schedule load failure');
	        		  }
	        	  }

	          ]	                 
	      });
	      calendar.render();
	  });


