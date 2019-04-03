package ems.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EMSPDInchargeMgr {
	
	public String getPDEmailAdd(String pdName, Connection conn){
		
		String pdEmailAdd = " ";
		
		try{
			String sql = "SELECT PDEMAIL FROM USSO_EMS_PD_INCHARGE WHERE PDNAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pdName);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				pdEmailAdd = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return pdEmailAdd;
		
	}
	
}
