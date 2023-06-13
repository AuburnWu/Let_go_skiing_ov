package order.courseOrder.service;

import java.util.List;

import org.json.JSONArray;

import order.courseOrder.model.CourseOrder;
import order.courseOrderDetail.model.CourseOrderDetail;

public interface CourseOrderService {
	
	//列出該使用者所有訂單,並已courseOrder做區別排列
	List<CourseOrder> listOrderByID(Integer userID);
	//以id取得該使用者該筆訂單之總價
	Integer getOrderTotal(Integer courseOrderID);
	//回傳訂單的ID與時間作為訂單列表的表頭
	JSONArray getOrderListHeader(Integer userID);
	//以id取得該使用者所有已通過審核的訂單,userID查詢CourseOrder取得CourseOrderID,再用以查詢courseOrderDetail中的courseID與相關資料
	JSONArray getCourseINFO(Integer courseOrderID);
	//移除訂單細節
	Integer removeOrderDetail(Integer courseOrderDetailID);
	//若訂單之細節已空,刪除訂單
	String removeOrder(Integer courseOrderID);
	//重新計算訂單之總價並回傳
	Integer calOrderTotal(Integer courseOrderID);
	//呼叫上面方法並改變訂單總價
	void updateOrderTotal(Integer courseOrderID);
	//正式結帳訂單,將此會員以通過的課程訂單狀態改為已結帳
	Integer updateDetailStatus(Integer courseOrderDetailID);
	//以姓名查詢該使用者id,考慮到同名狀況,size()>1時需要再從前端選擇會員,並送出一個ID來查詢
	List<Integer> getIdByUserName(String userName);
	//上面情況經前端選擇後再用id取得訂單
	List<CourseOrder> listOrderByUserName(String userName,Integer index);
	//取得該訂單主檔所有明細
	List<CourseOrderDetail>listOrderDetail(Integer courseOrderID);
	//檢查訂單內所有細項,並回傳字串
	Integer statusCheck(Integer courseOrderID);
	//查詢是否有該使用者ID,並回傳布林值
	boolean checkIdExist(Integer userID);
	//查詢是否有此使用者ID並以此搜尋訂單資料回傳
	JSONArray checkIdToOrder(Integer userID);
	//查詢是否有相應的使用者名稱,並回傳符合的使用者資料
	JSONArray checkUserName(String userName);
}
