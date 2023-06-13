package article.service;

import java.util.List;


import article.vo.Report;

public interface BackendReportService {

	//顯示全部文章分類
	List<Report> findAll();
	
	//新增(修改)處理回覆
	void updateReport(Report reportID);

}