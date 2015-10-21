package edu.jaen.java.factory;
// Factory Method Sub클래스 ( 대개 ConcreteCreator  라고 하죠.)
public class JDBCHelperFactory implements JDBCHelperFactoryIF
{
	public JDBCHelper getFactory(String type){
		
			if(type.equals("oracle")){
					return new JDBCOracleHelper();
			}else{
					return new JDBCMysqlHelper();
			}

	}
}

