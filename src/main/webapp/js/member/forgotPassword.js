var forgotPasswordReq = function () {
  var data = {
    email: $("#email").val(),
  };
  var jsonData = JSON.stringify(data);
  console.log(jsonData); // debug

  $.ajax({
    url: "/ski/member/forgotPassword",
    type: "POST",
    dataType: "json", //指定回傳的資料格式
    data: jsonData,
    success: function (resp) {
      console.log(resp);
      alert(resp.message);
    },
    error: function (xhr, status, error) {
      console.log("xhr=" + xhr);
      console.log("status=" + status);
      console.log("error=" + error);
    },
  });
};

$("#btn_mail").on("click", function () {
  forgotPasswordReq();
});