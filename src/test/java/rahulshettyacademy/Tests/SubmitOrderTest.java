package rahulshettyacademy.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider="getData", groups = {"Purchase"} )
	public void SubmitOrder(HashMap <String,String> input) throws IOException, InterruptedException
	{
		LandingPage landingPage = LaunchApplication();					
		ProductCatalog productCatPage = landingPage.LoginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products = productCatPage.getProductList();
		productCatPage.addProductToCart(input.get("product"));
		
		CartPage cartPage = productCatPage.goToCartPage();
		Boolean match = cartPage.VerifyProductDetails(input.get("product"));
		Assert.assertTrue(match);
		
		CheckOutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.SelectCountry("United");
		
		ConfirmationPage confirmationPage = checkoutPage.SubmitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

	@Test(dependsOnMethods={"SubmitOrder"})
	public void orderHistoryTest()
	{
		//Check if ADIDAS ORIGINAL is in the Order History
		ProductCatalog productCatPage = landingPage.LoginApplication("bindupatiban@yahoo.com", "Sairam79!s");
		OrderPage ordersPage = productCatPage.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDetails(productName));
	}
	
	

	//Extent Reports
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//List<HashMap<String, String>> data = getJsonDataToMap("C://Raghu//bindu//cucumber//eclipse//SeleniumFrameworkDesign//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + 
				"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
	
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
}
	
//	@DataProvider
//	public Object[][] getData() throws IOException
//	{
//		HashMap <String,String> map1 = new HashMap <String,String>();
//		map1.put("email", "bindupatiban@yahoo.com");
//		map1.put("password", "Sairam79!s");
//		map1.put("product", "ZARA COAT 3");
//		
//		HashMap <String,String> map2 = new HashMap <String,String>();
//		map2.put("email", "text@test.com");
//		map2.put("password", "Pat1234!");
//		map2.put("product", "ADIDAS ORIGINAL");
//	}
//	@DataProvider
//	public Object[][] getData()
//	{
//		
//
//		return new Object[][] {{"bindupatiban@yahoo.com","Sairam79!s","ZARA COAT 3"},{"text@test.com","Pat1234!","ADIDAS ORIGINAL"}};
//	}
	

