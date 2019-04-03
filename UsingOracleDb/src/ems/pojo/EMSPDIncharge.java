package ems.pojo;

public class EMSPDIncharge {

	private String pdIdNum;
	private String pdName;
	private String pdEmail;
	private String pdCrtUsr;
	private int pdCrtDate;
	private int pdCrtTime;
	private String pdUpdUser;
	private int pdUpdDate;
	private int pdUpdTime;


	public EMSPDIncharge(String pdIdNum, String pdName, String pdEmail, String pdCrtUsr, int pdCrtDate, int pdCrtTime,
			String pdUpdUser, int pdUpdDate, int pdUpdTime) {
		this.pdIdNum = pdIdNum;
		this.pdName = pdName;
		this.pdEmail = pdEmail;
		this.pdCrtUsr = pdCrtUsr;
		this.pdCrtDate = pdCrtDate;
		this.pdCrtTime = pdCrtTime;
		this.pdUpdUser = pdUpdUser;
		this.pdUpdDate = pdUpdDate;
		this.pdUpdTime = pdUpdTime;
	}
	
	public EMSPDIncharge() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPdIdNum() {
		return pdIdNum;
	}
	public void setPdIdNum(String pdIdNum) {
		this.pdIdNum = pdIdNum;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public String getPdEmail() {
		return pdEmail;
	}
	public void setPdEmail(String pdEmail) {
		this.pdEmail = pdEmail;
	}
	public String getPdCrtUsr() {
		return pdCrtUsr;
	}
	public void setPdCrtUsr(String pdCrtUsr) {
		this.pdCrtUsr = pdCrtUsr;
	}
	public int getPdCrtDate() {
		return pdCrtDate;
	}
	public void setPdCrtDate(int pdCrtDate) {
		this.pdCrtDate = pdCrtDate;
	}
	public int getPdCrtTime() {
		return pdCrtTime;
	}
	public void setPdCrtTime(int pdCrtTime) {
		this.pdCrtTime = pdCrtTime;
	}
	public String getPdUpdUser() {
		return pdUpdUser;
	}
	public void setPdUpdUser(String pdUpdUser) {
		this.pdUpdUser = pdUpdUser;
	}
	public int getPdUpdDate() {
		return pdUpdDate;
	}
	public void setPdUpdDate(int pdUpdDate) {
		this.pdUpdDate = pdUpdDate;
	}
	public int getPdUpdTime() {
		return pdUpdTime;
	}
	public void setPdUpdTime(int pdUpdTime) {
		this.pdUpdTime = pdUpdTime;
	}

}
