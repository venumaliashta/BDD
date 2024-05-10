package com.bddautomation.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bddframework.base.ObjectRepo;
import static com.bddframework.util.Utils.*;

public class POM_SwagLabsLoginPage extends ObjectRepo{

	@FindBy(xpath="//input[@id='user-name']") private  WebElement userName;
	@FindBy(xpath="//input[@id='password']") private  WebElement password;
	@FindBy(xpath="//input[@id='login-button']") private  WebElement loginButton;
	
public POM_SwagLabsLoginPage(WebDriver driver) {
		
		PageFactory.initElements(driver,this);
	}	
  public  void enterUserName(String username) {
	sendKeys(userName,"UserName",username);
  }
  public  void enterPassword(String pass) {
	sendKeys(password,"Password",pass);
  }
  public  void clickOnLoginBtn() {
	click(loginButton,"Login Button");
	
  }
}
