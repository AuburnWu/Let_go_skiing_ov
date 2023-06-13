package product.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import product.vo.Product;

public class ProductDaoImpl implements ProductDao {

	@Override
	public int insert(Product product) {
//		String sql = "INSERT INTO Product (ProductClass, ProductName, ProductPrice, ProductQuantity, ProductImage, ProductDetail, ProductBuyPerson, ProductDate, ProductStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			getSession().persist(product);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insert方法發生錯誤：" + e.getMessage());
		}
		return -1;
	}

	public int deleteByProductID(Integer productID) {
	    try {
	        Session session = getSession();
	        Product product = session.get(Product.class, productID);

	        if (product != null) {
	            session.delete(product);
	            return 1;
	        } else {
	            return 0; 
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("deleteByProductID方法發生錯誤：" + e.getMessage());
	    }
	    return -1;
	}
	
	public byte[] loadingImage(Integer productID, Session session) {
        if (productID == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        
        String hql = "SELECT p.productImage FROM Product p WHERE p.productID = :productID";
        byte[] imageData = null;
        
        try {
            imageData = (byte[]) session.createQuery(hql)
                .setParameter("productID", productID)
                .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace(); // 或者使用更合适的方式记录错误，比如日志记录器。
        }
        
        if (imageData == null || imageData.length == 0) {
            throw new IllegalStateException("No image data found for the product ID " + productID);
        }
        
        return imageData;
    }


	public int updateByProductID(Product product) {
	    
	        Session session = getSession();  
	        session.merge(product);
	    return 1;
	}

	public List<Product> selectByProductID(Integer productID) {
	    try {
	        Session session = getSession();
	        Query<Product> query = session.createQuery("FROM Product WHERE productID = :productID", Product.class);
	        query.setParameter("productID", productID);
	        List<Product> productList = query.getResultList();
	        return productList;
	    } catch (Exception e) {
	        System.out.println("selectByProductID方法发生错误：" + e.getMessage());
	        e.printStackTrace();
	    }
	    return null;
	}
	

	@Override
	public List<Product> selectByProductClass(String productClass) {
	    try {
	        Session session = getSession();
	        String hql = "FROM Product p WHERE p.productClass = :productClass";
	        Query<Product> query = session.createQuery(hql, Product.class);
	        query.setParameter("productClass", productClass);
	        List<Product> list = query.getResultList();
	        return list;
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("selectByProductClass方法發生錯誤：" + e.getMessage());
	    }
	    return new ArrayList<>();
	}

	@Override
	public List<Product> selectByProductName(String productName) {
	    try {
	        Session session = getSession();
	        String hql = "FROM Product p WHERE p.productName LIKE :productName";
	        Query<Product> query = session.createQuery(hql, Product.class);
	        query.setParameter("productName", "%" + productName + "%");
	        List<Product> list = query.getResultList();
	        return list;
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("selectByProductName方法發生錯誤：" + e.getMessage());
	    }
	    return new ArrayList<>();
	}


	@Override
	public List<Product> selectAll() throws ClassNotFoundException {
	    try {
	        Session session = getSession();
	        String hql = "FROM Product";
	        Query<Product> query = session.createQuery(hql, Product.class); // import org.hibernate.query.Query;
			List<Product> list = query.getResultList();
			return list;
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("selectAll方法發生錯誤：" + e.getMessage());
	    }
	    return null;
	}
//=================main方法測試=================
//	public static void main(String[] args) {
//	    ProductDao productDao = new ProductDaoImpl();
//	    Product product = new Product();
//	    
//	    product.setProductStatus("上架中");
//	    product.setProductDetail("安安");
//	    product.setProductQuantity(1);
//	    product.setProductDate("2023-05-05");
//	    product.setProductBuyPerson(2);
//	    product.setProductPrice(4);
//	    product.setProductClass("機能服飾");
//	    product.setProductName("發熱衣");
//
//	    // 讀取圖片檔案並將其轉換為 byte array
//	    File file = new File("/Users/tomo/Downloads/istockphoto-878524608-612x612.jpg"); 
//	    byte[] productImage = new byte[(int) file.length()];
//	    try {
//	        FileInputStream fileInputStream = new FileInputStream(file);
//	        fileInputStream.read(productImage);
//	        fileInputStream.close();
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//
//	    // 將圖片資料設定到 product 物件
//	    product.setProductImage(productImage);
//
//	    try {
//	        productDao.insert(product);
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	}


//	public static void main(String[] args) {
//		ProductDao productDao = new ProductDaoImpl();
//		Product product = new Product();
//		byte[] productImage = new byte[1];
//		product.setProductId(3);
//		product.setProductClass("安安");
//		product.setProductName("安安3");
//		product.setProductImage(productImage);
//		try {
//			productDao.updateByProductID(product);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public static void main(String[] args) {
//		ProductDao productDao = new ProductDaoImpl();
//		Product product = new Product();
//		byte[] productImage = new byte[1];
//
//		try {
//			productDao.deleteByProductID(2);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public static void main(String[] args) {
//		ProductDao product = new ProductDaoImpl();
//
//		try {
//			for (Product pro : product.selectByProductID(3)) {
//				System.out.println(pro.toString());
//
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public static void main(String[] args) {
//		ProductDao product = new ProductDaoImpl();
//
//		try {
//			for (Product pro : product.selectByProductName("板")) {
//				System.out.println(pro.toString());
//
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public static void main(String[] args) {
//		ProductDao product = new ProductDaoImpl();
//
//		try {
//			for (Product pro : product.selectByProductClass("機能服飾")) {
//				System.out.println(pro.toString());
//
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public static void main(String[] args) {
//		ProductDao product = new ProductDaoImpl();
//		
//		try {
//			for (Product emp : product.selectAll()) {
//				System.out.println(emp.toString());
//			
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
