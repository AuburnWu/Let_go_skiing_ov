package ad.product.dao.Impl;

import java.util.List;

import ad.product.dao.ProductAdHistoryDao;
//>>>>>>> e6638a84f6a961976edf1a7acf72d24bfb983847:src/main/java/ad/product/Dao/Impl/productADHistoryDaoImpl.java
import ad.product.vo.ProductAdHistory;

public class ProductAdHistoryDaoImpl implements ProductAdHistoryDao {
//	getSession() getSession() = HibernateUtil.getgetSession()Factory().getCurrentgetSession()();

	@Override
	public int insert(ProductAdHistory ProductAdHistory) {
		getSession().persist(ProductAdHistory);
		return -1;
	}

	@Override
	public int delete(Integer id) {
		ProductAdHistory pAdHistory = getSession().load(ProductAdHistory.class, id);
		getSession().remove(pAdHistory);
		return 1;
	}

	@Override
	public int update(ProductAdHistory ProductAdHistory) {
		getSession().update(ProductAdHistory);
		return 1;
	}

	@Override
	public ProductAdHistory selectById(Integer id) {
		return getSession().get(ProductAdHistory.class, id);
	}

	@Override
	public List<ProductAdHistory> selectAll() {
		return getSession().createQuery("FROM ProductAdHistory", ProductAdHistory.class).list();
	}

}
