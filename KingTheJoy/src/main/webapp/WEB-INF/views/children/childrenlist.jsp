<%@page import="com.project.kingthejoy.children.dto.ChildrenPagingDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
   overflow: auto;
   
}

div {
   /* border: 1px dotted blue; */
   margin: 20px;
}

#body {
   height: 800px;
   /* overflow: auto; */
}

.up {
   width: 80%;
   margin-left: 200px;
   margin-right: auto;
   height: 650px;
   margin-top: 50px;
   /* overflow: auto; */
   /* display: flex; */
}

/* .down {
   width: 80%;
   height: 300px;
   margin-left: auto;
   margin-right: auto;
   display: flex;
} */
.titlebar {
   height: 50px;
   width: 70%;
   margin-left: auto;
   margin-right: auto;
}

.child {
   width: 180px;
   height: 250px;
   /* border: 1px solid green; */
   margin-left: 30px;
   margin-right: 30px;
   float: left;
   background-color: white;
   box-shadow: 4px 4px 4px 4px gray;
}

.section01 {
   padding: 0;
   margin: 5%;
   width: 90%;
   height: 120px;
}

.section02 {
   padding: 0;
   margin: 5%;
   width: 90%;
   height: 100px;
}

img {
   display: center;
   width: 100px;
   height: 120px;
}

#classbar {
   list-style: none;
   width: 65%;
   height: 30px;
   /* border: 1px dotted black; */
   margin-left: 150px;
   /* margin-right: auto; */
   margin-top: 10px;
}

#classbar02 {
   list-style: none;
   margin-top: -47px;
   margin-left: 950px;
}

.class-item01 {
   display: inline;
   margin-left: 5px;
}

.class-item02 {
   display: inline;
   margin-left: 5px;
}

#button {
   width: 180px;
   margin-left: 950px;
   margin-top: -45px;
}

.titlebar ul#classbar li {
   display: inline;
   padding-left: 120px;
}

.notice {
   position: relative;
   left: 30px;
   top: 1px
}

#name {
   magin: 10px;
   text-align: center;
}

#insert { /* position: absolute; */
   
}
</style>


<script type="text/javascript"
   src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">

var lastScrollTop = 0;
var page =1;

$(window).scroll(function() {//1

               var currentScrollTop = $(window).scrollTop();

               if (currentScrollTop - lastScrollTop > 0) {//2
                  //downscroll

                  if ($(window).scrollTop() >= ($(document).height() - $(window).height())) {//3 현재 스크롤의 위치가 화면의 보이는 위치보다 크다면
                      page++; 
                           getDown(page);
                             
                             
                     }//3
                   }//2
   })//1
            
            function getDown(page){
                       
                        var page = {"page" : page}
                        
               $.ajax({//4
                        type : 'post',
                        url : '/children/childlistajaxdown.do',
                        dataType:'json',
                        data : page,//
                        success : function(data) {//5
                           
                           var str = "";
                           
                           if (data != "") {//6
                              
                              
                              $(data).each(//7
                                                       function(){//8
                                                   
                                                          str +=   "<div class="+'"child"'+">"
                                                      +"<input type="+'"hidden"' + "name="+'"children_seq"'+" value="+this.children_seq+"/>" 
                                                      +"<input type="+'"hidden"'+ "name="+'"class_seq"'+" value="+this.class_seq+"/>"
                                                      +"<div class="+'"section01"'+" style="+'"text-align: center;"'+">"
                                                      +"<img src =\""+this.children_img + "\" onerror=this.src='/resources/image/default.png'>"
                                                   
                                                      +"</div>"
                                                      +"<div class="+'"section02"'+">"
                                                      +"<p id="+'"name"'+">"+this.class_name+"</p>"
                                                      +"<p style="+'"text-align: center;"'+">"+this.children_name +"어린이"+"</p>"
                                                      +"<input type="+'"button"' + "value="+'"사진Up"'+" class="+'"img"'+ "onclick="+"location.href="+"'/portrait/portraitChildUpload.do?school_seq="+this.school_seq+'&children_seq='+this.children_seq+"' \/>"
                                                      +"<input type="+'"button"' + "value="+'"알림장"'+" onclick="+'"openNote();"'+" class="+'"notice"'+"/>"
                                                      +"</div>"
                                                      +"</div>";
                                                       } 
                                                             )}
                            
                              $(".child:last").after(str);
                                     }//6
                           }
                     )}
   
                        
</script>

</head>

<body>

   <section>
      <jsp:include page="../common/TeacherHeader.jsp" />
      <div id="body">
   <h2 align="center" style="margin-top: 80px;"></h2>
         <div class="titlebar">
            <ul id="classbar">
               <li class="class-item"><a href="#"></a></li>
               <li class="class-item"><a href="#"></a></li>
               <li class="class-item"><a href="#"></a></li>
            </ul>
            <ul id="classbar02">

            </ul>

         </div>
       

         <div class="up">
             
            <c:choose>
               <c:when test="${empty list }">
                  <p>no child</p>
               </c:when>
               <c:otherwise>
                  <c:forEach items="${list }" var="dto" begin="${dto.children_seq }"
                     step="1">
                     <div class="child">

                        <input type="hidden" name="children_seq" value="${dto.children_seq}"> <input type="hidden"
                           name="class_seq" value="${dto.class_seq }">
                        <div class="section01" style="text-align: center;">
                           <img src="${dto.children_img }" onerror="this.src='/resources/image/default.png'">
                        </div>
                        <div class="section02">
                           <p id="name">${dto.class_name}</p>
                           <p style="text-align: center;">${dto.children_name }어린이</p>
                           <input type="button" value="사진Up" class="img" onclick="location.href='/portrait/portraitChildUpload.do?school_seq=${dto.school_seq }&children_seq=${dto.children_seq }'">
                           <input type="button" value="알림장" onclick="openNote();"
                              class="notice">

                           <script type="text/javascript">
                           //알림장 팝업띄우기
                           function openNote(){
                              var openWin;
                              var popUrl = "/note/note.do?member_seq="+${dto.member_seq}+"&children_seq="+${dto.children_seq};
                              var popOption = "width=725px, height=800px, left=100, top=50";
                              openWin = window.open(popUrl, "NoteForm", popOption);
                           }
                           
                              
                           </script>
                        </div>
                     </div>
                  </c:forEach>
               </c:otherwise>
            </c:choose>
         </div>
      </div>

     <footer></footer>

   </section>

</body>
</html>