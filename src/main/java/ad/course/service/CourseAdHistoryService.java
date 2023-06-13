package ad.course.service;

import java.util.List;

import ad.course.vo.CourseAdHistory;

public interface CourseAdHistoryService {

	CourseAdHistory insert(Integer courseId);
	List<CourseAdHistory> select();

}