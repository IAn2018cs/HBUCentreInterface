package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Repair;

public class RepairDB {
	Connection conn = null;
	Statement stmt = null;

	public RepairDB() {
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

	// 插入一条维修信息
	public boolean addRepair(Repair repair) throws IOException {
		String sql = "INSERT INTO t_repair (Account,Article,Handle,Time,Description) VALUES (?,?,?,?,?);";
		boolean flag = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, repair.getAccount());
			pstmt.setString(2, repair.getArticle());
			pstmt.setString(3, repair.getHandle());
			pstmt.setString(4, repair.getTime());
			pstmt.setString(5, repair.getDescription());
			int rs = pstmt.executeUpdate();
			
			if (rs > 0) {
				flag = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}
		return flag;
	}

	// 查询维修记录
	public List<Repair> getRepairList() throws IOException {
		List<Repair> list = new ArrayList<Repair>();
		String sql = "SELECT "
				+ "Nid, "
				+ "Account, "
				+ "(SELECT Name FROM t_login WHERE t_login.Account = t_repair.Account) AccountName, "
				+ "Article, "
				+ "Handle, "
				+ "(SELECT Name FROM t_login WHERE t_login.Account = t_repair.Handle) HandleName, "
				+ "Time, "
				+ "Description "
				+ "FROM `t_repair`;";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int nid = rs.getInt("Nid");
				String accoun = rs.getString("Account");
				String article = rs.getString("Article");
				String handle = rs.getString("Handle");
				String time = rs.getString("Time");
				String description = rs.getString("Description");
				String accountName = rs.getString("AccountName");
				String handleName = rs.getString("HandleName");

				Repair repair = new Repair(nid,accoun,article,handle,time,description);
				repair.setAccountName(accountName);
				repair.setHandleName(handleName);
				
				list.add(repair);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return list;
	}
}
