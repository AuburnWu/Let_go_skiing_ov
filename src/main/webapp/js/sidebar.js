$(document).ready(function () {
var data = {
userID: sessionStorage.getItem("userID")
};
var jsonData = JSON.stringify(data);
// console.log(jsonData); // debug

$.ajax({
url: "/ski/member/coachInfo",
type: "POST",
dataType: "json", //指定回傳的資料格式
data: jsonData,
success: function (resp) {
  if(resp.applyStatus == "1"){
    // 選取元素
    var coachInfo = document.getElementById("applyCoach");
    // 修改文字內容
    coachInfo.textContent = "查看教練資訊";

    // 創建新的元素
    var newNavItem = $("<li></li>")
    .addClass("nav-item")
    .append(
      $("<a></a>")
    .addClass("nav-link")
    .attr("href", "/ski/course/frontend_coachMaintain.html")
    .text("教練課程管理")
    );
    $(".nav.flex-column").append(newNavItem);
  }
},
error: function (xhr, status, error) {
  console.log("xhr=" + xhr);
  console.log("status=" + status);
  console.log("error=" + error);
},
});
});