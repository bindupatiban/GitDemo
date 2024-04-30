package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;

import rahulshettyacademy.Abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent{
			
		WebDriver driver;
		
		@FindBy(css=".totalRow button")
		WebElement checkoutEle;
		
		@FindBy(css=".cartSection h3")
		List<WebElement> cartProducts;
		
		
		public CartPage(WebDriver driver) 
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
			

		public boolean VerifyProductDetails(String productName)
		{
			Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
			return match;
		}
		
		public CheckOutPage goToCheckout()
		{
			checkoutEle.click();
			return new CheckOutPage(driver);
		}
	}

