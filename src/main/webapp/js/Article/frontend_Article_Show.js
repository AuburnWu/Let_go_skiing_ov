var articleID1 = 1;
var userID1 = 1;
var articleTypeID1 = 1;
var articleTitle1 = "";
var articleContent1 = "";
var articleDateTime1 = "";
var articleModified1 = "";
var articleLike1 = 1;
var articleStatus1 = "";
var saveUserName = "";
var saveArticleTypeContent = "";
var savearticleID = sessionStorage.getItem("articleID");
var savearticleTitle = sessionStorage.getItem("articleTitle");
const saveUserID = sessionStorage.getItem("userID");
var savearticleLike = 0;
var savecomment = 0;
var commentShow = "hidden";

//顯示全部
$(document).ready(async function init() {
    // console.log(savearticleTitle);
    console.log("js載入成功");
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
            let articleTypeID = data.articleTypeID;
            let articleTitle = data.articleTitle;
            let articleContent = data.articleContent;
            let articleDateTime = data.articleDateTime;
            let articleModified = data.articleModified;
            let articleLike = data.articleLike;
            let articleStatus = data.articleStatus;
            // console.log(articleModified);

            $.ajax({
                url: "http://localhost:8080/ski/FrontendArticle",
                type: "Post",
                data: {
                    "userID": saveUserID,
                    "action": "getMemberUserName"
                },
                dataType: "json",
                success: function (data) {
                    saveUserName = data.userName;
                    $.ajax({
                        url: "http://localhost:8080/ski/FrontendArticle",
                        type: "Post",
                        data: {
                            "articleTypeID": articleTypeID,
                            "action": "searchID",
                        },
                        dataType: "json",
                        success: function (data) {
                            saveArticleTypeContent = data.articleTypeContent;
                            $.ajax({
                                url: "http://localhost:8080/ski/FrontendArticle",
                                type: "Post",
                                data: {
                                    "articleID": savearticleID,
                                    "action": "searchArticleLikeID"
                                },
                                dataType: "json",
                                success: function (data) {
                                    // console.log(data);
                                    let articleLikeID = data.articleLikeID;
                                    let articleID = data.articleID;
                                    let userID = data.userID;
                                    let articleLikeStatus = data.articleLikeStatus;
                                    $.ajax({
                                        url: "http://localhost:8080/ski/FrontendArticle",
                                        type: "Post",
                                        data: {
                                            "articleID": savearticleID,
                                            "action": "showComment"
                                        },
                                        dataType: "json",
                                        success: function (data) {
                                            if (articleLikeStatus == "1") {
                                                if (articleModified == undefined) {
                                                    if (articleStatus == "0") {
                                                        list_html += `<p class="articleType text-start">#${saveArticleTypeContent}&ensp;&ensp;${articleTitle}&ensp;&ensp;發文時間:${articleDateTime}&ensp;&ensp;by${saveUserName}</p>
                                                                <hr>
                                                                <p>${articleContent}</p>
                                                                <br>
                                                                <p class="right-align"><i onclick="myFunction(this)" class="fa fa-thumbs-up like-icon">${articleLike}</i><span><i class="fa fa-commenting" onclick="showComment()"></i></span><span class="comment">${data.length}</span><i class="fa fa-exclamation-triangle" onclick="openReportForm()"></i></p>`;
                                                        $("#type").append(list_html); // 加入網頁表格中
                                                    }
                                                } else {
                                                    if (articleStatus == "0") {
                                                        list_html += `<p class="articleType text-start">#${saveArticleTypeContent}&ensp;&ensp;${articleTitle}&ensp;&ensp;修改時間:${articleModified}&ensp;&ensp;by${saveUserName}</p>
                                                                <hr>
                                                                <p>${articleContent}</p>
                                                                <br>
                                                                <p class="right-align"><i onclick="myFunction(this)" class="fa fa-thumbs-up like-icon">${articleLike}</i><span><i class="fa fa-commenting" onclick="showComment()"></i></span><span class="comment">${data.length}</span><i class="fa fa-exclamation-triangle" onclick="openReportForm()"></i></p>`;
                                                        $("#type").append(list_html); // 加入網頁表格中
                                                    }
                                                }
                                            } else if (articleLikeStatus == "0") {
                                                if (articleModified == undefined) {
                                                    if (articleStatus == "0") {
                                                        list_html += `<p class="articleType text-start">#${saveArticleTypeContent}&ensp;&ensp;${articleTitle}&ensp;&ensp;發文時間:${articleDateTime}&ensp;&ensp;by${saveUserName}</p>
                                                                <hr>
                                                                <p>${articleContent}</p>
                                                                <br>
                                                                <p class="right-align"><i onclick="myFunction(this)" class="fa fa-thumbs-up like-icon active">${articleLike}</i><span><i class="fa fa-commenting" onclick="showComment()"></i></span><span class="comment">${data.length}</span><i class="fa fa-exclamation-triangle" onclick="openReportForm()"></i></p>`;
                                                        $("#type").append(list_html); // 加入網頁表格中
                                                    }
                                                } else {
                                                    if (articleStatus == "0") {
                                                        list_html += `<p class="articleType text-start">#${saveArticleTypeContent}&ensp;&ensp;${articleTitle}&ensp;&ensp;修改時間:${articleModified}&ensp;&ensp;by${saveUserName}</p>
                                                                <hr>
                                                                <p>${articleContent}</p>
                                                                <br>
                                                                <p class="right-align"><i onclick="myFunction(this)" class="fa fa-thumbs-up like-icon active">${articleLike}</i><span><i class="fa fa-commenting" onclick="showComment()"></i></span><span class="comment">${data.length}</span><i class="fa fa-exclamation-triangle" onclick="openReportForm()"></i></p>`;
                                                        $("#type").append(list_html); // 加入網頁表格中
                                                    }
                                                }
                                            }
                                        }
                                    });

                                }
                            });
                            articleID1 = articleID;
                            userID1 = userID;
                            articleTypeID1 = articleTypeID;
                            articleTitle1 = articleTitle;
                            articleContent1 = articleContent;
                            articleDateTime1 = articleDateTime;
                            articleModified1 = articleModified;
                            articleLike1 = 1;
                            articleStatus1 = articleStatus;
                        },
                    });
                }
            });
        }
    });
});

