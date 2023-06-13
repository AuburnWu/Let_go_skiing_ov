package order.productOrderDetail.dao;

import java.util.List;

import org.hibernate.Session;

import order.productOrderDetail.model.ProductOrderDetail;



public class ProductOrderDetailDaoImpl implements ProductOrderDetailDao{

	

	@Override
	public int insert(ProductOrderDetail ProductOrderDetail) {
		getSession().persist(ProductOrderDetail);
		return ProductOrderDetail.getProductOrderDetailID();
	}

	@Override
	public int deleteByID(Integer ProductOrderDetailID) {
		Session session=getSession();
		ProductOrderDetail productOrderDetail=session.get(ProductOrderDetail.class, ProductOrderDetailID);
		session.remove(productOrderDetail);
		return ProductOrderDetailID;
	}

	@Override
	public int updateByID(ProductOrderDetail ProductOrderDetail) {
		getSession().update(ProductOrderDetail);
		return ProductOrderDetail.getProductOrderDetailID();
	}

	@Override
	public ProductOrderDetail selectByID(Integer ProductOrderDetailID) {
		ProductOrderDetail productOrderDetail=getSession().get(ProductOrderDetail.class, ProductOrderDetailID);
		return productOrderDetail;
			
	}

	@Override
	public List<ProductOrderDetail> selectAll() throws ClassNotFoundException {
		return getSession().createQuery("FROM ProductOrderDetail",ProductOrderDetail.class).list();
	}

}
