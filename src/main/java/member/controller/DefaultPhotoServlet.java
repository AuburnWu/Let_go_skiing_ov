package member.controller;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.service.MemberService;
import member.service.impl.MemberServiceImpl;
import member.vo.Member;

@WebServlet("/member/defaultPhoto") // http://localhost:8080/ski/member/defaultPhoto
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class DefaultPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = -1291314635170915203L;
	private MemberService service;
	private Gson gson;

	@Override
	public void init() throws ServletException {
		service = new MemberServiceImpl();
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

		Member member = new Member();
		member.setUserID(Integer.valueOf(userID));
		
		String realPath = getServletContext().getRealPath("\\img\\default_photo.png");

		FileInputStream fis = new FileInputStream(realPath);
		byte[] buf = new byte[fis.available()];
		fis.read(buf);
		member.setPhoto(buf);
		fis.close();

		member = service.infoChange(member);

		String memberJson = gson.toJson(member);
		resp.getWriter().write(memberJson);
		resp.getWriter().flush();
		
	}

}