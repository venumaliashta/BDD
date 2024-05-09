package com.bddframework.extentReport;

import static com.automation.base.ObjectsRepo.extent;
import static com.automation.base.ObjectsRepo.test;
import static com.automation.utilities.Utils.screenshotStorePath;
import static com.automation.utilities.Utils.takesScreenShotFailedTc;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automation.extentreport.ExtentReportSetUp;
import com.aventstack.extentreports.Status;

public class ExtentReportImplementation implements ITestListener {

		public void onStart(ITestContext context) {

			extent = ExtentReportSetUp.setupExtentReport();
		}
		public void onTestStart(ITestResult result) {
			test = extent.createTest(result.getMethod().getMethodName());
			test.log(Status.INFO, "TEST CASE : " + result.getMethod().getMethodName() + " IS EXECUTION STARTED");
			
		}

		public void onTestSuccess(ITestResult result) {
			test.log(Status.PASS, "TEST CASE : " + result.getMethod().getMethodName() + " IS PASSED");
		}

		public void onTestFailure(ITestResult result) {
			test.log(Status.FAIL, "TEST CASE : " + result.getMethod().getMethodName() + " IS FAILED");
			test.log(Status.FAIL, result.getThrowable());

			takesScreenShotFailedTc(result.getMethod().getMethodName());
			File screenshotpathofFailedTc = screenshotStorePath;
			test.addScreenCaptureFromPath(screenshotpathofFailedTc.toString());

		}
		public void onTestSkipped(ITestResult result) {
			test.log(Status.SKIP, "TEST CASE : " + result.getMethod().getMethodName() + " IS SKIPPED");
			test.log(Status.FAIL, result.getThrowable());
		}

		public void onTestFailedWithTimeout(ITestResult result) {
			test.log(Status.FAIL, "TEST CASE : " + result.getMethod().getMethodName() + " IS FAILED WITH TIMEOUT");
		}
		public void onFinish(ITestContext context) {
			
			extent.flush();
			
		}
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// TODO Auto-generated method stub
			
		}
}
