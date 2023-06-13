package article.dao;

import java.util.List;

import core.CoreDao;
import product.vo.Product;
import article.vo.ArticleType;

public interface ArticleTypeDao extends CoreDao{

	int insert(ArticleType articleType);

	int deleteByArticleTypeID(Integer articleTypeID);

	int updateByArticleTypeID(ArticleType articleType);
	
	ArticleType selectByArticleTypeID(Integer articleTypeID);
	
	List<ArticleType> selectByArticleTypeContent(String articleTypeContent);
	
	List<ArticleType> selectByArticleTypeContentRepeat(String articleTypeContent);

	List<ArticleType> selectAll() throws ClassNotFoundException;
	
	List<ArticleType> deleteAllAndInsertAll() throws ClassNotFoundException;
	
	List<ArticleType> getArticlesByPage(int pageNumber, int pageSize) throws ClassNotFoundException;

}