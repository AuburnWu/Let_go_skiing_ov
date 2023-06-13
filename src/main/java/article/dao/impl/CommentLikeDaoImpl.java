package article.dao.impl;

import java.util.List;


import article.dao.CommentLikeDao;
import article.vo.CommentLike;

public class CommentLikeDaoImpl implements CommentLikeDao {
	
	@Override
	public int insert(CommentLike commentLike) {
		//Hibernate
		getSession().persist(commentLike);
		return commentLike.getCommentID();
		
//		String sql = "insert into CommentLike values(?,?,?,?)";
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, commentLike.getCommentLikeID());
//			ps.setInt(2, commentLike.getCommentID());
//			ps.setInt(3, commentLike.getUserID());
//			ps.setString(4, commentLike.getCommentLikeStatus());
//			
//			return ps.executeUpdate(); // 回傳影響0到n筆資料
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return -1;
	}
	
	@Override
	public int deleteByCommentLikeID(Integer commentLikeID) {
		//Hibernate
		CommentLike commentLike = getSession().get(CommentLike.class, commentLikeID);
		getSession().remove(commentLike);
		return commentLikeID;
		
//		String sql = "delete from CommentLike where commentLikeID = ?";
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, commentLikeID);
//			return ps.executeUpdate(); // 回傳影響0到n筆資料
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return -1;
	}
	
	@Override
	public int updateByCommentLikeID(CommentLike commentLike) {
		//Hibernate
		getSession().update(commentLike);
		return commentLike.getCommentLikeID();
		
		// 後面沒逗號都可以刪掉\r\n
//		String sql = "UPDATE team6.CommentLike\r\n"
//				+ "SET\r\n"
//				+ "commentLikeID = ?,"
//				+ "commentID = ?,"
//				+ "userID = ?,"
//				+ "commentLikeStatus = ?\r\n"
//				+ "WHERE commentLikeID = ?;"
//				;
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, commentLike.getCommentLikeID());
//			ps.setInt(2, commentLike.getCommentID());
//			ps.setInt(3, commentLike.getUserID());
//			ps.setString(4, commentLike.getCommentLikeStatus());
//			ps.setInt(5, commentLike.getCommentLikeID());
//			
//			return ps.executeUpdate(); // 回傳影響0到n筆資料
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return -1;
	}
	
	@Override
	public CommentLike selectByCommentLikeID(Integer commentLikeID) {
		//Hibernate
		CommentLike commentLike = getSession().get(CommentLike.class, commentLikeID);
		return commentLike;
		
//		String sql = "select * from CommentLike where commentLikeID = ?";
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, commentLikeID);
//			try (ResultSet rs = ps.executeQuery();) { // 用rs保存它
//				// rs.next() 從0指到n資料，如果有資料會回傳true
//				if (rs.next()) { // 用if不用while是因為只有一筆資料，也可以用while
//					CommentLike commentLike = new CommentLike();
//					commentLike.setCommentLikeID(rs.getInt("commentLikeID"));
//					commentLike.setCommentID(rs.getInt("commentID"));
//					commentLike.setUserID(rs.getInt("userID"));
//					commentLike.setCommentLikeStatus(rs.getString("commentLikeStatus"));
//					return commentLike; // 回傳一個物件
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null; // 失敗回傳null給我
	}
	
	@Override
	public List<CommentLike> selectAll() throws ClassNotFoundException {
		//Hibernate
		return getSession().createQuery("FROM CommentLike", CommentLike.class).list();
		
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		String sql = "select * from CommentLike";
//		try (
//				
//				Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();
//				) {
//			List<CommentLike> list = new ArrayList<CommentLike>();
//
//			while (rs.next()) { // 查詢多筆資料，所以用while
//				CommentLike commentLike = new CommentLike();
//				commentLike.setCommentLikeID(rs.getInt("commentLikeID"));
//				commentLike.setCommentID(rs.getInt("commentID"));
//				commentLike.setUserID(rs.getInt("userID"));
//				commentLike.setCommentLikeStatus(rs.getString("commentLikeStatus"));
//				list.add(commentLike); // 把資料丟進陣列
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
//		CommentLikeDao dao = new CommentLikeDaoImpl();
//		try {
//			tr.begin();
//			
//			//新增
////			CommentLike commentLike1 = new CommentLike(2, 2, 2, "0");
////			dao.insert(commentLike1);
//			
//			//刪除ID
////			dao.deleteByCommentLikeID(2);
//			
//			//修改
////			CommentLike commentLike2 = new CommentLike(2, 2, 2, "1");
////			dao.updateByCommentLikeID(commentLike2);
//			
//			//查詢ID
//			System.out.println(dao.selectByCommentLikeID(1));
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
//		
////		CommentLikeDao dao = new CommentLikeDaoImpl();
//
//		// new byte[]{0, 1, 2, 3} 圖片
//		//新增
////		CommentLike commentLike1 = new CommentLike(2, 2, 2, "0");
////		dao.insert(commentLike1);
//		
//		//查詢ID
////		System.out.println(dao.selectByCommentLikeID(1));
//		
//		//查詢全部
////		for (CommentLike commentLike : dao.selectAll()) {
////			System.out.println(commentLike.getCommentLikeID());
////		}
//		
//		//刪除
////		dao.deleteByCommentLikeID(2);
//		
//		//修改
////		CommentLike commentLike2 = new CommentLike(2, 2, 2, "1");
////		dao.updateByCommentLikeID(commentLike2);
//	}


}
