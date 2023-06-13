(() => {
    fetch('qalistServlet')
    .then(response => response.json())
    .then(qalist => {
        // 將資料放入表格
        let item = $('#qalist');
        qalist.forEach(qa => {
            let html = `
            <div class="pa" id="qaItem">
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button
                        class="accordion-button"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#panelsStayOpen-${qa.qaID}"
                        aria-expanded="true"
                        aria-controls="panelsStayOpen-${qa.qaID}">
                            <div class="que" style="margin: 0 5px 0 0">${qa.questionTitle}</div>
                            <span class="badge rounded-pill bg-info text-dark">${qa.questionType}</span>
                    </button>
                </h2>
  
                <div id="panelsStayOpen-${qa.qaID}"
                class="accordion-collapse collapse show"
                >
                    <div class="accordion-body">
                        ${qa.answerContent}
                    </div>
                </div>
            </div>
            </div>
`;
            item.append(html);
        });
    })
    .catch(error => {
        console.log('獲取資料失敗:', error);
    });

})()