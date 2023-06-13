const saveReportID = sessionStorage.getItem("saveReportID");
// const saveUserID = sessionStorage.getItem("userID");
// var saveReportID = 2;
var reportID1 = 1;
var articleID1 = 1;
var userID1 = 1;
var reportContent1 = "";
var reportStatus1 = "";
var reportResponse1 = "";
var number = 0;
var repeat = 0;
var saveMemberEmail = "";



//顯示全部
$(document).ready(function init() {

    console.log("js載入成功");
    // console.log(saveReportID);
    $.ajax({
        url: "http://localhost:8080/ski/BackendReport",
        type: "Post",
        data: {
            "reportID": saveReportID,
            "articleID": articleID1,
            "userID": userID1,
            "reportContent": reportContent1,
            "reportStatus": reportStatus1,
            "reportResponse": reportResponse1,
            "action": "showAll"
        },
        dataType: "json",
        success: function (data) {
            // console.log(data.length);
            // console.log(data);
            console.log("後端檢舉處理載入成功");
            let list_html = "";
            var reportID = data[saveReportID - 1].reportID;
            var articleID2 = data[reportID - 1].articleID;
            var userID2 = data[reportID - 1].userID;
            var reportContent2 = data[reportID - 1].reportContent;
            var reportStatus2 = data[reportID - 1].reportStatus;
            var reportResponse2 = data[reportID - 1].reportResponse;

            if (reportStatus2 == "1") {
                list_html += `<tr>
                <td><p>檢舉編號:</p></td>
                <td><p class="id">${reportID}</p></td>
                </tr>
                <tr>
                <td><p>文章編號:</p></td>
                <td><p>${articleID2}</p></td>
                </tr>
                <tr>
                <td><p>檢舉理由:</p></td>
                <td><p>${reportContent2}</p></td>
                </tr>
                <tr>
                <td><p>檢舉回覆:</p></td>
                <td>
                <p>${reportResponse2}</p>
                </td>
                </tr>
                <tr>
                <td><p>結果:</p></td>
                <td><button class="btn_finish">已送出檢舉回覆，返回上一頁</button></td>
                /tr>
                                `;
            } else if (reportStatus2 == "0") {
                list_html += `<tr>
                <td><p>檢舉編號:</p></td>
                <td><p class="id">${reportID}</p></td>
                </tr>
                <tr>
                <td><p>文章編號:</p></td>
                <td><p>${articleID2}</p></td>
                </tr>
                <tr>
                <td><p>檢舉理由:</p></td>
                <td><p>${reportContent2}</p></td>
                </tr>
                <tr>
                <td><p>檢舉回覆:</p></td>
                <td>
                <textarea id="content" name="content" rows="10" cols="50" class="statusResponse" value=""></textarea>
                </td>
                </tr>
                <tr>
                <td><p>結果:</p></td>
                <td><button class="btn_update">送出檢舉回覆</button></td>
                /tr>
                                `;
            }

            $("#reportTableBody").append(list_html); // 加入網頁表格中
            reportID1 = reportID;
            articleID1 = articleID2;
            userID1 = userID2;
            reportContent1 = reportContent2;
            reportStatus1 = reportStatus2;
            reportResponse1 = reportResponse2;

        },
        error: function (err) {
            console.log("後端檢舉處理載入失敗");
            console.log(err);
        }

    })
});

//修改狀態1(檢舉處理回覆)
$("#type").on("click", "button.btn_update", function () {
    console.log("檢舉處理回覆囉!");
    reportStatus1 = "1";
    let task_text = $("textarea.statusResponse").val().trim();
    if(task_text != ""){
        $.ajax({
            url: "http://localhost:8080/ski/BackendReport",
            type: "Post",
            data: {
                "reportID": saveReportID,
                "articleID": articleID1,
                "userID": userID1,
                "reportContent": reportContent1,
                "reportStatus": reportStatus1,
                "reportResponse": task_text,
                "action": "updateContent"
            },
            dataType: "json",
            success: function (data) {
                
            },
            error: function (err) {
                console.log("後端檢舉處理修改失敗");
                console.log(err);
            }
        });
        $.ajax({
            url: "http://localhost:8080/ski/FrontendArticle",
            type: "Post",
            data: {
                "userID": userID1,
                "action": "getMemberUserName"
            },
            dataType: "json",
            success: function (data) {
                saveMemberEmail = data.email;
                console.log(saveMemberEmail);
                $.ajax({ //傳email
                    url: "http://localhost:8080/ski/SendEmailServlet",
                    type: "Post",
                    data: {
                        "email":saveMemberEmail,
                        "subject":"Let's go skiing 檢舉回覆",
                        "message":task_text
                    },
                    success: function (data) {
                        // alert("Email傳送成功");
                        // console.log(saveMemberEmail);
                        alert("檢舉處理成功");
                        window.location.href = "http://localhost:8080/ski/article/backend_Report.html";
                    },
                    error: function (err) {
                        console.log("Email傳送失敗");
                        // console.log(saveMemberEmail);
                        console.log(err);
                    }
                })
            }
        });

    }else if(task_text == ""){
        alert("檢舉回覆不可為空白");
    }
});


//已送出檢舉回覆，返回上一頁(已經回覆)
$("#type").on("click", "button.btn_finish", function () {
    window.location.href = "http://localhost:8080/ski/article/backend_Report.html";
});