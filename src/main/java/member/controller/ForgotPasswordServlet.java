package member.controller;

import java.io.BufferedReader;
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

@WebServlet("/member/forgotPassword") // http://localhost:8080/ski/member/forgotPassword
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = -954708970546514632L;
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

		// 從 HttpServletRequest 中取得 JSON 字串
		BufferedReader br = req.getReader();
		Member member = gson.fromJson(br, Member.class);

		member = service.forgotPassword(member);

		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊，請重新輸入");
			member.setSuccessful(false);
			String memberJson = gson.toJson(member);
			resp.getWriter().write(memberJson);
			resp.getWriter().flush();
			return;
		} 

		String userID = String.valueOf(member.getUserID());
		String encodedUserID = Base64Util.encode(userID);
	
		String resetPasswordUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
		+ req.getContextPath() + "/member/passwordChange?userID=" + encodedUserID + "&from=mail";

		String to = member.getEmail(); 
		String subject = "【忘記密碼通知】Let's go skiing";
		String ch_name = member.getUserName();
	
		String messageText = "Hello " + ch_name + "，您好：\r\n\r\n"
		            + "您申請了忘記密碼通知，如果您沒有申請，請忽略這封信件。\r\n"
		            + "如果您要重設密碼，請點選下方連結：\r\n\r\n"
		            + resetPasswordUrl
		            + "\r\n\r\n"
		            + "若有任何問題歡迎隨時回覆此郵件與我們聯繫，謝謝。\r\n"
		            + "Let's go skiing 會員中心 "; 

		service.sendMail(to, subject, messageText);
		
		member.setMessage("郵件已成功發送！");
		member.setSuccessful(true);
		String memberJson = gson.toJson(member);
		resp.getWriter().write(memberJson);
		resp.getWriter().flush();
		
		
	}
}