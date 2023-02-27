<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="${path }/resources/css/common_css/uglyFD-main.css">
<style>
    div#board-write-container
    {
        width:600px;
        margin:0 auto;
        text-align:center;
    }
    
    div#board-write-container h2
    {
        margin:10px 0;
    }
    
    table#tbl-board
    {
        width:500px;
        margin:0 auto;
        border:1px solid black;
        border-collapse:collapse;
    }
    
    table#tbl-board th
    {
        width:125px;
        border:1px solid;
        padding:5px 0;
        text-align:center;
    }
    
    table#tbl-board td
    {
        border:1px solid;
        padding:5px 0 5px 10px;
        text-align:left;
    }
</style>

<body>

<jsp:include page="/views/common/header.jsp" />

	<section id="content">
    <div id='board-write-container'>
        <h2>공지사항 게시판 작성</h2>
        <form action="${ path }/notice/write" method="POST" enctype="multipart/form-data">
            <table id='tbl-board'>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" id="title"></td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td><input type="text" name="writer" value="${ loginMember.name}" readonly></td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td><input type="file" name="upfile"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea name="content" cols="50" rows="15" ></textarea></td>
                </tr>
                <tr>
                    <th colspan="2">
<%--                         <input type="hidden" name="m_no" value="${loginMember.m_no }"> --%>
                        
                        <input type="submit" value="등록">
                        <input type="reset" value="취소">
                    </th>
                </tr>
            </table>
        </form>
    </div>
</section>





<jsp:include page="/views/common/footer.jsp" /> 

</body>
</html>