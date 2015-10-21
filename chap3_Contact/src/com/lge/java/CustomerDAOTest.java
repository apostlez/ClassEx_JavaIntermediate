package com.lge.java;

import edu.jaen.java.CustomerDAO;
import junit.framework.TestCase;

public class CustomerDAOTest extends TestCase {
	
	CustomerDAO dao;

	protected void setUp() throws Exception {
		System.out.println("call setup");
		dao = new CustomerDAO();
	}

	protected void tearDown() throws Exception {
		System.out.println("call teardown");
	}

	public void testAddCust() {
		System.out.println("call testAddCust");
		dao.addCust("aaa", "010-1234-5678", 1);
		int count = dao.allCust().size();
		assertTrue(count>0);
	}

	public void testAllCust() {
		System.out.println("call testAllCust");
		int count = dao.allCust().size();
		assertTrue(count>0);
	}
	
	public void testABC() {
		System.out.println("call testABC");
	}

}
