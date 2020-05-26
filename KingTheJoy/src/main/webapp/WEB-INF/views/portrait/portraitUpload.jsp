<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>사진 업로드</h4>
	<div class="uploadDiv">
		<input type="file" name="uploadFile">
		<button id="uploadBtn" style="a">Submit</button>
	</div>
	
<script src="https://code.jquery.com/jquery-3.5.0.min.js" ></script>	
<script type="text/javascript">
$(document).ready(function () { 

	//올린 파일 초기화
	var cloneObj = $(".uploadDiv").clone();

	$("#uploadBtn").on("click", function(e){
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		console.log(files[0]);
	
		//유효성 검사
		if(!checkExtension(files[0].name, files[0].size)){
			return false;
		}
		formData.append("uploadFile", files[0]);

		$.ajax({
			url: '${pageContext.request.contextPath}/portrait/portraitAction.do?school_seq='+${school_seq}+'&member_seq='+${member_seq},
			processData: false,
			contentType: false,
			data: formData,
				type: 'POST',
				dataType:'json',
				success: function(result){
					console.log(result);
					$(".uploadDiv").html(cloneObj.html());
				}
			}); // end ajax
			
   		setTimeout(function() {
			self.close();
		}, 1000);	
			
	}); //end click uploadBtn 업로드 버튼
	
	//유효성 검사
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var regex2 = new RegExp("(.*?)\.(jpg|jpeg|png|bmp|gif|tif)$");
	var maxSize = 5242880; // 5MB
	
	function checkExtension(fileName, fileSize) {
		if(fileSize >= maxSize){
			alert("파일 사이즈 초과 (5MB)");
			return false;
		}
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
 		if(!regex2.test(fileName)){
			alert("이미지 파일이 아닙니다.");
			return false;
		}
		return true;
	}
	
	
	
	
	
});
</script>	
</body>
</html>