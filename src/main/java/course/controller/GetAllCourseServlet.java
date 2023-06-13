package course.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import course.dao.impl.CourseDaoImpl;
import course.entity.Course;


@WebServlet("/course_GA")
public class GetAllCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
			
			CourseDaoImpl dao = new CourseDaoImpl();

			List<Course> list = dao.getAllCourses();				
			response.setContentType("application/json");
			Gson gson = new Gson();
			String jsonStr = gson.toJson(list);
			
			try {
				response.getWriter().write(jsonStr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
	}
}

