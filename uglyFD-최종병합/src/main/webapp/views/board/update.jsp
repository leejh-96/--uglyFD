<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<link rel="stylesheet" href="${path }/resources/css/common_css/uglyFD-main.css">
<!-- uglyFD-figure / section 부분 script -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<style>
    .main-section{width:800px; margin:0 auto; text-align:center;}
/*      section>div#board-write-container h2{margin:10px 0;}  */
/*       table.table table-bordered{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }   */
/*       table.table table-bordered th {width: 125px; border:1px solid #1d2124; padding: 5px 0; text-align:center;}    */
/*       table.table table-bordered td {border:1px solid #1d2124; padding: 5px 0 5px 10px; text-align:left;}   */
     div#comment-container button#btn-insert{width:60px;height:50px; color:white; background-color:#3300FF;position:relative;top:-20px;} 
    
    /*댓글테이블*/
    table#tbl1-comment123{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#tbl1-comment123 tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;} 
    table#tbl1-comment123 tr td:first-of-type{padding: 5px 5px 5px 50px;}
    table#tbl1-comment123 tr td:last-of-type {text-align:right; width: 100px;}
    table#tbl1-comment123 button.btn-delete{display:none;}
    table#tbl1-comment123 tr:hover {background:lightgray;}
    table#tbl1-comment123 tr:hover button.btn-delete{display:inline;}
    table#tbl1-comment123 sub.comment-writer {color:navy; font-size:14px}
    table#tbl1-comment123 sub.comment-date {color:tomato; font-size:10px}

 	.main-section .table th { width: 80px; border:1px solid #1d2124; background:lightgray; text-align:center;} 
	.main-section .table td { width: 280px; text-align: left; border:1px solid #1d2124;}
	.main-section .table tr:nth-child(4) td { height: 200px; }
</style>
<jsp:include page="/views/common/header.jsp" />

	<section class="main-section">
	<div id='board-write-container'>
			<br>
		<h1>1대1<small>문의사항</small></h1>
		<br>
		<form action="${ path }/board/update" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="no" value="${ board.no }">
		<table class="table table-bordered" >
			<tr>
				<th>제목</th>
				<td><input  type="text" name="title" id="title"
							value="${ board.title }"></td>
			</tr>				
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="${ board.writerId }" readonly></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" cols="90" rows="15" >${ board.content }</textarea></td>
			</tr>		
			<tr>
            <th colspan="4" style="padding-left:74%">
						<input type="submit" value="수정">
						<input type="button" onclick="location.replace('${path}/board/inquire')" value="목록으로">
            </th>
         </tr>
      </table>
	</form>
	</div>
	</section>
<jsp:include page="/views/common/footer.jsp" />
