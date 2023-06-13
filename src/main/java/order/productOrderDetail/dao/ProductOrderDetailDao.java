package order.productOrderDetail.dao;

import java.util.List;

import core.CoreDao;
import order.productOrderDetail.model.ProductOrderDetail;




public interface ProductOrderDetailDao extends CoreDao {
	int insert(ProductOrderDetail ProductOrderDetail);

	int deleteByID(Integer ProductOrderDetailID);

	int updateByID(ProductOrderDetail ProductOrderDetail);

	ProductOrderDetail selectByID(Integer ProductOrderDetailID);

	List<ProductOrderDetail> selectAll() throws ClassNotFoundException;
}
