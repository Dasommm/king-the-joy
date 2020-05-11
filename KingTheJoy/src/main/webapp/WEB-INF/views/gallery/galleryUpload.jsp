<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Insert title here</title>
</head>
<body>
	<h1>${dto.classseq }반 앨범</h1>
	<p>jpg 파일만 올려주세요. (5MB이하)</p>
	<div class="uploadDiv">
		1번 : <input type="file" accept=".jpg" name="uploadFile1">
	</div>
	<div class="uploadDiv">
		2번 : <input type="file" accept=".jpg" name="uploadFile2">
	</div>
	<div class="uploadDiv">
		3번 : <input type="file" accept=".jpg" name="uploadFile3">
	</div>
	<button id="uploadBtn">Upload</button>
	<hr/>
	<!-- 4번 드래그 범위 -->
	<div class="uploadDragDiv"></div>
	
<script src="https://code.jquery.com/jquery-3.5.0.min.js" ></script>
<script type="text/javascript">
$(document).ready(function () { 
	
	//이미지 선택
	var imageNumber = 0;
 	$("input[type=file]:eq(0)").change(function(e){
		alert("첫번째 선택됨");
		imageNumber = 1;
		upload(imageNumber);
	});
	$("input[type=file]:eq(1)").change(function(e){
		alert("두번째 선택됨");
		imageNumber = 2;
		upload(imageNumber);
	});
	$("input[type=file]:eq(2)").change(function(e){
		alert("세번째 선택됨");
		imageNumber = 3;
		upload(imageNumber);
	}); 
	
	
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
	//$("#uploadBtn").on("click", function(e){
	function upload(imageNumber) {
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile"+imageNumber+"']");
		var files = inputFile[0].files;
		console.log(files);
		
		//add filedate to formdata
		for(var i=0; i<files.length; i++){	
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url: 'uploadAjaxAction.do?imageNumber='+imageNumber,
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
	
	//드래그 업로드
	const $fileDrop = $('div.uploadDragDiv');
	$fileDrop.on('dragover dragenter', (evt) => {
		evt.preventDefault();
		$fileDrop.css("border", "1px dotted green");
	});
	$fileDrop.on('dragleave', (evt) => {
	    evt.preventDefault();
	    $fileDrop.css("border", "none");
	});
	$fileDrop.on('drop', (evt) => {
	    evt.preventDefault();
	    let files = evt.originalEvent.dataTransfer.files;
	    console.debug("drop>>", files);
	    $fileDrop.css("border", "none");
	    //$fileDrop.html(files[0].name);
	    //$("#ajax-file").prop("files", evt.originalEvent.dataTransfer.files);
	    //$('#form3').submit();
	    alert("드래그 성공")
	});
	
});

</script>	
</body>
</html>