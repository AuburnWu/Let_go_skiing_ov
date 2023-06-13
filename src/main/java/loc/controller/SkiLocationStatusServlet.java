package loc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loc.model.SkiLocation;
import loc.service.SkiLocationService;

@WebServlet("/loc/backend_statusmap")
public class SkiLocationStatusServlet extends HttpServlet {
	private static final long serialVersionUID = -4074728963439065891L;
	private SkiLocationService service;

	public void init() {
		service = new SkiLocationService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("aaaaaaaa");

		String pointID = request.getParameter("pointID");
		String pointStatus = request.getParameter("pointStatus");

		SkiLocation skiLocation = new SkiLocation();
		skiLocation.setPointID(Integer.parseInt(pointID));
		skiLocation.setPointStatus(pointStatus);

		 boolean updateSuccess = service.update(skiLocation);

		    if (updateSuccess) {
		        // 根据状态判断并返回相应的成功消息
		        if (pointStatus.equals("上架中")) {
		            out.println("已经上架");
		        } else if (pointStatus.equals("下架中")) {
		            out.println("已经下架");
		        } else {
		            out.println("状态更新成功：" + skiLocation);
		        }
		    } else {
		        out.println("状态更新失败");
		    }
		}

	}
