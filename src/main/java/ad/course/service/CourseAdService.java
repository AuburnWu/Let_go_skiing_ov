package ad.course.service;

import java.util.List;

import ad.course.vo.CourseAd;
import ad.course.vo.CourseDTO;
import course.entity.Course;

public interface CourseAdService {

//	public courseAd selectAd(Integer id);

	public CourseAd insertAd(Integer courseId);

	public int deleteAd(Integer id);

	public List<CourseDTO> getCourse();

	public List<Course> search(String keyword);

	List<CourseDTO> courseAdlist();
	
	CourseAd selectAd(Integer id);
	List<String> slider();
}
