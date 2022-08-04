package common;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Report {
	
	private static ExtentReports report;
	private static ExtentTest test;
	
	public static void initializeReport() {
		report = new ExtentReports(System.getProperty("user.dir") + "\\Reports\\Report.html");
	}
	
	public static void addTestToReport(String methodName) {
		test = report.startTest(methodName);
	}
	
	public static void closeTest() {
		report.endTest(test);
	}
	
	public static void flushReport() {
		report.flush();
	}
	
	public static void PASS(String message) {
		test.log(LogStatus.PASS, message);
	}
	
	public static void FAIL(String message) {
		test.log(LogStatus.FAIL, message);
	}

	public static void INFO(String message) {
		test.log(LogStatus.INFO, message);
	}
}
