package ad.course.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import ad.course.service.CourseAdService;
import ad.course.service.Impl.CourseAdServiceImpl;
import ad.course.vo.CourseAd;

@WebServlet("/ad/courseAdDelete")
@MultipartConfig
public class DeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
//	 public void init() {
	private CourseAdService Service = new CourseAdServiceImpl();

//	 }
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 設置跨域
		resp.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE"); // 允許的 HTTP 方法
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		resp.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
		// 設置返回格式
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");

		Integer cAdid = Integer.valueOf(req.getParameter("cAdId"));
//		System.out.println(cAdid);
//		Service.deleteAd(cAdid);
		
		
		 int result = Service.deleteAd(cAdid);
		 JsonObject jsonObject = new JsonObject();
		 jsonObject.addProperty("status", "success");
		 if(result < 0) {resp.getWriter().println(jsonObject.toString());}
	}
}
