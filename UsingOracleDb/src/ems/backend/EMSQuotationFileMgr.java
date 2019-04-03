package ems.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ems.pojo.EMSQuotationFile;

public class EMSQuotationFileMgr {

	public List<EMSQuotationFile> getQuoteFile(String status, Connection conn) {

		List<EMSQuotationFile> emsArr = new ArrayList<EMSQuotationFile>();

		try {

			String queryString = "SELECT * FROM USSO_EMS_QUOTATION_FILE WHERE QFSTATUS = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(queryString);
			preparedStatement.setString(1, status);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				EMSQuotationFile ems = new EMSQuotationFile();
				ems.setQfYear(rs.getInt(1));
				ems.setQfMonth(rs.getInt(2));
				ems.setQfCust(rs.getString(3));
				ems.setQfFileName(rs.getString(4));
				ems.setQfCrtUsr(rs.getString(5));
				ems.setQfCrtDate(rs.getInt(6));
				ems.setQfCrtTime(rs.getInt(7));
				ems.setQfUpdUsr(rs.getString(8));
				ems.setQfUpdDate(rs.getInt(9));
				ems.setQfUpdTime(rs.getInt(10));
				ems.setQfDeadline(rs.getInt(11));
				ems.setQfUserId(rs.getString(12));
				ems.setQfStatus(rs.getString(13));
				emsArr.add(ems);
			}
			rs.close();
			preparedStatement.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return emsArr;

	}

	public boolean updateQuoteFile(int qfMonth, int qfYear, String qfCust, String qfFileName, int qfDeadline,
			String qfUserId, String status, Connection conn) {

		boolean isUpdated = false;

		String updateString = "UPDATE USSO_EMS_QUOTATION_FILE SET QFSTATUS = ? WHERE QFMONTH = ? AND QFYEAR = ?"
				+ " AND QFCUST = ? AND QFFILENAME = ? AND QFDEADLINE = ? AND QFUSERID = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(updateString);
			ps.setString(1, status);
			ps.setInt(2, qfMonth);
			ps.setInt(3, qfYear);
			ps.setString(4, qfCust);
			ps.setString(5, qfFileName);
			ps.setInt(6, qfDeadline);
			ps.setString(7, qfUserId);
			ps.execute();
			ps.close();
			isUpdated = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isUpdated;

	}
}
