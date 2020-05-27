<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/menu/menuDetail.css">

</head>
<body>

<div class="container">
  
  <h2>행사<small>Event</small></h2>
  <div class=groupbox style="margin-left: 17%;">
    <div class="group">      
      <input type="text" name="title" value="${scheduleOne.title }" readonly="readonly">
      <span class="highlight"></span>
      <span class="bar"></span>
      <label style="top:-17px">제목</label>
    </div>
    <div class="group">      
      <input type="text" name="title" value="${scheduleOne.start }" readonly="readonly">
      <span class="highlight"></span>
      <span class="bar"></span>
      <label style="top:-17px">시작일</label>
    </div>
    <div class="group">      
      <input type="text" name="title" value="${scheduleOne.end }" readonly="readonly">
      <span class="highlight"></span>
      <span class="bar"></span>
      <label style="top:-17px">종료일</label>
    </div>

    <div>
    	<textarea rows="10" cols="30" name="description" style="margin: 0px; width: 300px; height: 85px;" readonly="readonly">${scheduleOne.description }</textarea>
    </div>
    
    <input type="button" onclick="self.close();" value="닫기"/>
  </div>
</div>


</body>
</html>