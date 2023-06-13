package ad.course.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.course.service.CourseAdService;
import ad.course.service.Impl.CourseAdServiceImpl;
import core.util.GsonUtils;

@WebServlet("/ad/courseAdList")

public class AdlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseAdService service = new CourseAdServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 設置跨域
		resp.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST"); // 允許的 HTTP 方法
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		resp.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
		// 設置返回格式
		resp.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		resp.getWriter().print(GsonUtils.toJson(service.courseAdlist()));

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
