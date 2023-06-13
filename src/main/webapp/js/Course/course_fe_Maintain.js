var deleteID;
$(document).ready(function () {
  const urlParams = new URLSearchParams(window.location.search);
  const userID = sessionStorage.getItem("userID");
  // var data = { userID: userID };
  // var jsonData = JSON.stringify(data);
  // getCoachID(jsonData);

  $("#courseCoach").val(userID);
  getCoachCourse(userID);
  locSelctBuild();

  resetInputState();

  // 綁定欄位輸入事件
  $(
    "#courseSkill, #courseLevel, #courseName, #courseLocation, #courseDate, #startDate, #endDate,#coursePrice,#courseMax,#courseMin,#courseStatus,#skiImage,#courseIntroduce"
  ).on("input change", function () {
    validateInputField($(this));
  });

  $("#insert_btn").on("click", function (event) {
    resetForm();
    var courseID;
    merge(courseID);
  });

  //更新事件綁定
  $(".card_zone").on("click", ".update_btn", function (event) {
    courseID = $(this)
      .closest(".btnGroup")
      .siblings(".textZone")
      .find(".courseID")
      .val();
    getCourseInfo(courseID);
    bringInfoState();
    merge(courseID);
  });

  $(".card_zone").on("click", ".deleteEntrace", function (event) {
    deleteID = $(this)
      .closest(".btnGroup")
      .siblings(".textZone")
      .find(".courseID")
      .val();
      console.log("deleteEntrace" + deleteID)
  });

  $(".card_zone").on("click", "#delete_btn", function (event) {
   console.log("delete_btn" + deleteID)
    deleteCourse(deleteID);
  });

  //表單依輸入圖片更新預覽圖
  document
    .getElementById("coursePhoto")
    .addEventListener("change", handleImageChange);
});

function renderCourse(Course) {
  Course.forEach((course) => {
    //刪除按鈕生成
    const deleteButton = $("<button>")
      .addClass("btn mx-1 btn-primary deleteEntrace")
      .attr("type", "button")
      .attr("data-bs-toggle", "modal")
      .attr("data-bs-target", "#delete")
      .text("刪除課程");

    //更新按鈕生成
    const updateButton = $("<button>")
      .addClass("btn mx-1 btn-primary update_btn")
      .attr("type", "button")
      .attr("data-bs-toggle", "modal")
      .attr("data-bs-target", "#merge")
      .text("更改課程資訊");

    const base64Image = btoa(
      new Uint8Array(course.coursePhoto).reduce(
        (data, byte) => data + String.fromCharCode(byte),""
      )
    );
    const imageSrc = `data:image/png;base64,${base64Image}`;
    
    
    const courseDate = moment(course.courseDate).format("YYYY-MM-DD");

    var courseSkill = "";
    if (course.skill == 0) {
      courseSkill = "單板";
    } else if (course.skill == 1) {
      courseSkill = "雙版";
    }

    var courseLevel = "";
    if (course.level == 0) {
      courseLevel = "初階";
    } else if (course.level == 1) {
      courseLevel = "中階";
    } else if (course.level == 2) {
      courseLevel = "高階";
    }

    var courseStatus = "";
    var textDec = "";
    if (course.courseStatus == 0) {
      courseStatus = "上架中";
      textDec = "text-success";
    } else if (course.courseStatus == 1) {
      courseStatus = "下架中";
      textDec = "text-danger";
    }

    cardStr = `
       <div class="courseCard border-top my-3 p-2 d-flex align-items-start justify-content-start position-relative">

           <div class="imgZone mx-2 ">
             <img class="crousePhoto" src='${imageSrc}' style="max-height: 160px; max-width: 180px; height: 160px; width: 180px;">
           </div>

           <div class="textZone fw-bold mb-3">
                <div id="titleGroup " class="d-flex align-items-center">
                  
                  <p class="mx-2 my-0 fs-3">${course.courseName}</p>
                  <p class="mb-0">${courseSkill}/${courseLevel}</p>
   
                  <input type="text" class="d-none courseID" value="${course.courseID}">
                </div>
                <div class="d-flex flex-column ">
                
                  <p class="mb-0 mx-2 text-start">${courseDate}</p>
                  <p class="mb-0 mx-2 text-start ${textDec}">${courseStatus}</p>
                  
                </div>
              </div>       

           <div class="btnGroup m-2 position-absolute bottom-0 end-0">
           </div>
     		</div>
     `;

    $(".card_zone").append(cardStr);
    $(".courseCard").each(function () {
      const btnGroup = $(this).find(".btnGroup");
      btnGroup.append(deleteButton, updateButton);
    });
  });
}

function validateInputField(field) {
  var value = field.val().trim();

  if (value === "") {
    field.removeClass("is-valid");
    field.addClass("is-invalid");
    field.next(".invalid-feedback");
  } else {
    value = field.val().trim();
    field.removeClass("is-invalid");
    field.addClass("is-valid");
    field.next(".invalid-feedback");
  }
}

