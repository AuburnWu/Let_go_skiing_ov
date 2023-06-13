var articleTypeID = 1;
var articleTypeContent = "";
const saveUserID = sessionStorage.getItem("userID");
//顯示全部
$(document).ready(function init() {
    console.log("js載入成功");
    $.ajax({
        url: "http://localhost:8080/ski/BackendArticleType",
        type: "Post",
        data: {
            "articleTypeID": articleTypeID,
            "articleTypeContent": articleTypeContent,
            "action": "showAll"
        },
        dataType: "json",
        success: function (data) {
            // console.log(data.length);
            console.log("前端文章發文載入成功");
            // console.log(saveUserID);
            for (let i = 1; i < data.length; i++) {
                let list_html = "";
                let articleTypeID = data[i].articleTypeID;
                let articleTypeContent = data[i].articleTypeContent;

                list_html += `<option value=${articleTypeID}>${articleTypeContent}</option>`;

                $("select.articleType").append(list_html); // 加入網頁表格中
                number = articleTypeID; // 紀錄目前分類編號最後一碼
            }
        }
    });
});

const textarea = document.getElementById('text');
const imageWrapper = document.querySelector('.image-wrapper');

textarea.addEventListener('input', function() {
    const textareaRect = textarea.getBoundingClientRect();
    imageWrapper.style.top = `${textareaRect.top}px`;
    imageWrapper.style.right = `${window.innerWidth - textareaRect.right}px`;
});

//上傳圖片
// $("#uploadForm").submit(function (event) {
//     event.preventDefault(); // 阻止表單的默認提交行為

//     // 取得選擇的圖片檔案
//     var imageFile = $("#imageFile")[0].files[0];

//     // 建立 FormData 物件
//     var formData = new FormData();
//     formData.append("imageFile", imageFile); // 將圖片檔案添加到 FormData

//     // 發送 Ajax 請求
//     $.ajax({
//         url: "http://localhost:8080/ski/UploadServlet",
//         type: "Post",
//         data: formData,
//         contentType: false,
//         processData: false,
//         success: function (response) {
//             console.log("圖片上傳成功");
//             console.log(response); // 處理回應
//         },
//         error: function (xhr, status, error) {
//             console.log("圖片上傳失敗");
//             console.log(error); // 處理錯誤
//         }
//     });
// });


//新增文章
$("button.add").on("click", function () {
    var selectElement = document.querySelector('.articleType');
    var selectedValue = selectElement.value;
    let articleTitle = $("input.title").val();
    let articleContent = $("textarea.content").val();
    // console.log(articleTitle); // 標題
    // console.log(articleContent); // 內容
    // console.log(selectedValue); // 分類ID
    // 建立一個新的 Date 物件
    var now = new Date();
    // 獲取年、月、日、時、分、秒
    var year = now.getFullYear();
    var month = ('0' + (now.getMonth() + 1)).slice(-2);  // 注意月份從0開始，需要+1
    var day = ('0' + now.getDate()).slice(-2);
    var hours = ('0' + now.getHours()).slice(-2);
    var minutes = ('0' + now.getMinutes()).slice(-2);
    var seconds = ('0' + now.getSeconds()).slice(-2);
    // 組合成所需的時間格式
    var formattedTime = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
    // 在控制台中輸出時間格式
    // console.log(formattedTime); // 現在時間
    $.ajax({
        url: "http://localhost:8080/ski/FrontendArticle",
        type: "Post",
        data: {
            "userID": saveUserID,
            "articleTypeID": selectedValue,
            "articleTitle": articleTitle,
            "articleContent": articleContent,
            "articleDateTime": formattedTime,
            "articleModified": undefined,
            "articleLike": 0,
            "action": "addArticle"
        },
        dataType: "json",
        success: function (data) {
            console.log("新增文章成功!")
            window.location.href = "http://localhost:8080/ski/article/frontend_Article.html";
        }
    });

});