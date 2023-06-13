package member.service;

import java.io.IOException;
import java.util.List;

import member.vo.Member;

public interface MemberService {
	
	Member register(Member member);

	Member login(Member member);
	
	String genAuthCode();
	
	Member forgotPassword(Member member);
	
	void sendMail(String to, String subject, String messageText);
	
	String getHtmlContent(String filePath) throws IOException;

	Member infoChange(Member member);
	
	Member checkPassword(Member member);

	Member passwordChange(Member member);

	List<Member> findAll();

	Member findOne(Integer userID);
	
	Member settings(Member member);
	
}