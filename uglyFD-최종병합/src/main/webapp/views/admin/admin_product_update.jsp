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
.grid{
	width:100%;
	height:600px;
/* 	border: 1px solid red;  */
}
#productDetail{
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
            <h1>상품수정</h1>
            <div class="div-h1"></div>
            
            <form action="${path }/product/update" method="POST" enctype="multipart/form-data" class="update-btn">
            <div class="container-fluid">
		        <div class="row">
		            <div class="col-6 grid">
		              	<img src="${path}/resources/upload/product/${productfile.renamedFileName}" width="480px" height="460px" class="rounded">
		            </div>
		            <div class="col-6 grid grid-2">
						<table class="table">
		                    <thead>
		                        <tr>
		                            <th scope="row" style="color: green;">상품종류</th>
		                            <th scope="row" style="color: green;">
		                            	<label for="fruit"><input type="radio" id="fruit" name="productCategoryNum" value="1" checked>과일</label>
                                		<label for="vegetable"><input type="radio" id="vegetable" name="productCategoryNum" value="2">채소</label>
		                            </th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                        <tr>
		                            <th scope="row" style="color: green;">상품명</th>
		                            <th style="color: green;">
		                            	<input id="productName" type="text" placeholder="${product.productName }" size="50px" name="productName" required>
		                            </th>
		                        </tr>
		                        <tr>
		                            <th scope="row" style="color: green;">판매가</th>
		                            <th colspan="2" style="color: green;">
		                            	<input id="productPrice" type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" pattern="[0-9]+" placeholder="숫자만, EX)10000 형식으로 입력해주세요." size="50px" name="productPrice" required>
		                            </th>
		                        </tr>
		                        <tr>
		                            <th scope="row" style="color: orange;">첨부파일</th>
		                            <th style="color: orange;">
		                            	<input id="productFile" type="file" size="60px" name="upfile" required>
		                            </th>
		                        </tr>
		                        <tr>
		                            <th scope="row" style="color: green;">상품소개</th>
		                            <th style="color: green;">
		                            	<textarea id="productDetail" placeholder="${product.productDetail }" name="productDetail" id="" cols="63" rows="5" style="resize:none;" required></textarea>
		                            </th>
		                        </tr>
		                        <tr>
		                        	<th>
		                        		<input type="hidden" name="productNum" value="${product.productNum }">
					            		<input id="productUpdate" type="submit" class="btn find-btn1" value="수정하기" style="background-color: rgb(255, 244, 164);">
					            		</form>
		                        	</th>
		                        	<th>
		                        		<form action="${path }/product/delete" method="GET" class="update-btn">
									        <input type="hidden" name="productNum" value="${product.productNum}">
								            <input type="hidden" name="productName" value="${product.productName}">
									        <button id="btnDelete" type="submit" class="btn find-btn1" name="update" value="delete" style="background-color: rgb(255, 244, 164);">
									        상품삭제
									        </button>
										</form>
		                        	</th>
		                        </tr>
		                        <tr>
		                        	<td>
            							<button onclick="history.go(-1);" type="button" class="btn find-btn1" style="background-color: rgb(255, 244, 164);">이전으로</button>
		                        	</td>
		                        </tr>
		                    </tbody>
		                </table>
		            </div>
		         </div>
		      </div>
		   </div>
		        </div>
		        <div id="wrap-div3"></div>
		    </div>
            
            <jsp:include page="/views/common/footer.jsp" />
	
</body>
</html>