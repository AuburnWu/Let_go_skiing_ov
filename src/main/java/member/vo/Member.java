package member.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table
public class Member extends Core {
	private static final long serialVersionUID = -1859851567932501006L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userID;
	private String email;
	private String password;
	private String userName;
	private String nickName;
	private String gender;
	private Date birthDate;
	private String personID;
	private String phone;
	private String address;
	private String level;
	private byte[] photo;
	private String userStatus;
}
