var time;
 // 從網址中獲取couponId的值
 var urlParams = new URLSearchParams(window.location.search);
 var qaId = urlParams.get("qaID");

$(document).ready(function () {
    $('#time').inputmask();
    init();
});
  

function init() {
    axios.get("/ski/qa/qaSelect", { params: { qaId: qaId } }).then((res) => {
        $("#qaType").val(res.data.questionType);
        $("#title").val(res.data.questionTitle);
        $("#ans").val(res.data.answerContent);
        time = res.data.questionDate;
        console.log(res.data)
    })
    
    // $.ajax({
    //     url: "http://localhost:8080/ski/qa/qaSelect?qaId="+qaId,
    //     type: "GET",
    //     dataType: "json",
    //     success: function (data) { 
    //       alert("成功")
    //     },
    //     error: function (error) {
    //         console.log(error);

    //     }

    // });
}


//submit
    $(document).on("click", "#submit", function newQA() { 
        
        let title = $("#title").val();
        let type = $("#qaType").val();
        // let time = $("#time").val();
        let ans = $("#ans").val();
        console.log("title:", title);
        console.log("type:", type);
        console.log("ans:", ans);
        
        $.ajax({
            url: "/ski/editQa",
            type: "POST",
            data:JSON.stringify({
                questionTitle: title,
                questionType: type,
                // questionDate: time,
                answerContent: ans,
                questionDate: time,
                qaID:qaId
            }),
            dataType: "json",
            success: function (data) {
                console.log("a");
                
                // Swal.fire({
                //     title: '新增成功!',
                //     icon: 'success',
                //     showConfirmButton: false,   
                // });
                alert("123")
                
                window.location.href = '/ski/qa/back_qaList.html';
            },
            error: function (error) {
                console.log(error);
                window.location.href = '/ski/qa/back_qaList.html';
            }
        })

        
    })