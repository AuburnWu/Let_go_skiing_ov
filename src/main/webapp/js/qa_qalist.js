$(document).ready(function () {
    init();
})

function init() {
    $.ajax({
        url: "qalistServlet",
        type: "GET",
        dataType: "json",
        success: function (data) { 
            let Qdata = $("#Qalist");
            Qdata.html("");
            data.forEach(qa => {
                let html = `
                <tr>
                <td id="Id">${qa.qaID}</td>
                <td id="Type">${qa.questionType}</td>
                <td id="Title">${qa.questionTitle}</td>
                <td id="Ans">${qa.answerContent}</td>
                <td id="Time">${qa.questionDate}</td>
                <td>
                <div class="tools" style="display: flex;flex-wrap;">
                <div style = "width:50%">
                <a href="back_qaEdit.html?qaID=${qa.qaID}" data-id="${qa.qaID}" id="edit" style="display: inline-block;" >
                <i class="fas fa-edit me-5"></i>
                </a>
                </div>
                <div style = "width:50%">
                  <i class="fa-solid fa-trash fa-sm" id="delete" role="button" data-id="${qa.qaID}" style="width:20px,display: inline-block;"></i>
                </div>
                </div>
                </td>
              </tr>
                `;
                Qdata.append(html);
            });
        },
        error: function (error) {
            console.log(error);

        }

    });
}

//刪除
$(document).on("click", "#delete", function deleteQa() {
    
    console.log(12);
    let qa = $(this).data('id');
    $.ajax({
        url: "http://localhost:8080/ski/qa/deleteQa",
        type: "POST",
        data: { qaId: qa },
        dataType: "json",
        success: function (data) {
            console.log("a");
            Swal.fire({
                title: '成功!',
                icon: 'success',
                showConfirmButton: false,   
            });
            
            setTimeout(function() {
                location.reload();
              }, 5000);
        },
        error: function (error) {
            console.log(error);
        }
    })
})



//新增
$(document).on("click", "#btn-insert", function InsertQa() {

    //iframe
    
    
    
    
})


//修改
// $(document).on("click", "#edit", function editQA() { 
    
//   // 取得所在行的相關值
//   let qaID = $(this).closest("tr").find("#id").text();
//   let questionType = $(this).closest("tr").find("#type").text();
//   let questionTitle = $(this).closest("tr").find("#title").text();
//   let answerContent = $(this).closest("tr").find("#ans").text();
//   let questionDate = $(this).closest("tr").find("#time").text();

//   sessionStorage.setItem('qaID', qaID);
//   sessionStorage.setItem('questionType', questionType);
//   sessionStorage.setItem('questionTitle', questionTitle);
//   sessionStorage.setItem('answerContent', answerContent);
//   sessionStorage.setItem('questionDate', questionDate);

//     //iframe
    
//     $.fancybox.show({
        
//     })

  
// })

