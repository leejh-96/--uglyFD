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
<title>상품상세페이지</title>
<%-- <link rel="stylesheet" href="${path }/resources/css/uglyFD-components/uglyFD-figure,section.css"> --%>
<link rel="stylesheet" href="${path }/resources/css/uglyFD-components/uglyFD-recycle.css">
<link rel="stylesheet" href="${path }/views/admin/product_css/product_detail_page.css">
<link rel="stylesheet" href="${path }/resources/css/common_css/uglyFD-main.css">
</head>
<style>
.grid{
	width:100%;
	height:600px;
/*  	border: 1px solid red;  */
	display: flex;
	justify-content: center;
	align-items: center;
}
.cartbtn{
	background-color: rgb(255, 244, 164);
}
.h1{
	color: orange; 
	text-align: center;
	font-weight: bolder;
}
#nostock{
	color: red;
}
</style>
<body>
	
	<jsp:include page="/views/common/header.jsp" />
	
	<div id="productdetail-wrap">
        <div id="productdetail-wrap-div1"></div>
        <div id="productdetail-wrap-div2">
			 <div class="recycle-div2"></div>
           		<h1 class="h1">상품 정보</h1>
             <div class="recycle-div2"></div>
			 <div class="container-fluid">
		        <div class="row">
		            <!-- row 1 (total : 12) -->
		            <div class="col-6 grid">
		              	<img src="${path }/resources/upload/product/${productFile.renamedFileName}" width="400px" height="400px" class="rounded">
		            </div>
		            <div class="col-6 grid">
						<table class="table">
		                    <thead>
		                        <tr>
		                            <th scope="row">상품이름</th>
		                            <th scope="row">${product.productName }</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                        <tr>
		                            <th scope="row">가격</th>
		                            <th>${product.productPrice } (원)</th>
		                        </tr>
		                        <tr>
		                            <th scope="row">상품내용</th>
		                            <th colspan="2">${product.productDetail }</th>
		                        </tr>
		                        <tr>
		                            <th scope="row" style="color: orange;">할인율</th>
		                            <th style="color: orange;">${product.discount }%</th>
		                        </tr>
		                        <tr>
		                            <th scope="row" style="color: cornflowerblue;">현재재고</th>
		                            <th style="color: cornflowerblue;"><5kg> ${product.productAmount }  Box</th>
		                        </tr>
		                        
		                        <tr>
		                        	<!-- 비회원 && 관리자일 경우 -->
		                        	<c:if test="${empty loginMember || loginMember.grade == 1 }">
		                        		<c:if test="${product.productAmount == 0 }">
		                        			<th id="nostock">
												현재 주문가능한 수량이 없습니다.<br> 다음에 다시 이용해주세요.
											</th>
											<th>
												<button onclick="history.go(-1);" type="button" class="btn find-btn1" style="background-color: rgb(255, 244, 164);">이전으로</button>
											</th>
		                        		</c:if>
		                        		<c:if test="${product.productAmount > 0 && empty loginMember}">
		                        			<th id="nostock">
												로그인 후 이용 가능합니다.
											</th>
											<th>
												<button onclick="history.go(-1);" type="button" class="btn find-btn1" style="background-color: rgb(255, 244, 164);">이전으로</button>
											</th>
		                        		</c:if>
		                        		<c:if test="${product.productAmount > 0 && loginMember.grade == 1 }">
		                        			<th id="nostock">
												관리자님 환영합니다.
											</th>
											<th>
												<button onclick="history.go(-1);" type="button" class="btn find-btn1" style="background-color: rgb(255, 244, 164);">이전으로</button>
											</th>
		                        		</c:if>
		                        	</c:if>
		                        	<!-- 회원일 경우 -->
		                        	<c:if test="${not empty loginMember && loginMember.grade == 2 }">
		                        		
		                        		<c:if test="${product.productAmount == 0 }">
		                        			<th id="nostock">
												현재 주문가능한 수량이 없습니다.<br> 다음에 다시 이용해주세요.
											</th>
											<th>
												<button onclick="history.go(-1);" type="button" class="btn find-btn1" style="background-color: rgb(255, 244, 164);">이전으로</button>
											</th>
		                        		</c:if>
		                        		<c:if test="${product.productAmount > 0 }">
		                        			<th>
												<label for="amount">수량  5kg
									            <input type="number" name="amount" id="amount" value="0" min="0"
									             max="${product.productAmount }" step="1" required="required"
									             oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" > Box
												</label>
		                        			</th>
		                        			<th>
												<button onclick="location.href='${path}/views/mypage/mycart.jsp'" type="button" class="cartbtn btn find-btn1">장바구니</button>
	   											<button onclick="history.go(-1);" type="button" class="btn find-btn1" style="background-color: rgb(255, 244, 164);">이전으로</button>
		                        			</th>
		                        		</c:if>
		                        	</c:if>
		                        </tr>
		                    </tbody>
		                </table>
		            </div>
		       	</div>
		     </div>
			 <form action="${path }/review/update" method="post">
				<table class="table">
	               <tbody>
	                   <tr>
	                       <th scope="row" colspan="2"></th>
	                       <td>
	                           <input type="hidden" name="productCategoryNum" value="${product.productCategoryNum }">
	                           <input type="hidden" name="productNum" value="${product.productNum }">
	                           <input type="hidden" name="loginMemberId" value="${loginMember.id}">
	                           <div class="recycle-div2"></div>
		                       <input id="review" type="text" placeholder="리뷰등록^_^" size="108px" name="review" required>
		                       <button id="reviewbtn" type="submit" class="cartbtn btn find-btn1">등록</button>
	                       </td>
	                       <td></td>
	                   </tr>
	               </tbody>
	            </table>
			 </form>
			 <c:if test="${not empty product.review }">
				<table class="table">
		             <tbody>
		                <c:forEach var="pr" items="${product.review }">
		                	<tr>
		                		<th>${pr.memberId }</th>
		                        <th>${pr.content }</th>
		                        <th>${pr.createDate }</th>
		                        <th>${pr.modifyDate }</th>
		                    </tr>
		                </c:forEach>
		             </tbody>
	            </table>
			 </c:if>
    	</div>
        <div id="productdetail-wrap-div3"></div>
     </div>
	
	<jsp:include page="/views/common/footer.jsp" />
	
<script type="text/javascript">
	
	$('#reviewbtn').click(function() {
	    if (!$('#review').val()) {
	        alert('댓글을 입력해주세요.');
	        return false;
	    }
	    
</script>
</body>
</html>