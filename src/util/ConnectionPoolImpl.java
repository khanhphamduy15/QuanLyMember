package util;

import java.sql.*;
import java.util.*;

public class ConnectionPoolImpl implements ConnectionPool {
	///Trinh dieu khien lam viec
	private String driver;
	
	//Tai khoan ket noi
	private String username;
	private String userpass;
	
	//duong dan thuc thi
	private String url;
	
	//doi tuong ket noi
	private Stack<Connection> pool;
	
	public ConnectionPoolImpl(){
		this.driver = "com.mysql.cj.jdbc.Driver";
		//Nap trinh dieu khieen
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		}
		//xac dinh tai khoan
		this.username = "root";
		this.userpass = "root";
		
		//xac dinh duong dan thuc thi
		this.url = "jdbc:mysql://localhost:3306/quanlymem?allowMultiQueries=true";
		
		//Khoi tao doi tuong luu tru
		this.pool = new Stack<>();
		
		
	}
	@Override
	public Connection getConnection(String objectName) throws SQLException {
		// TODO Auto-generated method stub
		
		if(this.pool.isEmpty()) {
			System.out.println(objectName+ "Da khoi tao 1 ket noi moi.");
		return DriverManager.getConnection(this.url, this.username, this.userpass);
		}else {
			System.out.println(objectName+ "Da lay ra 1 ket noi.");
			return this.pool.pop();
		}
	}

	@Override
	public void releaseConnection(Connection con, String objectName) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(objectName+ "Da tra ve 1 ket noi.");
		this.pool.push(con);
	}
	protected void finalize() throws Throwable {
		this.pool.clear();
		this.pool = null;
		System.out.println("CPool is Closed!");
	}

}
