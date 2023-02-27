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
	            <h1>재고등록</h1>
	       <div class="div-h1"></div>
	       
<%-- 	        <form action="${path }/product/update" method="POST" enctype="multipart/form-data" class="update-btn"> --%>
            <div class="container-fluid">
		        <div class="row">
		            <div class="col-6 grid">
		              	<img src="${path}/resources/upload/product/${productfile.renamedFileName}" width="480px" height="480px" class="rounded">
		            </div>
		            <div class="col-6 grid grid-2">
		            <form action="${path }/productstockupdate" method="POST" >
						<table class="table">
		                    <thead>
		                        <tr>
		                            <th scope="row" colspan="2">상품종류</th>
		                            <th scope="row">
		                            	<c:if test="${product.productCategoryNum == 1 }">
				                                과일
                            			</c:if>
                            			<c:if test="${product.productCategoryNum == 2 }">
				                                채소
                            			</c:if>
		                            </th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                        <tr>
		                            <th scope="row" colspan="2">상품명</th>
		                            <th>
		                            	${product.productName }
		                            </th>
		                        </tr>
		                        <tr>
		                            <th scope="row" colspan="2">판매가</th>
		                            <th colspan="2">
		                            	${product.productPrice } (원)
		                            </th>
		                        </tr>
		                        <tr>
		                            <th scope="row" colspan="2">상품내용</th>
		                            <th>
		                            	${product.productDetail }
		                            </th>
		                        </tr>
		                        <tr>
		                        	<th scope="row" colspan="2" style="color: orange;">할인율</th>
                            		<th style="color: orange;">${product.discount }%</th>
		                        </tr>
		                        <tr>
		                            <th scope="row" colspan="2">등록날짜</th>
		                            <th>${product.createDate}</th>
                        		</tr>
                        		<tr>
		                            <th scope="row" colspan="2">수정날짜</th>
		                            <th>${product.modifyDate }</th>
                        		</tr>
                        		<tr>
		                            <th scope="row" colspan="2" style="color: green;">현재상품수량</th>
		                            <th style="color: green;">5kg ${product.productAmount } (Box)</th>
                        		</tr>
                        		<tr>
		                            <th scope="row" colspan="2">입/출고 등록</th>
		                            <th>
		                            	<input type="radio" class="inout" id="in" name="inout" value="입고"checked>입고
		                                <input type="radio" class="inout" id="out" name="inout" value="출고">출고
			                        </th>
                        		</tr>
                        		<tr>
		                            <th scope="row" colspan="2" style="color: green;">등록재고수량</th>
		                            <th style="color: green;">5kg
		                            	<input oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" id="productAcount" type="text" size="5px" name="stock" required>
		                            	(Box)단위입력
			                        </th>
                        		</tr>
		                    </tbody>
		                </table>
		                <div class="find-btn">
		                	<input type="hidden" id="productCategoryNum" name="productCategoryNum" value="${product.productCategoryNum}">
		                	<input type="hidden" id="productNum" name="productNum" value="${product.productNum}">
		                    <button id="productStock" type="submit" class="btn find-btn1" style="background-color: rgb(255, 244, 164);">등록하기</button>
		                    <button onclick="history.go(-1);" type="button" class="btn find-btn1" style="background-color: rgb(255, 244, 164);">이전으로</button>
                		</div>
            		</form>
		            </div>
		         </div>
		      	</div>
				</div>
		        <div id="wrap-div3"></div>
		    </div>
            
            <jsp:include page="/views/common/footer.jsp" />
	
	<script>
	
	$('#productStock').click(function() {
	    if (!$('#productAcount').val()) {
	        alert('수량을 입력해주세요.');
	        return false;
	    }
	})
	

		$('#productStock').on('click',function(){
			
			let productNum = $('#productNum').val();
			let productCategoryNum = $('#productCategoryNum').val();
			let productAcount = $('#productAcount').val();
			let inout = $('.inout').val();
			
			$.ajax({
				
				type: 'POST',
				url: '${pageContext.request.contextPath}/productstockupdate',
				datatype: 'text',
				data:{
					productNum,
					productCategoryNum,
					productAcount,
					inout
					
				},
				success: (data==='ok')=>{
					alert('ajax통신성공 + 재고등록 성공')
					
				},
				error: (error)=>{
					
					alert('ajax통신실패')

				}
				
			});
			
		});

	</script>
	
</body>
</html>