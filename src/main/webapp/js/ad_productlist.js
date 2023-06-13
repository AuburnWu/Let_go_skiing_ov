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
        url: "productList",
        type: "GET",
        dataType: "json",
        success: function (data) {
            let tableBody = $("#productlist");
            tableBody.html("");
            data.forEach(product => {
                let html = `
                <tr>
                <td>${product.productID}</td>
                <td>${product.productName}</td>
                <td>${product.productClass}</td>
                <td>${product.productPrice}</td>
                <td><button class="btn btn-outline-info btn-insert" data-id="${product.productID}">新增</button></td>
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
    const inputValue = $('#productSearch').val();
    
    if (inputValue == null || inputValue === "") {
        Swal.fire({
            title: '請輸入關鍵字!',
            showConfirmButton: true,
            confirmButtonText: '知道了',
            confirmButtonColor: '#427AA1',

        });
      $('#productSearch').trigger("focus");
      return;
    } else {
      $.ajax({
        url: "productAdSearch",
        type: "POST",
        data: { input: inputValue },
        dataType: "json",
        success: function (data) {
          let tableBody = $("#productlist");
          tableBody.html("");
          $("#noResultsMessage").empty();
          if (data.length === 0) {
            // 查詢結果為空，顯示提示訊息
            let html = `
                <div class="text-center my-3 align-items-center h5" style="test-align:center">
                    查無資料
                </div>
        `;
            $("#noResultsMessage").append(html);
          } else {
            // // 查詢結果不為空，隱藏提示訊息
            $("#noResultsMessage").hide();
  
            data.forEach(product => {
              let html = `
                <tr>
                <td>${product.productID}</td>
                <td>${product.productName}</td>
                <td>${product.productClass}</td>
                <td>${product.productPrice}</td>
                <td><button class="btn btn-outline-info btn-insert" data-id="${product.productID}">新增</button></td>
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
  let productID = $(this).data("id");
  $.ajax({
    url: "productAdinsert",
    type: "POST",
    data: { pAdId: productID },
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
  
    
      
    //topten
    $(document).ready(function() {
        $("#srcButton2").click(function() {
          $.ajax({
            url: "topTen",
            type: "GET",
            dataType: "json",
            success: function(data) {
              let tableBody = $("#productlist");
              tableBody.html("");
              data.forEach(function(product) {
                let html = `
                  <tr>
                    <td>${product.productID}</td>
                    <td>${product.productName}</td>
                    <td>${product.productClass}</td>
                    <td>${product.productPrice}</td>
                    <td><button class="btn btn-outline-info btn-insert" data-id="${product.productID}">新增</button></td>
                  </tr>`;
                tableBody.append(html);
              });
            },
            error: function(error) {
              console.log("Error:");
              console.log(error);
            }
          });
        });
      });
      
})
