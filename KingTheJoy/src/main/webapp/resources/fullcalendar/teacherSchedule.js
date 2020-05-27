/**
 * 선생님용 schedule (조회, 입력, 삭제)
 */

document.addEventListener('DOMContentLoaded', function () {
			console.log("캘린더!!!!!!");
	      var calendarEl = document.getElementById('calendar');
	      var clickCnt = 0;
	      var menuPk = 0;	
	      var calendar = new FullCalendar.Calendar(calendarEl, {
	          plugins: ['dayGrid', 'interaction','timeGrid'],
	          defaultView: 'dayGridMonth',
	          height : 800,
	          locale : 'ko',
	          displayEventTime : false,
	          dateClick : function(){
	        	  window.open("/kingthejoy/scheduleInsertForm.do?","scheduleInsert","width=560, height=820, left=100, top=50");
	          },
	          
	          eventRender : function(info){
	        	  console.log(info.el);
	        	  console.log(info.event.extendedProps.description);
	        	  
	        	  info.el.addEventListener('click', function(){
	        		  clickCnt++;
	        		if(clickCnt === 1){
	        			oneClickTimer = setTimeout(function(){
	        				clickCnt = 0;
	        				schedule_seq = info.event.extendedProps.schedule_seq;
	        				
	        				var openWin;
	        				
	        				var popUrl = "scheduleUpdate.do?schedule_seq="+schedule_seq;
	        				var popOption = "width=560, height=820, left=100, top=50";
	        				window.name="scheduleUpdateForm";
	        				openWin = window.open(popUrl,"scheduleUpdate",popOption);
	        			}, 400);
	        		}else if(clickCnt === 2){
	        			clearTimeout(oneClickTimer);
	        			clickCnt = 0;
	        			schedule_seq = info.event.extendedProps.schedule_seq;
	        			if(confirm("삭제하시겠습니까?")==true){
	        				$.ajax({
	        					url : "scheduleDelete.do",
	        					type: "POST",
	        					data : {"schedule_seq": schedule_seq},
	        					success : function(data){
	        						location.reload();
	        					},
	        					error : function(){
	        						alert("통신실패");
	        					}
	        				})
	        			}
	        			
	        		}else{
	        			return false;
	        		}
	        		  
	        	  })
	        	  
	          },
	          eventSources : [
	        	  {
	        		  url : '/kingthejoy/scheduleSelect.do',
	        		  type : 'POST',
	        		  error : function(){
	        			  alert('menu load failure');
	        		  }
	        	  }

	          ]
	          	          
	      });
	      calendar.render();
	  });
