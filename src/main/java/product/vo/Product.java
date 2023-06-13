package product.vo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@DynamicUpdate
@Table(name="Product")
public class Product implements Serializable {
	
	private static final long serivalVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productID;
	private String productClass;
	private String productName;
	private Integer productPrice;
	private Integer productQuantity;
	private byte[] productImage;
	private String productDetail;
	@Builder.Default
	private Integer productBuyPerson = 0;
	 @Builder.Default
	private Date productDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	@Column(name="productStatus")
	private String productStatus;
	

	@Override
	public String toString() {
		return "Product [productId=" + productID + ", productClass=" + productClass + ", productName=" + productName
				+ ", productPrice=" + productPrice + ", productQuantity=" + productQuantity + ", productImage="
				+ productImage + ", productDetail=" + productDetail + ", productBuyPerson=" + productBuyPerson
				+ ", productDate=" + productDate + ", productStatus=" + productStatus + "]";
	}
	public static byte[] getPicture(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	
	
}