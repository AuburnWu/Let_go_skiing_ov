$(document).ready(function () {
    init();
})

function init() {
    $.ajax({
        url: "/ski/ad/slideAd",
        type: "GET",
        // dataType: "blob",
        success: function (data) {
            // console.log(data);
            // let btn = $("#carousel-indicators")
            let body = $(".carousel-inner");
            // body.html("");
            // btn.html("");
            data.forEach(product => {
                console.log(1)
                // console.log(product);
                
                // <img src="data:image/png;base64,${base64Data}" class="d-block w-100" alt="">
                
                // if (product.productImage != null || product.productImage.length === 0) {
                //     console.log(product.productImage);
                    // 將像素值列表轉換為 Uint8Array
                    // var pixelData = new Uint8Array(product.productImage);
                    // console.log(pixelData);
                    // 將 Uint8Array 轉換為 Base64 字串
                    // var imageUrl = URL.createObjectURL(product.productImage); // 將 Blob 轉換為可用於顯示的 URL
              
                    // var base64Data = btoa(String.fromCharCode.apply(null, product.productImage));
                    // console.log(base64Data);
                    let html = `
                <div class="carousel-item active">
                <img src="data:image/png;base64,${product}" class="d-block" style="heigh:200px weight:200px" alt="">
                </div>       
                `;
                    body.append(html);
                // }
            })

        },
        error: function (error) {
            console.log(error)
        }

    });
}