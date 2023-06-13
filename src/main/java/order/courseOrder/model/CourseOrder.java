package order.courseOrder.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class CourseOrder implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseOrderID;
	private Integer userID;
	private Integer totalPrice;
	private Timestamp buyDateTime;
	
	public CourseOrder() {};
	public CourseOrder(Integer courseOrderID, Integer userID, Integer totalPrice, Timestamp buyDateTime) {
		super();
		this.courseOrderID = courseOrderID;
		this.userID = userID;
		this.totalPrice = totalPrice;
		this.buyDateTime = buyDateTime;
	}
	public Integer getCourseOrderID() {
		return courseOrderID;
	}
	public void setCourseOrderID(Integer courseOrderID) {
		this.courseOrderID = courseOrderID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Timestamp getBuyDateTime() {
		return buyDateTime;
	}
	public void setBuyDateTime(Timestamp buyDateTime) {
		this.buyDateTime = buyDateTime;
	}
	
	

}
