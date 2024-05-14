package com.bddautomation.testrunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features="src/test/resources/features/SwagLabsLoginPage.feature",
         glue={"com.bddframework.stepdefinition"},
         plugin = {"pretty", // Pretty format in console
        		 /* "html:target/Cucumber-reports.html",//HTML report,
       		  "json:target/cucumber-reports/Cucumber.json", // json report
       		  "junit.target/cucumber-report/Cucucmber.xml",//Junit report*/
       		   "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","rerun:failed_scenarios/failed_scenarios.txt"},
         monochrome = false, //Console output more readable
         dryRun =false //Checks the mapping between features and step definitions without executing
        //tags = "Smoke // include/exclude scenarios
        )

public class TestNgTestRunner extends AbstractTestNGCucumberTests{

	  @BeforeClass
	    public void setUpClass() {
	        System.out.println("Setup before the class");
	    }

	  @AfterClass
	   public void tearDownClass() {
	        System.out.println("Cleanup after the class");
	    }
	
	@Override
	@DataProvider(parallel=false)
	public Object[][] scenarios(){
		
		return super.scenarios();
	}
}
