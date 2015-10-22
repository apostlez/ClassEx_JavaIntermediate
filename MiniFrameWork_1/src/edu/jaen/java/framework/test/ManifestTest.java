package edu.jaen.java.framework.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.jaen.java.framework.manager.ActivityManager;
import edu.jaen.java.framework.util.parser.Manifest;

public class ManifestTest {

	ActivityManager  mgr;

	@Before
	public void setUp() throws Exception {
		mgr=ActivityManager.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		mgr.loadManifest();
		List<Manifest> list=mgr.getParseList();
		System.out.println(list);
		assertTrue(list.size()>0);
	}

}
