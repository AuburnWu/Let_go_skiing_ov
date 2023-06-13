package serv.dao;

import java.util.List;

import core.CoreDao;
import serv.vo.ServChat;

public interface ServChatDao extends CoreDao{

	int insert(ServChat servChat);

	int delete(Integer id);

	int update(ServChat sChat);

	ServChat select(Integer id);

	List<ServChat> selectAll();

}