<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

.table01 th, .table td {
	padding: 10px;
	border: 1px solid #ddd;
}

.table01 th:first-child, .table td:first-child {
	border-left: 0;
}

.table01 th:last-child, .table td:last-child {
	border-right: 0;
}

.table01 tr td:first-child {
	text-align: center;
}

.table01 caption {
	caption-side: bottom;
	display: none;
}

#fixed{
		width: 100px;
		height: 300px;
		background-color: gold;
		position: fixed;
		right: 10px;
		top: 200px;
	}
</style>
</head>
<body>
	<section>
	<%@include file="../common/parentHeader.jsp"%>
		<h1 align="center">투약 요청서</h1>
		<form action="pillInsert.do" method="post">
			<table border="1" class="table01"
				style="margin-left: auto; margin-right: auto; margin-top: 100px" >
				<tr>
					<th>제목</th>
					<td><input type="text" name="pill_title"></td>
				</tr>

				<tr>
					<th>아동명</th>
					<td><input type="text" name="children_name"></td>
				</tr>
				<tr>
					<th>증상</th>
					<td><input type="text" name="pill_symptom"> ex)발열,
						코막힘, 기침 등등...</td>
				</tr>
				<tr>
					<th>약종류</th>
					<td><input type="text" name="pill_type"> ex)물약, 알약,
						가루약 등등</td>
				</tr>
				<tr>
					<th>주의사항</th>
					<td><textarea rows="10" cols="50" name="pill_special"></textarea></td>
				<tr>
					<th>의뢰자</th>
					<td><input type="text" name="member_name"></td>

				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="전송">
					</td>

				</tr>

			</table>
		</form>
		<div id="fixed">
		<a href="pillrequest.do">투약의뢰서</a>
		<a href="translate.do">번역</a>
		
		</div>
		<footer> <%@include file="../common/footer.jsp"%> </footer>
	</section>
</body>
</html>