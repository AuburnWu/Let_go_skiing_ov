var MemberID=sessionStorage.getItem("userID");
var productCar;
$(document).ready(function init(){
     $.ajax({
         url:"ProductCarServlet",
         type:"POST",
         data:{"userID":MemberID,
                "action":"listUserCar"
        },
         dataType:"json",
         success:function(data){
         
             console.log("購物車載入成功");
             console.log(data);
             productCar=data;
             console.log(productCar);
             for(let i=0;i<data.length;i++){
                let list_html = "";
                let productID=data[i].productID;
                let productImage=data[i].productImage;
                let productName=data[i].productName;
                let productPrice=data[i].productPrice;
                let quantity=data[i].quantity;
                let subTotal=data[i].subTotal;
                list_html +="<tr listID="+productID+">";
                list_html +=    '<td><img alt="" src="/ski/productImgServlet?productID='+productID+'" /></td>'
                list_html +=    "<td>"+productName+"</td>"
                list_html +=    "<td>"+productPrice+"</td>"
                list_html +=    "<td>"+quantity+"</td>"
                list_html +=    "<td>"+subTotal+"</td>"
                list_html +=    "<td><div class='delMark'>X</div></td>"
                list_html +="</tr>"

                $("#productCarList > table > tbody").prepend(list_html);
                stockCheck(productID,productName);
             }
             

         },
         error:function(err){
         	console.log("購物車載入失敗")
         }

     })

    total();
});
$(".row").on("click",".delMark",function(){
    let listID=$(this).closest("tr").attr("listID");
    console.log("觸發"+listID);
    $.confirm({
        title:"移除商品?",
        content:"確定要從購物車移除商品嗎?",
        buttons:{
            確定:function(){
                $.ajax({
                    url:"ProductCarServlet",
                    type:"POST",
                    data:{
                        "action":"removeCar",
                        "userID":MemberID,
                        "productID":listID               
                },
                    dataType:"json",
                    success:function(data){
                        console.log("購物車成功刪除");
                        let productID=data.productID;
                        $("tr[listID="+productID+"]").remove();
                        total();
                    },
                    error:function(err){
                        console.log("購物車刪除失敗")
                        
                    }

                })
            },
            取消:function(){

            }
        }
    })
    
})
$("#checkout").click(function(){
    $("#alert").html("");
    let address=$("#address").val();
    let cardNumber=$("#cardNumber").val();
    let expiryDate=$("#expiryDate").val();
    let CSC=$("#CSC").val();
    let alert="";
    if(address.length==0){
        alert+="<p>請輸入寄送地址</p>";
    }

    $("#alert").html(alert);
    $("#phoneNumber").val("");
    $("#idNumber").val("");
    if(alert.length==0){
        console.log("資料輸入成功")
        for(let i=0;i<productCar.length;i++){
            stockCheck(productCar[i].productID,productCar[i].productName);
        }
        $.confirm({
            title:"申請確認",
            content:"確定結帳以上商品嗎?",
            buttons:{
                確定:function(){
                    $.ajax({
                        url:"ProductCarServlet",
                        type:"POST",
                        data:{"userID":MemberID,
                                "address":address,
                               "action":"checkout"
                       },
                        dataType:"json",
                        success:function(data){
                            if(data.sendApply){
                                console.log("送出申請成功")
                                $("#productCarList>table>tbody").html("");
                                total();
                            }
                            else{
                                console.log("送出申請失敗(資料庫錯誤)")
                            }
                            
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

})
$("#getMemberINFO").click(function(){
    let radioChecked=$("#getMemberINFO").is(":checked");
    if(radioChecked==true){
        $("#getMemberINFO").prop("checked",false);
        $.ajax({
            url:"CourseCarServlet",
            type:"POST",
            data:{"userID":MemberID,
                   "action":"getMemberINFO"
           },
            dataType:"json",
            success:function(data){
                let userPhone=data.userPhone;
                let userPersonID=data.userPersonID;
                console.log(userPhone);
                $("#phoneNumber").val(userPhone);
                $("#idNumber").val(userPersonID);
                console.log("取得會員資料成功")
            },
            error:function(){
                console.log("取得會員資料失敗")
            }
        })
    }

    
})
function total(){
    $.ajax({
       url:"ProductCarServlet",
       type:"POST",
       data:{"userID":MemberID,
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
function stockCheck(productID,productName){
    $.ajax({
        url:"ProductCarServlet",
        type:"POST",
        data:{"userID":MemberID,
                "productID":productID,
               "action":"stockCheck"
       },
        dataType:"json",
        success:function(data){
            console.log("存貨檢查成功")
            if(data.stockCheck){
                console.log("尚有存貨")
                
            }else{
                $.alert({
                    title:"警告",
                    content:productName+"存貨不足,請至商品頁面重新選擇數量加入購物車",
                    "知道了":function(){
                    }
                })
                $("tr[listID="+productID+"]").remove();
                total();
     
            }

        },
        error:function(){
            console.log("存貨檢查失敗")
        }
     })
}
