<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    width: 170px;
    float: left;
    padding-bottom: 10px;
  }
  .portlet {
    margin: 1em 1em 1em 1em;
    padding: 0.3em;
  }
  .portlet-header {
    padding: 0.2em 0.3em;
    margin-bottom: 0.5em;
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
  	width: 7.9em;
  	height: 7.9em;
  	border-radius: 3px 3px 3px 3px;
  	background-color: #e9e9e9;
  	margin-bottom: 4.8px;
  }
   .portrait img{
   	width: 7.9em;
   	height: 7.9em;
  }
  .portlet-content p{
  	margin: 2px;
  	font-size: 13px;
  	border: 1px solid #e2e2e2;
  }
  </style>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
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
		url: 'principalHeader.do',
		type: 'GET',
		dataType: 'html',
			success: function(result){
				$("#header").html(result);				
			}
		}); // end ajax

  } );
  
  </script>
</head>
<body>
<%-- <jsp:include page="../common/PrincipalHeader.jsp" /> --%>
<div id="header"></div>
	<h2>선생님 관리 페이지</h2><hr>
	<p align="right" style="magin:10px;">
		<input type="button" value="등록" 
			onclick="window.open('portraitUpload.do?member_seq=1','이미지업로드','width=430,height=140,location=no,status=no,scrollbars=yes')">
		<input type="button" value="열기">
		<input type="button" value="닫기">
	</p>
	<div style="width:96%; height:100%; overflow:hidden; padding:2%; background-color: gray;">
	<c:forEach var="i" begin="1" end="12" step="1">
		<div class="column">
			<div class="portlet">
				<div class="portrait"><img src="https://cdn.pixabay.com/photo/2020/05/10/16/06/squirrel-5154634_960_720.jpg"></div>
				<div class="portlet-header">선생님1</div>
				<div class="portlet-content">
					<p>직위: 선생님</p>
					<p>담당: 햇님반</p>
					<p>아이디: teacher</p>
					<p>전화번호: 01012341234</p>
					<p>이메일: tttt@daum.net</p>
					<p>주소: 서울시</p>
					<p>가입일: 2020.05.05</p>
					<p>상태: 가입</p>
				</div>
			</div>
		</div>
	</c:forEach>
	</div>
</body>
</html>