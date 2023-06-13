package order.courseCar.dao;

import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class CourseCarPK implements java.io.Serializable{
	private static final long seriaVersionUID=1L;
	private Integer userID;
	private Integer	courseID;
	
	public CourseCarPK() {}
	public CourseCarPK(Integer userID,Integer	courseID) {
		this.userID=userID;
		this.courseID =courseID;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o==null ||getClass()!=o.getClass()) {
			return false;
		}
		CourseCarPK pk=(CourseCarPK) o;
		return Objects.equals(userID,pk.userID) && Objects.equals(courseID, pk.courseID);
		
	}
	@Override
	public int hashCode() {
		return Objects.hash(userID,courseID);
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}
	
}
