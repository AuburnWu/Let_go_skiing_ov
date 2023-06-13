package article.dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import article.dao.ArticleLikeDao;
import article.vo.ArticleLike;
import article.vo.ArticleType;

public class ArticleLikeDaoImpl implements ArticleLikeDao{
	
	@Override
	public int insert(ArticleLike articleLike) {
		//Hibernate
		getSession().persist(articleLike);
		return articleLike.getArticleLikeID();
				
//		String sql = "insert into ArticleLike values(?,?,?,?)";
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, articleLike.getArticleLikeID());
//			ps.setInt(2, articleLike.getArticleID());
//			ps.setInt(3, articleLike.getUserID());
//			ps.setString(4, articleLike.getArticleLikeStatus());
//			return ps.executeUpdate(); // 回傳影響0到n筆資料
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return -1;
	}
	
	@Override
	public int deleteByArticleLikeID(Integer articleLikeID) {
		//Hibernate
		ArticleLike articleLike = getSession().get(ArticleLike.class, articleLikeID);
		getSession().remove(articleLike);
		return articleLikeID;
		
//		String sql = "delete from ArticleLike where articleLikeID = ?";
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, articleLikeID);
//			return ps.executeUpdate(); // 回傳影響0到n筆資料
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return -1;
	}
	
	@Override
	public int updateByArticleLikeID(ArticleLike articleLike) {
		//Hibernate
		getSession().update(articleLike);
		return articleLike.getArticleID();
		
		
		// 後面沒逗號都可以刪掉\r\n
//		String sql = "UPDATE team6.ArticleLike\r\n"
//				+ "SET\r\n"
//				+ "articleLikeID = ?,"
//				+ "articleID = ?,"
//				+ "userID = ?,"
//				+ "articleLikeStatus = ?\r\n"
//				+ "WHERE articleLikeID = ?;"
//				;
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, articleLike.getArticleLikeID());
//			ps.setInt(2, articleLike.getArticleID());
//			ps.setInt(3, articleLike.getUserID());
//			ps.setString(4, articleLike.getArticleLikeStatus());
//			ps.setInt(5, articleLike.getArticleLikeID());
//			return ps.executeUpdate(); // 回傳影響0到n筆資料
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return -1;
	}
	
	@Override
	public ArticleLike selectByArticleLikeID(Integer articleLikeID) {
		//Hibernate
		ArticleLike articleLike = getSession().get(ArticleLike.class, articleLikeID);
		return articleLike;
			
//		String sql = "select * from ArticleLike where articleLikeID = ?";
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, articleLikeID);
//			try (ResultSet rs = ps.executeQuery();) { // 用rs保存它
//				// rs.next() 從0指到n資料，如果有資料會回傳true
//				if (rs.next()) { // 用if不用while是因為只有一筆資料，也可以用while
//					ArticleLike articleLike = new ArticleLike();
//					articleLike.setArticleLikeID(rs.getInt("articleLikeID"));
//					articleLike.setArticleID(rs.getInt("articleID"));
//					articleLike.setUserID(rs.getInt("userID"));
//					articleLike.setArticleLikeStatus(rs.getString("articleLikeStatus"));
//					
//					return articleLike; // 回傳一個物件
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null; // 失敗回傳null給我
	}
	
	@Override
	public ArticleLike selectByArticleID(Integer articleID) {
		//Hibernate
//		System.out.println(articleID);
		String hqlQuery = "FROM ArticleLike WHERE articleID = :articleID";
	    Query<ArticleLike> query = getSession().createQuery(hqlQuery, ArticleLike.class);
	    query.setParameter("articleID", articleID);
//	    System.out.println("执行的HQL查询语句: " + hqlQuery);
//	    System.out.println("查询参数 articleID: " + articleID);
	    return query.getSingleResult();
//	    ArticleLike articleLike = query.getSingleResult();
//	    return articleLike;
	}
	
	@Override
	public List<ArticleLike> selectAll() throws ClassNotFoundException {
		//Hibernate
		return getSession().createQuery("FROM ArticleLike", ArticleLike.class).list();
		
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		String sql = "select * from ArticleLike";
//		try (
//				
//				Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();
//				) {
//			List<ArticleLike> list = new ArrayList<ArticleLike>();
//
//			while (rs.next()) { // 查詢多筆資料，所以用while
//				ArticleLike articleLike = new ArticleLike();
//				articleLike.setArticleLikeID(rs.getInt("articleLikeID"));
//				articleLike.setArticleID(rs.getInt("articleID"));
//				articleLike.setUserID(rs.getInt("userID"));
//				articleLike.setArticleLikeStatus(rs.getString("articleLikeStatus"));
//				list.add(articleLike); // 把資料丟進陣列
//			}
//			return list; // 回傳一個陣列
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null;
	}
	
//	public static void main(String[] args) throws ClassNotFoundException {
//
//		SessionFactory sf=HibernateUtil.getSessionFactory();
//		Session session=sf.getCurrentSession();
//		Transaction tr=session.getTransaction();
//		ArticleLikeDao dao = new ArticleLikeDaoImpl();
//		try {
//			tr.begin();
//			
//			//新增
////			ArticleLike articleLike1 = new ArticleLike(2, 2, 2, "0");
////			dao.insert(articleLike1);
//			
//			//刪除ID
////			dao.deleteByArticleLikeID(4);
//			
//			//修改
////			ArticleLike articleLike2 = new ArticleLike(2, 2, 2, "1");
////			dao.updateByArticleLikeID(articleLike2);
//			
//			//查詢ID
//			System.out.println(dao.selectByArticleLikeID(2));
//			
//			//查詢全部
////			System.out.println(dao.selectAll());
//			
//			tr.commit();
//		} catch (Exception e) {
//			tr.rollback();
//			// TODO: handle exception
//		}
//		
//		
//		//------------------------------------------------------------------
//
////		ArticleDao dao = new ArticleDaoImpl();
//		// new byte[]{0, 1, 2, 3} 圖片
//		//新增
////		ArticleLike articleLike1 = new ArticleLike(2, 2, 2, "0");
////		dao.insert(articleLike1);
//		
//		//查詢ID
////		System.out.println(dao.selectByArticleLikeID(1));
//		
//		//查詢全部
////		for (ArticleLike articleLike : dao.selectAll()) {
////			System.out.println(articleLike.getArticleLikeID());
////		}
//		
//		//刪除
////		dao.deleteByArticleLikeID(2);
//		
//		//修改
////		ArticleLike articleLike2 = new ArticleLike(2, 2, 2, "1");
////		dao.updateByArticleLikeID(articleLike2);
//	}

}
