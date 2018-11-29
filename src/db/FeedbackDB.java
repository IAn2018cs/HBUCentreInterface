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

import model.Feedback;


public class FeedbackDB {
	Connection conn = null;
	Statement stmt = null;
	
	public FeedbackDB(){
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

	// 查询所有反馈信息
	public List<Feedback> getFeedBack() throws IOException {
		List<Feedback> list = new ArrayList<Feedback>();
		String sql = "SELECT "
				+ "t_feedback.id, "
				+ "t_feedback.account, "
				+ "t_feedback.msg, "
				+ "t_feedback.PhoneBrand, "
				+ "t_feedback.PhoneBrandType, "
				+ "t_feedback.AndroidVersion, "
				+ "t_feedback.Anonymous, "
				+ "t_feedback.Time, "
				+ "t_login.`Name`, "
				+ "t_login.GroupCode, "
				+ "t_login.GradeCode "
				+ "FROM "
				+ "`t_feedback` "
				+ "INNER JOIN t_login ON t_login.Account = t_feedback.account;";
		ResultSet rs = null;
		try {
			Statement pstmt = conn.createStatement();  
			rs = pstmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("id");
				String account = rs.getString("account");
				String msg = rs.getString("msg");
				String PhoneBrand = rs.getString("PhoneBrand");
				String PhoneBrandType = rs.getString("PhoneBrandType");
				String AndroidVersion = rs.getString("AndroidVersion");
				int anonymous = rs.getInt("Anonymous");
				String time = rs.getString("Time");
				String name = rs.getString("Name");
				int groupCode = rs.getInt("GroupCode");
				int gradeCode = rs.getInt("GradeCode");
				
				Feedback feedback = new Feedback(id,account, msg, PhoneBrand, PhoneBrandType, AndroidVersion,anonymous,time);
				feedback.setName(name);
				feedback.setGroupCode(groupCode);
				feedback.setGradeCode(gradeCode);
				
				list.add(feedback);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 插入一条反馈信息
	public boolean upFeedback(Feedback feedback) throws IOException {
		String sql = "INSERT INTO t_feedback (account,msg,PhoneBrand,PhoneBrandType,AndroidVersion,Anonymous,Time) VALUES (?,?,?,?,?,?,?);";
		// 获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
		boolean flag = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);  
			pstmt.setString(1, feedback.getAccount());  
			pstmt.setString(2, feedback.getMsg());  
			pstmt.setString(3, feedback.getPhoneBrand());  
			pstmt.setString(4, feedback.getPhoneBrandType());  
			pstmt.setString(5, feedback.getAndroidVersion());  
			pstmt.setInt(6, feedback.getAnonymous());  
			pstmt.setString(7, time);
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
