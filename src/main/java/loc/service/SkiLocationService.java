package loc.service;

import java.util.List;

import loc.dao.SkiLocDao;
import loc.dao.SkiLocDaoImpl;
import loc.model.SkiLocation;
import member.vo.Member;

public class SkiLocationService {
	private SkiLocDao skiLocDao;
	
	public SkiLocationService() {
		skiLocDao =new SkiLocDaoImpl();
	}
	
	  
		//新增雪點
		public void addMap(SkiLocation skiLocation) {
		    skiLocDao.insert(skiLocation);
		    System.out.println("新增雪點成功");
		}
		
//	    public int deleteMap(int pointID) {
//	        return skiLocDao.deleteByPointID(pointID);
//	    }

//	    public int updateMap(SkiLocation skiLocation) {
//	        skiLocDao.updateByPointID(skiLocation);
//	    }
	    
	    public boolean update(SkiLocation skiLocation) {
	    	return skiLocDao.updateByPointID(skiLocation);
		}
	    
	    public boolean updateImage(SkiLocation skiLocation) {
	    	return skiLocDao.updateImageByPointID(skiLocation);
		}

	    public SkiLocation getLocationById(int pointID) {
	    	System.out.println("1");
	        return skiLocDao.selectByPointID(pointID);
	    }
		
		
	    public SkiLocation selectByIDMap(int pointID) {
	        return skiLocDao.selectByPointID(pointID);
	    }

	    public List<SkiLocation> selectAllMap() {
	        return skiLocDao.selectAll();
	    }
		
		
	}
	


