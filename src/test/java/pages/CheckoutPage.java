package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
	//Driver and logger
	WebDriver driver;
	
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;	
	}
	
	//Locators
	private By procedToCheckoutBtn = By.xpath("//button[@class= 'button btn btn-default button-medium']|//button[@class= 'button btn btn-default standard-checkout button-medium']|//a[@class='button btn btn-default standard-checkout button-medium']");
	private By termsConditionsCheckbox = By.xpath("//input[@type= 'checkbox']");
	private By banWireBtn = By.xpath("//*[@class='bankwire']");
	private By orderText = By.xpath("//*[contains(text(),'Your order on My Store is complete')]");
	private By totalPrice = By.id("total_price");
	private By qntyInput = By.xpath("//*[@class='cart_quantity_input form-control grey']");
	
	/*
	 *Click on the proceed to checkout button
	 */
	public void clickOnProceedToCheckoutButton() {
		driver.findElement(procedToCheckoutBtn).click();
		
	}
	
	/*
	 *Click on the accept terms and conditions checkbox
	 */
	public void clickOnAcceptTermsAndConditions() {
		driver.findElement(termsConditionsCheckbox).click();
		
	}
	
	/*
	 *Click on the bank wire payment method
	 */
	public void clickOnBankWire() {
		driver.findElement(banWireBtn).click();
		
	}
	
	/*
	 *Validate order
	 */
	public void validatePurchaseOrder() {
		Assert.assertTrue("Order was not placed", driver.findElement(orderText).isDisplayed());
		
	}
	
	/*
	 *Increase the qnty in the checkout
	 */
	public void IncreaseQnty(String qty) {
		driver.findElement(qntyInput).clear();
		driver.findElement(qntyInput).sendKeys(qty);
	}
	
	/*
	 * Get the text of the total price in checkout
	 */
	public String getTotalPriceValue() throws InterruptedException {
		Thread.sleep(5000);
		String totalPrc = driver.findElement(totalPrice).getText();
		Thread.sleep(5000);
		return totalPrc;
	}
	
	/*
	 * Validate that the increased price matches
	 */
	public void validateIncreasedQntyBy2(String firstQty, String incr) {
		float increasedPrc = Float.parseFloat(incr.replaceAll("$", ""));
		float newPrice = Float.parseFloat(firstQty.replaceAll("$", "")) * 2;
		
		//System.out.println(increasedPrc);
		//System.out.println(newPrice);
		
		Assert.assertEquals("Price did not match", newPrice, increasedPrc, 3);
	}
}
