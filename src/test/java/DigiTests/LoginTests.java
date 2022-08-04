package DigiTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.security.auth.x500.X500Principal;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.annotations.Parameters;

import Pages.LoginPage;
import base.BaseTest;
import Pages.LandingPage;

public class LoginTests extends BaseTest{


	
	@Test(testName = "validacredentials", priority = 0, groups = "smoke")
	public void validateUserLoginWithValidCredentials() throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.performUserLogin(excelTestData.get("UserID"), excelTestData.get("Password"));
		LandingPage landingPage = new LandingPage(loginPage.getDriver());
		landingPage.validateLogoutButton();
	}
	
	@Test(priority = 1, groups = "regression")
    public void validateUserLoginWithInvalidCredentials() throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.performUserLogin(excelTestData.get("InvalidUserId"), excelTestData.get("InvalidPassword"));
		LandingPage landingPage = new LandingPage(loginPage.getDriver());
		landingPage.validateLogoutButton();
	}
	
	@Test(priority = 2, groups = "regression")
    public void validateUserLoginWithvalidUserIdAndInvalidPassword() throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.performUserLogin("SADMIN", "222");
		LandingPage landingPage = new LandingPage(loginPage.getDriver());
		landingPage.validateLogoutButton();
	}
	
}
