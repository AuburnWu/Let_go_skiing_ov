package member.service;

import java.util.List;

import member.vo.Coach;

public interface CoachService {
	
	Coach coachInfo(Integer userID);

	Coach apply(Coach coach);

	List<Coach> findAll();

	Coach findOne(Integer userID);

	Coach statusChange(Coach coach);

}