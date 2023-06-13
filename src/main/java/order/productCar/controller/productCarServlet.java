package order.productCar.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import order.productCar.service.ProductCarService;
import order.productCar.service.ProductCarServiceImpl;

@WebServlet("/order/ProductCarServlet")
public class productCarServlet extends HttpServlet{
	private ProductCarService productCarService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		productCarService=new ProductCarServiceImpl();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doPost(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action=req.getParameter("action");
		System.out.println(action);
		String fowardPath="";
		switch(action) {
			case "listUserCar":
				fowardPath=listUserCar(req,res);
				break;
			case "getTotal":
				fowardPath=getTotal(req,res);
				break;
			case "removeCar":
				fowardPath=removeCar(req,res);
				break;
			case "checkout":
				fowardPath=checkout(req,res);
				break;
			case "stockCheck":
				fowardPath=stockCheck(req,res);
				break;
		}
	}
	private String stockCheck(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer userID=Integer.parseInt(req.getParameter("userID"));
		Integer productID=Integer.parseInt(req.getParameter("productID"));
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("stockCheck",productCarService.stockCheck(userID, productID));
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.print(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	private String checkout(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer userID=Integer.parseInt(req.getParameter("userID"));
		String address=(req.getParameter("address"));
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("sendApply",productCarService.sendAply(userID,address));
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.print(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	private String removeCar(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer userID=Integer.parseInt(req.getParameter("userID"));
		Integer productID=Integer.parseInt(req.getParameter("productID"));
		JSONObject jsonObject=productCarService.removeCar(userID, productID);
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.print(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	private String getTotal(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer userID=Integer.parseInt(req.getParameter("userID"));
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("totalPrice",productCarService.subTotal(userID));
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.print(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	private String listUserCar(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer userID=Integer.parseInt(req.getParameter("userID"));
		
		JSONArray jsonArray=productCarService.listProductCar(userID);
		PrintWriter writer;
		System.out.println(jsonArray);
		try {
			writer = res.getWriter();
			writer.print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
