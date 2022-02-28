package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class AutoMainPage {
	//Driver and logger
	WebDriver driver;
	
	
	public AutoMainPage(WebDriver driver) {
		this.driver = driver;	
	}
	
	//Locators
	private By loginBtn = By.xpath("//*[@class='login']");
	private By womenLink = By.xpath("//a[@title='Women']");
	private By tshirtsLink = By.xpath("//li/ul/li/a[@title='T-shirts']");
	
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
	 * Hovers the mouse over the woman category to display the menu
	 */
	public void hoverWomenCategory() {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(womenLink)).perform();
	}
	
	/*
	 * Click on the t shirts link displayed in the woman's section
	 */
	public void clickOnTshirtsSubmenu() {
		driver.findElement(tshirtsLink).click();
	}

}
