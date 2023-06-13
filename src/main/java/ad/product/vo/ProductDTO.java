package ad.product.vo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import product.vo.Product;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private Integer productAD;
	private Integer productID;
	private String productClass;
	private String productName;
	private Integer productPrice;
	private Integer productQuantity;
	private byte[] productImage;
	private String productDetail;
	private Integer productBuyPerson = 0;
	private Date productDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	private String productStatus;


}
