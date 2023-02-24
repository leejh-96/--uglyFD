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
            <h1>입/출고조회</h1>
            <div class="recycle-div1"></div>
            <c:if test="${list == null}">
            조회된 입출고 내역이 없습니다.
            </c:if>
            <c:if test="${not empty list }">
            <table class="table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>입/출고번호</th>
                        <th>상품명</th>
                        <th>상품가격</th>
                        <th>수량</th>
                        <th>입/출고구분</th>
                        <th>입/출고날짜</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="io" items="${list }">
	                    <tr>
	                        <th>${io.num }</th>
	                        <td>${io.inOutNum }</td>
	                        <td>${io.productName }</td>
	                        <td>${io.productPrice }</td>
	                        <td>${io.productStock }</td>
	                        <td>${io.status }</td>
	                        <td>${io.inOutDate }</td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <!-- 입출고관리-출력하는 table 끝-->
	            <div id="pagebar">
	                <button class="btn inoutbtn" onclick="location.href='${path}/productinout?page=1'"><<</button>
	                <button class="btn inoutbtn" onclick="location.href='${path}/productinout?page=${pageInfo.prevPage }'"><</button>
	            <c:forEach begin="${pageInfo.startPage }" end="${pageInfo.endPage }" varStatus="status">
	            	<c:choose>
	            		<c:when test="${status.current == pageInfo.currentPage }">
			                <button class="btn inoutbtn" disabled>${status.current }</button>
		                </c:when>
		                <c:otherwise>
			                <button class="btn inoutbtn" onclick="location.href='${path}/productinout?page=${status.current }'">${status.current }</button>
						</c:otherwise>			                
	                </c:choose>
				</c:forEach>
	                <button class="btn inoutbtn" onclick="location.href='${path}/productinout?page=${pageInfo.nextPage}'">></button>
	                <button class="btn inoutbtn" onclick="location.href='${path}/productinout?page=${pageInfo.maxPage}'">>></button>
	            </div>
            </c:if>
        </div>
        <div id="wrap-div3"></div>
    </div>
    <jsp:include page="/views/common/footer.jsp" />
    
</body>
</html>