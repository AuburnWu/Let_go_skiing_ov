var userID=sessionStorage.getItem("userID");
function increment() {
	document.getElementById('quantity').stepUp();
}

function decrement() {
	document.getElementById('quantity').stepDown();
}

// 取得URL中的productID參數
const urlParams = new URLSearchParams(window.location.search);
const productID = urlParams.get('productID');



document.getElementById('productID').value = productID;

// Your previous code

fetch(`/ski/productSelectByID?productID=${productID}`, {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    }
})
    .then(response => response.json())
    .then(data => {
        // 確保 data 為陣列且長度大於 0
        if (Array.isArray(data) && data.length) {
            const product = data[0];
            // 將資訊填入表單
            document.getElementById('productName').textContent = product.productName;
            document.getElementById('productDetail').textContent = product.productDetail;
            document.getElementById('productPrice').textContent = product.productPrice.textContent = `價格：${product.productPrice}元`;
            document.getElementById('quantity').setAttribute('max',product.productQuantity);

            // 將圖片填入 img 元素
            const base64Image = btoa(new Uint8Array(product.productImage).reduce((data, byte) => data + String.fromCharCode(byte), ''));
            const imageSrc = `data:image/png;base64,${base64Image}`;
            document.querySelector('.img-thumbnail').src = imageSrc;
        }
    })
    .catch((error) => console.error('錯誤:', error));

// Your following code

$("#commit").on("click",function(){
	let id=$(this).attr("productID");
    let quantity=$(".form-control").val();
	console.log(id);
		if(userID!=null){
			var check=confirm("確定新增商品至購物車?")
			if(check){
				var url = "/ski/addCar?userID="+userID+"&productID=" + productID + "&quantity=" + quantity;
			
				fetch(url)
				.then(response => response.json())
					.then(data => {
						let status=data.status;
						if(status){
							alert("購物車新增成功")
						}else{
							alert("購物車已有同一商品")
						}
						
					})
					.catch(error => {
						console.log(error);
						alert("購物車新增失敗")
					});
			}else{
		
			}
		}else{
			alert("請先登入")
		}

	
})

	
