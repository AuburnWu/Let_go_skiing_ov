package order.courseCar.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import order.courseCar.model.CourseCar;

public interface CourseCarService {

	// 讀取並列出該使用者購物車內容
	List<CourseCar> ListAll(Integer userID);

	// 回傳購物車頁面的課程資料
	JSONArray listCourseCar(Integer userID);

	// 加入購物車
	boolean addCar(CourseCar courseCar);

	// 從購物車移除
	JSONObject removeCar(Integer userID, Integer courseID);

	// 清空購物車
	void cleanCar(Integer userID);

	// 小計
	Integer subTotal(Integer userID);

	// 回傳使用者的電話套馬與身分證字號
	JSONObject userINFO(Integer userID);

	// 將購物車內已過審核的選擇的資料存至訂單
	void carPush(Integer userID);

	// 送出申請
	boolean sendAply(Integer userID);

	// 回傳商品圖片
	byte[] courseImg(Integer courseID);

//	void insertImg(String base64);
//
//	String selectCourseImg(Integer courseID);

}
