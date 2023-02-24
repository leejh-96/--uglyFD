 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<link rel="stylesheet" href="${path }/resources/css/board/cuscenter.css">
<link rel="stylesheet" href="${path }/resources/css/common_css/uglyFD-main.css">
<!-- uglyFD-figure / section 부분 script -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script> 
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />
  
  
  <body>

  <jsp:include page="/views/common/header.jsp" />
	  <br><br>
    <div class="area">
      <br />
      <h1>고객 센터</h1>
      <h3>아래의 연락처로 연락하기</h3>
      <p>
        평일 : AM 10:00 ~ PM 17:50<br />
        점심시간 : PM 12:00 ~ PM 13:00<br /><br />
        공휴일 휴무
      </p>

      <br /><br /><br />
      <div class="custom_header">
        <div class="custom_phone">
          <span class="material-icons md-48" id="icon1">phone_iphone </span>
          <p class="custom_content1">전화 상담</p>
          <p class="custom_content">010-7676-8442</p>
        </div>
        <div class="custom_email">
          <span class="material-icons md-48" id="icon1">mail_outline</span>
          <p class="custom_content1">이메일 상담</p>
          <p class="custom_content">gksthe@naver.com</p>
        </div>
        <a href="#" class="custom_a">
            <div class="custom_kakao">
                <span class="material-icons md-48" id="icon1"
                >chat_bubble_outline</span
                >
                <p class="custom_content1">카카오톡 상담</p>
                <p class="custom_content">ID : 123</p>
            </a>
        </div>
      </div>
    </div>
    <jsp:include page="/views/common/footer.jsp"/>
  </body>