package com.bddautomation.testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/AddProductIntoCart.feature",
         glue={"com.bddframework.hook","com.bddframework.stepdefinition"},
         plugin = {"pretty", // Pretty format in console
        		 /* "html:target/Cucumber-reports.html",//HTML report,
        		  "json:target/cucumber-reports/Cucumber.json", // json report
        		  "junit.target/cucumber-report/Cucucmber.xml",//Junit report*/
        		   "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","rerun:failed_scenarios/failed_scenarios.txt"},
         monochrome = false, //Console output more readable
         dryRun =false //Checks the mapping between features and step definitions without executing
         //tags = "Smoke // include/exclude scenarios
         )
public class JUnitTestRunner {

}








