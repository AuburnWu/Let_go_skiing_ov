document.addEventListener("DOMContentLoaded", function () {
  const tableBody = document.getElementById("courseTableBody");
  // 獲取數據並渲染表格
  AllCrouseRequsest();

  // 為刪除按鈕綁定事件
  $("#courseTableBody").on("click", ".btn-delete", function (event) {
    const courseID = $(this).parents().siblings("th").text();
        deleteExc(courseID);
  });
  

  $("#serachbar").on("input", function () {
    var keyWord = $(this).val();
    $.ajax({
      url: "http://localhost:8080/ski/course_GBK",
      type: "POST",
      dataType: "json",
      data: { keyWord: keyWord },
      success: function (data) {
        renderCourse(data);
      },
      error: function () {
        console.log("error");
      },
    });
  });

  function AllCrouseRequsest() {
    $.ajax({
      url: "http://localhost:8080/ski/course_GA",
      type: "POST",
      dataType: "json",
      success: function (data) {
        renderCourse(data);
      },
      error: function () {
        console.log("error");
      },
    });
  }

  // 渲染商品表格
  function renderCourse(Course) {
    $("#courseTableBody").empty();
    Course.forEach((course) => {
      const row = document.createElement("tr");
      row.classList.add("align-middle");

      const editButton = document.createElement("button");
      editButton.classList.add("btn", "btn-secondary", "btn-edit");
      editButton.textContent = "修改";
      const editlink = document.createElement("a");
      editlink.href =
        "http://localhost:8080/ski/course/backend_courseUpdate.html?courseID=" +
        course.courseID;

      editlink.appendChild(editButton);

      const deleteButton = document.createElement("button");
      deleteButton.classList.add("btn", "btn-secondary", "btn-delete");
      deleteButton.textContent = "刪除";

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

      // 將日期格式轉換為 yyyy-MM-dd 格式
      const date = new Date(course.courseDate);
      const formattedDate = `${date.getFullYear()}-${(date.getMonth() + 1)
        .toString()
        .padStart(2, "0")}-${date.getDate().toString().padStart(2, "0")}`;

      const base64Image = btoa(
        new Uint8Array(course.coursePhoto).reduce(
          (data, byte) => data + String.fromCharCode(byte),
          ""
        )
      );
      const imageSrc = `data:image/png;base64,${base64Image}`;

      var courseStatus = "";
      if (course.courseStatus == 0) {
        courseStatus = "上架中";
      } else if (course.courseStatus == 1) {
        courseStatus = "下架中";
      }


		
		
      row.innerHTML = `
      <th scope="row" class="courseID">${course.courseID}</th>
      <td><img src='${imageSrc}' style="max-width: 150px; max-height: 80px;"></img></td>
      <td>${course.coachID}</td>
      <td>${courseSkill}</td>
      <td>${courseLevel}</td>
      <td>${course.courseName}</td>
      <td>${course.skiLocation.pointName}</td>
      <td>${formattedDate}</td>
      <td>${course.coursePerson}/${course.courseMax}</td>
      <td>${courseStatus}</td>
      <td></td>
      <td></td>
    `;

      row.querySelector("td:nth-child(11)").appendChild(editlink);
      row.querySelector("td:nth-child(12)").appendChild(deleteButton);

      tableBody.appendChild(row);
    });
  }

  function deleteExc(courseID) {
    if (confirm("確認刪除此課程?")) {
      $.ajax({
        url: "http://localhost:8080/ski/course_DL",
        type: "POST",
        data: { courseID: courseID },
        dataType: "json",
        success: function (data) {
          console.success(data);
        },
        error: function (error) {
          console.log(error);
        },
      });
      alert ("刪除成功");
      AllCrouseRequsest();
    } else {
      alert ("刪除失敗");
    }
  }
 
});

