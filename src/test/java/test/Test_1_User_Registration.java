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

public class Test_1_User_Registration {
	
	WebDriver driver = null;
	ExtentSparkReporter htmlreporter = new ExtentSparkReporter("Test_1_Report.html");
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
	public void UserRegistrationTest() throws IOException {		
		ExcelReader sheet = new ExcelReader();
		AutoMainPage mainPage = new AutoMainPage(driver);
		AutoLoginPage loginPage = new AutoLoginPage(driver);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		//Test case creation for reporter
		userTest = extent.createTest("User creation", "Creation of a user in the platform");
		
		
		driver.get(sheet.readData("url"));
		mainPage.validateUrl(sheet.readData("url"));
		userTest.pass("Step 1: Open this url: http://automationpractice.com/index.php");
		
		mainPage.clickOnSigIn();
		userTest.pass("Step 2: Click on sign in link.");
		
		loginPage.enterRandEmail(sheet.readData("ranEmail"));
		userTest.pass("Step 3: Enter your email address in 'Create and account' section.");
		
		loginPage.clickOnCreateAccount();
		userTest.pass("Step 4: Click on create an account button.");
		
		loginPage.enterPersonalInfo(sheet.readData("name"), sheet.readData("lastn"), sheet.readData("pass"), sheet.readData("bday"), sheet.readData("bmonth"), sheet.readData("byear"));
		loginPage.enterAddressInfo(sheet.readData("company"), sheet.readData("address"), sheet.readData("city"), sheet.readData("state"), sheet.readData("zip"), sheet.readData("phone"));
		userTest.pass("Step 5: Enter your Personal Information, Address and Contact info.");
		
		loginPage.ClickRegister();
		userTest.pass("Step 6: Click on Register button.");
		
		loginPage.ValidateCreatedAcc();
		userTest.pass("Step 7: Validate that user is created.");
		
		//Generate report
		extent.flush();
	}

}
