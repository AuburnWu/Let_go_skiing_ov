package article.service;

import java.util.List;

import article.vo.Article;

public interface BackendArticleService {

	//顯示全部文章
	List<Article> findAll();

	//搜尋文章編號
	void searchArticleID(Integer articleID);

	//搜尋文章標題
	void searchArticleTitle(String articleTitle);

	//搜尋文章狀態
	void searchArticleStatus(String articleStatus);

}