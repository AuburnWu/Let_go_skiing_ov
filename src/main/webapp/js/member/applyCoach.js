// 預覽圖片
function previewImage(event) {
  var input = event.target;
  var reader = new FileReader();

reader.onload = function () {
  var preview2 = document.getElementById("preview2");
  preview2.src = reader.result;
  preview2.style.display = "block";
};
reader.readAsDataURL(input.files[0]);
}
// 監聽上傳事件
var license = document.getElementById("license");
license.addEventListener("change", previewImage);

// ----------------------------------------分隔線----------------------------------------  

$(document).ready(function() {
  var data = {
    userID: sessionStorage.getItem("userID")
  };
  var jsonData = JSON.stringify(data);
  // console.log(jsonData); // debug

  const preview = $("#preview");

  $.ajax({
  url: "/ski/member/memberInfo",
  type: "POST",
  dataType: "json", //指定回傳的資料格式
  data: jsonData,
  success: function (resp) {
    const avatar = resp.photo;
    const uint8Array = new Uint8Array(avatar);
    const blob = new Blob([uint8Array]);
    preview.attr('src', URL.createObjectURL(blob));

    $("#email").text(resp.email);
    $("#userName").text(resp.userName);

    var level = resp.level;
    var levelMap = {
      "0": "初學者",
      "1": "中階者",
      "2": "高階者"
    };
    $("#level").text(levelMap[level]);
  },
  error: function (xhr, status, error) {
    console.log("xhr=" + xhr);
    console.log("status=" + status);
    console.log("error=" + error);
  },
  });

  const preview2 = $("#preview2");
  
  $.ajax({
    url: "/ski/member/coachInfo",
    type: "POST",
    dataType: "json", //指定回傳的資料格式
    data: jsonData,
    success: function (resp) {
      if(resp != null){
        switch (resp.applyStatus) {
          case "0":
            $("#skill").val(resp.skill).prop("disabled", true);
            $("#background").val(resp.background).prop("disabled", true);
            $("#introduce").val(resp.introduce).prop("disabled", true);
            $("#preview2").css("display", "block");
            $("#license").css("display", "none");
            // 按鈕 + 提示字
            $("#btn_cancel").addClass("disabled");
            $("#btn_upload").addClass("disabled");
            $("#applyMsg").text("管理員審核中...");
          break;
          case "1":
            $("#skill").val(resp.skill).prop("disabled", true);
            $("#background").val(resp.background).prop("disabled", true);
            $("#introduce").val(resp.introduce).prop("disabled", true);
            $("#preview2").css("display", "block");
            $("#license").css("display", "none");
            // 按鈕 + 提示字
            $("#btn_upload").replaceWith('<button type="button" id="btn_edit" class="btn btn-primary">編輯</button>');
          break;
          case "2":
            $("#skill").val("請選擇");
            $("#background").val("");
            $("#introduce").val("");
            $("#applyMsg").text("審核未通過，請重新申請");
          break;
        }
        const avatar = resp.license;
        const uint8Array = new Uint8Array(avatar);
        const blob = new Blob([uint8Array]);
        preview2.attr('src', URL.createObjectURL(blob));
      }
    },
    error: function (xhr, status, error) {
      console.log("xhr=" + xhr);
      console.log("status=" + status);
      console.log("error=" + error);
    },
  }); 
})