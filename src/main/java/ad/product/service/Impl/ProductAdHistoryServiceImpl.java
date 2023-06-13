package ad.product.service.Impl;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import ad.product.dao.ProductAdHistoryDao;
import ad.product.dao.Impl.ProductAdHistoryDaoImpl;
import ad.product.service.ProductAdHistoryService;
import ad.product.vo.ProductAdHistory;

public class ProductAdHistoryServiceImpl implements ProductAdHistoryService {

	private ProductAdHistoryDao dao = new ProductAdHistoryDaoImpl();
	
	@Override
	public List<ProductAdHistory> historyList(){
		return dao.selectAll();
	}
}
