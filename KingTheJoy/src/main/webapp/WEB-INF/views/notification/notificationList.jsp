<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

#noticeTable{
	width: 800px;
	height: 850px;
}


</style>


<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">


if(${memberDto.getMember_role()}<3){
	$("insert").css("display","");
}

</script>
</head>
<body>

<section>	

<%@include file="../common/parentHeader.jsp" %>
<%@include file="../parent/parentChatbot.jsp" %>
<%@include file="../parent/weatherApi.jsp" %>

	<div id="noticeTable">
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
	
	<input type="button" value="공지사항 작성" onclick="location.href='notificationInsert.do'" id="insert" style="display: none;">
	
	</div>

</section>
<%@include file="../common/footer.jsp" %>
    	
	
</body>
</html>