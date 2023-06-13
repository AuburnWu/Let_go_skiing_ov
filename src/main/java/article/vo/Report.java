package article.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Report implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reportID;
	private Integer articleID;
	private Integer userID;
	private String reportContent;
	private String reportStatus;
	private String reportResponse;
	//
	public Integer getReportID() {
		return reportID;
	}
	public void setReportID(Integer reportID) {
		this.reportID = reportID;
	}
	public Integer getArticleID() {
		return articleID;
	}
	public void setArticleID(Integer articleID) {
		this.articleID = articleID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public String getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	public String getReportResponse() {
		return reportResponse;
	}
	public void setReportResponse(String reportResponse) {
		this.reportResponse = reportResponse;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//
	public Report(Integer reportID, Integer articleID, Integer userID, String reportContent, String reportStatus,
			String reportResponse) {
		super();
		this.reportID = reportID;
		this.articleID = articleID;
		this.userID = userID;
		this.reportContent = reportContent;
		this.reportStatus = reportStatus;
		this.reportResponse = reportResponse;
	}
	//
	@Override
	public String toString() {
		return "Report [reportID=" + reportID + ", articleID=" + articleID + ", userID=" + userID + ", reportContent="
				+ reportContent + ", reportStatus=" + reportStatus + ", reportResponse=" + reportResponse + "]";
	}
	public Report() {
		// TODO Auto-generated constructor stub
	}

}
