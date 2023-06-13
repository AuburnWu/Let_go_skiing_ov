package member.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import member.dao.MemberDao;
import member.vo.Member;

public class MemberDaoImpl implements MemberDao {

	@Override
	public int insert(Member member) {
		try {
			getSession().persist(member);
			return 1;
		} catch (Exception e) {
			System.out.println("insert方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int deleteById(Integer userID) {
		try {
			Session session = getSession();
			Member member = session.get(Member.class, userID);
			session.remove(member);
			return 1;
		} catch (Exception e) {
			System.out.println("deleteById方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public boolean updateById(Member nMember) {
		try {
			Session session = getSession();
			Member oMember = session.get(Member.class, nMember.getUserID());
			
			final String email = nMember.getEmail();
			if (email != null && !email.isEmpty()) {
				oMember.setEmail(email);
			}
			final String passowrd = nMember.getPassword();
			if (passowrd != null && !passowrd.isEmpty()) {
				oMember.setPassword(passowrd);
			}
			final String userName = nMember.getUserName();
			if (userName != null && !userName.isEmpty()) {
				oMember.setUserName(userName);
			}
			final String nickName = nMember.getNickName();
			if (nickName != null && !nickName.isEmpty()) {
				oMember.setNickName(nickName);
			}
			final String gender = nMember.getGender();
			if (gender != null && !gender.isEmpty()) {
				oMember.setGender(gender);
			}
			final Date birthDate = nMember.getBirthDate();
			if (birthDate != null) {
				oMember.setBirthDate(birthDate);
			}
			final String personID = nMember.getPersonID();
			if (personID != null) {
				oMember.setPersonID(personID);
			}
			final String phone = nMember.getPhone();
			if (phone != null) {
				oMember.setPhone(phone);
			}
			final String address = nMember.getAddress();
			if (address != null) {
				oMember.setAddress(address);
			}
			final String level = nMember.getLevel();
			if (level != null && !level.isEmpty()) {
				oMember.setLevel(level);
			}
			final byte[] photo = nMember.getPhoto();
			if (photo != null && photo.length > 0) {
				oMember.setPhoto(photo);
			}
			final String userStatus = nMember.getUserStatus();
			if (userStatus != null && !userStatus.isEmpty()) {
				oMember.setUserStatus(userStatus);
			}
			return true;
		} catch (Exception e) {
			System.out.println("updateById方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Member selectById(Integer userID) {
		try {
			Session session = getSession();
			Member member = session.get(Member.class, userID);
			return member;
		} catch (Exception e) {
			System.out.println("selectById方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Member> selectAll() {
		final String hql = "FROM Member ORDER BY userID";
		try {
			Session session = getSession();
			Query<Member> query = session.createQuery(hql, Member.class); // import org.hibernate.query.Query;
			List<Member> list = query.getResultList();
			return list;
		} catch (Exception e) {
			System.out.println("selectAll方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Member selectForLogin(String email, String password) {
		final String sql = "SELECT * FROM Member WHERE email = :email AND password = :password";
			Session session = getSession();
			NativeQuery<Member> nativeQuery = session.createNativeQuery(sql, Member.class);
			nativeQuery.setParameter("email", email);
			nativeQuery.setParameter("password", password);
			Member member = nativeQuery.uniqueResult();
			return member;
	}

	@Override
	public Member selectByEmail(String email) {
		final String sql = "SELECT * FROM Member WHERE email = :email";
			Session session = getSession();
			NativeQuery<Member> nativeQuery = session.createNativeQuery(sql, Member.class);
			nativeQuery.setParameter("email", email);
			Member member = nativeQuery.uniqueResult();
			return member;
	}

}