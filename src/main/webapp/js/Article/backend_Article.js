var articleID = 1;
var userID = 1;
var articleTypeID = 1;
var articleTitle = "";
var articleContent = "";
var articleDateTime = "";
var articleModified = "";
var articleLike = 1;
var articleStatus = "";
var number = 0;
var buttonPage = 0;
var saveArticleID = 0;
var saveArticleTypeID = 0;
var saveArticleTypeContent = "";
var articleTypeContent = "";


//顯示全部
$(document).ready(function init() {
    
    console.log("js載入成功");
    $.ajax({
        url: "http://localhost:8080/ski/BackendArticle",
        type: "Post",
        data: {
            "articleID": articleID,
            "articleTitle": articleTitle,
            "articleTypeID": articleTypeID,
            "articleStatus": articleStatus,
            "action": "showAll"
        },
        dataType: "json",
        success: function (data) {
            // console.log(data.length);
            // console.log(data);
            console.log("後端文章載入成功");
            if (data.length <= 10) {
                for (let i = 0; i < data.length; i++) {
                    let list_html = "";
                    let articleID = data[i].articleID;
                    let articleTitle = data[i].articleTitle;
                    let articleTypeID = data[i].articleTypeID;
                    if (data[i].articleStatus == "0") {
                        list_html += `<tr class="tr">`;
                        list_html += `<td><p class="id">${articleID}</p></td>`;
                        // list_html += `<td><a class="saveArticleID" href="http://localhost:8080/ski/article/backend_Article_ChangeStatus.html">${articleTitle}</a></td>`;
                        list_html += `<td><p class="id2 -none">${articleTypeID}</p><a>${articleTitle}</a></td>`;
                        list_html += `<td><p class="green">上架</p>
                                            <p class="red -none">下架</p></td>`;
                        list_html += `</tr>`
                    } else if (data[i].articleStatus == "1") {
                        list_html += `<tr class="tr">`;
                        list_html += `<td><p class="id">${articleID}</p></td>`;
                        // list_html += `<td><a class="saveArticleID" href="http://localhost:8080/ski/article/backend_Article_ChangeStatus.html">${articleTitle}</a></td>`;
                        list_html += `<td><p class="id2 -none">${articleTypeID}</p><a>${articleTitle}</a></td>`;
                        list_html += `<td><p class="green -none">上架</p>
                                            <p class="red">下架</p></td>`;
                        list_html += `</tr>`
                    }
                    $("#type > table > tbody").append(list_html); // 加入網頁表格中
                    number = articleID; // 紀錄目前分類編號最後一碼
                }

            } else if (data.length > 10) {
                for (let i = 0; i < 10; i++) {
                    let list_html = "";
                    let articleID = data[i].articleID;
                    let articleTitle = data[i].articleTitle;
                    let articleTypeID = data[i].articleTypeID;
                    if (data[i].articleStatus == "0") {
                        list_html += `<tr class="tr">`;
                        list_html += `<td><p class="id">${articleID}</p></td>`;
                        // list_html += `<td><a class="saveArticleID" href="http://localhost:8080/ski/article/backend_Article_ChangeStatus.html">${articleTitle}</a></td>`;
                        list_html += `<td><p class="id2 -none">${articleTypeID}</p><a>${articleTitle}</a></td>`;
                        list_html += `<td><p class="green">上架</p>
                                            <p class="red -none">下架</p></td>`;
                        list_html += `</tr>`
                    } else if (data[i].articleStatus == "1") {
                        list_html += `<tr class="tr">`;
                        list_html += `<td><p class="id">${articleID}</p></td>`;
                        // list_html += `<td><a class="saveArticleID" href="http://localhost:8080/ski/article/backend_Article_ChangeStatus.html">${articleTitle}</a></td>`;
                        list_html += `<td><p class="id2 -none">${articleTypeID}</p><a>${articleTitle}</a></td>`;
                        list_html += `<td><p class="green -none">上架</p>
                                            <p class="red">下架</p></td>`;
                        list_html += `</tr>`
                    }
                    $("#type > table > tbody").append(list_html); // 加入網頁表格中
                    number = articleID; // 紀錄目前分類編號最後一碼
                }
            }
            if (data.length > 10) {
                var pagination = $("#pagination");
                buttonPage = (data.length / 10) + 1;
                for (let i = 1; i < buttonPage; i++) {
                    let list_html = "";
                    if (i == 1) {
                        list_html += `
                        <button class="btn_limit -disabled">${i}</button>
                        `;
                    } else {
                        list_html += `
                        <button class="btn_limit">${i}</button>
                        `;
                    }
                    // 當點擊其中一個btn_limit，就將他加上-disabled，其餘的btn_limit
                    // 就刪掉-disabled
                    pagination.append(list_html);
                }
            }

        },
        error: function (err) {
            console.log("後端文章載入失敗")
        }

    })
});

