package course.dao.impl;

import java.util.List;

//import org.hibernate.Session;
import org.hibernate.query.Query;

import course.dao.CourseDao;
import course.entity.Course;
//import product.vo.Product;



	    public class CourseDaoImpl implements CourseDao {
	    	
	        @Override
	    	public int insertCourse(Course course) {
	        	getSession().persist(course);
	        	return 1;
	    	}
	              
	        @Override
	    	public Course getCourseByCourseId(Integer courseId) {
	        	return getSession().get(Course.class, courseId);
	    	}
	        
	        @Override
	    	public List<Course> getAllCourses() {
		        	String hql = "FROM Course";
		        	Query<Course> query = getSession().createQuery(hql, Course.class);
		        	List<Course> resultList = query.getResultList();		        	
		        	return resultList; 
	    	}
	        
	    	@Override
	        public int updateCourse(Course course) {
	    		getSession().merge(course);
	    		return 1;
	    	}
	           
	        @Override
	        public int deleteByCourseId(Integer courseId) {
	        	Course course = getSession().get(Course.class, courseId);
	    		try {
	    			getSession().remove(course);
	    			return 1;
				} catch (Exception e) {				
					e.printStackTrace();
			        System.out.println("deleteByCourseId：" + e.getMessage());
			        return 0;
				}        	
	        }

			@Override
			public List<Course> getCourseByKeywordAndTag(String Keyword, Integer skill, Integer level) {
				String hql = "FROM Course WHERE courseStatus = 0";
				
				Boolean input = false;
				if ( Keyword != null || !Keyword.isEmpty()) {
			        hql += " AND courseName LIKE :courseName ";
			    }
				
				if (skill >= 0) {
			        hql += " AND skill = :skill";
			    }

			    if (level >= 0) {
			        hql += " AND level = :level";
			    }
			    
	        	Query<Course> query = getSession().createQuery(hql, Course.class);
	   
	        	if ( Keyword != null || !Keyword.isEmpty()) {
	        		query.setParameter("courseName", "%" + Keyword + "%");
			    }
				
				if (skill >= 0) {
					query.setParameter("skill", skill);
			    }

			    if (level >= 0) {
			    	query.setParameter("level", level);
			    }
	        	List<Course> resultList = query.getResultList();		        	
	        	return resultList; 	        		    
			}

			
			@Override
			public List<Course> getCourseByKeyword(String Keyword){
				String hql = "FROM Course WHERE courseName LIKE :courseName";
			    
				Query<Course> query = getSession().createQuery(hql, Course.class);
				query.setParameter("courseName", "%" + Keyword + "%");	     
	        	List<Course> resultList = query.getResultList();		        	
	        	return resultList; 	        	
			}
			
			@Override
			public List<Course> getRuningCourse(){
				String hql = "FROM Course WHERE courseStatus = 0 ";			    
				Query<Course> query = getSession().createQuery(hql, Course.class);   
	        	List<Course> resultList = query.getResultList();		        	
	        	return resultList; 	
			}

			@Override
			public List<Course> GetCoachCourse(Integer coachID) {
				String hql = "FROM Course WHERE coachID = :coachID ";			    
				Query<Course> query = getSession().createQuery(hql, Course.class);   
				query.setParameter("coachID",  coachID  );	
	        	List<Course> resultList = query.getResultList();		        	
	        	return resultList; 	
			}

			

	        
	    }

			
	    


