package article.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CommentLike implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentLikeID;
	private Integer commentID;
	private Integer userID;
	private String commentLikeStatus;
	//
	public Integer getCommentLikeID() {
		return commentLikeID;
	}
	public void setCommentLikeID(Integer commentLikeID) {
		this.commentLikeID = commentLikeID;
	}
	public Integer getCommentID() {
		return commentID;
	}
	public void setCommentID(Integer commentID) {
		this.commentID = commentID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getCommentLikeStatus() {
		return commentLikeStatus;
	}
	public void setCommentLikeStatus(String commentLikeStatus) {
		this.commentLikeStatus = commentLikeStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//
	public CommentLike(Integer commentLikeID, Integer commentID, Integer userID, String commentLikeStatus) {
		super();
		this.commentLikeID = commentLikeID;
		this.commentID = commentID;
		this.userID = userID;
		this.commentLikeStatus = commentLikeStatus;
	}
	//
	@Override
	public String toString() {
		return "CommentLike [commentLikeID=" + commentLikeID + ", commentID=" + commentID + ", userID=" + userID
				+ ", commentLikeStatus=" + commentLikeStatus + "]";
	}
	
	public CommentLike() {
		
	}
	

}
