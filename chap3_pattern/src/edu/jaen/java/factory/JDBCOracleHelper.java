package edu.jaen.java.factory;
import java.sql.*;
import java.util.*;

//���� ConcreteProduct �̶�� ���� �մϴ�. 
//product 2
public  class JDBCOracleHelper extends JDBCHelper {

	// ConnectionPooling�� �������� ������ Ȱ��

	public Connection connect() {
		Connection con = null ;			
		Statement stmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver") ;
			con = DriverManager.getConnection("jdbc:oracle:thin:@202.30.177.91:1521:diana","scott","tiger") ;
		}catch (Exception e){	
			e.printStackTrace() ;
		}
		return con ;	
	}

}
