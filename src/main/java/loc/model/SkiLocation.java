package loc.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SkiLocation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pointID;
	private String pointName;
	private Double longitude;
	private Double latitude;
	private String pointArea;
	private String pointView;
	private String pointInfo;
	private Integer starMonth;
	private Integer endMonth;
	private byte[] pointPicture;
	private Integer pointRating;
	private String pointStatus;
	
	public SkiLocation() {}
	
	public Integer getPointID() {
		return pointID;
	}
	public void setPointID(Integer pointID) {
		this.pointID = pointID;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getPointArea() {
		return pointArea;
	}
	public void setPointArea(String pointArea) {
		this.pointArea = pointArea;
	}
	public String getPointView() {
		return pointView;
	}
	public void setPointView(String pointView) {
		this.pointView = pointView;
	}
	public String getPointInfo() {
		return pointInfo;
	}
	public void setPointInfo(String pointInfo) {
		this.pointInfo = pointInfo;
	}
	public Integer getStarMonth() {
		return starMonth;
	}
	public void setStarMonth(Integer starMonth) {
		this.starMonth = starMonth;
	}
	public Integer getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(Integer endMonth) {
		this.endMonth = endMonth;
	}
	public byte[] getPointPicture() {
		return pointPicture;
	}
	public void setPointPicture(byte[] pointPicture) {
		this.pointPicture = pointPicture;
	}
	public Integer getPointRating() {
		return pointRating;
	}
	public void setPointRating(Integer pointRating) {
		this.pointRating = pointRating;
	}
	public String getPointStatus() {
		return pointStatus;
	}
	public void setPointStatus(String pointStatus) {
		this.pointStatus = pointStatus;
	}
	public static long getSeriaversionuid() {
		return serialVersionUID;
	}
	public SkiLocation(Integer pointID, String pointName, Double longitude, Double latitude, String pointArea,
			String pointView, String pointInfo, Integer starMonth, Integer endMonth, byte[] pointPicture,
			Integer pointRating, String pointStatus) {
		super();
		this.pointID = pointID;
		this.pointName = pointName;
		this.longitude = longitude;
		this.latitude = latitude;
		this.pointArea = pointArea;
		this.pointView = pointView;
		this.pointInfo = pointInfo;
		this.starMonth = starMonth;
		this.endMonth = endMonth;
		this.pointPicture = pointPicture;
		this.pointRating = pointRating;
		this.pointStatus = pointStatus;
	}

	@Override
	public String toString() {
		return "SkiLocation [pointID=" + pointID + ", pointName=" + pointName + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", pointArea=" + pointArea + ", pointView=" + pointView + ", pointInfo="
				+ pointInfo + ", startMonth=" + starMonth + ", endMonth=" + endMonth + ", pointPicture="
				+ Arrays.toString(pointPicture) + ", pointRating=" + pointRating + ", pointStatus=" + pointStatus + "]";
	}
	
	
}
