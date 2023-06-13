package course.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import course.dao.impl.CourseDaoImpl;
import course.entity.Course;


@WebServlet("/course_IS")
@MultipartConfig
public class InsertCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {		
			CourseDaoImpl dao = new CourseDaoImpl();			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
//			Integer courseID =  Integer.valueOf(request.getParameter("courseID"));
			
			System.out.println(request.getParameter("courseSkill"));
			Integer courseSkill =  Integer.valueOf(request.getParameter("courseSkill"));
			Integer courseLevel =  Integer.valueOf(request.getParameter("courseLevel"));
	        String courseName = request.getParameter("courseName");
	        Integer coachID =  Integer.valueOf(request.getParameter("courseCoach"));
	        Integer courseLocation =  Integer.valueOf(request.getParameter("courseLocation"));
	        
	        Date courseDate = null;
	        Date startDate = null;
	        Date endDate = null;
	        
			try {
				courseDate = dateFormat.parse(request.getParameter("courseDate"));
				startDate = dateFormat.parse(request.getParameter("startDate"));
		        endDate = dateFormat.parse(request.getParameter("endDate"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        
	        Integer coursePrice = Integer.valueOf(request.getParameter("coursePrice"));
	        Integer courseMax =  Integer.valueOf(request.getParameter("courseMax"));
	        Integer courseMin =  Integer.valueOf(request.getParameter("courseMin"));
	        Integer courseStatus =  Integer.valueOf(request.getParameter("courseStatus"));
	        
	        Part filePart = request.getPart("coursePhoto");
	        InputStream fileContent = filePart.getInputStream();
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        byte[] buffer = new byte[4096];
	        int bytesRead;
	        while ((bytesRead = fileContent.read(buffer)) != -1) {
	            outputStream.write(buffer, 0, bytesRead);
	        }
	        byte[] coursePhoto = outputStream.toByteArray();
	   
	        String courseIntroduce = request.getParameter("courseIntroduce");
	        
	        Course course = new Course();
	        
//			course.setCourseID(courseID);
			course.setSkill(courseSkill);
			course.setLevel(courseLevel);
			course.setCoachID(coachID);
			course.setCourseName(courseName);
			course.setPointID(courseLocation);
			course.setCourseDate(courseDate);
			course.setStartDate(startDate);
			course.setEndDate(endDate);
			course.setCoursePrice(coursePrice);
			course.setCourseMax(courseMax);
			course.setCourseMin(courseMin);
			course.setCourseStatus(courseStatus);
			course.setCoursePhoto(coursePhoto);
			course.setCourseIntroduce(courseIntroduce);
			
			dao.insertCourse(course);				
	}
	
}

