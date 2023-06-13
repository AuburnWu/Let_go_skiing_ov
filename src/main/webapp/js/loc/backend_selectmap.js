



console.log("11111");



// 删除
$(document).on('click', '.btn.btn-danger', function(event) {
  event.preventDefault(); // 阻止默认的点击事件
      // 添加以下代码，阻止上传图片的事件继续执行
      event.stopPropagation();
      event.stopImmediatePropagation();
  var result = confirm("是否確定將資料整筆刪除？");

  if (result) {
    // 确认删除，执行删除相关操作
    // 可以在这里添加您需要的代码
    console.log("執行刪除操作");

    // 获取当前列表项
    var listItem = $(button).closest('.col-md-4');
    // 在列表中移除当前列表项
    listItem.remove();
  } else {
    // 取消删除，不执行删除操作
    // 可以在这里添加您需要的代码
    console.log("取消刪除");
  }
});



// 阻止默认的滚动行为
function preventScroll(event) {
  event.preventDefault();
  event.stopPropagation();
}

// 获取所有需要阻止滚动的元素
var elements = document.querySelectorAll('.item-pic, .button-group');

// 给每个元素添加点击事件监听器
elements.forEach(function(element) {
  element.addEventListener('click', preventScroll);
});






// window.onload = function() {
//   // 在這裡放置您的程式碼
//  // 取得商品圖片欄位和預覽照片元素
//  const productImageInput = document.getElementById('Image');
//  const previewImage = document.getElementById('skiImagePreview');
 
//  // 監聽商品圖片欄位的變動事件
//  productImageInput.addEventListener('change', function(event) {
//    // 確認使用者選擇了圖片
//    if (event.target.files && event.target.files[0]) {
//      // 讀取選擇的圖片檔案
//      const reader = new FileReader();
//      reader.onload = function(e) {
//        // 將讀取到的圖片顯示在預覽照片上
//        previewImage.src = e.target.result;
//      };
//      reader.readAsDataURL(event.target.files[0]);


//    }
//  });
// };





