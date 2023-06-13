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
    console.log(resp)
    const avatar = resp.photo;
    const uint8Array = new Uint8Array(avatar);
    const blob = new Blob([uint8Array]);
    
    preview.attr('src', URL.createObjectURL(blob));

    $("#userID").text(resp.userID);
    $("#email").text(resp.email);
    $("#userName").text(resp.userName);
    $("#nickName").text(resp.nickName);

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

    $("#personID").text(resp.personID);
    $("#phone").text(resp.phone);
    $("#address").text(resp.address);

    var level = resp.level;
    var levelMap = {
      "0": "初學者",
      "1": "中階者",
      "2": "高階者"
    };
    $("#level").text(levelMap[level]);

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

$("#btn_return").on("click", function (){
  location = "/ski/member/coachReview.html";
})