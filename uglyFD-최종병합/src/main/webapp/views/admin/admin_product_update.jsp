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
            <h1>상품수정</h1>
            <div class="recycle-div1"></div>
       	<div class="text-center">
			<img src="${path}/resources/upload/product/${productfile.renamedFileName}" width="700px" height="200px" class="rounded" alt="...">
			<div class="recycle-div1"></div>
		</div>
			<form action="${path }/product/update" method="POST" enctype="multipart/form-data" class="update-btn">
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
	                        <td>${product.productName }</td>
	                        <td>${product.productPrice }</td>
	                        <td>5kg ${product.productAmount }box</td>
	                        <td>${product.discount }%</td>
	                        <td>${product.createDate }</td>
	                        <td>${product.modifyDate }</td>
	                     </tr>
	                 </tbody>
	              </table>
	              <table class="table">
	                <thead>
                        <tr>
                            <th scope="row" colspan="7" style="color : red">수정상품정보입력</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row" colspan="5">상품종류</th>
                            <td>
                                <label for="fruit"><input type="radio" id="fruit" name="productCategoryNum" value="1" checked>과일</label>
                                <label for="vegetable"><input type="radio" id="vegetable" name="productCategoryNum" value="2">채소</label>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="5">상품명</th>
                            <td><input id="productName" type="text" placeholder="상품명을 입력해주세요." size="60px" name="productName"></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="5">판매가</th>
                            <td><input id="productPrice" type="text" pattern="[0-9]+" placeholder="판매가를 입력해주세요." size="60px" name="productPrice"></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="5">첨부파일</th>
                            <td><input id="productFile" type="file" size="60px" name="upfile"></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="5">상세설명</th>
                            <td><textarea id="productDetail" name="productDetail" id="" cols="63" rows="5" style="resize:none;"></textarea></td>
                        </tr>
                        <!--  -->
                    </tbody>
                        <tr>
                        	<th>
					            	<input type="hidden" name="productNum" value="${product.productNum }">
					            	<button id="productUpdate" type="submit" class="btn find-btn1">수정하기</button>
					            </form>
                        	</th>
                        	<th>
			                    <form action="${path }/product/delete" method="GET" class="update-btn">
								        <input type="hidden" name="productNum" value="${product.productNum}">
							            <input type="hidden" name="productName" value="${product.productName}">
								        <button id="btnDelete" type="submit" class="btn find-btn1" name="update" value="delete">상품삭제</button>
								</form>
                        	</th>
                        	<th>
                        		<button onclick="history.go(-1);" type="button" class="btn find-btn1">이전으로</button>
                        	</th>
                        </tr>
                </table>
		            </div>
		        <div id="wrap-div3"></div>
		    </div>
            
            <jsp:include page="/views/common/footer.jsp" />
	
	<script>
	
	$('#productUpdate').click(function() {
	    if (!$('#productName').val()) {
	        alert('상품이름을 입력해주세요.');
	        return false;
	    }
	    if (!$('#productPrice').val()) {
	        alert('상품가격을 입력해주세요.');
	        return false;
	    }
	    if (!$('#productFile').val()) {
	        alert('상품이미지 파일을 등록해주세요.');
	        return false;
	    }
	    if (!$('#productDetail').val()) {
	        alert('상품에 대한 소개를 입력해주세요.');
	        return false;
	    }
	    
	    
	})
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	</script>
	
</body>
</html>