//比讚的按鈕
function myFunction(x) {
    x.classList.toggle('active');
    // x.classList.toggle("fa-thumbs-down");// 動態可以正讚跟倒讚互換
    // let articleLike = $(this).closest("i").find("i.fa fa-thumbs-up").text();
    // console.log(savearticleID);
    $.ajax({
        url: "http://localhost:8080/ski/FrontendArticle",
        type: "Post",
        data: {
            "articleID": savearticleID,
            "action": "searchArticleLikeID"
        },
        dataType: "json",
        success: function (data) {
            // console.log(data);
            let articleLikeID = data.articleLikeID;
            let articleID = data.articleID;
            let userID = data.userID;
            let articleLikeStatus = data.articleLikeStatus;
            if (articleLikeStatus == "1") {
                $.ajax({
                    url: "http://localhost:8080/ski/FrontendArticle",
                    type: "Post",
                    data: {
                        "articleLikeID": articleLikeID,
                        "articleID": articleID,
                        "userID": userID,
                        "articleLikeStatus": "0",
                        "action": "updateStatus"
                    },
                    dataType: "json",
                    success: function (data) {
                        savearticleLike = Number($("i.fa.fa-thumbs-up").text()) + 1;
                        $.ajax({
                            url: "http://localhost:8080/ski/FrontendArticle",
                            type: "Post",
                            data: {
                                "articleID": articleID,
                                "userID": userID1,
                                "articleTypeID": articleTypeID1,
                                "articleTitle": articleTitle1,
                                "articleContent": articleContent1,
                                "articleDateTime": articleDateTime1,
                                "articleModified": articleModified1,
                                "articleLike": savearticleLike,
                                "articleStatus": "0",
                                "action": "updateLike"
                            },
                            dataType: "json",
                            success: function (data) {
                                console.log("按讚");
                                const likeCountElement = document.querySelector('i.fa.fa-thumbs-up');
                                likeCountElement.textContent = savearticleLike;
                            }
                        });
                    }
                });
            } else if (articleLikeStatus == "0") {
                $.ajax({
                    url: "http://localhost:8080/ski/FrontendArticle",
                    type: "Post",
                    data: {
                        "articleLikeID": articleLikeID,
                        "articleID": articleID,
                        "userID": userID,
                        "articleLikeStatus": "1",
                        "action": "updateStatus"
                    },
                    dataType: "json",
                    success: function (data) {
                        savearticleLike = Number($("i.fa.fa-thumbs-up").text()) - 1;
                        $.ajax({
                            url: "http://localhost:8080/ski/FrontendArticle",
                            type: "Post",
                            data: {
                                "articleID": articleID,
                                "userID": userID1,
                                "articleTypeID": articleTypeID1,
                                "articleTitle": articleTitle1,
                                "articleContent": articleContent1,
                                "articleDateTime": articleDateTime1,
                                "articleModified": articleModified1,
                                "articleLike": savearticleLike,
                                "articleStatus": "1",
                                "action": "updateLike"
                            },
                            dataType: "json",
                            success: function (data) {
                                console.log("收讚");
                                const likeCountElement = document.querySelector('i.fa.fa-thumbs-up'); // 即時更新畫面的按讚數
                                likeCountElement.textContent = savearticleLike;
                            }
                        });
                    }
                });
            }
        }
    });
}

