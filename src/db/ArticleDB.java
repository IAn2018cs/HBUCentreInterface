package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Article;

public class ArticleDB {
	Connection conn = null;
	Statement stmt = null;

	public ArticleDB() {
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

	// 获取中心物品列表
	public List<Article> getArticleList() throws IOException {
		List<Article> list = new ArrayList<Article>();
		String sql = "SELECT * FROM t_article";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int nid = rs.getInt("Nid");
				String name = rs.getString("Name");
				int type = rs.getInt("Type");
				int quantity = rs.getInt("Quantity");
				double price = rs.getDouble("Price");
				int status = rs.getInt("Status");
				String description = rs.getString("Description");
				String location = rs.getString("Location");
				String imageUrl = rs.getString("ImageUrl");

				Article article = new Article(nid, name, type, quantity, price,
						status, description, location,imageUrl);

				list.add(article);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return list;
	}

	// 删除物品
	public boolean deleteGoods(int id) throws IOException {
		String sql = "DELETE FROM t_article WHERE Nid=?;";
		String sql2 = "DELETE FROM t_loan WHERE ArticleID=?;";
		boolean flag = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int rs = pstmt.executeUpdate();

			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, id);
			pstmt2.executeUpdate();
			if (rs > 0) {
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

	// 添加物品
	public boolean addGoods(Article article) {
		String instSql = "INSERT INTO t_article (Name,Type,Quantity,Price,Status,Description,Location,ImageUrl) VALUES (?,?,?,?,0,?,?,?);";

		boolean flag = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(instSql);
			pstmt.setString(1, article.getName());
			pstmt.setInt(2, article.getType());
			pstmt.setInt(3, article.getQuanutity());
			pstmt.setDouble(4, article.getPrice());
			pstmt.setString(5, article.getDescription());
			pstmt.setString(6, article.getLocation());
			pstmt.setString(7, article.getImageUrl());
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错" + e.toString());
			e.printStackTrace();
		}
		return flag;
	}

	// 获取名字获取物品列表
	public List<Article> getArticleByName(String goodsName) throws IOException {
		List<Article> list = new ArrayList<Article>();
		String sql = "SELECT * FROM `t_article` WHERE `Name` LIKE '%"
				+ goodsName + "%'";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int nid = rs.getInt("Nid");
				String name = rs.getString("Name");
				int type = rs.getInt("Type");
				int quantity = rs.getInt("Quantity");
				double price = rs.getDouble("Price");
				int status = rs.getInt("Status");
				String description = rs.getString("Description");
				String location = rs.getString("Location");
				String imageUrl = rs.getString("ImageUrl");

				Article article = new Article(nid, name, type, quantity, price,
						status, description, location,imageUrl);

				list.add(article);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("数据库结果集出错");
			e.printStackTrace();
		}

		return list;
	}

	// 修改物品信息
	public boolean changeGoods(Article article) throws IOException {
		String sql = "UPDATE t_article SET Name=?,Type=?,Quantity=?,Price=?,Description=?,Location=?,ImageUrl=? WHERE Nid=?";
		boolean flag = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getName());
			pstmt.setInt(2, article.getType());
			pstmt.setInt(3, article.getQuanutity());
			pstmt.setDouble(4, article.getPrice());
			pstmt.setString(5, article.getDescription());
			pstmt.setString(6, article.getLocation());
			pstmt.setString(7, article.getImageUrl());
			pstmt.setInt(8, article.getNid());
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
