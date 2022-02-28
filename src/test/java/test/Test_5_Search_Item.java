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
import pages.SearchPage;
import utilities.DriverSetup;
import utilities.ExcelReader;


public class Test_5_Search_Item {
	
	WebDriver driver = null;
	ExtentSparkReporter htmlreporter = new ExtentSparkReporter("Test_5_Report.html");
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
	public void SearchItemTest() throws IOException {		
		ExcelReader sheet = new ExcelReader();
		AutoMainPage mainPage = new AutoMainPage(driver);
		TShirtsPage shirtsPage = new TShirtsPage(driver);
		SearchPage searchPage = new SearchPage(driver);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		//Test case creation for reporter
		userTest = extent.createTest("Search item", "Validation of the serach functionality");
		
		
		driver.get(sheet.readData("url"));
		mainPage.validateUrl(sheet.readData("url"));
		userTest.pass("Step 1: Open this url: http://automationpractice.com/index.php");
		
		mainPage.hoverWomenCategory();
		userTest.pass("Step 2: Move your cursor over Women's link.");
		
		mainPage.clickOnTshirtsSubmenu();
		userTest.pass("Step 3: Click on sub menu T-shirts.");
		
		shirtsPage.getFirstCategoryItemText();
		userTest.pass("Step 4: Get Name/Text of the first product displayed on the page.");
		
		shirtsPage.searchItem(shirtsPage.getFirstCategoryItemText());
		userTest.pass("Step 5: Now enter the same product name in the search bar present on top of page and click search button.");
		
		searchPage.validateItemFromSearch(shirtsPage.getFirstCategoryItemText());
		userTest.pass("Step 6: Validate that same product is displayed on searched page with same details which were displayed on T-Shirt's page.");
		
		//Generate report
		extent.flush();
	}

}
