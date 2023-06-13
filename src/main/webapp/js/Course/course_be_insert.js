const form = $("#courseForm");

//地點選單生成
$(document).ready(function () {
  $.ajax({
    url: "/ski/loc/backend_selectmap",
    type: "GET",
    processData: false,
    contentType: false,
    success: function (response) {
      response.forEach(function (Data) {
        option = $("<option>", {
          value: Data.pointID,
          text: Data.pointName,
        });

        $("#courseLocation").append(option);
      });
    },
    error: function () {
      alert("新增失敗");
    },
  });


//教練選單建立
  $.ajax({
    url: "/ski/member/getAllCoach",
    type: "GET",
    processData: false,
    contentType: false,
    success: function (response) {
      response.forEach(function (Data) {
        userID = Data.userID;
        var data = {
          userID: userID,
        };
        var jsonData = JSON.stringify(data);
        getCoachName(jsonData);
      });
    },
    error: function () {
      alert("教練選單建立失敗");
    },
  });
});

// Add event listener for form submission
$("#addButton").on("click", function (event) {
  event.preventDefault(); // Prevent form from being submitted

  // Reset error messages
  $(".text-danger").addClass("d-none");

  // Validate form inputs
  let isValid = true;

  form
    .find(
      'select, input[type="text"], input[type="file"], input[type="date"], textarea'
    )
    .each(function () {
      const input = $(this);

      const value = input.val();
      if (value === null || value === "") {
        const errorMsg = input.parent().parent().siblings(".text-danger");
        errorMsg.removeClass("d-none");
        isValid = false;
      }
    });

  if (isValid) {
    // Get form values
    const courseSkill = document.getElementById("courseSkill").value;
    const courseLevel = document.getElementById("courseLevel").value;
    const courseName = document.getElementById("courseName").value;
    const courseCoach = document.getElementById("courseCoach").value;
    const courseLocation = document.getElementById("courseLocation").value;
    const courseDate = document.getElementById("courseDate").value;
    const startDate = document.getElementById("startDate").value;
    const endDate = document.getElementById("endDate").value;
    const coursePrice = document.getElementById("coursePrice").value;
    const courseMax = document.getElementById("courseMax").value;
    const courseMin = document.getElementById("courseMin").value;
    const courseStatus = document.getElementById("courseStatus").value;
    const coursePhoto = document.getElementById("coursePhoto").files[0];
    const courseIntroduce = document.getElementById("courseIntroduce").value;

    // Create FormData object to store form data
    const formData = new FormData();

    formData.append("courseSkill", courseSkill);
    formData.append("courseLevel", courseLevel);
    formData.append("courseName", courseName);
    formData.append("courseCoach",courseCoach);
    formData.append("courseLocation", courseLocation);
    formData.append("courseDate", courseDate);
    formData.append("startDate", startDate);
    formData.append("endDate", endDate);
    formData.append("coursePrice", coursePrice);
    formData.append("courseMax", courseMax);
    formData.append("courseMin", courseMin);
    formData.append("courseStatus", courseStatus);
    formData.append("coursePhoto", coursePhoto);
    formData.append("courseIntroduce", courseIntroduce);

    $.ajax({
      url: "http://localhost:8080/ski/course_IS", 
      type: "POST",
      data: formData,
      processData: false,
      contentType: false,
      success: function (response) {
        alert("新增成功");
        window.location.href = "http://localhost:8080/ski/course/backend_courseMain.html";
      },
      error: function () {
        alert("新增失敗");
      },
    });
  }
});

function getCoachName(jsonData) {
  $.ajax({
    url: "/ski/member/getOneMember",
    type: "POST",
    data: jsonData,
    processData: false,
    contentType: false,
    success: function (response) {
      var CoachName = response.userName;
      var userID = response.userID;

      option = $("<option>", {
        value: userID,
        text: userID + " - " + CoachName,
      });

      $("#courseCoach").append(option);
    },
    error: function () {
      alert("教練名稱取得失敗");
    },
  });
}
