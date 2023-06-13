package member.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import member.dao.MemberDao;
import member.dao.impl.MemberDaoImpl;
import member.service.MemberService;
import member.vo.Member;

public class MemberServiceImpl implements MemberService {

	private MemberDao dao; // private MemberDao dao = new MemberDaoImpl();

	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
	}

	@Override
	public Member register(Member member) {
		final String email = member.getEmail();
		String emailPatt = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

		if (!(email.matches(emailPatt))) {
			member.setMessage("信箱格式不正確，請重新輸入");
			member.setSuccessful(false);
			return member;
		}

		if (dao.selectByEmail(email) != null) {
			member.setMessage("帳號重複，請重新輸入");
			member.setSuccessful(false);
			return member;
		}

		final int resultCount = dao.insert(member);
		if (resultCount < 1) {
			member.setMessage("註冊失敗，請聯絡管理員!");
			member.setSuccessful(false);
			return member;
		}
		member.setMessage("註冊成功");
		member.setSuccessful(true);
		return member;
	}

	@Override
	public Member login(Member member) {
		final String email = member.getEmail();
		final String password = member.getPassword();

		member = dao.selectForLogin(email, password);

		if (email == null || password == null) {
			member.setMessage("帳號或密碼未輸入");
			member.setSuccessful(false);
			return member;
		}
		if (member == null) {
			member = new Member();
			member.setMessage("帳號或密碼錯誤，請重新輸入");
			member.setSuccessful(false);
			return member;
		}
		member.setMessage("登入成功");
		member.setSuccessful(true);
		return member;
	}

	@Override
	public String genAuthCode() {
		char[] code = new char[8];
		for (int i = 0; i < code.length; i++) {
			int ascii = (int) (Math.random() * 75) + 48;
			if ((ascii >= 48 && ascii <= 57) || (ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122)) {
				code[i] = (char) ascii;
			} else {
				i--;
			}
		}
		return new String(code);
	}

	@Override
	public Member forgotPassword(Member member) {
		final String email = member.getEmail();
		return member = dao.selectByEmail(email);
	}

	@Override
	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String subject, String messageText) {
		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			final String myGmail = "gogo97930@gmail.com";
			final String myGmail_password = "ktwxcjqiatvaurio";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			// 設定信中的內容
			message.setText(messageText);

			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}

	@Override
	public String getHtmlContent(String filePath) throws IOException {
		StringBuilder contentBuilder = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				contentBuilder.append(line);
			}
		}

		return contentBuilder.toString();
	}

	@Override
	public Member infoChange(Member member) {
		Boolean isSuccess = dao.updateById(member);

		if (isSuccess == false) {
			member.setMessage("資訊變更失敗，請重新輸入");
			member.setSuccessful(false);
			return member;
		}

		member.setMessage("資訊變更成功");
		member.setSuccessful(true);
		return member;
	}

	@Override
	public Member checkPassword(Member member) {
		final Integer userID = member.getUserID();
		final String passwordInput = member.getPassword();

		String password = dao.selectById(userID).getPassword();

		if (!(password.equals(passwordInput))) {
			member.setMessage("原密碼輸入不正確");
			member.setSuccessful(false);
			return member;
		}

		member.setMessage("原密碼輸入正確");
		member.setSuccessful(true);
		return member;
	}

	@Override
	public Member passwordChange(Member member) {
		Boolean isSuccess = dao.updateById(member);

		if (isSuccess == false) {
			member.setMessage("密碼變更失敗，請重新輸入");
			member.setSuccessful(false);
			return member;
		}

		member.setMessage("密碼變更成功，請重新登入");
		member.setSuccessful(true);
		return member;
	}

	@Override
	public List<Member> findAll() {
		return dao.selectAll();
	}

	@Override
	public Member findOne(Integer userID) {
		return dao.selectById(userID);
	}

	@Override
	public Member settings(Member member) {
		Boolean isSuccess = dao.updateById(member);

		if (isSuccess == false) {
			member.setMessage("資訊變更失敗，請重新輸入");
			member.setSuccessful(false);
			return member;
		}

		member.setMessage("資訊變更成功");
		member.setSuccessful(true);
		return member;
	}

}
