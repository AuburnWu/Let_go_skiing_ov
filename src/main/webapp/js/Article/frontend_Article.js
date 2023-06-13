var articleID1 = 1;
var userID1 = 1;
var articleTypeID1 = 1;
var articleTitle1 = "";
var articleContent1 = "";
var articleDateTime1 = "";
var articleModified1 = "";
var articleLike1 = 1;
var articleStatus1 = "";
var saveUserName1 = "";
var saveArticleTypeContent1 = "";
var savearticleTitle = sessionStorage.getItem("savearticleTitle");
const saveUserID = sessionStorage.getItem("userID");

//顯示全部(同步)
//根據你提供的程式碼，我們可以看到你在迴圈中執行了多個 Ajax 請求，這可能導致請求完成的順序不確定，因為 Ajax 請求是非同步的。如果你想確保請求完成的順序與迴圈的順序相符，你可以使用 Promise 或 async/await 進行同步處理。
$(document).ready(async function init() {
    console.log("js載入成功");
    if(saveUserID==null){
        var button = document.querySelector('button.btn_addArticle');
        button.setAttribute('disabled','disabled');
        // $("button.btn_addArticle").addClass("-disabled")
    }
    try {
        const response = await $.ajax({
            url: "http://localhost:8080/ski/FrontendArticle",
            type: "Post",
            data: {
                "action": "showAll"
            },
            dataType: "json"
        });

        let repeat = Array();
        let list_button = "";
        repeat.push("");

        for (let i = 0; i < response.length; i++) {
            let list_html = "";
            let articleID = response[i].articleID;
            let userID = response[i].userID;
            let articleTypeID = response[i].articleTypeID;
            let articleTitle = response[i].articleTitle;
            let articleDateTime = response[i].articleDateTime;
            let articleLike = response[i].articleLike;
            let articleStatus = response[i].articleStatus;

            const memberResponse = await $.ajax({
                url: "http://localhost:8080/ski/FrontendArticle",
                type: "Post",
                data: {
                    "userID": userID,
                    "action": "getMemberUserName"
                },
                dataType: "json"
            });
            let saveUserName1 = memberResponse.userName;

            const articleTypeResponse = await $.ajax({
                url: "http://localhost:8080/ski/BackendArticleType",
                type: "Post",
                data: {
                    "articleTypeID": articleTypeID,
                    "action": "searchIDAndContent",
                    "type": "Number"
                },
                dataType: "json"
            });

            const commentResponse = await $.ajax({
                url: "http://localhost:8080/ski/FrontendArticle",
                type: "Post",
                data: {
                    "articleID": articleID,
                    "action": "showComment"
                },
                dataType: "json",
            });
            let saveCommentCount = commentResponse.length

            saveArticleTypeContent = articleTypeResponse.articleTypeContent;

            if (articleStatus == "0") {
                let repeatLength = repeat.length;
                for (let i = 0; i < repeatLength; i++) {
                    if (!repeat.includes(saveArticleTypeContent)) {
                        repeat.push(saveArticleTypeContent);
                        list_button = `<button class="searchArticleType">#${saveArticleTypeContent}</button>`;
                        $("div.btn").append(list_button);
                    }
                }

                list_html += `<tr class="tr">
                    <td>
                        <a><p class="ID -none">${articleID}</p><p class="article text-start">讚數:${articleLike} &ensp;&ensp;留言:${saveCommentCount} &ensp;&ensp;#${saveArticleTypeContent}</p><p class="article text-center">${articleTitle}</p><p class="article text-end">${articleDateTime} by${saveUserName1}</p></a>
                    </td>
                </tr>`;
                $("#type > table > tbody").append(list_html);
            }
        }
    } catch (error) {
        console.log(error);
    }
});

//顯示全部(非同步)
// $(document).ready(async function init() {

