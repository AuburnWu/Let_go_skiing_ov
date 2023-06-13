package qa.dao.Impl;

import qa.dao.QaDao;
//>>>>>>> e6638a84f6a961976edf1a7acf72d24bfb983847
import qa.vo.Qa;

public class QaDaoImpl implements QaDao {

//	getSession() getSession() = HibernateUtil.getgetSession()Factory().getCurrentgetSession()();

	@Override
	public int insert(Qa qa) {
		getSession().persist(qa);
		return -1;
	}

	@Override
	public int deleteById(Integer id) {
		Qa qa = getSession().load(Qa.class, id);
		getSession().remove(qa);
		return 1;
	}

	@Override
	public int updata(Qa qa) {
		getSession().update(qa);
		return 0;
	}

	@Override
	public Qa selectByID(Integer id) {
		return getSession().get(Qa.class, id);
	}

	@Override
	public java.util.List<Qa> selectAll() {
		return getSession().createQuery("FROM Qa", Qa.class).list();

	}

}
