<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uglyFD-main</title>
<link rel="stylesheet" href="${path }/resources/css/uglyFD-components/uglyFD-figure,section.css">
<link rel="stylesheet" href="${path }/resources/css/uglyFD-components/uglyFD-recycle.css">
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
.productcard{
	margin-bottom: 20px;
}
</style>
<body>
	<!-- uglyFD-figure -->
    <figure>
        <div class="recycle-div"></div>
        <div class="container">
            <div id="carouselExampleInterval" class="row carousel slide" data-ride="carousel">
                <div class="col  carousel-inner">
                    <div class="carousel-item active" data-interval="3000">
                        <img src="${path }/resources/images/uglyFD-motive.png" width="400px" height="400px" class="d-block w-100 img-carousel" alt="...">
                    </div>
                    <div class="carousel-item " data-interval="2000">
                        <img src="https://cdn.pixabay.com/photo/2018/10/27/01/21/pumpkin-3775726_1280.jpg" width="400px" height="400px" class="d-block w-100 img-carousel" alt="...">
                    </div>
                    
                    <div class="carousel-item " data-interval="2000">
                        <img src="https://cdn.pixabay.com/photo/2015/12/09/17/11/vegetables-1085063__480.jpg" width="400px" height="400px" class="d-block w-100 img-carousel" alt="...">
                    </div>
                    
                    <button class="carousel-control-prev" type="button" data-target="#carouselExampleInterval" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">이전</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-target="#carouselExampleInterval" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">다음</span>
                    </button>
                </div>
            </div>
        </div>
        <div class="recycle-div"></div>
    </figure>

	<!-- uglyFD-section -->
	<c:if test="${not empty list }">
    <section>
    <h2 class="sec-title">인기상품</h2><hr>
        <div id="card-divwrap" class="row">
        <c:forEach var="product" items="${list }">
<!--         <br> -->
            <div class="col-4 productcard">
                <div class="card">
                    <a href="${path }/product/detail?productNum=${product.productNum}">
                        <img src="${path}/resources/upload/product/${product.renamedFileName}" height="300px" class="card-img" alt="제주산 파인애플">
                        <p class="product-p1">무농약</p>
                        <p class="product-p2">특별할인</p>
                        <p class="product-p3">유기농 못난이 ${product.productName } 5kg</p>
                        <p class="product-p4"><i class="fa-solid fa-arrow-down"> ${product.discount }%할인중</i></p>
                        <p class="product-p5">${product.productPrice } (원)</p>
                    </a>
                </div>
            </div>
<!--             <br> -->
        </c:forEach>
        </div>
    </section>
	</c:if>        

</body>
</html>




