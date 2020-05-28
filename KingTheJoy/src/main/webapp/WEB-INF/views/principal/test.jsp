<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%
   request.setCharacterEncoding("UTF-8");
%>
<%
   response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   $(function() {
      $.getJSON("https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByAddr/json",function(data) {
         $.each(data, function(key, val) {
            if (key == "stores") {
               $.each(val, function(k, v) {
                  console.log(v.lat + ", " + v.lng);
               });
            }
         });
      });
   });
   
</script>

</head>
<body>

   <script type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a358c4a162c62bd5de53bab3619c7a06&libraries=services,clusterer,drawing"></script>
   <script type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a358c4a162c62bd5de53bab3619c7a06&libraries=services"></script>
   <script>
   
   var c1 = null;
   
   
    // 마커 클러스터러를 생성합니다  
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 9 // 클러스터 할 최소 지도 레벨 
    });
   

   // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
   // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다 
   addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
   addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);
   
   // 커스텀 오버레이 컨텐츠를 설정합니다
   placeOverlay.setContent(contentNode);

   // 각 카테고리에 클릭 이벤트를 등록합니다 
   addCategoryClickEvent();

   // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다 
   function addEventHandle(target, type, callback) {
      if (target.addEventListener) {
         target.addEventListener(type, callback);
      } else {
         target.attachEvent('on' + type, callback);
      }
   }

   // 카테고리 검색을 요청하는 함수입니다
   function searchPlaces() {
      if (!currCategory) {
         return;
      }

      // 커스텀 오버레이를 숨깁니다 
      placeOverlay.setMap(null);

      // 지도에 표시되고 있는 마커를 제거합니다
      removeMarker();

      ps.categorySearch(currCategory, placesSearchCB, {
         useMapBounds : true
      });
   } 

   // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
   function placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {

         // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
         displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
         // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요

      } else if (status === kakao.maps.services.Status.ERROR) {
         // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요

      }
   }

   // 지도에 마커를 표출하는 함수입니다 
   function displayPlaces(places) {

      // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
      // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
      var order = document.getElementById(currCategory).getAttribute('data-order');

      for (var i = 0; i < places.length; i++) {

         // 마커를 생성하고 지도에 표시합니다
         var marker = addMarker(new kakao.maps.LatLng(places[i].y,
               places[i].x), order);
         
         // 마커와 검색결과 항목을 클릭 했을 때
         // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
         (function(marker, place) {
            kakao.maps.event.addListener(marker, 'click', function() {
               displayPlaceInfo(place);
            });
         })(marker, places[i]);
      }
   }

   // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
   function addMarker(position, order) { 
      var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
      imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
      imgOptions = {
         spriteSize : new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
         spriteOrigin : new kakao.maps.Point(46, (order * 36)), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
         offset : new kakao.maps.Point(11, 28)
      
      
      // 마커 좌표에 일치시킬 이미지 내에서의 좌표 
      }, markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize,
            imgOptions), marker = new kakao.maps.Marker({
         position : position, // 마커의 위치
         image : markerImage 
      });

      marker.setMap(map); // 지도 위에 마커를 표출합니다
      markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
   }

   // 지도 위에 표시되고 있는 마커를 모두 제거합니다
   function removeMarker() {
      for (var i = 0; i < markers.length; i++) {
         markers[i].setMap(null);
      }
      markers = [];
   }

 

   // 각 카테고리에 클릭 이벤트를 등록합니다
   function addCategoryClickEvent() {
      var category = document.getElementById('category'), children = category.children;

      for (var i = 0; i < children.length; i++) {
         children[i].onclick = onClickCategory;
      }
   }

   // 카테고리를 클릭했을 때 호출되는 함수입니다
   function onClickCategory() {
      var id = this.id, className = this.className;

      placeOverlay.setMap(null);

      if (className === 'on') {
         currCategory = '';
         changeCategoryClass();
         removeMarker();
      } else {
         currCategory = id;
         changeCategoryClass(this);
         searchPlaces();
      }
   }
   


   // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
   function changeCategoryClass(el) {  
      var category = document.getElementById('category'), children = category.children, i;

      for (i = 0; i < children.length; i++) {
         children[i].className = '';
      }
      
      if (el) {
         el.className = 'on'; 
      }
   }
   
   
     var imageSrc = '/SINGLE/resources/images/img/mask.png', // 마커이미지의 주소입니다    
        imageSize = new kakao.maps.Size(20, 20), // 마커이미지의 크기입니다
        imageOption = {offset: new kakao.maps.Point(10, 10)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption), 
        markerPosition = new kakao.maps.LatLng(0, 0);
   

       $(function() {
          $.getJSON("https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByAddr/json",function(data) {
             $.each(data, function(key, val) {
                if (key == "stores") {
                   $.each(val, function(k, v) {
                      console.log(v.addr);
   // list.size 만큼 반복
   geocoder.addressSearch(v.addr, function(result, status) {
      
      iwContent = '<div style="padding:5px; width:150%;">'+v.addr+'<br>'+ 
               '약국 이름 : ' +v.name + '<br>'+
               '재고 들어오는 시간 : ' + v.stock_at + '<br>' +
               '재고량 : ' + v.remain_stat + '</div>';
         if (status === kakao.maps.services.Status.OK) {

            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            
            c1 = map.getCenter();

               var marker = new kakao.maps.Marker({
                  map : map,
                  position : coords, 
                  image : markerImage
               });
               
               var markers = [marker]; 
               
               clusterer.addMarkers(markers);

               var infowindow = new kakao.maps.InfoWindow(
                     {
                        content : iwContent
                     });
               kakao.maps.event.addListener(marker,
                     'mouseover', function() {
                        // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
                        infowindow.open(map, marker);
                     });

               // 마커에 마우스아웃 이벤트를 등록합니다
               kakao.maps.event.addListener(marker,
                     'mouseout', function() {
                        // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다 
                        infowindow.close();
                     });
         }
      })
   
                   });
                }
             });
          });
       });
    
    
    
   </script>
</body>
</html>