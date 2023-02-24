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
            <h1>재고관리</h1>
            <div class="recycle-div1"></div>
            <form action="${path }/productStock" method="GET">
                <table class="table">
                    <thead>
                        <tr>
                            <th colspan="2">상품찾기</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th>상품종류</th>
                            <td>
                                <label for="fruit"><input type="radio" id="fruit" name="productCategoryNum" value="1" checked>과일</label>
                                <label for="vegetable"><input type="radio" id="vegetable" name="productCategoryNum" value="2">채소</label>
                            </td>
                        </tr>
                        <tr>
                            <th>상품명</th>
                            <td><input id="productName" type="text" placeholder="상품명을 입력해주세요." size="60px" name="productName"></td>
                        </tr>
                    </tbody>
                </table>
                <div class="find-btn">
                    <button id="productsearch" type="submit" class="btn find-btn1">조회하기</button>
                    <button type="reset" class="btn find-btn1">취소</button>
                </div>
                <div class="recycle-div1"></div>
            </form>
	           	<c:if test="${not empty product }">
		            <table class="table">
		                <thead>
		                    <tr>
		                        <th scope="col">상품번호</th>
		                        <th scope="col">상품명</th>
		                        <th scope="col">상품가격</th>
		                        <th scope="col">재고수량</th>
		                        <th scope="col">할인율</th>
		                        <th scope="col">등록일자</th>
		                        <th scope="col">수정일자</th>
		                        <th scope="col">-</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <tr>
		                        <th>${product.productNum}</th>
		                        <td><a href="${path }/product/update?productCategoryNum=${product.productCategoryNum}&productName=${product.productName}&productNum=${product.productNum}">${product.productName }</a></td>
		                        <td>${product.productPrice }</td>
		                        <td>5kg ${product.productAmount }box</td>
		                        <td>${product.discount }%</td>
		                        <td>${product.createDate }</td>
		                        <td>${product.modifyDate }</td>
		                        <td>
		                        	<input type="hidden" name="productCategoryNum" value="${product.productCategoryNum}">
		                            <input type="hidden" name="productName" value="${product.productName}">
		                            <input type="hidden" name="productNum" value="${product.productNum }">
		                        </td>
		                     </tr>
		                     <tr>
		                     	<td></td>
		                     	<td></td>
		                     	<td></td>
		                     	<td>
					           	 	<form action="${path }/productstockupdate" method="GET">
					            		<input type="hidden" name="productCategoryNum" value="${product.productCategoryNum}">
				                        <input type="hidden" name="productName" value="${product.productName}">
				                        <input type="hidden" name="productNum" value="${product.productNum }">
					                    <button type="submit" class="btn find-btn1">재고등록</button>
					           	 	</form>
		                     	</td>
		                     	<td>
					            	<form action="${path }/product/update" method="GET">
					           	 		<input type="hidden" name="productCategoryNum" value="${product.productCategoryNum}">
				                        <input type="hidden" name="productName" value="${product.productName}">
				                        <input type="hidden" name="productNum" value="${product.productNum}">
					                    <button type="submit" class="btn find-btn1" name="update" value="update">수정하기</button>
					                </form>
		                     	
		                     	</td>
		                     	<td>
<%-- 		                            <form action="${path }/product/delete" method="GET"> --%>
<%-- 					                	<input type="hidden" name="productNum" value="${product.productNum}"> --%>
<%-- 				                        <input type="hidden" name="productName" value="${product.productName}"> --%>
<!-- 					                    <button id="btnDelete" type="submit" class="btn find-btn1" name="update" value="delete">상품삭제</button> -->
<!-- 					                </form> -->
		                     	</td>
		                     	<td></td>
		                     	<td></td>
		                     	<td></td>
		                     </tr>
		                 </tbody>
		             </table>
		          </c:if>
				</div>
		        <div id="wrap-div3"></div>
		    </div>
            <jsp:include page="/views/common/footer.jsp" />
            
<script type="text/javascript">
    
$('#productsearch').click(function() {
    if (!$('#productName').val()) {
        alert('상품명을 입력해주세요.');
        return false;
    }
})

</script>
</body>
</html>