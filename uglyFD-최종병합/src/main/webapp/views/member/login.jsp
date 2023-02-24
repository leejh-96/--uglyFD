<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/bootstrap/css/bootstrap.css">
    <link href="${path}/resources/css/member/login.css" rel="stylesheet"/> 


<!-- LOGIN MODULE -->
        <div class="login">
            <div class="wrap">

                <!-- LOGIN FORM -->
                <div class="user">
                    <div class="logo">
                        <a href="${path }"><img src="http://res.cloudinary.com/dpcloudinary/image/upload/v1506186248/logo.png" alt=""></a>
                    </div>
                    <!-- TOGGLE -->
                    <div id="toggle-wrap">
                        <div id="toggle-terms">
                            <div id="cross">
                                <span></span>
                                <span></span>
                            </div>
                        </div>
                    </div>

                    <!-- FORM -->
                    <div class="form-wrap">
                        <!-- TABS -->
                      <div class="tabs">
                            <h3 class="login-tab"><a class="log-in active"><span>로그인<span></a></h3>
                      </div>
                        <!-- TABS CONTENT -->
                      <div class="tabs-content">
                            <!-- TABS CONTENT LOGIN -->
                        <div id="login-tab-content" class="active">
                          <form class="login-form" action="${ path }/login" method="post">
                            <input name="userId" type="text" class="input" id="user_login" autocomplete="off" placeholder="User ID" 
                            value="${ empty cookie.saveId  ? '' : cookie.saveId.value}" required>
                            <input name ="userPwd" type="password" class="input" id="user_pass" autocomplete="off" placeholder="Password">

                            <input type="checkbox" class="checkbox" id="remember_me" checked name="saveId" ${ empty cookie.saveId ? "" : "checked" }>
                            <label for="remember_me" >아이디 저장</label>

                            <input type="submit" class="button" value="Login" >
                          </form>
                          <div>
                              <div class="help-action">
                                  <span><i class="fa fa-arrow-left" aria-hidden="true"></i><a class="forgot" href="#">아이디 찾기</a></span>
                                  <span class="help"><i class="fa fa-arrow-left" aria-hidden="true"></i><a class="forgot" href="#">비밀번호 찾기</a></span>
                                </div>
                            </div>
                        </div>

