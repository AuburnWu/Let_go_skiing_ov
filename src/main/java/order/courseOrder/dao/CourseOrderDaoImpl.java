package order.courseOrder.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import course.entity.Course;
import member.vo.Member;
import order.courseOrder.model.CourseOrder;
import order.courseOrderDetail.model.CourseOrderDetail;




public class CourseOrderDaoImpl implements CourseOrderDao{




	@Override
	public int insert(CourseOrder CourseOrder) {
		getSession().persist(CourseOrder);
		return CourseOrder.getCourseOrderID();
	}
	@Override
	public int deleteByID(Integer CourseOrderID) {
		Session session=getSession();
		CourseOrder courseOrder=session.get(CourseOrder.class, CourseOrderID);
		session.remove(courseOrder);
		return CourseOrderID;
	}
	@Override
	public int updateByID(CourseOrder CourseOrder) {
		getSession().update(CourseOrder);
		return CourseOrder.getCourseOrderID();
	}
	@Override
	public CourseOrder selectByID(Integer CourseOrderID) {
		CourseOrder courseOrder=getSession().get(CourseOrder.class, CourseOrderID);
		return courseOrder;

	}
	@Override
	public List<CourseOrder> selectAll() throws ClassNotFoundException {
		return getSession().createQuery("FROM CourseOrder",CourseOrder.class).list();
	}
	@Override
	public List<Integer> getIdByUserName(String userName) {
		List<Integer>list=getSession().createQuery("SELECT userID FROM Member WHERE userName = '"+userName+"'",Integer.class).list();
		return list;
	}
	@Override
	public List<CourseOrder> selectAllByUserID(Integer userID) throws ClassNotFoundException {
		
		return getSession().createQuery("FROM CourseOrder WHERE userID = "+userID,CourseOrder.class).list();
	}
	@Override
	public List<CourseOrderDetail> selectDetailByOrderID(Integer courseOrderID) {
		
		return getSession().createQuery("FROM CourseOrderDetail WHERE courseOrderID = "+courseOrderID,CourseOrderDetail.class).list();
	}
	@Override
	public int getTotalPriceByID(Integer courseOrderID) {
		return getSession().get(CourseOrder.class, courseOrderID).getTotalPrice();
	}
	@Override
	public byte[] getCoursePhoto(Integer courseID) {
		return getSession().createQuery("SELECT coursePhoto FROM Course WHERE courseID = "+courseID,byte[].class).list().get(0);
	}
	@Override
	public String getCourseName(Integer courseID) {
		return getSession().createQuery("SELECT courseName FROM Course WHERE courseID = "+courseID,String.class).list().get(0);
	}
	@Override
	public List<Integer> getAllOrderIdByUser(Integer userID) {
		return getSession().createQuery("SELECT courseOrderID FROM CourseOrder WHERE userID = "+userID,Integer.class).list();
	}
	@Override
	public Timestamp getOrderBuyDate(Integer courseOrderID) {
		Date date=getSession().createQuery("SELECT buyDateTime FROM CourseOrder WHERE courseOrderID = "+courseOrderID,Date.class).list().get(0);
		Timestamp ts=new Timestamp(date.getTime());
		return ts;
	}
	@Override
	public int deleteDetailByID(Integer CourseOrderDetailID) {
		CourseOrderDetail courseOrderDetail=getSession().get(CourseOrderDetail.class, CourseOrderDetailID);
		getSession().remove(courseOrderDetail);
		return CourseOrderDetailID;
	}
	@Override
	public int getOrderIdByDetail(Integer courseOrderDetailID) {
		System.out.println(courseOrderDetailID);
//		return getSession().createQuery("SELECT courseOrderID FROM CourseOrderDetail WHERE courseOrderDetailID = "+courseOrderDetailID,Integer.class).list().get(0);
		return getSession().get(CourseOrderDetail.class, courseOrderDetailID).getCourseOrderID();
		
	}
	@Override
	public List<Integer> getOrderAllDetailPrice(Integer courseOrderID) {
		return getSession().createQuery("SELECT coursePrice FROM CourseOrderDetail WHERE courseOrderID = "+courseOrderID,Integer.class).list();
	}
	@Override
	public void updateOrderPrice(Integer courseOrderID, Integer price) {
		getSession().createQuery("UPDATE CourseOrder "
									+"SET totalPrice = :totalPrice "
									+"WHERE courseOrderID = :courseOrderID")
									.setParameter("totalPrice", price)
									.setParameter("courseOrderID",courseOrderID).executeUpdate();
		
	}
	@Override
	public List<String> getOrderDetailStatus(Integer courseOrderID) {

		List<String> list=getSession().createQuery("SELECT courseOrderDetailStatus FROM CourseOrderDetail WHERE courseOrderID = "+courseOrderID,String.class).list();
		return list;
	}
	@Override
	public void updateDetailStatus(Integer courseOrderID) {
		getSession().createQuery("UPDATE CourseOrderDetail "
									+"SET courseOrderDetailStatus = :courseOrderDetailStatus "
									+"WHERE courseOrderID = :courseOrderID")
									.setParameter("courseOrderDetailStatus","已結帳")
									.setParameter("courseOrderID", courseOrderID).executeUpdate();
				
		
	}
	@Override
	public Member selectMember(Integer userID) {
		return getSession().get(Member.class, userID);
	}
	@Override
	public List<Member> selectMemberByName(String userName) {
		return getSession().createQuery("FROM Member "
										+"WHERE userName "
										+"LIKE :searchName")
										.setParameter("searchName","%"+userName+"%").list();
				
	}
	@Override
	public void updatePerson(Integer courseID) {
		Integer person=getSession().get(Course.class,courseID).getCoursePerson()+1;
		getSession().createQuery("UPDATE Course "
								+"SET coursePerson = :person "
								+"WHERE courseID = :ID")
								.setParameter("person", person)
								.setParameter("ID", courseID).executeUpdate();
		System.out.println(courseID+"更新"+person+"人");
		
	}

	

}
