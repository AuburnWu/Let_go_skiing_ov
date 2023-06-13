package loc.dao;

import java.util.List;

import core.CoreDao;
import loc.model.SkiLocation;

public interface SkiLocDao extends CoreDao{

	// insert
	int insert(SkiLocation skiLocation);
	

	// delete
	int deleteByPointID(Integer PointID);

	// update
	boolean updateByPointID(SkiLocation skiLocation);
	
	boolean updateImageByPointID(SkiLocation skiLocation);

	//select
	SkiLocation selectByPointID(Integer PointID);

	//select all
	List<SkiLocation> selectAll();
	
	

}