//點文章標題
// $("#articleTableBody").on("click", "button.nextPageBtn", function () {
$("#articleTableBody").on("click", "a", function (e) {
    saveArticleID = $(this).closest("tr").find("p.id").text();
    saveArticleTypeID = $(this).closest("tr").find("p.id2").text();
    $.ajax({
        url: "http://localhost:8080/ski/BackendArticleType",
        type: "Post",
        data: {
            "articleTypeID": saveArticleTypeID,
            "articleTypeContent": articleTypeContent,
            "action": "searchIDAndContent",
            "type": "Number"
        },
        dataType: "json",
        success: function (data) {
            console.log("搜尋成功");
            // console.log(data.articleTypeContent);
            saveArticleTypeContent = data.articleTypeContent;
            // console.log(saveArticleTypeContent);
            sessionStorage.setItem("saveArticleTypeContent", saveArticleTypeContent); // 存下分類ID
        },
    })
    sessionStorage.setItem("saveArticleID", saveArticleID); // 存下文章ID
    window.location.href = "http://localhost:8080/ski/article/backend_Article_ChangeStatus.html"; // 將存下來的ID丟到此網址
});

//ChangeStatus網頁
// $("#articleTableBody").on("click", "a.saveArticleID", function () {
//     saveArticleID = $(this).closest("tr").find("p.id").text();
//     sessionStorage.setItem("saveArticleID", saveArticleID); // 存下ID
//     window.location.href = "http://localhost:8080/ski/article/backend_Article_ChangeStatus.html"; // 將存下來的ID丟到此網址
// });

