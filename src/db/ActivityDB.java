package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Activity;
import model.BackDuty;

public class ActivityDB {
	private Connection connection = null;
	private Statement stmt = null;

	public ActivityDB() {
		try {
			connection = DBUtils.getConnection();
			stmt = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		DBUtils.close(stmt, connection);
	}

	// 根据云子id获取活动 有固定值班表的情况
	/*
	 * 活动类型 1--活动 2--值班 3--培训 4--跑操 5--晨读
	 */
	public List<Activity> getActivityById(String id, String studentNum) {
		List<Activity> list = new ArrayList<>();
		String sql = "SELECT * FROM v_sensoro_active WHERE SensoroId=? AND (Rule=1 OR Rule=3 OR Rule=4 OR Rule=5 OR StudentNum=?)";
		String sql2 = "SELECT * FROM v_sensoro_active WHERE SensoroId=?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt;
			if (!studentNum.equals("")) {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, studentNum);
			} else {
				pstmt = connection.prepareStatement(sql2);
				pstmt.setString(1, id);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Activity activity = new Activity();
				activity.setId(rs.getInt("ActivityId"));
				activity.setAccount(rs.getString("Account"));
				activity.setActivityName(rs.getString("ActivityName"));
				activity.setActivityDes(rs.getString("ActivityDescription"));
				activity.setTime(rs.getString("Time"));
				activity.setEndTime(rs.getString("EndTime"));
				activity.setLocation(rs.getString("Location"));
				activity.setRule(rs.getInt("Rule"));
				activity.setDisplay(rs.getInt("Display"));
				activity.setStudentNum(rs.getString("StudentNum"));
				activity.setBackTo(rs.getInt("BackTo"));
				activity.setWeek(rs.getInt("Week"));
				list.add(activity);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库结果集出错");
		}
		return list;
	}

