package order.courseCar.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import course.entity.Course;
import order.courseCar.dao.CourseCarDao;
import order.courseCar.dao.CourseCarDaoImpl;
import order.courseCar.dao.CourseCarPK;
import order.courseCar.model.CourseCar;
import order.courseOrder.model.CourseOrder;
import order.courseOrderDetail.model.CourseOrderDetail;


public class CourseCarServiceImpl implements CourseCarService{
	private CourseCarDao dao;

	
	public CourseCarServiceImpl() {
		dao=new CourseCarDaoImpl();
	}
	@Override
	public List<CourseCar> ListAll(Integer userID) {		
		try {
			System.out.println("取得購物車成功");
			return dao.selectAllByUserID(userID);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("取得購物車失敗");
			return null;
		}
	}
	@Override
	public JSONArray listCourseCar(Integer userID) {
		//列出圖片之base64問題未處理
		List<CourseCar>carList=ListAll(userID);
		List<Course>courseData =new ArrayList<Course>();
		try {
			for(CourseCar coursecar:carList) {
				Integer courseID=coursecar.getId().getCourseID();
				Course course=dao.getCourseByID(courseID);
				courseData.add(course);
			}
			JSONArray jsonArray=new JSONArray(courseData);
			System.out.println("取得購物車成功");
			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("取得購物車失敗");
			return null;
		}
	}

	@Override
	public boolean addCar(CourseCar courseCar) {

		if(dao.selectByID(courseCar.getId())==null) {
			dao.insert(courseCar);
			return true;

		}else {
			System.out.println("購物車已有同一商品");
			return false;
		}
		
		
	}

	@Override
	public JSONObject removeCar(Integer userID,Integer courseID) {
		CourseCarPK coursePK=new CourseCarPK(userID,courseID);
		try {
			dao.deleteByID(coursePK);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("courseID",courseID);
			return jsonObject;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.err.println("無此資料");
			return null;
		}
		
	}

	@Override
	public void cleanCar(Integer userID) {
		try {
			System.out.println("刪除使用者"+userID+"的"+dao.deleteAllByUserID(userID)+"筆購物車資料");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("清空購物車失敗");
		}
		
	}

	@Override
	public Integer subTotal(Integer userID) {
		Integer total=0;
		try {
			List<CourseCar> list=dao.selectAllByUserID(userID);
			for(CourseCar courseCar:list) {
				int courseID=courseCar.getId().getCourseID();
				Course course=dao.getCourseByID(courseID);
				int price=(int) course.getCoursePrice();
				total=total+price;
			}
			return total;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("計算總額失敗");
			return null;
		}
		
		
	}


	@Override
	public JSONObject userINFO(Integer userID) {
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("userPhone", dao.getUserPhone(userID));
		jsonObject.put("userPersonID", dao.getUserPersonID(userID));
		return jsonObject;
	}

	@Override
	public void carPush(Integer userID) {
		try {
			List<CourseCar>list=dao.selectAllByUserID(userID);
			CourseOrder courseOrder=new CourseOrder(null,userID,subTotal(userID),Timestamp.from(Instant.now()));
			dao.insertCourseOrder(courseOrder);
			int courseOrderID=courseOrder.getCourseOrderID();
			for(CourseCar courseCar:list) {
				int courseID=courseCar.getId().getCourseID();
				int coursePrice=dao.getCourseByID(courseID).getCoursePrice();
				int quantity=courseCar.getQuantity();
				CourseOrderDetail courseOrderDetail=new CourseOrderDetail(null,courseOrderID,courseID,coursePrice,quantity,"審核中");
				dao.insertCourseOrderDetail(courseOrderDetail);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("送出購物車失敗");
		}
		
	}
	@Override
	public boolean sendAply(Integer userID) {
		try {
			carPush(userID);
			cleanCar(userID);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	@Override
	public byte[] courseImg(Integer courseID) {
		return dao.getCourseByID(courseID).getCoursePhoto();
	}


//	public void insertImg(String base64) {
//		Course course = new Course();
//		// 把base64轉byte陣列
//		byte[] bytes = base64.getBytes();
//		course.setCoursePhoto(bytes);
//		dao.insertCourse(course);
//	}
//	
//	public String selectCourseImg(Integer courseID) {
//		byte[] coursePhoto = dao.getCourseByID(courseID).getCoursePhoto();
//		// 把byte陣列轉回base64字串
//		return new String(coursePhoto);
//	}




}
