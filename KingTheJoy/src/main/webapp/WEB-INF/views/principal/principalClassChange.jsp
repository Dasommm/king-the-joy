<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	변경할 반 이름: <input type="text" maxlength="20">
	<input type="button" value="변경" >
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$("input[type=button]").on("click", function() {
		send();
	});
	$("input[type=text]").keydown(function(key) {
		if (key.keyCode == 13) {
			send();
		}
	});
	function send() {
		var content = $("input[type=text]").val();
		$.ajax({
			url:"${pageContext.request.contextPath}/principal/principalClassChangeRes.do?member_seq="+${member_seq }+"&content="+content,
			type:'GET',
			success: function(result) {
				opener.parent.location.reload();
		   		setTimeout(function() {
					self.close();
				}, 1000);
			}
		});
		//location.href="${pageContext.request.contextPath}/principal/principalClassChangeRes.do?member_seq="+${member_seq }+"&content="+content;

	}
	


	
</script>	
</body>
</html>