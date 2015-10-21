package edu.jaen.java.factory;

import java.sql.*;
import java.util.*;

public class FactoryMethodTest {
	public static void main(String[] args) throws Exception {
		// Factory Method �� �����ϴ� ������ Creator ��ü ����
		JDBCHelperFactoryIF jdbcFactory = new JDBCHelperFactory(); // ���� ����
		JDBCHelper db = jdbcFactory.getFactory("oracle"); // ���ǿ� �´� ��ǰ ����
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
