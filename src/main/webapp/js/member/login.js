// 驗證碼
function displayCode(code) {
  $("#codeShow").text(code);
}

function codeReq() {
  $.ajax({
    url: "/ski/member/code",
    method: "GET",
    dataType: "text", //指定回傳的資料格式
    success: function (code) {
      // 將獲取到的驗證碼顯示在網頁上
      displayCode(code);
    },
    error: function (xhr, status, error) {
      console.error("無法獲取驗證碼：", error);
    },
  });
}

$(document).ready(function () {
  codeReq();
});

// ----------------------------------------分隔線----------------------------------------

// 登入請求
$(document).ready(function() {
  const email = document.getElementById("email");
  const password = document.getElementById("password");
  const code = document.getElementById("code");

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
  code.addEventListener("blur", function (event) {
    if ($("#code").val().trim() === "") {
      $("#code-text-danger").html("此欄位必填");
    } else {
      $("#code-text-danger").html("");
    }
  });

  var loginReq = function () {

    var code = $("#code").val();
    $.ajax({
      url: "/ski/member/code",
      type: "POST",
      dataType: "json", //指定回傳的資料格式
      data: {
        "code": code,
      },
      success: function (resp) {
        if(resp == false){
          alert("驗證碼輸入錯誤，請重新輸入");
          codeReq();
          return;
        }
        var data = {
          email: $("#email").val(),
          password: $("#password").val(),
        };
        var jsonData = JSON.stringify(data);
        // console.log(jsonData); // debug
    
        $.ajax({
          url: "/ski/member/login",
          type: "POST",
          dataType: "json", //指定回傳的資料格式
          data: jsonData,
          success: function (resp) {
            if(resp.successful == false){
              alert(resp.message);
              return;
            } 
            sessionStorage.setItem("userID", resp.userID);
            sessionStorage.setItem("userName", resp.userName);
            location = "/ski/index.html";
          },
          error: function (xhr, status, error) {
            console.log("xhr=" + xhr);
            console.log("status=" + status);
            console.log("error=" + error);
          },
        });
    
      },
      error: function (xhr, status, error) {
        console.log("xhr=" + xhr);
        console.log("status=" + status);
        console.log("error=" + error);
      },
    });
  };

  $("#loginForm").submit(function (e) {
    e.preventDefault(); // 阻止表單直接提交
    if ($("#email").val().trim() != "" && $("#password").val().trim() != "" && $("#code").val().trim() != "") {
      loginReq();
    } else{
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
      if ($("#code").val().trim() === "") {
        $("#code-text-danger").html("此欄位必填");
      } else {
        $("#code-text-danger").html("");
      }
    }
  });
})
