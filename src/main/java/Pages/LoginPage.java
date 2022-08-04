package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import base.BasePage;
import common.Report;

public class LoginPage extends BasePage{
	
	//locators

	
	//dynamic locator
	String sActionIcons = "//td[text()='@@']/following-sibling::td//span[contains(@class,'**')]";
	By txtUserName = By.id("txtUserID");
	By txtPassword = By.id("txtPassword");
	By btnLogin = By.xpath("//input[@type='submit']");
	By categoryTitleStatus = By.xpath("//td[text()='123']/following-sibling::td[text()='ACTIVE']");
	By categoryActionsIcons = By.xpath("//td[text()='123']/following-sibling::td//span[contains(@class,'trash')]");


	//constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void performUserLogin(String userName, String password) {
		getElement(txtUserName).sendKeys(userName);		
		Report.PASS("Entered username as " + userName);
		
		getElement(txtPassword).sendKeys(password);		
		Report.PASS("Entered password as " + password);
		
		getElement(btnLogin).click();
	}
}