	// 根据云子id获取活动，无值班表
	public List<Activity> getActivityByIdForNoDuty(String id) {
		List<Activity> list = new ArrayList<>();
		String sql = "SELECT * FROM t_activity INNER JOIN t_sensoro ON t_activity.Nid=t_sensoro.ActivityId WHERE SensoroId=?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Activity activity = new Activity();
				activity.setId(rs.getInt("ActivityId"));
				activity.setAccount(rs.getString("Account"));
				activity.setActivityName(rs.getString("ActivityName"));
				activity.setActivityDes(rs.getString("ActivityDescription"));
				activity.setTime(rs.getString("Time"));
				activity.setEndTime(rs.getString("EndTime"));
				activity.setLocation(rs.getString("Location"));
				activity.setRule(rs.getInt("Rule"));
				activity.setDisplay(rs.getInt("Display"));
				activity.setBackTo(0);
				activity.setWeek(0);
				list.add(activity);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库结果集出错");
		}
		return list;
	}

	// 添加活动
	public boolean addActivity(Activity activity, String sensoroId) {
		String instAciSql = "INSERT INTO t_activity (Account,ActivityName,ActivityDescription,Time,EndTime,Location,Rule,Display) VALUES (?,?,?,?,?,?,?,1);";
		String qurSql = "select @@identity as currentID;";
		String instSensoroSql = "INSERT INTO t_sensoro (SensoroId,ActivityId) VALUES (?,?);";

		boolean flag = false;
		try {
			PreparedStatement pstmt = connection.prepareStatement(instAciSql);
			pstmt.setString(1, activity.getAccount());
			pstmt.setString(2, activity.getActivityName());
			pstmt.setString(3, activity.getActivityDes());
			pstmt.setString(4, activity.getTime());
			pstmt.setString(5, activity.getEndTime());
			pstmt.setString(6, activity.getLocation());
			pstmt.setString(7, activity.getRule() + "");
			int rs = pstmt.executeUpdate();
			int id = 0;
			if (rs > 0) {
				ResultSet resultSet = stmt.executeQuery(qurSql);
				if (resultSet.next()) {
					id = resultSet.getInt("currentID");
				}
				PreparedStatement pstmt2 = connection
						.prepareStatement(instSensoroSql);
				pstmt2.setString(1, sensoroId);
				pstmt2.setInt(2, id);
				int update = pstmt2.executeUpdate();
				if (update > 0) {
					flag = true;
				}
				resultSet.close();
				pstmt2.close();
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错" + e.toString());
			e.printStackTrace();
		}
		return flag;
	}

	// 添加补班
	public boolean addBackDuty(Activity activity, BackDuty backDuty) {
		String instAciSql = "INSERT INTO t_activity (Account,ActivityName,ActivityDescription,Time,EndTime,Location,Rule,Display) VALUES (?,?,'请准时进行签到，值班结束务必签离，离开时将桌椅摆放整齐',?,?,'B3-123',2,1);";
		String qurSql = "select @@identity as currentID;";
		String instSensoroSql = "INSERT INTO t_sensoro (SensoroId,ActivityId) VALUES ('0117C535AB3C',?);";
		String instDutySql = "INSERT INTO t_onduty (StudentNum,ActivityId,BackTo,Week) VALUES (?,?,1,?);";
		String instBackDutySql = "INSERT INTO t_back_duty (Account,LeaveTime,LeaveActiveID,BackTime,BackActiveID,ActiveID) VALUES (?,?,?,?,?,?);";

		boolean flag = false;
		try {
			// 插入活动表
			PreparedStatement pstmt = connection.prepareStatement(instAciSql);
			pstmt.setString(1, activity.getAccount());
			pstmt.setString(2, activity.getActivityName());
			pstmt.setString(3, activity.getTime());
			pstmt.setString(4, activity.getEndTime());

			int rs = pstmt.executeUpdate();
			int id = 0;
			if (rs > 0) {
				// 获取刚插入的nid
				ResultSet resultSet = stmt.executeQuery(qurSql);
				if (resultSet.next()) {
					id = resultSet.getInt("currentID");
				}
				// 插入云子配置表
				PreparedStatement pstmt2 = connection
						.prepareStatement(instSensoroSql);
				pstmt2.setInt(1, id);
				int update = pstmt2.executeUpdate();

				// 插入值班表
				PreparedStatement pstmt3 = connection
						.prepareStatement(instDutySql);
				pstmt3.setString(1, activity.getStudentNum());
				pstmt3.setInt(2, id);
				pstmt3.setInt(3, activity.getWeek());
				int insert = pstmt3.executeUpdate();

				// 插入补班记录表
				PreparedStatement pstmt4 = connection
						.prepareStatement(instBackDutySql);
				pstmt4.setString(1, backDuty.getAccount());
				pstmt4.setString(2, backDuty.getLeaveTime());
				pstmt4.setInt(3, backDuty.getLeaveActiveID());
				pstmt4.setString(4, backDuty.getBackTime());
				pstmt4.setInt(5, backDuty.getBackActiveID());
				pstmt4.setInt(6, id);
				int insert2 = pstmt4.executeUpdate();

				if (update > 0 && insert > 0 && insert2 > 0) {
					flag = true;
				}
				resultSet.close();
				pstmt2.close();
				pstmt3.close();
				pstmt4.close();
			}
			stmt.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错" + e.toString());
			e.printStackTrace();
		}
		return flag;
	}

	// 修改活动
	public boolean changeActivity(Activity activity) throws IOException {
		String sql = "UPDATE t_activity SET ActivityName=?,ActivityDescription=?,Time=?,EndTime=?,Location=? WHERE Nid=?";
		boolean flag = false;
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, activity.getActivityName());
			pstmt.setString(2, activity.getActivityDes());
			pstmt.setString(3, activity.getTime());
			pstmt.setString(4, activity.getEndTime());
			pstmt.setString(5, activity.getLocation());
			pstmt.setInt(6, activity.getId());
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

	// 获取活动
	public List<Activity> getActivity(int rule, int account) {
		List<Activity> list = new ArrayList<>();
		String sql = "SELECT * FROM t_activity WHERE Rule = ?";
		if (account >= 0) {
			sql += " AND Account = ?";
		}

		ResultSet rs = null;
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, rule);
			if (account >= 0) {
				pstmt.setString(2, account + "");
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Activity activity = new Activity();
				activity.setId(rs.getInt("Nid"));
				activity.setAccount(rs.getString("Account"));
				activity.setActivityName(rs.getString("ActivityName"));
				activity.setActivityDes(rs.getString("ActivityDescription"));
				activity.setTime(rs.getString("Time"));
				activity.setEndTime(rs.getString("EndTime"));
				activity.setLocation(rs.getString("Location"));
				activity.setRule(rs.getInt("Rule"));
				activity.setDisplay(rs.getInt("Display"));
				list.add(activity);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库结果集出错");
		}
		return list;
	}

	// 删除活动（假删除，修改活动可见性）
	public boolean deleteActivityFalse(int id) throws IOException {
		String sql = "UPDATE t_activity SET Display=0 WHERE Nid=?";
		boolean flag = false;
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
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
}
