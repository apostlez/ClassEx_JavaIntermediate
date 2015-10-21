package edu.jaen.java.test;

import junit.framework.TestCase;
import edu.jaen.java.CustomerDAO;

public class CustomerDAOTest extends TestCase {
	CustomerDAO dao;

	protected void setUp() throws Exception {
		super.setUp();
		dao = new CustomerDAO();
	}
	
	public void testAddCust() {
		dao.addCust("ȫ�浿", "02", 1);
		fail("Not yet implemented");
	}

}
