<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<body>

	<h1 align="center">투약 의뢰함</h1>
	<table border="1" style="margin-left: auto; margin-right: auto;" class="table">
		<tr>
			<th>제목</th>
			<td>${dto.pill_title }</td>
		</tr>

		<tr>
			<th>아동명</th>
			<td>${dto.children_name }</td>
		</tr>
		<tr>
			<th>증상</th>
			<td>${dto.pill_symptom }</td>
		</tr>
		<tr>
			<th>약종류</th>
			<td>${dto.pill_type }
			</td>
		</tr>
		<tr>
			<th>주의사항</th>
			<td><textarea rows="10" cols="50" name="pill_special" readonly="readonly">${dto.pill_special }</textarea></td>
		<tr>
			<th>의뢰자</th>
			<td>${dto.member_name }</td>

		</tr>
		<tr>
			<td colspan="6" align="right">
				<input type="button" value="삭제" onclick="location.href='pillDelete.do?pill_seq=${dto.pill_seq}'" >
			</td>
		</tr>
	</table>

</body>
</html>