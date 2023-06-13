package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.Base64Util;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;
import member.vo.Member;

@WebServlet("/member/setPassword") // http://localhost:8080/ski/member/setPassword
public class SetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = -2642181847346797888L;
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
		String password = req.getParameter("password");
		String decodedUserID = Base64Util.decode(userID);
		
		Member member = new Member();
		member.setUserID(Integer.valueOf(decodedUserID));
		member.setPassword(password);
		
		member = service.passwordChange(member);
		
		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
			String memberJson = gson.toJson(member);
			resp.getWriter().write(memberJson);
			resp.getWriter().flush();
			return;
		}
		System.out.println(member);
		String memberJson = gson.toJson(member);
		resp.getWriter().write(memberJson);
		resp.getWriter().flush();
	
	}
}