package ems.pojo;

public class SpiritsXrefProd {

	private String firmCode;
	private String ussoId;
	private String spiritsId;
	private String outlookMail;
	
	public SpiritsXrefProd(String firmCode, String ussoId, String spiritsId, String outlookMail) {
		this.firmCode = firmCode;
		this.ussoId = ussoId;
		this.spiritsId = spiritsId;
		this.outlookMail = outlookMail;
	}
	
	public SpiritsXrefProd() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFirmCode() {
		return firmCode;
	}
	public void setFirmCode(String firmCode) {
		this.firmCode = firmCode;
	}
	public String getUssoId() {
		return ussoId;
	}
	public void setUssoId(String ussoId) {
		this.ussoId = ussoId;
	}
	public String getSpiritsId() {
		return spiritsId;
	}
	public void setSpiritsId(String spiritsId) {
		this.spiritsId = spiritsId;
	}
	public String getOutlookMail() {
		return outlookMail;
	}
	public void setOutlookMail(String outlookMail) {
		this.outlookMail = outlookMail;
	}
		
}
