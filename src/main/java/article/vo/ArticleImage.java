package article.vo;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ArticleImage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer articleImageID;
	private Integer articleID;
	private byte[] image;
	//
	public Integer getArticleImageID() {
		return articleImageID;
	}
	public void setArticleImageID(Integer articleImageID) {
		this.articleImageID = articleImageID;
	}
	public Integer getArticleID() {
		return articleID;
	}
	public void setArticleID(Integer articleID) {
		this.articleID = articleID;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//
	public ArticleImage(Integer articleImageID, Integer articleID, byte[] image) {
		super();
		this.articleImageID = articleImageID;
		this.articleID = articleID;
		this.image = image;
	}
	//
	@Override
	public String toString() {
		return "ArticleImage [articleImageID=" + articleImageID + ", articleID=" + articleID + ", image="
				+ Arrays.toString(image) + "]";
	}
	
	public ArticleImage() {
		
	}

}
