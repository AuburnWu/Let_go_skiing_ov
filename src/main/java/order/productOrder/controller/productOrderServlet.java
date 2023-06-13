package order.productOrder.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import order.productOrder.service.ProductOrderService;
import order.productOrder.service.ProductOrderServiceImpl;
@WebServlet("/order/ProductOrderServlet")
public class productOrderServlet extends HttpServlet{
	private ProductOrderService productOrderService;
	@Override
	public void init() throws ServletException {
		productOrderService=new ProductOrderServiceImpl();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action=req.getParameter("action");
		System.out.println(action);
		String fowardPath="";
		switch(action){
			case "setOrderHeader":
				fowardPath=setOrderHeader(req,res);
				break;
			case "listOrder":
				fowardPath=listOrder(req,res);
				break;
			case "checkUserID":
				fowardPath=checkUserID(req,res);
				break;
			case "checkUserName":
				fowardPath=checkUserName(req,res);
				break;
		}
	}

	private String checkUserName(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		String searchName=String.valueOf(req.getParameter("searchName"));
		JSONArray jsonArray=productOrderService.checkUserName(searchName);
		System.out.println(jsonArray);

		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	private String checkUserID(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer searchID=Integer.valueOf(req.getParameter("searchID"));
		JSONArray jsonArray=productOrderService.checkIdToOrder(searchID);

		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	private String listOrder(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer productOrderID=Integer.parseInt(req.getParameter("productOrderID"));
		JSONArray jsonArray=productOrderService.getProductINFO(productOrderID);
		System.out.println(jsonArray);
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	private String setOrderHeader(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer userID=Integer.parseInt(req.getParameter("userID"));
		JSONArray jsonArray=productOrderService.getOrderListHeader(userID);
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
