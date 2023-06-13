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
      userID: sessionStorage.getItem("userID")
    };
    var jsonData = JSON.stringify(data);
    // console.log(jsonData); // debug
   
    const preview = $("#preview");
  
    $.ajax({
    url: "/ski/member/memberInfo",
    method: "POST",
    dataType: "json", //指定回傳的資料格式
    data: jsonData,
    success: function (resp) {
      const avatar = resp.photo;
      const uint8Array = new Uint8Array(avatar);
      const blob = new Blob([uint8Array]);
      preview.attr('src', URL.createObjectURL(blob));
  
      $("#email").text(resp.email);
      $("#userName").text(resp.userName);
      $("#nickName").val(resp.nickName);
      var genderText;
      if (resp.gender == "m") {
        genderText = "男";
      } else if (resp.gender == "f") {
        genderText = "女";
      }
      $("#gender").text(genderText);
      var formattedDate = moment(resp.birthDate, "MMM DD, YYYY, hh:mm:ss A").format("YYYY-MM-DD");
      // console.log(formattedDate); 
      $("#birthDate").text(formattedDate);
      $("#personID").val(resp.personID);
      $("#phone").val(resp.phone);
      $("#address").val(resp.address);
      $("#level").val(resp.level);
    },
    error: function (xhr, status, error) {
        console.log("xhr=" + xhr);
        console.log("status=" + status);
        console.log("error=" + error);
    },
    });
})