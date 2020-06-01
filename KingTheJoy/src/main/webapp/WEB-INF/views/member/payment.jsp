<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

#pay{
	
	width: 500px;
	height: 500px;

}

</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript">
	$(function() {
		$('#payment').click(function() {
			var IMP = window.IMP; // 생략가능
			IMP.init('imp94660535'); // 가맹점 식별코드 사용
			var msg;
			var selectCount = $('#selectCount').val();

			IMP.request_pay({
				pg : 'kakaopay',
				pay_method : 'card',
				merchant_uid : 'merchant_' + new Date().getTime(),
				name : '주문명:학원비',
				amount : 140000*selectCount,
				buyer_email : '010-0000-0000',
				buyer_name : '정은주',
				buyer_tel : '010-0000-0000',
				buyer_addr : '서울시 강남구 kh',
				buyer_postcode : '123-456',
			}, function(rsp) {
				if (rsp.success) {
					//[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
					jQuery.ajax({
						url : "/payments/complete",
						type : 'POST',
						dataType : 'json',
						data : {
							imp_uid : rsp.imp_uid
						//기타 필요한 데이터가 있으면 추가 전달
						}
					}).done(function(data) {
						//[2] 서버에서 REST API로 결제정보확인 및 서비스 정상적인 경우
						if (everythings_fine) {
							msg = '결제가 완료되었습니다.';
							msg += '\n고유ID : ' + rsp.imp_uid;
							msg += '\n상점 거래ID : ' + rsp.merchant_uid;
							msg += '\결제 금액 : ' + rsp.paid_amount;
							msg += '카드 승인번호 : ' + rsp.apply_num;

							alert(msg);
						} else {
							//[3] 아직 제대로 결제가 되지 않았습니다.
							//[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
						}
					});
					//성공시 이동할 페이지 있으면 추가로 넣기 현재x
					if (rsp.imp_uid != null) {
						alert('결제 완료');
						$('#payment').hide();
						$('#payResult').attr({"type":"button"});
						
					}

				} else {
					msg = '결제에 실패하였습니다.';
					msg += '에러내용 : ' + rsp.error_msg;
					//실패시 이동할 페이지 있으면 추가로 넣기 현재x
					alert(msg);
				}
			});

		});
	});
</script>

</head>
<body>


	<div id="pay">
		<h1>결제하기</h1>
		<br>
		<table>
			<colgroup>
				<col width="100px;">
				<col width="100px;">
				<col width="100px;">
				<col width="100px;">
			</colgroup>
		
			<tr>
				<th>항목</th>
				<th>금액</th>
				<th>개수</th>
				<th>결과</th>
			</tr>
			<tr>
				<td>6월 학원비</td>
				<td>140,000원</td>
				<td>
					<select id="selectCount" class="btn btn-outline-dark dropdown-toggle">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
					</select>
				</td>
				<td>
					<input type="button" value="결제" id="payment" class="btn btn-primary">
					<input type="hidden" id="payResult" value="결제완료">
				</td>
			</tr>
		</table>
	</div>

</body>
</html>