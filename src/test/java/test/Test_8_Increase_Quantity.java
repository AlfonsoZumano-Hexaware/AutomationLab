package test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pages.AutoLoginPage;
import pages.AutoMainPage;
import pages.CheckoutPage;
import pages.ItemPage;
import pages.TShirtsPage;
import utilities.DriverSetup;
import utilities.ExcelReader;

public class Test_8_Increase_Quantity {
	
	WebDriver driver = null;
	ExtentSparkReporter htmlreporter = new ExtentSparkReporter("Test_8_Report.html");
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
	public void IncreaseQntyTest() throws IOException, InterruptedException {		
		ExcelReader sheet = new ExcelReader();
		AutoMainPage mainPage = new AutoMainPage(driver);
		TShirtsPage shirtsPage = new TShirtsPage(driver);
		AutoLoginPage loginPage = new AutoLoginPage(driver);
		ItemPage itemPage = new ItemPage(driver);
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		//Test case creation for reporter
		userTest = extent.createTest("Purchase item", "Validation of the purchase functionality");
		
		
		driver.get(sheet.readData("url"));
		mainPage.validateUrl(sheet.readData("url"));
		userTest.pass("Step 1: Open this url: http://automationpractice.com/index.php");
		
		mainPage.clickOnSigIn();
		loginPage.signIn(sheet.readData("loginMail"), sheet.readData("loginPass"));
		userTest.pass("Step 2: Login to the website");
		
		mainPage.hoverWomenCategory();
		userTest.pass("Step 3: Move your cursor over Women's link.");
		
		mainPage.clickOnTshirtsSubmenu();
		userTest.pass("Step 4: Click on sub menu T-shirts.");
		
		shirtsPage.hoverFirstCategoryItem();
		userTest.pass("Step 5: Mouse hover on the second product displayed.");
		
		shirtsPage.clickOnMoreButton();
		userTest.pass("Step 6: 'More' button will be displayed, click on 'More' button.");
		
		userTest.pass("Step 7: Make sure quantity is set to 1.");
		
		itemPage.selectSize(sheet.readData("sizeM"));
		userTest.pass("Step 8: Select size 'M'");
		
		itemPage.selectColor();
		userTest.pass("Step 9: Select color");
		
		itemPage.clickOnAddToCartButton();
		userTest.pass("Step 10: Click 'Add to Cart' button.");
		
		itemPage.clickOnProceedToCheckoutButton();
		userTest.pass("Step 11: Click 'Proceed to checkout' button.");
		
		String firstItem = checkoutPage.getTotalPriceValue();
		String newFirst = firstItem.replace("$", "");
		checkoutPage.IncreaseQnty(sheet.readData("checkoutQty"));
		userTest.pass("Step 12: Change the quantity to 2");
		
		String increase = checkoutPage.getTotalPriceValue();
		String newIncrease = increase.replace("$", "");
		checkoutPage.validateIncreasedQntyBy2(newFirst, newIncrease);
		userTest.pass("Step 13: Verify that Total price is changing and reflecting correct price.");
		
		//Generate report
		extent.flush();
	}

}
