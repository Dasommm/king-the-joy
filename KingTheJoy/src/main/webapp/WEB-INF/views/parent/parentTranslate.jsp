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
<style type="text/css">

#body{

margin-top: 150px;
}

footer{
position: absolute;
top: 850px;

}

#fixed{
		width: 100px;
		height: 300px;
		background-color: gold;
		position: fixed;
		right: 10px;
		top: 200px;
	}

</style>
</head>
<body>

	<section>
		<%@include file="../common/parentHeader.jsp"%>
	
		<div id="title" align="center"><b>번역</b></div>
		<div id="body" align="center">
			<form>
				<select id="language" class="language">
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
		</div>
		<div id="fixed">
		<a href="pillrequest.do">투약의뢰서</a>
		<a href="translate.do">번역</a>
		
		</div>
	</section>
	
	
	<footer> <%@include file="../common/footer.jsp"%> </footer>
</html>