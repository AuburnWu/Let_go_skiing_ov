package course;

import course.dao.CourseDao;
import course.dao.impl.CourseDaoImpl;
import course.entity.Course;


public class AppTest {
	public static void main(String[] args) {
		Course course = new Course();
		CourseDaoImpl dao = new CourseDaoImpl();
		
		course.setCourseName("te123");
		course.setCourseIntroduce("p@ssw0rd");
		
		dao.insertCourse(course);
	}
}
