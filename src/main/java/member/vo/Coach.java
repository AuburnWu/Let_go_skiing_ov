package member.vo;

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
public class Coach extends Core{
	private static final long serialVersionUID = 5228656458800449563L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer coachID;
	private Integer userID;
	private String skill;
	private String background;
	private String introduce;
	private byte[] license;
	private String applyStatus;
}
