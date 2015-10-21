package edu.jaen.java.factory;
import java.sql.*;
import java.util.*;

//ConcreteProduct 
//product 1
public  class JDBCMysqlHelper extends JDBCHelper {

	// ConnectionPooling을 지원하지 않을때 활용

	public Connection connect() {
		Connection con = null ;			

		try{
			Class.forName("org.gjt.mm.mysql.Driver") ;
			con = DriverManager.getConnection("jdbc:mysql://202.30.177.91:3306/sl285","scott","tiger") ;
		}catch (Exception e){	
			e.printStackTrace() ;
		}
		return con ;	
	}

}
