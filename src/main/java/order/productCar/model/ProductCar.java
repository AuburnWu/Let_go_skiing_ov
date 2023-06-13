package order.productCar.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import order.productCar.dao.ProductCarPK;

@Entity
public class ProductCar implements java.io.Serializable{
	@EmbeddedId
	private ProductCarPK id;
	private Integer quantity;
	
	
	public ProductCarPK getId() {
		return id;
	}

	public void setId(ProductCarPK id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ProductCar(ProductCarPK id, Integer quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}

	public ProductCar(){};

}
