var MemberID=sessionStorage.getItem("userID");
$(document).ready(function init(){
     $.ajax({
         url:"CourseOrderServlet",
         type:"POST",
         data:{"userID":MemberID,
                "action":"setOrderHeader"
        },
         dataType:"json",
         success:function(data){
         
             console.log("訂單載入成功");
             for(let i=0;i<data.length;i++){
                let list_html = "";
                let courseOrderID=data[i].courseOrderID;
                let buyDateTime=data[i].buyDateTime;

                console.log(data[i]);
                list_html +="<h4>"+buyDateTime+"</h4>";
                list_html +="<table id='Header"+courseOrderID+"' style='cursor: pointer' headerID="+courseOrderID+">";
                list_html +=    "<thead>";
                list_html +=        "<th>課程</th>";
                list_html +=        "<th>名稱</th>";
                list_html +=        "<th>單價</th>";
                list_html +=        "<th>審核狀態</th>";
                list_html +=    "</thead>";
                list_html +=    "<tbody></tbody>"
                list_html +="</table>";
                $("#courseOrderList").prepend(list_html);
                getOrderDetail(courseOrderID);
 
             }
             

         },
         error:function(err){
         	console.log("訂單載入失敗")
         }

     })
});


   function getOrderDetail(courseOrderID){
    $.ajax({
        url:"CourseOrderServlet",
        type:"POST",
        data:{"courseOrderID":courseOrderID,
               "action":"listOrder"
       },
        dataType:"json",
        success:function(data){
            console.log("訂單明細載入成功")
            for(let j=0;j<data.length;j++){
                let detail_html = "";
                let courseOrderDetailID=data[j].courseOrderDetailID;
                let courseID=data[j].courseID;
                let coursePrice=data[j].coursePrice;
                let courseOrderDetailStatus=data[j].courseOrderDetailStatus;
                let coursePhoto=data[j].coursePhoto;
                let courseName=data[j].courseName;

                detail_html+="<tr detailID="+courseOrderDetailID+">"
                detail_html +=  '<td><img alt="" src="/ski/courseImgServlet?courseID='+courseID+'" /></td>'
                detail_html+=   "<td>"+courseName+"</td>"
                detail_html+=   "<td>"+coursePrice+"</td>"
                detail_html+=   "<td>"+courseOrderDetailStatus+"</td>"
                detail_html+="</tr>"

                $("#Header"+courseOrderID+">tbody").prepend(detail_html);


            }
            
        },
        error:function(){
            console.log("訂單明細載入失敗")
        }
    })
   }
$("#courseOrderList").on("click","table[id*='Header']",function(){
    // 傳值到訂單細節頁面
    var courseOrderID=$(this).attr("headerID");
    sessionStorage.setItem("courseOrderID",courseOrderID);
    $(location).attr("href","courseOrderDetail.html")
    console.log(courseOrderID);



})
