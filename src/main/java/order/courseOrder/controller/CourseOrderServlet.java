package order.courseOrder.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import order.courseOrder.service.CourseOrderService;
import order.courseOrder.service.CourseOrderServiceImpl;

@WebServlet("/order/CourseOrderServlet")
public class CourseOrderServlet extends HttpServlet{
	private CourseOrderService courseOrderService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		courseOrderService=new CourseOrderServiceImpl();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action=req.getParameter("action");
		System.out.println(action);
		String fowardPath="";
		switch(action) {
			case "setOrderHeader":
				fowardPath=setOrderHeader(req,res);
				break;
			case "listOrder":
				fowardPath=listOrder(req,res);
				break;
			case "removeOrderDetail":
				fowardPath=removeOrderDetail(req,res);
				break;
			case "getTotal":
				fowardPath=getTotal(req,res);
				break;
			case "orderStatus":
				fowardPath=orderStatus(req,res);
				break;
			case "checkout":
				fowardPath=checkout(req,res);
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
		JSONArray jsonArray=courseOrderService.checkUserName(searchName);
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
		JSONArray jsonArray=courseOrderService.checkIdToOrder(searchID);

		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	private String checkout(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer courseOrderID=Integer.valueOf(req.getParameter("courseOrderID"));
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("status",courseOrderService.updateDetailStatus(courseOrderID));
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.print(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	private String orderStatus(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer courseOrderID=Integer.valueOf(req.getParameter("courseOrderID"));
		int status=courseOrderService.statusCheck(courseOrderID);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("status",status);
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
		Integer courseOrderID=Integer.valueOf(req.getParameter("courseOrderID"));
		int total=courseOrderService.getOrderTotal(courseOrderID);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("totalPrice",total);
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.print(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private String setOrderHeader(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer userID=Integer.parseInt(req.getParameter("userID"));
		JSONArray jsonArray=courseOrderService.getOrderListHeader(userID);
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
		Integer courseOrderID=Integer.parseInt(req.getParameter("courseOrderID"));
		JSONArray jsonArray=courseOrderService.getCourseINFO(courseOrderID);
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
	private String removeOrderDetail(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer courseOrderDetailID=Integer.parseInt(req.getParameter("courseOrderDetailID"));
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("courseOrderDetailID",courseOrderService.removeOrderDetail(courseOrderDetailID));
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.print(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	

}
