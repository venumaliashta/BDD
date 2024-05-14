package com.bddframework.util;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bddframeowrk.pathconfiguration.Path;
import com.bddframework.base.Base;

public class Utils extends Base {

	public static Actions act;
	public static File screenshotStorePath;
	public static WebDriverWait wait;
	public static Select select;
	public static JavascriptExecutor js;
	public static Robot robot;
	public static StringSelection selectedFile;
	public static WebElement ele;
	public static boolean status;
	
	static StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
	static StackTraceElement caller = stackTraceElements[2]; // Assuming caller is at index 2
    static String callerClassName = caller.getClassName();
    static int lineNumber = caller.getLineNumber();
    
    static String callerClassDetails = " callerClassName- "+callerClassName+" lineNumber "+lineNumber;
    
    public static void sendKeys(WebElement element,String elementName, String value) {

    	try {
    		element.sendKeys(value);  		
    		log.info("Entered value in "+elementName+" input field sucessfully");
    	}catch(Exception e){
    		log.error("While entering the value in "+elementName+" Exception occured"+e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    public static void click(WebElement element,String elementName) {
    	try {
			element.click();
			log.info("Clicked sucessfully on "+elementName);
		} catch (Exception e) {
			log.error("While clicking on "+elementName+" Exception occured : "+e.getMessage());
			e.printStackTrace();
		}
    }
  
    
    
	/*public static void orangeHRMLogin() {
		try {
			loginPage = new LoginPage();
			homePage = new HomePage();
			loginPage.enterUsername(readProperty("username"));
			loginPage.enterPassword(readProperty("password"));
			loginPage.clickOnLoginbutton();
		
			if (homePage.homePageDashBoard().equals("Dashboard")) {

				log.info("Login Successfull...."+ callerClassDetails);
				log.info("User navigated on HomePage");
			} else {			
				log.fatal("********   LOGIN UNSUCCESSFULL PLEASE CHECK LOGIN CREDENTIALS OR CONDITIONS  **********"+callerClassDetails);
				System.err.println("********   LOGIN UNSUCCESSFULL PLEASE CHECK LOGIN CREDENTIALS OR CONDITIONS  **********"+callerClassDetails);
				
			}
		} catch (Exception e) {
			log.fatal("Login Unsuccessfull Exception Occurred  "+e);
			e.printStackTrace();
		}
	}
*/
    /*

	public void userLogin(String userName, String password) {
		try {
			loginPage = new LoginPage();
			loginPage.enterUsername(userName);
			loginPage.enterPassword(password);
			loginPage.clickOnLoginbutton();
			if (homePage.homePageDashBoard().equals("Dashboard")) {
				log.info("Login Successfull.................");
				log.info("User navigated on HomePage Sucessfully");
			} else {			
				log.fatal("Login Unsuccessfull ..............");
				System.err.println("****** PLEASE CHECK LOGIN CREDENTIALS *******");
				
			}			
		} catch (Exception e) {
			log.fatal("Failed to userLogin Exception Occurred :"+e);		
			e.printStackTrace();
		}
	}
*/
	public static WebElement toBeVisible(WebElement element, int timeout) {
		
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			ele = wait.until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {
			log.fatal("Failed to wait "+element.getText()+" element toBeVisible Exception Occured :"+e);	
			e.printStackTrace();
		}
		return ele;

	}

	public static WebElement toBeClickable(WebElement element, int timeout) {
      
		try {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		 ele = wait.until(ExpectedConditions.elementToBeClickable(element));
       }catch(Exception e) {
			log.fatal("Failed to wait "+element.getText()+" element toBeClickable Exception Occured :"+e);	
			e.printStackTrace();
       }
		return ele;
	}

	public static void isDisplayed(WebElement element) {
	try {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		 ele = wait.until(ExpectedConditions.visibilityOf(element));
		if (ele.isDisplayed()) {
			log.info(element.getText() + " element is displayed");

		} else {
			log.info(element.getText() + " element is not displayed");
		}
	}catch(Exception e) {
		log.fatal("Failed to wait "+element.getText()+" element isToBeDisplayed Exception Occured :"+e);	
        e.printStackTrace();
	}
	}

	public static void isSelected(WebElement element) {
		try {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		status = wait.until(ExpectedConditions.elementToBeSelected(element));

		if (status) {
			log.info(element.getText() + " element is selected");

		} else {
			log.info(element.getText() + " element is not selected");
		}
		}catch(Exception e) {
			
		}
	}

	public static void isEnabled(WebElement element) {
			
		if (element.isEnabled()) {
			log.info(element.getText() + " is enabled");

		} else {
			log.error(element.getText() + "is not enabled");
		}
	}

	// readpropertyfile
	public static String readProperty(String key) {
		Properties prop = null;
		String property = null;
		
		try {

			FileInputStream file = new FileInputStream(Path.PathConfigFile);
			prop = new Properties();
			prop.load(file);

			property = prop.getProperty(key);
			if (property != null) {

				if (property.isBlank() == false) {

					return property;
				} else {
					log.error(key+" - key is blank in config file");
				}
				return property;
			} else {

				log.error(key +" - key is not present in config file return null  "+callerClassDetails);
				
			}
		} catch (Exception e) {
		System.out.println(e);
		}
		return property;

	}

	// readexcelsheet
	public static String readExcel(String filePath, String sheetNo, int row, int col) {

		Sheet excelsheet = null;
		String data = null;
		try {
			FileInputStream file1 = new FileInputStream(filePath);
			excelsheet = WorkbookFactory.create(file1).getSheet(sheetNo);
			data = excelsheet.getRow(row).getCell(col).getStringCellValue().toString();
			file1.close();
		} catch (Exception e) {
			log.error("Failed to read the data in Excel file Exception Occurred " + e);
			e.printStackTrace();
		}
		return data;
	}

	// get current date
	public static String currentDate() {

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY hh-mm-ss aa");
		Date date = new Date();
		String actualDate = format.format(date);
		return actualDate;
	}

	public static Object takesScreenShotFailedTc(String methodName) {

		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			screenshotStorePath = new File(
					
				Path.pathFailedStepScreenshots+"   "+methodName +"   " +  currentDate()  + " .PNG");

			FileHandler.copy(src, screenshotStorePath);
			log.info("Screenshot captured Sucessfully Method name is " + methodName);
		} catch (Exception e) {
			log.fatal("Screenshot has not captured sucessfully exception occured " + e);
			e.printStackTrace();
		}
		return driver;

	}
	public static Object screenShot(String featureNameForScreenshot ,String descriptionOfLevel) {

		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			screenshotStorePath = new File(
					Path.pathFailedStepScreenshots +"   "+featureNameForScreenshot +"   " +  currentDate()  + " .PNG");
			FileHandler.copy(src, screenshotStorePath);
			log.info("Screenshot captured Sucessfully :"+descriptionOfLevel);
		} catch (Exception e) {
			log.fatal("Unsucessfull to capture Screenshot for the feature :"+featureNameForScreenshot +"Exception Occurred  :"+ e);
			e.printStackTrace();
		}
		return driver;

	}

	public static void moveBackToPage() {

		driver.navigate().back();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		log.info("Moved back to the page");
	}

	public static void moveForwordToPage() {

		driver.navigate().forward();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		log.info("Moved forword to the page");
	}

	public static void refreshPage() {
		driver.navigate().refresh();
		log.info("Page refreshed ");
	}

	public static String getTitleOfCurrentPage() {
		String pageTitle = null;
		try {
			pageTitle = driver.getTitle();
			log.info("Sucessfully get the PageTitle  :" + pageTitle);
		} catch (Exception e) {
			log.fatal("Failed to getTitle excption occurred " + e);
		}
		return pageTitle;

	}

	public static void switchToWindow(int windowNo) {

		try {
			Set<String> windowHandles = driver.getWindowHandles();
			List<String> list = new ArrayList(windowHandles);
			String window = list.get(windowNo);
			driver.switchTo().window(window);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
			String pageTitle = driver.getTitle();
			log.info("Switched to the new window Sucessfully " + windowNo, "Title of the page is " + pageTitle);
		} catch (Exception e) {
			log.error("Failed to switch the window exception occured :" + e);
		}
	}

	public static void alertClickOnOk(int seconds) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			log.info("waited for " + seconds + "seconds alert to be present and clicked on accept(ok) button ");

		} catch (Exception e) {
			e.printStackTrace();
			log.fatal("Failed to Click Ok on Alert " + e);
		}

	}

	public static void alertClickOnCancel(int seconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().dismiss();
			log.info("waited for " + seconds + "seconds alert to be present and clicked on dismiss(cancel) button ");

		} catch (Exception e) {
			e.printStackTrace();
			log.fatal("Failed to Click Cancel on Alert");
		}
	}

	// alert accept

	public static void acceptAlert() {
		driver.switchTo().alert();
		log.info("Clicked on Alert accept(ok) button");
	}

	// alert dismiss

	public static void dismissAlert() {
		driver.switchTo().alert().dismiss();
		log.info("Clicked on Alert dismiss(cancel) button");
	}

	// alert sendkeys

	public static void sendkeysOnAlert(String value) {
		driver.switchTo().alert().sendKeys(value);
		log.info("Sucessfully send the value on alert");
	}

	// ============================================
	// switch to frame by using index

	public static void switchToFrameUsing_Index(WebElement index, int timeout) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
			log.info("Switched to the Frame using index  :" + index);
		} catch (Exception e) {
			log.error("Failed to switch on frame by using Index  " + index + " :" + e);
			e.printStackTrace();
		}
	}

	public static void switchToFrameUsing_Id(WebElement idAttributeValue, int timeout) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idAttributeValue));
			log.info("Switched to frame using id  :" + idAttributeValue);
		} catch (Exception e) {
			log.error("Failed to switch on frame by using ID  " + idAttributeValue + " :" + e);
			e.printStackTrace();
		}
	}

	public static void switchToFrameUsing_Name(WebElement nameAttributeValue, int timeout) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameAttributeValue));
			log.info("driver sucessfully Switched on frame using Name  :" + nameAttributeValue);
		} catch (Exception e) {
			log.error("driver failed to switched on frame by using Name " + nameAttributeValue + " :" + e);
			e.printStackTrace();
		}
	}

	// switch to default content == normal page area
	public static void switchToDefaultContent() {
		try {
			driver.switchTo().defaultContent();
			log.info("driver Sucessfully switch on Frame to Defaultcontent");
		} catch (Exception e) {
			log.error("driver failed to Switch on Defaultcontent " + e);
			e.printStackTrace();
		}
	}

	// switch to parentframe
	public static void switchToParentFrame() {
		try {
			driver.switchTo().parentFrame();
			log.info("driver Sucessfully switched on Frame to Parentframe");
		} catch (Exception e) {
			log.error("driver Failed to Switch on Frame to Parentframe " + e);
			e.printStackTrace();
		}
	}

	public static void selectByVisibleText(WebElement element, String text) {
		select = new Select(element);
		select.selectByVisibleText(text);
		log.info("Selected the dropdown value by using text");
	}

	public static void selectByValue(WebElement element, String value) {
		select = new Select(element);
		select.selectByValue(value);
		log.info("Selected the dropdown value by using value");
	}

	public static void selectByIndex(WebElement element, int index) {
		select = new Select(element);
		select.selectByIndex(index);
		log.info("Selected the dropdown value by using index");
	}

	// ========================================== javascript executor
	// ============================================

	// use scroll into view (element)
	public static void scrollIntoViewElement(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		log.info("Scrolled the page to element location");
	}

	// use for scroll up
	public static void scrollUp(int value) { // scroll up(-ve)
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-" + value + ")");
		log.info("Page scrolled Up");
	}

	// use for scroll down
	public static void scrollDown(int value) { // scroll down(+ve)
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + value + ")");
		log.info("Page scrolled Down");
	}

	// use for scroll right
	public static void scrollRight(int value) { // scroll right(+ve)
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + value + ",0)");
		log.info("Page scrolled Right");
	}

	// use for scroll left
	public static void scrollLeft(int value) { // scroll left(-ve)
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(-" + value + ",0)");
		log.info("Page scrolled Left");

	}

	// use javascript sendkeys
	public static void jsSendKeys(WebElement element, Object value) { // sendkeys javascript executor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement ele = element;
		js.executeScript("arguments[0].value=" + value + ";", "" + ele);
		log.info("Send the value on element sucessfully ");
	}

	// =========================================== Actions class

	// use mouseover on element
	public static void mouseOverOnElement(WebElement element) {
		act = new Actions(driver);
		act.moveToElement(element).build().perform();
		log.info("Mouse over on the targeted element");
	}

	// =========================================== close | quite
	// =================================================
	// use close current opened window
	public static void closeCurrentWindow() {
		driver.close();
		log.info("Closed current browser window Sucessfully");
	}

	// use close all the windows
	public static void closeAllWindows() {
		driver.quit();
		log.info("Closed all browser windows Sucessfully");
	}

	// ====================================================

	public static void sleep(int seconds) {
		try {
			Thread.sleep(seconds);
			String secondsString = Integer.toString(seconds);
			if (secondsString.length() == 4) {
				String fChar = secondsString.substring(0, 1);
				log.info("Sleeped the page for " + fChar + " seconds...");
			} else if (secondsString.length() == 5) {
				String fTwoChar = secondsString.substring(0, 2);
				log.info("Sleeped the page for " + fTwoChar + " seconds...");
			} else {
				log.info("Plese check sleep time you have chossed longer time");
			}
		} catch (Exception e) {
			log.error("Failed to sleep the page Exception Occurred " + e);
			e.printStackTrace();
		}
	}

	public static void search(WebElement searchBar, List<WebElement> suggestionsList, String searchItem) {
		try {
			searchBar.click();
			log.info("Clicked on Searchbar");
			searchBar.sendKeys(searchItem);
			log.info("Entered the item name on SearchBar");
			Thread.sleep(3000);
			for (WebElement suggestionName : suggestionsList) {
				Thread.sleep(3000);
				String suggesions = suggestionName.getText();
				if (suggesions.equalsIgnoreCase(searchItem)) {
					Thread.sleep(3000);
					suggestionName.click();
					log.info("Clicked sucessfully on " + suggestionsList + " Search Item");
					Utils.screenShot("SearchItem","Screenshot captured After Clicked on searched item");
				}else {
					log.fatal("Searched item not available.");
				}
			}
		} catch (Exception e) {
			log.fatal("Exception occurred in Search element "+e);
			e.printStackTrace();
		}

	}

	// get current date
	public static String getTodaysDate() {

		return (new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
	}

	// get current time
	public static String getSystemTime() {

		return (new SimpleDateFormat("HH-mm-ss-ssss aa").format(new Date()));
	}

	// upload file
	public static void uploadFile(WebElement uploadButton, String filePath) {

		try {

			uploadButton.click();
			log.info("Clicked on Upload button");
			robot = new Robot();

			selectedFile = new StringSelection(filePath);

			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selectedFile, null);
			robot.getAutoDelay();
			log.info("Focus on the Clipboard");
			robot.getAutoDelay();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.setAutoDelay(10000);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			log.info("Enter the filepath inside clipboard");
			robot.setAutoDelay(10000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			log.info("Clicked on open button");
			robot.setAutoDelay(10000);
			log.info("Start to Uploading file......");
			robot.setAutoDelay(10000);
		} catch (Exception e) {
			log.fatal("Failed to upload file Exception Occured "+e);
			e.printStackTrace();
		}
	}

	public static boolean isFileExists(String filePath) {
		File file = new File(filePath);

		if (file.exists()) {

			return true;
		}
		return false;
	}

	public static void downloadFile(WebElement downloadButton) {
		try {

			boolean fileAlreadyExistsDownloadAgainAndAgain = false;
			boolean fileAlreadyExistsDownloadFolderCantDownloadAgain = true;

			boolean fileStatusResult = Utils.isFileExists(Path.homeDirectory+"\\Downloads\\"+Path.fileName);
			log.info("Before downloading file is exists :" + fileStatusResult);

			if (fileStatusResult == fileAlreadyExistsDownloadAgainAndAgain) {
				log.info("File already exists in download folder");
			} else {
				downloadButton.click();
				log.info("Clicked on Download button " + downloadButton.getText());
			}
			log.info("After downloading file exists :" + fileStatusResult);
		} catch (Exception e) {
            log.error("Failed to download file exception occured "+e);
			e.printStackTrace();
		}
	}
	
	public static void cursorActionAndClick(WebElement element) {
		act = new Actions(driver);
		act.moveToElement(element).click().perform();
	}
	public static String readProperty(String filepath, String key) throws IOException {
		FileInputStream file = new FileInputStream(filepath);
		Properties prop = new Properties();
		prop.load(file);
		String property = prop.getProperty(key);
		if(property != null) {
			System.out.println("read the property sucessfully");
		}else {
			System.out.println("property value is null");
		}
		return property;
}

}
