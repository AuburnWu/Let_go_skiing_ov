package article.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ArticleType implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer articleTypeID;
	private String articleTypeContent;
	//
	public Integer getArticleTypeID() {
		return articleTypeID;
	}
	public void setArticleTypeID(Integer articleTypeID) {
		this.articleTypeID = articleTypeID;
	}
	public String getArticleTypeContent() {
		return articleTypeContent;
	}
	public void setArticleTypeContent(String articleTypeContent) {
		this.articleTypeContent = articleTypeContent;
	}
	//
	@Override
	public String toString() {
		return "ArticleType [articleTypeID=" + articleTypeID + ", articleTypeContent=" + articleTypeContent + "]";
	}
	public ArticleType(Integer articleTypeID, String articleTypeContent) {
		super();
		this.articleTypeID = articleTypeID;
		this.articleTypeContent = articleTypeContent;
	}
	//
	public ArticleType() {}

}
