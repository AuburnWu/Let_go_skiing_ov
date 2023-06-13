package order.productCar.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import order.productCar.dao.ProductCarPK;
import order.productCar.model.ProductCar;

public interface ProductCarService {
	
	//讀取並列出購物車內容
	List<ProductCar> ListAll(Integer userID);
	//回傳購物車頁面的商品資料
	JSONArray listProductCar(Integer userID);
	//加入購物車
	boolean addCar(ProductCar productCar);
	//從購物車移除
	JSONObject removeCar(Integer userID,Integer productID);
	//清空購物車
	void cleanCar(Integer userID);
	//小計
	Integer subTotal(Integer userID);
	//將購物車的資料存至訂單
	void carPush(Integer userID,String deliveryAddr);
	//結帳
	boolean sendAply(Integer userID,String deliveryAddr);
	//檢查購物車內的商品是否高於存貨,若是的話回傳true並刪除該筆購物車資料
	boolean stockCheck(Integer userID,Integer productID);
	//回傳商品圖片
	byte[] productImg(Integer productID);

}
