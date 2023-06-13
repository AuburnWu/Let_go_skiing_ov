$(document).ready(function () {
    init();
    $("input#courseSearch").on("keyup", function(e){
        if(e.which == 13){ // 按下 Enter 鍵
            $(".btn-search").click();
        }
    });
})

function init() {
    $.ajax({
        url: "courseList",
        type: "GET",
        dataType: "json",
        success: function (data) {
            let tableBody = $("#courselist");
            tableBody.html("");
            data.forEach(course => {
                let html = `
                <tr>
                <td>${course.courseID}</td>
                <td>${course.courseName}</td>
                <td>${course.courseIntroduce}</td>
                <td>${course.courseDate}</td>
                <td><button class="btn btn-outline-info btn-insert" data-id="${course.courseID}">新增</button></td>
              </tr>`;
              tableBody.append(html);
            });
            },error: function (error) {
            console.log(error);
          }
    });
}

//查詢
$(".btn-search").on("click", function () {
    const inputValue = $('#courseSearch').val();
    
    if (inputValue == null || inputValue === "") {
        Swal.fire({
          title: '請輸入關鍵字!',
          showConfirmButton: true,
          confirmButtonText: '知道了',
          confirmButtonColor: '#427AA1',
        });
      $('#courseSearch').trigger("focus");
      return;
    } else {
      $.ajax({
        url: "courseAdsearch",
        type: "POST",
        data: { input: inputValue },
        dataType: "json",
        success: function (data) {
          let tableBody = $("#courselist");
          tableBody.html("");
          $("#noResultsMessage").empty();
          if (data.length === 0) {
            // 查詢結果為空，顯示提示訊息
            let html = `
            <div class="row" style = "justify-content: center; width: 100%;"">
                <div class="text-center my-3 align-items-center">
                    查無資料
                </div>
            </div>
        `;
            $("#noResultsMessage").append(html);
          } else {
            // // 查詢結果不為空，隱藏提示訊息
            $("#noResultsMessage").hide();
  
            data.forEach(course => {
              let html = `
                <tr>
                  <td>${course.courseID}</td>
                  <td>${course.courseName}</td>
                  <td>${course.courseIntroduce}</td>
                  <td>${course.courseDate}</td>
                  <td><button class="btn btn-outline-info btn-insert" data-id="${course.courseID}"  onclick="insertCourse(${course.courseID})">新增</button></td>
                </tr>`;
              tableBody.append(html);
            });
          }
        },
        error: function (error) {
          console.error(error);
        }
      });
    }
  });
  
  //新增
$(document).on("click", ".btn-insert", function () {
  let courseID = $(this).data("id");
  $.ajax({
    url: "http://localhost:8080/ski/ad/courseAdinsert",
    type: "POST",
    data: { cId: courseID },
    dataType: "json",
    success: function (data) {
      console.log("a");
      location.reload();
    },
    error: function (error) {
      console.log("b");
      console.log(error);
    }
  })
  
})
