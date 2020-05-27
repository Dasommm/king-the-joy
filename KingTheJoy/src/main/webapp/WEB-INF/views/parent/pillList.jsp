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
    .table {
      border-collapse: collapse;
      border-top: 3px solid #168;
    }  
    .table th {
      color: #168;
      background: #f0f6f9;
      text-align: center;
    }
    .table th, .table td {
      padding: 10px;
      border: 1px solid #ddd;
    }
    .table th:first-child, .table td:first-child {
      border-left: 0;
    }
    .table th:last-child, .table td:last-child {
      border-right: 0;
    }
    .table tr td:first-child{
      text-align: center;
    }
    .table caption{caption-side: bottom; display: none;}
    
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
	
		<h1 align="center">투약 의뢰함</h1>
		<form action="pillmuldel.do" method="post">
			<table border="1" class="table">
			<colgroup>
					<col width="30">
					<col width="50">
					<col width="300">
					<col width="100">
					<col width="100">
				</colgroup>
				<thead>
					<tr>
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
								<tr>
									<td align="center"><input type="checkbox" name="chk"
										value="${dto.pill_seq }" /></td>
									<td>${dto.pill_seq }</td>
									<td><a href="pillDetail.do?pill_seq=${dto.pill_seq }">${dto.pill_title }</a></td>
									<td>${dto.member_name }</td>
									<td><fmt:formatDate value="${dto.pill_date }"
											pattern="YYYY/MM/dd" type="date" /></td>

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
		<div style="float: left; width: 100%; text-align: center; align-content: center;">

			<ul class="pagination" style="list-style: none;">
				<li>
					<%
						if (pagegroup > 1) {
					%> <a href="pillList.do?page=<%=startpage - 1%>"><span
						class="button">prev</span></a> <%
 	}
 %>

				</li>
				<li>
					<%
						for (int pagenum = startpage; pagenum <= ((endpage < totalpage) ? endpage : totalpage); pagenum++) {
					%> <a href="pillList.do?page=<%=pagenum%>" class="page"><%=pagenum%></a>
					<%
						}
					%> <%-- 	<a href="answer.do?command=list&page=<%=pagenum%>" class="page"><%=pagenum %></a>	 --%>
					<%
						if (endpage < pdto.getTotalpage()) {
					%> <a href="pillList.do?page=<%=endpage + 1%>" class="button">next</a>
					<%
						}
					%>
				
			</ul>
			<%-- <a href="answer.do?command=list&page=<%=endpage+1%>" class="button">next</a> --%>
		</div>
		
	<footer><%@include file="../common/footer.jsp"%> </footer>
	</section>
	
</body>
</html>