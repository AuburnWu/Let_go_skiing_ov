package order.courseCar.dao;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import course.entity.Course;
import member.vo.Member;
import order.courseCar.model.CourseCar;
import order.courseOrder.model.CourseOrder;
import order.courseOrderDetail.model.CourseOrderDetail;





public class CourseCarDaoImpl implements CourseCarDao{



	@Override
	public CourseCarPK insert(CourseCar CourseCar) {
		getSession().persist(CourseCar);
		return CourseCar.getId();
		
	}
	
	@Override
	public Integer insertCourseOrder(CourseOrder courseOrder) {	
		getSession().persist(courseOrder);
		return courseOrder.getCourseOrderID();
	}
	
	@Override
	public Integer insertCourseOrderDetail(CourseOrderDetail courseOrderDetail) {
		getSession().persist(courseOrderDetail);
		return courseOrderDetail.getCourseOrderDetailID();
	}

	@Override
	public CourseCarPK deleteByID(CourseCarPK id) {
		Session session=getSession();
		CourseCar courseCar=session.get(CourseCar.class,id);
		getSession().remove(courseCar);
		return id;
	}
	
	@Override
	public int deleteAllByUserID(Integer userID) {
		Session session=getSession();
		String hql="DELETE FROM CourseCar WHERE userID = :userID";
		Query query=session.createQuery(hql);
		query.setInteger("userID", userID);
		return query.executeUpdate();
		
	}
	@Override
	public Course getCourseByID(Integer courseID) {
		return getSession().get(Course.class, courseID);
	}

	@Override
	public CourseCarPK updateByID(CourseCar CourseCar) {

		
		getSession().update(CourseCar);
		return CourseCar.getId();
	}

	@Override
	public CourseCar selectByID(CourseCarPK id) {

		CourseCar courseCar=getSession().get(CourseCar.class, id);
		return courseCar;
	}

	@Override
	public List<CourseCar> selectAll() throws ClassNotFoundException {

		return getSession().createQuery("FROM CourseCar",CourseCar.class).list();
		
	}

	@Override
	public List<CourseCar> selectAllByUserID(Integer userID) throws ClassNotFoundException {
		return getSession().createQuery("FROM CourseCar WHERE userID = "+userID,CourseCar.class).list();
		
	}

	@Override
	public String getUserPhone(Integer userID) {
		return getSession().get(Member.class,userID).getPhone();
	}

	@Override
	public String getUserPersonID(Integer userID) {
		return getSession().get(Member.class,userID).getPersonID();
	}









}
