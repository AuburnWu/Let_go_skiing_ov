package qa.dao;

import core.CoreDao;
import qa.vo.Qa;

public interface QaDao extends CoreDao{

	int insert(Qa qa);

	int deleteById(Integer id);

	int updata(Qa qa);

	Qa selectByID(Integer id);

	java.util.List<Qa> selectAll();

}