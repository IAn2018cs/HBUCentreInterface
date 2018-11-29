package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Login;
import model.User;

public class LoginDB {
	Connection conn = null;
	Statement stmt = null;

	public LoginDB() {
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

	// 登录
	public boolean login(Login login) throws IOException {
		boolean flag = false;
		String sql = "SELECT * FROM t_login WHERE Account = ? AND Password = ?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login.getAccount());
			pstmt.setString(2, login.getPassword());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int type = rs.getInt("Type");
				login.setType(type);

				String name = rs.getString("Name");
				login.setName(name);

				int gradeCode = rs.getInt("GradeCode");
				login.setGradeCode(gradeCode);

				String classDes = rs.getString("ClassDescription");
				login.setClassDes(classDes);

				String newImage = rs.getString("NewImage");
				login.setNewImage(newImage);

				String oldImage = rs.getString("OldImage");
				login.setOldImage(oldImage);

				int group = rs.getInt("GroupCode");
				login.setGroup(group);
				
				String phone = rs.getString("Phone");
				if(phone == null){
					login.setPhone("114");
				} else {
					login.setPhone(phone);
				}
				
				int interestGroup = rs.getInt("InterestGroupCode");
				login.setInterestGroupCode(interestGroup);

				flag = true;
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}
		return flag;
	}

	// 获取组名单
	public List<User> getUserByGroup(int groupCode, int gradeCode)
			throws IOException {
		List<User> list = new ArrayList<User>();
		String sql = "SELECT * FROM t_login WHERE GroupCode=?";
		if (gradeCode > 0) {
			sql += " AND GradeCode=?";
		}

		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, groupCode);
			if (gradeCode > 0) {
				pstmt.setInt(2, gradeCode);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String account = rs.getString("Account");
				String name = rs.getString("Name");
				int grade = rs.getInt("GradeCode");
				String classDescription = rs.getString("ClassDescription");
				int group = rs.getInt("GroupCode");
				String phone = rs.getString("Phone");
				int type = rs.getInt("Type");

				User user = new User(account, name, grade, classDescription,
						group, phone);
				user.setType(type);
				list.add(user);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return list;
	}

	// 根据学号获取姓名
	public String getName(String account) throws IOException {
		String sql = "SELECT * FROM t_login WHERE Account = ?";
		String name = "";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				name = rs.getString("Name");
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}
		return name;
	}

	// 获取维修人电话
	public User getRepairPhone() throws IOException {
		String sql = "SELECT * FROM t_login WHERE Type=5";
		User user = null;
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				user = new User();
				user.setPhone(rs.getString("Phone"));
				user.setName(rs.getString("Name"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}
		return user;
	}

	// 修改密码
	public boolean changePass(String account, String pass) throws IOException {
		String sql = "UPDATE t_login SET Password=? WHERE Account=?";
		boolean flag = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setString(2, account);
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

	// 修改用户类型
	public boolean changeType(String account, int type) throws IOException {
		String sql = "UPDATE t_login SET Type=? WHERE Account=?";
		boolean flag = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			pstmt.setString(2, account);
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

	// 修改用户的兴趣小组
	public boolean changeInterestGroup(String account, int interestGroup) throws IOException {
		String sql = "UPDATE t_login SET InterestGroupCode=? WHERE Account=?";
		boolean flag = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, interestGroup);
			pstmt.setString(2, account);
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

	// 修改个人信息
	public boolean changeInfo(String account, String name, int gradeCode,
			String classDes, int groupCode, String phone, String imageUrl)
			throws IOException {
		String sql = "UPDATE t_login SET Name=?,GradeCode=?,ClassDescription=?,GroupCode=?,Phone=?,NewImage=? WHERE Account=?";
		boolean flag = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, gradeCode);
			pstmt.setString(3, classDes);
			pstmt.setInt(4, groupCode);
			pstmt.setString(5, phone);
			pstmt.setString(6, imageUrl);
			pstmt.setString(7, account);
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

	// 注册
	public boolean signUp(String account, String pass, User user)
			throws IOException {
		String sqlQ = "SELECT * FROM t_login WHERE Account = ?";
		String sql = "INSERT INTO t_login (Account,Password,type,Name,GradeCode,ClassDescription,NewImage,OldImage,GroupCode,Phone) VALUES (?,?,0,?,?,?,'null','null',?,?)";
		boolean flag = false;
		try {
			PreparedStatement pstmtQ = conn.prepareStatement(sqlQ);
			pstmtQ.setString(1, account);
			ResultSet rsQ = pstmtQ.executeQuery();
			if (rsQ.next()) {
				flag = false;
				rsQ.close();
				pstmtQ.close();
			} else {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, account);
				pstmt.setString(2, pass);
				pstmt.setString(3, user.getName());
				pstmt.setInt(4, user.getGrade());
				pstmt.setString(5, user.getClas());
				pstmt.setInt(6, user.getGroupCode());
				pstmt.setString(7, user.getPhone());
				int rs = pstmt.executeUpdate();
				if (rs > 0) {
					flag = true;
				}
				pstmt.close();
			}

		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}
		return flag;
	}
}
