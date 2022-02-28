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
import pages.TShirtsPage;
import utilities.DriverSetup;
import utilities.ExcelReader;

public class Test_7_Wishlist_Account {
	
	WebDriver driver = null;
	ExtentSparkReporter htmlreporter = new ExtentSparkReporter("Test_7_Report.html");
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
	public void WishlistTest() throws IOException {		
		ExcelReader sheet = new ExcelReader();
		AutoMainPage mainPage = new AutoMainPage(driver);
		TShirtsPage shirtsPage = new TShirtsPage(driver);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		//Test case creation for reporter
		userTest = extent.createTest("Add to wishlist", "Verify that add to wishlist only works after login");
		
		
		driver.get(sheet.readData("url"));
		mainPage.validateUrl(sheet.readData("url"));
		userTest.pass("Step 1: Open this url: http://automationpractice.com/index.php");
		
		mainPage.hoverWomenCategory();
		userTest.pass("Step 2: Move your cursor over Women's link.");
		
		mainPage.clickOnTshirtsSubmenu();
		userTest.pass("Step 3: Click on sub menu T-shirts.");
		
		shirtsPage.hoverFirstCategoryItem();
		userTest.pass("Step 4: Mouse hover on the second product displayed.");
		
		shirtsPage.clickOnAddToWishlist();
		userTest.pass("Step 5: ''Add to Wishlist' will appear on the bottom of that product, click on it.");
		
		shirtsPage.validateMustBeLoggedError();
		userTest.pass("Step 6: Verify that error message is displayed 'You must be logged in to manage your wish list.'");
		
		//Generate report
		extent.flush();
	}
}
