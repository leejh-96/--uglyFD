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
<title>관리자 페이지</title>
<link rel="stylesheet" href="${path }/resources/css/common_css/uglyFD-main.css">
<link rel="stylesheet" href="${path }/resources/css/uglyFD-components/uglyFD-recycle.css">
<link rel="stylesheet" href="${path }/views/admin/admin_css/admin_page.css">
</head>
<body>
	<jsp:include page="/views/common/header.jsp" />
	
	<div id="admin-wrap">
        <div id="wrap-div1">
            <div><a href="${path }/views/admin/admin_members.jsp" class="admin-sidenav">회원관리</a></div>
            <div><a href="${path }/views/admin/admin_product.jsp" class="admin-sidenav">상품등록</a></div>
            <div><a href="${path }/views/admin/admin_product_detail.jsp" class="admin-sidenav">재고관리</a></div>
            <div><a href="${path }/productinout" class="admin-sidenav">입/출고조회</a></div>
        </div>
       <div id="wrap-div2">
            <div class="recycle-div1"></div>
            <h1>회원수정</h1>
            <div class="recycle-div1"></div>
            <form action="${path }/admin/member/update" method="POST">
                <table class="table">
	                <thead>
	                    <tr>
	                        <th scope="col">회원아이디</th>
	                        <th><input type="text" placeholder="${amember.id }" size="40px" name="id" required></th>
	                    </tr>
	                    <tr>
	                        <th scope="col">회원비밀번호</th>
	                        <th><input type="text" placeholder="${amember.password }" size="40px" name="password" required></th>
	                    </tr>
	                    <tr>
	                        <th scope="col">회원주소</th>
	                        <th><input type="text" placeholder="${amember.addr }" size="40px" name="addr" required></th>
	                    </tr>
	                    <tr>
	                        <th scope="col">회원이메일</th>
	                        <th><input type="text" placeholder="${amember.mail }" size="40px" name="mail" required></th>
	                    </tr>
	                    <tr>
	                        <th scope="col">회원전화번호</th>
	                        <th><input type="text" placeholder="${amember.phone }" size="40px" name="phone" required></th>
	                    </tr>
	                    <tr>
	                        <th scope="col">등록일자</th>
	                        <th>${amember.createDate }</th>
	                    </tr>
	                    <tr>
	                        <th scope="col">수정일자</th>
	                        <th>${amember.updateDate }</th>
	                    </tr>
	                </thead>
	              </table>
                <div class="find-btn">
                	<input type="hidden" name="memberNo" value="${amember.no }">
                    <button type="submit" class="btn find-btn1">수정하기</button>
                    <button onclick="history.go(-1);" type="reset" class="btn find-btn1">이전으로</button>
                </div>
            </form>

		            </div>
		        <div id="wrap-div3"></div>
		    </div>
            
            <jsp:include page="/views/common/footer.jsp" />
	
	<script>
// 	<a href="javascript:history.go(-1)">
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	</script>
	
</body>
</html>