package member.service.impl;

import java.util.List;

import member.dao.CoachDao;
import member.dao.impl.CoachDaoImpl;
import member.service.CoachService;
import member.vo.Coach;

public class CoachServiceImpl implements CoachService {

	private CoachDao dao; // private CoachDao dao = new CoachDaoImpl();

	public CoachServiceImpl() {
		dao = new CoachDaoImpl();
	}

	@Override
	public Coach coachInfo(Integer userID) {
		return dao.selectByUserId(userID);
	}

	@Override
	public Coach apply(Coach coach) {
		if (dao.selectByUserId(coach.getUserID()) != null) {
			coach.setMessage("您已申請過，等待審核中");
			coach.setSuccessful(false);
			return coach;
		}
		if ("請選擇".equals(coach.getSkill())) {
			coach.setMessage("請選擇技能");
			coach.setSuccessful(false);
			return coach;
		}
		if (coach.getBackground().trim() == null || coach.getBackground().trim().isEmpty()) {
			coach.setMessage("請輸入個人背景、經歷");
			coach.setSuccessful(false);
			return coach;
		}
		if (coach.getIntroduce().trim() == null || coach.getIntroduce().trim().isEmpty()) {
			coach.setMessage("請輸入自我介紹");
			coach.setSuccessful(false);
			return coach;
		}
		final int resultCount = dao.insert(coach);
		if (resultCount < 1) {
			coach.setMessage("申請失敗，請聯絡管理員!");
			coach.setSuccessful(false);
			return coach;
		}
		coach.setMessage("申請成功，待管理員審核");
		coach.setSuccessful(true);
		return coach;
	}

	@Override
	public List<Coach> findAll() {
		return dao.selectAll();
	}
	
	@Override
	public Coach findOne(Integer userID) {
		return dao.selectById(userID);
	}
	
	@Override
	public Coach statusChange(Coach coach) {
		Boolean isSuccess = dao.updateById(coach);

		if (isSuccess == false) {
			coach.setMessage("審核失敗，請重新審核");
			coach.setSuccessful(false);
			return coach;
		}

		coach.setMessage("審核成功");
		coach.setSuccessful(true);
		return coach;
	}

}
