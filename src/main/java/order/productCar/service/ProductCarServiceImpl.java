package order.productCar.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import order.productCar.dao.ProductCarDao;
import order.productCar.dao.ProductCarDaoImpl;
import order.productCar.dao.ProductCarPK;
import order.productCar.model.ProductCar;
import order.productOrder.model.ProductOrder;
import order.productOrderDetail.model.ProductOrderDetail;
import product.vo.Product;

public class ProductCarServiceImpl implements ProductCarService{
	private ProductCarDao dao;	
	public ProductCarServiceImpl() {
		dao=new ProductCarDaoImpl();
	}
	@Override
	public List<ProductCar> ListAll(Integer userID) {
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
	public JSONArray listProductCar(Integer userID) {
		//列出圖片之base64問題未處理
		List<ProductCar>productList=ListAll(userID);
		JSONArray jsonArray=new JSONArray();
		try {
			for(ProductCar productcar:productList) {
				JSONObject jsonObject=new JSONObject();
				Integer productID=productcar.getId().getProductID();
				Product product=dao.getProductByID(productID);
				jsonObject.put("productID",productID);
				jsonObject.put("productImage",product.getProductImage());
				jsonObject.put("productName",product.getProductName());
				Integer productPrice=product.getProductPrice();
				jsonObject.put("productPrice",productPrice);
				Integer quantity=productcar.getQuantity();
				jsonObject.put("quantity",quantity);
				Integer subTotal= productPrice*quantity;
				jsonObject.put("subTotal",subTotal);
						
				jsonArray.put(jsonObject);
			}
	
			System.out.println("取得購物車成功");
			return jsonArray;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addCar(ProductCar productCar) {
		if(dao.selectByID(productCar.getId())==null) {
			dao.insert(productCar);
			return true;

		}else {
			System.out.println("購物車已有同一商品");
			return false;
		}
		
	}

	@Override
	public JSONObject removeCar(Integer userID,Integer productID) {
		ProductCarPK productPK=new ProductCarPK(userID,productID);
		try {
			dao.deleteByID(productPK);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("productID", productID);
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
			List<ProductCar> list=dao.selectAllByUserID(userID);
			for(ProductCar productCar:list) {
				int productID=productCar.getId().getProductID();
				Product product=dao.getProductByID(productID);
				int price=product.getProductPrice();
				int quantity=productCar.getQuantity();
				total=total+(price*quantity);
				
			}
			System.out.println("計算總額成功:"+total);
			return total;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("計算總額失敗");
			return null;
		}
	
	}


	@Override
	public void carPush(Integer userID,String deliveryAddr) {
		try {
			List<ProductCar>list=dao.selectAllByUserID(userID);
			ProductOrder productOrder=new ProductOrder(null,userID,subTotal(userID),deliveryAddr,Timestamp.from(Instant.now()),"已收到訂單");
			dao.insertProductOrder(productOrder);
			int productOrderID=productOrder.getProductOrderID();
			for(ProductCar productCar:list) {
				int productID=productCar.getId().getProductID();
				int productPrice=dao.getProductByID(productID).getProductPrice();
				int quantity=productCar.getQuantity();
				ProductOrderDetail productOrderDetail=new ProductOrderDetail(null,productOrderID,productID,productPrice,quantity);
				dao.insertProductOrderDetail(productOrderDetail);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("送出購物車失敗");
		}
		
	}

	@Override
	public boolean sendAply(Integer userID,String deliveryAddr) {
		try {
			List<ProductCar>list=dao.selectAllByUserID(userID);
			carPush(userID,deliveryAddr);
			for(ProductCar productCar:list) {
				int productID=productCar.getId().getProductID();
				int carQuantity=productCar.getQuantity();
				int stock=dao.getProductByID(productID).getProductQuantity();
				int newStock=stock-carQuantity;			
				dao.updateStock(productID, newStock);
			}
			cleanCar(userID);
			
	
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	@Override
	public boolean stockCheck(Integer userID,Integer productID) {
		ProductCarPK productPK=new ProductCarPK(userID,productID);
		int carQuantity=dao.selectByID(productPK).getQuantity();
		int stock=dao.getProductByID(productID).getProductQuantity();
		if(carQuantity<=stock) {
			return true;
		}else {
			dao.deleteByID(productPK);
			return false;
		}
		
	}
	@Override
	public byte[] productImg(Integer productID) {
		return dao.getProductByID(productID).getProductImage();
	}



}
