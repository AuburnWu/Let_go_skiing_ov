package course.controller;


import java.io.IOException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import course.dao.impl.CourseDaoImpl;
import course.entity.Course;


@WebServlet("/course_GBI")
@MultipartConfig
public class GetCourseByCrouseIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
			Integer courseID =  Integer.valueOf(request.getParameter("courseID"));
			CourseDaoImpl dao = new CourseDaoImpl();
			Course course = dao.getCourseByCourseId(courseID);


				Gson gson = new Gson();
				String jsonStr = gson.toJson(course);
				response.setContentType("application/json");
				try {
					response.getWriter().write(jsonStr);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return;			
	
	}
}

