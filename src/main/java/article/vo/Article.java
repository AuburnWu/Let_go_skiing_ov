package article.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Article implements Serializable{
	
	//Serializable 給他一個長數值
	private static final long serialVersionUID = 1L;
	
	//使用包裝型別的原因：基本資料型別不能為 null，而使用包裝型別可以為 null
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer articleID;
	private Integer userID;
	private Integer articleTypeID;
	private String articleTitle;
	private String articleContent;
	private String articleDateTime;
	private String articleModified;
	private Integer articleLike;
	private String articleStatus;
	//如果有圖片型別用byte[]
	
	//source->Getters and Setters
	public Integer getUserID() {
		return userID;
	}
	public Integer getArticleID() {
		return articleID;
	}
	public void setArticleID(Integer articleID) {
		this.articleID = articleID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getArticleTypeID() {
		return articleTypeID;
	}
	public void setArticleType(Integer articleTypeID) {
		this.articleTypeID = articleTypeID;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public String getArticleDateTime() {
		return articleDateTime;
	}
	public void setArticleDateTime(String articleDateTime) {
		this.articleDateTime = articleDateTime;
	}
	public String getArticleModified() {
		return articleModified;
	}
	public void setArticleModified(String articleModified) {
		this.articleModified = articleModified;
	}
	public Integer getArticleLike() {
		return articleLike;
	}
	public void setArticleLike(Integer articleLike) {
		this.articleLike = articleLike;
	}
	public String getArticleStatus() {
		return articleStatus;
	}
	public void setArticleStatus(String articleStatus) {
		this.articleStatus = articleStatus;
	}
	
	//source->Fields
	public Article(Integer articleID, Integer userID, Integer articleTypeID, String articleTitle, String articleContent,
			String sDateTime, String sModified, Integer articleLike, String articleStatus) {
		super();
		this.articleID = articleID;
		this.userID = userID;
		this.articleTypeID = articleTypeID;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.articleDateTime = sDateTime;
		this.articleModified = sModified;
		this.articleLike = articleLike;
		this.articleStatus = articleStatus;
	}
	//source->toString
	@Override
	public String toString() {
		return "Article [articleID=" + articleID + ", userID=" + userID + ", articleTypeID=" + articleTypeID
				+ ", articleTitle=" + articleTitle + ", articleContent=" + articleContent + ", articleDateTime="
				+ articleDateTime + ", articleModified=" + articleModified + ", articleLike=" + articleLike
				+ ", articleStatus=" + articleStatus + "]";
	}
	
	public Article() {
		
	}
	
	
	
	
	
	

}
