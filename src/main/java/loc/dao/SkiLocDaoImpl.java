package loc.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import loc.model.SkiLocation;
import member.vo.Member;

public class SkiLocDaoImpl implements SkiLocDao {

	//insert	
	@Override
	public int insert(SkiLocation skiLocation) {
		//Hibernate
		getSession().persist(skiLocation);
		return skiLocation.getPointID();
	}

	// delete
	@Override
	public int deleteByPointID(Integer pointID) {
		//Hibernate
//		Session session = getSession();
		SkiLocation skiLocation = getSession().get(SkiLocation.class, pointID);
		getSession().remove(skiLocation);
		return pointID;
	}

	// update
//	@Override
//	public boolean updateByPointID(SkiLocation skiLocation) {
//		//Hibernate
//		Session session = getSession();
//		 SkiLocation existingSkiLocation = session.get(SkiLocation.class, skiLocation.getPointID());
//		final String status = skiLocation.getPointStatus();
//		if(status != null && !status.isEmpty()) {
//			existingSkiLocation.setPointStatus(status); //更新已存在的對象的狀態
//
//		}
//		session.update(skiLocation); //將更新後數據保存到資料庫中
//		return true;
//
//	}
	@Override
	public boolean updateByPointID(SkiLocation skiLocation) {
	    Session session = getSession();
	    SkiLocation existingSkiLocation = session.get(SkiLocation.class, skiLocation.getPointID());
	    if (existingSkiLocation != null) {
	        existingSkiLocation.setPointStatus(skiLocation.getPointStatus());
	        session.update(existingSkiLocation);
	        return true;
	    }
	    return false;
	}
	
	public boolean updateImageByPointID(SkiLocation skiLocation) {
	    Session session = getSession();
	    SkiLocation existingSkiLocation = session.get(SkiLocation.class, skiLocation.getPointID());
	    if (existingSkiLocation != null) {
	        existingSkiLocation.setPointPicture(skiLocation.getPointPicture());
	        session.update(existingSkiLocation);
	        return true;
	    }
	    return false;
	}




	
	// select
	@Override
	public SkiLocation selectByPointID(Integer pointID) {
		//Hibernate
		SkiLocation skiLocation;
		try {
			skiLocation = getSession().get(SkiLocation.class, pointID);
			return skiLocation;
		} catch (Exception e) {
			System.out.println("selectByPointID錯誤");
			e.printStackTrace();
		}
		return null;
	}

	// select all
	@Override
	public List<SkiLocation> selectAll() {
		//Hibernate
		return getSession().createQuery("FROM SkiLocation", SkiLocation.class).list();
	}
	
}
