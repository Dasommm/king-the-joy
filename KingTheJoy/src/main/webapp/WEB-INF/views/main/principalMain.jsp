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
        width: 230px;
        height: 230px;
            
        background-color: #cccccc;
        border-radius: 50%;
     }
     .image-container img{
     	top: -12px;
     }
     .image-container span{
     	font-size: 1.5em; 
		font-weight: bold; 
		position: absolute; 
		
     }
     .prin_menu_top span{
     	top: 17.6em;
     }     
     .prin_menu_bot span{
     	top: 28.3em;
     }     
</style>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/PrincipalHeader.jsp" />
<div class="principalBody">
	<h2>원장 메인 페이지</h2>
	<div class="prin_menu_top">
	 	<div class="image-container">
	 		<a href="#">
	        <img src="${pageContext.request.contextPath}/resources/img/main/location.png"></a>
	        <span>원아위치</span>
	    </div>
	 	<div class="image-container">
	 		<a href="#">
	        <img src="${pageContext.request.contextPath}/resources/img/main/mykindergarten.png"></a>
	        <span>내 어린이집</span>
	    </div>
	 	<div class="image-container">
	 		<a href="#">
	        <img src="${pageContext.request.contextPath}/resources/img/main/board.png"></a>
	        <span>관리자 게시판</span>
	    </div>
	</div>
	<div class="prin_menu_bot">
	 	<div class="image-container">
	        <a href="#">
	        <img src="${pageContext.request.contextPath}/resources/img/main/letter.png" style="width: 10em; cursor: pointer;"
	        	onclick="window.open('${pageContext.request.contextPath}/letter/letterPage.do?currentpage=1&member_seq=${memberDto.member_seq }','쪽지함','width=430,height=520,location=no,status=no,scrollbars=yes')"></a>
	        <span>쪽지함</span>
	    </div>

	 	<div class="image-container">
	        <a href="${pageContext.request.contextPath}/principal/principalTeacherMgt.do">
	        <img src="${pageContext.request.contextPath}/resources/img/main/teacherMgt.png"></a>
	        <span>선생님관리</span>
	    </div>
	 	<div class="image-container">
	        <a href="#">
	        <img src="${pageContext.request.contextPath}/resources/img/main/childMgt.png"></a>
	        <span>원생관리</span>
	    </div>
	</div>  
    
</div>
</body>
</html>