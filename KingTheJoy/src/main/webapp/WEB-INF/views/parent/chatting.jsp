<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
	<table>
		<tr>
			<th>
				<textarea rows="5" cols="30"></textarea>
			</th>
		</tr>
		<tr>
			<td><input type="text" id="chatMsg"></td>
			<td><input type="text" id="targetUser"></td>
			<td><input type="button" value="전송" id="btnSend"></td>
		</tr>
	
	</table>
	<textarea rows="5" cols="30">
	</textarea>
	<input type="text" id="chatMsg">
	<input type="text" id="targetUser">
	<input type="button" value="전송" id="btnSend">
</div>



    <script type="text/javascript">
        var ws;
        var messages=document.getElementById("messages");
        
        function openSocket(){
            if(ws!==undefined && ws.readyState!==WebSocket.CLOSED){
                writeResponse("WebSocket is already opened.");
                return;
            }
            //웹소켓 객체 만드는 코드
            ws=new WebSocket("ws://localhost:8090/kingthejoy/echo.do");
            
            ws.onopen=function(event){
                if(event.data===undefined) return;
                
                writeResponse(event.data); 
            };
            ws.onmessage=function(event){
                writeResponse(event.data);
            };
            ws.onclose=function(event){
                writeResponse("채팅이 종료 되었습니다");
            }
        }
        
        function send(){
            var text=document.getElementById("messageinput").value+","+document.getElementById("sender").value;
            ws.send(text);
            text="";
        }
        
        function closeSocket(){
            ws.close();
        }
        
        function writeResponse(text){
            messages.innerHTML+="<br/>"+text;
        }
  </script>

</body>
</html>