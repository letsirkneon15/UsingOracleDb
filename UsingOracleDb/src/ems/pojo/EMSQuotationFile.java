package ems.pojo;

public class EMSQuotationFile {

	private int qfYear;
	private int qfMonth;
	private String qfCust;
	private String qfFileName;
	private String qfCrtUsr;
	private int qfCrtDate;
	private int qfCrtTime;
	private String qfUpdUsr;
	private int qfUpdDate;
	private int qfUpdTime;
	private int qfDeadline;
	private String qfUserId;
	private String qfStatus;
	
	
	public EMSQuotationFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EMSQuotationFile(int qfYear, int qfMonth, String qfCust, String qfFileName, String qfCrtUsr, int qfCrtDate,
			int qfCrtTime, String qfUpdUsr, int qfUpdDate, int qfUpdTime, int qfDeadline, String qfUserId,
			String qfStatus) {
		this.qfYear = qfYear;
		this.qfMonth = qfMonth;
		this.qfCust = qfCust;
		this.qfFileName = qfFileName;
		this.qfCrtUsr = qfCrtUsr;
		this.qfCrtDate = qfCrtDate;
		this.qfCrtTime = qfCrtTime;
		this.qfUpdUsr = qfUpdUsr;
		this.qfUpdDate = qfUpdDate;
		this.qfUpdTime = qfUpdTime;
		this.qfDeadline = qfDeadline;
		this.qfUserId = qfUserId;
		this.qfStatus = qfStatus;
	}
	
	public int getQfYear() {
		return qfYear;
	}
	public void setQfYear(int qfYear) {
		this.qfYear = qfYear;
	}
	public int getQfMonth() {
		return qfMonth;
	}
	public void setQfMonth(int qfMonth) {
		this.qfMonth = qfMonth;
	}
	public String getQfCust() {
		return qfCust;
	}
	public void setQfCust(String qfCust) {
		this.qfCust = qfCust;
	}
	public String getQfFileName() {
		return qfFileName;
	}
	public void setQfFileName(String qfFileName) {
		this.qfFileName = qfFileName;
	}
	public String getQfCrtUsr() {
		return qfCrtUsr;
	}
	public void setQfCrtUsr(String qfCrtUsr) {
		this.qfCrtUsr = qfCrtUsr;
	}
	public int getQfCrtDate() {
		return qfCrtDate;
	}
	public void setQfCrtDate(int qfCrtDate) {
		this.qfCrtDate = qfCrtDate;
	}
	public int getQfCrtTime() {
		return qfCrtTime;
	}
	public void setQfCrtTime(int qfCrtTime) {
		this.qfCrtTime = qfCrtTime;
	}
	public String getQfUpdUsr() {
		return qfUpdUsr;
	}
	public void setQfUpdUsr(String qfUpdUsr) {
		this.qfUpdUsr = qfUpdUsr;
	}
	public int getQfUpdDate() {
		return qfUpdDate;
	}
	public void setQfUpdDate(int qfUpdDate) {
		this.qfUpdDate = qfUpdDate;
	}
	public int getQfUpdTime() {
		return qfUpdTime;
	}
	public void setQfUpdTime(int qfUpdTime) {
		this.qfUpdTime = qfUpdTime;
	}
	public int getQfDeadline() {
		return qfDeadline;
	}
	public void setQfDeadline(int qfDeadline) {
		this.qfDeadline = qfDeadline;
	}
	public String getQfUserId() {
		return qfUserId;
	}
	public void setQfUserId(String qfUserId) {
		this.qfUserId = qfUserId;
	}
	public String getQfStatus() {
		return qfStatus;
	}
	public void setQfStatus(String qfStatus) {
		this.qfStatus = qfStatus;
	}
	
	
	
}