//分頁按鈕
$("#pagination").on("click", "button.btn_limit", function () {

    $(this).addClass("-disabled");
    var clickButtonNumber = $(this).text();
    // console.log(clickButtonNumber);
    var buttons = document.getElementsByClassName("btn_limit");
    // 迭代按鈕元素
    for (var i = 0; i < buttons.length; i++) {
        // 檢查按鈕的文字是否為 clickButtonNumber
        if (buttons[i].textContent.trim() != clickButtonNumber) {
            // 刪除 "-disabled" class
            buttons[i].classList.remove("-disabled");
        }
    }
    $.ajax({
        url: "http://localhost:8080/ski/BackendArticle",
        type: "Post",
        data: {
            "articleID": articleID,
            "articleTitle": articleTitle,
            "articleTypeID": articleTypeID,
            "articleStatus": articleStatus,
            "action": "showAll"
        },
        dataType: "json",
        success: function (data) {
            // console.log(data.length);
            $("#articleTypeTableBody").empty();
            // for (let i = 0; i < data.length; i++) {
            if (clickButtonNumber == 1) {
                limit = 10;
            } else if (clickButtonNumber > 1) {
                limit = 10 * clickButtonNumber;
                if (data.length <= limit) {
                    limit = data.length;
                }
            }
            for (let i = 0 + (10 * (clickButtonNumber - 1)); i < limit; i++) {
                let list_html = "";
                let articleID = data[i].articleID;
                let articleTitle = data[i].articleTitle;
                let articleTypeID = data[i].articleTypeID;
                if (data[i].articleStatus == "0") {
                    list_html += `<tr class="tr">`;
                    list_html += `<td><p class="id">${articleID}</p></td>`;
                    // list_html += `<td><a class="saveArticleID" href="http://localhost:8080/ski/article/backend_Article_ChangeStatus.html">${articleTitle}</a></td>`;
                    list_html += `<td><p class="id2 -none">${articleTypeID}</p><a>${articleTitle}</a></td>`;
                    list_html += `<td><p class="green">上架</p>
                                        <p class="red -none">下架</p></td>`;
                    list_html += `</tr>`
                } else if (data[i].articleStatus == "1") {
                    list_html += `<tr class="tr">`;
                    list_html += `<td><p class="id">${articleID}</p></td>`;
                    // list_html += `<td><a class="saveArticleID" href="http://localhost:8080/ski/article/backend_Article_ChangeStatus.html">${articleTitle}</a></td>`;
                    list_html += `<td><p class="id2 -none">${articleTypeID}</p><a>${articleTitle}</a></td>`;
                    list_html += `<td><p class="green -none">上架</p>
                                        <p class="red">下架</p></td>`;
                    list_html += `</tr>`
                }

                $("#type > table > tbody").append(list_html); // 加入網頁表格中
            }
        },
        error: function (err) {
            console.log("載入分頁失敗")
        }
    })
})

//搜尋後的分頁按鈕
$("#pagination").on("click", "button.btn_limit2", function () {

    $(this).addClass("-disabled");
    var clickButtonNumber = $(this).text();
    // console.log(clickButtonNumber);
    var buttons = document.getElementsByClassName("btn_limit2");
    // 迭代按鈕元素
    for (var i = 0; i < buttons.length; i++) {
        // 檢查按鈕的文字是否為 clickButtonNumber
        if (buttons[i].textContent.trim() != clickButtonNumber) {
            // 刪除 "-disabled" class
            buttons[i].classList.remove("-disabled");
        }
    }
    if (task_name_search === "上架") {
        form_data = { // 將輸入的文字送入資料庫
            "articleID": articleID,
            "articleTypeID": articleTypeID,
            "articleStatus": "1",
            "action": "searchIDAndTitle",
            "type": "上下架"
        }
    } else if (task_name_search === "下架") {
        form_data = { // 將輸入的文字送入資料庫
            "articleID": articleID,
            "articleTypeID": articleTypeID,
            "articleStatus": "0",
            "action": "searchIDAndTitle",
            "type": "上下架"
        }
    } else {
        form_data = { // 將輸入的文字送入資料庫
            "articleID": articleID,
            "articleTitle": task_name_search,
            "articleTypeID": articleTypeID,
            "action": "searchIDAndTitle",
            "type": "String"
        }
    }
    $.ajax({
        url: "http://localhost:8080/ski/BackendArticle",
        type: "Post",
        data: form_data,
        dataType: "json",
        success: function (data) {
            console.log('輸入為字串');
            for (let i = 0; i < data.length; i++) { // 再把已經更變過的資料新增到網頁表格
                let list_html = "";
                let articleID = data[i].articleID;
                let articleTitle = data[i].articleTitle;
                let articleTypeID = data[i].articleTypeID;
                // console.log(data[i].articleStatus);
                if (data[i].articleStatus == "0") {
                    list_html += `<tr class="tr">`;
                    list_html += `<td><p class="id">${articleID}</p></td>`;
                    // list_html += `<td><a class="saveArticleID" href="http://localhost:8080/ski/article/backend_Article_ChangeStatus.html">${articleTitle}</a></td>`;
                    list_html += `<td><p class="id2 -none">${articleTypeID}</p><a>${articleTitle}</a></td>`;
                    list_html += `<td><p class="green">上架</p>
                                                    <p class="red -none">下架</p></td>`;
                    list_html += `</tr>`
                } else if (data[i].articleStatus == "1") {
                    list_html += `<tr class="tr">`;
                    list_html += `<td><p class="id">${articleID}</p></td>`;
                    // list_html += `<td><a class="saveArticleID" href="http://localhost:8080/ski/article/backend_Article_ChangeStatus.html">${articleTitle}</a></td>`;
                    list_html += `<td><p class="id2 -none">${articleTypeID}</p><a>${articleTitle}</a></td>`;
                    list_html += `<td><p class="green -none">上架</p>
                                                    <p class="red">下架</p></td>`;
                    list_html += `</tr>`
                }

                $("#type > table > tbody").append(list_html); // 加入網頁表格中
                // number = articleTypeID; // 紀錄目前分類編號最後一碼
            }
        },
        error: function (err) {
            console.log("載入分頁失敗")
        }
    })
})


