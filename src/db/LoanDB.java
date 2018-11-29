package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Loan;
import model.User;

public class LoanDB {
	Connection conn = null;
	Statement stmt = null;

	public LoanDB() {
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

	// 插入一条租借信息
	public boolean addLoan(Loan loan, int quantity) throws IOException {
		String sql = "INSERT INTO t_loan (Account,ArticleID,Time,BackTime,Handle) VALUES (?,?,?,?,?);";
		String updateSql = "UPDATE t_article SET Quantity=?,Status=? WHERE Nid=?";
		boolean flag = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loan.getAccount());
			pstmt.setInt(2, loan.getArticleID());
			pstmt.setString(3, loan.getTime());
			pstmt.setString(4, loan.getBacktime());
			pstmt.setString(5, loan.getHandle());
			int rs = pstmt.executeUpdate();

			// 更新物品表
			PreparedStatement pstmt2 = conn.prepareStatement(updateSql);
			pstmt2.setInt(1, quantity);
			if (quantity > 0) {
				pstmt2.setInt(2, 0);
			} else {
				pstmt2.setInt(2, 1);
			}
			pstmt2.setInt(3, loan.getArticleID());
			int rs2 = pstmt2.executeUpdate();

			if (rs > 0 && rs2 > 0) {
				flag = true;
			}
			pstmt.close();
			pstmt2.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}
		return flag;
	}

	// 归还
	public boolean backArticle(int id, int articleId, String actualTime)
			throws IOException {
		String updateLoanSql = "UPDATE t_loan SET ActualBackTime=?,Status=1 WHERE Nid=?";
		String updateArticleSql = "UPDATE t_article SET Quantity=Quantity+1,Status=0 WHERE Nid=?";
		boolean flag = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(updateLoanSql);
			pstmt.setString(1, actualTime);
			pstmt.setInt(2, id);
			int rs = pstmt.executeUpdate();

			PreparedStatement pstmt2 = conn.prepareStatement(updateArticleSql);
			pstmt2.setInt(1, articleId);
			int rs2 = pstmt2.executeUpdate();
			if (rs > 0 && rs2 > 0) {
				flag = true;
			}
			pstmt.close();
			pstmt2.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}
		return flag;
	}

	// 查询租借记录
	public List<Loan> getLoanList(String account) throws IOException {
		List<Loan> list = new ArrayList<Loan>();
		String sql = "SELECT t_article.Name,t_loan.Account,t_loan.ActualBackTime,t_loan.ArticleID,t_loan.BackTime,t_loan.Handle,t_loan.Nid,t_loan.Status,t_loan.Time"
				+ " FROM t_loan,t_article WHERE t_article.Nid = t_loan.ArticleID AND t_loan.Account=?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("Name");
				int nid = rs.getInt("Nid");
				String accoun = rs.getString("Account");
				int articleID = rs.getInt("ArticleID");
				String time = rs.getString("Time");
				String backTime = rs.getString("BackTime");
				String handle = rs.getString("Handle");
				String actualBackTime = rs.getString("ActualBackTime");
				int status = rs.getInt("Status");

				Loan loan = new Loan(name, nid, accoun, articleID, time,
						backTime, handle, actualBackTime, status);

				list.add(loan);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return list;
	}

	// 根据物品ID和状态为未归还  查当前租借人信息
	public List<User> getLoanUser(int id,int status) throws IOException {
		List<User> list = new ArrayList<User>();
		String sql = "SELECT "
				+ "t_login.`Name`, "
				+ "t_login.GradeCode, "
				+ "t_login.GroupCode, "
				+ "t_login.Phone "
				+ "FROM t_loan "
				+ "INNER JOIN t_login ON t_login.Account = t_loan.Account " 
				+ "WHERE t_loan.ArticleID = ? AND t_loan.`Status`=?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, status);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("Name");
				int gradeCode = rs.getInt("GradeCode");
				int groupCode = rs.getInt("GroupCode");
				String phone = rs.getString("Phone");

				User user = new User();
				user.setName(name);
				user.setGrade(gradeCode);
				user.setGroupCode(groupCode);
				user.setPhone(phone);
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
}
