<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	.principalBody{
		margin: 4.5em 10em;
	}
	.prin_menu_top, .prin_menu_bot{
		margin: 25px;
		/*background-color: #feeeef; */
		display: flex;
		justify-content: space-evenly;
	}
	.principalBody img{
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
        width: 250px;
        height: 250px;
            
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
     .prin_menu_top span{
     	top: 18em;
     }     
     .prin_menu_bot span{
     	top: 29.6em;
     }     
</style>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/PrincipalHeader.jsp" />
<div class="principalBody">
	<h1>원장 메인 페이지</h1>
	<div class="prin_menu_top">
	 	<div class="image-container">
	        <img src="/kingthejoy/resources/img/main/위치확인.png">
	        <span>원아위치</span>
	    </div>
	 	<div class="image-container">
	        <img src="/kingthejoy/resources/img/main/내어린이집.png">
	        <span>내 어린이집</span>
	    </div>
	 	<div class="image-container">
	        <img src="/kingthejoy/resources/img/main/게시판.png">
	        <span>관리자 게시판</span>
	    </div>
	 	<div class="image-container">
	        <img src="/kingthejoy/resources/img/main/결제관리.png" style="width: 28em;">
	        <span>결제관리</span>
	    </div>
	</div>
	<div class="prin_menu_bot">
	 	<div class="image-container">
	        <img src="/kingthejoy/resources/img/main/쪽지.png" style="width: 10em;">
	        <span>쪽지함</span>
	    </div>
	 	<div class="image-container">
	        <img src="/kingthejoy/resources/img/main/채팅.png" style="width: 10em;">
	        <span>채팅</span>
	    </div>
	 	<div class="image-container">
	        <a href="principalTeacherMgt.do"><img src="/kingthejoy/resources/img/main/선생님관리.png"></a>
	        <span>선생님관리</span>
	    </div>
	 	<div class="image-container">
	        <img src="/kingthejoy/resources/img/main/원아관리.png">
	        <span>원생관리</span>
	    </div>
	</div>
    
    	<button onclick="#">내 어린이집</button>
		<button onclick="#">관리자 게시판</button>
		<button onclick="#">결제 관리</button>
		<button onclick="#">쪽지함</button>
		<button onclick="#">채팅</button>
		<button onclick="'">선생님 관리</button>
		<button onclick="#">원생관리</button>
    
</div>
</body>
</html>