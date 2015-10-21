package edu.jaen.java.factory;
import java.sql.*;
import java.util.*;

//보통 ConcreteProduct 이라고 많이 합니다. 
//product 2
public  class JDBCOracleHelper extends JDBCHelper {

	// ConnectionPooling을 지원하지 않을때 활용

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
