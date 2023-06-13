package order.courseOrder.service;

import java.util.List;

import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;

import core.util.HibernateUtil;
import member.vo.Member;
import order.courseOrder.dao.CourseOrderDao;
import order.courseOrder.dao.CourseOrderDaoImpl;
import order.courseOrder.model.CourseOrder;
import order.courseOrderDetail.model.CourseOrderDetail;

public class CourseOrderServiceImpl implements CourseOrderService{
	private CourseOrderDao dao;
	static Session session=HibernateUtil.getSessionFactory().getCurrentSession();
	public CourseOrderServiceImpl() {
		dao=new CourseOrderDaoImpl();
	}
	
	@Override
	public List<CourseOrder> listOrderByID(Integer userID) {
		try {
			return dao.selectAllByUserID(userID);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("取得購物車失敗");
			return null;
		}
		
	}
	@Override
	public Integer getOrderTotal(Integer courseOrderID) {
		return dao.getTotalPriceByID(courseOrderID);
	}
	@Override
	public JSONArray getOrderListHeader(Integer userID) {
		JSONArray jsonArray=new JSONArray();
		List<Integer>courseOrderIdList=dao.getAllOrderIdByUser(userID);
		for(Integer courseOrderID:courseOrderIdList) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("courseOrderID",courseOrderID);
			jsonObject.put("buyDateTime",dao.getOrderBuyDate(courseOrderID));
			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}
	@Override
	public JSONArray getCourseINFO(Integer courseOrderID) {
		JSONArray jsonArray=new JSONArray();
			List<CourseOrderDetail>list=dao.selectDetailByOrderID(courseOrderID);

			for(CourseOrderDetail courseOrderDetail:list) {
				JSONObject jsonObject=new JSONObject();
				int courseID=courseOrderDetail.getCourseID();
	
				jsonObject.put("courseOrderDetailID",courseOrderDetail.getCourseOrderDetailID());
				jsonObject.put("courseID",courseID);
				jsonObject.put("coursePrice",courseOrderDetail.getCoursePrice());
				jsonObject.put("courseOrderDetailStatus",courseOrderDetail.getCourseOrderDetailStatus());
				jsonObject.put("coursePhoto",dao.getCoursePhoto(courseID));
				jsonObject.put("courseName",dao.getCourseName(courseID));
				jsonArray.put(jsonObject);				
		}
		return jsonArray;
	}

	@Override
	public Integer calOrderTotal(Integer courseOrderID) {
		int total=0;
		List<Integer>list=dao.getOrderAllDetailPrice(courseOrderID);
		for(Integer price:list) {
			total=total=price;
		}	
		return total;
	}

	@Override
	public void updateOrderTotal(Integer courseOrderID) {
		int total=calOrderTotal(courseOrderID);
		dao.updateOrderPrice(courseOrderID, total);
		
	}



	@Override
	public List<Integer> getIdByUserName(String userName) {
		return dao.getIdByUserName(userName);
		//可能查到同名的會員或無資料,需要送到controller做處理
	}
	@Override
	public List<CourseOrder> listOrderByUserName(String userName,Integer index) {
		List<Integer>list=getIdByUserName(userName);
		Integer userID=list.get(index);	
		return listOrderByID(userID);
	}

	@Override
	public List<CourseOrderDetail> listOrderDetail(Integer courseOrderID) {
		
		return dao.selectDetailByOrderID(courseOrderID);
	}

	@Override
	public Integer removeOrderDetail(Integer courseOrderDetailID) {
		int courseOrderID=dao.getOrderIdByDetail(courseOrderDetailID);
		dao.deleteDetailByID(courseOrderDetailID);
		updateOrderTotal(courseOrderID);
		removeOrder(courseOrderID);
		return courseOrderDetailID;
	}

	@Override
	public String removeOrder(Integer courseOrderID) {
		if(dao.selectDetailByOrderID(courseOrderID).isEmpty()) {
			dao.deleteByID(courseOrderID);
			return "remove empty order success";
		}else {
			return "remove empty order failed";
		}
	}

	@Override
	public Integer updateDetailStatus(Integer courseOrderID) {
		dao.updateDetailStatus(courseOrderID);
		List<CourseOrderDetail>list=dao.selectDetailByOrderID(courseOrderID);
		for(CourseOrderDetail courseOrderDetail:list) {
			Integer courseID=courseOrderDetail.getCourseID();
			dao.updatePerson(courseID);
			
		}
		return courseOrderID;
		
	}

	@Override
	public Integer statusCheck(Integer courseOrderID) {
		int statusChecked=0;
		List<String> list=dao.getOrderDetailStatus(courseOrderID);
				if(list.contains("未通過")) {
					statusChecked=4;//此訂單有未通過的細項
				}else if(list.contains("審核中")){
					statusChecked=2;//此訂單待審核
				}else if(list.contains("已結帳")){
					statusChecked=1;//此訂單已結帳
				}else {
					statusChecked=3;//全部皆為已通過
				}
				return statusChecked;
	}

	@Override
	public boolean checkIdExist(Integer userID) {
		return (dao.selectMember(userID)!=null);
	}

	@Override
	public JSONArray checkIdToOrder(Integer userID) {
		boolean IdExist=checkIdExist(userID);
		
		if(IdExist) {
			try {
				List<CourseOrder>list=dao.selectAllByUserID(userID);
				JSONArray jsonArray=new JSONArray(list);
				return jsonArray;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			
		}else {
			return null;
		}
		
	}

	@Override
	public JSONArray checkUserName(String userName) {
		List<Member>list=dao.selectMemberByName(userName);
		JSONArray jsonArray=new JSONArray();
		if(list.size()!=0) {
			for(Member member:list) {
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("userID",member.getUserID());
				jsonObject.put("userName",member.getUserName());
				jsonObject.put("email",member.getEmail());
				jsonObject.put("personID",member.getPersonID());
				jsonObject.put("phone", member.getPhone());
				jsonArray.put(jsonObject);
				
			}
			return jsonArray;
		}else {
			return null;
		}
		
	}









}
