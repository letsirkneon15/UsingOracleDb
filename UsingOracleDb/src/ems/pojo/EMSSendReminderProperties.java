package ems.pojo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EMSSendReminderProperties {
	    
	    private String as400url;
		private String as400id;
		private String as400pw;
		private String as400server;
		private String as400driver;
		private String oracleurl;
		private String oracleid;
		private String oraclepw;
		private String oracledriver;
		private String ulcllib20;
		private String ussolib20;
		private String ulcllib21;
		private String ussolib21;
		private String ulcllib24;
		private String ussolib24;
		private String ulcllib25;
		private String ussolib25;
		private String osa20;
		private String osa21;
		private String osa24;
		private String osa25;
		
		public void getProperties() throws IOException{
			Properties progProperties = new Properties();
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("EMSSendReminder.properties");
			progProperties.load(in);
		    this.setAs400url(progProperties.getProperty("as400url"));
		    this.setAs400id(progProperties.getProperty("as400id"));
		    this.setAs400pw(progProperties.getProperty("as400pw"));
		    this.setAs400server(progProperties.getProperty("as400url"));
		    this.setAs400driver(progProperties.getProperty("as400driver"));
		    this.setOracleurl(progProperties.getProperty("oracleurl"));
		    this.setOracleid(progProperties.getProperty("oracleid"));
		    this.setOraclepw(progProperties.getProperty("oraclepw"));
		    this.setOracledriver(progProperties.getProperty("oracledriver"));
		    this.setUlcllib20(progProperties.getProperty("ulcllib20"));
		    this.setUssolib20(progProperties.getProperty("ussolib20"));
		    this.setUlcllib21(progProperties.getProperty("ulcllib21"));
		    this.setUssolib21(progProperties.getProperty("ussolib21"));
		    this.setUlcllib24(progProperties.getProperty("ulcllib24"));
		    this.setUssolib24(progProperties.getProperty("ussolib24"));
		    this.setUlcllib25(progProperties.getProperty("ulcllib25"));
		    this.setUssolib25(progProperties.getProperty("ussolib25"));
		    this.setOsa20(progProperties.getProperty("osa20"));
		    this.setOsa21(progProperties.getProperty("osa21"));
		    this.setOsa24(progProperties.getProperty("osa24"));
		    this.setOsa25(progProperties.getProperty("osa25"));
			
		}
		
		public String getAs400url() {
			return as400url;
		}
		public void setAs400url(String as400url) {
			this.as400url = as400url;
		}
		public String getAs400id() {
			return as400id;
		}
		public void setAs400id(String as400id) {
			this.as400id = as400id;
		}
		public String getAs400pw() {
			return as400pw;
		}
		public void setAs400pw(String as400pw) {
			this.as400pw = as400pw;
		}
		public String getAs400server() {
			return as400server;
		}
		public void setAs400server(String as400server) {
			this.as400server = as400server;
		}
		public String getAs400driver() {
			return as400driver;
		}
		public void setAs400driver(String as400driver) {
			this.as400driver = as400driver;
		}
		public String getOracleurl() {
			return oracleurl;
		}
		public void setOracleurl(String oracleurl) {
			this.oracleurl = oracleurl;
		}
		public String getOracleid() {
			return oracleid;
		}
		public void setOracleid(String oracleid) {
			this.oracleid = oracleid;
		}
		public String getOraclepw() {
			return oraclepw;
		}
		public void setOraclepw(String oraclepw) {
			this.oraclepw = oraclepw;
		}
		public String getOracledriver() {
			return oracledriver;
		}
		public void setOracledriver(String oracledriver) {
			this.oracledriver = oracledriver;
		}
		public String getUlcllib20() {
			return ulcllib20;
		}
		public void setUlcllib20(String ulcllib20) {
			this.ulcllib20 = ulcllib20;
		}
		public String getUssolib20() {
			return ussolib20;
		}
		public void setUssolib20(String ussolib20) {
			this.ussolib20 = ussolib20;
		}
		public String getUlcllib21() {
			return ulcllib21;
		}
		public void setUlcllib21(String ulcllib21) {
			this.ulcllib21 = ulcllib21;
		}
		public String getUssolib21() {
			return ussolib21;
		}
		public void setUssolib21(String ussolib21) {
			this.ussolib21 = ussolib21;
		}
		public String getUlcllib24() {
			return ulcllib24;
		}
		public void setUlcllib24(String ulcllib24) {
			this.ulcllib24 = ulcllib24;
		}
		public String getUssolib24() {
			return ussolib24;
		}
		public void setUssolib24(String ussolib24) {
			this.ussolib24 = ussolib24;
		}
		public String getUlcllib25() {
			return ulcllib25;
		}
		public void setUlcllib25(String ulcllib25) {
			this.ulcllib25 = ulcllib25;
		}
		public String getUssolib25() {
			return ussolib25;
		}
		public void setUssolib25(String ussolib25) {
			this.ussolib25 = ussolib25;
		}
		public String getOsa20() {
			return osa20;
		}
		public void setOsa20(String osa20) {
			this.osa20 = osa20;
		}
		public String getOsa21() {
			return osa21;
		}
		public void setOsa21(String osa21) {
			this.osa21 = osa21;
		}
		public String getOsa24() {
			return osa24;
		}
		public void setOsa24(String osa24) {
			this.osa24 = osa24;
		}
		public String getOsa25() {
			return osa25;
		}
		public void setOsa25(String osa25) {
			this.osa25 = osa25;
		}
	
}
