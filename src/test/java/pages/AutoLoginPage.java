package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

public class AutoLoginPage {
	
	//Driver and logger
	WebDriver driver;
	
	public AutoLoginPage(WebDriver driver) {
		this.driver = driver;	
	}
	
	//Locators
	private By createEmailField = By.id("email_create");
	private By loginBtn = By.xpath("//*[@class='login']");
	private By createAccBtn = By.id("SubmitCreate");
	private By genderM = By.id("id_gender1");
	private By firstName = By.id("customer_firstname");
	private By lastName = By.id("customer_lastname");
	private By passW = By.id("passwd");
	private By daysSelect = By.id("days");
	private By monthsSelect = By.id("months");
	private By yearsSelect = By.id("years");
	private By company = By.id("company");
	private By address = By.id("address1");
	private By city = By.id("city");
	private By state = By.id("id_state");
	private By zipCode = By.id("postcode");
	private By mPhone = By.id("phone_mobile");
	private By submitAccountData = By.id("submitAccount");
	private By accountMessage = By.xpath("//p[@class='info-account']");
	private By invalidMailMessage = By.xpath("//div[@id='create_account_error']//li");
	private By mandatoryFieldError = By.xpath("//div[@class='alert alert-danger']");
	private By invalidData = By.xpath("(//div[@class='alert alert-danger']//li[contains(.,'invalid')])");
	private By loginEmail = By.id("email");
	private By loginPassword = By.id("passwd");
	private By loginButton = By.id("SubmitLogin");

	
	//Click on x thing
	public void validateUrl(String url) {
		Assert.assertEquals("URL is incorrect", driver.getCurrentUrl(), url);
	}
	
	/*
	 * Clicks on the sign in button on the main page
	 * @Author: AZR
	 */
	public void clickOnSigIn() {
		driver.findElement(loginBtn).click();
	}
	
	/*
	 * Enters a randomized email on the create an account field
	 * @Author: AZR
	 */
	public void enterRandEmail(String email) {
		String randStr = RandomStringUtils.randomAlphanumeric(5);
		driver.findElement(createEmailField).sendKeys(email + randStr + "@go.com");
	}
	
	/*
	 * Click on the create account button
	 */
	public void clickOnCreateAccount() {
		driver.findElement(createAccBtn).click();
	}
	
	/*
	 * Enter the personal information
	 * 
	 */
	public void enterPersonalInfo(String name, String lname, String password, String dayBirth, String monthBirth, String yearBirth) {
		driver.findElement(genderM).click();
		driver.findElement(firstName).sendKeys(name);
		driver.findElement(lastName).sendKeys(lname);
		driver.findElement(passW).sendKeys(password);
		
	    Select day = new Select(driver.findElement(daysSelect));
	    day.selectByValue(dayBirth);
	    
	    Select month = new Select(driver.findElement(monthsSelect));
	    month.selectByValue(monthBirth);
	    
	    Select year = new Select(driver.findElement(yearsSelect));
	    year.selectByValue(yearBirth);
	}
	
	
	/*
	 * Enter address information
	 * 
	 */
	public void enterAddressInfo(String comp, String addr, String cty, String stat, String zip, String phone) {
		driver.findElement(company).sendKeys(comp);
		driver.findElement(address).sendKeys(addr);
		driver.findElement(city).sendKeys(cty);
	    Select st = new Select(driver.findElement(state));
	    st.selectByValue(stat);
	    driver.findElement(zipCode).sendKeys(zip);
	    driver.findElement(mPhone).sendKeys(phone);
	}
	
	/*
	 * Click on the register button
	 */
	public void ClickRegister() {
		driver.findElement(submitAccountData).click();
	}
	
	/*
	 * Validate that an account was created
	 */
	public void ValidateCreatedAcc() {
		String welcomeTxt = driver.findElement(accountMessage).getText();
		Assert.assertTrue("Account not created", welcomeTxt.contains("Welcome to your account"));
	}
	
	/*
	 * Enters an email on the create an account field
	 * @Author: AZR
	 */
	public void enterEmail(String email) {
		driver.findElement(createEmailField).sendKeys(email);
	}
	
	/*
	 * Validate that an error message pops up when entering an invalid email
	 */
	public void ValidateInvalidMailMessage() {
		String errorMsg = driver.findElement(invalidMailMessage).getText();
		Assert.assertTrue("Message not displayed", errorMsg.contains("Invalid email address"));
	}
	
	/*
	 * Validate that a message box with errors pops up when attempting to create an account wihtout the mandatory fields
	 */
	public void ValidateMandatoryFieldsError() {
		Assert.assertTrue("Message not displayed", driver.findElement(mandatoryFieldError).isDisplayed());
	}
	
	/*
	 * Validate that an error message pops up when entering invalid data
	 */
	public void ValidateInvalidDataMessages() {
		List<WebElement> errorMsg = driver.findElements(invalidData);
		Assert.assertTrue("Invalid messages are not displayed", errorMsg.size() > 0);
	}
	
	/*
	 * Login to the website
	 */
	public void signIn(String email, String password) {
		driver.findElement(loginEmail).sendKeys(email);
		driver.findElement(loginPassword).sendKeys(password);
		driver.findElement(loginButton).click();
	}

}
