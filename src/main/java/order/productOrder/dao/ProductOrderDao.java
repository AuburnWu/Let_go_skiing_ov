package order.productOrder.dao;

import java.sql.Timestamp;
import java.util.List;

import core.CoreDao;
import member.vo.Member;
import order.productOrder.model.ProductOrder;
import order.productOrderDetail.model.ProductOrderDetail;
import product.vo.Product;




public interface ProductOrderDao extends CoreDao{
	int insert(ProductOrder ProductOrder);

	int deleteByID(Integer productOrderID);

	int updateByID(ProductOrder ProductCar);
	
	List<Integer> getIdByUserName(String userName);

	ProductOrder selectByID(Integer productOrderID);

	List<ProductOrder> selectAll() throws ClassNotFoundException;
	
	List<ProductOrder> selectAllByUserID(Integer userID) throws ClassNotFoundException;
	
	List<ProductOrderDetail> selectDetailByOrderID(Integer productOrderID);
	
	Product getProductByID(Integer productID);
	
	List<Integer> getAllOrderIdByUser(Integer userID);

	Timestamp getOrderBuyDate(Integer productOrderID);
	
	String getAddr(Integer productOrderID);
	
	Integer getTotalPrice(Integer productOrderID);
	
	String getOrderStatus(Integer productOrderID);
	
	byte[]getProductImage(Integer productID);
	
	String getProductName(Integer productID);
	
	Member selectMember(Integer userID);
	
	List<Member> selectMemberByName(String userName);
	
	
}
