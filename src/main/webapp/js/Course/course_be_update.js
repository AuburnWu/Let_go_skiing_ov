// Usage example: Call the function with the course ID to retrieve the information
const urlParams = new URLSearchParams(window.location.search);
const courseID = urlParams.get("courseID");

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
          alert("新增失敗");
        },
      });

  getCourseInfo();

  // Get form element by ID
  const form = $("#courseForm");

  // Add event listener for form submission
  $("#addButton").on("click", function (event) {
    event.preventDefault(); // Prevent form from being submitted
    // Reset error messages

    // Validate form inputs
    let isValid = true;

    form
      .find('select, input[type="text"], input[type="date"], textarea')
      .each(function () {
        const input = $(this);
        const value = input.val();
        if (value === null || value === "") {
          const errorMsg = input.parent().parent().siblings(".text-danger");
          errorMsg.removeClass("d-none");
          isValid = false;
        }
      })
      .find('select, input[type="file"]')
      .each(function () {
        const input = $(this);
        const value = input.val();
        const src = $("#myImage").attr("src");
        if (!src) {
          if (value === null || value === "") {
            const errorMsg = input.parent().parent().siblings(".text-danger");
            errorMsg.removeClass("d-none");
            isValid = false;
          }
        }
      });
    console.log(isValid);
    if (isValid) {
      // Get form values
      const courseSkill = document.getElementById("courseSkill").value;
      const courseLevel = document.getElementById("courseLevel").value;
      const courseName = document.getElementById("courseName").value;
      const courseCoach = document.getElementById("courseCoach").value;
		const coursePerson = document.getElementById("coursePerson").value;
      const courseLocation = document.getElementById("courseLocation").value;
      const courseDate = document.getElementById("courseDate").value;
      const startDate = document.getElementById("startDate").value;
      const endDate = document.getElementById("endDate").value;
      const coursePrice = document.getElementById("coursePrice").value;
      const courseMax = document.getElementById("courseMax").value;
      const courseMin = document.getElementById("courseMin").value;
      const courseStatus = document.getElementById("courseStatus").value;
      const courseIntroduce = document.getElementById("courseIntroduce").value;

      let coursePhoto = document.getElementById("coursePhoto").files[0];
      if (!coursePhoto) {
        const originalBase64 =
          document.getElementById("originalImage").dataset.originalImage; // 獲取 originalBase64 的值
        if (typeof originalBase64 === "undefined") {
          console.error("原始圖片的URL格式不正確，無法提取base64數據。");
          return;
        }
        const cleanedBase64 = originalBase64.replace(/(\r\n|\n|\r)/gm, "");
        const blob = base64ToBlob(cleanedBase64, "image/png");
        coursePhoto = new File([blob], "coursePhoto.png", { type: blob.type });
      }

      // Create FormData object to store form data
      const formData = new FormData();
      formData.append("courseID", courseID);
      formData.append("courseSkill", courseSkill);
      formData.append("courseLevel", courseLevel);
      formData.append("courseName", courseName);
      formData.append("courseCoach", courseCoach);
      formData.append("courseLocation", courseLocation);
      formData.append("courseDate", courseDate);
      formData.append("startDate", startDate);
      formData.append("endDate", endDate);
      formData.append("coursePrice", coursePrice);
       formData.append("coursePerson", coursePerson);
      formData.append("courseMax", courseMax);
      formData.append("courseMin", courseMin);
      formData.append("courseStatus", courseStatus);
      formData.append("coursePhoto", coursePhoto);
      formData.append("courseIntroduce", courseIntroduce);

      $.ajax({
        url: "http://localhost:8080/ski/course_UD",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function (response) {
          alert("更新成功");
          window.location.href =
            "http://localhost:8080/ski/course/backend_courseMain.html";
        },
        error: function () {
          alert("更新失敗");
        },
      });
    }
  });

  document
    .getElementById("coursePhoto")
    .addEventListener("change", handleImageChange);
});

// Function to retrieve course information by ID
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

      $("#courseSkill").val(response.skill);
      $("#courseLevel").val(response.level);
      $("#courseName").val(response.courseName);
      console.log("response.coachID  " + response.coachID);
      $("#courseCoach").val(response.coachID);
      $("#courseLocation").val(response.skiLocation.pointID);
      $("#courseDate").val(courseDate);
      $("#startDate").val(startDate);
      $("#endDate").val(endDate);
      $("#coursePrice").val(response.coursePrice);
      $("#coursePerson").val(response.coursePerson);
      $("#courseMax").val(response.courseMax);
      $("#courseMin").val(response.courseMin);
      $("#courseStatus").val(response.courseStatus);

      const base64Image = btoa(
        new Uint8Array(response.coursePhoto).reduce(
          (data, byte) => data + String.fromCharCode(byte),
          ""
        )
      );

      const imageSrc = `data:image/png;base64,${base64Image}`;
      document.getElementById("originalImage").src = imageSrc;
      document.getElementById("originalImage").dataset.originalImage =
        base64Image;

      $("#courseIntroduce").val(response.courseIntroduce);
    },
    error: function (status, error) {
      console.error("Request failed. Status:", status, "Error:", error);
    },
  });
}

function base64ToBlob(base64) {
  var binary = atob(base64);
  var bytes = new Uint8Array(binary.length);
  for (var i = 0; i < binary.length; i++) {
    bytes[i] = binary.charCodeAt(i);
  }
  return new Blob([bytes], { type: "image/png" });
}

function handleImageChange() {
  const selectedImage = document.getElementById("coursePhoto").files[0];
  if (selectedImage) {
    const reader = new FileReader();
    reader.onload = function (event) {
      document.getElementById("originalImage").src = event.target.result;
      document.getElementById("originalImage").dataset.originalImage =
        event.target.result;
    };
    reader.readAsDataURL(selectedImage);
  } else {
    const originalImageURL =
      document.getElementById("originalImage").dataset.originalImage;
    document.getElementById("originalImage").src = originalImageURL;
  }
}

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
        text: CoachName,
      });

      $("#courseCoach").append(option);
    },
    error: function () {
      alert("教練名稱取得失敗");
    },
  });
}
