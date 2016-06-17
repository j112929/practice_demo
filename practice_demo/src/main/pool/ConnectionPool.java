package main.pool;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectionPool {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public synchronized Connection getConnection(){
		return null;
	}
	/**
	 * 将连接还给空闲池
	 * @param conn
	 */
	public synchronized void freeConnection(Connection conn){
		
	}
	public synchronized Connection getFreeConnection(){
		return null;
	}
	private Connection newConnection() throws SQLException, InterruptedException{
		Connection conn = null;
		boolean flag = false;
		
		while(!flag){
			try {
				conn = (Connection) DriverManager.getConnection("url","user","password");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Thread.sleep(60000);
			}
		}
		return conn;
	}
	public synchronized void release(){
		
	}
}