//搜尋分類內容、編號
$("button.task_search").on("click", function () {
    var task_name_search = $("input.task_name_search").val().trim(); // 將輸入的文字存下來
    if ($("input.task_name_search").val().trim() != "") { // 如果輸入的文字扣掉空格不為空值的話就執行

        if (!$(this).hasClass("-disabled")) { // 如果新增按鈕的class沒有"-disabled"就執行
            let form_data = {}
            if (!(isNaN(parseInt(task_name_search)))) {
                // console.log('輸入為數字');
                form_data = { // 將輸入的文字送入資料庫
                    "articleID": task_name_search,
                    "articleTitle": articleTitle,
                    "articleTypeID": articleTypeID,
                    "action": "searchIDAndTitle",
                    "type": "Number"
                }
            } else {
                // console.log('輸入為字串');
                if (task_name_search === "上架") {
                    form_data = { // 將輸入的文字送入資料庫
                        "articleID": articleID,
                        "articleTypeID": articleTypeID,
                        "articleStatus": "0",
                        "action": "searchIDAndTitle",
                        "type": "上下架"
                    }
                } else if (task_name_search === "下架") {
                    form_data = { // 將輸入的文字送入資料庫
                        "articleID": articleID,
                        "articleTypeID": articleTypeID,
                        "articleStatus": "1",
                        "action": "searchIDAndTitle",
                        "type": "上下架"
                    }
                } else {
                    form_data = { // 將輸入的文字送入資料庫
                        "articleID": articleID,
                        "articleTitle": task_name_search,
                        "articleTypeID": articleTypeID,
                        "action": "searchIDAndTitle",
                        "type": "String"
                    }
                }
            }
            $.ajax({
                url: "http://localhost:8080/ski/BackendArticle",
                type: "Post",
                data: form_data,
                dataType: "json",
                beforeSend: function () { // 執行前對新增按鈕加入"-disabled"，讓按鈕沒辦法被連續啟動
                    $("button.task_search").addClass("-disabled");
                },
                success: function (data) {
                    console.log("搜尋成功");
                    // console.log(data);
                    if ((data != null) && (data.length != 0)) {
                        $("#type").find("button.btn_limit").remove(); // 刪除分頁按鈕
                        $("#type").find("button.btn_limit2").remove(); // 刪除分頁按鈕
                        $("#type > table > tbody").find("tr").remove(); // 刪除原網頁表格
                        if (!(isNaN(parseInt(task_name_search)))) {

                            console.log('輸入為數字');
                            let list_html = "";
                            if (data.articleStatus == "0") {
                                list_html += `<tr class="tr">`;
                                list_html += `<td><p class="id">${data.articleID}</p></td>`;
                                // list_html += `<td><a class="saveArticleID" href="http://localhost:8080/ski/article/backend_Article_ChangeStatus.html">${articleTitle}</a></td>`;
                                list_html += `<td><p class="id2 -none">${data.articleTypeID}</p><a>${data.articleTitle}</a></td>`;
                                list_html += `<td><p class="green">上架</p>
                                                <p class="red -none">下架</p></td>`;
                                list_html += `</tr>`
                            } else if (data.articleStatus == "1") {
                                list_html += `<tr class="tr">`;
                                list_html += `<td><p class="id">${data.articleID}</p></td>`;
                                // list_html += `<td><a class="saveArticleID" href="http://localhost:8080/ski/article/backend_Article_ChangeStatus.html">${articleTitle}</a></td>`;
                                list_html += `<td><p class="id2 -none">${data.articleTypeID}</p><a>${data.articleTitle}</a></td>`;
                                list_html += `<td><p class="green -none">上架</p>
                                                <p class="red">下架</p></td>`;
                                list_html += `</tr>`
                            }

                            $("#type > table > tbody").append(list_html); // 加入網頁表格中

                        } else {
                            console.log('輸入為字串');
                            $("#type").find("button.btn_limit").remove(); // 刪除分頁按鈕
                            $("#type").find("button.btn_limit2").remove(); // 刪除分頁按鈕
                            $("#type > table > tbody").find("tr").remove(); // 刪除原網頁表格
                            for (let i = 0; i < data.length; i++) { // 再把已經更變過的資料新增到網頁表格
                                let list_html = "";
                                let articleID = data[i].articleID;
                                let articleTitle = data[i].articleTitle;
                                let articleTypeID = data[i].articleTypeID;
                                // console.log(data[i].articleStatus);
                                if (data[i].articleStatus == "0") {
                                    list_html += `<tr class="tr">`;
                                    list_html += `<td><p class="id">${articleID}</p></td>`;
                                    // list_html += `<td><a class="saveArticleID" href="http://localhost:8080/ski/article/backend_Article_ChangeStatus.html">${articleTitle}</a></td>`;
                                    list_html += `<td><p class="id2 -none">${articleTypeID}</p><a>${articleTitle}</a></td>`;
                                    list_html += `<td><p class="green">上架</p>
                                                    <p class="red -none">下架</p></td>`;
                                    list_html += `</tr>`
                                } else if (data[i].articleStatus == "1") {
                                    list_html += `<tr class="tr">`;
                                    list_html += `<td><p class="id">${articleID}</p></td>`;
                                    // list_html += `<td><a class="saveArticleID" href="http://localhost:8080/ski/article/backend_Article_ChangeStatus.html">${articleTitle}</a></td>`;
                                    list_html += `<td><p class="id2 -none">${articleTypeID}</p><a>${articleTitle}</a></td>`;
                                    list_html += `<td><p class="green -none">上架</p>
                                                    <p class="red">下架</p></td>`;
                                    list_html += `</tr>`
                                }

                                $("#type > table > tbody").append(list_html); // 加入網頁表格中
                                // number = articleTypeID; // 紀錄目前分類編號最後一碼
                            }
                            if (data.length > 10) { //分頁
                                var pagination = $("#pagination");
                                buttonPage = (data.length / 10) + 1;
                                for (let i = 1; i < buttonPage; i++) {
                                    let list_html = "";
                                    if (i == 1) {
                                        list_html += `
                                    <button class="btn_limit2 -disabled">${i}</button>
                                    `;
                                    } else {
                                        list_html += `
                                    <button class="btn_limit2">${i}</button>
                                    `;
                                    }
                                    // 當點擊其中一個btn_limit，就將他加上-disabled，其餘的btn_limit
                                    // 就刪掉-disabled
                                    pagination.append(list_html);
                                }
                            }
                        }
                    } else {
                        alert(`搜尋不到${task_name_search}`);
                    }

                },
                complete: function () { // 執行結束後刪除新增按鈕的"-disabled"，恢復功能
                    $("button.task_search").removeClass("-disabled");
                }
            })
        }
    } else {
        alert("搜尋不可為空");
    }
});