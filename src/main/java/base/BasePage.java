package base;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
	
	protected WebDriver driver = null;
		
	WebDriverWait wait ;
	JavascriptExecutor js;
	final long explicitWait = 30;
	
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	protected WebElement getElement(By by) {
		wait = new WebDriverWait(driver, explicitWait);
		js = (JavascriptExecutor) driver;
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(by));
		return driver.findElement(by);
	}
	
	protected void switchToWindow(int windowIndex) {		
		Set<String> winHandlers = driver.getWindowHandles();
		driver.switchTo().window(winHandlers.toArray()[windowIndex].toString());		
	}
	
	protected void selectDropdownOption(By by, String optionTitle) {
		WebElement element = getElement(by);
		Select dropdown = new Select(element); 
		dropdown.deselectByVisibleText(optionTitle);
	}

}
