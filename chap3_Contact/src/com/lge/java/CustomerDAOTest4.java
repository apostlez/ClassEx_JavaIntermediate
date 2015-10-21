package com.lge.java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.jaen.java.CustomerDAO;

public class CustomerDAOTest4 {
	
	CustomerDAO dao;

	@BeforeClass
	public static void b() throws Exception {
		System.out.println("setUpBeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("tearDownAfterClass");
	}

	@Before
	public void a() throws Exception {
		System.out.println("setUp");
		dao = new CustomerDAO();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
	}

	@Test
	public void testAddCust() {
		System.out.println("testAddCust");
		System.out.println(dao);
		dao.addCust("name", "phone", 1);
		assertTrue(dao.allCust().size() > 0);
	}

	@Test
	public void testAllCust() {
		System.out.println("testAllCust");
		System.out.println(dao);
		assertTrue(dao.allCust().size() == 0);
		dao.addCust("name", "phone", 1);
		assertTrue(dao.allCust().size() == 1);
	}

}
