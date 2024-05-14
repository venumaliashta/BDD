package com.bddframework.hook;

import com.bddframework.base.Base;
import com.bddframework.util.Utils;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks extends Base{
	
static Hooks hook ;

	//every test scenario execution before this method is executed
    @BeforeAll
	public static void setUp() {
    	
    	try {
			openBrowser();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	 }

	 @AfterAll
	 public static void tearDown() {
		 try {
		   closeBrowserWindows();
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	    }
	@Before
	public void beforeEachScenario(Scenario scenario) {
	System.out.println("Executed before the scenario: " + scenario.getName());
	}

	@After
	public void afterEachScenario(Scenario scenario) {
	System.out.println("Executed after the scenario: " + scenario.getName());
	 
	}
	
	@AfterStep
	public void postActionsAfterFailedStep(Scenario scenario) {
		Utils.takesScreenShotFailedTc(scenario.getName());
	}
	
}
