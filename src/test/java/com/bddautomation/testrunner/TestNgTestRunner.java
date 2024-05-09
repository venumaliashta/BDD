package com.bddautomation.testrunner;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.bddframework.base.Base;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features="C:\\Users\\DELL\\eclipse-workspace\\BddFramework\\src\\test\\resources\\features\\login.feature",
         glue={"com.bddframework.stepdefinition"})

public class TestNgTestRunner<CucumberFeatureWrapper> extends AbstractTestNGCucumberTests{
	@BeforeClass(alwaysRun = true)
    public void setup() {
		Base b = new Base();
		try {
			b.openBrowser();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}
	@Test
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		
	}
}
