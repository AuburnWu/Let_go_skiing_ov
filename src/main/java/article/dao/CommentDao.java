package article.dao;

import java.util.List;

import article.vo.ArticleType;
import article.vo.Comment;
import core.CoreDao;

public interface CommentDao extends CoreDao{

	int insert(Comment comment);

	int deleteByCommentID(Integer commentID);

	int updateByCommentID(Comment comment);

	Comment selectByCommentID(Integer commentID);
	
	List<Comment> selectByComment(Integer articleID);

	List<Comment> selectAll() throws ClassNotFoundException;

}