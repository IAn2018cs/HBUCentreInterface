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

import model.Rank;

public class RankDB {
	Connection conn = null;
	Statement stmt = null;

	public RankDB() {
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

	// 添加排名信息
	public boolean addRank(String group, String week, String ranks)
			throws IOException {
		boolean flag = false;
		String sql = "INSERT INTO t_rank (Account,GroupCode,Rank,WeekID) VALUES";

		String[] rankInfos = ranks.split("#");
		for (int i = 0; i < rankInfos.length; i++) {
			String[] rankInfo = rankInfos[i].split("-");
			String rank = rankInfo[0];
			String account = rankInfo[1];

			if (i == rankInfos.length - 1) {
				sql += " ('" + account + "'," + group + "," + rank + "," + week
						+ ");";
			} else {
				sql += " ('" + account + "'," + group + "," + rank + "," + week
						+ "),";
			}
		}
		System.out.println(sql);

		try {
			int executeUpdate = stmt.executeUpdate(sql);
			if (executeUpdate > 0) {
				flag = true;
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return flag;
	}

	// 获取组内排名
	public List<Rank> getRankList(String weekId, String group)
			throws IOException {
		List<Rank> list = new ArrayList<Rank>();
		String sql = "SELECT t_login.Name,t_rank.Rank FROM t_rank,t_login WHERE t_rank.Account=t_login.Account AND t_rank.GroupCode=? AND t_rank.WeekID=? ORDER BY t_rank.Rank ASC;";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, group);
			pstmt.setString(2, weekId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("Name");
				int ran = rs.getInt("Rank");

				Rank rank = new Rank(name, ran);

				list.add(rank);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return list;
	}

	// 获取个人排名
	public int getRankForMe(String account)
			throws IOException {
		int rank = -1;
		String sqlTime = "SELECT Nid FROM t_dic_week WHERE ? >= StartDate AND ? <= EndDate";
		String sql = "SELECT Rank FROM t_rank WHERE Account=? AND WeekID=?;";
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			// 获取当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = df.format(new Date());
			PreparedStatement pstmt = conn.prepareStatement(sqlTime);
			pstmt.setString(1, time);
			pstmt.setString(2, time);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int week = rs.getInt("Nid");
				
				PreparedStatement pstmt2 = conn.prepareStatement(sql);
				pstmt2.setString(1, account);
				pstmt2.setInt(2, week);
				rs2 = pstmt2.executeQuery();
				if(rs2.next()) {
					rank = rs2.getInt("Rank");
				}
				rs2.close();
				pstmt2.close();
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return rank;
	}
}