//留言按鈕(顯示此文章的留言)
async function showComment() {
    console.log("觸發到留言按鈕");
    let list_input = "";
    list_input = `<tr>
        <td>
            <textarea id="content" name="content" rows="5" cols=20" class="content"></textarea><button class="btn_addcomment">送出</button>
        </td>
    </tr>`;
    $("thead.thead").append(list_input);
    if (commentShow == "hidden") {
        commentShow = "show";
        $.ajax({
            url: "http://localhost:8080/ski/FrontendArticle",
            type: "Post",
            data: {
                "articleID": savearticleID,
                "action": "showComment"
            },
            dataType: "json",
            success: function (data) {
                // console.log(data);
                if (data.length <= 10) {
                    for (let i = 0; i < data.length; i++) {
                        let commentID = data[i].commentID;
                        let articleID = data[i].articleID;
                        let userID = data[i].userID;
                        let commentContent = data[i].commentContent;
                        let commentDateTime = data[i].commentDateTime;
                        let commentModified = data[i].commentModified;
                        let commentLike = data[i].commentLike;
                        // console.log(data[0].userID);
                        $.ajax({
                            url: "http://localhost:8080/ski/FrontendArticle",
                            type: "Post",
                            data: {
                                "userID": data[i].userID,
                                "action": "getMemberUserName"
                            },
                            dataType: "json",
                            success: function (data) {
                                // console.log(data);
                                let saveUserName1 = data.userName;
                                let list_comment = "";
                                list_comment = `<tr>
                        <td>
                            <p class="text-left">${saveUserName1}</p>
                            <br>
                            <p class="text-left">${commentContent}</p><p class="time right-align">${commentDateTime}</p>
                        </td>
                        </tr>`;
                                $("tbody.tbody").append(list_comment);
                            }
                        });
                    }
                } else if (data.length > 10) {
                    for (let i = 0; i < 10; i++) {
                        let commentID = data[i].commentID;
                        let articleID = data[i].articleID;
                        let userID = data[i].userID;
                        let commentContent = data[i].commentContent;
                        let commentDateTime = data[i].commentDateTime;
                        let commentModified = data[i].commentModified;
                        let commentLike = data[i].commentLike;
                        $.ajax({
                            url: "http://localhost:8080/ski/FrontendArticle",
                            type: "Post",
                            data: {
                                "userID": data.userID,
                                "action": "getMemberUserName"
                            },
                            dataType: "json",
                            success: function (data) {
                                let saveUserName1 = data.userName;
                                let list_comment = "";
                                list_comment = `<tr>
                        <td>
                            <p class="text-left">${saveUserName1}</p>
                            <br>
                            <p class="text-left">${commentContent}</p><p class="time right-align">${commentDateTime}</p>
                        </td>
                        </tr>`;
                                $("tbody.tbody").append(list_comment);
                            }
                        });
                    }
                }
            }
        });
    } else if (commentShow == "show") {
        commentShow = "hidden";
        $("tbody.tbody").empty(); //清空留言
        $("thead.thead").empty(); //清空新增留言
    }
}


