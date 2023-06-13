package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.service.CoachService;
import member.service.impl.CoachServiceImpl;
import member.vo.Coach;

@WebServlet("/member/getAllCoach") // http://localhost:8080/ski/member/getAllCoach
public class GetAllCoachServlet extends HttpServlet {
	private static final long serialVersionUID = -6889666671121857909L;
	private CoachService service;
	private Gson gson;

	@Override
	public void init() throws ServletException {
		service = new CoachServiceImpl();
		gson = new Gson();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		resp.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求

		// 避免快取
		resp.setHeader("Cache-Control", "no-store");
		resp.setHeader("Pragma", "no-cache");
		resp.setDateHeader("Expires", 0);

		// 設置中文編碼
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");

		List<Coach> list = service.findAll();
		
		String listJson = gson.toJson(list);
		resp.getWriter().write(listJson);
		resp.getWriter().flush();
	}
	
}