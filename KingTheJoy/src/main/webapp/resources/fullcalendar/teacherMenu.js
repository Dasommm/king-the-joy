/**
 * menuTeacher에서 보여주는 선생님용(식단 등록, 수정, 삭제 가능) 식단표
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
	        	  window.open("/menu/menuInsertForm.do?","menuInsert","width=560, height=700, left=100, top=50");
	          },
	          
	          eventRender : function(info){
	        	  console.log(info.el);
	        	  console.log(info.event.extendedProps.description);
	        	  
	        	  info.el.addEventListener('click', function(){
	        		  clickCnt++;
	        		if(clickCnt === 1){
	        			oneClickTimer = setTimeout(function(){
	        				clickCnt = 0;
	        				menu_seq = info.event.extendedProps.menu_seq;
	        				
	        				var openWin;
	        				
	        				var popUrl = "/menu/menuUpdate.do?menu_seq="+menu_seq;
	        				var popOption = "width=770, height=860, left=100, top=50";
	        				window.name="menuParentForm";
	        				openWin = window.open(popUrl,"menuUpdate",popOption);
	        			}, 400);
	        		}else if(clickCnt === 2){
	        			clearTimeout(oneClickTimer);
	        			clickCnt = 0;
	        			menu_seq = info.event.extendedProps.menu_seq;
	        			if(confirm("삭제하시겠습니까?")==true){
	        				$.ajax({
	        					url : "/menu/menuDelete.do",
	        					type: "POST",
	        					data : {"menu_seq": menu_seq},
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
	        		  url : '/menu/menuList.do',
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