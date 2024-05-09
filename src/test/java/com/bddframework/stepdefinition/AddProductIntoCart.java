package com.bddframework.stepdefinition;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import com.bddautomation.pom.POM_AddProductIntoCart;
import com.bddautomation.pom.POM_SwagLabsLoginPage;
import com.bddframework.base.Base;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddProductIntoCart extends Base{
	POM_AddProductIntoCart addProductIntoCart;

	@Given("User open the Safeway login page on browser_")
	public void user_open_the_safeway_login_page_on_browser_() {
		   try {
				driver = openBrowser();
			} catch (IOException e) {
				e.printStackTrace();
			}
		   addProductIntoCart = new POM_AddProductIntoCart(driver);
			   
		}
	@When("User enter userName on userName field_")
	public void user_enter_user_name_on_user_name_field_() {
		addProductIntoCart.enterUserName("standard_user");
	}
	@When("User enter password on password field_")
	public void user_enter_password_on_password_field_() {
		addProductIntoCart.enterPassword("secret_sauce");
	}
	@Then("User enter click on login button_")
	public void user_enter_click_on_login_button_() {
		addProductIntoCart.clickOnLoginBtn();
		assertEquals(driver.getTitle(),"Swag Labs");
	}
	@Then("add Product into cart")
	public void add_product_into_cart() {
		addProductIntoCart.clickOnSauceLabsBackpack();
	
	}
	@Then("Check the quantity should be one")
	public void check_the_quantity_should_be_one() {
		addProductIntoCart.clickOnAddToCartButton();
		assertEquals(addProductIntoCart.getTextRemoveButton(),"REMOVE");
		addProductIntoCart.clickOnCartIcon();
		assertEquals(addProductIntoCart.verifyCartQuantity(),"1");
	}
}
