<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	.bigPictureWrapper{
		position: absolute;
		display: none;
		justify-content: center;
		align-items: center;
		top: 0%;
		width: 100%;
		height: 100%;
		background-color: gray;
		z-index: 100;
		background: rgba(0,0,0,0.5);
	}
	.bigPicture{
		position: relative;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.bigPicture img{
		width: 600px; 
		height: auto; 
	}
</style>
<link rel="stylesheet" href="/kingthejoy/resources/css/galleryPage.css">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/PrincipalHeader.jsp" />
<div class="galleryBody">
	<h1>☆유치원 갤러리</h1>
	<div class="grid">
		<c:forEach var="i" begin="1" end="9" step="1">
			<div class="item">
				<div class="item-content">
					<a ondblclick="location.href='javascript:showImage(${i })';">
					<img src="/kingthejoy/resources/gallery/${dto.classseq }/${i }.jpg"
						onerror="this.src='/kingthejoy/resources/img/imageWarning.png'">
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="bigPictureWrapper">
		<div class="bigPicture"></div>
	</div>
</div>	
<script src="https://code.jquery.com/jquery-3.5.0.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/web-animations/2.3.2/web-animations.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/hammer.js/2.0.8/hammer.min.js"></script>
<script src="https://unpkg.com/muuri@0.8.0/dist/muuri.min.js"></script>
<script type="text/javascript">
	const grid = new Muuri('.grid', {
		dragEnabled: true,
	});
	
	function showImage(fileNum){
		//alert("fileNum: "+fileNum);
 		$(".bigPictureWrapper").css("display","flex").show();
		$(".bigPicture")
		.html("<img src='display.do?fileName="+fileNum+".jpg'>")
		.animate({width:'600px', height:'600px'}, 1000); // size를 %로 하면 떨림현상
	}

	$(".bigPictureWrapper").on("click", function(e){
		$(".bigPicture").animate({width:'0px', height:'0px'}, 1000);
		setTimeout(() => {
			$(this).hide();
		}, 1000);
	});
	
</script>
</body>
</html>