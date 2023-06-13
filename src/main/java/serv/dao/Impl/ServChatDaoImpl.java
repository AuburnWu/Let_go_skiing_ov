package serv.dao.Impl;

import java.util.List;

import serv.dao.ServChatDao;
//>>>>>>> e6638a84f6a961976edf1a7acf72d24bfb983847:src/main/java/serv/Dao/Impl/servChatDaoImpl.java
import serv.vo.ServChat;

public class ServChatDaoImpl implements ServChatDao {
//	private getSession() getSession();
	
	
	@Override
	public int insert(ServChat servChat) {
		getSession().persist(servChat);
		return 1;
	}

	@Override
	public int delete(Integer id) {
		ServChat servChat = getSession().load(ServChat.class, id);
		getSession().remove(servChat);
		return 1;
	}
	@Override
	public int update(ServChat sChat) {
		ServChat servChat = getSession().load(ServChat.class, sChat.getServChatID());
		servChat.setManagerID(sChat.getManagerID());
		servChat.setMessage(sChat.getMessage());
		servChat.setSendTime(sChat.getSendTime());
		return 1;
	}
	@Override
	public ServChat select(Integer id) {
		return getSession().get(ServChat.class, id);
	}
	@Override
	public List<ServChat> selectAll(){
		return getSession().createQuery("FROM ServChat",ServChat.class).list();
	}
}
