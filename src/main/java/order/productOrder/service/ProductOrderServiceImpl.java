package order.productOrder.service;

import java.util.List;

import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;

import core.util.HibernateUtil;
import member.vo.Member;
import order.productOrder.dao.ProductOrderDao;
import order.productOrder.dao.ProductOrderDaoImpl;
import order.productOrder.model.ProductOrder;
import order.productOrderDetail.model.ProductOrderDetail;

public class ProductOrderServiceImpl implements ProductOrderService{
	private ProductOrderDao dao;
	static Session session=HibernateUtil.getSessionFactory().getCurrentSession();
	public ProductOrderServiceImpl() {
		dao=new ProductOrderDaoImpl();
	}
	@Override
	public List<ProductOrder> listOrderByID(Integer userID) {
		try {
			return dao.selectAllByUserID(userID);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("取得購物車失敗");
			return null;
		}
	}

	@Override
	public List<Integer> getIdByUserName(String userName) {
		return dao.getIdByUserName(userName);
		//可能查到同名的會員或無資料,需要送到controller做處理
	}
	@Override
	public List<ProductOrder> listOrderByUserName(String userName,Integer index) {
		List<Integer>list=getIdByUserName(userName);
		Integer userID=list.get(index);	
		return listOrderByID(userID);
	}

	@Override
	public List<ProductOrderDetail> listOrderDetail(Integer productOrderID) {
		return dao.selectDetailByOrderID(productOrderID);
	}
	@Override
	public JSONArray getOrderListHeader(Integer userID) {
		JSONArray jsonArray=new JSONArray();
		List<Integer>productOrderIdList=dao.getAllOrderIdByUser(userID);
		for(Integer productOrderID:productOrderIdList) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("productOrderID",productOrderID);
			jsonObject.put("buyDateTime",dao.getOrderBuyDate(productOrderID));
			jsonObject.put("deliveryAddr", dao.getAddr(productOrderID));
			jsonObject.put("totalPrice", dao.getTotalPrice(productOrderID));
			jsonObject.put("productOrderStatus",dao.getOrderStatus(productOrderID));
			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}
	@Override
	public JSONArray getProductINFO(Integer productOrderID) {
		JSONArray jsonArray=new JSONArray();
		List<ProductOrderDetail>list=dao.selectDetailByOrderID(productOrderID);

		for(ProductOrderDetail productOrderDetail:list) {
			JSONObject jsonObject=new JSONObject();
			int productID=productOrderDetail.getProductID();

			jsonObject.put("productOrderDetailID",productOrderDetail.getProductOrderDetailID());
			jsonObject.put("productID",productID);
			jsonObject.put("productPrice",productOrderDetail.getProductPrice());
			jsonObject.put("quantity",productOrderDetail.getQuantity());
			jsonObject.put("productImage",dao.getProductImage(productID));
			jsonObject.put("productName",dao.getProductName(productID));
			jsonArray.put(jsonObject);				
	}
	return jsonArray;
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
				List<ProductOrder>list=dao.selectAllByUserID(userID);
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
