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
            <h1>상품등록</h1>
            <div class="recycle-div1"></div>
            <form action="${path }/product/register" method="POST" enctype="multipart/form-data">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="row" colspan="4">상품정보입력</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row" colspan="2">상품종류</th>
                            <td>
                                <label for="fruit"><input type="radio" id="fruit" name="productCategoryNum" value="1" checked>과일</label>
                                <label for="vegetable"><input type="radio" id="vegetable" name="productCategoryNum" value="2">채소</label>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">상품명</th>
                            <td><input id="pName" type="text" placeholder="상품명을 입력해주세요." size="60px" name="productName" required></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">판매가</th>
                            <td><input id="pPrice" type="text" placeholder="숫자만 입력해주세요." size="60px" name="productPrice" required></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">할인율</th>
                            <td><input id="pDiscount" type="hidden" name="discount" value="20">20%</td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">첨부파일</th>
                            <td><input id="pFile" type="file" size="60px" name="upfile" required></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">상세설명</th>
                            <td><textarea id="pDetail" name="productDetail"  cols="63" rows="5" style="resize:none;" required></textarea></td>
                        </tr>
                    </tbody>
                </table>
                <div class="find-btn">
                    <button id="productInsert" type="submit" class="btn find-btn1">등록하기</button>
                    <button type="reset" class="btn find-btn1">취소</button>
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