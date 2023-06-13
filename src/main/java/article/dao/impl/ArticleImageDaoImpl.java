package article.dao.impl;

import java.util.List;


import article.dao.ArticleImageDao;
import article.vo.ArticleImage;

public class ArticleImageDaoImpl implements ArticleImageDao{
	
	@Override
	public int insert(ArticleImage articleImage) {
		//Hibernate
		getSession().persist(articleImage);
		return articleImage.getArticleImageID();
		
//		String sql = "insert into ArticleImage values(?,?,?)";
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, articleImage.getArticleImageID());
//			ps.setInt(2, articleImage.getArticleID());
//			ps.setBytes(3, articleImage.getImage());
//			return ps.executeUpdate(); // 回傳影響0到n筆資料
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return -1;
	}
	
	@Override
	public int deleteByArticleImageID(Integer articleImageID) {
		//Hibernate
		ArticleImage articleImage = getSession().get(ArticleImage.class, articleImageID);
	    getSession().remove(articleImage);
	    return articleImageID;
		
//		String sql = "delete from ArticleImage where articleImageID = ?";
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, articleImageID);
//			return ps.executeUpdate(); // 回傳影響0到n筆資料
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return -1;
	}
	
	@Override
	public int updateByArticleImageID(ArticleImage articleImage) {
		//Hibernate
		getSession().update(articleImage);
	    return articleImage.getArticleID();
		
		// 後面沒逗號都可以刪掉\r\n
//		String sql = "UPDATE team6.ArticleImage\r\n"
//				+ "SET\r\n"
//				+ "articleImageID = ?,"
//				+ "articleID = ?,"
//				+ "image = ?\r\n"
//				+ "WHERE articleImageID = ?;"
//				;
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, articleImage.getArticleImageID());
//			ps.setInt(2, articleImage.getArticleID());
//			ps.setObject(3, articleImage.getImage());
//			ps.setInt(4, articleImage.getArticleImageID());
//			return ps.executeUpdate(); // 回傳影響0到n筆資料
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return -1;
	}
	
	@Override
	public ArticleImage selectByArticleImageID(Integer articleImageID) {
		//Hibernate
		ArticleImage articleImage = getSession().get(ArticleImage.class, articleImageID);
	    return articleImage;
		
//		String sql = "select * from ArticleImage where articleImageID = ?";
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, articleImageID);
//			try (ResultSet rs = ps.executeQuery();) { // 用rs保存它
//				// rs.next() 從0指到n資料，如果有資料會回傳true
//				if (rs.next()) { // 用if不用while是因為只有一筆資料，也可以用while
//					ArticleImage articleImage = new ArticleImage();
//					articleImage.setArticleImageID(rs.getInt("articleImageID"));
//					articleImage.setArticleID(rs.getInt("articleID"));
//					articleImage.setImage(rs.getBytes("image"));
//					return articleImage; // 回傳一個物件
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null; // 失敗回傳null給我
	}
	
	@Override
	public List<ArticleImage> selectAll() throws ClassNotFoundException {
		//Hibernate
		return getSession().createQuery("FROM ArticleImage", ArticleImage.class).list();
		
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		String sql = "select * from ArticleImage";
//		try (
//				
//				Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();
//				) {
//			List<ArticleImage> list = new ArrayList<ArticleImage>();
//
//			while (rs.next()) { // 查詢多筆資料，所以用while
//				ArticleImage articleImage = new ArticleImage();
//				articleImage.setArticleImageID(rs.getInt("articleImageID"));
//				articleImage.setArticleID(rs.getInt("articleID"));
//				articleImage.setImage(rs.getBytes("image"));
//				list.add(articleImage); // 把資料丟進陣列
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
//		byte[] img = new byte[]{0, 1, 2, 3};
//		SessionFactory sf=HibernateUtil.getSessionFactory();
//		Session session=sf.getCurrentSession();
//		Transaction tr=session.getTransaction();
//		ArticleImageDao dao = new ArticleImageDaoImpl();
//		try {
//			tr.begin();
//			
//			//新增
////			ArticleImage articleImage1 = new ArticleImage(null, 2, img);
////			dao.insert(articleImage1);
//			
//			//刪除ID
////			dao.deleteByArticleImageID(4);
//			
//			//修改
////			ArticleImage articleImage2 = new ArticleImage(2, 3, img);
////			dao.updateByArticleImageID(articleImage2);
//			
//			//查詢ID
//			System.out.println(dao.selectByArticleImageID(2));
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
//		//新增
//		
////		ArticleDao dao = new ArticleDaoImpl();
////		ArticleImage articleImage1 = new ArticleImage(2, 2, img);
////		dao.insert(articleImage1);
//		
//		//查詢ID
////		System.out.println(dao.selectByArticleImageID(1));
//		
//		//查詢全部
////		for (ArticleImage articleImage : dao.selectAll()) {
////			System.out.println(articleImage.getArticleImageID());
////		}
//		
//		//刪除
////		dao.deleteByArticleImageID(2);
//		
//		//修改
////		ArticleImage articleImage2 = new ArticleImage(2, 3, img);
////		dao.updateByArticleImageID(articleImage2);
//	}
	
	

}
