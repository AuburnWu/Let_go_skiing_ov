package order.productOrder.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class ProductOrder implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productOrderID;
	private Integer userID;
	private Integer totalPrice;
	private String deliveryAddr;
	private Timestamp buyDateTime;
	private String productOrderStatus;
	
	public ProductOrder(){}

	public ProductOrder(Integer productOrderID, Integer userID, Integer totalPrice, String deliveryAddr,
			Timestamp buyDateTime, String productOrderStatus) {
		this.productOrderID = productOrderID;
		this.userID = userID;
		this.totalPrice = totalPrice;
		this.deliveryAddr = deliveryAddr;
		this.buyDateTime = buyDateTime;
		this.productOrderStatus = productOrderStatus;
	}

	public Integer getProductOrderID() {
		return productOrderID;
	}

	public void setProductOrderID(Integer productOrderID) {
		this.productOrderID = productOrderID;
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

	public String getDeliveryAddr() {
		return deliveryAddr;
	}

	public void setDeliveryAddr(String deliveryAddr) {
		this.deliveryAddr = deliveryAddr;
	}

	public Timestamp getBuyDateTime() {
		return buyDateTime;
	}

	public void setBuyDateTime(Timestamp buyDateTime) {
		this.buyDateTime = buyDateTime;
	}

	public String getProductOrderStatus() {
		return productOrderStatus;
	}

	public void setProductOrderStatus(String productOrderStatus) {
		this.productOrderStatus = productOrderStatus;
	}
	
	

}
