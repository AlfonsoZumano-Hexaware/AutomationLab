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

public class Test_3_Mandatory_Fields {
	
	WebDriver driver = null;
	ExtentSparkReporter htmlreporter = new ExtentSparkReporter("Test_3_Report.html");
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
	public void MandatoryFieldsTest() throws IOException {		
		ExcelReader sheet = new ExcelReader();
		AutoMainPage mainPage = new AutoMainPage(driver);
		AutoLoginPage loginPage = new AutoLoginPage(driver);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		//Test case creation for reporter
		userTest = extent.createTest("Mandatory fields", "Testing the mandatory fields validations for user creation");
		
		
		driver.get(sheet.readData("url"));
		mainPage.validateUrl(sheet.readData("url"));
		userTest.pass("Step 1: Open this url: http://automationpractice.com/index.php");
		
		mainPage.clickOnSigIn();
		userTest.pass("Step 2: Click on sign in link.");
		
		loginPage.enterRandEmail(sheet.readData("ranEmail"));
		loginPage.clickOnCreateAccount();
		userTest.pass("Step 3: Enter email address and click Register button.");
		
		loginPage.enterPersonalInfo(sheet.readData("name"), sheet.readData("lastn"), sheet.readData("pass"), sheet.readData("bday"), sheet.readData("bmonth"), sheet.readData("byear"));
		loginPage.ClickRegister();
		userTest.pass("Step 4: Enter incorrect values in fields like., enter numbers in first and last name, city field etc., and enter alphabets in Mobile no, Zip postal code etc., and click on 'Register' button.");
		
		loginPage.ValidateMandatoryFieldsError();
		userTest.pass("Step 5: Verify that error messages fpr respective fields are displaying.");
		
		//Generate report
		extent.flush();
	}

}
