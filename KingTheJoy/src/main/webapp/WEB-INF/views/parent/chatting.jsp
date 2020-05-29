<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

<style type="text/css">

table{
        	font-family: 'Do Hyeon', sans-serif;
        }
targetUser{
	background-color: #FCF3CF;
}


</style>

</head>
<body>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

	

	<table>
		<tr>
			<th>
				<textarea rows="10" cols="30" id="msgArea" readonly="readonly" style="resize: none;"></textarea>
			</th>
		</tr>
		<tr>
			<c:choose>
				<c:when test="${sessionScope.memberDto.member_role == 3}">
					<td colspan="2" style="text-align: left;" >받는 사람 :
						<select id="targetUser" class="btn dropdown-toggle" data-toggle="dropdown">
							<option>teac11</option>
							<option>teac12</option>
						</select>
					</td>
				</c:when>
				<c:otherwise>
					<td colspan="2" style="text-align: right;">받는 사람:<input type="text" id="targetUser"/></td>
				</c:otherwise>
			</c:choose>	
		</tr>
		<tr>
			<td colspan="2" style="text-align: right;">
				<input type="text" id="chatMsg"  style="height: 35px; width: 180px;">
				<input type="button" class="btn btn-primary" value="전송" id="btnSend">
			</td>
		</tr>
	</table>




    <script type="text/javascript">
    
       
        var ws;
        var userid="${sessionScope.memberDto.member_id }";
        
        function connect(){
             
            //웹소켓 객체 만드는 코드
            ws = new WebSocket("ws://localhost:8090/chatting.do");
            
            ws.onopen=function(){
            	console.log('연결 생성');
            	register();
            };
            
            ws.onmessage=function(e){
            	console.log('메세지 받음');
            	var data = e.data;
            	
            	addMsg(data);
            };
            
            ws.onclose=function(){
            	console.log('연결 끊김');
            };
        }
        
        function addMsg(msg){
            var chat = $("#msgArea").val();
            chat = chat +"\n"+ msg;
            $("#msgArea").val(chat);
        }
        
        function register(){
            var msg = {
            		type : "register",
            		userid: "${sessionScope.memberDto.member_id }"
            };
        	//alert("register: "+JSON.stringify(msg))
            ws.send(JSON.stringify(msg));
        }
        
        function sendMsg(){
        	var msg={
        			type : "chat",
        			userid: "${sessionScope.memberDto.member_id }",
        			target : $("#targetUser").val(),
        			message : $("#chatMsg").val()
        	};
        	ws.send(JSON.stringify(msg));
        };
        
        $(function(){
        	connect();
        	$('#btnSend').on("click", function(){
        		var chat = $("#msgArea").val();
        	chat = chat +"\n"+userid+": "+$("#chatMsg").val();
        	$("#msgArea").val(chat);
        	sendMsg();
        	$("#chatMsg").val("");
        	$("#msgArea").animate({scrollTop: $('#msgArea')[0].scrollHeight }, 1000);
        	    
        })
	});
  </script>

</body>
</html>