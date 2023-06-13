package course.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import course.dao.impl.CourseDaoImpl;


@WebServlet("/course_DL")
public class DeleteCourseByIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
			Integer CourseID = Integer.parseInt(request.getParameter("courseID"));
			CourseDaoImpl dao = new CourseDaoImpl();

			int result = dao.deleteByCourseId(CourseID);
			
			return ;					
	}
}

