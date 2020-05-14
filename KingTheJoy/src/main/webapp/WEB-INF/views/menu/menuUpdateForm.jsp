<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
   * { box-sizing:border-box; }

/* basic stylings ------------------------------------------ */
body              { background:url(https://scotch.io/wp-content/uploads/2014/07/61.jpg); }
.container       { 
  font-family:'Roboto';
  width:600px; 
  margin:30px auto 0; 
  display:block; 
  background:#FFF;
  padding:10px 50px 50px;
}
h2        { 
  text-align:center; 
  margin-bottom:50px; 
}
h2 small { 
  font-weight:normal; 
  color:#888; 
  display:block; 
}
.footer    { text-align:center; }
.footer a  { color:#53B2C8; }

/* form starting stylings ------------------------------- */
.group            { 
  position:relative; 
  margin-bottom:45px; 
}
input             {
  font-size:18px;
  padding:10px 10px 10px 5px;
  display:block;
  width:300px;
  border:none;
  border-bottom:1px solid #757575;
}
input:focus       { outline:none; }

/* LABEL ======================================= */
label              {
  color:#999; 
  font-size:18px;
  font-weight:normal;
  position:absolute;
  pointer-events:none;
  left:5px;
  top:10px;
  transition:0.2s ease all; 
  -moz-transition:0.2s ease all; 
  -webkit-transition:0.2s ease all;
}

/* active state */
input:focus ~ label, input:valid ~ label       {
  top:-20px;
  font-size:14px;
  color:#5264AE;
}

/* BOTTOM BARS ================================= */
.bar    { position:relative; display:block; width:300px; }
.bar:before, .bar:after    {
  content:'';
  height:2px; 
  width:0;
  bottom:1px; 
  position:absolute;
  background:#5264AE; 
  transition:0.2s ease all; 
  -moz-transition:0.2s ease all; 
  -webkit-transition:0.2s ease all;
}
.bar:before {
  left:50%;
}
.bar:after {
  right:50%; 
}

/* active state */
input:focus ~ .bar:before, input:focus ~ .bar:after {
  width:50%;
}

/* HIGHLIGHTER ================================== */
.highlight {
  position:absolute;
  height:60%; 
  width:100px; 
  top:25%; 
  left:0;
  pointer-events:none;
  opacity:0.5;
}

/* active state */
input:focus ~ .highlight {
  -webkit-animation:inputHighlighter 0.3s ease;
  -moz-animation:inputHighlighter 0.3s ease;
  animation:inputHighlighter 0.3s ease;
}

/* ANIMATIONS ================ */
@-webkit-keyframes inputHighlighter {
   from { background:#5264AE; }
  to    { width:0; background:transparent; }
}
@-moz-keyframes inputHighlighter {
   from { background:#5264AE; }
  to    { width:0; background:transparent; }
}
@keyframes inputHighlighter {
   from { background:#5264AE; }
  to    { width:0; background:transparent; }
}

form{
    margin-left: 17%;
}

.submitbtn{
   color: #fff;
    background-color: #2C3E50;
    border-color: #2C3E50;
    cursor: pointer;
}
</style>
<script type="text/javascript">
   function writeRes(){
      if($("input[name=title]").val() == ""){
         alert("제목을 입력해주세요.");
      }else if($("input[name=start]").val() == ""){
         alert("시작일을 입력해주세요.");
      }else if($("input[name=end]").val() == ""){
         alert("종료일을 입력해주세요.");
      }else{
         updateresform.target="menuParentForm";
         updateresform.submit();
         self.close();
      }
   }

   $(function(){
      $(".submitbtn").hover(function(){
         $(this).css("background","#4c6177");
      },function(){
         $(this).css("background","#2C3E50");
      });
   });
</script>
</head>
<body>
<div class="container">
  
  <h2>일정 수정<small>Calendar</small></h2>
  
  <form name="updateresform" action="menuUpdateDb.do" method="post">
    <input type="hidden" name="menu_seq" value="${menuSelectOne.menu_seq }"/>
    <div class="group">      
      <input type="text" name="title" value="${menuSelectOne.title }" required>
      <span class="highlight"></span>
      <span class="bar"></span>
      <label>일정Title</label>
    </div>
      
    <div class="group">      
      <input type="date" name="start" value="${menuSelectOne.start }" required>
      <span class="highlight"></span>
      <span class="bar"></span>
      <label style="top:-17px">날짜</label>
    </div>
    <div>
    	<textarea rows="10" cols="30" name="description" >${menuSelectOne.description }</textarea>
    </div>
    
    <input type="button" onclick="writeRes();" value="수정" class="submitbtn"/>
  </form>
  
</div>


</body>
</html>