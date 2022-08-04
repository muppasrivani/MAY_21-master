package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LandingPage {
	
By lnkLogout = By.xpath("//a[text()=' Logout ']/");
	
	WebDriver driver = null;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;	
	}
	
	public void validateLogoutButton() {
		
		WebElement logoutButton = driver.findElement(lnkLogout);
		
		Assert.assertNotNull(logoutButton, "Logout button is not visible");
		
	}

}
