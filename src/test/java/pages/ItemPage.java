package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ItemPage {
	//Driver and logger
	WebDriver driver;
	
	
	public ItemPage(WebDriver driver) {
		this.driver = driver;	
	}
	
	//Locators
	private By quantityInput = By.id("quantity_wanted");
	private By sizeSelect = By.id("group_1");
	private By itemColorBlue = By.xpath("//*[@name='Blue']");
	private By addToCartBtn = By.xpath("//*[@name='Submit']");
	private By procedToCheckoutBtn = By.xpath("//*[@title='Proceed to checkout']");
	
	
	/*
	 * select the quantity for the item
	 */
	public void setQuantity(String item) {
		driver.findElement(quantityInput).clear();
		driver.findElement(quantityInput).sendKeys(item);
		
	}
	
	/*
	 * Select the size of the item
	 */
	public void selectSize(String size) {
	    Select day = new Select(driver.findElement(sizeSelect));
	    day.selectByValue(size);
	}
	
	/*
	 *Select an item color
	 */
	public void selectColor() {
		driver.findElement(itemColorBlue).click();
		
	}
	
	/*
	 *Click on the add to cart button
	 */
	public void clickOnAddToCartButton() {
		driver.findElement(addToCartBtn).click();
		
	}
	
	/*
	 *Click on the proceed to checkout button
	 */
	public void clickOnProceedToCheckoutButton() {
		driver.findElement(procedToCheckoutBtn).click();
		
	}
}
