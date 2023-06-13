
  // 取得商品圖片欄位和預覽照片元素
const productImageInput = document.getElementById('skiImage');
const previewImage = document.getElementById('skiImagePreview');

// 監聽商品圖片欄位的變動事件
productImageInput.addEventListener('change', function(event) {
  // 確認使用者選擇了圖片
  if (event.target.files && event.target.files[0]) {
    // 讀取選擇的圖片檔案
    const reader = new FileReader();
    reader.onload = function(e) {
      // 將讀取到的圖片顯示在預覽照片上
      previewImage.src = e.target.result;
    };
    reader.readAsDataURL(event.target.files[0]);
  }
});


// 用Jquery抓前台使用者輸入的值
// 按下新增 在控制台中輸出值

// $("#555").on("click",function(){

//   if ($(".is-invalid").length > 0) {
//     // 提示使用者填寫完整資料
//     alert("請填寫完整資料！");
//   } else {
//     // 彈出確認對話框
//     if (confirm("確認要新增嗎？")) {
  

//   // 取得使用者輸入的值
//     //名稱
//     var name = $("#skiNameInput").val();
//     //區域
//     var area = $("#areaInput").val();
//     //緯度
//     var latitude = $("#latInput").val();
//     //經度
//     var longitude = $("#lngInput").val();
//     //雪點資訊
//     var info = $("#infoInput").val();
//     //推薦分數
//     var rating = $("#ratingInput").val();
//     //起始月份
//     var startMonth = $("#starMonthInput").val();
//     //結束月份
//     var endMonth = $("#endMonthInput").val();
//     //雪點簡介
//     var skiView = $("#skiView").val();
//     //雪點圖片
//     var skiImage = $("#skiImage").val(); 
//     console.log($("#skiImage").val());
    

//     // 建立要傳送到後端的資料物件
//   var data = {
//     name: name,
//     area: area,
//     latitude: latitude,
//     longitude: longitude,
//     info: info,
//     rating: rating,
//     startMonth: startMonth,
//     endMonth: endMonth,
//     skiView: skiView,
//     skiImage: skiImage
    
//   };
  // console.log(data.skiImage);
//   $.ajax({
//     url: "backend_addmap",
//     type: "POST",
//     data: data,
//     success: function(response) {
//       // 請求成功的處理程式碼
//       console.log("資料傳送成功");
//       console.log(response); // 伺服器回傳的回應

//         // 顯示新增成功訊息
//         alert("新增成功！");
          
//         // 重新整理頁面
//         location.reload();

//     },
//     error: function(xhr, status, error) {
//       // 請求失敗的處理程式碼
//       console.log("資料傳送失敗");
//       console.log(error); // 錯誤訊息

      
//     }
//   });
//   }
// }
// })

console.log("kfsddd")
$("#555").on("click", function(event) {
  event.preventDefault(); // 取消默认的表单提交行为


  console.log("koeddd")

  if ($(".is-invalid").length > 0) {
    alert("請填寫完整資料！");
    console.log("koe")
    
  } else {
    console.log("ˇˇe")
    
    if (confirm("確認要新增嗎？")) {
      console.log("ulloe")
    
      var name = $("#skiNameInput").val();
      var area = $("#areaInput").val();
      var latitude = $("#latInput").val();
      var longitude = $("#lngInput").val();
      var info = $("#infoInput").val();
      var rating = $("#ratingInput").val();
      var starMonth = $("#starMonthInput").val();
      var endMonth = $("#endMonthInput").val();
      var skiView = $("#skiView").val();
      var skiImage = $("#skiImage")[0].files[0];

      var formData = new FormData();
      formData.append('name', name);
      formData.append('area', area);
      formData.append('latitude', latitude);
      formData.append('longitude', longitude);
      formData.append('info', info);
      formData.append('rating', rating);
      formData.append('starMonth', starMonth);
      formData.append('endMonth', endMonth);
      formData.append('skiView', skiView);
      formData.append('skiImage', skiImage);

      $.ajax({
        url: "backend_addmap",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,
        success: function(response) {
          console.log("資料傳送成功");
          console.log(response);
          alert("新增成功！請到雪點列表點擊上架顯示到滑雪地圖");
          location.reload();
         
        },
        error: function(xhr, status, error) {
          console.log("資料傳送失敗");
          console.log(error);
        }
      });
    } 
  }
});





$(document).ready(function() {
  // 綁定欄位失去焦點事件
  $("#skiNameInput, #areaInput, #latInput, #lngInput, #infoInput, #skiView, #ratingInput,#starMonthInput,#endMonthInput,#skiImage").blur(function() {
    validateField($(this));
  });

  // 綁定欄位輸入事件
  $("#skiNameInput, #areaInput, #latInput, #lngInput, #infoInput, #skiView, #ratingInput,#starMonthInput,#endMonthInput,#skiImage").on("input", function() {
    validateField($(this));
  });

    // 點擊經度欄位時檢查格式
    $("#latInput").click(function() {
      validateField($(this));
    });
  
    // 點擊緯度欄位時檢查格式
    $("#lngInput").click(function() {
      validateField($(this));
    });
  

  // 顯示欄位不能為空的錯誤提示
  $("#skiNameInput, #areaInput, #latInput, #lngInput, #infoInput, #skiView, #ratingInput,#starMonthInput,#endMonthInput,#skiImage").addClass("is-invalid");
  $(".invalid-feedback")
});

function validateField(field) {
  var value = field.val().trim();
  
  if (value === "") {
    field.removeClass("is-valid");
    field.addClass("is-invalid");
    field.next(".invalid-feedback")
  } else {
    field.removeClass("is-invalid");
    field.addClass("is-valid");
    field.next(".invalid-feedback")
  }

   // 檢查經度和緯度格式
   if (field.attr("id") === "latInput") {
    if (!isValidLatitude(value)) {
      field.removeClass("is-valid");
      field.addClass("is-invalid");
      field.next(".invalid-feedback").text("請符合緯度格式(必填)");
    }
  } else if (field.attr("id") === "lngInput") {
    if (!isValidLongitude(value)) {
      field.removeClass("is-valid");
      field.addClass("is-invalid");
      field.next(".invalid-feedback").text("請符合經度格式(必填)");
    }
  }
}

function isValidLatitude(latitude) {
  // 檢查緯度格式是否正確，這裡使用正規表達式進行檢查
  var pattern = /^[-+]?([1-8]?\d(\.\d+)?|90(\.0+)?)$/;
  return pattern.test(latitude);
}

function isValidLongitude(longitude) {
  // 檢查經度格式是否正確，這裡使用正規表達式進行檢查
  var pattern = /^[-+]?(180(\.0+)?|((1[0-7]\d)|([1-9]?\d))(\.\d+)?)$/;
  return pattern.test(longitude);
}












