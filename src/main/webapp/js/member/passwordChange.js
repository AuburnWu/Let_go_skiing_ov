if (window.location.search.includes("from=mail")) {
  $('label[for="oPassword"], input#oPassword').hide();

  // 獲取完整的 URL 查詢參數部分
  var queryString = window.location.search;
  // 創建一個 URLSearchParams 物件，並將查詢參數部分傳入
  var params = new URLSearchParams(queryString);
  // 使用 get() 方法獲取指定參數的值
  var userID = params.get('userID');
  console.log(userID);
}

var pwdChangeReq = function () {
  var nPassword = document.getElementById("nPassword").value;
  var nPasswordAgain = document.getElementById("nPasswordAgain").value;
  if (nPassword != nPasswordAgain) {
    alert("新密碼與確認密碼不一致，請重新輸入");
    return;
  }

  if (window.location.search.includes("from=mail")) {
    console.log("from=mail");

    var data = {
      userID: userID,
      password: $("#nPassword").val(),
    };
    var jsonData = JSON.stringify(data);
    console.log("jsonData = " + jsonData); // debug
    console.log("data = " + data)

    $.ajax({
      url: "/ski/member/setPassword",
      type: "POST",
      dataType: "json", //指定回傳的資料格式
      data: data,
      success: function (resp) {
        if(resp.successful == false){
          alert(resp.message);
        }
        alert(resp.message);
        location = "/ski/member/login.html";
      },
      error: function (xhr, status, error) {
        console.log("xhr=" + xhr);
        console.log("status=" + status);
        console.log("error=" + error);
      },
    });
    return;  
  }

// ----------------------------------------分隔線----------------------------------------

  var data = {
    userID: sessionStorage.getItem("userID"),
    password: $("#oPassword").val(),
  };
  var jsonData = JSON.stringify(data);
  // console.log(jsonData); // debug

  $.ajax({
    url: "/ski/member/checkPassword",
    type: "POST",
    dataType: "json", //指定回傳的資料格式
    data: jsonData,
    success: function (resp) {
      if(resp.successful == false){
        alert(resp.message);
        return;
      }
      var data = {
        userID: sessionStorage.getItem("userID"),
        password: $("#nPassword").val(),
      };
      var jsonData = JSON.stringify(data);
      // console.log(jsonData); // debug

      $.ajax({
        url: "/ski/member/passwordChange",
        type: "POST",
        dataType: "json", //指定回傳的資料格式
        data: jsonData,
        success: function (resp) {
          if(resp.successful == true){
            sessionStorage.clear();
            alert(resp.message);
            location = "/ski/member/login.html";
          }
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

$("#pwdChangeForm").submit(function (e) {
  e.preventDefault();
  pwdChangeReq();
});