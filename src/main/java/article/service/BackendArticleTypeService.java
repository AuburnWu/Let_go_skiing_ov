package article.service;

import java.util.List;

import article.vo.ArticleType;

public interface BackendArticleTypeService {

	//顯示全部文章分類
	List<ArticleType> findAll();

	//新增文章分類
	void addArticleType(ArticleType articleType);

	//搜尋文章分類編號
	void searchArticleTypeID(Integer articleTypeID);

	//搜尋文章分類內容
	void searchArticleTypeContent(String articleTypeContent);

	//修改文章分類選項
	void updateArticleType(ArticleType articleTypeID);

}