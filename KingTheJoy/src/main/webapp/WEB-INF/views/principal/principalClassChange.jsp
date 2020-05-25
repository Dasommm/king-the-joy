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
		location.href="principalClassChangeRes.do?member_seq="+${member_seq }+"&content="+content;
		opener.parent.location.reload();
		//opener.parent.location='principalTeacherMgt.do';
		//window.opener.parent.location.href='principalTeacherMgt.do';
		//window.opener.document.location.href=window.opener.document.URL;
		//opener.location.replace("principalTeacherMgt.do");
		//window.opener = self;
   		setTimeout(function() {
			self.close();
			//window.close();
		}, 1000);
	}
	


	
</script>	
</body>
</html>