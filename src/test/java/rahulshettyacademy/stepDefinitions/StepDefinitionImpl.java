package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalog productCatPage;
	public ConfirmationPage confirmationPage;
	
	@Given ("I landed on the Ecommerce Page")
	public void I_landed_on_the_Ecommerce_Page() throws IOException
	{
		landingPage = LaunchApplication();
	}

	@Given("logged in with username (.+) and password (.+)")
	public void logged_in_username_and_password(String username, String password)
	{
		productCatPage = landingPage.LoginApplication(username,password);

	}
	
	
	@When ("I add the product (.+) to Cart")
	public void Add_Product_To_Cart(String paoductName) throws InterruptedException
	{
		List<WebElement> products = productCatPage.getProductList();
		productCatPage.addProductToCart(paoductName);
	}
	
	@When ("Checkout (.+) and Submit the order")
	public void Checkout_And_Submit_Order(String paoductName)
	{
		CartPage cartPage = productCatPage.goToCartPage();
		Boolean match = cartPage.VerifyProductDetails(paoductName);
		Assert.assertTrue(match);
		
		CheckOutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.SelectCountry("United");
		
		confirmationPage = checkoutPage.SubmitOrder();
	}
	
	@Then  ("(.+) message is displayed on the ConfirmtionPage")
	public void Message_Displayed_On_ConfirmationPage(String message)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));	
	}
	
}
