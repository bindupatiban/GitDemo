package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.Abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
			
		WebDriver driver;
		
		public LandingPage(WebDriver driver) 
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}

		//driver.findElement(By.id("userEmail")).sendKeys("bindupatiban@yahoo.com");
		//driver.findElement(By.id("userPassword")).sendKeys("Sairam79!s");
		//Page Factory
		@FindBy(id="userEmail")
		WebElement userEmail;
		
		@FindBy(id="userPassword")
		WebElement passwordEle;
		
		@FindBy(id="login")
		WebElement login;
		
		@FindBy(css="[class*='flyInOut']")
		WebElement errorMessage;

		public ProductCatalog LoginApplication(String email, String password) {
			userEmail.sendKeys(email);
			passwordEle.sendKeys(password);
			login.click();
			ProductCatalog productCatPage = new ProductCatalog(driver); 
			return productCatPage;
		}
		
		public String getErrorMessage()
		{
			waitForWebElementToAppear(errorMessage);
			return errorMessage.getText();
		}
		
		public void goTo() {
			driver.get("https://rahulshettyacademy.com/client");
		}
	}

