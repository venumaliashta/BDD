Feature: Add Product into Cart

  Scenario: Login Safeway with valid credetials
    When User enter userName on userName field_
    When User enter password on password field_
    Then User enter click on login button_
    Then add Product into cart
    Then Check the quantity should be one
