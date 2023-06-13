package serv.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ServiceChat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServChat {

	@Id
	@Column(name="serviceChatID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer servChatID;
	@Column
	private Integer managerID;
	@Column
	private Integer userID;
	@Column
	private String message;
	@Column
	private Timestamp sendTime;

}
