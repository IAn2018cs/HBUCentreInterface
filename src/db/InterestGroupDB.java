package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.InterestGroup;

public class InterestGroupDB {
	Connection conn = null;
	Statement stmt = null;

	public InterestGroupDB() {
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

	// 获取兴趣小组
	public List<InterestGroup> getInterestGroupList() throws IOException {
		List<InterestGroup> list = new ArrayList<InterestGroup>();
		String sql = "SELECT * FROM t_dic_interest_group";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int nid = rs.getInt("Nid");
				String interestGroup = rs.getString("InterestGroup");
				String description = rs.getString("Description");

				InterestGroup iGroup = new InterestGroup(nid,interestGroup,description);

				list.add(iGroup);
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
