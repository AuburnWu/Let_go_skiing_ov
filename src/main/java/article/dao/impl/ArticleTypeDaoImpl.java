package article.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;

//import article.core.HibernateUtil;
import article.dao.ArticleTypeDao;
import article.vo.ArticleType;
import core.HibernateUtil;
import product.vo.Product;

public class ArticleTypeDaoImpl implements ArticleTypeDao {
	
	@Override
	public int insert(ArticleType articleType) {
		//Hibernate
		getSession().persist(articleType);
		return articleType.getArticleTypeID();
	}
	@Override
	public int deleteByArticleTypeID(Integer articleTypeID) {
		//Hibernate
		ArticleType articleType = getSession().get(ArticleType.class, articleTypeID);
	    getSession().remove(articleType);
	    return articleTypeID;
	}
	
	@Override
	public int updateByArticleTypeID(ArticleType articleType) {
		//Hibernate
		getSession().update(articleType);
	    return articleType.getArticleTypeID();
	}
	
	@Override
	public ArticleType selectByArticleTypeID(Integer articleTypeID) {
		//Hibernate
		ArticleType articleType = getSession().get(ArticleType.class, articleTypeID); // 方法僅支援根據主鍵 (PK) 值來檢索資料
	    return articleType;
	}
	
	@Override
	public List<ArticleType> selectByArticleTypeContent(String articleTypeContent) {
		String hqlQuery = "FROM ArticleType WHERE articleTypeContent LIKE :content";
	    Query<ArticleType> query = getSession().createQuery(hqlQuery, ArticleType.class);
	    query.setParameter("content","%"+ articleTypeContent+"%");
	    List<ArticleType> list = query.getResultList();
	    return list;
	}
	
	@Override
	public List<ArticleType> selectByArticleTypeContentRepeat(String articleTypeContent) {
		String hqlQuery = "FROM ArticleType WHERE articleTypeContent = :content";
	    Query<ArticleType> query = getSession().createQuery(hqlQuery, ArticleType.class);
	    query.setParameter("content", articleTypeContent);
	    List<ArticleType> list = query.getResultList();
	    return list;
	}
	
	@Override
	public List<ArticleType> selectAll() throws ClassNotFoundException {
		//Hibernate
		return getSession().createQuery("FROM ArticleType", ArticleType.class).list();
	}
	
	@Override
	public List<ArticleType> deleteAllAndInsertAll() throws ClassNotFoundException {
		String articleTypeProvisional = "ArticleType_provisional";
		getSession().createNativeQuery("CREATE TABLE "+ articleTypeProvisional+ " LIKE ArticleType").executeUpdate();
		getSession().createNativeQuery("INSERT INTO " + articleTypeProvisional + "(articleTypeContent) SELECT articleTypeContent FROM ArticleType").executeUpdate();
		getSession().createNativeQuery("TRUNCATE TABLE ArticleType").executeUpdate();
		getSession().createNativeQuery("INSERT INTO ArticleType(articleTypeContent) SELECT articleTypeContent FROM "+articleTypeProvisional).executeUpdate();
		getSession().createNativeQuery("DROP TABLE "+articleTypeProvisional).executeUpdate();
		return getSession().createQuery("FROM ArticleType", ArticleType.class).list();
	}
	
	@Override
	public List<ArticleType> getArticlesByPage(int pageNumber, int pageSize) {
//	    Session session = sessionFactory.getCurrentSession();
	    Criteria criteria = getSession().createCriteria(ArticleType.class);
	    criteria.setFirstResult((pageNumber - 1) * pageSize);
	    criteria.setMaxResults(pageSize);
	    List<ArticleType> articlesTypes = criteria.list();
	    return articlesTypes;
	}
	
//	public static void main(String[] args) throws ClassNotFoundException {
//
//		SessionFactory sf=HibernateUtil.getSessionFactory();
//		Session session=sf.getCurrentSession();
//		Transaction tr=session.getTransaction();
//		ArticleTypeDao dao = new ArticleTypeDaoImpl();
//		try {
//			tr.begin();
//			
//			//新增
////			ArticleType articleType1 = new ArticleType(null, "nnnnn");
////			System.out.println(dao.insert(articleType1));
//			
//			//刪除ID
////			dao.deleteByArticleTypeID(2);
//			
//			//修改
////			ArticleType articleType2 = new ArticleType(2, "新手");
////			dao.updateByArticleTypeID(articleType2);
//			
//			//查詢ID
////			System.out.println(dao.selectByArticleTypeID(2));
//			
//			//查詢內容
////			for(ArticleType articleType: dao.selectByArticleTypeContent("ewead")) {
////				System.out.println(articleType.toString());
////			}
//			
//			
//			//查詢全部
////			System.out.println(dao.selectAll());
//			
//			//刪除後重整ArticleType
////			dao.deleteAllAndInsertAll();
//			
//			tr.commit();
//		} catch (Exception e) {
//			tr.rollback();
//			// TODO: handle exception
//		}
//	}
	

}
