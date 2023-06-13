package ad.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ad.product.service.ProductAdService;
import ad.product.service.Impl.ProductAdServiceImpl;
import antlr.collections.impl.LList;
import product.vo.Product;

@WebServlet("/ad/productAdSearch")
public class SearchAdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductAdService serv = new ProductAdServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 設置跨域
		resp.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		resp.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
		// 設置返回格式
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");

		String input = req.getParameter("input");

			List<Product> result = serv.search(input);

			resp.setContentType("application/json");
			Gson gson = new Gson();
			String json = gson.toJson(result);
			resp.getWriter().print(json);
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
