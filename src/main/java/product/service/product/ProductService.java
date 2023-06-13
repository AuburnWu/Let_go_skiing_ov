package product.service.product;

import java.sql.Date;
import java.util.List;

import product.dao.ProductDao;
import product.dao.ProductDaoImpl;
import product.vo.Product;



public class ProductService {
	private ProductDao productDao = new ProductDaoImpl();
	

	//新增
	public Product insert(String ProductClass, String ProductName, Integer ProductPrice, byte[] ProductImage,
	        String ProductDetail, String ProductStatus,Integer productQuantity) {
	    Product product = new Product();
	    product.setProductClass(ProductClass);
	    product.setProductName(ProductName);
	    product.setProductPrice(ProductPrice);
	    product.setProductImage(ProductImage);
	    product.setProductDetail(ProductDetail);
	    product.setProductStatus(ProductStatus);
	    product.setProductQuantity(productQuantity);

	    int rowsInserted = productDao.insert(product);

	    if (rowsInserted > 0) {
	        return product;
	    } else {
	        return null;
	    }
	}
	
	//更新
	public boolean updateProduct(Product updatedProduct) {
	    // 驗證必要欄位是否為非空值
	    if (updatedProduct.getProductID() == null || updatedProduct.getProductName() == null || updatedProduct.getProductClass() == null
	            || updatedProduct.getProductPrice() == null || updatedProduct.getProductQuantity() == null
	            || updatedProduct.getProductDetail() == null || updatedProduct.getProductStatus() == null) {
	        return false; // 返回失敗，有必要欄位為空值
	    }

	    // 檢查產品是否存在
	    List<Product> productList = productDao.selectByProductID(updatedProduct.getProductID());
	    if (productList == null || productList.isEmpty()) {
	        return false; // 返回失敗，產品不存在
	    }
	    
	    Product existingProduct = productList.get(0);

	    // 更新產品屬性
	    existingProduct.setProductClass(updatedProduct.getProductClass());
	    existingProduct.setProductName(updatedProduct.getProductName());
	    existingProduct.setProductPrice(updatedProduct.getProductPrice());
	    existingProduct.setProductQuantity(updatedProduct.getProductQuantity());
	    existingProduct.setProductImage(updatedProduct.getProductImage());
	    existingProduct.setProductDetail(updatedProduct.getProductDetail());
	    existingProduct.setProductStatus(updatedProduct.getProductStatus());

	    // 呼叫 DAO 的更新方法進行資料庫更新
	    return productDao.updateByProductID(existingProduct) > 0;
	}
	
	//刪除
	public boolean deleteProduct(Integer productID) {
        // 调用 ProductDao 的 deleteByProductID 方法删除产品
        int rowsDeleted = productDao.deleteByProductID(productID);
        return rowsDeleted > 0;
    }
	
	//用商品ID搜尋
	public List<Product> selectByProductsID(Integer productID){
		return productDao.selectByProductID(productID);
	}
	
	
	//用商品類別搜尋
	public List<Product> selectByProductClass(String productClass){
		return productDao.selectByProductClass(productClass);
	}
	
	
	
	//用商品名稱搜尋
	public List<Product> selectByProductName(String productName ) {
		return productDao.selectByProductName(productName);
	}
	
	//全商品
	public List<Product> selectAll() throws Exception{
		return productDao.selectAll();
	}
}
