package member.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import member.service.CoachService;
import member.service.impl.CoachServiceImpl;
import member.vo.Coach;

@WebServlet("/member/applyCoachSave") // http://localhost:8080/ski/member/applyCoachSave
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ApplyCoachSaveServlet extends HttpServlet {
	private static final long serialVersionUID = -5916058450028841844L;
	private CoachService service;
	private Gson gson;

	@Override
	public void init() throws ServletException {
		service = new CoachServiceImpl();
		gson = new Gson();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

		String userID = req.getParameter("userID");
		String skill = req.getParameter("skill");
		String background = req.getParameter("background");
		String introduce = req.getParameter("introduce");
		String applyStatus = req.getParameter("applyStatus");

		Coach coach = new Coach();
		coach.setUserID(Integer.valueOf(userID));
		coach.setSkill(skill);
		coach.setBackground(background);
		coach.setIntroduce(introduce);
		coach.setApplyStatus(applyStatus);

		Part license = req.getPart("license");
		
		if(license == null) {
			coach.setMessage("請上傳相關證照");
			coach.setSuccessful(false);
			String coachJson = gson.toJson(coach);
			resp.getWriter().write(coachJson);
			resp.getWriter().flush();
			return;
		}
		
		if(license != null) {
			// 把圖片都讀到byte陣列中
			InputStream in = license.getInputStream();
			byte[] buf = new byte[in.available()];
			in.read(buf);
			coach.setLicense(buf);
		}
	
		coach = service.apply(coach);

		String coachJson = gson.toJson(coach);
		resp.getWriter().write(coachJson);
		resp.getWriter().flush();

	}

}