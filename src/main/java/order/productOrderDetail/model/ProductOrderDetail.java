package order.productOrderDetail.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class ProductOrderDetail implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productOrderDetailID;
	private Integer productOrderID;
	private Integer productID;
	private Integer productPrice;
	private Integer quantity;
	
	public ProductOrderDetail(){};
	
	public ProductOrderDetail(Integer productOrderDetailID, Integer productOrderID, Integer productID,
			Integer productPrice, Integer quantity) {

		this.productOrderDetailID = productOrderDetailID;
		this.productOrderID = productOrderID;
		this.productID = productID;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}

	public Integer getProductOrderDetailID() {
		return productOrderDetailID;
	}

	public void setProductOrderDetailID(Integer productOrderDetailID) {
		this.productOrderDetailID = productOrderDetailID;
	}

	public Integer getProductOrderID() {
		return productOrderID;
	}

	public void setProductOrderID(Integer productOrderID) {
		this.productOrderID = productOrderID;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
