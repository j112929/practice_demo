package main.db;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class HikariCPTest {
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
	
			// 建立数据库 连接
			String url = "jdbc:mysql://10.20.99.105:3306/datahouse_db?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true";
			String uid = "javadatacenter";
			String pw = "1ou@6o2R";
			conn = (Connection) DriverManager.getConnection(url, uid, pw);
			System.out.println("数据库连接成功!!!");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		getConnection();
	}
}
