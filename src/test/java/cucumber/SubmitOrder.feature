@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on the Ecommerce Page
	
  @tag2
  Scenario Outline: Positive Test for Submitting the Order
    Given I logged in with username <username> and password <password>
    When I add the product <paoductName> to Cart
    And Checkout <paoductName> and Submit the order
    Then <message> is displayed on the ConfirmtionPage

    Examples: 
      | username               | password  | paoductName        |message                |
      | bindupatiban@yahoo.com |Sairam79!s | ADIDAS ORIGINAL    |THANKYOU FOR THE ORDER.|
      
