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
<script src="https://kit.fontawesome.com/ecdfb9b41a.js"></script>
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
</head>
<body>
	<!-- uglyFD-header -->
    <header>
        <div id="header-divwrap">
            <div class="divwrap-sub"><a class="a-inline-block1" href="${path }"><img src="${path }/resources/images/ugly-logo.png" alt="uglyFD-logo"></a></div>
            <div class="divwrap-sub"></div>
            <div class="divwrap-sub"><a class="a-inline-block1 uglyFD-title" href="${path }/main/home.do">uglyFD</a></div>
            <div class="divwrap-sub"></div>
            <div class="divwrap-sub">

                <!-- 비로그인 시 처리 -->
                <c:if test="${ empty loginMember }">
                 <div class="divwrap-sub5">
                <form action="${ path }/login" method="get">                
                    <a href="${path }/login"><button class="btn-primary btn-sm">로그인</button></a>
                </form>
                    <a href="${path }/enroll"><button class="btn-primary btn-sm">회원가입</button></a>
                       
                </div> 
                </c:if>
                
<!--                 로그인 시-회원 처리 -->
				<c:if test="${ not empty loginMember }">
					<c:if test="${loginMember.grade == 2 }">
		                <div class="divwrap-sub5">
		                    <button class="sub5-btn"><a href="${path }"><i class="fa-solid fa-user fa-lg"></i></a></button>
		                    <button class="sub5-btn"><a href="${path }"><i class="fa-sharp fa-solid fa-cart-shopping fa-lg"></i></a></button>
		                    <span class="badge-absolute badge">0</span>
		                    <button onclick="location.replace('${ path }/logout')" class="btn-primary btn-sm" >로그아웃</button>
		                </div>
		            </c:if>
				</c:if>

<!--                 로그인 시-관리자 처리 -->
				<c:if test="${loginMember.grade == 1 }">
	                <div class="divwrap-sub5">
	                    	<button class="sub5-btn"><a href="${path }/views/admin/admin_page.jsp"><i class="fa-solid fa-user-secret fa-lg">-MANAGER-</i></a></button>
	                    <form action="${path }" method="GET">
	                        <button onclick="location.replace('${ path }/logout')" class="btn-primary btn-sm">로그아웃</button>
	                    </form>
	                </div> 
                </c:if>

            </div>
        </div>
        <nav id="header-navwrap">
            <ul id="navwrap-ul">
                <li>
                    <a href="${path }">상품</a>
                    <ul>
                        <li><a href="${path }/product/category?productCategoryNum=2">채소</a></li>
                        <li><a href="${path }/product/category?productCategoryNum=1">과일</a></li>
                    </ul>
                </li>
                <li><a href="${path }">공지사항</a></li>
                <li><a href="${path }/board/inquire">1 : 1 문의</a></li>
                <li>
                    <a href="${path }/board/cuscenter">고객센터</a>
                </li>
            </ul>
        </nav>
    </header>
</body>
</html>