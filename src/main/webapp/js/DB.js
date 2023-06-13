// function init() {
//     $.ajax({
//         url: "courseAdList",
//         type: "GET",
//         dataType: "json",
//         success: function(data) {
//             // 將回應資料顯示在HTML上
//             // var adListDiv = $("#adList");
//             // adListDiv.html(""); // 清空內容
//             // data.forEach(function(course) {
//             //     adListDiv.append("<p>" + course.name + "</p>");
//             // });
//             console.log(data);
//         },
//         error: function() {
//             console.log("Request failed");
//         }
//     });
// }
// $(document).ready(function() {
//     init();
// });

(() => {

    fetch('courseAdList')
    .then(response => response.json())
    .then(adlist => {
        // 將資料放入表格
        let tableBody = $('#adList');
        adlist.forEach(ad => {
            let html = `
            <tr>
            <td>${ad.courseAD}</td>
            <td>${ad.courseID}</td>
            <td>${ad.courseName}</td>
            <td>${ad.startDate}</td>
            <td>${ad.endDate}</td>
            <td><button class="btn btn-outline-danger btn-delete" data-id="${ad.courseAD}"">刪除</button></td></tr>`;
            tableBody.append(html);
        });
    })
    .catch(error => {
        console.log('獲取資料失敗:', error);
    });
    
    // 監聽刪除按鈕的點擊事件
    $(document).on("click",".btn-delete",function deleteAD() {
        let courseAD = $(this).data('id');

        $.ajax({
            url: "courseAdDelete",
            type: "POST",
            data: { cAdId: courseAD },
            dataType: "json",
            success: function (data) {
                console.log("a");
                location.reload();
            },
            error: function (error) {
              console.log(error);
            },
          });
    });


    //新增事件
    $(".btn-insert").click(function () {
        $.fancybox.open({
            type: 'iframe',
            src: 'back_Insert.html',
            opts: {
              modal: true,
              // afterShow: function (instance, current) {
              //   callBackFunction();
              // }
            }
          });
    });
})();