//     console.log("js載入成功");
//     $.ajax({
//         url: "http://localhost:8080/ski/FrontendArticle",
//         type: "Post",
//         data: {
//             "action": "showAll"
//         },
//         dataType: "json",
//         success: function (data) {
//             if (data.length <= 10) {
//                 let repeat = Array();
//                 let list_button = "";
//                 repeat.push("");
//                 for (let i = 0; i < data.length; i++) {
//                     let list_html = "";
//                     let articleID = data[i].articleID;
//                     let userID = data[i].userID;
//                     let articleTypeID = data[i].articleTypeID;
//                     let articleTitle = data[i].articleTitle;
//                     let articleDateTime = data[i].articleDateTime;
//                     let articleLike = data[i].articleLike;
//                     let articleStatus = data[i].articleStatus;
//                     // console.log(articleID);
//                     $.ajax({
//                         url: "http://localhost:8080/ski/FrontendArticle",
//                         type: "Post",
//                         data: {
//                             "userID": userID,
//                             "action": "getMemberUserName"
//                         },
//                         dataType: "json",
//                         success: function (data) {
//                             // console.log(data);
//                             let saveUserName1 = data.userName;
//                             // console.log(saveUserName1);
//                             $.ajax({
//                                 url: "http://localhost:8080/ski/BackendArticleType",
//                                 type: "Post",
//                                 data: {
//                                     "articleTypeID": articleTypeID,
//                                     "action": "searchIDAndContent",
//                                     "type": "Number"
//                                 },
//                                 dataType: "json",
//                                 success: function (data) {
//                                     saveArticleTypeContent = data.articleTypeContent;
//                                     console.log(articleID);
//                                     // console.log(saveUserName1);
//                                     if (articleStatus == "0") {
//                                         // console.log(data.articleTypeID);
//                                         let repeatLength = repeat.length;
//                                         for (let i = 0; i < repeatLength; i++) {
//                                             if (!repeat.includes(saveArticleTypeContent)) { // 如果陣列有相同元素就不新增
//                                                 repeat.push(saveArticleTypeContent);
//                                                 list_button = `<button class="searchArticleType">#${saveArticleTypeContent}</button>`;
//                                                 $("div.btn").append(list_button)
//                                             }
//                                         }
//                                         // console.log(repeat);
//                                         // console.log(articleID);
//                                         // console.log(saveUserName1);
//                                         list_html += `<tr class="tr">
//                                         <td>
//                                             <a><p class="ID -none">${articleID}</p><p class="article text-start">讚數:${articleLike} &ensp;&ensp;留言:0 &ensp;&ensp;#${saveArticleTypeContent}</p><p class="article text-center">${articleTitle}</p><p class="article text-end">${articleDateTime} by${saveUserName1}</p></a>
//                                         </td>
//                                         </tr>`;
//                                         $("#type > table > tbody").append(list_html); // 加入網頁表格中
//                                         // myArray = []; //這種方式將原本的陣列 myArray 指派為一個空陣列，從而清空了陣列中的所有元素。
//                                         // repeat.length = 0; //這種方式將陣列 myArray 的 length 屬性設定為 0，從而將陣列清空。這種方法效率比較高，因為它不需要重新分配記憶體。
//                                         // repeat.splice(0, repeat.length); //這種方式使用 splice() 方法，從索引 0 開始刪除 myArray 中的所有元素。
//                                     }
//                                 },
//                             })
//                         }
//                     });
//                 }
//             } else if (data.length > 10) {
//                 let repeat = Array();
//                 let list_button = "";
//                 repeat.push("");
//                 for (let i = 0; i < 10; i++) {
//                     let list_html = "";
//                     let articleID = data[i].articleID;
//                     let userID = data[i].userID;
//                     let articleTypeID = data[i].articleTypeID;
//                     let articleTitle = data[i].articleTitle;
//                     let articleDateTime = data[i].articleDateTime;
//                     let articleLike = data[i].articleLike;
//                     let articleStatus = data[i].articleStatus;
//                     $.ajax({
//                         url: "http://localhost:8080/ski/FrontendArticle",
//                         type: "Post",
//                         data: {
//                             "userID": userID,
//                             "action": "getMemberUserName"
//                         },
//                         dataType: "json",
//                         success: function (data) {
//                             // console.log(data);
//                             let saveUserName1 = data.userName;
//                             $.ajax({
//                                 url: "http://localhost:8080/ski/BackendArticleType",
//                                 type: "Post",
//                                 data: {
//                                     "articleTypeID": articleTypeID,
//                                     "action": "searchIDAndContent",
//                                     "type": "Number"
//                                 },
//                                 dataType: "json",
//                                 success: function (data) {
//                                     saveArticleTypeContent = data.articleTypeContent;
//                                     if (articleStatus == "0") {
//                                         let repeatLength = repeat.length;
//                                         for (let i = 0; i < repeatLength; i++) {
//                                             if (!repeat.includes(saveArticleTypeContent)) { // 如果陣列有相同元素就不新增
//                                                 repeat.push(saveArticleTypeContent);
//                                                 list_button = `<button class="searchArticleType">#${saveArticleTypeContent}</button>`;
//                                                 $("div.btn").append(list_button)
//                                             }
//                                         }
//                                         // console.log(repeat);
//                                         list_html += `<tr class="tr">
//                                         <td>
//                                             <a><p class="ID -none">${articleID}</p><p class="article text-start">讚數:${articleLike} &ensp;&ensp;留言:0 &ensp;&ensp;#${saveArticleTypeContent}</p><p class="article text-center">${articleTitle}</p><p class="article text-end">${articleDateTime} by${saveUserName1}</p></a>
//                                         </td>
//                                         </tr>`;
//                                         $("#type > table > tbody").append(list_html); // 加入網頁表格中
//                                     }
//                                 },
//                             })
//                         }
//                     });
//                 }
//             }
//         }
//     });
// });


