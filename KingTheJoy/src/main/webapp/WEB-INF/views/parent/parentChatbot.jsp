<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

		/*모달css*/

        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }
    
        /* Modal Content/Box */
        .modal_content {
            background-color: #fefefe;
            margin: 15% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 288px; /* Could be more or less, depending on screen size */                          
        	height: 436px;]
        }
        
        .closeButton{
        	display: block;
        	text-align: center;
        }
        
        



/* 챗봇 이미지 사이즈 */
#chatBot img {
   width: 130px;
   height: 130px;
   position: fixed;
   top: 80%;
   right: 10px;
}

/* 챗봇 답변 div */
.pNa {
   text-align: center;
   width: 300px;
   height: 100px;
   display: none;
   position: fixed;
   top: 600px;
   right: 130px;
   z-index: 3;
}

/* 챗봇 답변 div boder */
.boder {
	border-radius: 50px 50px 50px 50px;
}


</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">


	//챗봇 눌렀을 때 회원,비회원 선택시 출력
	$(function() {
		$('#chatBot').click(function() {
			if ($('.pNa').css('display') == "none") {
				$('.pNa').css('top', '600px');
				$('.pNa').hide();
				$('#question01').show('slow');
			} else {
				$('.pNa').hide();
			}
		});
	});

	//회원 비회원 선택 시 선택지들 show
	//-회원
	$(function() {
		$('#pNa01').click(function() {
			$('#question01').hide();
			$('.pNa').css('top', '550px');
			$('#question02').show('slow');
		});
	});

	

	//회원 선택지들 클릭 답변
	$(function() {
		$('#pNa03').click(function() {
			$('#question02').hide();
			$('.pNa').css('top', '630px');
			$('#answer02_01').show('slow');
		});
	});

	$(function() {
		$('#pNa04').click(function() {
			$('#question02').hide();
			$('.pNa').css('top', '630px');
			$('#answer02_02').show('slow');
		});
	});

	$(function() {
		$('#pNa05').click(function() {
			$('#question02').hide();
			$('.pNa').css('top', '630px');
			$('#answer02_03').show('slow');
		});
	});

	$(function() {
		$('#pNa06').click(function() {
			$('#question02').hide();
			$('.pNa').css('top', '630px');
			$('#answer02_04').show('slow');
		});
	});
	
	//채팅 연결
	$(function(){
		$('#answer02_01').click(function(){
			$('#answer02_01').hide();
			$('.modal').show();
			//window.open('/chat/chat.do','채팅','width=270,height=230,left=1100,top=400');
		});
	});
	
	//채팅 끄기
	$(function(){
		$('#chat_close').click(function(){
			$('.modal').hide();
		})
	})
	
	
</script>
</head>
<body>
   <!-- 챗봇 -->
   <div id="chatBot">
      <img src="/resources/img/chatIcon.png">
   </div>
   <div id="question01" class="pNa">
      <p id="pNa01" class="boder" style="background-color: #f5c2c2">도움이 필요하세요?</p>
   </div>
   <div id="question02" class="pNa">
      <p id="pNa03" class="boder" style="background-color: #f5f189">실시간 채팅 문의</p>
      <p id="pNa04" class="boder" style="background-color: #a8f58c">투약의뢰는 어떻게 하나요?</p>
      <p id="pNa06" class="boder" style="background-color: #ffe8ff">아이들의 사진은 어디서 보나요?</p>
   </div>

   <div id="answer02_01" class="pNa">
      <p id="ans01" class="boder" style="background-color: #d8f5f5">상담사 연결</p>
   </div>
   <div id="answer02_02" class="pNa">
      <p id="ans02" class="boder" style="background-color: #d8f5f5">상단 메뉴의 요청사항으로 들어가세요!</p>
   </div>
   <div id="answer02_04" class="pNa">
      <p id="ans04" class="boder" style="background-color: #d8f5f5">포토갤러리로 들어가시면 됩니다!</p>
   </div>


<div id="chatmodal" class="modal">
	<div class="modal_content">
	
		<%@ include file="../parent/chatting.jsp" %>
	<br>
	<div class="closeButton">
		<input type="button" value="닫기" id="chat_close" class="btn btn-secondary">
	</div>
	
	</div>
	
</div>



</body>
</html>