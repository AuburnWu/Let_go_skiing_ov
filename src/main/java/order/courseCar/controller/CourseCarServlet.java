package order.courseCar.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import order.courseCar.service.CourseCarService;
import order.courseCar.service.CourseCarServiceImpl;

@WebServlet("/order/CourseCarServlet")
public class CourseCarServlet extends HttpServlet{
	private CourseCarService courseCarService;
	
	@Override
	public void init() throws ServletException{
		super.init();
		courseCarService=new CourseCarServiceImpl();
		
	}
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		doPost(req,res);

	}
	
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		String action=req.getParameter("action");
		System.out.println(action);
		String fowardPath="";
		switch(action) {
			case "listUserCar":
				fowardPath=listUserCar(req,res);
				break;
			case "removeCar":
				fowardPath=removeCar(req,res);
				break;
			case "getTotal":
				fowardPath=getTotal(req,res);
				break;
			case "sendApply":
				fowardPath=sendApply(req,res);
				break;
			case "getMemberINFO":
				fowardPath=getMemberINFO(req,res);
				break;
			default:{
				System.out.println("收到未知請求");
				break;
			}
				
			
		}
		


	}
	private String listUserCar(HttpServletRequest req,HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer userID=Integer.parseInt(req.getParameter("userID"));
		
		JSONArray jsonArray=courseCarService.listCourseCar(userID);
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
	private String removeCar(HttpServletRequest req,HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer userID=Integer.parseInt(req.getParameter("userID"));
		Integer courseID=Integer.parseInt(req.getParameter("courseID"));
		JSONObject jsonObject=courseCarService.removeCar(userID, courseID);
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.print(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	private String getTotal(HttpServletRequest req,HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer userID=Integer.parseInt(req.getParameter("userID"));
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("totalPrice",courseCarService.subTotal(userID));
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.print(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	private String sendApply(HttpServletRequest req,HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer userID=Integer.parseInt(req.getParameter("userID"));
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("sendApply",courseCarService.sendAply(userID));
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.print(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	private String getMemberINFO(HttpServletRequest req,HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		Integer userID=Integer.parseInt(req.getParameter("userID"));
		JSONObject jsonObject=courseCarService.userINFO(userID);
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
