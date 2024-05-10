package com.bddframework.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
//import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.bddframework.util.Log4jImplementation;

//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;

@Listeners(com.bddframework.util.Log4jImplementation.class)
public class ObjectRepo{
	public static   WebDriver driver;
	public static   ExtentReports extent;
	public static   ExtentTest test;
	public static   Logger log = LogManager.getLogger("BddFramework");;
	

}
