<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/header.css"/>">
<style type="text/css">
   .navbar-right .dropdown-menu {
      right: 0;
      left: auto;
   }
   .dropdown-menu{
      top: auto;
      padding: .5rem .5rem;
      margin: 10px 10px;
   }
   .navbar-brand{
      margin-right: 10rem;
   }
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <nav class="navbar navbar-expand-sm bg-white navbar-white">
        <!-- Brand -->
        <a class="navbar-brand header-logo" href="/member/roleCheck.do">
        <img src="${pageContext.request.contextPath}/resources/img/Logo.png" style="width: 150px;"></a>
      
        <!-- Links -->
        <ul class="navbar-nav">
            <li class="nav-item">
               <a class="nav-link" href="/teacher/teacher.do">선생 메인</a>
            </li>
            <li class="nav-item">
               <a class="nav-link" href="/notification/notification.do">공지사항</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/member/childrenMap.do">원아 위치</a>
            </li>
          <li class="nav-item">
               	<a class="nav-link" href="/children/childrenlist.do?page=1">원아 관리</a>
          </li>
          <li class="nav-item">
               <a class="nav-link" href="/study/studyBookForm.do">수업자료 등록</a>
          </li>

          <!-- Dropdown -->
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
              기타
            </a>
            <div class="dropdown-menu">
              <a class="dropdown-item" href="/pill/pillList.do?page=1">요청사항 확인(투약)</a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/gallery/galleryPage.do?school_seq=${memberDto.school_seq }">갤러리</a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/gallery/galleryUpload.do?school_seq=${memberDto.school_seq }">갤러리 등록</a>
              <a class="dropdown-item" onclick="window.open('${pageContext.request.contextPath}/letter/letterPage.do?currentpage=1&member_seq=${memberDto.member_seq }','쪽지함','width=430,height=520,location=no,status=no,scrollbars=yes')">쪽지함</a>
            </div>
          </li>
          </ul>
         <div class="navbar-right">
             <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
                메뉴버튼
             <span class="caret"></span>
             </button>
             <ul class="dropdown-menu">
                 <li><a class="dropdown-item" href="/member/myPage.do">마이페이지</a></li>
                 <li><a class="dropdown-item" href="/member/logout.do">로그아웃</a></li>
             </ul>
         </div>
      </nav>
<script type="text/javascript">

   $('.dropdown-toggle').dropdown();

</script>
</body>
</html>