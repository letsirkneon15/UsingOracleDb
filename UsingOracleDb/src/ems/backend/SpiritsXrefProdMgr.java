package ems.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SpiritsXrefProdMgr {

	public String getEmailbySpiritsId(String spiritsId, Connection conn){
		
		String emailAdd = " ";
		
		try{
			String sql = "SELECT OUTLOOKMAIL FROM USSO_SPIRITS_XREF_PROD WHERE SPIRITSID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, spiritsId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				emailAdd = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return emailAdd;
	}
}