//發表文章
$("#type").on("click", "button.btn_addArticle", function (e) {
    window.location.href = "http://localhost:8080/ski/article/frontend_Article_Add.html"; // 將存下來的ID丟到此網址
})



//點文章標題
$("#type").on("click", "a", function (e) {
    articleTitle1 = $(this).closest("tr").find("p.article").text();
    articleID1 = $(this).closest("tr").find("p.ID").text();
    sessionStorage.setItem("articleID", articleID1); // 存下文章ID
    sessionStorage.setItem("articleTitle", articleTitle1); // 存下文章標題
    window.location.href = "http://localhost:8080/ski/article/frontend_Article_Show.html"; // 將存下來的ID丟到此網址
});


//搜尋文章標題
$("#type").on("click", "button.search", function (e) {

    var task_name_search = $("input.text_search").val().trim(); // 將輸入的文字存下來
    if ($("input.text_search").val().trim() != "") { // 如果輸入的文字扣掉空格不為空值的話就執行
        if (!$(this).hasClass("-disabled")) { // 如果新增按鈕的class沒有"-disabled"就執行
            $.ajax({
                url: "http://localhost:8080/ski/FrontendArticle",
                type: "Post",
                data: {
                    "articleTitle": task_name_search,
                    "action": "searchTitle"
                },
                dataType: "json",
                success: function (data) {
                    $("#type").find("button.btn_limit").remove(); // 刪除分頁按鈕
                    $("#type").find("button.btn_limit2").remove(); // 刪除分頁按鈕
                    $("#type > table > tbody").find("tr").remove(); // 刪除原網頁表格
                    if (data.length <= 10) {
                        for (let i = 0; i < data.length; i++) {
                            let list_html = "";
                            let articleID = data[i].articleID;
                            let userID = data[i].userID;
                            let articleTypeID = data[i].articleTypeID;
                            let articleTitle = data[i].articleTitle;
                            let articleDateTime = data[i].articleDateTime;
                            let articleLike = data[i].articleLike;
                            let articleStatus = data[i].articleStatus;

                            $.ajax({
                                url: "http://localhost:8080/ski/FrontendArticle",
                                type: "Post",
                                data: {
                                    "userID": userID,
                                    "action": "getMemberUserName"
                                },
                                dataType: "json",
                                success: function (data) {
                                    // console.log(data);
                                    let saveUserName1 = data.userName;
                                    $.ajax({
                                        url: "http://localhost:8080/ski/BackendArticleType",
                                        type: "Post",
                                        data: {
                                            "articleTypeID": articleTypeID,
                                            "action": "searchIDAndContent",
                                            "type": "Number"
                                        },
                                        dataType: "json",
                                        success: function (data) {
                                            saveArticleTypeContent = data.articleTypeContent;
                                            if (articleStatus == "0") {
                                                list_html += `<tr class="tr">
                                                <td>
                                                    <a><p class="ID -none">${articleID}</p><p class="article text-start">讚數:${articleLike} &ensp;&ensp;留言:0 &ensp;&ensp;#${saveArticleTypeContent}</p><p class="article text-center">${articleTitle}</p><p class="article text-end">${articleDateTime} by${saveUserName1}</p></a>
                                                </td>
                                                </tr>`;
                                                $("#type > table > tbody").append(list_html); // 加入網頁表格中
                                            }
                                        },
                                    })
                                }
                            });
                        }

                    } else if (data.length > 10) {
                        for (let i = 0; i < 10; i++) {
                            let list_html = "";
                            let articleID = data[i].articleID;
                            let userID = data[i].userID;
                            let articleTypeID = data[i].articleTypeID;
                            let articleTitle = data[i].articleTitle;
                            let articleDateTime = data[i].articleDateTime;
                            let articleLike = data[i].articleLike;
                            let articleStatus = data[i].articleStatus;

                            $.ajax({
                                url: "http://localhost:8080/ski/FrontendArticle",
                                type: "Post",
                                data: {
                                    "userID": userID,
                                    "action": "getMemberUserName"
                                },
                                dataType: "json",
                                success: function (data) {
                                    // console.log(data);
                                    let saveUserName1 = data.userName;
                                    $.ajax({
                                        url: "http://localhost:8080/ski/BackendArticleType",
                                        type: "Post",
                                        data: {
                                            "articleTypeID": articleTypeID,
                                            "action": "searchIDAndContent",
                                            "type": "Number"
                                        },
                                        dataType: "json",
                                        success: function (data) {
                                            saveArticleTypeContent = data.articleTypeContent;
                                            if (articleStatus == "0") {
                                                list_html += `<tr class="tr">
                                                <td>
                                                    <a><p class="ID -none">${articleID}</p><p class="article text-start">讚數:${articleLike} &ensp;&ensp;留言:0 &ensp;&ensp;#${saveArticleTypeContent}</p><p class="article text-center">${articleTitle}</p><p class="article text-end">${articleDateTime} by${saveUserName1}</p></a>
                                                </td>
                                                </tr>`;
                                                $("#type > table > tbody").append(list_html); // 加入網頁表格中
                                            }
                                        },
                                    })
                                }
                            });
                        }
                    }
                }
            });

        } else {
            alert("搜尋不可為空");
        }
    }
});