$(document).ready(function() {

  //更改圖片
$(document).on('click', '.item-pic', function(event) {
  event.preventDefault(); // 阻止默认的点击事件

  var listItem = $(this).closest('.col-md-4');
  // 触发文件上传的点击事件
  listItem.find('.upload-input').click();
});

// 当上传文件发生变化时触发事件处理函数
$(document).on('change', '.upload-input', function(event) {
  var file = event.target.files[0];
  var listItem = $(this).closest('.col-md-4');
  var itemID = listItem.find('img.photo').data('pointid');
  event.stopPropagation();
  event.stopImmediatePropagation();

console.log(listItem.find('img.photo').data('pointid'))
  // 上传文件并更新图片
  uploadImage(file, listItem, itemID);
});

function uploadImage(file, listItem, itemID) {
  var formData = new FormData();
  formData.append('Image', file);
  formData.append('pointID', itemID); // 将滑雪场地ID添加到formData中
console.log(file)
  $.ajax({
    url: 'backend_upload_image',
    method: 'POST',
    data: formData,
    processData: false,
    contentType: false,
    dataType: 'json', // 添加dataType选项，指定期望的响应类型为JSON
    success: function(response) {
      if (response.status === "success") {
        // 更新成功
        var previewImage = listItem.find('.photo');
        previewImage.attr('src', URL.createObjectURL(file));
        console.log('照片更新成功');

      } else {
        // 更新失败
        console.log('照片更新失败');
      }
      }

    //   //後端一直報錯但圖片有更新到所以可能一直回傳錯，就跑到下面erro
    //   //就直接把應該要跑成功後要執行更換當下照片的地方移到下面erro處
    //   var previewImage = listItem.find('.photo');
    //   previewImage.attr('src', URL.createObjectURL(file)); // 使用新的上传文件路径更新图片

    //   console.log('照片更新成功');
    // },
    // error: function(error) {
    //   console.log('圖片上傳失敗:', error);
    //   var previewImage = listItem.find('.photo');
    //   previewImage.attr('src', URL.createObjectURL(file)); // 使用新的上传文件路径更新图片
    // }
  });
}
    // 获取所有需要阻止滚动的元素
    var elements = document.querySelectorAll('.item-pic, .button-group');

    // 给每个元素添加点击事件监听器
    elements.forEach(function(element) {
      element.addEventListener('click', preventScroll);
    });
  


  $('#exampleModal').on('hidden.bs.modal', function (e) {
    // 当模态框关闭后，重新启用背后的内容
    $('body').removeClass('modal-open');
    $('.modal-backdrop').remove();
  });


  
  
  // 從後端獲取資料
  console.log("aaa");
  $.ajax({
    url: 'backend_selectmap',
    method: 'GET',
    dataType: 'json',
    success: function(response) {
      // 循环处理每个数据项
      response.forEach(function(itemData) {
        const avatar = itemData.pointPicture;
        const uint8Array = new Uint8Array(avatar);
        const blob = new Blob([uint8Array]);
        const a = itemData.pointStatus;
        
        // 动态生成列表项
        var listItem = `
          <div class="col-md-4 mb-5">
            <a href="#" class="item-pic card pic-primary">
              <img class="photo" src="" alt="" data-pointid="${itemData.pointID}" />
              <div class="card-body">
                <h3 class="h4 card-title">
                  <span class="title-inner bold text-secondary" id="title">${itemData.pointName}</span>
                </h3>
                <div class="button-group">
                <button class="btn btn-warning bold text-dark shelve-btn" data-pointStatus="${itemData.pointStatus}" data-pointID="${itemData.pointID}">上架</button>
                <button class="btn btn-secondary bold text-dark unshelve-btn" data-pointStatus="${itemData.pointStatus}" data-pointID="${itemData.pointID}">下架</button>
                
                  <button class="btn btn-info bold text-dark" data-toggle="modal" data-target="#exampleModal" data-item="${encodeURIComponent(JSON.stringify(itemData))}">編輯</button>
                  <button class="btn btn-danger bold text-dark" onclick="confirmDelete()">刪除</button>
                </div>
              </div>

            </a>
            <input type="file" class="upload-input" style="display: none;">
          </div>
        `;
  
        // 将生成的列表项插入到列表中
        $('#mycon').append(listItem);
           // 根据每个数据项选择对应的图像元素并设置 src 属性
  var photoElement = $('#mycon .photo:last');
  photoElement.attr('src', URL.createObjectURL(blob));
 // 更新字体颜色和按钮状态
var title = $('#mycon .title-inner:last');
var shelveButton = $('#mycon .btn-warning:last');
var unshelveButton = $('#mycon .btn-secondary:last');

if (itemData.pointStatus === '上架中') {
  title.removeClass('text-secondary');
  title.addClass('text-warning');
  // shelveButton.text('上架'); // 修改按钮文字为已上架
  unshelveButton.prop('disabled', false);
} else if (itemData.pointStatus === '下架中') {
  title.addClass('text-secondary');
  // unshelveButton.text('下架'); // 修改按钮文字为已下架
  shelveButton.prop('disabled', false);
}

      });
     
    },
    error: function(error) {
      console.log('請求失敗:', error);
    }
  });


  
  
 
  // 上架按钮点击事件

$(document).on('click', '.shelve-btn', function(event) {
  event.preventDefault(); // 阻止默认的点击事件
   // 添加以下代码，阻止上传图片的事件继续执行
   event.stopPropagation();
   event.stopImmediatePropagation();
  var button = $(this);
  var itemData = {
    pointID: $(this).attr('data-pointID'),
    pointStatus: '上架中'
  };

  console.log("执行上架操作");
  // 执行上架操作，发送请求到后端更新数据库中对应数据的状态为上架中
  $.ajax({
    url: 'backend_statusmap',
    method: 'POST',
    data: itemData,
    success: function(response) {
      console.log("成功")
      console.log(response); // 打印响应数据
            var title = button.closest('.card').find('.title-inner');
            title.removeClass('text-secondary');
            title.addClass('text-warning');
    },
    error: function(error) {
      console.log(error); // 打印错误信息
    }
  });
});
  
  // 下架按钮点击事件
$(document).on('click', '.unshelve-btn', function(event) {
  event.preventDefault(); // 阻止默认的点击事件
    // 添加以下代码，阻止上传图片的事件继续执行
    event.stopPropagation();
    event.stopImmediatePropagation();
  var button = $(this);
  var itemData = {
    pointID: $(this).attr('data-pointID'),
    pointStatus:  "下架中",
  };

  console.log("执行下架操作");
  // 执行下架操作，发送请求到后端更新数据库中对应数据的状态为下架中
  $.ajax({
    url: 'backend_statusmap',
    method: 'POST',
    data: itemData,
    success: function(response) {
      console.log("成功")
      console.log(response); // 打印响应数据
      var title = button.closest('.card').find('.title-inner');
      title.removeClass('text-warning');
      title.addClass('text-secondary');
    },
    error: function(error) {
      console.log(error); // 打印错误信息
    }
  });
});
  });
  


// 监听编辑按钮的点击事件
$(document).on('click', '.btn.btn-info', function(event) {
  event.preventDefault(); // 阻止默认的点击事件
    // 添加以下代码，阻止上传图片的事件继续执行
    event.stopPropagation();
    event.stopImmediatePropagation();
  // 获取保存在自定义属性中的数据
  // var itemData = $(this).data('item');
  var itemData = JSON.parse(decodeURIComponent($(this).data('item')));


  // 将数据填充到编辑弹窗的输入字段中
  $('#skiNameInput').val(itemData.pointName);
  $('#areaInput').val(itemData.pointArea);
  $('#latInput').val(itemData.latitude);
  $('#lngInput').val(itemData.longitude);
  $('#infoInput').val(itemData.pointInfo);
  $('#ratingInput').val(itemData.pointRating);
  $('#starMonthInput').val(itemData.starMonth);
  $('#endMonthInput').val(itemData.endMonth);
  $('#skiView').val(itemData.pointView);
  // $('#skiImage').attr('src', itemData.pointPicture);
  // $('#skiImagePreview').attr('src', itemData.pointPicture);
});








