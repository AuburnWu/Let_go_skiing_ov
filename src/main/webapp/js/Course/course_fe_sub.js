const urlParams = new URLSearchParams(window.location.search);
const courseID = urlParams.get("courseID");

$(document).ready(function () {
  getCourseInfo();

  $(".addCart").on("click", function (event) {
    userID = sessionStorage.getItem("userID");

    $.ajax({
      url: "http://localhost:8080/ski/course_AC",
      type: "POST",
      dataType: "json",
      data: {
        userID: userID,
        courseID: courseID,
      },
      success: function (response) {
        //  if (response) {
        //    alert("加入購物車成功");
        //  } else {
        //    alert("操作失敗，請聯絡客服人員");
        // }
      },
      error: function () {
        console.log("error");
      },
    });
  });
});

function getCourseInfo() {
  // Perform AJAX request to fetch course data
  $.ajax({
    url: "http://localhost:8080/ski/course_GBI",
    data: { courseID: courseID },
    type: "POST",
    success: function (response) {
      const courseDate = moment(response.courseDate).format("YYYY-MM-DD");
      const startDate = moment(response.startDate).format("YYYY-MM-DD");
      const endDate = moment(response.endDate).format("YYYY-MM-DD");

      var courseSkill = "";
      if (response.skill == 0) {
        courseSkill = "單板";
      } else if (response.skill == 1) {
        courseSkill = "雙版";
      }

      var courseLevel = "";
      if (response.level == 0) {
        courseLevel = "初階";
      } else if (response.level == 1) {
        courseLevel = "中階";
      } else if (response.level == 2) {
        courseLevel = "高階";
      }

      $("#courseID").val(response.courseID);
      $("#courseName").text(response.courseName);
      $("#coursePrice").text("$ " + response.coursePrice);
      $("#courseSkill").text(courseSkill);
      $("#courseLevel").text(courseLevel);

      $("#courseLocation").text("上課地點 : " + response.skiLocation.pointName);
      $("#courseDate").text("上課日期 : " + courseDate);

      $("#coursePeriod").html("報名期間 :<br/>" + startDate + " ~ " + endDate);
      $("#coursePerson").html(
        "報名人數 : <br/>" + response.coursePerson + " / " + response.courseMax
      );
      console.log(response.coachID);
      var data = {
        userID: response.coachID,
      };
      var jsonData = JSON.stringify(data);
      getCoachName(jsonData);

      const base64Image = btoa(
        new Uint8Array(response.coursePhoto).reduce(
          (data, byte) => data + String.fromCharCode(byte),
          ""
        )
      );
      const imageSrc = `data:image/png;base64,${base64Image}`;

      $("#coursePhoto").attr("src", imageSrc);

      $("#courseIntroduce").text(response.courseIntroduce);
    },
    error: function (status, error) {
      console.error("Request failed. Status:", status, "Error:", error);
    },
  });
}

function getCoachName(jsonData) {
  $.ajax({
    url: "/ski/member/getOneMember",
    type: "POST",
    data: jsonData,
    processData: false,
    contentType: false,
    success: function (response) {
      console.log(response);
      $("#courseCouch").text("開課教練 : " + response.userName);
    },
    error: function () {
      alert("教練名稱取得失敗");
    },
  });
}
