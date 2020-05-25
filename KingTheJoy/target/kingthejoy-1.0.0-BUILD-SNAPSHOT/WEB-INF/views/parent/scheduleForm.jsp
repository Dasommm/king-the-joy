<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"	href="resources/css/menu/menuDetail.css">
</head>
<body>

<body>
	<!-- 제목, 내용, 시작일, 종료일, 컬러 -->
  <h2>행사 등록<small>Event Insert</small></h2>

	<form action="scheduleInsertDb.do?" method="post">

		<div class="group">
			<input type="date" name="start" required="required"> 
			<span class="highlight"></span> 
			<span class="bar"></span> 
			<label style="top: -17px">시작일</label>
		</div>
		
		<div class="group">
			<input type="date" name="end" required="required"> 
			<span class="highlight"></span> 
			<span class="bar"></span> 
			<label style="top: -17px">종료일</label>
		</div>

		<div class="group">
			<input type="radio" id="red" name="color" value="red" required="required">
			<label for="red">Red</label>
			<input type="radio" id="yellow" name="color" value="yellow" required="required">
			<label for="yellow">Yellow</label>
			<input type="radio" id="green" name="color" value="green" required="required">
			<label for="green">Green</label>
			<input type="radio" id="blue" name="color" value="blue" required="required">
			<label for="blue">Blue</label>
			<span class="highlight"></span> 
			<span class="bar"></span> 
			<label style="top:-25px">컬러선택</label>
		</div>
		
		<div class="group">
			<input type="text" name="title" required="required"> 
			<span class="highlight"></span> 
			<span class="bar"></span> 
			<label style="top: -17px">제목</label>
		</div>
		
		<div>
			<textarea name="description" rows="10" cols="30" style="margin: 0px; width: 300px; height: 85px;" required="required"
			placeholder="일정을 입력해주세요"></textarea>
		</div>

		<input type="submit" value="등록" class="submitbtn"> 
		<input type="button" value="닫기" onclick="window.close()">
	</form>


</body>


</body>
</html>