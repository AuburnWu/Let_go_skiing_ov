package qa.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import qa.service.QaService;
import qa.service.Impl.QaServiceImpl;

@WebServlet("/qa/deleteQa")
public class DeleteServlet extends HttpServlet {
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
		
		
		System.out.println(req.getParameter("qa"));
		
		boolean result = service.remove(Integer.parseInt(req.getParameter("qaId")));
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", "success");
		if(result) {resp.getWriter().println(jsonObject.toString());}


	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
