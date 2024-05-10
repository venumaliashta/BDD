package com.bddframework.hook;
import java.io.IOException;
import com.bddframework.base.Base;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks{
	
	//every test scenario execution before this method is executed


	@BeforeAll
	public void swtUp(Scenario scenario){
	
		System.out.println("Executed the before first scenarion");
		
	}
	@BeforeAll
	public void tearDown(Scenario scenario){
	
		System.out.println("Executed the before first scenarion");
		
	}

	
}
