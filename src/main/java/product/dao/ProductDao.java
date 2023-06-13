package product.dao;

import java.util.List;

import org.hibernate.Session;

import core.CoreDao;
import product.vo.Product;


public interface ProductDao extends CoreDao {
	int insert(Product product);

	int updateByProductID(Product product);
	
	int deleteByProductID(Integer productID);
	
	byte[] loadingImage(Integer productID, Session session);

	List<Product> selectByProductClass(String productClass);
	
	List<Product> selectByProductID(Integer productID);

	List<Product> selectByProductName(String productName);

	List<Product> selectAll() throws ClassNotFoundException;
}
