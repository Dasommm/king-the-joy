<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/resist.js"/>"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/myPage.css"/>">

<style type="text/css">

	#mainbody{
		height: 850px;
	}
	
	#paymentDiv{
		position: relative;
		top: -300px;
		left: 774px;
		height: 500px;
		width: 500px;
	}

</style>

</head>
<body>
<%@include file="../common/parentHeader.jsp" %>
<section id="mainbody">
	<div class="container">
		<div class=".col-sm-4 .col-md-offset-2">
			<div class="panel panel-default panel-info Profile">
				<div class="panel-body">
					<div class="form-horizontal">
						<form> 
							<div class="form-group">
								<label class="col-sm-2 control-label">아이디</label>
								<div class="col-sm-5">
									<input class="form-control" type="text" name="member_id"
										value="${sessionScope.memberDto.getMember_id()}" readonly>
								</div>
								
								<div class="col-sm-6 right">
								<h1>자녀정보</h1>
										<table>
											<tr><th>자녀 이름</th></tr>
											<c:forEach items="${childrenList }" var = "childrenList">
												<tr>
													<td>
														  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
														   	<c:out value="${childrenList.children_name }"/>
														  </button>
													</td>
												</tr>
											</c:forEach>
										</table>
								</div>
								
								
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">이름</label>
								<div class="col-sm-5">
									<input class="form-control" type="text" name="member_name"
										 value="${sessionScope.memberDto.getMember_name()}" readonly>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">이메일</label>
								<div class="col-sm-5">
									<input class="form-control" type="text" name="member_email"
										 value="${sessionScope.memberDto.getMember_email()}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">전화번호</label>
								<div class="col-sm-5">
									<input class="form-control" type="text" name="member_phone"
										value="${sessionScope.memberDto.getMember_phone()}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">주소</label> 
								 <button type="button" class="btn btn-default" onclick="execPostCode();"><i class="fa fa-search"></i> 우편번호 찾기</button>
								<div class="col-sm-5"> 
									<input class="form-control" type="text" name="member_oaddr"
										value="${sessionScope.memberDto.getMember_oaddr()}">
									<input class="form-control" type="text" name="member_addr"
										value="${sessionScope.memberDto.getMember_addr()}">
									<input class="form-control" type="text" name="member_detailaddr"
										value="${sessionScope.memberDto.getMember_detailaddr()}">
								</div>
							</div> 
							<div class="form-group">
								<label class="col-sm-2 control-label">회원 분류</label>
								<div class="col-sm-5">
									<c:choose>
										<c:when test="${sessionScope.memberDto.getMember_role() eq 0}">
											<input class="form-control" type="text" name="member_rodle" value="관리자" readonly>
										</c:when>
										<c:when test="${sessionScope.memberDto.getMember_role() eq 1}">
											<input class="form-control" type="text" name="member_rodle" value="원장님" readonly>
										</c:when>
										<c:when test="${sessionScope.memberDto.getMember_role() eq 2}">
											<input class="form-control" type="text" name="member_rodle" value="선생님" readonly>
										</c:when>
										<c:when test="${sessionScope.memberDto.getMember_role() eq 3}">
											<input class="form-control" type="text" name="member_rodle" value="학부모" readonly>
										</c:when>
									</c:choose> 
								</div>
								
							</div>
								
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button class="btn btn-primary">회원 수정</button>
									<button class="btn btn-primary">회원 탈퇴</button>
								</div>
							</div>
						</form>
						
					
					</div>
					<!-- end form-horizontal -->
				</div>
				<!-- end panel-body -->

			</div>
			<!-- end panel -->


		</div>
		<!-- end size -->
	</div>
	
	<div id="paymentDiv">
		<%@ include file="../member/payment.jsp" %>
	</div>
	
	  <!-- The Modal -->
  <div class="modal" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">유치원 정보</h4>
           	<button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          	유치원 정보 들어갈 곳
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          
          <button type="button" class="btn btn-danger" data-dismiss="modal">유치원 정보 수정</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
        </div>
        
      </div>
    </div>
  </div>
</section>
  
<%@include file="../common/footer.jsp" %>
</body>
</html>