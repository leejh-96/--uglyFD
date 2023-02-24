<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- uglyFD-footer -->
    <footer>
        <hr>
        <div id="footer-div1">
            <div>
                <img src="${path }/resources/images/ugly-logo.png" alt="uglyFD-logo">
            </div>
            <div>
                <table>
                    <tr>
                        <td>월-목요일 </td>
                        <td>11:00 ~ 18:00(점심시간 12:00 ~ 13:00)</td>
                    </tr>
                    <tr>
                        <td>금요일 </td>
                        <td>09:00 ~ 18:00(점심시간 12:00 ~ 13:00)</td>
                    </tr>
                    <tr>
                        <td>토·일·공휴일 </td>
                        <td>휴무</td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="footer-div2">
            <p>상호명: 주식회사 어글리 푸드 대표 : 어글리 푸드  사업자등록번호 : 572-81-02284 소재지 : 강남지원 1관 : 서울특별시 강남구 테헤란로 14길 6 남도빌딩 2F,3F,4F,5F,6F</p>
            <p>서비스 이용약관   개인정보 처리방침  Copyright  2023 주식회사 어글리 푸드 All rights reserved.</p>
        </div>
    </footer>
</body>
</html>