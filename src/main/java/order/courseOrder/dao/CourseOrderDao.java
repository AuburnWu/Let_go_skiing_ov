package order.courseOrder.dao;

import java.sql.Timestamp;
import java.util.List;

import core.CoreDao;
import member.vo.Member;
import order.courseOrder.model.CourseOrder;
import order.courseOrderDetail.model.CourseOrderDetail;




public interface CourseOrderDao extends CoreDao{
	int insert(CourseOrder CourseOrder);

	int deleteByID(Integer CourseOrderID);

	int updateByID(CourseOrder CourseOrder);
	
	int getTotalPriceByID(Integer courseOrderID);
	
	List<Integer>getAllOrderIdByUser(Integer userID);
	
	List<Integer> getIdByUserName(String userName);

	CourseOrder selectByID(Integer CourseOrderID);

	List<CourseOrder> selectAll() throws ClassNotFoundException;
	
	List<CourseOrder> selectAllByUserID(Integer userID) throws ClassNotFoundException;
	
	List<CourseOrderDetail> selectDetailByOrderID(Integer courseOrderID);
	
	byte[] getCoursePhoto(Integer courseID);
	String getCourseName(Integer courseID);
	Timestamp getOrderBuyDate(Integer courseOrderID);
	int deleteDetailByID(Integer CourseOrderDetailID);
	int getOrderIdByDetail(Integer courseOrderDetailID);
	List<Integer> getOrderAllDetailPrice(Integer courseOrderID);
	void updateOrderPrice(Integer courseOrderID,Integer price);
	List<String> getOrderDetailStatus(Integer courseOrderID);
	void updateDetailStatus(Integer courseOrderDetailID);
	Member selectMember(Integer userID);
	List<Member> selectMemberByName(String userName);
	void updatePerson(Integer courseID);

	

}
