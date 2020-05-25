<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">
function selChange(){
	var sel = document.getElementById('cntPerPage').value;
	location.href="list.do?nowPage=${paging.nowPage}&cntPerPage="+sel;
}
var asd = ${memberDto.getMember_role()};
if(asd<3){
	$("insert").css("display","");
}

</script>
</head>
<body>
	<div>
		<select id="cntPerPage" name="sel" onchange="selChange()">
			<option value="5"
				<c:if test="${paging.cntPerPage == 5}">selected</c:if>>5줄
				보기</option>
			<option value="10"
				<c:if test="${paging.cntPerPage == 10}">selected</c:if>>10줄
				보기</option>
			<option value="15"
				<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15줄
				보기</option>
			<option value="20"
				<c:if test="${paging.cntPerPage == 20}">selected</c:if>>20줄
				보기</option>
		</select>
	</div>
	<!-- 옵션선택 끝 -->
	<table border="1">
		<thead>
			<tr>
				<td>제목</td>
				<td>작성</td>
				<td>작성자</td>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty notificationList }">
					<tr>
						<td colspan="3" align="center">----- 작성된 공지사항이 없습니다 -----</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${notificationList}" var="dto">
						<tr>
							<td><strong><a
									href="notificationDetail.do?notification_seq=${dto.notification_seq}">${dto.notification_title}</a></strong></td>
							<td>${dto.notification_writer}</td>
							<td>${dto.notification_date}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<div id ="pageGroup" style="display: block; text-align: center;">
			<a>&lt;</a>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
					<a class="pageNum" href="notification.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
		</c:forEach> 
			<a href="list.do?nowPage=${paging.endPage + 1}&cntPerPage=${paging.cntPerPage}">&gt;</a> 
	</div>
	
	<input type="button" value="공지사항 작성" onclick="location.href='notificationInsert.do'" id="insert">
    	
	
</body>
</html>