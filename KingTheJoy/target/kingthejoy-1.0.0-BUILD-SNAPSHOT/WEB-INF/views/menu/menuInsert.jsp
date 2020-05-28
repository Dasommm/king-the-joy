<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="resources/css/menu/menuDetail.css">

</head>
<body>

  <h2>식단 등록<small>Menu Insert</small></h2>

	<form action="menuInsertDb.do?" method="post">

		<div class="group">
			<input type="date" name="start" required="required"> 
			<span class="highlight"></span> 
			<span class="bar"></span> 
			<label style="top: -17px">날짜</label>
		</div>

		<div class="group">
			<select name="title" required="required">
				<option value="아침">아침</option>
				<option value="간식">간식</option>
				<option value="점심">점심</option>
			</select> 
			<span class="highlight"></span> 
			<span class="bar"></span> 
			<label style="top:-25px">식단구분</label>
		</div>
		<div>
			<textarea name="description" rows="10" cols="30" style="margin: 0px; width: 300px; height: 85px;" required="required"
			placeholder="메뉴를 입력해주세요"></textarea>
		</div>

		<input type="submit" value="등록" class="submitbtn"> 
		<input type="button"
			value="닫기" onclick="window.close()">
	</form>


</body>
</html>