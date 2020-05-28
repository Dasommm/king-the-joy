<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="../common/parentHeader.jsp" %>
<link rel="stylesheet" href="<c:url value="/resources/css/studyBookOne.css"/>">
</head> 
<body> 
<div class="container mt-3"> 
   <h2 style="text-align:center; margin-top:50px;">${studyBook.book_name }</h2>
<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- The slideshow -->
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="<c:url value="/resources/img/ppt${studyBook.book_folder_number}/ppt_image_1.png"/>">
    </div>
    <c:forEach var="i" begin="2" end="${studyBook.book_img_lastnumber }">
    <div class="carousel-item">
      <img src="<c:url value="/resources/img/ppt${studyBook.book_folder_number}/ppt_image_${i }.png"/>">
    </div>
    </c:forEach>
  </div>
  
  <!-- Left and right controls -->
  <a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#myCarousel" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>
</div>
</div>

</body>
</html>