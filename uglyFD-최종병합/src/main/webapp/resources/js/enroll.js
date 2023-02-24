function validate() {
	
	
    var objID = document.getElementById("userId");
    var objPwd1 = document.getElementById("userPwd");
    var objPwd2 = document.getElementById("userPwd2");
    var objEmail = document.getElementById("email");
    var objName = document.getElementById("userName");
    var objpho = document.getElementById("phone");

    var arrNum1 = new Array();
    var arrNum2 = new Array();

    var regul1 = /^[a-zA-Z0-9]{4,12}$/;

    var regul2 =
      /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;

    var regul3 = /^[가-힝a-zA-Z]{2,}$/;

    var regul4 = /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;

    var regul5 =
      /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;

    if (objID.value == "") {
      alert("아이디를 입력하지 않았습니다.");
      objID.focus();
      return false;
    }
    if (
      !check(
        regul1,
        objID,
        "아이디는 4~12자의 대소문자와 숫자로만 입력 가능합니다."
      )
    ) {
      return false;
    }
    if (objPwd1.value == "") {
      alert("비밀번호를 입력해 주세요");
      objPwd1.focus();
      return false;
    }
    if (objPwd2.value == "") {
      alert("비밀번호를 입력해 주세요");
      objPwd2.focus();
      return false;
    }

    if (
      !check(
        regul1,
        objPwd1,
        "비밀번호는 4~12자의 대소문자와 숫자로만 입력 가능합니다."
      )
    ) {
      return false;
    }

    if (objPwd1.value != objPwd2.value) {
      alert("비밀번호가 일치 하지 않습니다.");
      objPwd1.value = "";
      objPwd2.value = "";
      objPwd1.focus();
      objPwd2.focus();
      return false;
    }
    if (objEmail.value == "") {
      alert("이메일을 입력해 주세요");
      objEmail.focus();
      return false;
    }
    if (!check(regul2, objEmail, "이메일을 잘못 입력 했습니다.")) {
      return false;
    }
    if (objName.value == "") {
      alert("이름을 입력해 주세요");
      objName.focus();
      return false;
    }
    if (!check(regul3, objName, "이름이 잘못 되었습니다.")) {
      return false;
    }
    if (objpho.value == "") {
      alert("전화번호를 입력해 주세요");
      objName.focus();
      return false;
    }
    if (!check(regul4, objpho, "전화번호가 잘못 되었습니다.")) {
      return false;
    }
    if (
      document.getElementById("gender").checked != true &&
      document.getElementById("gender").checked != true
    ) {
      alert("성별을 체크해 주세요.");
      document.getElementById("gender").focus();
      return false;
    }
  }
  function check(re, what, message) {
    if (re.test(what.value)) {
      return true;
    }
    alert(message);
    what.value = "";
    what.focus();
  }

//   const agreeChkAll = document.querySelector("input[name=agree_all]");
//   agreeChkAll.addEventListener("change", (e) => {
//     let agreeChk = document.querySelectorAll("input[name=agree]");
//     for (let i = 0; i < agreeChk.length; i++) {
//       agreeChk[i].checked = e.target.checked;
//     }
//   });
	