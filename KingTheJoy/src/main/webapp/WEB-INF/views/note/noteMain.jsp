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
</head>

<!-- 팝업으로 띄우기 -->

<body>

	<h1>알림장 보내기</h1>
	<form action="noteInsert.do?" method="post">
	<input type="hidden" name="member_seq" value="21">
	<input type="hidden" name="children_seq" value="7">
        <fieldset>
            <legend>기본입력</legend>
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
                    <th>체온</th>
                    <td>
                        <input type="range" name="note_temp" min="35.0" max="39.0" step="0.5">
                    </td>
                </tr>
                <tr>
                    <th>식사여부</th>
                    <td>
                        <input type="text" name="note_meal">
                    </td>
                </tr>
                <tr>
                    <th>수면시간</th>
                    <td>
                        <input type="range" name="note_sleep" id="" min="0" max="10">
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
            </table>
        </fieldset>
        <fieldset>
            <legend>특이사항</legend>
            <textarea name="note_spec" id="" cols="30" rows="10"></textarea>
        </fieldset>
        <input type="submit" value="보내기">
        <input type="button" value="취소" onclick="self.close();">

    </form>

</body>
</html>