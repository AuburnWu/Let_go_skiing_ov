package order.courseOrderDetail.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class CourseOrderDetail implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseOrderDetailID;
	private Integer courseOrderID;
	private Integer courseID;
	private Integer coursePrice;
	private Integer quantity;
	private String courseOrderDetailStatus;
	public CourseOrderDetail() {};
	public CourseOrderDetail(Integer courseOrderDetailID, Integer courseOrderID, Integer courseID, Integer coursePrice,
			Integer quantity, String courseOrderDetailStatus) {
		super();
		this.courseOrderDetailID = courseOrderDetailID;
		this.courseOrderID = courseOrderID;
		this.courseID = courseID;
		this.coursePrice = coursePrice;
		this.quantity = quantity;
		this.courseOrderDetailStatus = courseOrderDetailStatus;
	}
	public Integer getCourseOrderDetailID() {
		return courseOrderDetailID;
	}
	public void setCourseOrderDetailID(Integer courseOrderDetailID) {
		this.courseOrderDetailID = courseOrderDetailID;
	}
	public Integer getCourseOrderID() {
		return courseOrderID;
	}
	public void setCourseOrderID(Integer courseOrderID) {
		this.courseOrderID = courseOrderID;
	}
	public Integer getCourseID() {
		return courseID;
	}
	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}
	public Integer getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(Integer coursePrice) {
		this.coursePrice = coursePrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getCourseOrderDetailStatus() {
		return courseOrderDetailStatus;
	}
	public void setCourseOrderDetailStatus(String courseOrderDetailStatus) {
		this.courseOrderDetailStatus = courseOrderDetailStatus;
	}
	
	

}
