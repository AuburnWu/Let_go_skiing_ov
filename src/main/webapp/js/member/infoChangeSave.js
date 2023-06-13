var infoChangeSaveReq = function(){
  const userID = sessionStorage.getItem("userID")
  const photo = document.getElementById("photo").files[0];
  const nickName = document.getElementById("nickName").value;
  const personID = document.getElementById("personID").value;
  const phone = document.getElementById("phone").value;
  const address = document.getElementById("address").value;
  const level = document.getElementById("level").value;

  const formData = new FormData();

  if (photo) {
    console.log("已選擇檔案");
    formData.append('photo', photo);
  }
  formData.append('userID', userID);
  formData.append('nickName', nickName);
  formData.append('personID', personID);
  formData.append('phone', phone);
  formData.append('address', address);
  formData.append('level', level);  

  console.log(formData)

  $.ajax({
    url: "/ski/member/infoChangeSave",
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
     location = "/ski/member/memberInfo.html";
    },
    error: function (xhr, status, error) {
      console.log(xhr);
      console.log(status);
      console.log(error);
    }
  });
  }

$("#btn_upload").on("click", function (){
    infoChangeSaveReq();
})

$("#btn_cancel").on("click", function (){
  location = "/ski/member/memberInfo.html";
})
