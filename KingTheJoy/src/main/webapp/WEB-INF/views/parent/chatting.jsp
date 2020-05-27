<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<div>
	<table border="1">
		<tr>
			<th>
				<textarea rows="5" cols="30" id="msgArea"></textarea>
			</th>
		</tr>
		<tr>
			<td>받는 사람 : <input type="text" id="targetUser"></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: right;">
			<input type="text" id="chatMsg">
			<input type="button" value="전송" id="btnSend">
			</td>
		</tr>
	</table>
</div>



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
        	alert("register: "+JSON.stringify(msg))
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
        })
	});
  </script>

</body>
</html>