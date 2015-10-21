package edu.jaen.java.factory;
// Factory Method SubŬ���� ( �밳 ConcreteCreator  ��� ����.)
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

