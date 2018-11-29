package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import utils.Utils;
import model.HistoryActivity;
import model.OnDutySign;
import model.OnDutyTime;
import model.Sign;
import model.SignInfo;

public class SignDB {
	Connection conn = null;
	Statement stmt = null;

	public SignDB() {
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

	// 插入一条签到信息 返回该条信息的id
	public int signIn(Sign sign) throws IOException {
		String sql = "INSERT INTO t_sign (StudnetNum,InTime,OutTime,ActivityId,InLocation) VALUES (?,?,?,?,?);";
		String qurSql = "select @@identity as currentID;";
		int nid = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sign.getStudentNum());
			pstmt.setString(2, sign.getInTime());
			pstmt.setString(3, sign.getOutTime());
			pstmt.setInt(4, sign.getActivityId());
			pstmt.setString(5, sign.getInLocation());
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				ResultSet resultSet = stmt.executeQuery(qurSql);
				if (resultSet.next()) {
					nid = resultSet.getInt("currentID");
				}
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}
		return nid;
	}

	// 签离
	public boolean signOut(int id, String outTime, String outLocation) throws IOException {
		String sql = "UPDATE t_sign SET OutTime=?,OutLocation=? WHERE Nid=?";
		boolean flag = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, outTime);
			pstmt.setString(2, outLocation);
			pstmt.setInt(3, id);
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

	// 查询签到历史
	public List<HistoryActivity> getSignHistory(String num) throws IOException {
		List<HistoryActivity> list = new ArrayList<HistoryActivity>();
		String sql = "SELECT * FROM v_sign_activity WHERE StudnetNum=?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ActivityId");
				int rule = rs.getInt("Rule");
				String studnetNum = rs.getString("StudnetNum");
				String inTime = rs.getString("InTime");
				String outTime = rs.getString("OutTime");
				String activityDescription = rs
						.getString("ActivityDescription");
				String time = rs.getString("Time");
				String endTime = rs.getString("EndTime");
				String location = rs.getString("Location");
				String activityName = rs.getString("ActivityName");

				HistoryActivity activity = new HistoryActivity();
				activity.setId(id);
				activity.setRule(rule);
				activity.setStudentNum(studnetNum);
				activity.setInTime(inTime);
				activity.setOutTime(outTime);
				activity.setActivityDes(activityDescription);
				activity.setTime(time);
				activity.setEndTime(endTime);
				activity.setLocation(location);
				activity.setActivityName(activityName);

				list.add(activity);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return list;
	}

	// 查询活动的签到详情
	public List<SignInfo> getSignInfoById(int nid) throws IOException {
		List<SignInfo> list = new ArrayList<SignInfo>();
		String sql = "SELECT * FROM v_sign_activity WHERE ActivityId=?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("Name");
				String inTime = rs.getString("InTime");
				String outTime = rs.getString("OutTime");
				int groupCode = rs.getInt("GroupCode");

				SignInfo signInfo = new SignInfo();
				signInfo.setName(name);
				signInfo.setInTime(inTime);
				signInfo.setOutTime(outTime);
				signInfo.setGroupCode(groupCode);

				list.add(signInfo);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return list;
	}

	// 根据活动ID和时间查询活动的签到详情
	public List<SignInfo> getSignInfoByTime(int nid, String date)
			throws IOException, ParseException {
		List<SignInfo> list = new ArrayList<SignInfo>();
		String sql = "SELECT * FROM v_sign_activity WHERE ActivityId=?";

		// 格式化时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		long time = df.parse(date).getTime();

		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String inTime = rs.getString("InTime");

				if (time == df.parse(inTime.substring(0, 10)).getTime()) {
					String name = rs.getString("Name");
					String outTime = rs.getString("OutTime");
					int group = rs.getInt("GroupCode");
					SignInfo signInfo = new SignInfo();
					signInfo.setName(name);
					signInfo.setInTime(inTime);
					signInfo.setOutTime(outTime);
					signInfo.setGroupCode(group);

					list.add(signInfo);
				}
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return list;
	}

	// 根据活动类型和时间查询活动的签到详情
	public List<SignInfo> getSignInfoByRule(int rule, String date)
			throws IOException, ParseException {
		List<SignInfo> list = new ArrayList<SignInfo>();
		String sql = "SELECT * FROM v_sign_activity WHERE Rule=?";

		// 格式化时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		long time = df.parse(date).getTime();

		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rule);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String inTime = rs.getString("InTime");

				if (time == df.parse(inTime.substring(0, 10)).getTime()) {
					String name = rs.getString("Name");
					String outTime = rs.getString("OutTime");
					int group = rs.getInt("GroupCode");
					SignInfo signInfo = new SignInfo();
					signInfo.setName(name);
					signInfo.setInTime(inTime);
					signInfo.setOutTime(outTime);
					signInfo.setGroupCode(group);

					list.add(signInfo);
				}
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return list;
	}

	// 获取值班签到总时间
	public String getAllTime(String num) {
		long allTime = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = "";
		String sql = "SELECT * FROM v_sign_activity WHERE StudnetNum=? AND Rule=2";
		try {
			PreparedStatement prstm = conn.prepareStatement(sql);
			prstm.setString(1, num);
			ResultSet rs = prstm.executeQuery();
			while (rs.next()) {
				String inTime = rs.getString("InTime");
				long in = df.parse(inTime).getTime();
				String outTime = rs.getString("OutTime");
				long out = df.parse(outTime).getTime();

				allTime += (out - in);
			}
			time = Utils.TimeFormat(allTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	// 查询值班的签到情况
	public List<OnDutySign> getOnDutySign(int acticeID, int groupCode,
			String date) throws Exception {
		// 格式化时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		long time = df.parse(date).getTime();

		// 根据日期获取星期
		Calendar c = Calendar.getInstance();
		// c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		c.setTime(df.parse(date));
		String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		int week = Integer.valueOf(mWay);
		System.out.println("当前星期是：" + week);

		// 先查询值班表中当星期值班的人 有签到的只返回当天签到的，没签到的直接返回
		// 然后查询补班表，返回当天应该补班的人
		List<OnDutySign> list = new ArrayList<OnDutySign>();
		String sql = "SELECT               "
				+ "t_login.`Name`, "
				+ "t_login.GroupCode, "
				+ "t_sign.InTime, "
				+ "t_sign.OutTime, "
				+ "t_onduty.BackTo "
				+ "FROM "
				+ "t_onduty "
				+ "INNER JOIN t_login ON t_onduty.StudentNum = t_login.Account "
				+ "LEFT OUTER JOIN t_sign ON t_onduty.StudentNum = t_sign.StudnetNum AND t_onduty.ActivityId = t_sign.ActivityId ";

		String sql2 = "SELECT "
				+ "t_login.`Name`, "
				+ "t_login.GroupCode, "
				+ "t_sign.InTime, "
				+ "t_sign.OutTime, "
				+ "t_back_duty.BackTime "
				+ "FROM "
				+ " `t_back_duty` "
				+ "INNER JOIN t_login ON t_back_duty.Account=t_login.Account "
				+ "LEFT OUTER JOIN t_sign ON t_sign.StudnetNum=t_back_duty.Account AND t_back_duty.ActiveID = t_sign.ActivityId ";

		if (groupCode == 0) {
			sql += "WHERE t_onduty.ActivityId=" + acticeID
					+ " AND t_onduty.`Week`=" + week + ";";

			sql2 += "WHERE BackActiveID=" + acticeID + ";";
		} else {
			sql += "WHERE t_onduty.ActivityId=" + acticeID
					+ " AND t_onduty.`Week`=" + week
					+ " AND t_login.GroupCode=" + groupCode + ";";

			sql2 += "WHERE BackActiveID=" + acticeID + " AND GroupCode="
					+ groupCode + ";";
		}
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("Name");
				String inTime = rs.getString("InTime");
				String outTime = rs.getString("OutTime");
				int group = rs.getInt("GroupCode");
				int backTo = rs.getInt("BackTo");
				if (inTime == null
						|| time == df.parse(inTime.substring(0, 10)).getTime()) {
					OnDutySign onDutySign = new OnDutySign();
					onDutySign.setName(name);
					onDutySign.setGroupCode(group);
					onDutySign.setInTime(inTime);
					onDutySign.setOutTime(outTime);
					onDutySign.setBackTo(backTo);

					list.add(onDutySign);
				}
			}
			rs.close();

			rs2 = stmt.executeQuery(sql2);
			while (rs2.next()) {
				String name = rs2.getString("Name");
				String inTime = rs2.getString("InTime");
				String outTime = rs2.getString("OutTime");
				int group = rs2.getInt("GroupCode");
				String backTime = rs2.getString("BackTime");
				if (time == df.parse(backTime.substring(0, 10)).getTime()) {
					OnDutySign onDutySign = new OnDutySign();
					onDutySign.setName(name);
					onDutySign.setGroupCode(group);
					onDutySign.setInTime(inTime);
					onDutySign.setOutTime(outTime);
					onDutySign.setBackTo(1);

					list.add(onDutySign);
				}
			}
			rs2.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return list;
	}

	// 获取每周的签到时间
	public String getAllTimeForWeekByNum(String num) {
		// 查询本周的开始时间和结束时间
		String sqlWeek = "SELECT * FROM t_dic_week WHERE ? >= StartDate AND ? <= EndDate";
		ResultSet rsWeek = null;

		long allTime = 0;
		String allTimeStr = "";
		// 查询本周的值班签到信息，只算成功签到的，没签离的不算
		String sql = "SELECT * " + "FROM v_sign_activity "
				+ "WHERE InTime >= ? AND InTime <= ? AND Rule=2 "
				+ "AND StudnetNum=?";
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 获取当前时间
			String time = df.format(new Date());

			PreparedStatement pstmt = conn.prepareStatement(sqlWeek);
			pstmt.setString(1, time);
			pstmt.setString(2, time);
			rsWeek = pstmt.executeQuery();
			if (rsWeek.next()) {
				// 先获取到当前周的时间段
				String startDate = rsWeek.getString("StartDate");
				String endDate = rsWeek.getString("EndDate");

				// 然后根据时间段去查询
				PreparedStatement prstm = conn.prepareStatement(sql);
				prstm.setString(1, startDate);
				prstm.setString(2, endDate);
				prstm.setString(3, num);
				ResultSet rs = prstm.executeQuery();
				while (rs.next()) {
					String inTime = rs.getString("InTime");
					long in = df.parse(inTime).getTime();
					String outTime = rs.getString("OutTime");
					long out = df.parse(outTime).getTime();

					allTime += (out - in);
				}
				rs.close();
				prstm.close();

				allTimeStr = Utils.TimeFormat(allTime);
			}
			rsWeek.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return allTimeStr;
	}

	// 获取各组每周的签到时间列表
	public List<OnDutyTime> getAllTimeForWeekByGroup(int group) {
		List<OnDutyTime> list = new ArrayList<OnDutyTime>();
		
		// 查询本周的开始时间和结束时间
		String sqlWeek = "SELECT * FROM t_dic_week WHERE ? >= StartDate AND ? <= EndDate";
		ResultSet rsWeek = null;

		// 查询本周的值班签到信息，只算成功签到的，没签离的不算
		String sql = "SELECT SUM(UNIX_TIMESTAMP(OutTime)-UNIX_TIMESTAMP(InTime)) AllTime,StudnetNum,`Name`,GroupCode,GradeCode " 
				+ "FROM v_sign_activity "
				+ "WHERE InTime >= ? AND InTime <= ? AND Rule=2 "
				+ "AND GroupCode=? GROUP BY StudnetNum ORDER BY AllTime DESC";
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 获取当前时间
			String time = df.format(new Date());

			PreparedStatement pstmt = conn.prepareStatement(sqlWeek);
			pstmt.setString(1, time);
			pstmt.setString(2, time);
			rsWeek = pstmt.executeQuery();
			if (rsWeek.next()) {
				// 先获取到当前周的时间段
				String startDate = rsWeek.getString("StartDate");
				String endDate = rsWeek.getString("EndDate");

				// 然后根据时间段去查询
				PreparedStatement prstm = conn.prepareStatement(sql);
				prstm.setString(1, startDate);
				prstm.setString(2, endDate);
				prstm.setInt(3, group);
				ResultSet rs = prstm.executeQuery();
				while (rs.next()) {
					String studentName = rs.getString("Name");
					String studentNum = rs.getString("StudnetNum");
					// 通过UNIX_TIMESTAMP()方式将两个datetime类型相减，得到的是秒为单位的数，这里转换成毫秒
					long allTime = rs.getLong("AllTime")*1000;
					int groupCode = rs.getInt("GroupCode");
					int gradeCode = rs.getInt("GradeCode");
					String allTimeStr = Utils.TimeFormat(allTime);
					
					OnDutyTime onDutyTime = new OnDutyTime(allTimeStr,studentName,studentNum,groupCode,gradeCode,allTime);
					list.add(onDutyTime);
				}
				rs.close();
				prstm.close();

			}
			rsWeek.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return list;
	}

}
