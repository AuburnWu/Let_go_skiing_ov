package article.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.CoreDao;
import article.dao.ReportDao;
import article.dao.impl.ReportDaoImpl;
import article.vo.Report;

public class BackendReportServiceImpl implements CoreDao, BackendReportService{
	
	ReportDao reportDao;
	public BackendReportServiceImpl() {
		reportDao=new ReportDaoImpl();
	}
	
	//顯示全部文章分類
	@Override
	public List<Report> findAll(){
		try {
			return reportDao.selectAll();
		} catch (Exception e) {
			System.out.println("資訊取得失敗");
			// TODO: handle exception
		}
		return null;
		
	}
	
	//新增(修改)處理回覆
	public void updateReport(Report reportID) {
		try {
			reportDao.updateByReportID(reportID);
		} catch (Exception e) {
			System.out.println("修改失敗");
			// TODO: handle exception
		}
	}
	
}
