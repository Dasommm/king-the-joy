<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/css/menu/menuDetail.css">

</head>
<body>

<div class="container">
  
  <h2>식단<small>Menu</small></h2>
  
  <form name="updateresform" action="menuUpdateDb.do" method="post">
    <div class="group">      
      <input type="text" name="title" value="${menuClick.title }" readonly="readonly">
      <span class="highlight"></span>
      <span class="bar"></span>
      <label style="top:-17px">식단 구분</label>
    </div>
      
    <div class="group">      
      <input type="date" name="start" value="${menuClick.start }" readonly="readonly">
      <span class="highlight"></span>
      <span class="bar"></span>
      <label style="top:-17px">날짜</label>
    </div>
    <div>
    	<textarea rows="10" cols="30" name="description" style="margin: 0px; width: 300px; height: 85px;" readonly="readonly">${menuClick.description }</textarea>
    </div>
    
    <input type="button" onclick="self.close();" value="닫기"/>
  </form>
  
</div>



</body>

</html>