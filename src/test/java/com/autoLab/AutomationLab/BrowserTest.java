package com.autoLab.AutomationLab;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.AutoMainPage;
import utilities.DriverSetup;
import utilities.ExcelReader;

public class BrowserTest {
	
	Logger LOG = LoggerFactory.getLogger(DriverSetup.class);
	WebDriver driver = null;
	
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
	public void searchPythonPageTest() throws IOException {
		ExcelReader sheet = new ExcelReader();
		AutoMainPage mainPage = new AutoMainPage(driver);
		
		driver.get(sheet.readData("url"));
		//mainPage.searchItem("Array");
		LOG.info("Searched for item");
	}

}
