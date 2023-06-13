package ad.product.dao;

import java.util.List;

import ad.product.vo.ProductAd;
import core.CoreDao;
import product.vo.Product;

public interface ProductDao extends CoreDao {

	int insert(ProductAd ProductAd);

	int delete(Integer id);

	int update(ProductAd ProductAd);

	ProductAd selectAd(Integer id);

	List<ProductAd> selectAllProduct();

	List<Product> searchProduct(String keyword);
	List<Product> selectSaleProducts();
	List<Product> selectTopTenProducts();
}