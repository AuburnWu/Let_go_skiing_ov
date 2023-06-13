var applyCoachSaveReq = function(){
  const userID = sessionStorage.getItem("userID")
  const skill = document.getElementById("skill").value;
  const background = document.getElementById("background").value;
  const introduce = document.getElementById("introduce").value;
  const license = document.getElementById("license").files[0];
  const applyStatus = "0";

  const formData = new FormData();

  if (license) {
    console.log("已選擇檔案");
    formData.append('license', license);
  }
  formData.append('userID', userID);
  formData.append('skill', skill);
  formData.append('background', background);
  formData.append('introduce', introduce);
  formData.append('applyStatus', applyStatus);

  console.log(formData)

  $.ajax({
    url: "/ski/member/applyCoachSave",
    method: "POST",
    dataType: "json", //指定回傳的資料格式
    data: formData,
    processData: false, // 預設true，適合contentType為"application/x-www-form-urlencoded"(禁止jQuery自動處理資料)
    contentType: false, // 預設application/x-www-form-urlencoded，false為不傳送header
    success: function (resp) {
     if(resp.successful == false){
      alert(resp.message);
      return;
     }
     alert(resp.message);
     location.reload();
    },
    error: function (xhr, status, error) {
      console.log(xhr);
      console.log(status);
      console.log(error);
    }
  });
  }

$("#btn_upload").on("click", function (){
  applyCoachSaveReq();
})

$("#btn_cancel").on("click", function (){
  location = "/ski/member/applyCoach.html";
})
