package order.courseCar.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import order.courseCar.dao.CourseCarPK;






@Entity
public class CourseCar implements java.io.Serializable{
	@EmbeddedId
	private CourseCarPK id;

	
	private Integer quantity;
	public CourseCarPK getId() {
		return id;
	}
	public void setId(CourseCarPK id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public CourseCar(CourseCarPK id, Integer quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}
	public CourseCar() {};



	
}
