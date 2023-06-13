package article.dao;

import java.util.List;

import article.vo.ArticleImage;
import core.CoreDao;

public interface ArticleImageDao extends CoreDao{

	int insert(ArticleImage articleImage);

	int deleteByArticleImageID(Integer articleImageID);

	int updateByArticleImageID(ArticleImage articleImage);

	ArticleImage selectByArticleImageID(Integer articleImageID);

	List<ArticleImage> selectAll() throws ClassNotFoundException;

}