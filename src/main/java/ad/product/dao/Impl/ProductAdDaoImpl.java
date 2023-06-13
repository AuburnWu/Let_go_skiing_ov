package ad.product.dao.Impl;

import java.sql.Blob;
import java.util.Collections;
import java.util.List;

import org.hibernate.query.Query;

import ad.product.controller.ProductAdlistServlet;
import ad.product.dao.ProductAdDao;
import ad.product.vo.ProductAd;
import product.vo.Product;


public class ProductAdDaoImpl implements ProductAdDao {

//	getSession() getSession() = HibernateUtil.getgetSession()Factory().getCurrentgetSession()();

	//新增
	@Override
	public int insert(ProductAd ProductAd) {
		getSession().persist(ProductAd);
		return -1;
	}

	//刪除
	@Override
	public int delete(Integer id) {
		ProductAd pAd = getSession().get(ProductAd.class, id);
		getSession().remove(pAd);
		return 1;
	}

	//修改
	@Override
	public int update(ProductAd ProductAd) {
		getSession().update(ProductAd);
		return 1;
	}

	// 找廣告
	@Override
	public ProductAd selectAd(Integer id) {
		return getSession().get(ProductAd.class, id);
	}

	//選全廣告
	public List<ProductAd> selectAllAd() {
		return getSession().createQuery("FROM ProductAd", ProductAd.class).list();
	}
	
	// 選全商品
	public List<ProductAd> selectAllProduct() {
		return getSession().createQuery("FROM ProductAd", ProductAd.class).list();
	}

	//模糊查詢
	public List<Product> searchProduct(String keyword) {
		String hql = "FROM Product WHERE " + "productName LIKE :keyword " + "OR productDetail LIKE :keyword "
				+ "OR productClass LIKE :keyword";
		Query<Product> query = getSession().createQuery(hql, Product.class);
		query.setParameter("keyword", "%" + keyword + "%");
		return query.getResultList();
	}

	//前十名商品
	public List<Product> selectTopTenProducts() {
		String hql = "FROM Product ORDER BY productBuyPerson DESC";
		Query<Product> query = getSession().createQuery(hql, Product.class);
		return query.getResultList();
	}

	//上架商品
	public List<Product> selectSaleProducts() {
		String hql = "SELECT p FROM Product p WHERE p.productID NOT IN (SELECT pAd.productID FROM ProductAd pAd)"
				+ "AND productStatus LIKE '%上架%'";
		Query<Product> query = getSession().createQuery(hql, Product.class);
		return query.getResultList();
	}
	
	//取商品
	public Product selectProduct(Integer id) {
		return getSession().get(Product.class,id);
	}
	
	//廣告中商品
	public List<byte[]> random() {
		String hql = "SELECT p.productImage FROM Product p WHERE p.productID IN (SELECT pAd.productID FROM ProductAd pAd)";
		List<byte[]> blobList = getSession().createQuery(hql, byte[].class).list();
		

	    Collections.shuffle(blobList);
	    List<byte[]> randomProducts = blobList.subList(0, Math.min(blobList.size(), 3));
//	    System.out.println(randomProducts);
	    return randomProducts;

		
	}
}
