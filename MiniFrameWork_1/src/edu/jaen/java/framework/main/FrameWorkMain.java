package edu.jaen.java.framework.main;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import edu.jaen.java.framework.manager.ActivityManager;

public class FrameWorkMain {

	public static void main(String[] args) {

		ActivityManager activityM = ActivityManager.getInstance();
		activityM.managerAction();
	}

}
