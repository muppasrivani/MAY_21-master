package base;

import java.io.FileInputStream;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
import org.testng.annotations.Parameters;

import common.Report;

public class BaseTest {
	
	protected WebDriver driver = null;
	String projectPath = System.getProperty("user.dir");
	Properties envProp = null;
	protected HashMap<String, String> excelTestData = null;	
	
	@Parameters({"browser", "env"})
	@BeforeSuite
	public void beforeSuite(String browser, String env) {
		System.out.println("Before suite");
		loadEnvironementData(env);
		loadTestData();
		Report.initializeReport();
	}
	
	@AfterSuite
	public void afterSuite() {
		Report.flushReport();		
	}
		
	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test");
	}
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("Before Class");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("After Test");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("After Class");
	}
	
	public void loadTestData() {
		try {	
			
			FileInputStream fs = new FileInputStream(projectPath + "\\testdata\\TestData.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(fs); 
			XSSFSheet sheet = wb.getSheet("LoginData");
			Iterator<Row> rows = sheet.iterator();
			
			excelTestData = new HashMap<String, String>();
			List<String> keys = new ArrayList<String>();
			List<String> values = new ArrayList<String>();
			
			boolean isHeader = true;
			
			while(rows.hasNext()) {		
				XSSFRow eachRow = (XSSFRow) rows.next();
				Iterator<Cell> cells = eachRow.iterator();
				while(cells.hasNext()){
					XSSFCell eachCell = (XSSFCell) cells.next();
					if(isHeader) {
						keys.add(readCellValue(eachCell));
				    } else {
				    	values.add(readCellValue(eachCell));
				    }
				}	
				
				isHeader = false;
			}
			
			for(int index =0; index < keys.size(); index++) {
			    excelTestData.put(keys.get(index), values.get(index));
			}
			
		}catch(Exception ex) {
			System.out.println("Not able to read test data from excel");
		}
	}
	
	public String readCellValue(Cell cell) {
		String cellValue = null;
		
		switch(cell.getCellType()) {
			case Cell.CELL_TYPE_STRING : cellValue = new DataFormatter().formatCellValue(cell);
			  break;
			case Cell.CELL_TYPE_NUMERIC : cellValue = new DataFormatter().formatCellValue(cell);
			  break;
		}
		
		return cellValue;
	}
	
	@Parameters({"browser", "env"})
	@BeforeMethod
	public void beforeMethod(Method method, String browser, String env) {	
		Report.addTestToReport(method.getName());		
		
		String driverPath = projectPath + "\\drivers\\chromedriver.exe";
		String browserName = browser;
		Report.INFO("Initializing browser: " + browserName );		
		switch(browserName.toLowerCase()) {
		  case "chrome": 
			  System.setProperty("webdriver.chrome.driver", driverPath);
			  driver = new ChromeDriver();
		  	break;
		  case "firefox": 
			  System.setProperty("webdriver.gecko.driver", driverPath);
			  driver = new FirefoxDriver();
		  	break;
		}
		driver.manage().window().maximize();
		Report.PASS("Browser initialization is completetd");
		driver.get(String.valueOf(envProp.get("appurl")));
		Report.PASS("Application is launched: " + envProp.get("appurl"));
		//Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		System.out.println("After Method");
		if (result.getStatus() == ITestResult.SUCCESS) { // Test passed with out any interruption
			Report.PASS("Test is Passed");
		} else if(result.getStatus() == ITestResult.FAILURE) { // Test passed with out any interruption
			Report.FAIL("Test is failed");
			Report.FAIL(result.getThrowable().getLocalizedMessage());
		}
		
		Report.closeTest();
		
		if(driver != null) {
			driver.close();
			driver.quit();
		}
	}
	
	private void loadEnvironementData(String environment) {
		
		try {
			FileReader reader = new FileReader(projectPath+"\\src\\test\\resources\\"+environment+".properties");
			envProp = new Properties();
			envProp.load(reader);
			
			System.out.println(envProp.get("appurl"));
			
		}catch(Exception ex) {
			System.out.println("Error occured while reading environment file");
		}
	}
	
}
