package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Update;


public class UpdateDB {
	Connection conn = null;
	Statement stmt = null;
	
	public UpdateDB(){
		try {
			conn = DBUtils.getConnection();
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("数据库连接出错");
			e.printStackTrace();
		}
	}
	
	public void close() {
		DBUtils.close(stmt, conn);
	}

	// 查询应用更新信息
	public Update getUpdateInfo() throws IOException {
		Update update = null;
		String sql = "SELECT * FROM `t_update`;";
		ResultSet rs = null;
		try {
			Statement pstmt = conn.createStatement();  
			rs = pstmt.executeQuery(sql);
			if(rs.next()){
				int appVersion = rs.getInt("appVersion");
				String appDescribe = rs.getString("appDescribe");
				String appUrl = rs.getString("appUrl");
				int showScansSensor = rs.getInt("showScansSensor");
				
				update = new Update();
				update.setAppVersion(appVersion);
				update.setAppDescribe(appDescribe);
				update.setAppUrl(appUrl);
				update.setShowScansSensor(showScansSensor);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}
		
		return update;
	}
	
	// 修改更新信息
	public boolean changeUpdateInfo(Update update) throws IOException {
		String sql = "UPDATE t_update SET appDescribe=?,appVersion=?,appUrl=?,showScansSensor=?";
		boolean flag = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);  
			pstmt.setString(1, update.getAppDescribe());  
			pstmt.setInt(2, update.getAppVersion());  
			pstmt.setString(3, update.getAppUrl());  
			pstmt.setInt(4, update.getShowScansSensor());  
			int rs = pstmt.executeUpdate(); 
			if(rs > 0){
				flag = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}
		return flag;
	}

}
