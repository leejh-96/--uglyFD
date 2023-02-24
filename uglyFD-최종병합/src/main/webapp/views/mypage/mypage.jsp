<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" href="${path }/views/mypage/mypage.css">
<link rel="stylesheet" href="${path }/resources/css/common_css/uglyFD-main.css">
<!-- uglyFD-figure / section 부분 script -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<jsp:include page="/views/common/header.jsp" />
	
		<div class="c_shop">
    <div class="title">
        <img style="vertical-align:-1px;"src="${path }/resources/images/mypage.png" width="3%" height="40px">   My Page
    </div>
</div> 


<%-- <c:if test="${ empty loginmember }"> --%>
<%-- <div>${ loginmember.name } 님 환영합니다!</div> --%>
<%-- </c:if> --%>
				

<c:if test="${ not empty loginmember }">
<div>${ loginmember.name } 님 환영합니다!</div>
</c:if>
<hr>

<br><br>
<div class="row" style="width:80%; margin-left:10%; border-radius: 15px; border: 3px solid rgba(249, 219, 99, 0.54); ">
&nbsp;&nbsp;&nbsp;&nbsp;주문/배송조회
<div class="col align-self-start">

 <br><br><a href="">0</a>  <br>  결제 완료
</div>
<div class="col align-self-start">
<br><br><a href="https://search.naver.com/search.naver?where=nexearch&sm=top_sug.pre&fbm=1&acr=1&acq=%EC%9A%B4%EC%86%A1%EC%9E%A5&qdt=0&ie=utf8&query=%EC%9A%B4%EC%86%A1%EC%9E%A5%EB%B2%88%ED%98%B8+%EC%A1%B0%ED%9A%8C">0</a>  <br>   배송중
</div>
<div class="col align-self-start">
 <br><br><a href="${path }/mypage/myview">0</a> <br> 구매후기
</div>

</div>
<br><br>

<div>
<div class="c-fo">
    <div class="first" style="border: 3px solid rgba(249, 219, 99, 0.54); border-radius: 15px;"><a href="${path }/mypage/mycart" style="color:black;" >장바구니</a></div>


    <div class="second"style="border: 3px solid rgba(249, 219, 99, 0.54); border-radius: 15px;"><a href="${path }/mypage/myrevise" style="color:black;">회원수정</a></div>
  
 <div class="c-fo1">
    <div class="third1" style="border: 3px solid rgba(249, 219, 99, 0.54); border-radius: 15px;"><a  href="${path }/mypage/myboard" style="color:black;">나의문의</a></div>
    </div>
</div>
</div>

	


</body>
	<jsp:include page="/views/common/footer.jsp" />
</html>