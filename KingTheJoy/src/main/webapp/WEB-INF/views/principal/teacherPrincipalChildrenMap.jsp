<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${memberDto.getMember_role() == 2}">
			<%@include file="../common/TeacherHeader.jsp"%>
			<input type="hidden" id="school_address" value="${school_address}">
			<c:forEach items="${memberAddressList }" var="memberAddressList">
				<input type="hidden" name="member_name" class="member_name" value="${memberAddressList.member_name }">
				<input type="hidden" name="member_address" class="member_address" value="${memberAddressList.member_addr }">
			</c:forEach>
		</c:when>
		<c:when test="${memberDto.getMember_role() == 1}">
			<%@include file="../common/PrincipalHeader.jsp"%>
			<input type="hidden" id="school_address" value="${school_address}">
			<c:forEach items="${memberAddressList }" var="memberAddressList">
				<input type="hidden" name="member_name" class="member_name" value="${memberAddressList.member_name }">
				<input type="hidden" name="member_address" class="member_address" value="${memberAddressList.member_addr }">
			</c:forEach>
		</c:when>
	</c:choose>
	
	
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8aacaa83f01d2383b4d16da68b1c1b9c&libraries=services"></script>
	<div id="map" style="width: 1200px; height: 800px; margin: auto;"></div>
	<script type="text/javascript">
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(33.450701, 126.570667), 
	        level: 4 // 지도의 확대 레벨
	    };  

	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	
	// 주소로 좌표를 검색합니다
	<c:forEach items="${memberAddressList }" var="memberAddressList">
	// 주소로 좌표를 검색합니다
		geocoder.addressSearch('${memberAddressList.member_addr}', function(result, status) {

		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
	
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords	
		        });
	
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">${memberAddressList.member_name}</div>'
		        });
		        infowindow.open(map, marker);
	
		    }  
		});    
	</c:forEach>

	
	geocoder.addressSearch($('#school_address').val(), function(result, status) {

	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {

	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });

	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">School</div>'
	        });
	        infowindow.open(map, marker);

	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    } 
	}); 
	

		</script>
	<%@include file="../common/footer.jsp"%>
</body>
</html>