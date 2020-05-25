/**
 * parentMain에서 띄워주는 부모용 식단페이지
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
	        	  menu_seq = info.event.extendedProps.menu_seq;
	        	  var openWin;
	        	  var popUrl = "menuSelectOne.do?menu_seq="+menu_seq;
	        	  var popOption = "width=770, height=860, left=100, top=50";
	        	  openWin = window.open(popUrl, "menuSelect", popOption);
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

function openNote(){
	var openWin;
	var popUrl = "note.do?";
	var popOption = "width=725px, height=800px, left=100, top=50";
	openWin = window.open(popUrl, "NoteForm", popOption);
}
