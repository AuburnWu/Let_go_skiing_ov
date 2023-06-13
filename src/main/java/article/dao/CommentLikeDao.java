package article.dao;

import java.util.List;

import article.vo.CommentLike;
import core.CoreDao;

public interface CommentLikeDao extends CoreDao{

	int insert(CommentLike commentLike);

	int deleteByCommentLikeID(Integer commentLikeID);

	int updateByCommentLikeID(CommentLike commentLike);

	CommentLike selectByCommentLikeID(Integer commentLikeID);

	List<CommentLike> selectAll() throws ClassNotFoundException;

}