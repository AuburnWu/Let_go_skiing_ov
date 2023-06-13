
$("#searchByIdBtn").click(function(){
    let searchID=$("#searchByID").val();
    checkUserID(searchID);
    $("#searchByID").val("");
    
})

$("#searchByNameBtn").click(function(){
    let searchName=$("#searchByName").val();
    checkUserName(searchName);
    $("#searchByName").val("");
})

function checkUserID(searchID){
$.ajax({
    url:"CourseOrderServlet",
    type:"POST",
    data:{"searchID":searchID,
           "action":"checkUserID"
   },
    dataType:"json",
    success:function(data){
        console.log("使用者ID確認成功");
        if(data!=null){
            $("#displayOrder>table>thead").html("<th>訂單編號</th><th>會員編號</th><th>訂單總價</th><th>購買時間</th>");
            $("#displayOrder>table>tbody").html("");
            $("#displayDetail>table>thead").html("");
            $("#displayDetail>table>tbody").html("");
                
            for(let i=0;i<data.length;i++){
                let list_html = "";
                let courseOrderID=data[i].courseOrderID;
                let userID=data[i].userID;
                let totalPrice=data[i].totalPrice;
                let buyDateTime=data[i].buyDateTime;

                list_html +="<tr orderID="+courseOrderID+" type='order' style='cursor: pointer'>";
                list_html +=    "<td>"+courseOrderID+"</td>";
                list_html +=    "<td>"+userID+"</td>";
                list_html +=    "<td>"+totalPrice+"</td>";
                list_html +=    "<td>"+buyDateTime+"</td>";
                list_html +="</tr>";

                $("#displayOrder>table>tbody").prepend(list_html);
            }
        }else{
            $("#displayOrder>table>thead").html("");
            $("#displayOrder>table>tbody").html("<p style='color:red'>該使用者不存在</p>");
            $("#displayDetail>table>thead").html("");
            $("#displayDetail>table>tbody").html("");
            console.log("無此使用者")
        }


    },
    error:function(){
        console.log("使用者ID確認失敗");
    }
})
}

function checkUserName(searchName){
    $.ajax({
        url:"CourseOrderServlet",
        type:"POST",
        data:{"searchName":searchName,
               "action":"checkUserName"
       },
       dataType:"json",
       success:function(data){
            if(data!=null){
                console.log("列出符合條件的使用者")
                $("#displayOrder>table>thead").html("<th>會員編號</th><th>會員姓名</th><th>信箱</th><th>身分證字號</th><th>電話號碼</th>");
                $("#displayOrder>table>tbody").html("");
                $("#displayDetail>table>thead").html("");
                $("#displayDetail>table>tbody").html("");
                for(let i=0;i<data.length;i++){
                    let list_html = "";
                    let userID=data[i].userID;
                    let userName=data[i].userName;
                    let email=data[i].email;
                    let personID=data[i].personID;
                    let phone=data[i].phone;
    
                    list_html +="<tr userID="+userID+" type='user' style='cursor: pointer'>";
                    list_html +=    "<td>"+userID+"</td>";
                    list_html +=    "<td>"+userName+"</td>";
                    list_html +=    "<td>"+email+"</td>";
                    list_html +=    "<td>"+personID+"</td>";
                    list_html +=    "<td>"+phone+"</td>";
                    list_html +="</tr>";
    
                    $("#displayOrder>table>tbody").prepend(list_html);
                }
            }else{
                console.log("無符合條件的使用者")
                $("#displayOrder>table>thead").html("");
                $("#displayOrder>table>tbody").html("<p style='color:red'>無使用者符合搜尋條件</p>");
                $("#displayDetail>table>thead").html("");
                $("#displayDetail>table>tbody").html("");
            }
       },
       error:function(){
       }
    })
}
$("#displayOrder").on("click","tr[type*='user']",function(){
    let memberID=$(this).attr("userID");
    checkUserID(memberID);
})

$("#displayOrder").on("click","tr[type*='order']",function(){
    let orderID=$(this).attr("orderID");
    $.ajax({
        url:"CourseOrderServlet",
        type:"POST",
        data:{
            "action":"listOrder",
            "courseOrderID":orderID
        },
        dataType:"JSON",
        success:function(data){
            $("#displayDetail>table>thead").html("<th>訂單細項編號</th><th>課程圖片</th><th>課程名稱</th><th>課程價格</th><th>審核狀態</th>")
            $("#displayDetail>table>tbody").html("");
            for(let j=0;j<data.length;j++){
                let detail_html = "";
                let courseOrderDetailID=data[j].courseOrderDetailID;
                let courseID=data[j].courseID;
                let coursePrice=data[j].coursePrice;
                let courseOrderDetailStatus=data[j].courseOrderDetailStatus;
                let coursePhoto=data[j].coursePhoto;
                let courseName=data[j].courseName;

                detail_html+="<tr detailID="+courseOrderDetailID+">"
                detail_html+=   "<td>"+courseOrderDetailID+"</td>"
                detail_html+=   '<td><img alt="" src="/ski/courseImgServlet?courseID='+courseID+'" /></td>'
                detail_html+=   "<td>"+courseName+"</td>"
                detail_html+=   "<td>"+coursePrice+"</td>"
                detail_html+=   "<td>"+courseOrderDetailStatus+"</td>"
                detail_html+="</tr>"

                $("#displayDetail>table>tbody").prepend(detail_html);
            }
        },
        error:function(){
            console.log("查詢訂單資料失敗")
        }
    })
})