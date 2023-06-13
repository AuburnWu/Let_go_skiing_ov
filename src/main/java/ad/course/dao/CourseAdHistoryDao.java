package ad.course.dao;

import java.util.List;

import ad.course.vo.CourseAdHistory;
import core.CoreDao;

public interface CourseAdHistoryDao extends CoreDao {

	int insert(CourseAdHistory courseAdHistory);

	int delete(Integer id);

	int update(CourseAdHistory nCourseAdHistory);

	CourseAdHistory selectById(Integer id);

	List<CourseAdHistory> selectAll();

}