package com.bddframework.extentReport;

import static com.automation.utilities.Utils.readProperty;

import com.automation.base.ObjectsRepo;
import com.automation.pathconfiguration.Path;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportSetup extends ObjectsRepo{

		public static ExtentReports setupExtentReport() {
				
			    ExtentSparkReporter sparkReport = new ExtentSparkReporter(Path.pathExtentReport);
				
			    extent = new ExtentReports();
				extent.attachReporter(sparkReport);
				
				sparkReport.config().setDocumentTitle("Documenttitle");
				sparkReport.config().setReportName("Extent Execution Report");
				sparkReport.config().setTheme(Theme.DARK);
				
				extent.setSystemInfo("Automation FrameworkType",readProperty("AutomationFrameworkType"));
				extent.setSystemInfo("OS ",System.getProperty("os.name"));
				extent.setSystemInfo("javaVersion",System.getProperty("java.version"));
				extent.setSystemInfo("UserName ",System.getProperty("user.name"));	
				extent.setSystemInfo("Browser",readProperty("browsername"));
				extent.setSystemInfo("URL",readProperty("applicationUrl"));	
				extent.setSystemInfo("TeamName",readProperty("Teamname"));
			
				return extent;
			}
		}

