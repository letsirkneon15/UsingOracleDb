package ems.pojo;

public class EMSQuotationDataPDName {
	private int qdMonth;
	private int qdYear;
	private String qdCust;
	private String qdPdName;
	private String qdFileName;
	private int qdDeadline;
	private String qdUserId;
	private String qdStatus;


	public EMSQuotationDataPDName() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EMSQuotationDataPDName(int qdMonth, int qdYear, String qdCust, String qdPdName, String qdFileName,
			int qdDeadline, String qdUserId, String qdStatus) {
		this.qdMonth = qdMonth;
		this.qdYear = qdYear;
		this.qdCust = qdCust;
		this.qdPdName = qdPdName;
		this.qdFileName = qdFileName;
		this.qdDeadline = qdDeadline;
		this.qdUserId = qdUserId;
		this.qdStatus = qdStatus;
	}


	public int getQdMonth() {
		return qdMonth;
	}


	public void setQdMonth(int qdMonth) {
		this.qdMonth = qdMonth;
	}


	public int getQdYear() {
		return qdYear;
	}


	public void setQdYear(int qdYear) {
		this.qdYear = qdYear;
	}


	public String getQdCust() {
		return qdCust;
	}


	public void setQdCust(String qdCust) {
		this.qdCust = qdCust;
	}


	public String getQdPdName() {
		return qdPdName;
	}


	public void setQdPdName(String qdPdName) {
		this.qdPdName = qdPdName;
	}


	public String getQdFileName() {
		return qdFileName;
	}


	public void setQdFileName(String qdFileName) {
		this.qdFileName = qdFileName;
	}


	public int getQdDeadline() {
		return qdDeadline;
	}


	public void setQdDeadline(int qdDeadline) {
		this.qdDeadline = qdDeadline;
	}


	public String getQdUserId() {
		return qdUserId;
	}


	public void setQdUserId(String qdUserId) {
		this.qdUserId = qdUserId;
	}


	public String getQdStatus() {
		return qdStatus;
	}


	public void setQdStatus(String qdStatus) {
		this.qdStatus = qdStatus;
	}
	
	
}
