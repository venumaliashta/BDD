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
import org.testng.annotations.Listeners;

import static com.bddframework.util.Utils.*;

import com.automation.base.ObjectsRepo;
import com.bddframeowrk.pathconfiguration.Path;
import com.bddframework.util.Log4jImplementation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

//@Listeners({com.bddframework.extentReport.ExtentReportImplementation.class,com.bddframework.util.Log4jImplementation.class})
public class Base extends Objectrepo{
	public static WebDriver driver;
	

	public static WebDriver openBrowser() throws IOException {

		String BrowserName = readProperty(Path.PathConfigFile,"browername");
		if (BrowserName.equalsIgnoreCase("c")) {

			log.info("---");
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver(options);							
		
			System.out.println("    -----------  Open chrome browser  ------------");
		
	
		} else if (BrowserName.equalsIgnoreCase("e")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		
			System.out.println("   -----------  Open edge browser  --------------");

		} else if (BrowserName.equalsIgnoreCase("f")) {
			WebDriverManager.edgedriver().setup();
			driver = new FirefoxDriver();
	
			System.out.println("   ------------  Open firefox browser  -------------");

		}
		driver.get(readProperty(Path.PathConfigFile,"swagLabsURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
	
		return driver;

	}
	  
	     public void closeBrowserWindows() {  	  
    	  driver.quit();
    	  System.out.println("user closed all the windows...");
      }
}
