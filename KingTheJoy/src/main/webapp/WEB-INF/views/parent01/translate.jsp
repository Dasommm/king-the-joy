<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>translate</title>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#translate").click(function() {
			var objParams = {
				"lang" : $("#language").val(),
				"bef" : $("#before").val()
			}
			$.ajax({
				url : 'transResponse.do',
				data : objParams,
				type : 'POST',
				success : function(msg) {
					if (msg != 0) {
						jsonObj = JSON.parse(msg);
						var content = jsonObj['translated_text'];
						document.getElementById("after").innerHTML = content;
					}
				},
				error : function() {
					alert("잘못된 통신입니다.");
				}
			})
		})
	})
</script>
</head>
<body>



	<h1>번역하기</h1>
	<form>
		<select id="language">
			<optgroup label="선택">
				<option value="en">영어</option>
				<option value="cn">중국어</option>
				<option value="vi">베트남어</option>
				<option value="th">태국어</option>
			</optgroup>
		</select><br>


		<textarea rows="10" cols="50" name="before" id="before"></textarea>



		<input type="button" value="번역" id="translate">



		<textarea rows="10" cols="50" name="after" id="after"></textarea>
	</form>

</body>
</html>