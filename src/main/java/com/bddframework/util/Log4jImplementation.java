package com.bddframework.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.bddframeowrk.pathconfiguration.Path;
import com.bddframework.base.Objectrepo;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
public class Log4jImplementation extends Objectrepo implements ITestListener{
	//extends Objectrepo implements ITestListener
	public void onStart(ITestContext context) {
		
     	log = LogManager.getLogger("BddFramework");
        
		log.info("                                                                                         ");
		log.info("*******************************     LOGGING START     *********************************");
		log.info("                                                                                         ");
	
	}
	public void onTestStart(ITestResult result) {

		log.info("TEST START :" + result.getMethod().getMethodName() + " TEST IS START FOR EXECUTION");
	}

	public void onTestSuccess(ITestResult result) {

		log.info(Status.PASS + " " + "TESTCASE  :" + result.getMethod().getMethodName() + " IS PASSED");
	}

	public void onTestFailure(ITestResult result) {

        log.fatal(Status.FAIL + " " + "TESTCASE  :" + result.getMethod().getMethodName() + " IS FAILED");
	}

	public void onTestSkipped(ITestResult result) {

		log.fatal(Status.SKIP + " " + "TESTCASE  :" + result.getMethod().getMethodName() + " IS SKIPPED");

	}

	public void onTestFailedWithTimeout(ITestResult result) {

		log.fatal(Status.FAIL + " " + "TESTCASE  :" + result.getMethod().getMethodName() + " IS FAILED WITH TIMEOUT");
	
	}
	
	public void onFinish(ITestContext context) {

		log.info("                                                                                         ");
		log.info("*******************************      LOGGING END      ************************************");
		log.info("                                                                                         ");

	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("//");
	}
}
