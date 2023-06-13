var MemberID=sessionStorage.getItem("userID");
$(document).ready(function init(){
     $.ajax({
         url:"CourseCarServlet",
         type:"POST",
         data:{"userID":MemberID,
                "action":"listUserCar"
        },
         dataType:"json",
         success:function(data){
         
             console.log("購物車載入成功");
             for(let i=0;i<data.length;i++){
                let list_html = "";
                let courseID=data[i].courseID;
                let coursePhoto=data[i].coursePhoto;
                let courseName=data[i].courseName;
                let coursePrice=data[i].coursePrice;

                
                list_html +="<tr listID="+courseID+">";
                list_html +=    '<td><img alt="" src="/ski/courseImgServlet?courseID='+courseID+'" /></td>'
                list_html +=    "<td>"+courseName+"</td>"
                list_html +=    "<td>"+coursePrice+"</td>"
                list_html +=    "<td><div class='delMark'>X</div></td>"
                list_html +="</tr>"

                $("#courseCarList > table > tbody").prepend(list_html);
                
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
        title:"移除課程?",
        content:"確定要從購物車移除課程嗎?",
        buttons:{
            確定:function(){
                $.ajax({
                    url:"CourseCarServlet",
                    type:"POST",
                    data:{
                        "action":"removeCar",
                        "userID":MemberID,
                        "courseID":listID               
                },
                    dataType:"json",
                    success:function(data){
                        console.log("購物車成功刪除");
                        let courseID=data.courseID;
                        $("tr[listID="+courseID+"]").remove();
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
$("#sendApply").click(function(){
    $("#alert").html("");
    let phoneNumber=$("#phoneNumber").val();
    let idNumber=$("#idNumber").val();
    let regexPhone=new RegExp("^09[0-9]{8}$");
    let regexId=new RegExp("^[a-zA-Z][0-9]{9}$");
    let alert="";
    if(phoneNumber.length==0){
        alert+="<p>請輸入電話號碼</p>";
    }
    if(!regexPhone.test(phoneNumber)){
        alert+="<p>請輸入正確的電話號碼格式</p>"
    }
    if(idNumber.length==0){
        alert+="<p>請輸入身分證字號</p>"
    }
    if(!regexId.test(idNumber)){
        alert+="<p>請輸入正確的身分證字號格式</p>"
    }
    $("#alert").html(alert);
    $("#phoneNumber").val("");
    $("#idNumber").val("");
    if(alert.length==0){
        console.log("資料輸入成功")
        $.confirm({
            title:"申請確認",
            content:"確定送出以上課程申請嗎?",
            buttons:{
                確定:function(){
                    $.ajax({
                        url:"CourseCarServlet",
                        type:"POST",
                        data:{"userID":MemberID,
                               "action":"sendApply"
                       },
                        dataType:"json",
                        success:function(data){
                            if(data.sendApply){
                                console.log("送出申請成功")
                                $("#courseCarList>table>tbody").html("");
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
       url:"CourseCarServlet",
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
