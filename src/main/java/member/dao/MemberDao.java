package member.dao;

import java.util.List;

import core.CoreDao;
import member.vo.Member;

public interface MemberDao extends CoreDao {
	int insert(Member member);

	int deleteById(Integer userID);

	boolean updateById(Member member);

	Member selectById(Integer userID);

	List<Member> selectAll();

	Member selectForLogin(String email, String password); 

	Member selectByEmail(String email);
	
}