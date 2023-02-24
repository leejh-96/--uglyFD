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
<body>

	<jsp:include page="/views/common/header.jsp" />
	
		<c:if test="${productCategoryNum == 1 }">
			<h2 class="sec-title">과일</h2><hr>
		</c:if>
		<c:if test="${productCategoryNum == 2 }">
			<h2 class="sec-title">채소</h2><hr>
		</c:if>
					
	 	<c:if test="${not empty list }">
			<div id="card-divwrap" class="row">
	 			<c:forEach var="product" items="${list }">
	           		<div class="col-4">
			           <div class="card">
			              <a href="${path }/product/detail?productNum=${product.productNum}">
			                <img src="${path }/resources/upload/product/${product.renamedFileName}" height="250px" class="card-img" alt="제주산 파인애플">
			                <p>${product.productName }</p>
	   	                    <p>3kg ${product.productPrice }원</p>
		                    <p>${product.discount }% 할인중</p>
			              </a>
			           </div>
	            	</div>
	    		</c:forEach>
	        </div>
	    </c:if>
	
	<jsp:include page="/views/common/footer.jsp" />

</body>
</html>