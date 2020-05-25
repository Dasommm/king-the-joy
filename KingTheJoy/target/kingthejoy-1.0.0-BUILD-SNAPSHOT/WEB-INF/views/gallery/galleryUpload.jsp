<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.uploadDragDiv{
	width: 200px;
	height: 200px;
	border: 1px solid gray;
}
</style>
<link rel="stylesheet" href="/kingthejoy/resources/css/galleryPage.css">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/PrincipalHeader.jsp" />
<div class="galleryBody">
	<h1>사진등록</h1>
	<h4>사진을 드래그해서 올려주세요. jpg 파일만 가능합니다. (5MB이하)</h4>
	<div class="grid">
		<c:forEach var="i" begin="1" end="9" step="1">
			<div class="item">
				<div class="item-content">
					<img src="/kingthejoy/resources/gallery/${dto.classseq }/${i }.jpg"
						onerror="this.src='/kingthejoy/resources/img/dropicon.png'">
				</div>
			</div>
		</c:forEach>
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
$(document).ready(function () { 
	//반번호
	var classseq = ${dto.classseq};
	
	//파일 제한
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880; // 5MB
	function checkExtension(fileName, fileSize) {
		if(fileSize >= maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		return true;
	}
	
	//ajax file upload
	function upload(imageNumber, files, classseq) {
		var formData = new FormData();
		console.log("drop>>", files);
		//var inputFile = $("input[name='uploadFile"+imageNumber+"']");
		//var files = inputFile[0].files;
		//console.log(files);
		
		//add filedate to formdata
		for(var i=0; i<files.length; i++){	
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url: 'uploadAjaxAction.do?imageNumber='+imageNumber+"&classseq="+classseq,
			processData: false,
			contentType: false,
			data: formData,
			type: 'POST',
				success: function(result){
					console.log(result);
					//showUploadedFile(result);
					//$(".uploadDiv").html(cloneObj.html());
				}
			}); // end ajax
	}		
	//}); //end click uploadBtn 업로드 버튼
	
	//드래그 & 드랍 업로드
	$(".item").each(function(i, e) {
		$(".item-content:eq("+i+")").on('dragover dragenter', (evt) => {
			evt.preventDefault();
			$(".item-content:eq("+i+")").css("border", "2px solid red");
		});
		$(".item-content:eq("+i+")").on('dragleave', (evt) => {
		    evt.preventDefault();
		    $(".item-content:eq("+i+")").css("border", "2px solid #222222");
		});
		$(".item-content:eq("+i+")").on('drop', (evt) => {
		    evt.preventDefault();
		    let files = evt.originalEvent.dataTransfer.files;
		    //console.log("drop>>", files);
		    $(".item-content:eq("+i+")").css("border", "none");
		    var imageNum = i+1;
		    upload(imageNum, files, classseq);
		    setTimeout(function() { showUploadedFile(i) }, 300);
		});
	});
	
	//이미지 보여주기
	function showUploadedFile(num) {
		var str = "";
		var imageNum = num+1;
		console.log("num: "+num+"/ imageNum: "+imageNum); 
		var uploadAppend = $(".item-content:eq("+num+")");
		$(".item-content:eq("+num+") img").remove();
		str += "<img src='/kingthejoy/resources/gallery/"+classseq+"/"+imageNum+".jpg' style='border: 2px solid #222222; border-radius: 4px 4px 4px 4px; box-sizing: border-box;'>"
		uploadAppend.append(str);
	}
	
});

</script>	
</body>
</html>