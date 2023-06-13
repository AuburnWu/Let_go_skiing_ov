package loc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SkiLocationHistory implements Serializable {

	private static final long seriaVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer skiLocationHistoryID;
	private Integer pointID;
	private Integer courseID;
	
	
	public SkiLocationHistory() {}
	
	
	public Integer getSkiLocationHistoryID() {
		return skiLocationHistoryID;
	}
	public void setSkiLocationHistoryID(Integer skiLocationHistoryID) {
		this.skiLocationHistoryID = skiLocationHistoryID;
	}
	public Integer getPointID() {
		return pointID;
	}
	public void setPointID(Integer pointID) {
		this.pointID = pointID;
	}
	public Integer getCourseID() {
		return courseID;
	}
	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}
	public static long getSeriaversionuid() {
		return seriaVersionUID;
	}
	public SkiLocationHistory(Integer skiLocationHistoryID, Integer pointID, Integer courseID) {
		super();
		this.skiLocationHistoryID = skiLocationHistoryID;
		this.pointID = pointID;
		this.courseID = courseID;
	}
	@Override
	public String toString() {
		return "SkiLocationHistory [skiLocationHistoryID=" + skiLocationHistoryID + ", pointID=" + pointID
				+ ", courseID=" + courseID + "]";
	}

	
}
