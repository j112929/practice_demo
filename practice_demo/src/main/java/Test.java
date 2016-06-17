package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {

	public static void main(String args[]) {
		try {

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:odbc:testExcel", "", "");
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery("select * from [sheet1$]");
			System.out.println("测试");
			while (rs.next()) {
				System.out.println(rs.getString("name"));
				System.out.println(rs.getInt("age"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
