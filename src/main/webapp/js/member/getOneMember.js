// 預覽圖片
function previewImage(event) {
  var input = event.target;
  var reader = new FileReader();

reader.onload = function () {
  var preview = document.getElementById("preview");
  preview.src = reader.result;
  preview.style.display = "block";
};
reader.readAsDataURL(input.files[0]);
}
// 監聽上傳事件
var photo = document.getElementById("photo");
photo.addEventListener("change", previewImage);

// ----------------------------------------分隔線----------------------------------------  

$(document).ready(function() { 
  var data = {
    userID: sessionStorage.getItem("userID"),
  };
  var jsonData = JSON.stringify(data);
  // console.log(jsonData); // debug

  const preview = $("#preview");

  $.ajax({
    url: "/ski/member/getOneMember",
    method: "POST",
    dataType: "json",
    data: jsonData,
    success: function (resp) {
      const avatar = resp.photo;
      const uint8Array = new Uint8Array(avatar);
      const blob = new Blob([uint8Array]);
      preview.attr('src', URL.createObjectURL(blob));

      $("#userID").text(resp.userID);
      $("#email").val(resp.email);
      $("#userName").val(resp.userName);
      $("#nickName").val(resp.nickName);

      if (resp.gender == "m") {
        document.getElementById("male").checked = true;
      } else if (resp.gender == "f") {
        document.getElementById("female").checked = true;
      }

      document.getElementById("birthDate").value = new Date(resp.birthDate).toISOString().substring(0, 10);

      $("#personID").val(resp.personID);
      $("#phone").val(resp.phone);
      $("#address").val(resp.address);
      $("#level").val(resp.level);

      var userStatusText;
      if (resp.userStatus == "0") {
        userStatusText = "啟用";
      } else if (resp.userStatus == "1") {
        userStatusText = "停權";
      }
      $("#userStatus").text(userStatusText);
    },
    error: function (xhr, status, error) {
      console.log("xhr=" + xhr);
      console.log("status=" + status);
      console.log("error=" + error);
    },
  });
})