//送出新增留言
$("thead.thead").on("click", "button.btn_addcomment", function () {
    console.log("送出留言成功");
    let addCommentContent = $("textarea.content").val();
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
    articleID1
    $.ajax({
        url: "http://localhost:8080/ski/FrontendArticle",
        type: "Post",
        data: {
            "articleID": savearticleID,
            "userID": 1,
            "commentContent": addCommentContent,
            "commentDateTime": formattedTime,
            "commentModified": undefined,
            "commentLike": 0,
            "action": "addComment"
        },
        dataType: "json",
        success: function (data) {
            console.log("新增留言成功");
            // console.log(Number($("span.comment").text()));
            savecomment = Number($("span.comment").text()) + 1;
            const commentCountElement = document.querySelector('span.comment'); // 即時更新畫面的留言數
            commentCountElement.textContent = savecomment;
            $("tbody.tbody").empty(); //清空留言
            $("textarea.content").val(""); // 輸入框清空
            $.ajax({
                url: "http://localhost:8080/ski/FrontendArticle",
                type: "Post",
                data: {
                    "articleID": savearticleID,
                    "action": "showComment"
                },
                dataType: "json",
                success: function (data) {
                    // console.log(data);
    
                    if (data.length <= 10) {
                        for (let i = 0; i < data.length; i++) {
                            let commentID = data[i].commentID;
                            let articleID = data[i].articleID;
                            let userID = data[i].userID;
                            let commentContent = data[i].commentContent;
                            let commentDateTime = data[i].commentDateTime;
                            let commentModified = data[i].commentModified;
                            let commentLike = data[i].commentLike;
                            $.ajax({
                                url: "http://localhost:8080/ski/FrontendArticle",
                                type: "Post",
                                data: {
                                    "userID": data[i].userID,
                                    "action": "getMemberUserName"
                                },
                                dataType: "json",
                                success: function (data) {
                                    let saveUserName1 = data.userName;
                                    let list_comment = "";
                                    list_comment = `<tr>
                            <td>
                                <p class="text-left">${saveUserName1}</p>
                                <br>
                                <p class="text-left">${commentContent}</p><p class="time right-align">${commentDateTime}</p>
                            </td>
                            </tr>`;
                                    $("tbody.tbody").append(list_comment);
                                }
                            });
                        }
                    } else if (data.length > 10) {
                        for (let i = 0; i < 10; i++) {
                            let commentID = data[i].commentID;
                            let articleID = data[i].articleID;
                            let userID = data[i].userID;
                            let commentContent = data[i].commentContent;
                            let commentDateTime = data[i].commentDateTime;
                            let commentModified = data[i].commentModified;
                            let commentLike = data[i].commentLike;
                            $.ajax({
                                url: "http://localhost:8080/ski/FrontendArticle",
                                type: "Post",
                                data: {
                                    "userID": data.userID,
                                    "action": "getMemberUserName"
                                },
                                dataType: "json",
                                success: function (data) {
                                    let saveUserName1 = data.userName;
                                    let list_comment = "";
                                    list_comment = `<tr>
                            <td>
                                <p class="text-left">${saveUserName1}</p>
                                <br>
                                <p class="text-left">${commentContent}</p><p class="time right-align">${commentDateTime}</p>
                            </td>
                            </tr>`;
                                    $("tbody.tbody").append(list_comment);
                                }
                            });
                        }
                    }
                }
            });
            
        }
    });
});




//檢舉按鈕(按下按鈕呼叫此fumction)
function openReportForm() {
    // 這裡可以寫彈出填寫表單的程式碼，例如使用 JavaScript 的 prompt() 函式或彈出自訂的模態視窗等
    // 在這個範例中，我們使用 prompt() 函式彈出一個簡單的填寫表單
    const reportReason = prompt("請填寫檢舉原因：");
    if (reportReason) {
        // 在這裡可以處理使用者填寫的檢舉原因，例如發送到伺服器或進行其他操作
        $.ajax({
            url: "http://localhost:8080/ski/FrontendArticle",
            type: "Post",
            data: {
                "articleID": articleID1,
                "userID": saveUserID,
                "reportContent": reportReason,
                "action": "addReportContent"
            },
            dataType: "json",
            success: function (data) {
                alert("感謝你的檢舉！檢舉原因：" + reportReason);
            }
        });
    }
}
