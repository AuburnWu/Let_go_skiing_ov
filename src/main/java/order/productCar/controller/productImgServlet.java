package order.productCar.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.productCar.service.ProductCarService;
import order.productCar.service.ProductCarServiceImpl;

@WebServlet("/productImgServlet")
public class productImgServlet extends HttpServlet {
	private ProductCarService productCarService;

	@Override
	public void init() throws ServletException {
		super.init();
		productCarService = new ProductCarServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/png");
		String productID = req.getParameter("productID");
		Integer id = Integer.valueOf(productID);
		byte[] imgFromDB = productCarService.productImg(id);
		ServletOutputStream stream = res.getOutputStream();
		stream.write(imgFromDB);
		stream.flush();
		stream.close();
	}

}
