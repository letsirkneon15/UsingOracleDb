package ems.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMgr {

	public void setJT400Driver(){
		try{
			Class.forName("com.ibm.as400.access.AS400JDBCDriver");
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public Connection connectToAS400(String url, String id, String password){
		Connection conn=null;
		this.setJT400Driver();
		try{
			conn = DriverManager.getConnection(url, id, password);
		} catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeAS400Connection(Connection conn){
		if (conn!=null){
			try{
				conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void setOracleDriver(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public Connection connectToOracle(String url, String id, String password){
		Connection conn=null;
		this.setOracleDriver();
		try{
			conn = DriverManager.getConnection(url, id, password);
		} catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeOracleConnection(Connection conn){
		if (conn!=null){
			try{
				conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
