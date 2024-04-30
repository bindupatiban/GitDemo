package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class ErrorValidations extends BaseTest {

	String productName = "ADIDAS ORIGINAL";
	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException
	{
		
		LandingPage landingPage = LaunchApplication();					
		landingPage.LoginApplication("bindupatiban@yahoo.com", "Sairam!s");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email password.");
			
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
		LandingPage landingPage = LaunchApplication();					
		ProductCatalog productCatPage = landingPage.LoginApplication("bindupatiban@yahoo.com", "Sairam79!s");
		
		List<WebElement> products = productCatPage.getProductList();
		productCatPage.addProductToCart(productName);
		
		CartPage cartPage = productCatPage.goToCartPage();
		Boolean match = cartPage.VerifyProductDetails("ADIDAS ORIGINAL");
		Assert.assertTrue(match);		
			
	}


}
