<%@page import="com.project.kingthejoy.children.dto.ChildrenPagingDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	overflow: auto;
	background-color: oldlace;
}

div {
	/* border: 1px dotted blue; */
	margin: 20px;
}

#body {
	height: 800px;
	/* overflow: auto; */
}

.up {
	width: 80%;
	margin-left: 200px;
	margin-right: auto;
	height: 650px;
	/* overflow: auto; */
	/* display: flex; */
}

/* .down {
	width: 80%;
	height: 300px;
	margin-left: auto;
	margin-right: auto;
	display: flex;
} */
.titlebar {
	height: 50px;
	width: 70%;
	margin-left: auto;
	margin-right: auto;
}

.child {
	width: 180px;
	height: 250px;
	/* border: 1px solid green; */
	margin-left: 30px;
	margin-right: 30px;
	float: left;
	background-color: white;
	box-shadow: 4px 4px 4px 4px gray;
}

.section01 {
	padding: 0;
	margin: 5%;
	width: 90%;
	height: 120px;
}

.section02 {
	padding: 0;
	margin: 5%;
	width: 90%;
	height: 100px;
}

img {
	display: center;
	width: 100px;
	height: 120px;
}

[type*=checkbox] {
	top: 10px;
	float: right;
}

#classbar {
	list-style: none;
	width: 65%;
	height: 30px;
	border: 1px dotted black;
	margin-left: 150px;
	/* margin-right: auto; */
	margin-top: 10px;
}

#classbar02 {
	list-style: none;
	margin-top: -47px;
	margin-left: 950px;
}

.class-item01 {
	display: inline;
	margin-left: 5px;
}
.class-item02 {
	display: inline;
	margin-left: 5px;
}

#button {
	width: 180px;
	margin-left: 950px;
	margin-top: -45px;
}

.titlebar ul#classbar li {
	display: inline;
	padding-left: 120px;
}

.notice {
	position: relative;
	left: 50px;
	top:1px
}

#name {
	magin: 10px;
	text-align: center;
}

#insert { /* position: absolute; */
	
}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

</head>

<body>

	<div id="header">
		<h1>asdfasf</h1>
		<div>
			<span><a href="#">1</a></span> <span><a href="#">2</a></span> <span><a
				href="#">3</a></span> <span><a href="#">4</a></span>
		</div>
	</div>

	<div id="body">
		<div class="titlebar">
			<ul id="classbar">
				<li class="class-item"><a href="#"></a></li>
				<li class="class-item"><a href="#"></a></li>
				<li class="class-item"><a href="#"></a></li>
			</ul>
			<ul id="classbar02">
				<li class="class-item01"><a href="childreninsert.do"style="text-decoration: none">원아등록</a></li>
				<!-- <li class="class-item02"><a href="childrendelete.do"style="text-decoration: none">원아삭제</a></li>  -->
			</ul>
			<!-- <div id="button">
				
				<button class="class-item02">출석확인</button>
			</div> -->
		</div>


		<div class="up">

			<c:choose>
				<c:when test="${empty list }">
					<p>no child</p>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list }" var="dto" begin="${dto.children_seq }"
						step="1">

						<%-- <c:if test=${dto.children_seq % 5 == 0} > --%>
						<div class="child">
							<form action="childrendelete.do" method="post">
								<input type="hidden" name="children_seq" value="${dto.children_seq}">
								<input type="hidden" name="class_seq" value="${dto.class_seq }">

								<div class="section01" style="text-align: center;">
									<img alt=""
										<%-- src="reource/${dto.children_image }" --%> src="resources/img/image01.png">
									<%-- <input type="checkbox" name="children_seq" value="value="${dto.children_seq}" id="checkbox"> --%>
								</div>

								<div class="section02">
									<p id="name">${dto.class_name}</p>
									<p style="text-align: center;">${dto.children_name }어린이</p>
									<!-- <input type="submit" value="삭제" class="delete">  -->
									<input type="button" value="알림장" onclick="openNote();" class="notice">
									
									<script type="text/javascript">
									//알림장 팝업띄우기
									function openNote(){
										var openWin;
										var popUrl = "/note/note.do?member_seq="+${dto.member_seq}+"&children_seq="+${dto.children_seq};
										var popOption = "width=725px, height=800px, left=100, top=50";
										openWin = window.open(popUrl, "NoteForm", popOption);
									}
										
									</script>
									
								</div>
							</form>
						</div>


					</c:forEach>
				</c:otherwise>
			</c:choose>




		</div>



	</div>



	<!-- </div> -->
	<!-- body -->

</body>
</html>