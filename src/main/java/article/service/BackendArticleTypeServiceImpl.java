package article.service;

import java.util.List;

import core.CoreDao;
import article.dao.ArticleTypeDao;
import article.dao.impl.ArticleTypeDaoImpl;
import article.vo.ArticleType;

public class BackendArticleTypeServiceImpl implements CoreDao, BackendArticleTypeService{
	
	ArticleTypeDao articleTypeDao;
	public BackendArticleTypeServiceImpl() {
		articleTypeDao=new ArticleTypeDaoImpl();
	}
	
	//顯示全部文章分類
	@Override
	public List<ArticleType> findAll(){
		try {
			return articleTypeDao.selectAll();
		} catch (Exception e) {
			System.out.println("資訊取得失敗");
			// TODO: handle exception
		}
		return null;
		
	}
	
	//新增文章分類
	@Override
	public void addArticleType(ArticleType articleType) {
		try {
			ArticleType articleType1 = new ArticleType(null, "揪團");
			articleTypeDao.insert(articleType1);
		} catch (Exception e) {
			System.out.println("新增失敗");
			// TODO: handle exception
		}
	}
		
	//搜尋文章分類編號
	@Override
	public void searchArticleTypeID(Integer articleTypeID) {
		try {
			articleTypeDao.selectByArticleTypeID(articleTypeID);
		} catch (Exception e) {
			System.out.println("搜尋文章分類編號失敗");
			// TODO: handle exception
		}
		
	}
	
	//搜尋文章分類內容
	@Override
	public void searchArticleTypeContent(String articleTypeContent) {
		try {
			//Hibernate
			ArticleType articleType = getSession().get(ArticleType.class, articleTypeContent);
//		    return articleType;
		} catch (Exception e) {
			System.out.println("搜尋文章分類內容失敗");
			// TODO: handle exception
		}	
	}
		
	//修改文章分類選項
	@Override
	public void updateArticleType(ArticleType articleTypeID) {
		try {
			articleTypeDao.updateByArticleTypeID(articleTypeID);
		} catch (Exception e) {
			System.out.println("修改失敗");
			// TODO: handle exception
		}
		
	}
	
}
