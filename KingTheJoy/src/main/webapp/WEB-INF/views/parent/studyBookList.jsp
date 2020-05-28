<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="../common/parentHeader.jsp" %>
<link rel="stylesheet" href="<c:url value="/resources/css/studyBookList.css"/>">
</head>
<body>
   <div class="module cat-direct">
      <div class="grid-row">
         <h2 class="sec-title" style="text-align:center; margin-top:30px;">수업자료 목록</h2> 
      </div>
      <ul class="grid-row">
         <c:forEach items="${studyBookList}" var="sbList">
             <li class="grid-col">
                 <div class="col-inner">
                     <a href="studyBookOne.do?book_seq=${sbList.book_seq}" class="btn-complex link">
                        <span class="thumb">
                           <img src="<c:url value='/resources/img/ppt${sbList.book_folder_number}/ppt_image_5.png'/>"/>
                        </span>
                       <p class="fluid-txt txt bg-main1">
                           <span>${sbList.book_name }</span>
                        </p>
                     </a>
                 </div>
             </li>
             
          </c:forEach>
       </ul>
   </div>
</body>
</html>