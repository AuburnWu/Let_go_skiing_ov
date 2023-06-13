package article.dao; //把impl刪掉
//ArticleDaoimpl那邊空白處右鍵->Refactor->Extract Interface...
import java.util.List;

import article.vo.Article;
import article.vo.ArticleType;
import core.CoreDao;

public interface ArticleDao extends CoreDao{

	int insert(Article article);

	//複製insert
	int deleteByArticleID(Integer articleID);

	//複製insert
	//開MySQL，點你要的TABLE右鍵->Send to SQL Editor->Update Statement
	//全選複製(第一行只留資料庫名,跳號全刪)，貼在sql裡面
	int updateByArticleID(Article article);

	//複製insert
	Article selectByArticleID(Integer articleID) throws ClassNotFoundException;

	List<Article> selectByArticleTitle(String articleTitle);
	
	List<Article> selectByArticleTypeID(Integer ArticleTypeID);
	
	List<Article> selectByuserID(Integer userID);
	
	List<Article> selectByArticleStatus(String articleTitle);
	
	int updateAllSelectArticleTypeToOne(Integer articleTypeID);
	//複製select
	//List袋子裝，只能裝Article
	List<Article> selectAll() throws ClassNotFoundException;

}