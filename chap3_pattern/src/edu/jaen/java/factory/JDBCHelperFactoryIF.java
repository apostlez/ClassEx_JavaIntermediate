package edu.jaen.java.factory;

// Factory  Method Interface (�밳 �̰��� Creator��� ����)
public interface JDBCHelperFactoryIF {
	public JDBCHelper getFactory(String type);
}
