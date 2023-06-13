package course.dao;

import java.util.List;

import org.hibernate.Session;

import core.CoreDao;
import course.core.util.HibernateUtil;
import course.entity.Course;

public interface CourseDao extends CoreDao{
//	default Session getSession() {
//		return HibernateUtil.getSessionFactory().getCurrentSession();
//	}
	
	//事後可能與update透過 merge()做整合
	int insertCourse(Course course) ;
	
    Course getCourseByCourseId(Integer courseId) ;
    
    List<Course> getCourseByKeywordAndTag(String Keyword, Integer skill, Integer level) ;
    
    List<Course> getCourseByKeyword(String Keyword) ;

    List<Course> getRuningCourse();
    
    List<Course> GetCoachCourse(Integer coachID);
    
    List<Course> getAllCourses();
    
    int updateCourse(Course course) ;
    
//    int updateCourseStatus(Integer courseId) ;
    
    int deleteByCourseId(Integer courseId) ;
}
