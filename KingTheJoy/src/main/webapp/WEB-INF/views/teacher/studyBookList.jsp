<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/css/studyBookList2.css"/>">
</head>
<body>
<%@include file="../common/TeacherHeader.jsp" %>
<div class="container">
  <div class="simple-panel">
    <div class="row">
      <div class="col-xs-12">
        <div class="panel-head u-upper">
          <h4>올린 수업자료</h4>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <table class="table table-bordered">
          <thead>
            <tr style="background-color: #eee;">
              <th width="10%">자료 번호</th>
              <th width="25%">수업자료 이름</th>
              <th>수업자료 내용</th>
              <th width="10%" class="u-center">삭제여부</th>
            </tr>
          </thead>
          <tbody>
          	<c:forEach items="${studyBookList}" var="sbList">
            <tr>
              <td><p>${sbList.book_seq }</p></td>
              <td><p>${sbList.book_name}</p></td>
              <td><p>${sbList.book_content }</p></td>
              <td class="u-center"><p><a href="/study/studyBookDelete.do?book_seq=${sbList.book_seq }">삭제하기</a></p></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
</body>
</html>