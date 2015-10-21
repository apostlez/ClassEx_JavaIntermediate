package edu.jaen.java.factory;

// Factory  Method Interface (대개 이것을 Creator라고 하죠)
public interface JDBCHelperFactoryIF {
	public JDBCHelper getFactory(String type);
}
