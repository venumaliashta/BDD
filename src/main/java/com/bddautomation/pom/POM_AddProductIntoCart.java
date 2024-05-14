package com.bddautomation.pom;

import static com.bddframework.util.Utils.click;
import static com.bddframework.util.Utils.sendKeys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POM_AddProductIntoCart {


	
	
	@FindBy(xpath="//input[@id='user-name']") private  WebElement userName;
	@FindBy(xpath="//input[@id='password']") private  WebElement password;
	@FindBy(xpath="//input[@id='login-button']") private  WebElement loginButton;
	@FindBy(xpath="//a[@id='item_4_title_link']") private  WebElement Sauce_Labs_Backpack;
	@FindBy(xpath="//button[text()='ADD TO CART']") private  WebElement addToCartButton;
	@FindBy(xpath="//button[text()='REMOVE']") private  WebElement removeButton;
	@FindBy(xpath="//span[@class='fa-layers-counter shopping_cart_badge']") private  WebElement cartIcon;
	@FindBy(xpath="//div[@class='cart_quantity']") private  WebElement cartQuantity;
	
	////div[@class='cart_quantity']
	//span[@class='fa-layers-counter shopping_cart_badge']
	
	
	////button[text()='REMOVE']
	//ADD TO CART
public POM_AddProductIntoCart(WebDriver driver) {
		
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
  public void clickOnSauceLabsBackpack() {
	Sauce_Labs_Backpack.click();
  }
  public void clickOnAddToCartButton() {
	addToCartButton.click();
  }
  
  public String getTextRemoveButton() {
	  return removeButton.getText();
  } 
  public void clickOnCartIcon() {
	  cartIcon.click();
  }
  public String verifyCartQuantity() {
	  return cartQuantity.getText();
  }
}
