package ad.product.dao;

import java.util.List;

import ad.product.vo.ProductAdHistory;
import core.CoreDao;

public interface ProductAdHistoryDao extends CoreDao{

	int insert(ProductAdHistory ProductAdHistory);

	int delete(Integer id);

	int update(ProductAdHistory ProductAdHistory);

	ProductAdHistory selectById(Integer id);

	List<ProductAdHistory> selectAll();

}