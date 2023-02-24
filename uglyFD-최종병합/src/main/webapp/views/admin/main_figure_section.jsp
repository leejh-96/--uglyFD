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
<body>
	<!-- uglyFD-figure -->
    <figure>
        <div class="recycle-div"></div>
        <div class="container">
            <div id="carouselExampleInterval" class="row carousel slide" data-ride="carousel">
                <div class="col  carousel-inner">
                    <div class="carousel-item active" data-interval="5000">
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
            <div class="col-4">
                <div class="card">
                    <a href="${path }/product/detail?productNum=${product.productNum}">
                        <img src="${path}/resources/upload/product/${product.renamedFileName}" height="300px" class="card-img" alt="제주산 파인애플">
                        <p>${product.productName }</p>
                    </a>
                </div>
            </div>
        </c:forEach>
        </div>
    </section>
	</c:if>        
<!--     <h2 class="sec-title">마감임박</h2><hr> -->
    
<!--         <div id="card-divwrap" class="row"> -->
<!--             <div class="col-4"> -->
<!--                 <div class="card"> -->
<!--                     <a href="#"> -->
<!--                         <img src="https://cdn.pixabay.com/photo/2013/04/07/10/31/walnuts-101425__480.jpg" style="height: 300px"class="card-img" alt="제주산 파인애플"> -->
<!--                         <p>와자자작 호두</p> -->
<!--                     </a> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
        

</body>
</html>




