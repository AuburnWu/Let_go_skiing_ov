package order.courseCar.dao;

import java.util.List;

import core.CoreDao;
import course.entity.Course;
import order.courseCar.model.CourseCar;
import order.courseOrder.model.CourseOrder;
import order.courseOrderDetail.model.CourseOrderDetail;





public interface CourseCarDao extends CoreDao{
	CourseCarPK insert(CourseCar CourseCar);
	
	Integer insertCourseOrder(CourseOrder courseOrder);
	
	Integer insertCourseOrderDetail(CourseOrderDetail courseOrderDetail);

	CourseCarPK deleteByID(CourseCarPK id);
	
	int deleteAllByUserID(Integer userID);

	CourseCarPK updateByID(CourseCar CourseCar);
	
	Course getCourseByID(Integer courseID);

	CourseCar selectByID(CourseCarPK id);

	List<CourseCar> selectAll() throws ClassNotFoundException;
	
	List<CourseCar> selectAllByUserID(Integer userID) throws ClassNotFoundException;
	
	String getUserPhone(Integer userID);
	
	String getUserPersonID(Integer userID);

}
