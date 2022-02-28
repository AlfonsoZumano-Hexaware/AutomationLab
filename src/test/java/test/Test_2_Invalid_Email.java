package test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pages.AutoMainPage;
import pages.AutoLoginPage;
import utilities.DriverSetup;
import utilities.ExcelReader;

public class Test_2_Invalid_Email {
	
	WebDriver driver = null;
	ExtentSparkReporter htmlreporter = new ExtentSparkReporter("Test_2_Report.html");
	ExtentTest userTest = null;
	
	@BeforeTest
	public void setUp() {
		DriverSetup driverSetUp = new DriverSetup();
		driver = driverSetUp.setUpDriver("chrome");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void InvalidEmailTest() throws IOException {		
		ExcelReader sheet = new ExcelReader();
		AutoMainPage mainPage = new AutoMainPage(driver);
		AutoLoginPage loginPage = new AutoLoginPage(driver);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		//Test case creation for reporter
		userTest = extent.createTest("Invalid email", "Validation of error message when entering an invalid email");
		
		
		driver.get(sheet.readData("url"));
		mainPage.validateUrl(sheet.readData("url"));
		userTest.pass("Step 1: Open this url: http://automationpractice.com/index.php");
		
		mainPage.clickOnSigIn();
		userTest.pass("Step 2: Click on sign in link.");
		
		loginPage.enterEmail(sheet.readData("invalidMail"));
		loginPage.clickOnCreateAccount();
		userTest.pass("Step 3: Enter invalid email address in the email box and click enter.");
		
		loginPage.ValidateInvalidMailMessage();
		userTest.pass("Step 4: Validate that an error message is displaying saying \"Invalid email address.\"");
		
		//Generate report
		extent.flush();
	}

}
