sessionStorage.clear();

// ----------------------------------------分隔線----------------------------------------

$(document).ready(function () {
  $.ajax({
    url: "/ski/member/getAllCoach",
    method: "GET",
    dataType: "json", //指定回傳的資料格式
    success: function (resp) {
      var coachList = resp;
      var tbody = $("#allCoach tbody");
      tbody.empty();
      $.each(coachList, function (index, coach) {
        var row = $("<tr></tr>");
        row.append($("<td></td>").text(coach.coachID));
        row.append($("<td></td>").text(coach.userID));
        var skillText;
        if (coach.skill == "0") {
          skillText = "單板";
        } else if (coach.skill == "1") {
          skillText = "雙板";
        } else if (coach.skill == "2") {
          skillText = "兩者";
        }
        row.append($("<td></td>").text(skillText));
        row.append($("<td></td>").text(coach.background));
        row.append($("<td></td>").text(coach.introduce));

        const imgElement = $("<img class='preview-image' src='#' alt='預覽圖片' class='fixed-image' style='max-width: 150px' />");
        const tdElement = $("<td></td>").append(imgElement);
        row.append(tdElement);

        const preview = tdElement.find(".preview-image");
        const avatar = coach.license;
        const uint8Array = new Uint8Array(avatar);
        const blob = new Blob([uint8Array]);
        preview.attr("src", URL.createObjectURL(blob));

        // 按鈕欄位
        var detailsBtn = $("<td></td>");
        var detailsBtnHtml = `<button class="btn btn-primary btn-sm details-btn" data-member-id="${coach.userID}">詳細資訊</button>`;
        detailsBtn.html(detailsBtnHtml);
        row.append(detailsBtn);
        tbody.append(row);

        // 審核欄位
        var approveBtn = $("<button></button>")
          .addClass("btn btn-success btn-sm approve-btn")
          .attr("data-member-id", coach.userID)
          .text("符合資格")
          .prop("disabled", coach.applyStatus == "1" || coach.applyStatus == "2");
        var rejectBtn = $("<button></button>")
          .addClass("btn btn-danger btn-sm reject-btn")
          .attr("data-member-id", coach.userID)
          .text("駁回申請")
          .prop("disabled", coach.applyStatus == "1" || coach.applyStatus == "2");

        // 新增操作欄位
        var actionTd = $("<td></td>").append(approveBtn).append(rejectBtn);
        row.append(actionTd);

        var applyStatusText;
        if (coach.applyStatus == "0") {
          applyStatusText = "申請待審核";
        } else if (coach.applyStatus == "1") {
          applyStatusText = "申請已通過";
        } else if (coach.applyStatus == "2") {
          applyStatusText = "申請未通過";
        }
        row.append($("<td></td>").text(applyStatusText));
      });
    },
    error: function (xhr, status, error) {
      console.log("xhr=" + xhr);
      console.log("status=" + status);
      console.log("error=" + error);
    },
  });
});

// ----------------------------------------分隔線----------------------------------------

$(document).on("click", ".details-btn", function () {
  const userID = $(this).attr("data-member-id");
  sessionStorage.setItem("userID", userID);
  location = "/ski/member/getOneMemberSave.html";
});

// 符合資格
$(document).on("click", ".approve-btn", function () {
  var userID = $(this).attr("data-member-id");
  console.log("通過: " + userID);
  $(this).addClass("disabled");
  $(".reject-btn[data-member-id='" + userID + "']").addClass("disabled");

  var data = {
    userID: userID
  };
  var jsonData = JSON.stringify(data);
  // console.log(jsonData); // debug

  $.ajax({
    url: "/ski/member/coachInfo",
    type: "POST",
    dataType: "json", //指定回傳的資料格式
    data: jsonData,
    success: function (resp) {
      var data = {
        coachID: resp.coachID,
        applyStatus: "1"
      }
      var jsonData = JSON.stringify(data);
      // console.log(jsonData); // debug

      $.ajax({
        url: "/ski/member/applyStatus",
        type: "POST",
        dataType: "json", //指定回傳的資料格式
        data: jsonData,
        success: function (resp) {
          alert(resp.message)
          location.reload()
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
});

// 駁回申請
$(document).on("click", ".reject-btn", function () {
  var userID = $(this).attr("data-member-id");
  console.log("不通過: " + userID);
  $(this).addClass("disabled");
  $(".approve-btn[data-member-id='" + userID + "']").addClass("disabled");

  var data = {
    userID: userID
  };
  var jsonData = JSON.stringify(data);
  // console.log(jsonData); // debug

  $.ajax({
    url: "/ski/member/coachInfo",
    type: "POST",
    dataType: "json", //指定回傳的資料格式
    data: jsonData,
    success: function (resp) {
      var data = {
        coachID: resp.coachID,
        applyStatus: "2"
      }
      var jsonData = JSON.stringify(data);
      // console.log(jsonData); // debug

      $.ajax({
        url: "/ski/member/applyStatus",
        type: "POST",
        dataType: "json", //指定回傳的資料格式
        data: jsonData,
        success: function (resp) {
          alert(resp.message)
          location.reload()
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
});

