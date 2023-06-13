if (sessionStorage.getItem("userName") != null) {
  var userName = sessionStorage.getItem("userName");

  // 找到要移除元素的父元素
  var parentElement = document.querySelector("#nav-login");
  // 檢查父元素是否存在
  if (parentElement) {
    // 創建新的元素
    var newElement = document.createElement("li");
    newElement.classList.add("nav-item", "dropdown");
    // 創建新的元素內容
    newElement.innerHTML =
      `<a class="nav-link" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">` 
      + userName + `<i class="fa fa-angle-down"></i></a>
      <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        <a class="dropdown-item" href="/ski/member/memberInfo.html">會員中心</a>
        <div class="dropdown-divider"></div>
        <a id="logout" class="dropdown-item">登出</a>
      </div>`;
    // 替換父元素的子元素
    parentElement.parentNode.replaceChild(newElement, parentElement);
  }
} 

// ----------------------------------------分隔線----------------------------------------

var logoutReq = function () {
  $.ajax({
    url: "/ski/member/logout",
    type: "POST",
    dataType: "json", //指定回傳的資料格式
    success: function (resp) {
      sessionStorage.clear();
      alert(resp);
      location = "/ski/index.html";
    },
    error: function (xhr, status, error) {
      console.log("xhr=" + xhr);
      console.log("status=" + status);
      console.log("error=" + error);
    },
  });
};

$("#logout").click(function (e) {
  e.preventDefault();
  logoutReq();
});
