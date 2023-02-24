<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<link rel="stylesheet" href="${path }/views/mypage/myview.css">
<link rel="stylesheet" href="${path }/resources/css/common_css/uglyFD-main.css">
<!-- uglyFD-figure / section 부분 script -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


<body>
	<jsp:include page="/views/common/header.jsp" />
<p>
    <br><h1 align="center" style="font-weight: bolder;">회원 수정</h1><br><hr><br><br>
</p>

<form action="${path }/mypage/update" method="POST">
<table width="50%" height="80" border="3" align="center" cellpadding="5" cellspacing="0" >
    <tr align="center">
        <td colspan="2" align="cener" bgcolor="beige">
            <div style="font-weight: bold; font-size: 18px; padding : 10px 0;" >회원 기본 정보
            </div></td>
    </tr>
    <tr>
        <td bgcolor="beige" align="center">
            <div style="font-weight: bold; padding : 10px 0;">아이디 :
            </div></td> 
        <td>&nbsp<input type="text" name="userId" id="userId" size="12" maxlength="12" value="${ loginMember.id }" readonly>
          
    </tr>
    <tr>
        <td bgcolor="beige" align="center"><div style="font-weight: bold; padding : 10px 0;">비밀번호 :</div></td>
        <td>&nbsp<input type="password" id="userPwd1" name="userPwd1" size="12" maxlength="12"  value="${ loginMember.password }">
            <p>&nbsp4~12자의 영문 대소문자와 숫자로만 입력</p></td>
    </tr>
    <tr>
        <td bgcolor="beige" align="center"><div style="font-weight: bold; padding : 10px 0;">비밀번호 확인 :</div></td>
        <td>&nbsp<input type="password" id="userPwd2" name="userPwd2" size="12" maxlength="12" value="${ loginMember.password }"></td>
    </tr>
    <tr>
        <td bgcolor="beige" align="center"><div style="font-weight: bold; padding : 10px 0;">메일주소 :</div></td>
        <td>&nbsp<input type="text" name="email" id="email" size="30" maxlength="30" value="${ loginMember.mail }" readonly>
    </tr>
    <tr>
        <td bgcolor="beige" align="center"><div style="font-weight: bold; padding : 10px 0;">이름 :</div></td>
        <td>&nbsp<input type="text" name="userName" id="userName" size="10" maxlength="10" value="${ loginMember.name }"></td>
    </tr>
        <td bgcolor="beige" align="center"><div style="font-weight: bold; padding : 10px 0;">생년월일 :</div></td>
        <td>&nbsp<input type="text" name="birth" id="birth" size="20" value="${ loginMember.birth }" readonly>
        </td>

    </tr>
<!--     <tr>
        <td bgcolor="beige" align="center"><div style="font-weight: bold; padding : 10px 0;">성별 :</div></td>
        <td>&nbsp<input type="radio" name="gender" id="gender" size="10" value="M" checked> 남자 
            <input type="radio" name="gender" id="gender" size="10" value="W" > 여자</td>
    </tr> -->
    <tr>
        <td bgcolor="beige" align="center"><div style="font-weight: bold; padding : 10px 0;">전화번호 :</div></td>
        <td>&nbsp<input  value="${ loginMember.phone }" type="text" name="phone" id="phone" size="20" maxlength="30" >
            <p>&nbspex) 01000000000</p></td>
    </tr>
        <tr>
        <td bgcolor="beige" align="center"><div style="font-weight: bold; padding : 10px 0;">주소 :</div></td>
        <td>&nbsp<input    value="${ loginMember.addr }" type="text" name="addr" id="addr" size="20" maxlength="30">
            <p>&nbspex) 서울시 강남구 역삼동 000-000</p></td>
    </tr>
</table>
    	<br>		 <input type="submit" name="submit" value="완료" style="width: 100px; height: 35px; margin:10px 20px 30px 47%;">
<br><br>

</form>


<br><br>

	
		<jsp:include page="/views/common/footer.jsp" />
</body>