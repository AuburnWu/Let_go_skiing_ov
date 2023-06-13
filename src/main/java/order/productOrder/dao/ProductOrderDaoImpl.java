package order.productOrder.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import member.vo.Member;
import order.productOrder.model.ProductOrder;
import order.productOrderDetail.model.ProductOrderDetail;
import product.vo.Product;




public class ProductOrderDaoImpl implements ProductOrderDao{


	@Override
	public int insert(ProductOrder ProductOrder) {
		getSession().persist(ProductOrder);
		return ProductOrder.getProductOrderID();
	}

	@Override
	public int deleteByID(Integer productOrderID) {
		Session session=getSession();
		ProductOrder productOrder=session.get(ProductOrder.class, productOrderID);
		session.remove(productOrder);
		return productOrderID;
	}

	@Override
	public int updateByID(ProductOrder ProductOrder) {
		getSession().update(ProductOrder);
		return ProductOrder.getProductOrderID();
	}

	@Override
	public ProductOrder selectByID(Integer productOrderID) {
		ProductOrder productOrder=getSession().get(ProductOrder.class, productOrderID);
		return productOrder;
	}

	@Override
	public List<ProductOrder> selectAll() throws ClassNotFoundException {
		return getSession().createQuery("FROM ProductOrder",ProductOrder.class).list();
	}

	@Override
	public List<Integer> getIdByUserName(String userName) {
		List<Integer>list=getSession().createQuery("SELECT userID FROM Member WHERE userName = '"+userName+"'",Integer.class).list();
		return list;
	}

	@Override
	public List<ProductOrder> selectAllByUserID(Integer userID) throws ClassNotFoundException {
		return getSession().createQuery("FROM ProductOrder WHERE userID = "+userID,ProductOrder.class).list();
	}

	@Override
	public List<ProductOrderDetail> selectDetailByOrderID(Integer productOrderID) {
		return getSession().createQuery("FROM ProductOrderDetail WHERE productOrderID = "+productOrderID,ProductOrderDetail.class).list();
	}

	@Override
	public Product getProductByID(Integer productID) {
		return getSession().get(Product.class, productID);
	}

	@Override
	public List<Integer> getAllOrderIdByUser(Integer userID) {
		return getSession().createQuery("SELECT productOrderID FROM ProductOrder WHERE userID = "+userID,Integer.class).list();
	}

	@Override
	public Timestamp getOrderBuyDate(Integer productOrderID) {
		Date date=getSession().createQuery("SELECT buyDateTime FROM ProductOrder WHERE productOrderID = "+productOrderID,Date.class).list().get(0);
		Timestamp ts=new Timestamp(date.getTime());
		return ts;
	}

	@Override
	public String getAddr(Integer productOrderID) {
		return getSession().createQuery("SELECT deliveryAddr FROM ProductOrder WHERE productOrderID = "+productOrderID,String.class).list().get(0);
	}

	@Override
	public Integer getTotalPrice(Integer productOrderID) {
		return getSession().createQuery("SELECT totalPrice FROM ProductOrder WHERE productOrderID = "+productOrderID,Integer.class).list().get(0);
	}

	@Override
	public String getOrderStatus(Integer productOrderID) {
		return getSession().createQuery("SELECT productOrderStatus FROM ProductOrder WHERE productOrderID = "+productOrderID,String.class).list().get(0);
	}

	@Override
	public byte[] getProductImage(Integer productID) {
		return getSession().createQuery("SELECT productImage FROM Product WHERE productId = "+productID,byte[].class).list().get(0);
	}

	@Override
	public String getProductName(Integer productID) {
		return getSession().createQuery("SELECT productName FROM Product WHERE productId = "+productID,String.class).list().get(0);
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

}