function bringInfoState() {
  $(
    "#courseSkill, #courseLevel, #courseName, #courseLocation, #courseDate, #startDate, #endDate,#coursePrice,#courseMax,#courseMin,#courseStatus,#skiImage,#courseIntroduce"
  ).addClass("is-valid");
  $(
    "#courseSkill, #courseLevel, #courseName, #courseLocation, #courseDate, #startDate, #endDate,#coursePrice,#courseMax,#courseMin,#courseStatus,#skiImage,#courseIntroduce"
  ).removeClass("is-invalid");
}

function merge(courseID) {
  $("#submit_btn").on("click", function (event) {
    event.preventDefault();
    if ($(".is-invalid").length > 0) {
      alert("請填寫完整資料！");
    } else {
      if (courseID) {
        exist = true;
      } else {
        exist = false;
        method = "IS";
      }

      var coursePerson = $("#coursePerson").val();
      var courseSkill = $("#courseSkill").val();
      var courseLevel = $("#courseLevel").val();
      var courseName = $("#courseName").val();
      var courseCoach = $("#courseCoach").val();
      var courseLocation = $("#courseLocation").val();
      var courseDate = $("#courseDate").val();
      var startDate = $("#startDate").val();
      var endDate = $("#endDate").val();
      var coursePrice = $("#coursePrice").val();
      var courseMax = $("#courseMax").val();
      var courseMin = $("#courseMin").val();
      var courseStatus = $("#courseStatus").val();
      var coursePhoto = $("#coursePhoto")[0].files[0];
      var courseIntroduce = $("#courseIntroduce").val();

      var formData = new FormData();

      formData.append("courseSkill", courseSkill);
      formData.append("courseLevel", courseLevel);
      formData.append("courseCoach", courseCoach);
      formData.append("courseName", courseName);
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

      if (exist) {
        formData.append("courseID", courseID);
        formData.append("coursePerson", coursePerson);
        method = "UD";
      }

      $.ajax({
        url: "http://localhost:8080/ski/course_" + method,
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,
        success: function (response) {
          alert("執行成功！");
          location.reload();
        },
        error: function (xhr, status, error) {
          console.log("資料傳送失敗");
          console.log(error);
        },
      });
    }
  });
}

function deleteCourse(courseID) {
  $.ajax({
    url: "http://localhost:8080/ski/course_DL",
    type: "POST",
    data: { courseID: courseID },
    success: function (response) {
      alert("刪除成功!");
      //刷新結果
      location.reload();
    },
    error: function () {
      console.log("error");
    },
  });
}

function resetInputState() {
  $(
    "#courseSkill, #courseLevel, #courseName, #courseLocation, #courseDate, #startDate, #endDate,#coursePrice,#courseMax,#courseMin,#courseStatus,#skiImage,#courseIntroduce"
  ).removeClass("is-valid");
  $(
    "#courseSkill, #courseLevel, #courseName, #courseLocation, #courseDate, #startDate, #endDate,#coursePrice,#courseMax,#courseMin,#courseStatus,#skiImage,#courseIntroduce"
  ).addClass("is-invalid");
}

function resetForm() {
  resetInputState();

  resetVal($(".form-control"));
  resetOption($(".form-select"));

  function resetVal(field) {
    field.val("");
  }

  function resetOption(field) {
    field.prop("selectedIndex", -1);
  }

  $("#originalImage").attr("src", "");
}

//帶入原資料供更新使用
function getCourseInfo(courseID) {
  $.ajax({
    url: "http://localhost:8080/ski/course_GBI",
    data: { courseID: courseID },
    type: "POST",
    dataType: "json",
    success: function (response) {
      const courseDate = moment(response.courseDate).format("YYYY-MM-DD");
      const startDate = moment(response.startDate).format("YYYY-MM-DD");
      const endDate = moment(response.endDate).format("YYYY-MM-DD");
      $("#coursePerson").val(response.coursePerson);
      $("#courseSkill").val(response.skill);
      $("#courseLevel").val(response.level);
      $("#courseName").val(response.courseName);
      $("#courseLocation").val(response.skiLocation.pointID);
      $("#courseDate").val(courseDate);
      $("#startDate").val(startDate);
      $("#endDate").val(endDate);
      $("#coursePrice").val(response.coursePrice);
      $("#courseMax").val(response.courseMax);
      $("#courseMin").val(response.courseMin);
      $("#courseStatus").val(response.courseStatus);

      // $("#skiImage")[0].files[0];

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
    error: function () {
      console.log("error");
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

function locSelctBuild() {
  //地點選單
  $(document).ready(function () {
    $.ajax({
      url: "/ski/loc/backend_selectmap",
      type: "GET",
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
  });
}

//取得教練ID
// function getCoachID(jsonData) {
//   $.ajax({
//     url: "/ski/member/coachInfo",
//     type: "POST",
//     processData: false,
//     contentType: false,
//     data: jsonData,
//     success: function (response) {
//       $("#courseCoach").val(response.coachID);
//       getCoachCourse(response.coachID);
//     },
//     error: function () {
//       alert("新增失敗");
//     },
//   });
// }

//帶出用戶開設的相關課程
function getCoachCourse(coachID) {
  $.ajax({
    url: "http://localhost:8080/ski/course_GCC",
    data: { coachID: coachID },
    type: "POST",
    dataType: "json",
    success: function (response) {
      renderCourse(response);
    },
    error: function () {
      console.log("error");
    },
  });
}
