package com.bddautomation.testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/SwagLabsLoginPage.feature",
         glue={"com.bddframework.stepdefinition"},
         plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
         )
public class JUnitTestRunner {

}








