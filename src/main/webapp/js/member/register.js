$(document).ready(function() {
  const email = document.getElementById("email");
  const password = document.getElementById("password");
  const passwordAgain = document.getElementById("passwordAgain");
  const userName = document.getElementById("userName");
  const birthDate = document.getElementById("birthDate");

  email.addEventListener("blur", function (event) {
    if ($("#email").val().trim() === "") {
      $("#email-text-danger").html("此欄位必填");
    } else {
      $("#email-text-danger").html("");
    }
  });
  password.addEventListener("blur", function (event) {
    if ($("#password").val().trim() === "") {
      $("#password-text-danger").html("此欄位必填");
    } else {
      $("#password-text-danger").html("");
    }
  });
  passwordAgain.addEventListener("blur", function (event) {
    if ($("#passwordAgain").val().trim() === "") {
      $("#passwordAgain-text-danger").html("此欄位必填");
    } else {
      $("#passwordAgain-text-danger").html("");
    }
  });
  userName.addEventListener("blur", function (event) {
    if ($("#userName").val().trim() === "") {
      $("#userName-text-danger").html("此欄位必填");
    } else {
      $("#userName-text-danger").html("");
    }
  });
  birthDate.addEventListener("blur", function (event) {
    if (birthDate.value === "") {
      $("#birthDate-text-danger").html("此欄位必填");
    } else {
      $("#birthDate-text-danger").html("");
    }
  });

  var registerReq = function () {
    var password = document.getElementById("password").value;
    var passwordAgain = document.getElementById("passwordAgain").value;
    if (password != passwordAgain) {
      alert("輸入的密碼與確認密碼不一致，請重新輸入");
      return;
    }

    var data = {
      email: $("#email").val(),
      password: $("#password").val(),
      userName: $("#userName").val(),
      gender: $("input[name='gender']:checked").val(),
      birthDate: $("#birthDate").val(),
      level: $("select[name='level']").val(),
      // role: "user",
      userStatus: "0",
    };
    var jsonData = JSON.stringify(data);
    console.log(jsonData); // debug
    $.ajax({
      url: "/ski/member/register",
      type: "POST",
      dataType: "json", //指定回傳的資料格式
      data: jsonData,
      success: function (resp) {
        if (resp.successful == false) {
          alert(resp.message);
          return;
        }
        var date = moment(resp.birthDate);
        var formattedDate = date.format("YYYY-MM-DD");
        console.log(formattedDate); // debug

// ----------------------------------------分隔線----------------------------------------  

        $.ajax({
          url: "/ski/member/defaultPhoto",
          method: "POST",
          dataType: "json", //指定回傳的資料格式
          data: {
            "userID":resp.userID
          },
          success: function (resp) {
          var result = confirm("註冊成功，馬上登入！");
            if (result) {
              location = "/ski/member/login.html";
            } else {
              location = "/ski/index.html";
            }
          },
          error: function (xhr, status, error) {
            console.log(xhr);
            console.log(status);
            console.log(error);
          }
        });
      },
      error: function (xhr, status, error) {
        console.log(xhr);
        console.log(status);
        console.log(error);
      },
    });
  };
  $("#registerForm").submit(function (e) {
    e.preventDefault(); // 阻止表單直接提交
    if (
      $("#email").val().trim() != "" &&
      $("#password").val().trim() != "" &&
      $("#passwordAgain").val().trim() != "" &&
      $("#userName").val().trim() != "" &&
      birthDate.value != ""
    ) {
      registerReq();
    } else {
      if ($("#email").val().trim() === "") {
        $("#email-text-danger").html("此欄位必填");
      } else {
        $("#email-text-danger").html("");
      }
      if ($("#password").val().trim() === "") {
        $("#password-text-danger").html("此欄位必填");
      } else {
        $("#password-text-danger").html("");
      }
      if ($("#passwordAgain").val().trim() === "") {
        $("#passwordAgain-text-danger").html("此欄位必填");
      } else {
        $("#passwordAgain-text-danger").html("");
      }
      if ($("#userName").val().trim() === "") {
        $("#userName-text-danger").html("此欄位必填");
      } else {
        $("#userName-text-danger").html("");
      }
      if (birthDate.value === "") {
        $("#birthDate-text-danger").html("此欄位必填");
      } else {
        $("#birthDate-text-danger").html("");
      }
    }
  });
})