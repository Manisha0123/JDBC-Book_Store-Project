package book_store.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import book_store.dto.Book;
import book_store.dto.User;

public class BookCrud {
	public void createTable() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store?createDatabaseIfNotExist=true","root","root");
		Statement s = con.createStatement();
		s.execute("create table IF NOT EXISTS book (id int primary key," 
		+ " name varchar(45) not null, author varchar(45)," +" price double, language varchar(45))");
		con.close();
		s.close();
		//System.out.println("Table Created.....");
	}
	public void saveBook(Book book) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store?createDatabaseIfNotExist=true","root","root");
		PreparedStatement ps = con.prepareStatement("insert into book values(?,?,?,?,?)");
		ps.setInt(1, book.getId());
		ps.setString(2, book.getName());
		ps.setString(3, book.getAuthor());
		ps.setDouble(4, book.getPrice());
		ps.setString(5, book.getLanguage());
		ps.execute();
		con.close();
		ps.close();
		System.out.println("inserted....");
	}
	public void updateBook(double price,int id) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store?createDatabaseIfNotExist=true","root","root");
		PreparedStatement ps = con.prepareStatement("update book set price=? where id=?");
		ps.setDouble(1, price);
		ps.setInt(2, id);
		ps.execute();
		con.close();
		ps.close();
		System.out.println("updated");
	}
	public void delete(int id) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store?createDatabaseIfNotExist=true","root","root");
		PreparedStatement ps = con.prepareStatement("delete from book where id=?");
		ps.setInt(1, id);
		ps.execute();
		con.close();
		ps.close();
		System.out.println("deleted..");
	}
	public void fetch(Book book) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store?createDatabaseIfNotExist=true","root","root");
		PreparedStatement ps = con.prepareStatement("select * from book");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt("id") +" "+rs.getString("name") + " "+ rs.getString("author")+ " "+ rs.getDouble("price")+" "+ rs.getString("language"));
		}
		ps.close();
		con.close();
	}
	public void receipt(Book book,User user) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store?createDatabaseIfNotExist=true","root","root");
		PreparedStatement ps = con.prepareStatement("select * from book where id=?");
		ps.setInt(1, book.getId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println("Book Price:" + rs.getDouble("price"));
			System.out.println("Amount:" + UserCrud.receipt1(user));
			if(rs.getDouble("price") > UserCrud.receipt1(user)) {
				System.out.println("Insufficient balance to buy the book");
			}else {
				double result=UserCrud.receipt1(user) - rs.getDouble("price");
				System.out.println("Bill:" +result);
				UserCrud.updateAmount(result, user.getId());
			}
		}
		ps.close();
		con.close();
	}
}



















