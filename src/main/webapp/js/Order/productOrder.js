var MemberID=sessionStorage.getItem("userID");
$(document).ready(function init(){
     $.ajax({
         url:"ProductOrderServlet",
         type:"POST",
         data:{"userID":MemberID,
                "action":"setOrderHeader"
        },
         dataType:"json",
         success:function(data){
         
             console.log("訂單載入成功");
             for(let i=0;i<data.length;i++){
                let list_html = "";
                let productOrderID=data[i].productOrderID;
                let buyDateTime=data[i].buyDateTime;
                let totalPrice=data[i].totalPrice;
                let deliveryAddr=data[i].deliveryAddr;
                let productOrderStatus=data[i].productOrderStatus;


                console.log(data[i]);
                list_html +="<h4>"+buyDateTime+"</h4>";
                list_html +="<h7>總價:"+totalPrice+"</h7><br>";
                list_html +="<h7>地址:"+deliveryAddr+"</h7><br>";
                list_html +="<h7>狀態:"+productOrderStatus+"</h7>";
                list_html +="<table id='Header"+productOrderID+"' style='cursor: pointer' headerID="+productOrderID+">";
                list_html +=    "<thead>";
                list_html +=        "<th>課程</th>";
                list_html +=        "<th>名稱</th>";
                list_html +=        "<th>單價</th>";
                list_html +=        "<th>數量</th>";
                list_html +=        "<th>小計</th>";
                list_html +=    "</thead>";
                list_html +=    "<tbody></tbody>"
                list_html +="</table>";
                $("#productOrderList").prepend(list_html);
                getOrderDetail(productOrderID);
 
             }
             

         },
         error:function(err){
         	console.log("訂單載入失敗")
         }

     })
});


   function getOrderDetail(productOrderID){
    $.ajax({
        url:"ProductOrderServlet",
        type:"POST",
        data:{"productOrderID":productOrderID,
               "action":"listOrder"
       },
        dataType:"json",
        success:function(data){
            console.log("訂單明細載入成功")
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
                detail_html +=  '<td><img alt="" src="/ski/productImgServlet?productID='+productID+'" /></td>'
                detail_html+=   "<td>"+productName+"</td>"
                detail_html+=   "<td>"+productPrice+"</td>"
                detail_html+=   "<td>"+quantity+"</td>"
                detail_html+=   "<td>"+subTotal+"</td>"
                detail_html+="</tr>"

                $("#Header"+productOrderID+">tbody").prepend(detail_html);


            }
            
        },
        error:function(){
            console.log("訂單明細載入失敗")
        }
    })
   }

