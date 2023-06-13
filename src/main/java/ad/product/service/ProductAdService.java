package ad.product.service;

import java.sql.Blob;
import java.util.List;

import ad.product.vo.ProductAd;
import ad.product.vo.ProductDTO;
import product.vo.Product;

public interface ProductAdService {

	//新增
	ProductAd insert(Integer productId);

	//刪除
	int delete(Integer productId);

	//選取上架商品
	List<Product> getProducts();

	//模糊查詢商品
	List<Product> search(String keyword);
	
	List<ProductDTO> productAdList();
	
	 List<Product> Topten();
	 List<String> slider();

}