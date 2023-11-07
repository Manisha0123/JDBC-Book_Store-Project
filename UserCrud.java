package book_store.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import book_store.dto.User;

public class UserCrud {
	
	public void createTable() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?createDatabaseIfNotExist=true","root","root");
		Statement s = con.createStatement();
		s.execute("create table IF NOT EXISTS user(id int primary key, name varchar(35),phno int,amount double)");
		s.close();
		con.close();
		//System.out.println("created...");
	}
	public void saveUser(User user) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?createDatabaseIfNotExist=true","root","root");
		PreparedStatement ps = con.prepareStatement("insert into user values(?,?,?,?)");
		ps.setInt(1, user.getId());
		ps.setString(2, user.getName());
		ps.setLong(3, user.getPhno());
		ps.setDouble(4, user.getAmount());
		ps.execute();
		ps.close();
		con.close();
	}
	public void fetch(User user) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?createDatabaseIfNotExist=true","root","root");
		PreparedStatement ps = con.prepareStatement("select * from user where id=?");
		ps.setInt(1, user.getId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt("id")+" "+ rs.getString("name")+" "+rs.getLong("phno")+" "+ rs.getDouble("amount"));
		}
		ps.close();
		con.close();
	}
	public static double receipt1(User user) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?createDatabaseIfNotExist=true","root","root");
		PreparedStatement ps = con.prepareStatement("select amount from user where id=?");
		ps.setInt(1, user.getId());
		ResultSet rs = ps.executeQuery();
		double result = 0;
		while(rs.next()) {
			result = rs.getDouble("amount");
		}
		ps.close();
		con.close();
		return result;
	}
	public static void updateAmount(double amount,int id) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?createDatabaseIfNotExist=true","root","root");
		PreparedStatement ps = con.prepareStatement("update user set amount=? where id=?");
		ps.setDouble(1, amount);
		ps.setInt(2, id);
		ps.execute();
		ps.close();
		con.close();
	}
}














