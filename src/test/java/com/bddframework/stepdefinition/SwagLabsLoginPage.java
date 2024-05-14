package com.bddframework.stepdefinition;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import com.bddautomation.pom.POM_SwagLabsLoginPage;
import com.bddframework.base.Base;
import com.bddframework.base.ObjectRepo;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SwagLabsLoginPage extends Base{
	POM_SwagLabsLoginPage swagLabsLogin = new POM_SwagLabsLoginPage(this.driver);

	@When("User enter userName on userName field")
	public void user_enter_user_name_on_user_name_field() {
		swagLabsLogin.enterUserName("standard_user");
	}
	@When("User enter password on password field")
	public void user_enter_password_on_password_field() {
		swagLabsLogin.enterPassword("secret_sauce");
	}
	@Then("User enter click on login button")
	public void user_enter_click_on_login_button() {
		swagLabsLogin.clickOnLoginBtn();
		assertEquals(driver.getTitle(),"Swag Labs");
		
	}
}
