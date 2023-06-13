var articleTypeID = 1;
var articleTypeContent = "";
var articleDateTime1 = "";

const saveUserID = sessionStorage.getItem("userID");
const savearticleID = sessionStorage.getItem("articleID");
const savearticleTypeID = sessionStorage.getItem("articleTypeID");

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
            // console.log(savearticleTypeID);
            console.log("前端文章編輯載入成功");
            // console.log(saveUserID);
            for (let i = 1; i < data.length; i++) {
                let list_html = "";
                let articleTypeID2 = data[i].articleTypeID;
                let articleTypeContent2 = data[i].articleTypeContent;

                list_html += `<option value=${articleTypeID2}`;
                 // 檢查是否為要預先選中的選項
                if (articleTypeID2 == savearticleTypeID) {
                    list_html += ` selected`;
                }
                list_html += `>${articleTypeContent2}</option>`;
                // list_html += `<option value=${articleTypeID2}>${articleTypeContent2}</option>`;

                $("select.articleType").append(list_html); // 加入網頁表格中
            }
            
        }
    });
    $.ajax({
        url: "http://localhost:8080/ski/FrontendArticle",
        type: "Post",
        data: {
            "articleID": savearticleID,
            "action": "searchArticle"
        },
        dataType: "json",
        success: function (data) {
            // console.log(data);
            let list_html = "";
            let articleID = data.articleID;
            let userID = data.userID;
            let articleTypeID1 = data.articleTypeID;
            let articleTitle = data.articleTitle;
            let articleContent = data.articleContent;
            let articleDateTime = data.articleDateTime;
            let articleModified = data.articleModified;
            let articleLike = data.articleLike;
            let articleStatus = data.articleStatus;
            // console.log(articleModified);
            articleDateTime1 = articleDateTime;
            let list_html2 = "";
                        list_html2 = `<label for="title">標題：</label>
                            <input type="text" name="title" class="title" value="${articleTitle}"><br>
        
                            <label for="content">內容：</label><br>
                            <textarea id="content" name="content" rows="10" cols="50" class="content">${articleContent}</textarea><br>
        
                            `;
                        $("div.addc").append(list_html2); // 加入網頁表格中
            
        }
        
    });
    
});

//修改文章
$("button.update").on("click", function () {
    var selectElement = document.querySelector('.articleType');
    var selectedValue = selectElement.value; // 抓分類ID
    let articleTitle = $("input.title").val();
    let articleContent = $("textarea.content").val();
    console.log(articleTitle); // 標題
    console.log(articleContent); // 內容
    console.log(selectedValue); // 分類ID
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
            "articleID":savearticleID,
            "userID": saveUserID,
            "articleTypeID": selectedValue,
            "articleTitle": articleTitle,
            "articleContent": articleContent,
            "articleDateTime": articleDateTime1,
            "articleModified": formattedTime,
            "articleLike": 0,
            "action": "updateArticle"
        },
        dataType: "json",
        success: function (data) {
            console.log("修改文章成功!")
            window.location.href = "/ski/article/member_center_article.html";
        }
    });

});