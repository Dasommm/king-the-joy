<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	.teacherBody{
		margin: 4.5em 5em;
	}
	.tea_menu_top, .tea_menu_bot{
		/*background-color: #feeeef; */
		display: flex;
		justify-content: space-evenly;
	}
	.tea_menu_top{
	    margin: 1em 5em;
	}
	.tea_menu_bot{
     	margin: 2em 14em;
    } 
	.teacherBody img{
		position: relative;
		z-index: 1;
		width: 12.5em;
		left: 0em;
		top: 0em;
		text-align: center;
	}
	.image-container{
        overflow: hidden;
        display: flex;
        align-items: center;
        justify-content: center;
        width: 230px;
        height: 230px;
            
        background-color: #cccccc;
        border-radius: 50%;
     }
     .image-container img{
     	top: -20px;
     }
     .image-container span{
     	font-size: 1.5em; 
		font-weight: bold; 
		position: absolute; 
		
     }
     .tea_menu_top span{
     	top: 16.8em;
     }     
     .tea_menu_bot span{
     	top: 27.9em;
     }  
     h2{
     	margin-left: 5em;
     }   
</style>
<title>Insert title here</title>
</head>
<body>

<a href="childrenlist.do?page=1">childlist</a><br>
<a href="pillList.do?page=1">pillList</a><br>



<jsp:include page="../common/TeacherHeader.jsp" />
<div class="teacherBody">
	<h2>선생 메인 페이지</h2>
	<div class="tea_menu_top">
	 	<div class="image-container">
	        <img src="${pageContext.request.contextPath}/resources/img/main/abc.png" style="width: 9em;">
	        <span>수업자료 등록</span>
	    </div>
	 	<div class="image-container">
	        <img src="${pageContext.request.contextPath}/resources/img/main/location.png">
	        <span>원아 위치 확인</span>
	    </div>
	 	<div class="image-container">
	        <img src="${pageContext.request.contextPath}/resources/img/main/childMgt.png">
	        <span>원아관리</span>
	    </div>
	 	<div class="image-container">
	        <img src="${pageContext.request.contextPath}/resources/img/main/gallery.png" style="width: 15em;"
	        	onclick="location.href='galleryUpload.do?school_seq=${memberDto.school_seq }'">
	        <span>갤러리 등록</span>
	    </div>
	 	<div class="image-container">
	        <img src="${pageContext.request.contextPath}/resources/img/main/menu.png" style="width: 9em;"
	        	onclick="location.href='/menu/menuteacher.do?'">
	        <span>식단표 작성</span>
	    </div>
	</div>
	<div class="tea_menu_bot">
	 	<div class="image-container">
	        <img src="${pageContext.request.contextPath}/resources/img/main/letter.png" style="width: 10em; cursor: pointer;"
	        	onclick="window.open('letterPage.do?currentpage=1&member_seq=${memberDto.member_seq }','쪽지함','width=430,height=520,location=no,status=no,scrollbars=yes')">
	        <span>쪽지함</span>
	    </div>
	 	<div class="image-container">
	        <img src="${pageContext.request.contextPath}/resources/img/main/chat.png" style="width: 10em;">
	        <span>채팅</span>
	    </div>
	 	<div class="image-container">
	        <img src="${pageContext.request.contextPath}/resources/img/main/check.png" style="width: 8em;">
	        <span>요청사항 확인</span>
	    </div>
	 	<div class="image-container">
	        <img src="${pageContext.request.contextPath}/resources/img/main/calendar.png" style="width: 8em;"
	        	onclick="location.href='/schedule/teacherSchedule.do?'">
	        <span>행사일정</span>
	    </div>
	</div>  
    
</div>
</body>

</html>