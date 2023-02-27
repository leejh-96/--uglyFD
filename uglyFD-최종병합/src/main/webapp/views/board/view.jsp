<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<link rel="stylesheet" href="${path }/resources/css/common_css/uglyFD-main.css">
<!-- uglyFD-figure / section 부분 script -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="/views/common/header.jsp" />

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

    .main-section .table th { width: 120px; border:1px solid #1d2124; background:lightgray;} 
   .main-section .table td { width: 280px; text-align: left; border:1px solid #1d2124;}
   .main-section .table tr:nth-child(4) td { height: 200px; }
</style>
</head>
<body>

   <section class="main-section">
         <br>
      <h1>1대1<small>문의사항</small></h1>
      <br>
      <table class="table table-bordered" >
         <tr>
            <th>번호</th>
            <td>${ board.no }</td>
            <th>작성자</th>
            <td>${ board.writerId }</td>
         </tr>
         <tr>
            <th>날짜</th>
            <td>${ board.createDate }</td>
            <th>조회수</th>
            <td>${ board.readCount }</td>
         </tr>   
         <tr>
            <th>제목</th>
            <td colspan="3">${ board.title }</td>
         </tr>      
         <tr>
            <th>내용</th>
            <td colspan="3">${ board.content }</td>
         </tr>      
         <tr>
            <th colspan="4" style="padding-left:74%">
            <c:if test="${not empty loginMember && loginMember.grade == 1}">
                  <button type="button" id="btnDelete">삭제</button>
            </c:if>
            <c:if test="${not empty loginMember && (loginMember.grade == 1 && loginMember.id == board.writerId)}">
            	  <button type="button" onclick="location.href='${ path }/board/update?no=${ board.no }'">수정</button>
            </c:if>
            <c:if test="${ not empty loginMember && (loginMember.grade == 2 && loginMember.id == board.writerId)}">
                  <button type="button" onclick="location.href='${ path }/board/update?no=${ board.no }'">수정</button>
               	  <button type="button" id="btnDelete">삭제</button>
            </c:if>
               <button  type="button" onclick="location.href='${ path }/board/inquire'">목록으로</button>
            </th>
         </tr>
      </table>
      <br>
      <hr style="border: 1px groove; width: 131%; margin-left:-20%">
      <br>
      <div id="comment-container">
      <h3 style="margin-right:46%">댓글 달기</h3>
          <div class="comment-editor">
             <form action="${ path }/board/reply" method="POST">
                <input type="hidden" name="boardNo" value="${ board.no }">
               <textarea name="content" id="replyContent" cols="55" rows="2" style="resize:none;" required></textarea>
               <button type="submit" id="btn-insert">등록</button>                
             </form>
          </div>
       </div>
       <br>
       <hr style="border: 1px groove; width: 131%; margin-left:-20%">
       <table id="tbl1-comment123">
       <br>
       <h3>댓글</h3>
          <c:forEach var="reply" items="${ board.replies }">
                <tr class="level1">
                <td>
                   <sub class="comment-writer">${ reply.writerId }</sub>
                   <sub class="comment-date">${ reply.createDate }</sub>
                   <br>
                   <span>${ reply.content }</span>
                </td>
                <td>
                   <c:if test="${ not empty loginMember && loginMember.id == reply.writerId }">
                      <button  type="button" id="replyDelete" replyNo="${ reply.no }">삭제</button>
                   </c:if>
                </td>
             </tr>
          </c:forEach>
          <br>
      </table>
<br><br>
   </section>   

</body>

<script>
   $(document).ready(() => {
      $('#btnDelete').on('click', () => {
         if(confirm('에에에~? 진짜 삭제할꺼야? 정말 정말?? 진짜루 ?????? 정말????????')) {
            location.replace('${ path }/board/delete?no=${ board.no }');
         }
      });
      $('#replyDelete').on('click', (event) => {
         var replyNo = $(event.target).attr('replyNo');
          if(confirm('에에에~? 진짜 삭제할꺼야?')) {
              location.href='${ path}/reply/delete?no=' + replyNo;
          }
       });
      
      $('#fileDown').on('click', () => {
         let oname = encodeURIComponent('${ board.originalFileName }');
         let rname = encodeURIComponent('${ board.renamedFileName }');
         
         location.assign('${ path }/board/fileDown?oname=' + oname + '&rname=' + rname);
      });
      
      
      var objreply = document.getElementById("replyContent");
      
      
      $('#replyContent').on('focus', () => {
         if(${ empty loginMember}) {
            alert('로그인 후 이용해 주세요.');
            $('#loginbutton').focus();
         }
      });
      
      $('#btn-insert').on('focus', () => {
         if(${ empty loginMember}) {
            alert('로그인 후 이용해 주세요.') ;
            $('#loginbutton').focus();
         }
      });
      
      
   });
</script>
<jsp:include page="/views/common/footer.jsp" /> 