<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<link href="${path}/resources/css/board/inquire.css" rel="stylesheet"/> 
<link rel="stylesheet" href="${path }/resources/css/common_css/uglyFD-main.css">
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
      crossorigin="anonymous"
    />
<!-- uglyFD-figure / section 부분 script -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="/views/common/header.jsp" />
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>



    <nav class="nav1"></nav>

    <div id="wrap">
      <h2 style="text-align: center">1대1 문의</h2>
      <br /><br />
      <table id="div1">
        <tr>
          <td colspan="5" style="border: white; text-align: right">
		<c:if test="${ not empty loginMember }">
            <button type="button" onclick="location.href='${ path }/board/write'">글쓰기</button>
        </c:if>
          </td>
        </tr>
        <tr style="background: rgb(221, 221, 221)">
          <td><b>글번호</b></td>
          <td><b>글제목</b></td>
          <td><b>작성자</b></td>
          <td><b>작성날짜</b></td>
          <td><b>조회수</b></td>
        </tr>
       	<c:if test="${ empty inquire }">
       	 <tr>
       	 <td colspan="6">
       	 조회된 게시글이 없습니다.
       	 </td>
       	 </tr>
       	</c:if>
       	<c:if test="${ not empty inquire }">
       	<c:forEach var="board" items="${ inquire }">
          <tr></tr>
            <td>${ board.rowNum }</td>
            <td><a href="${ path }/board/view?no=${ board.no }">
            ${ board.title }</a></td>
            <td>${ board.writerId }</td>
            <td>${ board.createDate }</td>
            <td>${ board.readCount }</td>
       	</c:forEach>
       	</c:if>

       
      </table>
      <br /><br />
      <div>
      <nav aria-label="Page navigation example" id="nav">
         <div id="pageBar">
            <!-- 맨 처음으로 -->
            <button onclick="location.href='${ path }/board/inquire?page=1'">&lt;&lt;</button>

            <!-- 이전 페이지로 -->
            <button onclick="location.href='${ path }/board/inquire?page=${ pageInfo.prevPage }'">&lt;</button>

            <!--  10개 페이지 목록 -->
            <c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" varStatus="status">
                <c:choose>
                    <c:when test="${ status.current == pageInfo.currentPage}">
                        <button disabled>${ status.current }</button>
                    </c:when>
                    <c:otherwise>
                        <button onclick="location.href='${ path }/board/inquire?page=${ status.current }'">${ status.current }</button>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <button onclick="location.href='${ path }/board/inquire?page=${ pageInfo.nextPage }'">&gt;</button>

            <!-- 맨 끝으로 -->
            <button onclick="location.href='${ path }/board/inquire?page=${ pageInfo.maxPage }'">&gt;&gt;</button>
        </div>
      </nav>
    </div>

    <nav class="nav1"></nav>
    
<jsp:include page="/views/common/footer.jsp"/>