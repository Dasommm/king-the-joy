<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/menu/menuDetail.css">

<script type="text/javascript">
   $(function(){
      $(".submitbtn").hover(function(){
         $(this).css("background","#4c6177");
      },function(){
         $(this).css("background","#2C3E50");
      });
   });
</script>
</head>
<body>
<div class="container">
  
  <h2>식단<small>Menu</small></h2>
  
  <form name="updateresform" action="/menu/menuUpdateDb.do" method="post">
    <input type="hidden" name="menu_seq" value="${menuSelectOne.menu_seq }"/>
    <div class="group">      
      <input type="text" name="title" value="${menuSelectOne.title }" required="required">
      <span class="highlight"></span>
      <span class="bar"></span>
      <label>식단구분</label>
    </div>
      
    <div class="group">      
      <input type="date" name="start" value="${menuSelectOne.start }" required="required">
      <span class="highlight"></span>
      <span class="bar"></span>
      <label style="top:-17px">날짜</label>
    </div>
    <div>
    	<textarea rows="10" cols="30" name="description" style="margin: 0px; width: 300px; height: 85px;" required="required">${menuSelectOne.description }</textarea>
    </div>
    
    <input type="submit" value="수정" class="submitbtn"/><br>
    <input type="button" onclick="self.close();" value="닫기" >
  </form>
  
</div>
</body>
</html>