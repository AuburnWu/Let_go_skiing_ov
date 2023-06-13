package product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import order.productCar.dao.ProductCarPK;
import order.productCar.model.ProductCar;
import order.productCar.service.ProductCarService;
import order.productCar.service.ProductCarServiceImpl;
@WebServlet("/addCar")
public class ProductAddCar extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 private ProductCarService productCarService = new ProductCarServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	    resp.setContentType("application/json;charset=utf-8");
	    int userID=Integer.parseInt(req.getParameter("userID"));
	    int productID=Integer.parseInt(req.getParameter("productID"));
	    int quantity=Integer.parseInt(req.getParameter("quantity"));
	    ProductCarPK PK=new ProductCarPK(userID,productID);
	    ProductCar car=new ProductCar(PK,quantity);
	    boolean status=productCarService.addCar(car);

	    

	    JSONObject jsonObject=new JSONObject();
	    jsonObject.put("status", status);
	    System.out.println(jsonObject);
       resp.getWriter().print(jsonObject);

   }
}
