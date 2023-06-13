var MemberID=sessionStorage.getItem("userID");
// 訂單已結帳:1,訂單中有未通過或審核中的訂單:2,訂單中皆為已通過狀態:3,訂單中有未通過的細項:4
$(document).ready(function init(){
    let courseOrderID=sessionStorage.getItem("courseOrderID");
    $.ajax({
        url:"CourseOrderServlet",
        type:"POST",
        data:{
            "courseOrderID":courseOrderID,
            "action":"listOrder"
        },
        dataType:"json",
        success:function(data){
            console.log("訂單細項載入成功");
            for(let j=0;j<data.length;j++){
                let detail_html = "";
                let courseOrderDetailID=data[j].courseOrderDetailID;
                let courseID=data[j].courseID;
                let coursePrice=data[j].coursePrice;
                let courseOrderDetailStatus=data[j].courseOrderDetailStatus;
                let coursePhoto=data[j].coursePhoto;
                let courseName=data[j].courseName;

                detail_html+="<tr detailID="+courseOrderDetailID+">"
                detail_html+=   '<td><img alt="" src="/ski/courseImgServlet?courseID='+courseID+'" /></td>'
                detail_html+=   "<td>"+courseName+"</td>"
                detail_html+=   "<td>"+coursePrice+"</td>"
                detail_html+=   "<td class='status'>"+courseOrderDetailStatus+"</td>"
                if(courseOrderDetailStatus=="已結帳"){
                }
                else{
                    detail_html+=   "<td><div class='delMark'>X</div></td>"
                }
                detail_html+="</tr>"

                $("#courseOrderDetailList>table>tbody").prepend(detail_html);
            }
        },
        error:function(){
            console.log("訂單細項載入失敗");
        }
    })
    total(courseOrderID);
    check(courseOrderID);
    

});
$(".row").on("click",".delMark",function(){
    let listID=$(this).closest("tr").attr("detailID");
    console.log("觸發"+listID);
    $.confirm({
        title:"移除課程?",
        content:"確定要從訂單移除課程嗎?",
        buttons:{
            確定:function(){
                $.ajax({
                    url:"CourseOrderServlet",
                    type:"POST",
                    data:{
                        "action":"removeOrderDetail",
                        "courseOrderDetailID":listID               
                },
                    dataType:"json",
                    success:function(data){
                        console.log("訂單成功刪除");
                        let courseOrderDetailID=data.courseOrderDetailID;
                        $("tr[detailID="+courseOrderDetailID+"]").remove();
                        let courseOrderID=sessionStorage.getItem("courseOrderID");
                        total(courseOrderID);
                        check(courseOrderID);
                    },
                    error:function(err){
                        console.log("訂單刪除失敗");
                        
                    }

                })
            },
            取消:function(){

            }
        }
    })
    
})
$(".row").on("click","div[status='checkout']",function(){
    let courseOrderID=sessionStorage.getItem("courseOrderID");
        $.confirm({
            title:"結帳確認",
            content:"確定結帳訂單內的課程嗎?",
            buttons:{
                確定:function(){
                    $.ajax({
                        url:"CourseOrderServlet",
                        type:"POST",
                        data:{"courseOrderID":courseOrderID,
                               "action":"checkout"
                       },
                        dataType:"json",
                        success:function(data){

                                console.log("結帳成功")
                                $("#inputDiv").remove();
                                $(".delMark").remove();
                                $(".status").text("已結帳");
                                total(courseOrderID);
                                check(courseOrderID);
                                init();
                            
                        },
                        error:function(){
                            console.log("送出申請失敗")
                        }
                    }
                    )
                },
                取消:function(){

                }
            }
        })

    }

)

function total(courseOrderID){
    $.ajax({
       url:"CourseOrderServlet",
       type:"POST",
       data:{"courseOrderID":courseOrderID,
              "action":"getTotal"
      },
       dataType:"json",
       success:function(data){
           let totalPrice=data.totalPrice;
           console.log("總價載入成功:"+totalPrice)
           $("#totalPrice").text(totalPrice);
       },
       error:function(){
           console.log("總價載入失敗")
       }
    })
   }
   function check(courseOrderID){
    $("#checkBtn").empty();

    $.ajax({
        url:"CourseOrderServlet",
        type:"POST",
        data:{"courseOrderID":courseOrderID,
               "action":"orderStatus"
       },
        dataType:"json",
        success:function(data){
            let status=data.status;
            console.log("訂單狀態載入成功:"+status)
            if(status==3){
                let checkoutBtn="結帳"
                 $("#checkBtn").prepend(checkoutBtn);
                 $("#checkBtn").attr("status","checkout");
            }else if(status==2){
                let checkoutBtn="請等待課程審核"
                $("#checkBtn").prepend(checkoutBtn);
                $("#checkBtn").removeAttr("status");
            }
            else if(status==4){
                let checkoutBtn="訂單內有審核未通過的課程<br>可刪除項目或重新申請"     
                $("#checkBtn").prepend(checkoutBtn);
                $("#checkBtn").removeAttr("status");
            }
            else{
                let checkoutBtn="訂單已完成結帳" 
                $("#checkBtn").prepend(checkoutBtn);
                $("#checkBtn").removeAttr("status");
                $("#inputDiv").remove();
            }
        },
        error:function(){
            console.log("訂單狀態載入失敗")
        }
     })

   }

