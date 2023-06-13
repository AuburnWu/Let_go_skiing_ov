package serv.service;

import serv.dao.ServChatDao;
import serv.dao.Impl.ServChatDaoImpl;

public class ServChatServiceImpl {
	private ServChatDao dao;

	public ServChatServiceImpl() {
			dao = new ServChatDaoImpl();
	}
	
	
}
