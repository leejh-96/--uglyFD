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
#pDetail{
	border: 1.5px solid black;
	border-radius: 5px;
}
.div-h1{
	width: 100%;
	height: 50px;
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
            <h1>상품등록</h1>
            <div class="div-h1"></div>
            <form action="${path }/product/register" method="POST" enctype="multipart/form-data">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="row" colspan="4">상품정보입력</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row" colspan="2" style="color: green;">상품종류</th>
                            <td>
                                <label for="fruit"><input type="radio" id="fruit" name="productCategoryNum" value="1" checked>과일</label>
                                <label for="vegetable"><input type="radio" id="vegetable" name="productCategoryNum" value="2">채소</label>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2" style="color: green;">상품명</th>
                            <td style="color: green;">
                            	<input id="pName" type="text" placeholder="상품명을 입력해주세요." size="60px" name="productName" required>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2" style="color: green;">판매가</th>
                            <td style="color: green;">
                            	<input id="pPrice" type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" placeholder="숫자만, EX)10000 형식으로 입력해주세요." size="60px" name="productPrice" required>
                            	(원)
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2" style="color: green;">할인율</th>
                            <td><input id="pDiscount" type="hidden" name="discount" value="20" style="color: green;">20%</td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2" style="color: orange;">첨부파일</th>
                            <td style="color: orange;"><input id="pFile" type="file" size="60px" name="upfile" required style="color: orange;"></td>
                        </tr>
                        <tr>
		                    <th scope="row" colspan="2" style="color: green;">상품소개</th>
		                    <th style="color: green;">
		                        <textarea id="pDetail" placeholder="간략한 상품소개 작성" name="productDetail" id="" cols="63" rows="5" style="resize:none;" required></textarea>
		                    </th>
		                </tr>
                    </tbody>
                </table>
                <div class="find-btn">
                    <button id="productInsert" type="submit" class="btn find-btn1" style="background-color: rgb(255, 244, 164);">등록하기</button>
                    <button onclick="history.go(-1);" type="reset" class="btn find-btn1" style="background-color: rgb(255, 244, 164);">이전으로</button>
                </div>
            </form>
		            </div>
		        <div id="wrap-div3"></div>
		    </div>
            
            <jsp:include page="/views/common/footer.jsp" />
	
	<script>
	
	$('#productInsert').click(function() {
	    if (!$('#pName').val()) {
	        alert('상품이름을 입력해주세요.');
	        return false;
	    }
	    if (!$('#pPrice').val()) {
	        alert('상품가격을 입력해주세요.');
	        return false;
	    }
	    if (!$('#pFile').val()) {
	        alert('상품이미지 파일을 등록해주세요.');
	        return false;
	    }
	    if (!$('#pDetail').val()) {
	        alert('상품에 대한 소개를 입력해주세요.');
	        return false;
	    }
	    if (!$('#fruit').val()) {
			alert('한가지만 선택해주세요.');
			return false;
		}
	
	</script>
	
</body>
</html>