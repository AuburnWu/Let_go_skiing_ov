package article.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.hibernate.Session;
//import org.hibernate.SessionFactory;

import com.google.gson.Gson;

//import core.HibernateUtil;
import article.dao.ReportDao;
import article.dao.impl.ReportDaoImpl;
import article.vo.Report;



// http://localhost:8080/ski/BackendReport
@WebServlet("/BackendReport")
public class BackendReportServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String action=req.getParameter("action");
		System.out.println(action);
		String fowardPath="";
		switch(action) {
			case "showAll":
				fowardPath=showAll(req,res);
				break;
			case "updateContent":
				fowardPath=updateContent(req,res);
				break;
			default:{
				System.out.println("收到未知請求");
				break;
			}	
		}
	}
	
	// 顯示全部
	private String showAll(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		ReportDao dao = new ReportDaoImpl();
		List<Report> list = new ArrayList<Report>();
		try {
			list = dao.selectAll();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		res.getWriter().write(jsonStr);
//		System.out.println("success");
		return jsonStr;
	}
	
	// 新增(修改)處理回覆
	private String updateContent(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		ReportDao dao = new ReportDaoImpl();
		try {
			Report report = new Report(Integer.parseInt(req.getParameter("reportID")), Integer.parseInt(req.getParameter("articleID")), Integer.parseInt(req.getParameter("userID")),req.getParameter("reportContent"),req.getParameter("reportStatus"),req.getParameter("reportResponse"));
			dao.updateByReportID(report);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(dao);
		res.getWriter().write(jsonStr);
//		System.out.println("success");
		return jsonStr;
	}

}
