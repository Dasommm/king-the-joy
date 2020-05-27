<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@page isELIgnored="false" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/letterPage.css">
<title>Letter Box</title> 
</head>
<body>
	<h2>보낸 쪽지함</h2>
	
	<div align="right" style="margin-right: 10px;">
		<select name="letterPageType">
	    <option value="receive">받은쪽지</option>
	    <option value="send" selected >보낸쪽지</option>
		</select>
	</div>
	
	<form action="letterDel.do" method="post">
	<div align="center">
	<input type="hidden" name="member_seq" value=${member_seq }>
	<table class="table table-striped table-bordered">
	<colgroup>
		<col width="8%">
		<col width="auto">
		<col width="22%">
		<col width="20%">
	</colgroup>
		<tr>
			<th><input type="checkbox" name="allchk" onclick="allChk(this.checked);"></th>
			<th>제목</th>
			<th>받은이</th>
			<th>날짜</th>
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td><input type="checkbox" name="chk" value="${dto.letter_seq }"></td>

				<c:choose>
					<c:when test="${dto.letter_status == 0 }">
						<td class="title" style="color: blue; text-align: left; cursor:pointer; ">
						<img src="${pageContext.request.contextPath}/resources/img/new_icon.png" style="width:20px;">
						${dto.letter_title }</td>
					</c:when>
					<c:otherwise>
						<td class="title" style="text-align: left; cursor:pointer;">${dto.letter_title }</td>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${dto.letter_status == 0 }">
						<td class="sender" style="color: blue; cursor:pointer;">${dto.member_id }</td>
					</c:when>
					<c:otherwise>
						<td class="sender" style="cursor:pointer;">${dto.member_id }</td>
					</c:otherwise>
				</c:choose>
				<td><f:formatDate value="${dto.letter_regdate }" pattern="yyyy.MM.dd"/></td>
			</tr>
		</c:forEach>
	</table>
	</div>

 	<!-- 페이징  -->
	<div align="center">
	<ul class="pagination pagination-sm justify-content-center" 
		style="margin: 0 auto; display: inline-block;">
	<c:choose>
		<c:when test="${paging.blockgroup != 1 }">
			<li class="page-item"><a class="page-link" aria-label="Previous"
				href="letterPage.do?currentpage=${paging.blockstart-1 }&member_seq=${member_seq}" > 
			<span aria-hidden="true">&laquo;</span>
			<span class="sr-only">Previous</span>
			</a></li>
		</c:when>
		<c:when test="${paging.blockgroup == 1 }">
			<li><span style="color:gray;">&laquo;</span></li>
		</c:when>
	</c:choose>

 	<c:choose>
		<c:when test="${paging.blockend < paging.totalpage }">
			<c:forEach var="i" begin="${paging.blockstart}" end="${paging.blockend}" step="1">
				<li><a href="letterPage.do?currentpage=${i }&member_seq=${member_seq}">${i }</a></li>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach var="i" begin="${paging.blockstart}" end="${paging.totalpage}" step="1">
				<li><a href="letterPage.do?currentpage=${i }&member_seq=${member_seq}">${i }</a></li>
			</c:forEach>
		</c:otherwise>
	</c:choose>	

	<c:choose>
		<c:when test="${paging.blockend < paging.totalpage }">
			<li class="page-item"><a class="page-link" aria-label="Next"
				href="letterPage.do?currentpage=${paging.blockend+1 }&member_seq=${member_seq}" > 
			<span aria-hidden="true">&raquo;</span> 
			<span class="sr-only">Next</span>
			</a></li>
		</c:when>
		<c:otherwise>
			<li><span style="color:gray;">&raquo;</span></li>
		</c:otherwise>
	</c:choose>
	</ul>
	</div>
	
	<div align="right" style="margin-top: 20px; margin-right: 15px;">
		<input class="btn btn-default btn-sm" type=submit value="선택삭제" id="multyDel">
		<input class="btn btn-default btn-sm" type="button" value="작성" onclick="gowrite()">
	</div>
	</form>
	
<script src="https://code.jquery.com/jquery-3.5.0.min.js" ></script>	
<script type="text/javascript">
	//전체 체크
	function allChk(bool){
		var chks= document.getElementsByName("chk");
		for(var i=0; i<chks.length; i++){
			chks[i].checked = bool;
		}
	}
	
	function gowrite() {
		location.href="letterWrite.do?member_seq="+${member_seq};
	}
	
	$(function() {
		// 체크 확인
		$("#multyDel").click(function() {
			if ($("input:checked").length == 0) {
				alert("하나 이상 체크해 주세요");
				return false;
			}
		})
		// 지정 보내기
		$(".sender").click(function() {
			location.href="letterPickedWrite.do?member_seq="+${member_seq}+"&letter_sender="+$(this).text();
		})
		// 자세히 보기
		$(".title").each(function(i){
			$(this).click(function(e){
				var member_seq = $("input[name=member_seq]:eq(0)").val();
				var letter_seq = $("input[name=chk]:eq("+i+")").val();
				location.href="letterDetail.do?letter_seq="+letter_seq+"&member_seq="+member_seq;
			})
		})
		$("select[name=letterPageType]").change(function(){
    		var type =  $(this).val();
    		if(type=='send'){ location.href='letterSendPage.do?currentpage=1&member_seq='+${member_seq } }
    		if(type=='receive'){ location.href='letterPage.do?currentpage=1&member_seq='+${member_seq } }
		})

		/* if(${empty sessionScope.member_id}){
			alert("로그인이 필요합니다");
			location.href="/home.do";
		} */
	});
</script>
</body>
</html>