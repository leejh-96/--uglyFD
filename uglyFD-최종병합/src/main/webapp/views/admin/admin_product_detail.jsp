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
#productName{
	border: 2px solid; red; 
	border-radius: 5px;
}
#find-btn{
	margin-bottom: 30px;
}
.form-inline{
	display: inline-block;
	text-align: center;
}
.form-inline-first{
	margin-left: 421px;

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
	            <h1>재고등록 / 상품수정</h1>
	        <div class="div-h1"></div>
	            <form action="${path }/productStock" method="GET">
	                <table class="table">
	                    <thead>
	                        <tr>
	                            <th colspan="4">상품검색</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                        <tr>
	                            <th>상품종류</th>
	                            <td>
	                                <label for="fruit"><input type="radio" id="fruit" name="productCategoryNum" value="1" checked>과일</label>
	                                <label for="vegetable"><input type="radio" id="vegetable" name="productCategoryNum" value="2">채소</label>
	                            </td>
	                            <th>상품명</th>
	                            <td>
	                            	<input id="productName" type="search" placeholder="상품명을 입력해주세요." size="60px" name="productName">
	                            	<i class="fa fa-search" id="sch"></i>
	                            </td>
	                        </tr>
	                        <tr>
	                        </tr>
	                    </tbody>
	                </table>
		                <div class="find-btn" id="find-btn">
		                    <button id="productsearch" type="submit" class="btn find-btn1" style="background-color: rgb(255, 244, 164);">조회하기</button>
		                </div>
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
		                    </tr>
		                </thead>
		                <tbody>
		                    <tr>
		                        <th>${product.productNum}</th>
		                        <td><a href="${path }/product/update?productCategoryNum=${product.productCategoryNum}&productName=${product.productName}&productNum=${product.productNum}">${product.productName }</a></td>
		                        <td>${product.productPrice }(원)</td>
		                        <td>5kg ${product.productAmount }box</td>
		                        <td>${product.discount }%</td>
		                        <td>${product.createDate }</td>
		                        <td>${product.modifyDate }</td>
		                     </tr>
		                 </tbody>
		             </table>
		             	 <form action="${path }/productstockupdate" method="GET" class="form-inline form-inline-first">
					           <input type="hidden" name="productCategoryNum" value="${product.productCategoryNum}">
				               <input type="hidden" name="productName" value="${product.productName}">
				               <input type="hidden" name="productNum" value="${product.productNum }">
					           <button type="submit" class="btn find-btn1" style="background-color: rgb(255, 244, 164);">재고등록</button>
					     </form>
					     <form action="${path }/product/update" method="GET" class="form-inline">
					           <input type="hidden" name="productCategoryNum" value="${product.productCategoryNum}">
				               <input type="hidden" name="productName" value="${product.productName}">
				               <input type="hidden" name="productNum" value="${product.productNum}">
					           <button type="submit" class="btn find-btn1" name="update" value="update" style="background-color: rgb(255, 244, 164);">수정하기</button>
					     </form>
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