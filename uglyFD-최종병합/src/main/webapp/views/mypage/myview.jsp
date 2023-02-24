<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 리뷰</title>
<link rel="stylesheet" href="${path }/views/mypage/myview.css">
<link rel="stylesheet" href="${path }/resources/css/common_css/uglyFD-main.css">
<!-- uglyFD-figure / section 부분 script -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<jsp:include page="/views/common/header.jsp" />
	
	
<nav class="nav1"></nav>
<section class="notice">
  <div class="page-title">
    <div class="container">
      <h2 style="text-align: center"><b>나의리뷰</b></h2>
    </div>
  </div>

  <!-- board seach area -->
  <div id="board-search">
    <div class="container">
      <div class="search-window">
        <form action="">
          <div class="search-wrap">
            <!-- <label for="search" class="blind">공지사항 내용 검색</label> -->
            <input
              id="search"
              type="search"
              name=""
              placeholder="검색어를 입력해주세요."
              value=""
            />
            <button type="submit" class="btn btn-dark">검색</button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <!-- board list area -->
  <div id="board-list">
    <div class="container">
      <table class="board-table">
        <thead>
          <tr>
            <th scope="col" class="th-num">번호</th>
            <th scope="col" class="th-title">상품명</th>
            <th scope="col" class="th-date">리뷰</th>
            <th scope="col" class="th-date">등록일</th>

          </tr>
        </thead>
        <tbody>
          <tr>
            <td>3</td>
            <td>당근</td>
            <th>
              <a href="#!">당근이 너무 신선해서 도망갔어요 별점 낮게 드릴게요 </a>
            </th>
            <td>2017.07.13</td>
          </tr>

          <tr>
            <td>2</td>
            <td>배추</td>
            <th>
              <a href="#!">배추가 너무 달아서 설탕을 안썼어요 이게 맞나요 ?</a>
            </th>
            <td>2017.06.15</td>
          </tr>

          <tr>
            <td>1</td>
            <td>오이</td>
            <th><a href="#!">오이 이거 밥도둑이에요 경찰 불러주세요</a></th>
            <td>2017.06.15</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  <br /><br />
  <nav aria-label="Page navigation example" id="nav">
    <ul class="pagination">
      <li class="page-item">
        <a class="page-link" href="#" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
      <li class="page-item"><a class="page-link" href="#">1</a></li>
      <li class="page-item"><a class="page-link" href="#">2</a></li>
      <li class="page-item"><a class="page-link" href="#">3</a></li>
      <li class="page-item">
        <a class="page-link" href="#" aria-label="Next">
          <span aria-hidden="true">&raquo;</span>
        </a>
      </li>
    </ul>
  </nav>
</section>
	
	
	
	
	
	
	
	
	
	
	
	
	
		<jsp:include page="/views/common/footer.jsp" />
</body>
</html>