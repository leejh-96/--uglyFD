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
<title>상품페이지</title>
<link rel="stylesheet" href="${path }/resources/css/common_css/uglyFD-main.css">
<link rel="stylesheet" href="${path }/resources/css/uglyFD-components/uglyFD-figure,section.css">
<link rel="stylesheet" href="${path }/resources/css/uglyFD-components/uglyFD-recycle.css">
<link rel="stylesheet" href="${path }/views/admin/product_css/product_detail_page.css">
</head>
<style>
.product-p1{
	float: left;
	background-color: #57b038;
	border-radius: 15px;
	width: 50%;
	color: white;
	margin-top: 3px;
}
.product-p2{
	float: left;
	background-color: rgb(232, 57, 86);
	border-radius: 15px;
	width: 50%;
	color: white;
	margin-top: 3px;
}
.product-p3{
	color: black;
	font-weight: 900;
}
 .product-p5{ 
	font-weight: bolder;
	color: rgb(29, 108, 174);
 }
.fa-arrow-down{
	color: orange;
}
.div-h1{
	width: 100%;
	height: 50px;
}
.sec-title{
    text-align: center;
    color: rgb(237, 157, 26);
    font-weight: bolder;
}
.productcard{
	margin-bottom: 20px;
}
</style>
<body>

	<jsp:include page="/views/common/header.jsp" />
		<c:if test="${productCategoryNum == 1}">
			<div class="div-h1"></div>
	            <h1 class="sec-title">과일</h1>
	        <div class="div-h1"></div>
		</c:if>
		<c:if test="${productCategoryNum == 2 }">
			<div class="div-h1"></div>
	            <h1 class="sec-title">채소</h1>
	        <div class="div-h1"></div>	
		</c:if>
					
	 	<c:if test="${not empty list }">
			<div id="card-divwrap" class="row">
	 			<c:forEach var="product" items="${list }">
	           		<div class="col-4 productcard">
			           <div class="card">
			              <a href="${path }/product/detail?productNum=${product.productNum}">
			                <img src="${path }/resources/upload/product/${product.renamedFileName}" height="250px" class="card-img" alt="제주산 파인애플">
			                <p class="product-p1">무농약</p>
	                        <p class="product-p2">특별할인</p>
	                        <p class="product-p3">유기농 못난이 ${product.productName } 5kg</p>
	                        <p class="product-p4"><i class="fa-solid fa-arrow-down"> ${product.discount }%할인중</i></p>
	                        <p class="product-p5">${product.productPrice } (원)</p>
			              </a>
			           </div>
	            	</div>
	    		</c:forEach>
	        </div>
	    </c:if>
	
	<jsp:include page="/views/common/footer.jsp" />

</body>
</html>