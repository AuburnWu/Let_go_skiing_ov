package article.dao.impl;

import java.util.List;


import article.dao.ReportDao;
import article.vo.Report;


public class ReportDaoImpl implements ReportDao {

	@Override
	public int insert(Report report) {
		//Hibernate
		getSession().persist(report);
		return report.getReportID();
		
//		String sql = "insert into Report values(?,?,?,?,?,?)";
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, report.getReportID());
//			ps.setInt(2, report.getArticleID());
//			ps.setInt(3, report.getUserID());
//			ps.setString(4, report.getReportContent());
//			ps.setString(5, report.getReportStatus());
//			ps.setString(6, report.getReportResponse());
//			return ps.executeUpdate(); // 回傳影響0到n筆資料
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return -1;
	}
	
	@Override
	public int deleteByReportID(Integer reportID) {
		//Hibernate
		Report report = getSession().get(Report.class, reportID);
		getSession().remove(report);
		return reportID;
		
//		String sql = "delete from Report where reportID = ?";
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, reportID);
//			return ps.executeUpdate(); // 回傳影響0到n筆資料
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return -1;
	}
	
	@Override
	public int updateByReportID(Report report) {
		//Hibernate
		getSession().update(report);
		return report.getReportID();
		
		// 後面沒逗號都可以刪掉\r\n
//		String sql = "UPDATE team6.Report\r\n"
//				+ "SET\r\n"
//				+ "reportID = ?,"
//				+ "articleID = ?,"
//				+ "userID = ?,"
//				+ "reportContent = ?,"
//				+ "reportStatus = ?,"
//				+ "reportResponse = ?\r\n"
//				+ "WHERE reportID = ?;"
//				;
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, report.getReportID());
//			ps.setInt(2, report.getArticleID());
//			ps.setInt(3, report.getUserID());
//			ps.setString(4, report.getReportContent());
//			ps.setString(5, report.getReportStatus());
//			ps.setString(6, report.getReportResponse());
//			ps.setInt(7, report.getReportID());
//			return ps.executeUpdate(); // 回傳影響0到n筆資料
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return -1;
	}
	
	@Override
	public Report selectByReportID(Integer reportID) {
		//Hibernate
		Report report = getSession().get(Report.class, reportID);
		return report;
		
//		String sql = "select * from Report where reportID = ?";
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			ps.setInt(1, reportID);
//			try (ResultSet rs = ps.executeQuery();) { // 用rs保存它
//				// rs.next() 從0指到n資料，如果有資料會回傳true
//				if (rs.next()) { // 用if不用while是因為只有一筆資料，也可以用while
//					Report report = new Report();
//					report.setReportID(rs.getInt("reportID"));
//					report.setArticleID(rs.getInt("articleID"));
//					report.setUserID(rs.getInt("userID"));
//					report.setReportContent(rs.getString("reportContent"));
//					report.setReportStatus(rs.getString("reportStatus"));
//					report.setReportResponse(rs.getString("reportResponse"));
//					
//					return report; // 回傳一個物件
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null; // 失敗回傳null給我
	}
	
	@Override
	public List<Report> selectAll() throws ClassNotFoundException {
		//Hibernate
		return getSession().createQuery("FROM Report", Report.class).list();
		
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		String sql = "select * from Report";
//		try (
//				
//				Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
//				PreparedStatement ps = conn.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();
//				) {
//			List<Report> list = new ArrayList<Report>();
//
//			while (rs.next()) { // 查詢多筆資料，所以用while
//				Report report = new Report();
//				report.setReportID(rs.getInt("reportID"));
//				report.setArticleID(rs.getInt("articleID"));
//				report.setUserID(rs.getInt("userID"));
//				report.setReportContent(rs.getString("reportContent"));
//				report.setReportStatus(rs.getString("reportStatus"));
//				report.setReportResponse(rs.getString("reportResponse"));
//				
//				list.add(report); // 把資料丟進陣列
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
//		ReportDao dao = new ReportDaoImpl();
//		try {
//			tr.begin();
//			
//			//新增
////			Report report1 = new Report(2, 2, 2, "def", "1", "0");
////			dao.insert(report1);
//			
//			//刪除ID
////			dao.deleteByReportID(4);
//			
//			//修改
////			Report report2 = new Report(3, 3, 3, "aaa", "0", "1");
////			dao.updateByReportID(report2);
//			
//			//查詢ID
//			System.out.println(dao.selectByReportID(3));
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
////		ReportDao dao = new ReportDaoImpl();
//
//		// new byte[]{0, 1, 2, 3} 圖片
//		//新增
////		Report report1 = new Report(2, 2, 2, "def", "1", "0");
////		dao.insert(report1);
//		
//		//查詢ID
////		System.out.println(dao.selectByReportID(3));
//		
//		//查詢全部
////		for (Report report : dao.selectAll()) {
////			System.out.println(report.getReportID());
////		}
//		
//		//刪除
////		dao.deleteByReportID(4);
//		
//		//修改
////		Report report2 = new Report(3, 3, 3, "aaa", "0", "1");
////		dao.updateByReportID(report2);
//	}
}
