package article.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import article.dao.CommentDao;
import article.vo.ArticleType;
import article.vo.Comment;
import core.util.HibernateUtil;

public class CommentDaoImpl implements CommentDao {

	@Override
	public int insert(Comment comment) {
		// Hibernate
		getSession().persist(comment);
		return comment.getCommentID();

//		String sql = "insert into Comment values(?,?,?,?,?,?,?)";
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, comment.getCommentID());
//			ps.setInt(2, comment.getArticleID());
//			ps.setInt(3, comment.getUserID());
//			ps.setString(4, comment.getCommentContent());
//			ps.setTimestamp(5, comment.getCommentDateTime());
//			ps.setTimestamp(6, comment.getCommentModified());
//			ps.setInt(7, comment.getCommentLike());
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
	public int deleteByCommentID(Integer commentID) {
		// Hibernate
		Comment comment = getSession().get(Comment.class, commentID);
		getSession().remove(comment);
		return commentID;

//		String sql = "delete from Comment where commentID = ?";
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, commentID);
//			return ps.executeUpdate(); // 回傳影響0到n筆資料
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return -1;
	}

	@Override
	public int updateByCommentID(Comment comment) {
		// Hibernate
		getSession().update(comment);
		return comment.getArticleID();

		// 後面沒逗號都可以刪掉\r\n
//		String sql = "UPDATE team6.Comment\r\n"
//				+ "SET\r\n"
//				+ "commentID = ?,"
//				+ "articleID = ?,"
//				+ "userID = ?,"
//				+ "commentContent = ?,"
//				+ "commentDateTime = ?,"
//				+ "commentModified = ?,"
//				+ "commentLike = ?\r\n"
//				+ "WHERE commentID = ?;"
//				;
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, comment.getCommentID());
//			ps.setInt(2, comment.getArticleID());
//			ps.setInt(3, comment.getUserID());
//			ps.setString(4, comment.getCommentContent());
//			ps.setTimestamp(5, comment.getCommentDateTime());
//			ps.setTimestamp(6, comment.getCommentModified());
//			ps.setInt(7, comment.getCommentLike());
//			ps.setInt(8, comment.getCommentID());
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
	public Comment selectByCommentID(Integer commentID) {
		// Hibernate
		Comment comment = getSession().get(Comment.class, commentID);
		return comment;

//		String sql = "select * from Comment where commentID = ?";
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, commentID);
//			try (ResultSet rs = ps.executeQuery();) { // 用rs保存它
//				// rs.next() 從0指到n資料，如果有資料會回傳true
//				if (rs.next()) { // 用if不用while是因為只有一筆資料，也可以用while
//					Comment comment = new Comment();
//					comment.setCommentID(rs.getInt("commentID"));
//					comment.setArticleID(rs.getInt("articleID"));
//					comment.setUserID(rs.getInt("userID"));
//					comment.setCommentDateTime(rs.getTimestamp("commentDateTime"));
//					comment.setCommentModified(rs.getTimestamp("commentModified"));
//					comment.setCommentLike(rs.getInt("commentLike"));
//					return comment; // 回傳一個物件
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null; // 失敗回傳null給我
	}

	@Override
	public List<Comment> selectByComment(Integer articleID) {
		String hqlQuery = "FROM Comment WHERE articleID LIKE :articleID";
		Query<Comment> query = getSession().createQuery(hqlQuery, Comment.class);
		query.setParameter("articleID", articleID);
		List<Comment> list = query.getResultList();
		return list;
	}

	@Override
	public List<Comment> selectAll() throws ClassNotFoundException {
		// Hibernate
		return getSession().createQuery("FROM Comment", Comment.class).list();

//		Class.forName("com.mysql.cj.jdbc.Driver");
//		String sql = "select * from Comment";
//		try (
//				
//				Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();
//				) {
//			List<Comment> list = new ArrayList<Comment>();
//
//			while (rs.next()) { // 查詢多筆資料，所以用while
//				Comment comment = new Comment();
//				comment.setCommentID(rs.getInt("commentID"));
//				comment.setArticleID(rs.getInt("articleID"));
//				comment.setUserID(rs.getInt("userID"));
//				comment.setCommentDateTime(rs.getTimestamp("commentDateTime"));
//				comment.setCommentModified(rs.getTimestamp("commentModified"));
//				comment.setCommentLike(rs.getInt("commentLike"));
//				list.add(comment); // 把資料丟進陣列
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
//		CommentDao dao = new CommentDaoImpl();
//		try {
//			tr.begin();
//			
//			//新增
//			SimpleDateFormat df = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss");
//			Timestamp tsDateTime = Timestamp.valueOf("2023-04-30 00:00:00");
//			Timestamp tsModified = Timestamp.valueOf("2023-04-30 00:00:00");
//			String sDateTime = df.format(tsDateTime);
//			String sModified = df.format(tsModified);
//			Comment comment1 = new Comment(2, 2, 2, "c", sDateTime, sModified, 4);
//			dao.insert(comment1);
//			
//			//刪除ID
////			dao.deleteByCommentID(2);
//			
//			//修改
////			Comment comment2 = new Comment(2, 2, 2, "aaa", Timestamp.valueOf("2023-04-30 00:00:00"), Timestamp.valueOf("2023-04-30 00:00:00"), 4);
////			dao.updateByCommentID(comment2);
//			
//			//查詢ID
////			System.out.println(dao.selectByCommentID(1));
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
//		
////		CommentDao dao = new CommentDaoImpl();
//		// new byte[]{0, 1, 2, 3} 圖片
//		//新增
////		Comment comment1 = new Comment(2, 2, 2, "c", Timestamp.valueOf("2023-04-30 00:00:00"), Timestamp.valueOf("2023-04-30 00:00:00"), 4);
////		dao.insert(comment1);
//		
//		//查詢ID
////		System.out.println(dao.selectByCommentID(1));
//		
//		//查詢全部
////		for (Comment comment : dao.selectAll()) {
////			System.out.println(comment.getCommentID());
////		}
//		
//		//刪除
////		dao.deleteByCommentID(2);
//		
//		//修改
////		Comment comment2 = new Comment(2, 2, 2, "aaa", Timestamp.valueOf("2023-04-30 00:00:00"), Timestamp.valueOf("2023-04-30 00:00:00"), 4);
////		dao.updateByCommentID(comment2);
//	}

}
