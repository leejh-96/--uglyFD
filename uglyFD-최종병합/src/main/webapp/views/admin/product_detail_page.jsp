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
<link rel="stylesheet" href="${path }/resources/css/uglyFD-components/uglyFD-figure,section.css">
<link rel="stylesheet" href="${path }/resources/css/uglyFD-components/uglyFD-recycle.css">
<link rel="stylesheet" href="${path }/views/admin/product_css/product_detail_page.css">
<link rel="stylesheet" href="${path }/resources/css/common_css/uglyFD-main.css">
</head>
<style>

     .title {
        margin: 0 0 4px;
        font-size: 24px;
        font-weight: 600;
        color: #4e5968;
      }
      .form123 {
        width: 100%;
        text-align: center;
/*         margin-left: 28.5%; */
/*         margin-top: 50px; */
        border: none;
        position: relative;
      }


</style>


<body>
	
	<jsp:include page="/views/common/header.jsp" />
	
	<div id="productdetail-wrap">
        <div id="productdetail-wrap-div1"></div>
        <div id="productdetail-wrap-div2">
			<div class="recycle-div2"></div>
  			
			<form action="">
  			<div class="text-center">
			  <div class="recycle-div2"></div>
			</div>
			
			<div class="form123">
        <div class="boardWrite">
<!--         <img src="./당근.png" alt="" style="width: 100%; height: 100%;"> -->
           <h1 style="color: orange;">상품 정보</h1>
            <table class="format123">
                <tr>
			  <th rowspan="4"><img src="${path }/resources/upload/product/${productFile.renamedFileName}" width="400px" height="400px" class="rounded" alt="...">
                     </th>
                </tr>
                <tr>
                    <th scope="row" style="text-align: center;" >상품명</th>
                    <td><input class="inputline" type="text" placeholder="상품이름"></td>
                </tr>
                <tr>
                    <th scope="row" style="text-align: center;" >상품가격</th>
                    <td><input class="inputline" type="text" placeholder="상품가격"></td>
                </tr>
                <tr>
                    <th scope="row" style="text-align: center;" >상품 수량</th>
                    <td><input class="inputline" type="text" placeholder="상품 수량" style="border: none;"></td>
                </tr>
            </table>
<!--             <hr> -->
<!--             <table class="allprice"> -->
<!--                 <th > 총 금액 :</th> -->
<!--                 <td><input class="inputline" type="text" placeholder="총 금액12312312312" style="border: none;" readonly></td> -->
<!--             </table> -->
            
        
        </div>
    </div>
			
			
			
			
<!-- 			 <table class="table"> -->
<!--                     <thead> -->
<!--                         <tr> -->
<!--                             <th scope="row">상품이름</th> -->
<%--                             <th scope="row">${product.productName }</th> --%>
<!--                         </tr> -->
<!--                     </thead> -->
<!--                     <tbody> -->
<!--                         <tr> -->
<!--                             <th scope="row">가격</th> -->
<%--                             <th>${product.productPrice }</th> --%>
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <th scope="row">상품내용</th> -->
<%--                             <th colspan="2">${product.productDetail }</th> --%>
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <th scope="row">할인율</th> -->
<%--                             <th>${product.discount }%</th> --%>
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <th scope="row">현재재고</th> -->
<%--                             <th>5kg ${product.productAmount }Box</th> --%>
<!--                         </tr> -->
<!--                     </tbody> -->
<!--                 </table> -->
				
					<c:if test="${product.productAmount == 0 }">
						<h2>현재 주문가능한 수량이 없습니다. 다음에 다시 이용해주세요.</h2>
					</c:if>
					<c:if test="${product.productAmount != 0 }">
						<label for="amount">수량 : 5kg
	            			<input type="number" name="amount" id="amount" value="0" min="0" max="${product.productAmount }" step="1">Box
						</label>
					</c:if>
					<c:if test="${product.productAmount > 0 }">
	                    <button type="button" class="btn find-btn1">주문하기</button>
	                    <button type="button" class="btn find-btn1">장바구니</button>
	                </c:if>
				</form>
				<form action="${path }/review/update" method="post">
					<table class="table">
	                    <tbody>
	                        <tr>
	                            <th scope="row" colspan="2"></th>
	                            <td>
	                            	<input type="hidden" name="productCategoryNum" value="${product.productCategoryNum }">
	                            	<input type="hidden" name="productNum" value="${product.productNum }">
	                            	<input type="hidden" name="loginMemberId" value="${loginMember.id}">
		                            <input id="review" type="text" placeholder="리뷰를 달아주세요^_^" size="60px" name="review" required>
		                            <button id="reviewbtn" type="submit" class="btn find-btn1">등록</button>
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
		                			<th>${pr.memberId }</th>
		                        	<th>${pr.content }</th>
		                        	<th>${pr.createDate }</th>
		                        	<th>${pr.modifyDate }</th>
		                    	</tr>
		                    </c:forEach>
		                </tbody>
	            	</table>
<!-- 	            	<div id="pagebar"> -->
<%-- 	                <button class="btn inoutbtn" onclick="location.href='${path}/product/detail?page=1'"><<</button> --%>
<%-- 	                <button class="btn inoutbtn" onclick="location.href='${path}/product/detail?page=${pageInfo.prevPage }'"><</button> --%>
<%-- 	            <c:forEach begin="${pageInfo.startPage }" end="${pageInfo.endPage }" varStatus="status"> --%>
<%-- 	            	<c:choose> --%>
<%-- 	            		<c:when test="${status.current == pageInfo.currentPage }"> --%>
<%-- 			                <button class="btn inoutbtn" disabled>${status.current }</button> --%>
<%-- 		                </c:when> --%>
<%-- 		                <c:otherwise> --%>
<%-- 			                <button class="btn inoutbtn" onclick="location.href='${path}/product/detail?page=${status.current }'">${status.current }</button> --%>
<%-- 						</c:otherwise>			                 --%>
<%-- 	                </c:choose> --%>
<%-- 				</c:forEach> --%>
<%-- 	                <button class="btn inoutbtn" onclick="location.href='${path}/product/detail?page=${pageInfo.nextPage}'">></button> --%>
<%-- 	                <button class="btn inoutbtn" onclick="location.href='${path}/product/detail?page=${pageInfo.maxPage}'">>></button> --%>
<!-- 	            </div> -->
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