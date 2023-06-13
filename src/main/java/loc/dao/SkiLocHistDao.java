package loc.dao;

import java.util.List;

import core.CoreDao;
import loc.model.SkiLocationHistory;

public interface SkiLocHistDao extends CoreDao{

	// insert
	int insert(SkiLocationHistory skiLocationHistory);

	// delete
	int deleteBySkiLocationHistoryID(Integer skiLocationHistoryID);

	// update
	int updateBySkiLocationHistoryID(SkiLocationHistory skiLocationHistory);

	// select
	SkiLocationHistory selectBySkiLocationHistoryID(Integer skiLocationHistoryID);

	// select All
	List<SkiLocationHistory> selectAll();

}