//搜尋分類按鈕
$("div.btn").on("click", "button.searchArticleType", function () {
    // console.log("RRRRRRRrrr");
    let buttonText = $(this).text();
    let cleanedText = buttonText.replace('#', '');  // 移除文字中的 "#" 符號
    // console.log(cleanedText);
    $.ajax({
        url: "http://localhost:8080/ski/FrontendArticle",
        type: "Post",
        data: {
            "articleTypeContent": cleanedText,
            "action": "searchArticleTypeID"
        },
        dataType: "json",
        success: function (data) {
            // console.log(data[0].articleTypeID);
            $.ajax({
                url: "http://localhost:8080/ski/FrontendArticle",
                type: "Post",
                data: {
                    "articleTypeID": data[0].articleTypeID,
                    "action": "useTypeIDsearchArticle"
                },
                dataType: "json",
                success: function (data) {
                    // console.log(data);
                    $("#type > table > tbody").find("tr").remove(); // 刪除原網頁表格
                    if (data.length <= 10) {
                        for (let i = 0; i < data.length; i++) {
                            let list_html = "";
                            let articleID = data[i].articleID;
                            let userID = data[i].userID;
                            let articleTypeID = data[i].articleTypeID;
                            let articleTitle = data[i].articleTitle;
                            let articleDateTime = data[i].articleDateTime;
                            let articleLike = data[i].articleLike;
                            let articleStatus = data[i].articleStatus;

                            $.ajax({
                                url: "http://localhost:8080/ski/FrontendArticle",
                                type: "Post",
                                data: {
                                    "userID": userID,
                                    "action": "getMemberUserName"
                                },
                                dataType: "json",
                                success: function (data) {
                                    // console.log(data);
                                    let saveUserName1 = data.userName;
                                    $.ajax({
                                        url: "http://localhost:8080/ski/BackendArticleType",
                                        type: "Post",
                                        data: {
                                            "articleTypeID": articleTypeID,
                                            "action": "searchIDAndContent",
                                            "type": "Number"
                                        },
                                        dataType: "json",
                                        success: function (data) {
                                            saveArticleTypeContent = data.articleTypeContent;
                                            if (articleStatus == "0") {
                                                list_html += `<tr class="tr">
                                                <td>
                                                    <a><p class="ID -none">${articleID}</p><p class="article text-start">讚數:${articleLike} &ensp;&ensp;留言:0 &ensp;&ensp;#${saveArticleTypeContent}</p><p class="article text-center">${articleTitle}</p><p class="article text-end">${articleDateTime} by${saveUserName1}</p></a>
                                                </td>
                                                </tr>`;
                                                $("#type > table > tbody").append(list_html); // 加入網頁表格中
                                            }
                                        },
                                    })
                                }
                            });
                        }

                    } else if (data.length > 10) {
                        for (let i = 0; i < 10; i++) {
                            let list_html = "";
                            let articleID = data[i].articleID;
                            let userID = data[i].userID;
                            let articleTypeID = data[i].articleTypeID;
                            let articleTitle = data[i].articleTitle;
                            let articleDateTime = data[i].articleDateTime;
                            let articleLike = data[i].articleLike;
                            let articleStatus = data[i].articleStatus;

                            $.ajax({
                                url: "http://localhost:8080/ski/FrontendArticle",
                                type: "Post",
                                data: {
                                    "userID": userID,
                                    "action": "getMemberUserName"
                                },
                                dataType: "json",
                                success: function (data) {
                                    // console.log(data);
                                    let saveUserName1 = data.userName;
                                    $.ajax({
                                        url: "http://localhost:8080/ski/BackendArticleType",
                                        type: "Post",
                                        data: {
                                            "articleTypeID": articleTypeID,
                                            "action": "searchIDAndContent",
                                            "type": "Number"
                                        },
                                        dataType: "json",
                                        success: function (data) {
                                            saveArticleTypeContent = data.articleTypeContent;
                                            if (articleStatus == "0") {
                                                list_html += `<tr class="tr">
                                                <td>
                                                    <a><p class="ID -none">${articleID}</p><p class="article text-start">讚數:${articleLike} &ensp;&ensp;留言:0 &ensp;&ensp;#${saveArticleTypeContent}</p><p class="article text-center">${articleTitle}</p><p class="article text-end">${articleDateTime} by${saveUserName1}</p></a>
                                                </td>
                                                </tr>`;
                                                $("#type > table > tbody").append(list_html); // 加入網頁表格中
                                            }
                                        },
                                    })
                                }
                            });
                        }
                    }
                }
            });

        }
    })

})