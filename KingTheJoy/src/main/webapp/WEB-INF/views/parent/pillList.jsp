
<%@page import="com.project.kingthejoy.pill.dto.PillPagingDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

function allChk(check) {
	var chks = document.getElementsByName("chk");
	for (var i = 0; i < chks.length; i++) {
		chks[i].checked = check;
	}

}
</script>
<style>
.table01 {
	border-collapse: collapse;
	border-top: 3px solid #168;
}

.table01 th {
	color: #168;
	background: #f0f6f9;
	text-align: center;
}

.table01 th, .table01 td {
	padding: 10px;
	border: 1px solid #ddd;
}

.table01 th:first-child, .table01 td:first-child {
	border-left: 0;
}

.table01 th:last-child, .table01 td:last-child {
	border-right: 0;
}

.table01 tr td:first-child {
	text-align: center;
}

.table01 caption {
	caption-side: bottom;
	display: none;
}

td {
	text-align: center;
}
</style>

</head>
<%
	PillPagingDto pdto = (PillPagingDto) request.getAttribute("pdto");
	int pagegroup = (int) Math.ceil((double) pdto.getPage() / pdto.getPagescale());
	int startpage = pdto.getPagescale() * (pagegroup - 1) + 1;
	int endpage = pdto.getPagescale() * pagegroup;
	int totalpage = pdto.getTotalpage();
%>
<body>



	<section>
		<jsp:include page="../common/TeacherHeader.jsp" />

		<h1 align="center"></h1>
		<form action="/pill/pillmuldel.do" method="post">
			<table border="1" class="table01"
				style="margin-left: auto; margin-right: auto; margin-top: 200px;">
				<colgroup>
					<col width="50">
					<col width="50">
					<col width="300">
					<col width="120">
					<col width="130">
				</colgroup>
				<thead>
					<tr class="scrollLocation">
						<th><input type="checkbox" name="all"
							onclick="allChk(this.checked);" /></th>
						<th>NO.</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty list }">
							<tr>
								<td colspan="5">------------------------no
									comment---------------------</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${list }" var="dto">
								<tr class="listToChange">
									<td align="center"><input type="checkbox" name="chk"
										value="${dto.pill_seq }" /></td>
									<td class="scrolling">${dto.pill_seq }</td>
									<td><a
										href="/pill/pillDetail.do?pill_seq=${dto.pill_seq }">${dto.pill_title }</a></td>
									<td>${dto.member_name }</td>
									<td><fmt:formatDate value="${dto.pill_date }" type="date" pattern="YYYY/MM/dd"/></td>

								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5" align="right"><input type="submit"
							value="선택삭제"></td>
					</tr>
				</tfoot>
			</table>
		</form>
		<div
			style="float: left; width: 100%; margin-left: auto; margin-right: auto;">

			<ul class="pagination"
				style="list-style: none; margin-left: auto; margin-right: auto;">
				<li>
					<%
						if (pagegroup > 1) {
					%> <a href="/pill/pillList.do?page=<%=startpage - 1%>"><span
						class="button">prev</span></a> <%
 	}
 %>

				</li>
				<li>
					<%
						for (int pagenum = startpage; pagenum <= ((endpage < totalpage) ? endpage : totalpage); pagenum++) {
					%> <a href="/pill/pillList.do?page=<%=pagenum%>" class="page"><%=pagenum%></a>
					<%
						}
					%> <%-- <a href="answer.do?command=list&page=<%=pagenum%>" class="page"><%=pagenum %></a>	 --%>
					<%
						if (endpage < pdto.getTotalpage()) {
					%> <a href="/pill/pillList.do?page=<%=endpage + 1%>" class="button">next</a>
					<%
						}
					%>
				
			</ul>
			<%-- <a href="answer.do?command=list&page=<%=endpage+1%>" class="button">next</a> --%>
		</div>

		<jsp:include page="../common/footer.jsp" />
	</section>

</body>

</html>