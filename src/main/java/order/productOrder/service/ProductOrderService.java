package order.productOrder.service;

import java.util.List;

import org.json.JSONArray;

import order.productOrder.model.ProductOrder;
import order.productOrderDetail.model.ProductOrderDetail;

public interface ProductOrderService {
	//以id取得該使用者所有訂單
	List<ProductOrder> listOrderByID(Integer userID);
	//以姓名查詢該使用者id,考慮到同名狀況,size()>1時需要再從前端選擇會員,並送出一個ID來查詢
	List<Integer> getIdByUserName(String userName);
	//上面情況經前端選擇後再用id取得訂單
	List<ProductOrder> listOrderByUserName(String userName,Integer index);
	//取得該訂單主檔所有明細
	List<ProductOrderDetail>listOrderDetail(Integer productOrderID);
	//回傳訂單的ID與時間作為訂單列表的表頭
	JSONArray getOrderListHeader(Integer userID);
	//回傳訂單細節中的資料
	JSONArray getProductINFO(Integer productOrderID);
	//查詢是否有該使用者ID,並回傳布林值
	boolean checkIdExist(Integer userID);
	//查詢是否有此使用者ID並以此搜尋訂單資料回傳
	JSONArray checkIdToOrder(Integer userID);
	//查詢是否有相應的使用者名稱,並回傳符合的使用者資料
	JSONArray checkUserName(String userName);

}
