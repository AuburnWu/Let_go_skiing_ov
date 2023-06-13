var memberLevel;

$(document).ready(function () {
$(window).on("resize", function () {
    resizeMask();
  });
  if (sessionStorage.getItem("userID") == null) {
    getRunnungCourse("-1");
  } else {
    setLevel();
  }

  $(".skilloptions").on("change", function () {
    getCourseByKeywordAndTag();
  });

  $(".leveloptions").on("change", function () {
    getCourseByKeywordAndTag();
  });

  $("#searchbar").on("input", function () {
    getCourseByKeywordAndTag();
  });
  
  	//加入購物車功能綁定
  	$("#courseContent").on("click", ".addCart", function (event) {
     userID = sessionStorage.getItem("userID");
 		courseID = $(this)
      .closest(".btnGroup")
      .siblings(".textZone")
      .find(".courseID")
      .val();
		
		$.ajax({
        url: "http://localhost:8080/ski/course_AC",
        type: "POST",
        dataType: "json",
        data: { 
        userID: userID,
        courseID: courseID
         },
        success: function (response) {
        if(response){
        	alert("加入購物車成功");
        }else{
        	alert("操作失敗，請聯絡客服人員");
        }
          
        },
        error: function () {
          console.log("error");
        },
      });
      ;
    });
});

function setLevel() {
  var data = {
    userID: sessionStorage.getItem("userID"),
  };
  var jsonData = JSON.stringify(data);
  $.ajax({
    url: "http://localhost:8080/ski/member/getOneMember",
    data: jsonData,
    type: "POST",
    dataType: "json",
    success: function (data) {
    memberLevel = data.level;
    var levelText;
      if (memberLevel == 0) {
        levelText = "初階";
      } else if (memberLevel == 1) {
        levelText = "中階";
      } else if (memberLevel == 2) {
        levelText = "高階";
      }
      
      
      $("#levelHolder").removeClass("d-none");
    	$("#memberLevel").text(levelText);
      
      getRunnungCourse(memberLevel);
    },
    error: function () {
      console.log("error");
    },
  });
}

function getRunnungCourse(memberLevel) {
  $.ajax({
    url: "http://localhost:8080/ski/course_GRC",
    type: "POST",
    dataType: "json",
    success: function (data) {
      renderCourse(data, memberLevel);
    },
    error: function () {
      console.log("error");
    },
  });
}

function getCourseByKeywordAndTag() {
  var keyWord = $("#searchbar").val();
  var courseLevel = $("input[name='leveloptions']:checked").val();
  var courseSkill = $("input[name='skilloptions']:checked").val();

  if (!keyWord) {
    keyWord = "";
  }
  if (!courseLevel) {
    courseLevel = "-1";
  }
  if (!courseSkill) {
    courseSkill = "-1";
  }

  $.ajax({
    url: "http://localhost:8080/ski/course_GBKAT",
    type: "POST",
    dataType: "json",
    data: {
      keyWord: keyWord,
      courseLevel: courseLevel,
      courseSkill: courseSkill,
    },
    success: function (data) {
      renderCourse(data, memberLevel);
    },
    error: function () {
      console.log("error");
    },
  });
}

function renderCourse(Course, memberLevel) {
  $("#courseContent").empty();
   if(Course.length == 0){
   	noneConfirm = $("<p>")
       .text("尚無相關課程")
   	$("#courseContent").append(noneConfirm).addClass("d-flex justify-content-center");
   }else{
   $("#courseContent").removeClass("d-flex justify-content-center");
   	Course.forEach((course) => {
    // 子頁導向連結生成
    subDirectLink = $("<a>")
      .attr(
        "href",
        "http://localhost:8080/ski/course/frontend_courseDetail.html?courseID=" +
          course.courseID
      )
      .text("詳細資訊")
      .css({
        "text-decoration": "none",
        color: "white",
      });
    // 子頁導向按鈕生成
    const subDirectButton = $("<button>")
      .addClass("btn btn-secondary m-2 subDirect ")
      .append(subDirectLink);

    //購物車按鈕生成
    // register_allow = true;
    // valid_result = "";

    // if (register_allow) {
    //   valid_result = "加入購物車";
    // } else {
    //   valid_result = "會員層級不符";
    // }

    addCartButton = $("<button>")
      .addClass("btn btn-secondary m-2 addCart")
      .text("加入購物車");

    //if (register_allow) {
    //  addCartButton.addClass("disable");
    //}

    

    const base64Image = btoa(
      new Uint8Array(course.coursePhoto).reduce(
        (data, byte) => data + String.fromCharCode(byte),
        ""
      )
    );
    const imageSrc = `data:image/png;base64,${base64Image}`;

var courseSkill = "";
      if (course.skill == 0) {
        courseSkill = "單板";
      } else if (course.skill == 1) {
        courseSkill = "雙版";
      }

      var courseLevel = "";
      if (course.level == 0) {
        courseLevel = "初階";
      } else if (course.level == 1) {
        courseLevel = "中階";
      } else if (course.level == 2) {
        courseLevel = "高階";
      }

    cardStr = `
    
       <div class="courseCard border-top my-3 p-2 d-flex align-items-center position-relative">
           <div class="imgZone mx-2 ">
             <img class="crousePhoto" src='${imageSrc}' style="max-height: 180px; max-width: 250px; color: gray; height: 180px; width: 250px;">
           </div>

           <div class="textZone mb-5">
            <div id="titleGroup" class="d-flex align-items-center">
            	<input class="courseID d-none" value="${course.courseID}"></input>
               <h1 class="me-2">${course.courseName}</h1>
               <p>$ ${course.coursePrice}</p>               
             </div>
             <p>${courseSkill} / ${courseLevel}</p>
           </div>

           <div class="btnGroup m-2 position-absolute bottom-0 end-0"></div>
           <div id="${course.courseID}" class="cardMask position-absolute d-none d-flex align-items-center" style="background-color: rgba(43, 43, 43, 0.8);" > </div>
     		</div>
     `;

    $("#courseContent").append(cardStr);


    resizeMask(memberLevel);
    let courseID = course.courseID;
    levelFilter(courseID, memberLevel, course.level);

    $(".courseCard").each(function () {
      const btnGroup = $(this).find(".btnGroup");
      btnGroup.append(subDirectButton, addCartButton);
    });
  });
   }
  
}

function resizeMask(memberLevel) {
  //resize mask
  var sourceDiv = $(".courseCard");
  var targetDiv = $(".cardMask");

  var sourceWidth = sourceDiv.width();
  var sourceHeight = sourceDiv.height();

  targetDiv.width(sourceWidth);
  targetDiv.height(sourceHeight);
  targetDiv.addClass("d-flex justify-content-center align-item-center");

  var confirmText;
  if (memberLevel < 0) {
    confirmText = "請登入會員以進行相關操作";
  } else {
    confirmText = "課程難度高於設定級別";
  }

  filterConfirm = $("<p>").text(confirmText).css({
    color: "white",
  });

  targetDiv.html(filterConfirm);
}

function levelFilter(courseID, memberLevel, courseLevel) {
  if (memberLevel < 0) {
    $("#" + courseID).removeClass("d-none");
  } else if (memberLevel < courseLevel) {
    $("#" + courseID).removeClass("d-none");
  }
}

function addCart(){
	userID = sessionStorage.getItem("userID");
 	courseID = $(this)
      .closest(".btnGroup")
      .siblings(".textZone")
      .find(".courseID")
      .val();

      console.log(userID);
      console.log(courseID);
 
 
}
