package qa.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.GsonUtils;
import qa.service.QaService;
import qa.service.Impl.QaServiceImpl;

@WebServlet("/qa/qalistServlet")
public class QalistServlet extends HttpServlet {
	private static final long serialVersionUID = 7113413569589284935L;
	private QaService serv = new QaServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		resp.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");

		resp.getWriter().print(GsonUtils.toJson(serv.qalist()));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
