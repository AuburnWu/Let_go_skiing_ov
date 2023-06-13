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

import member.service.MemberService;
import member.service.impl.MemberServiceImpl;
import member.vo.Member;

@WebServlet("/member/infoChangeSave") // http://localhost:8080/ski/member/infoChangeSave
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class InfoChangeSaveServlet extends HttpServlet {
	private static final long serialVersionUID = -5916058450028841844L;
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
		String nickName = req.getParameter("nickName");
		String personID = req.getParameter("personID");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String level = req.getParameter("level");

		Member member = new Member();
		member.setUserID(Integer.valueOf(userID));
		member.setNickName(nickName);
		member.setPersonID(personID);
		member.setPhone(phone);
		member.setAddress(address);
		member.setLevel(level);

		Part photo = req.getPart("photo");
		
		if(photo != null) {
			// 把圖片都讀到byte陣列中
			InputStream in = photo.getInputStream();
			byte[] buf = new byte[in.available()];
			in.read(buf);
			member.setPhoto(buf);
		}
	
		member = service.infoChange(member);

		String memberJson = gson.toJson(member);
		resp.getWriter().write(memberJson);
		resp.getWriter().flush();

	}

}