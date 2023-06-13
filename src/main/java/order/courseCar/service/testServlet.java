package order.courseCar.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import order.productOrder.service.ProductOrderService;
import order.productOrder.service.ProductOrderServiceImpl;
@WebServlet("/web/test")
public class testServlet extends HttpServlet{
	private ProductOrderService pos;
	@Override
	public void init() throws ServletException {
		super.init();
		pos=new ProductOrderServiceImpl();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
//		 JSONObject jobj=new JSONObject(req.getParameter("userID"));
		 Integer a=Integer.parseInt(req.getParameter("userID"));
		 JSONObject jsob=new JSONObject();
		 jsob.put("userID",a);
		 PrintWriter writer=res.getWriter();
		 writer.print(jsob);

			
	}
//	private String test(HttpServletRequest req,HttpServletResponse res) {
//		 Integer userID=Integer.parseInt(req.getParameter("userID"));
//		 try {
//			PrintWriter writer=res.getWriter();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
