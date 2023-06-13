package member.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import member.dao.CoachDao;
import member.vo.Coach;

public class CoachDaoImpl implements CoachDao {

	@Override
	public int insert(Coach coach) {
		try {
			getSession().persist(coach);
			return coach.getCoachID();
		} catch (Exception e) {
			System.out.println("insert方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int deleteById(Integer coachID) {
		try {
			Session session = getSession();
			Coach coach = session.get(Coach.class, coachID);
			session.remove(coach);
			return 1;
		} catch (Exception e) {
			System.out.println("deleteById方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public boolean updateById(Coach nCoach) {
		try {
			Session session = getSession();
			Coach oCoach = session.get(Coach.class, nCoach.getCoachID());

			final String skill = nCoach.getSkill();
			if (skill != null && !skill.isEmpty()) {
				oCoach.setSkill(skill);
			}
			final String background = nCoach.getBackground();
			if (background != null && !background.isEmpty()) {
				oCoach.setBackground(background);
			}
			final String introduce = nCoach.getIntroduce();
			if (introduce != null && !introduce.isEmpty()) {
				oCoach.setIntroduce(introduce);
			}
			final byte[] license = nCoach.getLicense();
			if (license != null && license.length > 0) {
				oCoach.setLicense(license);
			}
			final String applyStatus = nCoach.getApplyStatus();
			if (applyStatus != null && !applyStatus.isEmpty()) {
				oCoach.setApplyStatus(applyStatus);
			}
			return true;
		} catch (Exception e) {
			System.out.println("updateById方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Coach selectById(Integer coachID) {
		try {
			Session session = getSession();
			Coach coach = session.get(Coach.class, coachID);
			return coach;
		} catch (Exception e) {
			System.out.println("selectById方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Coach> selectAll() {
		final String hql = "FROM Coach ORDER BY coachID";
		try {
			Session session = getSession();
			Query<Coach> query = session.createQuery(hql, Coach.class); // import org.hibernate.query.Query;
			List<Coach> list = query.getResultList();
			return list;
		} catch (Exception e) {
			System.out.println("selectAll方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Coach selectByUserId(Integer userID) {
		final String sql = "SELECT * FROM Coach WHERE userID = :userID";
			Session session = getSession();
			NativeQuery<Coach> nativeQuery = session.createNativeQuery(sql, Coach.class);
			nativeQuery.setParameter("userID", userID);
			Coach coach = nativeQuery.uniqueResult();
			return coach;
	}

}
