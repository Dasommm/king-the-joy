<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="/resources/css/note/noteForm.css">

<script type="text/javascript" src="/resources/js/note/noteForm.js"></script>

</head>

<!-- 팝업으로 띄우기 -->

<body>

	<form action="/note/noteInsert.do?" method="post" id="msform">
	<input type="hidden" name="member_seq" value="21">
	<input type="hidden" name="children_seq" value="7">

	 <!-- fieldSet 1 -->
        <fieldset>
            <table>
                <tr>
                    <th>기분</th>
                    <td>
                        <input type="radio" id="good" name="note_feeling" value="좋음">
                        <label for="good">좋음</label>
                        <input type="radio" id="norm" name="note_feeling" value="보통">
                        <label for="norm">보통</label>
                        <input type="radio" id="bad" name="note_feeling" value="나쁨">
                        <label for="bad">나쁨</label>
                    </td>
                </tr>
                <tr>
                    <th>건강</th>
                    <td>
                        <input type="radio" id="good_h" name="note_health" value="좋음">
                        <label for="good_h">좋음</label>
                        <input type="radio" id="norm_h" name="note_health" value="보통">
                        <label for="norm_h">보통</label>
                        <input type="radio" id="bad_h" name="note_health" value="나쁨">
                        <label for="bad_h">나쁨</label>
                    </td>
                </tr>
				<tr>
                    <th>배변</th>
                    <td>
                        <input type="radio" id="no_d" name="note_def" value="안했음">
                        <label for="no_d">안했음</label>
                        <input type="radio" id="norm_d" name="note_def" value="정상">
                        <label for="norm_d">정상</label>
                        <input type="radio" id="bad_d" name="note_def" value="나쁨">
                        <label for="bad_d">나쁨</label>
                    </td>
                </tr>
                <tr>
                    <th>체온</th>
                    <td>
                        <input type="range" id="range_t" name="note_temp" min="35.0" max="39.0" step="0.5"
                        onchange="handleMouseMove();">
                    	<span><span id="range-value">36.5</span>도</span>
                    </td>
                </tr>                
                <tr>
                    <th>수면시간</th>
                    <td>
                        <input type="range" id="range_n" name="note_sleep" min="0" max="10" onchange="handleMouseMoveSleep();">
                        <span><span id="range-value-sleep">5</span>시간</span>
                    </td>
                </tr>
                <tr>
                    <th>식사여부</th>
                    <td>
                        <input type="text" name="note_meal">
                    </td>
                </tr>
            </table>
            <textarea name="note_spec" id="" cols="10" rows="10" style="margin: 5px; width: 280px; height: 104px;" placeholder="특이사항이 있다면 입력해주세요"></textarea>
        <input type="submit" class="button" value="보내기">
        <input type="button" class="button gray" class="gray" value="취소" onclick="self.close();">
        </fieldset>
    </form>

</body>
</html>