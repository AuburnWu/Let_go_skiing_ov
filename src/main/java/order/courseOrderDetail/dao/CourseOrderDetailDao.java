package order.courseOrderDetail.dao;

import java.util.List;

import core.CoreDao;
import order.courseOrderDetail.model.CourseOrderDetail;





public interface CourseOrderDetailDao extends CoreDao{
	int insert(CourseOrderDetail CourseOrderDetail);

	int deleteByID(Integer CourseOrderDetailID);

	int updateByID(CourseOrderDetail CourseOrderDetail);

	CourseOrderDetail selectByID(Integer CourseOrderDetailID);

	List<CourseOrderDetail> selectAll() throws ClassNotFoundException;

}
