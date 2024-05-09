package com.bddautomation.testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\DELL\\eclipse-workspace\\BddFramework\\src\\test\\resources\\features\\AddProductIntoCart.feature",
         glue="com.bddframework.stepdefinition",
         plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
         )
public class JUnitTestRunner {

}

//src/test/resources/com.bddautomation.features/login.feature







