import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
By lnkLogout = By.xpath("//a[text()=' Logout ']");
	
	WebDriver driver = null;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;	
	}
	
	public void validateLogoutButton() {
		
		WebElement logoutButton = driver.findElement(lnkLogout);
		
		if(logoutButton != null) {
			System.out.println("Logout button is visible, TEST IS PASSED");
		} else {
			System.out.println("Logout button is not visible, TEST IS FAILED");
		}
		
	}
}
