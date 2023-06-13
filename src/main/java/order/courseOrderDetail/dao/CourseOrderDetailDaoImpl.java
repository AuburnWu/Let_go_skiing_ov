package order.courseOrderDetail.dao;

import java.util.List;

import org.hibernate.Session;

import order.courseOrderDetail.model.CourseOrderDetail;




public class CourseOrderDetailDaoImpl implements CourseOrderDetailDao{


	

	@Override
	public int insert(CourseOrderDetail CourseOrderDetail) {
		getSession().persist(CourseOrderDetail);
		return CourseOrderDetail.getCourseOrderDetailID();
	}
	@Override
	public int deleteByID(Integer CourseOrderDetailID) {
		Session session=getSession();
		CourseOrderDetail courseOrderDetail=session.get(CourseOrderDetail.class, CourseOrderDetailID);
		session.remove(courseOrderDetail);
		return CourseOrderDetailID;
	}

	@Override
	public int updateByID(CourseOrderDetail CourseOrderDetail) {
		getSession().update(CourseOrderDetail);
		return CourseOrderDetail.getCourseOrderDetailID();
	}

	@Override
	public CourseOrderDetail selectByID(Integer CourseOrderDetailID) {
		CourseOrderDetail courseOrderDetail=getSession().get(CourseOrderDetail.class, CourseOrderDetailID);
		return courseOrderDetail;
	}

	@Override
	public List<CourseOrderDetail> selectAll() throws ClassNotFoundException {
		return getSession().createQuery("FROM CourseOrderDetail",CourseOrderDetail.class).list();
	}

}
