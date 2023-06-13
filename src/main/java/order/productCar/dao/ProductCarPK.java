package order.productCar.dao;

import javax.persistence.Embeddable;

@Embeddable
public class ProductCarPK implements java.io.Serializable {
	private static final long seriaVersionUID=1L;
	private Integer userID;

	private Integer productID;


	
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public ProductCarPK(Integer userID, Integer productID) {
		super();
		this.userID = userID;
		this.productID = productID;
	}
	public ProductCarPK() {}
}
