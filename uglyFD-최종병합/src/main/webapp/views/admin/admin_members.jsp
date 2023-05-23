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
<style>
.div-h1{
	width: 100%;
	height: 50px;
}
.find-btn{
	margin-bottom: 15px;
}
#wrap-div1 a{
	text-decoration: none;
}
</style>
<body>
	<jsp:include page="/views/common/header.jsp" />
<div id="admin-wrap">
        <div id="wrap-div1">
            <div><a href="${path }/views/admin/admin_members.jsp" class="admin-sidenav"><i class="fa-solid fa-users"></i> 회원관리</a></div>
            <div><a href="${path }/views/admin/admin_product.jsp" class="admin-sidenav"><i class="fa-solid fa-crown"></i> 상품등록</a></div>
            <div><a href="${path }/views/admin/admin_product_detail.jsp" class="admin-sidenav"><i class="fa-solid fa-otter"></i> 재고등록 / 상품수정</a></div>
            <div><a href="${path }/productinout" class="admin-sidenav"><i class="fa-brands fa-waze"></i> 입/출고내역</a></div>
        </div>

        <div id="wrap-div2">
            <div class="div-h1"></div>
            <h1>회원관리</h1>
            <div class="div-h1"></div>
            <!-- 회원관리-회원 검색을 위한 form -->
            <form action="${path }/admin/member" method="POST" >
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="row" colspan="4">회원정보입력</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row" colspan="2">이름</th>
                            <td><input id="memberName" type="text" placeholder="회원이름을 입력해주세요." size="60px" name="name" required></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">아이디</th>
                            <td><input id="memberId" type="text" placeholder="4~12자의 영문 대소문자와 숫자로만 입력" size="60px" name="id" required ></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">전화번호</th>
                            <td><input id="memberPhone" type="text"  oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" placeholder=" ' - ' 제외한 숫자만 기입해주세요." size="60px" name="phone" required></td>
                        </tr>
                    </tbody>
                </table>
                <div class="find-btn">
                    <button id="memberSearch" type="submit" class="btn find-btn1 find-btn" style="background-color: rgb(255, 244, 164);">조회하기</button>
                </div>
            </form>
                <div class="recycle-div1"></div>
            <!-- 회원관리-회원 검색을 위한 form 끝-->
            <!-- 회원관리-검색한 회원을 출력하는 table -->
            <c:if test="${not empty amember }">
		            <table class="table">
		                <thead>
		                    <tr>
		                        <th>번호</th>
		                        <th>이름</th>
		                        <th>아이디</th>
		                        <th>비밀번호</th>
		                        <th>생년월일</th>
		                        <th>주소</th>
		                        <th>전화번호</th>
		                        <th>등록날짜</th>
		                        <th></th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <tr>
		                        <th>${amember.no}</th>
		                        <td>${amember.name }</td>
		                        <td>${amember.id }</td>
		                        <td>${amember.password }</td>
		                        <td>${amember.birth }</td>
		                        <td>${amember.addr }</td>
		                        <td>${amember.phone }</td>
		                        <td>${amember.createDate }</td>
		                        <td>
			                        <a href="${path }/admin/member/page?memberDelete=${amember.no}">
			                       	 	<button type="reset" class="btn find-btn1" name="memberDelete" style="background-color: rgb(255, 244, 164);">삭제하기</button>
			                        </a>
		                        </td>
		                    </tr>
		                </tbody>
		            </table>
	            <div class="find-btn">
		            <form action="${path }/admin/member/page" method="POST">
		            	<a >
		            		<input type="hidden" name="name" value="${amember.name }">
		            		<input type="hidden" name="id" value="${amember.id }">
		            		<input type="hidden" name="phone" value="${amember.phone }">
		            		<input type="hidden" name="memberNo" value="${amember.no }">
		            		<button type="submit" class="btn find-btn1" style="background-color: rgb(255, 244, 164);">회원정보수정</button>
		            	</a>
		            </form>
                </div>
            </c:if>
            <!-- 회원관리-검색한 회원을 출력하는 table 끝-->
        </div>
        <div id="wrap-div3"></div>
    </div>
    <jsp:include page="/views/common/footer.jsp" />
	
	<script>
	
	$('#memberSearch').click(function() {
	    if (!$('#memberName').val()) {
	        alert('회원이름을 입력해주세요.');
	        return false;
	    }
	    if (!$('#memberId').val()) {
	        alert('회원아이디를 입력해주세요.');
	        return false;
	    }
	    if (!$('#memberPhone').val()) {
	        alert('상품이미지 파일을 등록해주세요.');
	        return false;
	    }
	    
	</script>
	
</body>
</html>