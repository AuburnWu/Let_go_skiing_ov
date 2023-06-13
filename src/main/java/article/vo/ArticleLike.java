package article.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ArticleLike implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer articleLikeID;
	private Integer articleID;
	private Integer userID;
	private String articleLikeStatus;
	
	
	public Integer getArticleLikeID() {
		return articleLikeID;
	}
	public void setArticleLikeID(Integer articleLikeID) {
		this.articleLikeID = articleLikeID;
	}
	public Integer getArticleID() {
		return articleID;
	}
	public void setArticleID(Integer articleID) {
		this.articleID = articleID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getArticleLikeStatus() {
		return articleLikeStatus;
	}
	public void setArticleLikeStatus(String articleLikeStatus) {
		this.articleLikeStatus = articleLikeStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public ArticleLike(Integer articleLikeID, Integer articleID, Integer userID, String articleLikeStatus) {
		super();
		this.articleLikeID = articleLikeID;
		this.articleID = articleID;
		this.userID = userID;
		this.articleLikeStatus = articleLikeStatus;
	}
	@Override
	public String toString() {
		return "ArticleLike [articleLikeID=" + articleLikeID + ", articleID=" + articleID + ", userID=" + userID
				+ ", articleLikeStatus=" + articleLikeStatus + "]";
	}
	
	public ArticleLike() {
		
	}
	
	
	
	
	

}
