package qa.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import qa.service.QaService;
import qa.service.Impl.QaServiceImpl;

@WebServlet("/qa/insertQa")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QaService service = new QaServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 設置跨域
		resp.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		resp.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
		// 設置返回格式
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");

		// 獲取前端傳遞的資料
		Integer questionType = Integer.parseInt(req.getParameter("questionType"));
		String questionTitle = req.getParameter("questionTitle");
		String answerContent = req.getParameter("answerContent");
		Timestamp questionDate = new Timestamp(System.currentTimeMillis());

		// 呼叫 service 的 insert() 方法建立新的 qa 物件
		int res = service.insert(questionType, questionTitle, answerContent, questionDate);
		 JsonObject jsonObject = new JsonObject();
		 jsonObject.addProperty("status", "success");
		 if(res<0) resp.getWriter().println(jsonObject.toString());


	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
