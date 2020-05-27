<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/menu/scheduleForm.css">


<script type="text/javascript">
   $(function(){
      $(".submitbtn").hover(function(){
         $(this).css("background","#4c6177");
      },function(){
         $(this).css("background","#2C3E50");
      });
   });
   
   onload=function(){
	   var frm = document.getElementById("scheduleform");
	   if('red' == "${updateform.color}"){
		   $("input:radio[name='color']:radio[value='red']").prop('checked',true);
	   }else if("yellow" == "${updateform.color}"){
		   $("input:radio[name='color']:radio[value='yellow']").prop('checked',true);
	   }else if("green" == "${updateform.color}"){
		   $("input:radio[name='color']:radio[value='green']").prop('checked',true);
	   }else{
			   $("input:radio[name='color']:radio[value='blue']").prop('checked',true);

	   }
	 }
</script>

</head>
<body>
  
  <h2>행사 수정<small>Event Update</small></h2>
  
  <form id="scheduleform" action="/schedule/scheduleUpdateDb.do" method="post">
    <input type="hidden" name="schedule_seq" value="${updateform.schedule_seq }"/>
    <div class="group">
			<input type="date" name="start" value="${updateform.start }" required="required"> 
			<span class="highlight"></span> 
			<span class="bar"></span> 
			<label class="label-style" style="top: -17px">시작일</label>
		</div>
		
		<div class="group">
			<input type="date" name="end" value="${updateform.end }" required="required"> 
			<span class="highlight"></span> 
			<span class="bar"></span> 
			<label class="label-style" style="top: -17px">종료일</label>
		</div>

		<div class="group">
			<input type="radio" id="red" name="color" value="red" required="required">
			<label class="radio-label" for="red">Red</label>
			<input type="radio" id="yellow" name="color" value="yellow" required="required">
			<label class="radio-label" for="yellow">Yellow</label>
			<input type="radio" id="green" name="color" value="green" required="required">
			<label class="radio-label" for="green">Green</label>
			<input type="radio" id="blue" name="color" value="blue" required="required">
			<label class="radio-label" for="blue">Blue</label>

			<label class="label-style" style="top:-25px">컬러선택</label>
		</div>
		
		<div class="group">
			<input type="text" name="title" value="${updateform.title }" required="required"> 
			<span class="highlight"></span> 
			<span class="bar"></span> 
			<label class="label-style" style="top: -17px">제목</label>
		</div>
		
		<div>
			<textarea name="description" rows="10" cols="30" style="margin: 0px; width: 300px; height: 85px;" required="required"
			placeholder="일정을 입력해주세요">${updateform.description }</textarea>
		</div>

		<input type="submit" value="등록" class="submitbtn"> 
		<input type="button" value="닫기" onclick="window.close()">
  </form>
  

</body>
</html>