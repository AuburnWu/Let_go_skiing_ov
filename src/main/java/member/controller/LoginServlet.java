package member.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import member.service.MemberService;
import member.service.impl.MemberServiceImpl;
import member.vo.Member;

@WebServlet("/member/login") // http://localhost:8080/ski/member/login
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -7529801032154818432L;
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
		
		member = service.login(member);

		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
			String memberJson = gson.toJson(member);
			resp.getWriter().write(memberJson);
			resp.getWriter().flush();
			return;
		}

		if (member.isSuccessful()) {
			// 如果原先已有session將沿用之，如果沒有，則不會創建一個新的session (原先已有session，將會執行此段程式碼)
			if (req.getSession(false) != null) {
				req.changeSessionId();
			}
			final HttpSession session = req.getSession();
			session.setAttribute("userID", member.getUserID());
			session.setAttribute("userName", member.getUserName());
		}
		String memberJson = gson.toJson(member);
		resp.getWriter().write(memberJson);
		resp.getWriter().flush();

	}
}