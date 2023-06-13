(() => {
    fetch('productAdList')
        .then(response => response.json())
        .then(padlist => {
            // 將資料放入表格
            let tableBody = $('#padList');
            padlist.forEach(ad => {
                let html = `
            <tr>
            <td>${ad.productAD}</td>
            <td>${ad.productID}</td>
            <td>${ad.productName}</td>
            <td>${ad.productClass}</td>
            <td>${ad.productPrice}</td>
            <td><button class="btn btn-outline-danger btn-delete" data-id="${ad.productAD}"">刪除</button></td></tr>`;
                tableBody.append(html);
            });
        })
        .catch(error => {
            console.log('獲取資料失敗:', error);
        });


    // 監聽刪除按鈕的點擊事件
    $(document).on("click", ".btn-delete", function deleteAD() {
        let productAD = $(this).data('id');

        $.ajax({
            url: "productAdDelete",
            type: "POST",
            data: { pAdId: productAD },
            dataType: "json",
            success: function (data) {
                console.log("a");
                location.reload();
            },
            error: function (error) {
                console.log(error);
            },
        });
    });

    //新增事件

    //iframe?
    
})();