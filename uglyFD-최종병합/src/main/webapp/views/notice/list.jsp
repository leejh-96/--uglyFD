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
<title>공지사항게시판</title>
<link rel="stylesheet" href="${path }/resources/css/common_css/uglyFD-main.css">
</head>
<style>
    section#board-list-container{width:600px; margin:0 auto; text-align:center;}
    section#board-list-container h2{margin:10px 0;}
    table#tbl-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;} 
    /*글쓰기버튼*/
    input#btn-add{float:right; margin: 0 0 15px;}
    /*페이지바*/
    div#pageBar{margin-top:10px; text-align:center; background-color:rgba(0, 188, 212, 0.3);}
</style>

<body>

	<jsp:include page="/views/common/header.jsp" />

		<section id="content">
    <h2 align="center">-공지사항 게시판- </h2>
    <div id="board-list-container">
<%--         <c:if test="${ not empty loginMember }" > --%>
            <!--세션영역에 loginMember를 확인한다. loginMember가 비어있지 않으면(로그인이 되어있으면) 글쓰기 버튼이 보이는 구문이다.-->

<!-- '${ path }/board/write' 글쓰기 버튼을 CLICK시 URL영역으로 요청한다. -->
<c:if test="${not empty loginMember && loginMember.grade == 1 }">
            <button type="button" onclick="location.href='${ path }/notice/write'">글쓰기</button>
</c:if>
<%--         </c:if> --%>

        <table id="tbl-board">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <!-- BoardListServlet에서 조회되는 list가 비어있을때 다음줄을 실행시켜준다. -->
            <c:if test="${ empty list }">
                <tr>
                    <td colspan="6">
                        조회된 게시글이 없습니다.
                    </td>
                </tr>    
            </c:if>
            <!-- BoardListServlet에서 조회되는 list가 비어있지 않을 때,  다음줄을 실행시켜준다. -->
            <c:if test="${ not empty list }">
            <!-- items가 board라는 이름에 담겨서 반복해준다. -->
                <c:forEach var="board" items="${ list }">
                    <tr>
                        <td>${ board.rowNum }</td>
                        <td>
                        <!-- 게시글을 click 하면  -->
                            <a href="${ path }/notice/view?no=${ board.b_no }">
                                ${ board.bo_title }
                            </a>
                        </td>
                        <td>${ board.bo_writer }</td>    <!-- 작성자 -->
                        <td>${ board.bo_date }</td>
                        <td>
                            <c:if test="${ empty board.original_filename }">
                                <span> - </span>
                            </c:if>
                            <c:if test="${ not empty board.original_filename }">
                                <img width="20px" src="${ path }/resources/images/file.png">
                            </c:if>
                        </td>
                        <td>${ board.bo_hit }</td>
                    </tr>
                </c:forEach>
            </c:if>

        </table>
        <div id="pageBar">
            <!-- 맨 처음으로 -->
            <button onclick="location.href='${ path }/notice/list?page=1'">&lt;&lt;</button>

            <!-- 이전 페이지로 -->
            <button onclick="location.href='${ path }/notice/list?page=${ pageInfo.prevPage }'">&lt;</button>

            <!--  10개 페이지 목록 -->
            <c:forEach begin="${pageInfo.startPage }" end="${pageInfo.endPage }" varStatus="status">
                <c:choose>
                    <c:when test="${status.current == pageInfo.currentPage }">
                        <button disabled>${status.current }</button>
                    </c:when>
                    <c:otherwise>
                        <button onclick="location.href='${ path }/notice/list?page=${ status.current}'">${status.current }</button>
                    </c:otherwise>
                </c:choose>
            </c:forEach>


            <!-- 다음 페이지로 -->
            <button onclick="location.href='${ path }/notice/list?page=${ pageInfo.nextPage }'">&gt;</button>

            <!-- 맨 끝으로 -->
            <button onclick="location.href='${ path }/notice/list?page=${ pageInfo.maxPage }'">&gt;&gt;</button>
        </div>
    </div>
</section>

	<jsp:include page="/views/common/footer.jsp" />

</body>
</html>