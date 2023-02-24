   <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<link rel="stylesheet" href="${path }/resources/css/common_css/uglyFD-main.css">
<!-- uglyFD-figure / section 부분 script -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
   
   <script src="https://js.tosspayments.com/v1/payment-widget"></script>
    <style>
      #payment-button {
        width: 100%;
        padding: 15px;
        background-color: #3065ac;
        color: white;
        border-radius: 3px;
        font-size: 16px;
        border: none;
        margin-top: 10px;
      }
      .title {
        margin: 0 0 4px;
        font-size: 24px;
        font-weight: 600;
        color: #4e5968;
      }
      .form123 {
        width: 43%;
        text-align: center;
        margin-left: 28.5%;
        margin-top: 50px;
        border: none;
        position: relative;
      }
/* .boardWrite { border:1px solid #d7d5d5; } */
.boardWrite table { table-layout:fixed; color:#353535; line-height:1.5; }
.boardWrite th { width:120px; padding:11px 0 9px 19px; border-top:1px solid #e9e9e9; border-right:1px solid #e9e9e9; font-weight:normal; text-align:left; background:#fbfafa; }
.boardWrite td { width:auto; padding:8px 0 7px 10px; border-top:1px solid #e9e9e9; color:#757575; vertical-align:middle; }
.boardWrite tbody tr:first-child th,
.boardWrite tbody tr:first-child td { border-top:0; }
.boardWrite img { vertical-align:middle; }
.boardWrite a { text-decoration:none; }
.boardWrite input { height:22px; padding:0 0 0 9px; border:1px solid #d5d5d5; line-height:22px; }
.boardWrite input[type=radio],
.boardWrite input[type=checkbox] { width:13px; height:13px; margin:0 5px 0 0; padding:0; border:0; }
.boardWrite select { margin:0 4px 0 0; }
.inputline{
            width: 100%;
            position: relative;
}
.format12 {
    margin-left: 21%;
}

.title1 {
        margin: 0 0 4px;
        font-size: 24px;
        font-weight: 600;
        color: #4e5968;
        margin-left: 20px;
      }
      .format123 {
    margin-left: 10%;
      }
      .allprice {
        font-size: large;
        text-align: center;
        margin-left: 30%;
        margin-top: 1%;
        margin-bottom: -6%;
      }


    </style>
  </head>
  <body>
  <jsp:include page="/views/common/header.jsp" />
    <div class="form123">
        <div class="boardWrite">
        <details open>
            <summary class="title">상품 정보</summary>
            <table class="format123">
                <tr>
                    <th rowspan="4"> <img src="${path }/resources/images/ugly-logo.png">
                <tr>
                    <th scope="row" style="text-align: center;" >상품명</th>
                    <td><input class="inputline" type="text" placeholder="상품이름"></td>
                </tr>
                <tr>
                    <th scope="row" style="text-align: center;" >상품가격</th>
                    <td><input class="inputline" type="text" placeholder="상품가격"></td>
                </tr>
                <tr>
                    <th scope="row" style="text-align: center;" >상품 수량</th>
                    <td><input class="inputline" type="text" placeholder="상품 수량" style="border: none;"></td>
                </tr>
            </table>
            <hr>
            <table class="allprice">
                <th > 총 금액  :    </th>
                <td><input class="inputline" type="text" placeholder="총 금액12312312312" style="border: none;" readonly></td>
            </table>
            
        </details>
        </div>
    </div>
    <!-- 상품 정보 영역-->
    
    <div class="form123">
        <div class="boardWrite">
            <table summary="" class="format12">
                <hr>
                <div class="title1">배송 주소지 입력</div>

                <tr>
                    <th scope="row" style="text-align: center;" >배송지명</th>
                    <td><input class="inputline" type="text" placeholder="배송지이름"></td> 
                </tr>
                <tr>
                    <th scope="row" style="text-align: center;">성명</th>
                    <td><input class="inputline" type="text" placeholder="성명"></td>
                </tr>
                <tr>
                    <th scope="row" style="text-align: center;">이메일</th>
                    <td><input class="inputline" type="text" placeholder="이메일"></td>
                </tr>
                <tr>
                    <th scope="row" style="text-align: center;">주소</th>
                    <td>
                        <input class="inputline" type="text" placeholder="주소">
                        
                    </td>
                </tr>
                <tr>
                    <th scope="row" style="text-align: center;">전화번호</th>
                    <td><input class="inputline" type="text" placeholder="전화번호"></td>
                </tr>
            </table>
        </div>
    
    </table>

      <br />
      <hr />
      <br />
      <!-- 결제 방법 영역-->
      <div class="title">결제 방법</div>
      <br />
      <div id="payment-method"></div>
      <div id="agreement"></div>
      <button id="payment-button">결제하기</button>
    </div>
  </body>

  <script>
    const clientKey = "test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq"; // 상점을 특정하는 키
    const customerKey = "YbX2HuSlsC9uVJW6NMRMj"; // 결제 고객을 특정하는 키
    const amount = 15_000; // 결제 금액
    const couponAmount = 5_000; // 할인 쿠폰 금액

//     /*결제위젯 영역 렌더링*/
    const paymentWidget = PaymentWidget(clientKey, customerKey); // 회원 결제
//     const paymentWidget = PaymentWidget(clientKey, PaymentWidget.ANONYMOUS) // 비회원 결제
    paymentMethods = paymentWidget.renderPaymentMethods(
      "#payment-method",
      amount
    );

    /*약관 영역 렌더링*/
    const paymentAgreement = paymentWidget.renderAgreement("#agreement");

    /*결제창 열기*/
    document.querySelector("#payment-button").addEventListener("click", () => {
      paymentWidget
        .requestPayment({
          orderId: "AD8aZDpbzXs4EQa-UkIX6",
          orderName: "토스 티셔츠",
          successUrl: "http://localhost:8080/success",
          failUrl: "http://localhost:8080/fail",
          customerEmail: "customer123@gmail.com",
          customerName: "김토스",
        })
//         .catch(function (error) {
//           if (error.code === "USER_CANCEL") {
//             // 결제 고객이 결제창을 닫았을 때 에러 처리	
//           }
//           if (error.code === "INVALID_CARD_COMPANY") {
//             // 유효하지 않은 카드 코드에 대한 에러 처리
//           }
//         });
    });
    
//     $('#payment-button').on('click',()=>{
    	  
//      	 alert('결제가 완료되었습니다.'); 
//      	 location.href='${path}/main/home.do';
     	  
//        });
    
   
    
  </script>
  
  <jsp:include page="/views/common/footer.jsp"/>