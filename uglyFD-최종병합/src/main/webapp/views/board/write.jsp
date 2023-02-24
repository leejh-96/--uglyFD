<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<link href="${path}/resources/css/board/write.css" rel="stylesheet"/> 
<link rel="stylesheet" href="${path }/resources/css/common_css/uglyFD-main.css">
<!-- uglyFD-figure / section 부분 script -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

    <body>
    <jsp:include page="/views/common/header.jsp" />
    
    
    <div id="wrap">
      <div id="container">
        <div class="inner">
          <h2>게시글 작성</h2>
          <form id="boardForm" name="boardForm" method="post" enctype="multipart/form-data">
            <table width="100%" class="table02">
              <caption>
                <strong
                  ><span class="t_red">*</span> 표시는 필수입력
                  항목입니다.</strong
                >
              </caption>
              <colgroup>
                <col width="20%" />
                <col width="*" />
              </colgroup>
              <tbody id="tbody">
                <tr>
                  <th>제목<span class="t_red">*</span></th>
                  <td>
                    <input
                      id="board_subject"
                      name="title"
                      value=""
                      class="tbox01"
                    />
                  </td>
                </tr>
                <tr>
                  <th>작성자<span class="t_red">*</span></th>
                  <td>
                    <input
                      id="board_writer"
                      name="writer"
                      value="${ loginMember.id }"
                      class="tbox01" readonly
                    />
                  </td>
                </tr>
                                <tr>
<!--                   <th>첨부파일<span class="t_red">*</span></th> -->
<!--                   <td> -->
<!--                     <input type="file" name="upfile" /> -->
<!--                   </td> -->
                </tr>
                <tr>
                  <th>내용<span class="t_red">*</span></th>
                  <td>
                    <textarea
                      id="board_content"
                      name="content"
                      cols="10"
                      rows="10"
                      class="textarea01"
                    ></textarea>
                  </td>
                </tr>
              </tbody>
            </table>
                      <div class="btn_right mt15">
                      <br>
            <input
              type="reset"
              class="btn black mr5"
              value="목록으로"
              onclick="location.replace('${path}/board/inquire')"
            >
           <input
              type="submit"
              class="btn black"
              value="등록하기"
            >
    </div>
          </form>


          </div>
    <jsp:include page="/views/common/footer.jsp"/>
        </div>
      </div>

    </body>