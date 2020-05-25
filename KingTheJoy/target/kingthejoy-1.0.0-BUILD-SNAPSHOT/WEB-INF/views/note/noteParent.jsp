<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="resources/css/note/noteParent.css">
</head>
<body>

	<%@include file="../common/parentHeader.jsp"%>
	<div class="container-box">
		<section class="grid-container">
			<c:choose>
				<c:when test="${empty noteSelect }">
			====알림장이 없습니다=====
				</c:when>
				<c:otherwise>
					<c:forEach items="${noteSelect }" var="note">
						<article class="item">
							<h3>
								<fmt:formatDate value="${note.note_date }" pattern="MM월 dd일" />
								알림장
							</h3>
							<fieldset>
								<table>
									<tr>
										<th>기분 :${note.note_feeling }</th>
									</tr>
									<tr>
										<th>건강 :${note.note_health }</th>
									</tr>
									<tr>
										<th>배변 :${note.note_def }</th>
									</tr>
									<tr>
										<th>체온 :${note.note_temp }도</th>
									</tr>
									<tr>
										<th>수면시간 :${note.note_sleep }시간</th>
									</tr>
									<tr>
										<th>식사여부 :${note.note_meal }</th>
									</tr>
									<c:choose>
										<c:when test="${empty note.note_spec }">
											<tr>
												<th>특이사항 : 특이사항이 없습니다.</th>
											</tr>
										</c:when>
										<c:otherwise>
											<tr>
												<th>특이사항 :${note.note_spec }</th>
											</tr>
										</c:otherwise>
									</c:choose>
								</table>
							</fieldset>
						</article>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</section>
	</div>
	<%@include file="../common/footer.jsp"%>
</body>
</html>