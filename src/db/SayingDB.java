package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Saying;


public class SayingDB {
	Connection conn = null;
	Statement stmt = null;
	
	public SayingDB(){
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

	// 查询名言
	public List<Saying> getSaying() throws IOException {
		List<Saying> list = new ArrayList<Saying>();
		String sql = "SELECT * FROM t_saying";
		ResultSet rs = null;
		try {
			Statement pstmt = conn.createStatement();  
			rs = pstmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("id");
				String content = rs.getString("content");
				
				Saying saying = new Saying(id, content);
				
				list.add(saying);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}
		
		return list;
	}
}
