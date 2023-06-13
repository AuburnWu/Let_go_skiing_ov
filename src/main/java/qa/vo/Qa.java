package qa.vo;

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
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Qa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer qaID;
	@Column
	private Integer questionType;
	@Column
	private String questionTitle;
	@Column
	private String answerContent;
	@Column
	private Timestamp questionDate;

}
