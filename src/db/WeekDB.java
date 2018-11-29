package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Week;

public class WeekDB {
	Connection conn = null;
	Statement stmt = null;

	public WeekDB() {
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

	// 获取周列表
	public List<Week> getWeekList() throws IOException {
		List<Week> list = new ArrayList<Week>();
		String sql = "SELECT * FROM t_dic_week WHERE ? >= StartDate AND TermID = 2";
		ResultSet rs = null;
		try {
			// 获取当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = df.format(new Date());

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, time);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int nid = rs.getInt("Nid");
				String startDate = rs.getString("StartDate");
				String endDate = rs.getString("EndDate");
				int weekCode = rs.getInt("WeekCode");

				Week week = new Week(nid, startDate, endDate, weekCode);

				list.add(week);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return list;
	}

	// 获取本周信息
	public Week getWeek() throws IOException {
		Week week = null;
		String sql = "SELECT * FROM t_dic_week WHERE ? >= StartDate AND ? <= EndDate";
		ResultSet rs = null;
		try {
			// 获取当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = df.format(new Date());

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, time);
			pstmt.setString(2, time);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int nid = rs.getInt("Nid");
				String startDate = rs.getString("StartDate");
				String endDate = rs.getString("EndDate");
				int weekCode = rs.getInt("WeekCode");

				week = new Week(nid, startDate, endDate, weekCode);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return week;
	}
}
