package member.dao;

import java.util.List;

import core.CoreDao;
import member.vo.Coach;

public interface CoachDao extends CoreDao {
	int insert(Coach coach);

	int deleteById(Integer coachID);

	boolean updateById(Coach coach);

	Coach selectById(Integer coachID);

	List<Coach> selectAll();

	Coach selectByUserId(Integer userID);

}