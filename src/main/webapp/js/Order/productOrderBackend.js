
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
    url:"ProductOrderServlet",
    type:"POST",
    data:{"searchID":searchID,
           "action":"checkUserID"
   },
    dataType:"json",
    success:function(data){
        console.log("使用者ID確認成功");
        if(data!=null){
            $("#displayOrder>table>thead").html("<th>訂單編號</th><th>會員編號</th><th>訂單總價</th><th>寄送地址</th><th>購買時間</th><th>訂單狀態</th>");
            $("#displayOrder>table>tbody").html("");
            $("#displayDetail>table>thead").html("");
            $("#displayDetail>table>tbody").html("");
                
            for(let i=0;i<data.length;i++){
                let list_html = "";
                let productOrderID=data[i].productOrderID;
                let userID=data[i].userID;
                let totalPrice=data[i].totalPrice;
                let deliveryAddr=data[i].deliveryAddr;
                let buyDateTime=data[i].buyDateTime;
                let productOrderStatus=data[i].productOrderStatus;

                list_html +="<tr orderID="+productOrderID+" type='order' style='cursor: pointer'>";
                list_html +=    "<td>"+productOrderID+"</td>";
                list_html +=    "<td>"+userID+"</td>";
                list_html +=    "<td>"+totalPrice+"</td>";
                list_html +=    "<td>"+deliveryAddr+"</td>";
                list_html +=    "<td>"+buyDateTime+"</td>";
                list_html +=    "<td>"+productOrderStatus+"</td>";
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
        url:"ProductOrderServlet",
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
        url:"ProductOrderServlet",
        type:"POST",
        data:{
            "action":"listOrder",
            "productOrderID":orderID
        },
        dataType:"JSON",
        success:function(data){
            $("#displayDetail>table>thead").html("<th>訂單細項編號</th><th>商品圖片</th><th>商品名稱</th><th>商品價格</th><th>購買數量</th><th>小計</th>")
            $("#displayDetail>table>tbody").html("");
            for(let j=0;j<data.length;j++){
                let detail_html = "";
                let productOrderDetailID=data[j].productOrderDetailID;
                let productID=data[j].productID;
                let productPrice=data[j].productPrice;
                let quantity=data[j].quantity;
                let productImage=data[j].productImage;
                let productName=data[j].productName;
                let subTotal=productPrice*quantity;

                detail_html+="<tr detailID="+productOrderDetailID+">"
                detail_html+=   "<td>"+productOrderDetailID+"</td>"
                detail_html+=   '<td><img alt="" src="/ski/productImgServlet?productID='+productID+'" /></td>'
                detail_html+=   "<td>"+productName+"</td>"
                detail_html+=   "<td>"+productPrice+"</td>"
                detail_html+=   "<td>"+quantity+"</td>"
                detail_html+=   "<td>"+subTotal+"</td>"
                detail_html+="</tr>"

                $("#displayDetail>table>tbody").prepend(detail_html);
            }
        },
        error:function(){
            console.log("查詢訂單資料失敗")
        }
    })
})