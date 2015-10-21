package edu.jaen.java.factory;

import java.sql.*;
import java.util.*;

public class FactoryMethodTest {
	public static void main(String[] args) throws Exception {
		// Factory Method 를 포함하는 구현한 Creator 객체 생성
		JDBCHelperFactoryIF jdbcFactory = new JDBCHelperFactory(); // 공장 생성
		JDBCHelper db = jdbcFactory.getFactory("oracle"); // 조건에 맞는 제품 생성
		Connection con = db.connect();
		Statement stmt = con.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT * from customer");
		System.out.println("--- customer table -- ");
		while (rset.next()) {
			System.out.println(rset.getString(1) + "\t" + rset.getString(2) + "\t" + rset.getString(3));
		}
		stmt.close();
		con.close();

	}
}
