package com.bddframework.base;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.bddframework.util.Utils.*;

import com.automation.base.ObjectsRepo;
import com.bddframeowrk.pathconfiguration.Path;


import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Base extends ObjectRepo{
	public static WebDriver driver;
	
	public static WebDriver openBrowser() throws IOException {

		String BrowserName = readProperty(Path.PathConfigFile,"browername");
		if (BrowserName.equalsIgnoreCase("c")) {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver(options);	
		    
		    log.info("    -----------  Open chrome browser  ------------  ");
		
		} else if (BrowserName.equalsIgnoreCase("e")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		
			log.info("   -----------  Open edge browser  --------------");
		
		} else if (BrowserName.equalsIgnoreCase("f")) {
			WebDriverManager.edgedriver().setup();
			driver = new FirefoxDriver();
	
			log.info("   ------------  Open firefox browser  -------------");

		}
		driver.get(readProperty(Path.PathConfigFile,"swagLabsURL"));
		log.info("SwagLabs login page successfully launched.");
		driver.manage().window().maximize();
		log.info("Successfully maximized the browser window.");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
		log.info("Waiting for 40 seconds after maximizing the page to load.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		log.info("Implicilty wait of 40 seconds added after the page load.");
		return driver;

	} 
	    public static void closeBrowserWindows() {  	  
    	driver.quit();
    	log.info("User Closed all the browers Windows sucessfully...");
      }
}
