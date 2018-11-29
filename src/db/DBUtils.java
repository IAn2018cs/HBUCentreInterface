package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://123.206.57.216:3306/hbu_centre_test?useUnicode=true&amp;characterEncoding=UTF-8";
	private static String USERNAME = "cs";
	private static String PASSWORD = "chenshuaide";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭conn
	public static void close(Connection conn) {
		// 关闭前先判断链接对象是否存在
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 关闭Statement
	public static void close(Statement stmt) {
		if (null != stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 关闭ResultSet
	public static void close(ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 关闭执行Select的JDBC资源
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		close(rs);
		close(stmt, conn);
	}

	// 关闭执行Insert Update Delete的JDBC资源
	public static void close(Statement stmt, Connection conn) {
		close(stmt);
		close(conn);
	}

}
