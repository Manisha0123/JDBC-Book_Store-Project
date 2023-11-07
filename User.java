package book_store.dto;

import java.sql.ResultSet;

public class User {
	private int id;
	private String name;
	private long phno;
	private double amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhno() {
		return phno;
	}
	public void setPhno(long phno) {
		this.phno = phno;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public User(int id, String name, long phno, double amount) {
		super();
		this.id = id;
		this.name = name;
		this.phno = phno;
		this.amount = amount;
	}
	
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phno=" + phno + ", amount=" + amount + "]";
	}
	
	
}
