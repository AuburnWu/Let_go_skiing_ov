sessionStorage.clear();

// ----------------------------------------分隔線----------------------------------------

$(document).ready(function() {
  $.ajax({
    url: "/ski/member/getAllMember",
    method: "GET",
    dataType: "json", //指定回傳的資料格式
    success: function (resp) {
      var memberList = resp;
      var tbody = $("#allMember tbody");
      tbody.empty();
      $.each(memberList, function (index, member) {
        var row = $("<tr></tr>");
        row.append($("<td></td>").text(member.userID));
        row.append($("<td></td>").text(member.email));
        row.append($("<td></td>").text(member.userName));
        var genderText;
        if (member.gender == "m") {
          genderText = "男";
        } else if (member.gender == "f") {
          genderText = "女";
        }
        row.append($("<td></td>").text(genderText));
        var levelText;
        if (member.level == "0") {
          levelText = "初學者";
        } else if (member.level == "1") {
          levelText = "中階者";
        } else if (member.level == "2") {
          levelText = "高階者";
        }
        row.append($("<td></td>").text(levelText));
        // 按鈕欄位
        var detailsBtn = $("<td></td>");
        var detailsBtnHtml = `<button class="btn btn-primary btn-sm details-btn" data-member-id="${member.userID}">詳細資訊</button>`;
        detailsBtn.html(detailsBtnHtml);
        row.append(detailsBtn);
        tbody.append(row);
      });
    },
    error: function (xhr, status, error) {
      console.log("xhr=" + xhr);
      console.log("status=" + status);
      console.log("error=" + error);
    },
  });
})

// ----------------------------------------分隔線----------------------------------------

$(document).on("click", ".details-btn", function () {
  const userID = $(this).attr("data-member-id");
  sessionStorage.setItem("userID", userID);
  location = "/ski/member/getOneMember.html";
});