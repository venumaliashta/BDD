package com.bddframework.util;
import java.io.IOException;
import com.bddframework.base.Base;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends Base {
	
	//every test scenario execution before this method is executed

	//just like @beforeMethod
	@Before
	public void beforeScenario(Scenario scenario)
	{
		try {
			Hooks.openBrowser();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Scenario Executing Start :-"+scenario.getName());
		
	}

	//just like @afterMethod
}
