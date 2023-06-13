package ad.course.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.query.Query;

import ad.course.dao.CourseAdDao;
import ad.course.vo.CourseAd;
import ad.course.vo.CourseDTO;
import course.entity.Course;

public class CourseAdDaoImpl implements CourseAdDao {
//	getSession() getSession() = HibernateUtil.getgetSession()Factory().getCurrentgetSession()();

	@Override
	public int insert(CourseAd courseAd) {
		getSession().persist(courseAd);
		return -1;
	}

	@Override
	public Course getCourseByID(Integer courseID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Integer id) {
		System.out.println(id);
		CourseAd cAd = getSession().get(CourseAd.class,id);
//		String hql = "FROM CourseAd WHERE courseAD = :id";
//		CourseAd cAd = getSession().createQuery(hql,CourseAd.class).setParameter("id", id).uniqueResult();
		
		System.out.println(cAd);
		getSession().remove(cAd);
		return -1;
	}

	// ???
	@Override
	public int update(CourseAd courseAd) {
		getSession().update(courseAd);
		return 1;
	}

	@Override
	public CourseAd selectAd(Integer id) {
		System.out.println(id);
		return getSession().get(CourseAd.class,id);
	}

	@Override
	public List<CourseAd> selectAll() {
		return getSession().createQuery("FROM CourseAd", CourseAd.class).list();
	}

	public List<Course> selectAllCourses() {
		return getSession().createQuery("FROM Course", Course.class).list();
	}

	public List<Course> searchCourses(String keyword) {
		String hql = "SELECT c FROM Course c "
				+ "WHERE c.courseID NOT IN (SELECT cAd.courseID FROM CourseAd cAd) "
				+ "AND c.startDate <= CURRENT_TIMESTAMP() "
				+ "AND c.endDate > CURRENT_TIMESTAMP()"		
				+ "AND "
				+ "(level = :keyint "
				+ "OR skill = :keyint "				
				+ "OR courseName LIKE :keyword)";

		Query<Course> query = getSession().createQuery(hql, Course.class);
		Integer keyint = Integer.parseInt(keyword);
		query.setParameter("keyword", "%" + keyword + "%");
		query.setParameter("keyint", keyint);
		return query.getResultList();

	}

	public List<CourseDTO> selectActiveCourse() {
		String hql = "SELECT c FROM Course c "
				+ "WHERE c.courseID NOT IN (SELECT cAd.courseID FROM CourseAd cAd) "
				+ "AND c.startDate <= CURRENT_TIMESTAMP() "
				+ "AND c.endDate > CURRENT_TIMESTAMP()";		
	Query query = getSession().createQuery(hql);
	List<CourseDTO> results = query.list();

		return results;
	}

	public Course getInfoByID(Integer courseID) {
		String hql = "SELECT courseID, courseName, startDate, endDate\r\n" + "FROM Course\r\n"
				+ "WHERE coachIDz = :courseID";
		Query<Course> query = getSession().createQuery(hql, Course.class);
		query.setParameter("courseID", courseID);
		return query.getSingleResult();
	}

	
	//廣告中商品
	public List<byte[]> random() {
		String hql = "SELECT c.coursePhoto FROM Course c WHERE c.courseID IN (SELECT ad.courseID FROM Course ad)";
		List<byte[]> blobList = getSession().createQuery(hql, byte[].class).list();
		

	    Collections.shuffle(blobList);
	    List<byte[]> randomProducts = blobList.subList(0, Math.min(blobList.size(), 3));
//	    System.out.println(randomProducts);
	    return randomProducts;

		
	}



}
