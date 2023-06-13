package article.service;

import java.util.List;

import core.CoreDao;
import article.dao.ArticleDao;
import article.dao.impl.ArticleDaoImpl;
import article.vo.Article;


public class BackendArticleServiceImpl implements CoreDao, BackendArticleService{
	
	ArticleDao articleDao;
	public BackendArticleServiceImpl() {
		articleDao=new ArticleDaoImpl();
	}
	
	//顯示全部文章
	@Override
	public List<Article> findAll(){
		try {
			return articleDao.selectAll();
		} catch (Exception e) {
			System.out.println("資訊取得失敗");
			// TODO: handle exception
		}
		return null;
		
	}
	
	//搜尋文章編號
	@Override
	public void searchArticleID(Integer articleID) {
		try {
			articleDao.selectByArticleID(articleID);
		} catch (Exception e) {
			System.out.println("搜尋文章編號失敗");
			// TODO: handle exception
		}
		
	}
	
	//搜尋文章標題
	@Override
	public void searchArticleTitle(String articleTitle) {
		try {
			//Hibernate
			Article article = getSession().get(Article.class, articleTitle);
//		    return articleTitle;
		} catch (Exception e) {
			System.out.println("搜尋文章分類內容失敗");
			// TODO: handle exception
		}	
	}
	
	//搜尋文章狀態
	@Override
	public void searchArticleStatus(String articleStatus) {
		try {
			//Hibernate
			Article article = getSession().get(Article.class, articleStatus);
//		    return articleStatus;
		} catch (Exception e) {
			System.out.println("搜尋文章狀態失敗");
			// TODO: handle exception
		}	
	}
	
	//點文章標題進入修改上下架文章頁面
	

}
