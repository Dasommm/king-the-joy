<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <style>
  body {
    min-width: 520px;
  }
  .column {
    width: 230px;
    float: left;
    padding-bottom: 1.3em;
  }
  .portlet {
    margin: 1em 1em 1em 1em;
    padding: 0.3em;
  }
  .portlet-header {
    padding: 0.2em 0.3em;
    margin-bottom: 0.3em;
    position: relative;
  }
  .portlet-toggle {
    position: absolute;
    top: 50%;
    right: 0;
    margin-top: -8px;
  }
  .portlet-content {
    padding: 0.4em;
    display: none;
  }
  .portlet-placeholder {
    border: 1px dotted black;
    margin: 0 1em 1em 0;
    height: 50px;
  }
  
  .portrait{
  	width: 11.6em;
  	height: 15em;
  	border-radius: 3px 3px 3px 3px;
  	background-color: #e9e9e9;
  	margin-bottom: 5.5px;
  }
   .portrait img{
   	width: 11.6em;
   	height: 15em;
  }
  .portlet-content p{
  	margin: 2px;
  	padding: 1px;
  	font-size: 13px;
  	border: 1px solid #e2e2e2;
  }
  .mgtBody{
	margin: 3em 22em;
  }
  .mainBody{
  	width:100%; 
  	height:100%; 
  	overflow:hidden; 
  	padding:2%; 
  }
  .mgtButton{
  	margin: 1px;
  	font-size: 0.9em !important;
  }
  .mgtHeaderButton{
  	margin: 0px;
	top:-30px; 
	position: relative;
  }
  hr{
  	position: relative;
  	z-index: 1;
  	top: -20px;
  	margin-bottom: -1em !important;
  }
  </style>
</head>
<body>
<%-- <jsp:include page="../common/PrincipalHeader.jsp" /> --%>
<div id="header"></div>
<div class="mgtBody">
	<h2>선생님 관리 페이지</h2>
	<p align="right" class="mgtHeaderButton">
		<input type="button" name="button_register" value="등록">
		<input type="button" name="button_open" value="전체 열기">
		<input type="button" name="button_close" value="전체 닫기">
	</p><hr>
	<div class="mainBody">
	<c:forEach var="dto" items="${list }">
		<div class="column">
			<div class="portlet">
				<div class="portrait"><img src="${dto.member_image }"></div>
				<div class="portlet-header">${dto.member_name }</div>
				<div class="portlet-content">
					<input type="hidden" name="school_seq" value="${dto.school_seq }">
					<input type="hidden" name="member_seq" value="${dto.member_seq }">
					<p>직위: 
						<c:choose>
							<c:when test="${dto.member_role==1 }">원장님</c:when>
							<c:when test="${dto.member_role==2 }">선생님</c:when>
							<c:otherwise>미정</c:otherwise>
						</c:choose>
					</p>
					<p>담당: 
						<span>${dto.class_name }</span>
						<span style="float:right;"><input type="button" value="변경" style="font-size: 0.8em;"></span>
					</p>
					<p>아이디: ${dto.member_id }</p>
					<p>전화번호: ${dto.member_phone }</p>
					<p>이메일:<br> ${dto.member_email }</p>
					<p>주소: ${dto.member_addr }</p>
					<p>가입일: <f:formatDate value="${dto.member_date }" pattern="yyyy.MM.dd"/></p>
					<p>상태: 
						<c:choose>
							<c:when test="${fn:trim(dto.member_delflag) eq 'N' }">가입</c:when>
							<c:when test="${fn:trim(dto.member_delflag) eq 'Y' }">탈퇴</c:when>
							<c:otherwise>오류</c:otherwise>
						</c:choose>
					</p>
					<p style="text-align: center;">
						<input type="button" class="mgtButton" value="사진수정">
						<input type="button" class="mgtButton" value="탈퇴처리">
					</p>
				</div>
			</div>
		</div>
	</c:forEach>
	</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$( function() {
	
	var school_seq = $("input[name=school_seq]").val();
	
	$( ".column" ).sortable({
      connectWith: ".column",
      handle: ".portlet-header",
      cancel: ".portlet-toggle",
      placeholder: "portlet-placeholder ui-corner-all"
    });
 
    $( ".portlet" )
      .addClass( "ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" )
      .find( ".portlet-header" )
        .addClass( "ui-widget-header ui-corner-all" )
        .prepend( "<span class='ui-icon ui-icon-plusthick portlet-toggle'></span>");
 
    $( ".portlet-toggle" ).on( "click", function() {
      var icon = $( this );
      icon.toggleClass( "ui-icon-minusthick ui-icon-plusthick" );
      icon.closest( ".portlet" ).find( ".portlet-content" ).toggle();
    });
	$.ajax({
		url: '${pageContext.request.contextPath}/principal/principalHeader.do',
		type: 'GET',
		dataType: 'html',
			success: function(result){
				$("#header").html(result);				
			}
		}); // end ajax
		
	$("input[name=button_register]").on("click", function(){
		window.open('${pageContext.request.contextPath}/principal/principalTeacherRegistry.do','선생님등록','width=460,height=530');
	});
	var portletView = $(".portlet-content");
 	$("input[name=button_open]").on("click", function(){
		portletView.css("display", "block")
	});
	
	$("input[name=button_close]").on("click", function(){
		portletView.css("display", "none")
	});
	
 	$("input[value=변경]").each(function(i){
		$(this).click(function(e){
			var member_seq = $("input[name=member_seq]:eq("+i+")").val();
			window.open('${pageContext.request.contextPath}/principal/principalClassChange.do?member_seq='+member_seq+'','반변경','width=360,height=60');
		})
	});	
 	
 	$("input[value=사진수정]").each(function(i){
		$(this).click(function(e){
			var member_seq = $("input[name=member_seq]:eq("+i+")").val();
			window.open('${pageContext.request.contextPath}/portrait/portraitUpload.do?school_seq='+school_seq+'&member_seq='+member_seq+'','이미지업로드','width=430,height=140,location=no,scrollbars=yes');
		})
	});	
		
	$("input[value=탈퇴처리]").each(function(i){
		$(this).click(function(e){
			var member_seq = $("input[name=member_seq]:eq("+i+")").val();
			if(confirm("탈퇴 처리를 진행하시겠습니까?") == true){
				location.href="${pageContext.request.contextPath}/principal/principalFlag.do?member_seq="+member_seq;
			}else { return; }
		})
	});		
		
});
  
  </script>
</body>
</html>