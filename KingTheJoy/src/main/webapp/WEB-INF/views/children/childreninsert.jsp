<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
fieldset {
	margin-left: auto;
	margin-right: auto;
	width: 500px;
}
</style>
</head>


<body>


	<form action="childreninsertres.do" method="post">
		<fieldset>
			<legend>원아등록</legend>
			<input type="hidden" name="school_seq" value=""> 이름 : <input
				type="text" name="children_name" placeholder="이름을 등록해주세요"><br>
			사진 : <input type="file" id="childimg" name="file">
			<div class="select_img">
				<img src="" />
			</div>
			<script type="text/javascript"
				src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
			<script type="text/javascript">
				$("#childimg").change(
						function() {
							if (this.files && this.files[0]) {
								var reader = new FileReader;
								reader.onload = function(data) {
									$(".select_img img").attr("src",
											data.target.result).width(200);
								}
								reader.readAsDataURL(this.files[0]);
							}
						});
			</script>
			<input type="submit" value="등록">

		</fieldset>
	</form>

</body>
</html>