package loc.dao;

import java.util.List;

import loc.model.SkiLocationHistory;

public class SkiLocHistDaoImpl implements SkiLocHistDao {

	// insert
	@Override
	public int insert(SkiLocationHistory skiLocationHistory) {

		//Hibernate
		getSession().persist(skiLocationHistory);
		return skiLocationHistory.getSkiLocationHistoryID();
	}

	// delete
	@Override
	public int deleteBySkiLocationHistoryID(Integer skiLocationHistoryID) {
		SkiLocationHistory skiLocationHistory = getSession().get(SkiLocationHistory.class,skiLocationHistoryID);
		getSession().remove(skiLocationHistory);
		return skiLocationHistoryID;
	}

	// update
	@Override
	public int updateBySkiLocationHistoryID(SkiLocationHistory skiLocationHistory) {
		getSession().update(skiLocationHistory);
		return skiLocationHistory.getSkiLocationHistoryID();
	}

	// select
	public SkiLocationHistory selectBySkiLocationHistoryID(Integer skiLocationHistoryID) {
		SkiLocationHistory skiLocationHistory = getSession().get(SkiLocationHistory.class, skiLocationHistoryID);
		return skiLocationHistory;
	}

	// select All
	@Override
	public List<SkiLocationHistory> selectAll() {
		return getSession().createQuery("FROM SkiLocationHistory", SkiLocationHistory.class).list();
	}
	
	//test
//		public static void main(String[] args) {
//			//Hibernate
//			SessionFactory sf = HibernateUtil.getSessionFactory();
//			Session session = sf.getCurrentSession();
//			Transaction tr = session.beginTransaction();
//			SkiLocHistDaoImpl a = new SkiLocHistDaoImpl();
//			SkiLocationHistory b = new SkiLocationHistory(null, 8, 3);
//			SkiLocationHistory c = new SkiLocationHistory(10, 8, 3);
////			a.insert(b);
////			a.deleteBySkiLocationHistoryID(9);
////			a.updateBySkiLocationHistoryID(c);
//			a.selectBySkiLocationHistoryID(10);
//			System.out.println(a.selectBySkiLocationHistoryID(10));
////			a.selectAll();
////			System.out.println(a.selectAll());
//			tr.commit();
			
			
			
			
			
			
			
//			SkiLocHistDao dao = new SkiLocHistDaoImpl();
//			//insert
//			SkiLocationHistory ski1 = new SkiLocationHistory(10, 20, 30);
//			dao.insert(ski1);
			
//			//select all
//			for (SkiLocHistVo skilochistvo : dao.selectAll()){
//				System.out.println(skilochistvo.getCourseID());
//			}
			
			//select
//			System.out.println(dao.selectBySkiLocationHistoryID(10));
			
			//update
//			SkiLocHistVo ski2 = new SkiLocHistVo(10, 20, 30 );
//			System.out.println(dao.updateBySkiLocationHistoryID(ski2));
			
			//delete
//			System.out.println(dao.deleteBySkiLocationHistoryID(10));

//		}

}
